<template>
  <MainNavbar v-if="!session" />
  <VideoNavBar v-if="session" />
  <div
    id="main-container"
    :class="{ 'container-2': !session, 'containser-3': session }"
  >
    <div class="non-matching_bg" v-if="!session">
      <div class="matching-title pt-5">
        <p>친구 찾기</p>
      </div>
      <div class="matching-div">
        <div class="matching_btn">
          <div
            class="hate_mbti"
            style="cursor: pointer"
            @click="mbtiModalOpen = true"
          >
            싫어하는 MBTI
            <p v-if="mbtiModalOpen == true">{{ userHateMbtitList }}</p>
          </div>
          <div class="matching">
            <button
              @click="[matchingReqeust(), (matching_ing = true)]"
              v-if="!matching_ing"
            >
              매칭
            </button>
            <button
              @click="[cancleMatchingReqeust(), (matching_ing = false)]"
              v-if="matching_ing"
            >
              매칭 취소
            </button>
          </div>
        </div>
      </div>
    </div>
    <SelectHateMbti v-if="mbtiModalOpen == true" v-on:selectmbti="selectmbti" />
    <div v-if="session" class="container-fluid">
      <div class="video-chat-box row mt-3">
        
        <!-- video 부분 -->
        <div class="bodies">
          <div class="videos col-9" v-if="session">
            <button v-if="session" @click="leaveSession" :class="{ 'btn btn-danger btn-moving': !talkStatus && !gameStatus || talkStatus, 'btn btn-danger btn-moving2': !talkStatus && gameStatus }">방 나가기</button>
            <div class="video col">
              <!-- video1 -->
              <!-- <div class="video"> -->
              <user-video
                class="video-section"
                :stream-manager="mainStreamManager"
                v-bind:catchMindStatus="this.catchMindStatus"
              />
            </div>
            <!-- video2 -->
            <div class="video col">
              <user-video
                class="video-section"
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                v-bind:catchMindStatus="this.catchMindStatus"
                @click="updateMainVideoStreamManager(sub)"
              />
            </div>
            <!-- </div> -->
          </div>
          <div class="col-3">
            <div class="card2">
              <!-- 처음 진입시 -->

              <div v-if="!talkStatus && !gameStatus">
                <div class="card-container">
                  <div
                    id="carouselExampleIndicators"
                    class="carousel slide"
                    data-bs-ride="carousel"
                  >
                    <div class="carousel-indicators">
                      <button
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="0"
                        class="active"
                        aria-current="true"
                        aria-label="Slide 1"
                      ></button>
                      <button
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="1"
                        aria-label="Slide 2"
                      ></button>
                      <!-- <button
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="2"
                        aria-label="Slide 3"
                      ></button> -->
                    </div>
                    <div v-if="!talkStatus" class="carousel-inner">
                      <div class="carousel-item active">
                        <img
                          src="@/assets/talkcard/대화카드1.jpg"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                        <div class="container">
                          <div class="carousel-caption text-center">
                            <h1>대화카드</h1>
                            <p>대화 카드를 통해 상대방의 목소리를 벗겨보아요</p>
                          </div>
                        </div>
                      </div>
                      <div class="carousel-item">
                        <img
                          src="@/assets/talkcard/대화카드1.jpg"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                        <div class="container">
                          <div class="carousel-caption text-end">
                            <h1>대화카드</h1>
                            <p>당신의 일상을 들려주세요</p>
                            <p>
                              <a
                                v-if="!isTalkReady"
                                class="btn btn-lg btn-outline-light"
                                @click="startTalk"
                                >카드보기</a
                              >
                              <a
                                v-if="isTalkReady"
                                class="btn btn-lg btn-outline-light"
                                >카드 준비 중</a
                              >
                            </p>
                          </div>
                        </div>
                      </div>
                      <button
                        class="carousel-control-prev"
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="prev"
                      >
                        <span
                          class="carousel-control-prev-icon"
                          aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Previous</span>
                      </button>
                      <button
                        class="carousel-control-next"
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide="next"
                      >
                        <span
                          class="carousel-control-next-icon"
                          aria-hidden="true"
                        ></span>
                        <span class="visually-hidden">Next</span>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 시작하기 버튼을 클릭한 후 -->

              <!-- 게임 선택 버튼 -->
              <div class="card-body" v-if="!talkStatus && gameStatus">
                <h5 class="card-title">게임을 골라주세요!</h5>
                <button @click="startCM">
                  <div v-if="!isReadyCatch">캐치마인드</div>
                  <div v-else>준비완료</div>
                </button>
                <button @click="startABGame">
                  <div v-if="!isReadyBal">밸런스 게임</div>
                  <div v-else>준비완료</div>
                </button>
                <button @click="readyStartGame">
                  <div v-if="!isReadyCry">고요속의 외침</div>
                  <div v-else>준비완료</div>
                </button>
              </div>
              <!-- 캐치마인드 -->
              <!-- <CatchMind /> -->
              <div v-if="talkStatus" class="talk_card_box">
                <div class="card_content">{{ card_item }}</div>
                <img
                  :src="card_item_image"
                  alt=""
                  class="d-block w-100"
                />
                <button @click="nextTalkCard" class="btn btn-lg btn-outline-light next_card_btn">다음 대화 카드</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- random matching start -->
    <!-- <button @click="videoControll"> 페이스 오버레이 테스트 </button>
    <button @click="voiceControll"> 목소리 테스트 </button> -->
    <!-- <button @click="removeFilter"> 필터 취소 </button> -->

    <!-- random matching end -->

    <div class="Balgame" v-if="abgamestartStatus">
      <h3>밸런스 게임</h3>
      <h5>당신의 선택은? 신중히 골라주세요!</h5>
      <div class="select-box" @click="selectA">
        {{ A_item }}
      </div>
      <div class="select-box" @click="selectB">
        {{ B_item }}
      </div>

      <div class="select-result">
        <div v-for="(game, index) in gamedata" :key="index">
          <div class="select-comment">
            <span
              >{{ game.userId }}님이 {{ game.select }}를 선택하셨습니다.</span
            >
          </div>
        </div>
      </div>
      <div class="next-problem" @click="nextProblem">다음문제</div>
    </div>

    <!--  CatchMind start  -->
    <!-- // 캐치마인드 준비 화면 -->
    <!-- <div class="" v-if="!startStatus2 && !startStatus" @click="startGame2"> //모든 게임이 실행중이 아닌 경우 -->
    <!-- 우선 startStatus 하나만 가지고 사용 = runningGameState -->
    <!-- <div class="" v-if="!runningGameState" @click="startCatchMind">
         <div v-if="!isReadyCat" class="start-btn">캐치마인드</div>
            <div v-else class="start-btn">준비완료</div>
         </div>    -->

    <!-- 캐치마인드 시작 버튼 -->

    <div class="draw-box" v-bind:class="{ catDis: !catchMindStatus }">
      <!-- <Slider class="time-slider" v-if="catchMindStatus" v-model="countView" :max="60" /> - 이친구는 jquery인듯 돌아가는 게임이 없고 캐치마인드가 시작되었으면 -->
      <input
        class="time-slider"
        type="range"
        :max="60"
        v-if="catchMindStatus"
        v-model="countView"
      />
      <!-- 정답 입력란 -->
      <div v-if="catchMindStatus && !myTurn">
        <!-- 캐치마인드가 실행중 && 내가 그리는 차례가 아닐때-->
        <input
          class="ans-block"
          type="text"
          v-model="answer"
          placeholder="정답을 입력하세요"
          @keydown.enter="sendAns"
        />
      </div>
      <div class="pb-div" v-if="myTurn && catchMindStatus">{{ problem }}</div>
      <!-- 게임중 && 내가 그릴 차례 && 캐치마인드 시작됨  -->
      <div class="draw-container" v-bind:class="{ catDis: !catchMindStatus }">
        <!-- 게임이 실행중이 아니면 catDis 클래스 추가 -->
        <!-- 그림 그리는 캔버스 -->
        <canvas
          id="myCanvas"
          width="560"
          height="360"
          @mousedown="beginDrawing"
          @mousemove="keepDrawing"
          @mouseup="stopDrawing"
        />
        <!-- 그리기 도구 -->
        <div v-if="catchMindStatus && myTurn" class="draw-tool">
          <!-- 내가 그릴 차례|| 캐치마인드 시작 안됨  -->
          <table class="draw-opt">
            <!-- 그리기 도구 색상 지정 등-->
            <tr>
              <td>
                <Icon
                  class="colorBtn"
                  icon="akar-icons:circle-fill"
                  color="black"
                  @click="colorChange('black')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <Icon
                  class="colorBtn"
                  icon="akar-icons:circle-fill"
                  color="red"
                  @click="colorChange('red')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <Icon
                  class="colorBtn"
                  icon="akar-icons:circle-fill"
                  color="blue"
                  @click="colorChange('blue')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <Icon
                  class="colorBtn"
                  icon="akar-icons:circle-fill"
                  color="yellow"
                  @click="colorChange('yellow')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <Icon
                  class="colorBtn"
                  icon="akar-icons:circle-fill"
                  color="green"
                  @click="colorChange('green')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <Icon
                  class="colorBtn"
                  icon="bi:eraser"
                  @click="colorChange('white')"
                  width="24"
                  height="24"
                />
              </td>
              <td>
                <button class="removeBtn" @click="allDelete">
                  전체 지우기
                </button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div v-if="session" class="chat-container">
      <div id="chatbox">
        <div class="chat-header">채팅방</div>
        <div class="chat-detail">
          <div ref="chatmain" :class="{ chatmain: true }">
            <div v-for="(chat, index) in chatdata" :key="index">
              <div v-if="myUserName == chat.userId" class="my-chat-box">
                <div class="chat-block">
                  <div class="my-chat-time">{{ chat.now }}</div>
                  <div class="my-chat">{{ chat.msg }}</div>
                </div>
              </div>
              <div v-else class="opp-chat-box">
                <div class="opp-name">{{ chat.userId }}</div>
                <div class="opp-block">
                  <div class="opp-chat">{{ chat.msg }}</div>
                  <div class="opp-chat-time">{{ chat.now }}</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="send-box">
        <input
          class="send-input"
          v-model="msg"
          type="text"
          placeholder="메세지를 입력해주세요"
          @keydown.enter="sendMsg"
        />
        <Icon icon="fluent:send-24-filled" @click="sendMsg" />
        <!-- <i class="send-icon fas fa-reply" @click="sendMsg"></i> -->
      </div>
    </div>

    <!-- 교요 속의 외침 start -->
    <div v-if="crystartStatus">
      <div v-if="cryBoss">{{ cryList[cryCnt] }}</div>
      <div class="silence-cry" v-if="session && cryStatus">
        <!--<div class="silence-cry"> -->
        <div id="silence-timer" v-if="cryCheck">
          <p>{{ cryTime }}</p>
        </div>

        <div id="silence-input" v-if="cryCheck">
          <input
            id="cry-enter"
            v-model="cryAnswer"
            type="text"
            placeholder="정답을 입력해주세요"
            @keydown.enter="sendCryAnswer"
          />
          <Icon icon="fluent:send-24-filled" @click="sendCryAnswer" />
        </div>
      </div>
    </div>
    <!-- 고요 속의 외침 end -->
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import MainNavbar from "@/components/MainPage/MainNavbar.vue";
import UserVideo from "@/components/Video/UserVideo.vue";
import SelectHateMbti from "@/components/Video/SelectHateMbti.vue";
import { mapActions } from "vuex";
import VideoNavBar from "@/components/Video/VideoNavBar.vue";

// ----------------  Catch mind start
import Swal from "sweetalert2"; // 이쁜 alert 생성하는 친구
import { Icon } from "@iconify/vue"; // 아이콘 생성 //npm install --save-dev @iconify/vue
// ----------------  Catch mind end

// ----------------  Matching start
import Stomp from "webstomp-client"; // npm i webstomp-client sockjs-client - 웹 소켓 객체를 생성하기 위해 사용
import SockJS from "sockjs-client";
// ----------------  Matching end

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "/api/v1/webrtc";

export default {
  name: "App",

  components: {
    MainNavbar,
    UserVideo,
    SelectHateMbti,
    VideoNavBar,
    //---------------- catch mind , matching start
    Icon,
    //---------------- catch mind , matching end
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      matching_ing: false,

      faceOverStatus: false,
      micStatus: false,
      gameStatus: false,
      talkStatus: false, // 대화카드 상태
      vidStatus: false,
      voiceStatus: true,
      catchMindStatus: false, // 캐치마인드 게임이 실행중인지 확인용
      abgamestartStatus: false, // 밸런스 게임이 실행중인지 확인용

      myUserName: "",
      userHateMbtitList: [],
      mbtiModalOpen: false,
      userGender: "",

      // ----------------  Catch mind start
      sessionName: undefined, // 방장 여부 확인

      host: true, // 호스트인지 확인 - 호스트는 게임 실행 권한이 있음 - 차후 처리 (todo)

      runningGameState: false, // 현재 실행중인 게임 있는지 확인용

      myTurn: false, // 자신이 그림그릴 차례인지 확인

      countView: "", // 남은시간이 얼마나 남았는지 표시
      turn: 1, // 캐치마인드 진행된 턴 수
      answer: "", // 정답 제출용
      timerInit: null, // 잘 모르겠음 todo ?????
      count: 60, // 제한 시간
      // 문제 리스트
      catchProblem: [
        "샴푸",
        "송아지",
        "강아지",
        "소방관",
        "포크레인",
        "돈다발",
        "철학",
        "코딩",
        "싸피",
        "뚱이",
        "스폰지밥",
        "삼성",
        "애플",
        "아이폰",
        "에어팟",
        "경찰",
        "신과함께",
        "아이언맨",
        "캡틴아메리카",
        "토르",
        "헐크",
      ],
      problem: "", // 현재 문제

      canvas: null, // 그림을 그릴 캔버스 정보
      isDrawing: false, // 그리기 상태정보
      color: "black", // 펜 초기 색상
      penSize: 2, // 펜 초기 사이즈
      x: 0, // 펜 좌표정보
      y: 0, // 펜 좌표정보
      // ----------------  Catch mind end

      //---------------- catch mind , matching start
      userEmail: "",
      socket: undefined,
      matchingTestToken: undefined,
      matchingSessionName: undefined,
      //---------------- catch mind , matching end

      // ----------------  ab game start

      gamedata: [],
      A_item_list: [
        "토맛 토마토",
        "민초",
        "카레맛 똥",
        "팔만대장경 다 읽기",
        "겨울 에어컨",
        "월 200 백수",
        "탕수육 부먹",
      ],
      B_item_list: [
        "토마토맛 토",
        "반민초",
        "똥맛 카레",
        "대장내시경 팔만번 하기",
        "여름 히터",
        "월 500 직장인",
        "탕수육 찍먹",
      ],
      A_item: "",
      B_item: "",
      nextStatus: false,
      gIndex: 0,
      gameStart: [],
      isSelected: false,
      isReadyBal: false,

      // ----------------  ab game end

      // ----------------  chat start

      chatdata: [],
      msg: "",

      // ----------------  chat end
      // ----------------  talk card
      talkStart: [],
      isTalkReady: false,
      card_item: undefined, // 현재 보고 있는 카드 아이템
      card_item_image: undefined, // 현재 보고 있는 카드 아이템의 이미지
      talk_card_item: [
        "당신은 어떤 사람인가요?",
        "첫 여행을 기억하나요?",
        "인생이 영화라면 엔딩크레딧에 넣고 싶은 음악은?",
      ],
      talk_card_image: [
        require("@/assets/talkcard/대화카드1.jpg"),
        require("@/assets/talkcard/대화카드3.jpg"),
        require("@/assets/talkcard/대화카드2.jpg"),
      ],

      // ----------cry start -------------
      cryStart: [],
      crystartStatus: false,
      isReadyCry: false,
      cryStatus: false, // 게임 버튼 눌렀을 때 on/off 상태변수
      cryCnt: 0, // 몇 개 진행 했는지 카운트 할 변수
      cryCheck: false, // 정답자만 정답을 칠 수 있게 하는 T/F 변수
      cryListCheck: false, //출제자에게 어떤 단어인지 보여줌
      cryTime: 30, // 몇 초 남았는지 보여줄 변수
      cryAnswer: "",
      cryModalShow: false, // 모달 on/off
      cryList: [
        "백악관",
        "트럼프",
        "엘사",
        // "삼성화재 유성연수원",
        // "범죄도시",
        // "모나리자",
        // "미세먼지",
        // "거봉",
      ],
      cryCntMax: 3, //문제 최대치
      cryBoss: false, // 출제자인지 확인
      cryTimerInit: null, // setInterval 취소하기 위한 변수
      // --------------cry end------------
    };
  },
  computed: {},
  created() {
    const payload = JSON.parse(window.localStorage.vuex);
    this.fetchCurrentUser(payload.accounts.userEmail);
    this.userEmail = this.$store.state.accounts.userEmail;
    this.myUserName = this.$store.state.accounts.user.userNickName;
    this.userGender = this.$store.state.accounts.user.userGender;
    // this.myUserName = this.getUserInfo.userNickName
    // this.mySessionId = this.getUserInfo.userEmail
  },
  mounted() {
    // ----------------  Catch mind start

    // 캔버스 초기화
    var c = document.getElementById("myCanvas");
    this.canvas = c.getContext(`2d`);

    // ----------------  Catch mind end
  },
  methods: {
    ...mapActions(["fetchCurrentUser"]),
    selectmbti(value) {
      this.mbtiModalOpen = false;
      this.userHateMbtitList = value;
    },

    // ----------------- talk
    nextTalkCard() {
      this.session.signal({
        data: "next",
        to: [],
        type: "talk-card-next",
      });
    },

    startTalk() {
      //대화카드 시작~
      this.isTalkReady = !this.isTalkReady;
      this.session.signal({
        data: this.isTalkReady,
        to: [],
        type: "talk-card-start",
      });
    },

    vidControll() {
      // this.voiceStatus = !this.voiceStatus;
      this.vidStatus = !this.vidStatus;
      this.gameStatus = !this.gameStatus;
      this.publisher.publishVideo(this.vidStatus);
      console.log(this.vidStatus);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      this.socketClose();

      clearInterval(this.cryTimerInit);
      this.cryTimerInit = null;

      window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    // 서버에서 토큰 정보 발급 받기
    getCreateToken(mySessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(`${OPENVIDU_SERVER_URL}/createSession`, {
            userEmail: mySessionId,
            headers: {
              Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
            },
          })
          .then((response) => {
            console.log("debuf - getCreateToken", response.data["token"]);
            //this.token = response.data["token"];
            // ----------------  Catch mind start
            self.initMyTurn(); // 내차례 정하기
            // ----------------  Catch mind end
            resolve(response.data["token"]);
          })
          .catch((err) => {
            console.log(err);
            reject(err.response);
          });
      });
    },

    getjoinToken() {
      axios
        .post(`${OPENVIDU_SERVER_URL}/joinSession`, {
          sessionNameParam: this.sessionName,
        })
        .then((response) => {
          console.log("getjoinToken", response);
          this.token = response.data["token"];
          return this.token;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    voiceControll() {
      let pitchs = [
        "0.76",
        "0.77",
        "0.78",
        "0.79",
        "0.80",
        "1.3",
        "1.4",
        "1.5",
        "1.6",
        "1.7",
      ];
      let pitch = pitchs[8];
      //if (this.voiceStatus) {
      //  this.voiceStatus = !this.voiceStatus;
      this.publisher.stream.applyFilter("GStreamerFilter", {
        command: `pitch pitch=${pitch}`,
      });
      //}
    },
    // 아마도 FaceOverlay 적용하기
    videoControll() {
      console.log("faceOverlay!!!", this.userGender);
      if (!this.faceOverStatus) {
        if (this.userGender === "man") {
          this.publisher.stream
            .applyFilter("FaceOverlayFilter")
            .then((filter) => {
              filter.execMethod("setOverlayedImage", {
                uri: "https://cdn.discordapp.com/attachments/986931115833053186/1008060233341468743/14x-100.jpg",
                offsetXPercent: "-0.4F",
                offsetYPercent: "-0.4F",
                widthPercent: "2.0F",
                heightPercent: "2.0F",
              });
            });
        } else {
          this.publisher.stream
            .applyFilter("FaceOverlayFilter")
            .then((filter) => {
              filter.execMethod("setOverlayedImage", {
                uri: "https://cdn.discordapp.com/attachments/986895122392297515/1008064851786092686/14x-100.jpg",
                offsetXPercent: "-0.4F",
                offsetYPercent: "-0.4F",
                widthPercent: "2.0F",
                heightPercent: "2.0F",
              });
            });
        }
      } else {
        this.removeFilter();
      }
    },

    removeFilter() {
      this.publisher.stream.removeFilter();
    },

    // ----------------  Catch mind start

    // 그리기 도구 관련
    colorChange(col) {
      // 그리기 도구 - 색상 변경 method
      this.session.signal({
        data: col,
        to: [],
        type: "drawing-opt",
      });
    },

    allDelete() {
      // 그리기 도구 - 현재 그림 다 지우기 메소드
      this.session.signal({
        data: "all",
        to: [],
        type: "drawing-opt",
      });
    },

    // 그림 그리기 시작 - 마우스 클릭시
    beginDrawing(e) {
      if (!this.myTurn) return;
      this.x = e.offsetX;
      this.y = e.offsetY;
      this.isDrawing = true;
      const sdata = {
        x: e.offsetX,
        y: e.offsetY,
        isDrawing: true,
      };
      this.session.signal({
        data: JSON.stringify(sdata),
        to: [],
        type: "start-draw",
      });
    },

    // 그림 그리기 - 마우스 이동
    keepDrawing(e) {
      if (this.isDrawing) {
        const sdata = {
          x: e.offsetX,
          y: e.offsetY,
          isDrawing: true,
        };
        this.session.signal({
          data: JSON.stringify(sdata),
          to: [],
          type: "drawing",
        });
      }
    },

    // 그림 그리기 - 마우스 up - 선 끊어짐
    stopDrawing(e) {
      if (this.isDrawing) {
        const sdata = {
          x: e.offsetX,
          y: e.offsetY,
          isDrawing: false,
        };
        this.session.signal({
          data: JSON.stringify(sdata),
          to: [],
          type: "drawing",
        });
        this.isDrawing = false;
      }
    },

    // 그림 지나간 길 표시 - 캔버스에 그림 그리기
    drawLine(x1, y1, x2, y2, color) {
      let ctx = this.canvas;
      ctx.beginPath();
      ctx.strokeStyle = color;
      ctx.lineWidth = this.penSize;
      ctx.moveTo(x1, y1);
      ctx.lineTo(x2, y2);
      ctx.stroke();
      ctx.closePath();
    },

    //정답 맞추기 관련
    sendAns() {
      // 정답 입력하기 메소드
      const sdata = {
        // 보낼 데이터
        name: this.myUserName,
        ans: this.answer,
      };
      this.session.signal({
        // signal로 보내기
        data: JSON.stringify(sdata), // json 형식으로 정답 보내기
        to: [],
        type: "send-answer",
      });
      if (this.answer == this.problem) {
        // 정답인 경우
        this.answer = ""; // 내 정답 초기화
        setTimeout(() => {
          this.endCM(); // 캐치마인드 종료
        }, 1000);
      }
      this.answer = "";
    },

    // 캐치마인드 게임 시작
    startCM() {
      // 캐치마인드 게임 1턴 시작
      // this.publisher.resolution = "100x100"
      // this.publisher.resolution("320x240")
      if (this.turn > 4) {
        // 4개의 문항이 완료되면 게임 종료???
        return;
      }
      let pb =
        this.catchProblem[Math.floor(Math.random() * this.catchProblem.length)]; // 랜덤 문항 출제
      const sdata = {
        // 문제 출제용 데이터
        problem: pb,
      };
      this.session.signal({
        // 문제 출제 signal 보내기
        data: JSON.stringify(sdata),
        to: [],
        type: "catch-start",
      });
      this.timerInit = setInterval(() => {
        this.timer(); // 타이머 동작하도록
      }, 1000);
    },

    // 캐치마인드 게임 종료
    endCM() {
      // 캐치마인드 게임 1턴 종료

      this.turn++; // 현재 턴 증가
      const sdata = {
        // 턴 정보 공유
        turn: this.turn,
      };
      this.session.signal({
        data: JSON.stringify(sdata),
        to: [],
        type: "catch-end",
      });
    },

    // 게임 시간 타이머
    timer() {
      if (this.count > 1) {
        // 카운트가 아직 남아 있는 경우
        this.count--; //카운트 감소
        this.session.signal({
          // 감소한 카운트 정보 배포
          data: this.count,
          to: [],
          type: "timer",
        });
      } else {
        this.session.signal({
          // 카운트가 끝난경우
          data: 0,
          to: [],
          type: "timer",
        });
        this.endCM(); // 현재 진행중인 캐치마인드 한턴 증가 및 종료
      }
    },
    initMyTurn() {
      // sessionName이 있는경우 myTurn을 true로 반대의 경우 false로 입력
      console.log("debug - initMyTurn ", this.matchingSessionName);
      this.myTurn = this.matchingSessionName == undefined ? true : false;
    },
    // ----------------  Catch mind End

    //---------------- random matching start
    matchingReqeust() {
      //매칭 요청 하기
      let self = this;
      this.socketConnect();
      setTimeout(() => {
        console.log(localStorage.getItem("accessToken"));
        axios({
          url: `${OPENVIDU_SERVER_URL}/matching`,
          method: "post",
          data: { userEmail: self.userEmail },
          headers: {
            Authorization: `Bearer ${self.$store.state.accounts.accessToken}`,
          },
        })
          .then((response) => {
            console.log("debuf - matchingReqeust", response);
          })
          .catch((err) => {
            console.log(err);
            self.socketClose();
          });
      }, 3000);
    },

    cancleMatchingReqeust() {
      //매칭 요청 취소 하기
      let self = this;
      axios({
        url: `${OPENVIDU_SERVER_URL}/exitMatching`,
        method: "post",
        data: { userEmail: self.userEmail },
        headers: {
          Authorization: `Bearer ${self.$store.state.accounts.accessToken}`,
        },
      })
        .then((response) => {
          console.log("debuf - cancleMatchingReqeust", response);
          self.socketClose();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    waitMaching() {
      // 소켓 연결 후 매칭 대기
    },

    socketConnect() {
      // 소켓 연결
      let self = this;
      const serverURL = "https://i7b206.p.ssafy.io:443/ws";
      this.socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(this.socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
      this.stompClient.connect(
        {
          Authorization: `Bearer ${self.$store.state.accounts.accessToken}`,
        },
        (frame) => {
          // 소켓 연결 성공
          console.log("소켓 연결 성공", frame);
          this.stompClient.subscribe(
            "/sub/matching/" + self.userEmail,
            (res) => {
              let data = JSON.parse(res.body);
              console.log("구독으로 받은 메시지 입니다.", data);
              console.log("구독으로 받은 메시지 입니다.", data.token);
              console.log("구독으로 받은 메시지 입니다.", data.sessionName);
              self.matchingTestToken = data.token;
              self.matchingSessionName = data.sessionName;
              self.joinSession2();
            }
          );
        },
        (error) => {
          // 소켓 연결 실패
          console.log("소켓 연결 실패", error);
        }
      );
    },

    socketClose() {
      // 소켓 연결 취소
      this.socket.close();
      console.log("소켓 연결 취소");
    },

    matchingRoomEnter() {
      //---------------- random matching end
    },

    joinSession2() {
      console.log("뿡뿡뿡");
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        console.log("stream테스트");
        this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // ----------------  Catch mind start
      this.session.on("signal:catch-start", (event) => {
        // 게임 시작 시그널
        this.catchMindStatus = true; // 캐치마인드 진행중으로 변경
        this.count = 60; // 시간초 초기화

        // this.myTurn = !this.myTurn; // 그림 그리는 턴 변경
        console.log("myTurn 1", this.myTurn + " " + this.turn);
        if (this.matchingSessionName == undefined && this.turn % 2 == 1)
          this.myTurn = true;
        else if (this.matchingSessionName == undefined && this.turn % 2 == 0)
          this.myTurn = false;
        else if (this.matchingSessionName != undefined && this.turn % 2 == 1)
          this.myTurn = false;
        else this.myTurn = true;

        console.log("myTurn 2", this.myTurn + " " + this.turn);
        let pb = JSON.parse(event.data); // 주어진 문제 파싱 및 변수 등록
        this.problem = pb.problem;

        if (this.turn > 4) {
          // 4턴 이상 진행되면 캐치마인드 종료
          this.endCM();
          return;
        }
        if (this.myTurn) {
          // 그림그리기 차례인 경우
          Swal.fire({
            //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
            title: "내 차례에요!",
            showConfirmButton: false,
            timer: 1500,
          });
        } else {
          Swal.fire({
            //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
            title: "상대방 차례에요!",
            showConfirmButton: false,
            timer: 1500,
          });
        }
      });

      // ----------------- chat start
      this.session.on("signal:my-chat", (event) => {
        this.chatdata.push(JSON.parse(event.data));
        setTimeout(this.chatscroll, 10);
      });
      // ----------------- chat end

      // ------------------ talk card start
      this.session.on("signal:talk-card-start", (event) => {
        if (event.data) {
          this.talkStart.push(event.data);
        } else {
          this.talkStart.pop();
        }
        if (this.talkStart.length == 2) {
          this.talkStatus = true;
          this.card_item = this.talk_card_item[this.gIndex];
          this.card_item_image = this.talk_card_image[this.gIndex];
        }
      });

      this.session.on("signal:talk-card-next", () => {
        if (this.gIndex < this.talk_card_item.length - 1) {
          this.gIndex++;
          this.card_item = this.talk_card_item[this.gIndex];
          this.card_item_image = this.talk_card_image[this.gIndex];
        } else {
          Swal.fire("대화 카드 종료!");
          this.gIndex = 0;
          this.talkStatus = false;
          // this.voiceControll();
          this.removeFilter();
          setTimeout(() => {
            this.videoControll();
            this.vidControll();
          }, 500);
        }
      });

      // ------------------ talk card end
      // ---------------------- abGame Start

      this.session.on("signal:game-start", (event) => {
        console.log(event);

        if (event.data) {
          this.gameStart.push(event.data);
        } else {
          this.gameStart.pop();
        }
        if (this.gameStart.length == 2) {
          this.abgamestartStatus = true;
          this.startStatus = true;
          this.A_item = this.A_item_list[this.gIndex];
          this.B_item = this.B_item_list[this.gIndex];
        }
      });

      this.session.on("signal:my-game", (event) => {
        this.gamedata.push(JSON.parse(event.data));
        if (this.gamedata.length == 2) {
          this.nextStatus = true;
        }
      });

      this.session.on("signal:game-next", () => {
        if (this.gamedata.length == 2) {
          if (this.gIndex < this.A_item_list.length - 1) {
            this.nextStatus = false;
            this.isSelected = false;
            this.gIndex++;
            this.A_item = this.A_item_list[this.gIndex];
            this.B_item = this.B_item_list[this.gIndex];
            this.gamedata = [];
          } else {
            Swal.fire("밸런스 게임 종료!");
            this.gameStart = [];
            this.gamedata = [];
            this.startStatus = false;
            this.isReadyBal = false;
            this.gIndex = 0;
            this.abgamestartStatus = false;
          }
        }
      });
      // ---------------------- abGame End

      this.session.on("signal:catch-end", (event) => {
        // 게임 종료 시그널
        let val = JSON.parse(event.data); // 턴수 정보
        this.turn = val.turn;
        if (this.timerInit != null) {
          // ??? todo
          clearInterval(this.timerInit);
          this.timerInit = null;
        }
        this.allDelete(); // 캔버스 초기화
        if (this.turn > 4) {
          // 진행된 turn이 4 이상인 경우 게임 종료
          Swal.fire({
            // alert 보내기
            title: "게임 끝!",
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            this.catchMindStatus = false; // 게임중 상태 해제
            this.turn = 1; // 기타 게임 데이터 초기화
            this.count = 60;
          });
          return;
        }
        if (this.turn < 5) {
          setTimeout(() => {
            this.startCM(); // 다음 게임 실행
          }, 1000);
        }
      });

      // 그리기 도구 관련 시그널
      this.session.on("signal:drawing-opt", (event) => {
        // 그리기 도구 색상 변경
        if (event.data == "all") {
          // 모두 지우기 이벤트 - allDelete()
          this.canvas.clearRect(0, 0, 560, 360);
        } else if (event.data == "white") {
          // 흰색으로 변경 - 지우개
          this.penSize = 4;
          this.color = event.data;
        } else {
          // 다른 컬러로 변경
          this.color = event.data;
          this.penSize = 2;
        }
      });

      // 그리기 관련 시그널 - 마우스 down
      this.session.on("signal:start-draw", (event) => {
        let sm = JSON.parse(event.data);
        this.x = sm.x;
        this.y = sm.y;
        this.isDrawing = sm.isDrawing;
      });
      // 그리기 관련 시그널 - 마우스 up, move
      this.session.on("signal:drawing", (event) => {
        let sm = JSON.parse(event.data);
        this.drawLine(this.x, this.y, sm.x, sm.y, this.color);
        this.x = sm.x;
        this.y = sm.y;
        this.isDrawing = sm.isDrawing;
      });

      //정답 제출 관련
      this.session.on("signal:send-answer", (event) => {
        let info = JSON.parse(event.data); // name, ans 정보 받기
        if (this.problem == info.ans) {
          // 정답인경우
          Swal.fire({
            // alert 메시지
            title: `${info.name}님이 정답을 맞추셨습니다.`,
            showConfirmButton: false,
            timer: 1500,
          }).then(() => {
            this.allDelete(); // 캔버스 지우기
          });
        }
      });

      // 타이머
      this.session.on("signal:timer", (event) => {
        this.count = event.data; // count 정보
        // this.count = +this.count || 0; // 카운트 증가 시키기
        this.countView = this.count; // 10초 남은경우 카운트 뷰를 보여줌
      });

      // ----------------  Catch mind end

      // -------------silence cry start---------------

      this.session.on("signal:cry-game-start", (event) => {
        if (event.data) {
          this.cryStart.push(event.data);
        } else {
          this.cryStart.pop();
        }
        if (this.cryStart.length == 2) {
          this.crystartStatus = true;
          this.startCryGame();
        }
      });

      this.session.on("signal:startCryCheck", () => {
        if (!this.cryBoss) {
          this.cryCheck = true;
          this.cryStatus = true;
        }
      });

      this.session.on("signal:sendCryAnswer", (event) => {
        let data = JSON.parse(event.data);
        if (this.cryList[this.cryCnt] == data.answer) {
          //정답인 경우
          this.cryCnt++;
          Swal.fire({
            title: `${data.name}님이 정답을 맞추셨습니다.`,
            showConfirmButton: false,
            timer: 1500,
          });
          this.cryTime = 30;
          if (this.cryCnt == this.cryCntMax) {
            this.endCry();
          } else {
            this.nextCry();
          }
        } else {
          //정답이 아닌 경우
          if (!this.cryBoss) {
            //정답을 맞추는 사람에게만 정답이 아니라고 표시
            Swal.fire({
              title: `정답이 아닙니다.`,
              showConfirmButton: false,
              timer: 1000,
            });
          }
          if (this.cryCnt == this.cryCntMax) {
            this.endCry();
          }
        }
      });

      this.session.on("signal:endCry", () => {
        this.publisher.publishAudio(true);
        Swal.fire({
          title: `게임 종료!`,
          showConfirmButton: false,
          timer: 1500,
        });
        this.isReadyCry = false;
        this.cryCnt = 0;
        this.cryBoss = false;
        this.cryCheck = false;
        this.cryStatus = false;
        this.cryListCheck = false;
        this.cryStart = [];
        this.crystartStatus = false;
        if (this.cryTimerInit != null) {
          // ??? todo
          clearInterval(this.cryTimerInit);
          this.cryTimerInit = null;
        }
      });

      this.session.on("signal:nextCry", () => {
        if (this.cryBoss) {
          setTimeout(
            Swal.fire({
              title: this.cryList[this.cryCnt],
              showConfirmButton: false,
              timer: 1500,
            })
          );
        }
      });

      // 타이머
      this.session.on("signal:cryTimer", (event) => {
        this.cryTime = event.data;
      });

      // 시간 초과된 경우 다음 게임으로 넘어감
      this.session.on("signal:endCryTimer", () => {
        this.cryCnt++;
        this.cryTime = 30;
        if (this.cryCnt != this.cryCntMax) {
          Swal.fire({
            title: "다음 문제로 넘어갑니다",
            showConfirmButton: false,
            timer: 1000,
          });
          setTimeout(this.nextCry(), 1000);
        } else {
          this.endCry();
        }
      });

      //----------------silence cry end -------------

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend

      console.log(
        "debug - matchingRoomEnter token Info",
        this.matchingTestToken
      );
      console.log(
        "debug - matchingRoomEnter token Info",
        this.matchingSessionName
      );
      this.session
        .connect(this.matchingTestToken, { clientData: this.userEmail })
        .then(() => {
          // --- Get your own camera stream with the desired properties ---
          let publisher = this.OV.initPublisher(undefined, {
            audioSource: undefined, // The source of audio. If undefined default microphone
            videoSource: undefined, // The source of video. If undefined default webcam
            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
            publishVideo: false, // Whether you want to start publishing with your video enabled or not
            resolution: "400x360", // The resolution of your video
            frameRate: 30, // The frame rate of your video
            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
            mirror: false, // Whether to mirror your local video or not
          });

          this.mainStreamManager = publisher;
          this.publisher = publisher;

          // --- Publish your stream ---

          this.session.publish(this.publisher);

          console.log("------voice", this.voiceStatus);
          setTimeout(() => {
            this.voiceControll();
          }, 2500);
          this.initMyTurn();
        })
        .catch((error) => {
          console.log(
            "There was an error connecting to the session:",
            error.code,
            error.message
          );
        });

      window.addEventListener("beforeunload", this.leaveSession);
    },
    // ---------------- abgame Start

    startABGame() {
      // 밸런스게임 시작

      this.isReadyBal = !this.isReadyBal;
      this.session.signal({
        data: this.isReadyBal,
        to: [],
        type: "game-start",
      });
    },
    nextProblem() {
      if (!this.nextStatus && this.isSelected) {
        Swal.fire("상대방이 선택하지 않았습니다");
        return;
      } else if (!this.nextStatus && !this.isSelected) {
        Swal.fire("둘 중 하나를 선택해 주세요 ✅");
      }
      this.nextStatus = false;
      this.isSelected = false;

      this.session.signal({
        data: "next",
        to: [],
        type: "game-next",
      });
    },
    selectA() {
      if (this.isSelected) return;
      this.isSelected = true;
      const sdata = {
        userId: this.myUserName,
        select: this.A_item,
      };

      this.session.signal({
        data: JSON.stringify(sdata),
        to: [],
        type: "my-game",
      });
    },
    selectB() {
      if (this.isSelected) return;
      this.isSelected = true;
      const sdata = {
        userId: this.myUserName,
        select: this.B_item,
      };

      this.session.signal({
        data: JSON.stringify(sdata),
        to: [],
        type: "my-game",
      });
    },
    // ---------------- abgame End

    // ----------------  chat start
    chatscroll() {
      this.$refs.chatmain.scrollTop = this.$refs.chatmain.scrollHeight;
    },
    sendMsg() {
      this.msg = this.msg.trim();
      let date = new Date();
      let now = "";
      let min = date.getMinutes();
      if (min < 10) {
        min = "0" + date.getMinutes();
      }
      if (date.getHours() > 12) {
        now = "오후 " + (date.getHours() - 12) + ":" + min + "";
      } else {
        now = "오전 " + date.getHours() + ":" + min + "";
      }
      if (this.msg == "") {
        return;
      }
      const sdata = {
        userId: this.myUserName,
        msg: this.msg,
        now: now,
      };

      this.session.signal({
        data: JSON.stringify(sdata),
        to: [],
        type: "my-chat",
      });
      this.msg = "";
    },
    // ----------------  chat end

    // ------------------ silence cry start -------------------

    readyStartGame() {
      this.isReadyCry = !this.isReadyCry;
      this.session.signal({
        data: JSON.stringify("zzz"),
        to: [],
        type: "cry-game-start",
      });
    },
    // 게임 시작 버튼 누른 후 동작

    startCryGame() {
      this.cryListCheck = true;
      this.session.signal({
        data: JSON.stringify("zzz"),
        to: [],
        type: "startCryCheck",
      });

      Swal.fire({
        title: "게임이 시작됩니다.",
        showConfirmButton: false,
        timer: 1000,
      });

      if (this.matchingSessionName != undefined) {
        this.cryBoss = true;
        this.publisher.publishAudio(false); //게임 시작 누른 사람 음소거
      }

      setTimeout(() => {
        if (this.matchingSessionName != undefined) {
          Swal.fire({
            title: this.cryList[this.cryCnt],
            showConfirmButton: false,
            timer: 1500,
          });
        }
      }, 1000);

      this.cryTimerInit = setInterval(() => {
        this.cryTimer(); // 타이머 동작하도록
      }, 1000);
    },

    sendCryAnswer() {
      const cryData = {
        answer: this.cryAnswer,
        name: this.myUserName,
      };
      this.session.signal({
        data: JSON.stringify(cryData),
        to: [],
        type: "sendCryAnswer",
      });
      this.cryAnswer = "";
    },
    endCry() {
      this.session.signal({
        to: [],
        type: "endCry",
      });
    },
    nextCry() {
      this.session.signal({
        to: [],
        type: "nextCry",
      });
    },
    cryTimer() {
      if (this.cryTime > 1) {
        // 카운트가 아직 남아 있는 경우
        this.cryTime--; //카운트 감소
        this.session.signal({
          // 감소한 카운트 정보 배포
          data: this.cryTime,
          to: [],
          type: "cryTimer",
        });
      } else {
        this.session.signal({
          // 카운트가 끝난경우
          data: 0,
          to: [],
          type: "cryTimer",
        });
        this.endCryTimer(); // 현재 진행중인 캐치마인드 한턴 증가 및 종료
      }
    },
    endCryTimer() {
      setTimeout(
        this.session.signal({
          to: [],
          type: "endCryTimer",
        }),
        1000
      );
    },

    // -------------------- silence cry end--------------------
  },
};
</script>

<style scoped>
@font-face {
  font-family: "Cafe24Ssurround";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/Cafe24Ssurround.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "Godo";
  font-style: normal;
  font-weight: 400;
  src: url("//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff2")
      format("woff2"),
    url("//cdn.jsdelivr.net/korean-webfonts/1/corps/godo/Godo/GodoM.woff")
      format("woff");
}

.container-2 {
  margin-top: 1rem;
  margin-bottom: 1rem;
  padding-right: 0px;
  padding-left: 0px;
  margin-right: auto;
  margin-left: auto;
  background-color: rgba(148, 209, 202, 176);
  height: 900px;
  min-width: 1200px;
}

.non-matching_bg {
  background-image: url("@/assets/cat_background.jpg");
  width: 1200px;
  height: 800px;
  min-width: 600px;
  position: absolute;
  margin-top: 1rem;
  top: 100px;
  left: 50%;
  transform: translate(-50%, 0px);
}
.matching-div {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 500px;
  height: 500px;
}
.matching_btn {
  font-family: "Godo";
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 250px;
  height: 200px;
  border: 2px black solid;
  background-color: white;
  border-radius: 20px;
  margin: auto;
}
.matching {
  text-align: center;
  color: blue;
  font-size: 30px;
}
.hate_mbti {
  height: 140px;
  text-align: center;
  padding-top: 30px;
  font-size: 25px;
  color: red;
}
.matching-title {
  font-family: "Cafe24Ssurround";
  text-align: center;
  margin-top: 10px;
  font-size: 120px;
  color: white;
  -ms-user-select: none;
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
.card {
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.card2 {
  top: 40%;
  outline-style: solid;
  outline-color: #97cdbd;
  text-align: center;
}

.card-title {
  color: #97cdbd;
  position: relative;
  text-align: center;
}

.firstimage {
  height: 400px;
  width: 400px;
}

.bodies {
  display: flex;
  height: 80%;
  background: #f0f2f5;
  min-width: 1200px;
}
.video-box {
  height: 100%;
}

.videos {
  display: flex;
  width: 75%;
  height: 100%;
  /* background: #f0f2f5; */
}
.video {
  width: 50%;
  height: 100%;
  margin: 3px;
}
.video-section {
  width: 20px;
}
.chat-box {
  height: 100%;
}

.header {
  position: relative;
}

.close-btn {
  position: absolute;
  color: white;
  top: 3px;
  right: 10px;
}

.text-box {
  background-color: #d1d1d1;
  width: 100%;
  border-radius: 20px;
  color: black;
  padding-left: 10px;
}

.text-box:focus {
  outline: none;
}

.title {
  padding-left: 5%;
  font-family: "Jua" !important;
  font-size: 1rem !important;
  color: white;
}

/* .header {
  width: 100%;
  border-radius: 20px;
  box-shadow: 3px 3px 3px rgb(0, 0, 0, 0.3);
  height: 4vh;
} */
.message-title {
  font-size: 0.8rem;
}

.message-header {
  font-family: "Nanum Gothic", sans-serif;
}

.message {
  color: white;
}

.send-btn {
  color: white;
}

#chat-area {
  overflow-y: auto;
}

#chat-area::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

#chat-area::-webkit-scrollbar-track {
  background: #37474f;
}

#chat-area::-webkit-scrollbar-corner {
  background: #37474f;
}

#chat-area::-webkit-scrollbar-thumb {
  background: #b0a2c8;
}

#chat-area::-webkit-scrollbar-button {
  background-color: #37474f;
  height: 0;
}

.catDis {
  display: none;
}

/* Balance Game */
.select-box {
  padding: 5%;
  margin: 5%;
  color: #37474f;
  background: white;
  font-weight: bold;
  border-radius: 5px;
}
.select-box:hover {
  transform: scale(1.1);
  cursor: pointer;
  border: 1px solid #00aba2;
}

.select-comment {
  border: 2px solid #00aba2;
  font-size: 12px;
  font-weight: bold;
  padding: 5px;
  border-radius: 5px;
  margin: 5%;
}

.next-problem {
  width: 80%;
  margin: 10% auto;
  padding: 3%;
  border: 2px solid #323545;
  background: #fff;
  border-radius: 5px;
  color: #323545;
  font-weight: bold;
}
.next-problem:hover {
  cursor: pointer;
  color: #00aba2;
  border: 2px solid #00aba2;
}

/* Chat */

.chat-header {
  width: 25%;
  text-align: center;
}
.chat-body {
  width: 25%;
  height: 100%;
}
.chat-block {
  display: flex;
  justify-content: flex-end;
  padding: 3px;
  margin: 10px;
}
.chatmain {
  max-height: 500px;
  overflow: auto;
}
.my-chat-time {
  font-size: 10px;
  font-weight: bold;
  width: 25%;
  text-align: right;
  margin-right: 5px;
  align-self: flex-end;
}
.my-chat {
  background: #00aba2;
  color: white;
  padding: 2%;
  border-radius: 5px;
  text-align: left;
  word-break: break-all;
}
.opp-chat-box {
  padding: 3px;
  margin: 10px;
}
.opp-block {
  display: flex;
}
.opp-name {
  font-size: 12px;
  font-weight: bold;
  text-align: left;
  margin-bottom: 2px;
}
.opp-chat {
  background: white;
  padding: 2%;
  border-radius: 7px;
  text-align: left;
  word-break: break-all;
}
.opp-chat-time {
  font-size: 10px;
  font-weight: bold;
  text-align: left;
  margin-left: 5px;
  width: 25%;
  align-self: flex-end;
}

.send-box {
  width: 25%;
}
.send-input {
  width: 85%;
  height: 90%;
  border: 0;
  border-radius: 5px;
  margin: 1%;
  padding-left: 3%;
}
.send-input:focus {
  outline: none;
}
.send-icon {
  font-size: 20px;
  color: #00aba2;
}
.send-icon:hover {
  transform: scale(1.1);
  cursor: pointer;
}
.bodies {
  display: flex;
  height: 80%;
  background: #f0f2f5;
  position: relative;
}
.videos {
  display: flex;
  width: 75%;
  height: 100%;
  background: #f0f2f5;
}
.video {
  float: left;
  margin-top:10%;
  width: 50%;
  height: 100%;
  margin: 3px;
}
.video-chat-box {
  height: 100%;
}

.next_card_btn{
  position:absolute;
  bottom:10%;
  right:10%;
}

.talk_card_box{
  position:relative;
}
.card_content{
  width:85%;  
  font-size: 20px;
  font-weight: bold;
  color : white;
  position:absolute;
  top:10%;
  left:50%;
  transform:translate(-50%, -50%);
}

.btn-moving{
  position:absolute;
  bottom:5%;
  right:30%;
}

.btn-moving2{
  position:absolute;
  bottom:5%;
  right:10%;
}
</style>
