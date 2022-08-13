<template>
  <MainNavbar />
  <div id="main-container" class="container">
    <div class="card mt-3" v-if="!session">
      <div class="row g-0">
        <div class="col-md-4 col-lg-4">
          <img
            src="@/assets/img.png"
            class="firstimage img-fluid"
            alt="img"
          />
        </div>

        
        <div class="col-md-8 col-lg-8">
          <div class="card-body">
            <div id="join">
              <div id="join-dialog" class="jumbotron vertical-center">
                <h1 class="card-title">랜덤매칭 시작하기</h1>
                <br />
                <p class="card-text">
                  <label>닉네임 {{ myUserName }}</label>
                </p>
                <br />
                <p class="card-text">
                  <label>이메일 {{ userEmail }}</label>
                </p>
                <br />
                <tr>
                  <td>MBTI</td>
                  <br />
                  <td>
                    <span
                      class="mx-3"
                      style="cursor: pointer"
                      @click="mbtiModalOpen = true"
                    >
                      싫어하는 MBTI 선택하기</span
                    >
                  </td>
                  <p>{{ userHateMbtitList }}</p>
                </tr>
                <SelectHateMbti
                  v-if="mbtiModalOpen == true"
                  v-on:selectmbti="selectmbti"
                />
                <p class="mt-3 text-end">
                  <button class="btn btn-lg btn-success" @click="matchingReqeust">
                    Join!
                  </button>
                  <button @click="cancleMatchingReqeust"> 매칭 요청 취소 하기 </button>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="session" class="container-fluid">
      
      <div class="row">
        <!-- video 부분 -->
        <div class="v_box">
          <div class="video-box col-6" v-if="session">
            <div class="videos">
              <!-- video1 -->
              <div class="video">
                <user-video :stream-manager="mainStreamManager" />
              </div>
              <!-- video2 -->
              <div class="video">
                <user-video
                  v-for="sub in subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  @click="updateMainVideoStreamManager(sub)"
                />
              </div>
            </div>
          </div>
          <div class="col-3">
            <div class="card2">
              <!-- 처음 진입시 -->
              
              <div v-if="!talkStatus && !gameStatus">
                <div class="card-container">
                  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
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
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img
                          src="@/assets/img.png"
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
                          src="@/assets/img.png"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                        <div class="container">
                          <div class="carousel-caption text-end">
                            <h1>대화카드</h1>
                            <p>당신의 일상을 들려주세요</p>
                            <p>
                              <a class="btn btn-lg btn-outline-light" @click="startTalk">카드보기</a>
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
                  <div class="card-body" v-if="talkStatus && !gameStatus">
                    <div class="card-container">
                  <div id="carouselExampleIndicators" class="carousel slide">
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
                      <button
                        type="button"
                        data-bs-target="#carouselExampleIndicators"
                        data-bs-slide-to="2"
                        aria-label="Slide 3"
                      ></button>
                    </div>
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img
                          src="@/assets/talkcard/대화카드1.jpg"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                      </div>
                      <div class="carousel-item">
                        <img
                          src="@/assets/talkcard/대화카드3.jpg"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                      </div>
                      <div class="carousel-item">
                        <img
                          src="@/assets/talkcard/대화카드2.jpg"
                          class="d-block w-100"
                          alt="nonLoginPage.jpg"
                        />
                        <div class="container">
                          <div class="carousel-caption text-end"> 
                            <p>
                              <a class="btn btn-lg btn-outline-light" @click="vidControll">대화완료</a>
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
                  <!-- 게임 선택 버튼 -->
                <div class="card-body" v-if="!talkStatus && gameStatus">
                  <h5 class="card-title">게임을 골라주세요!</h5>
                  <button @click="startGame1">
                    <div v-if="!isReadyCatch">캐치마인드</div>
                    <div v-else>준비완료</div>
                  </button>
                  <button @click="startGame2">
                    <div v-if="!isReadyCatch">옛날옛적에</div>
                    <div v-else>준비완료</div>
                  </button>
                </div>
                  <!-- 캐치마인드 -->
                  <!-- <CatchMind /> -->
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
                    <div class="a-or-b-game" v-if="abgamestartStatus">
                        <h3>밸런스 게임</h3>
                        <h5>당신의 선택은? 신중히 골라주세요!</h5>
                        <div class="select-box" @click="selectA">
                           {{A_item}}
                        </div>
                        <div class="select-box" @click="selectB">
                           {{B_item}}
                        </div>
                        </div>
                    <div class="select-result">
                           <div v-for="(game,index) in gamedata" :key="index">
                              <div class="select-comment">
                                 <span>{{game.userId}}님이 {{game.select}}를 선택하셨습니다.</span>
                              </div>
                           </div>
                        </div>   
                        <div class="next-problem" @click="nextProblem">다음문제</div>

    <!--  CatchMind start  -->
    <!-- // 캐치마인드 준비 화면 -->
      <!-- <div class="" v-if="!startStatus2 && !startStatus" @click="startGame2"> //모든 게임이 실행중이 아닌 경우 -->
    <!-- 우선 startStatus 하나만 가지고 사용 = runningGameState -->
    <div class="" v-if="!runningGameState" @click="startCatchMind">
         <div v-if="!isReadyCat" class="start-btn">캐치마인드</div>
            <div v-else class="start-btn">준비완료</div>
         </div>   

      <!-- 캐치마인드 시작 버튼 -->
      <button @click="startCM" v-if="!catchMindStatus && host">시작</button>
      <button @click="startABGame">
                      <div v-if="!isReadyBal">밸런스 게임</div>
                      <div v-else>준비완료</div>
      </button>
      <div class="draw-box" v-bind:class="{catDis:!catchMindStatus}">
        <!-- <Slider class="time-slider" v-if="catchMindStatus" v-model="countView" :max="60" /> - 이친구는 jquery인듯 돌아가는 게임이 없고 캐치마인드가 시작되었으면 -->
        <input  class="time-slider" type="range" :max="60"  v-if="catchMindStatus" v-model="countView">
         <!-- 정답 입력란 -->
        <div v-if="catchMindStatus && !myTurn"> <!-- 캐치마인드가 실행중 && 내가 그리는 차례가 아닐때-->
        <input class="ans-block" type="text" v-model="answer" placeholder="정답을 입력하세요" @keydown.enter="sendAns">
        </div>     
        <div class="pb-div" v-if=" myTurn && catchMindStatus">{{problem}}</div>  <!-- 게임중 && 내가 그릴 차례 && 캐치마인드 시작됨  -->
          <div class="draw-container" v-bind:class="{catDis:!catchMindStatus}"> <!-- 게임이 실행중이 아니면 catDis 클래스 추가 -->
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
            <div v-if="catchMindStatus && myTurn" class="draw-tool"> <!-- 내가 그릴 차례|| 캐치마인드 시작 안됨  -->
              <table class="draw-opt"> <!-- 그리기 도구 색상 지정 등-->
                <tr>
                  <td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="black" @click="colorChange('black')" width="24" height="24"/></td>
                  <td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="red" @click="colorChange('red')" width="24" height="24"/></td>
                  <td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="blue" @click="colorChange('blue')" width="24" height="24"/></td>
                  <td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="yellow" @click="colorChange('yellow')" width="24" height="24"/></td>
                  <td><Icon class="colorBtn" icon="akar-icons:circle-fill" color="green" @click="colorChange('green')" width="24" height="24"/></td>
                  <td><Icon class="colorBtn" icon="bi:eraser" @click="colorChange('white')" width="24" height="24"/></td>
                  <td><button class="removeBtn" @click="allDelete">전체 지우기</button></td>
                </tr>
              </table>
            </div>                  
          </div>          
        </div>
        <div>
          <input type="text" v-model="userEmail">
          <button @click="socketConnect">소켓 연결해보기</button>
        </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import MainNavbar from "@/components/MainPage/MainNavbar.vue";
import UserVideo from "@/components/Video/UserVideo.vue";
import SelectHateMbti from "@/components/Video/SelectHateMbti.vue";
import { mapActions } from 'vuex';

// ----------------  Catch mind start   
import Swal from 'sweetalert2'; // 이쁜 alert 생성하는 친구 
import { Icon } from '@iconify/vue'; // 아이콘 생성 //npm install --save-dev @iconify/vue
// ----------------  Catch mind end

// ----------------  Matching start   
import Stomp from 'webstomp-client'; // npm i webstomp-client sockjs-client - 웹 소켓 객체를 생성하기 위해 사용
import SockJS from 'sockjs-client'
// ----------------  Matching end

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "http://i7b206.p.ssafy.io:8080/api/v1/webrtc";

export default {
  name: "App",

  components: {
    MainNavbar,
    UserVideo,
    SelectHateMbti,
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
      micStatus: false,
      gameStatus: false,
      talkStatus: false,
      vidStatus: false,
      voiceStatus: true,
      bgStatus: false,

      myUserName: "",
      userHateMbtitList: [],
      mbtiModalOpen: false,

      // ----------------  Catch mind start   
      sessionName: undefined, // 방장 여부 확인

      host:true, // 호스트인지 확인 - 호스트는 게임 실행 권한이 있음 - 차후 처리 (todo)

      runningGameState:false, // 현재 실행중인 게임 있는지 확인용
      catchMindStatus:false, // 캐치마인드 게임이 실행중인지 확인용
      myTurn:false, // 자신이 그림그릴 차례인지 확인

      countView:'', // 남은시간이 얼마나 남았는지 표시      
      turn:1, // 캐치마인드 진행된 턴 수
      answer:'', // 정답 제출용 
      timerInit:null,   // 잘 모르겠음 todo ?????   
      count:60, // 제한 시간     
      // 문제 리스트
      catchProblem:["샴푸","송아지","강아지","소방관","포크레인","돈다발","철학","코딩","싸피","뚱이","스폰지밥","삼성","애플","아이폰","에어팟","경찰","신과함께","아이언맨","캡틴아메리카","토르","헐크"],
      problem:'', // 현재 문제

      canvas: null, // 그림을 그릴 캔버스 정보
      isDrawing: false, // 그리기 상태정보
      color: "black", // 펜 초기 색상
      penSize:2, // 펜 초기 사이즈
      x: 0, // 펜 좌표정보
         y: 0,// 펜 좌표정보
// ----------------  Catch mind end

//---------------- catch mind , matching start
    userEmail:'',
    socket:undefined,
    matchingTestToken: undefined,
    matchingSessionName: undefined,
//---------------- catch mind , matching end

// ----------------  ab game start

      abgamestartStatus:true,
      gamedata:[],
      A_item_list: ["토맛 토마토","민초","카레맛 똥", "팔만대장경 다 읽기","겨울 에어컨" ,"월 200 백수", "탕수육 부먹"],
         B_item_list: ["토마토맛 토","반민초","똥맛 카레", "대장내시경 팔만번 하기","여름 히터", "월 500 직장인", "탕수육 찍먹"],
         A_item:'',
         B_item:'',
      nextStatus:false,
      gIndex:0,
      gameStart:[],
      isSelected:false,
      isReadyBal:false,
      
// ----------------  ab game end
    };
  },
  computed: {

  },
  created() {
    const payload = JSON.parse(window.localStorage.vuex)
    this.fetchCurrentUser(payload.accounts.userEmail)
    this.userEmail = this.$store.state.accounts.userEmail
    this.myUserName = this.$store.state.accounts.user.userNickName
    // this.myUserName = this.getUserInfo.userNickName
    // this.mySessionId = this.getUserInfo.userEmail
  },
  mounted(){
// ----------------  Catch mind start   

    // 캔버스 초기화
    var c = document.getElementById("myCanvas");
      this.canvas = c.getContext(`2d`); 

// ----------------  Catch mind end 
  },
  methods: {
    ...mapActions(['fetchCurrentUser']),
    selectmbti(value) {
      this.mbtiModalOpen = false;
      this.userHateMbtitList = value;
    },
    startTalk(){
         this.talkStatus=!this.talkStatus;
         // this.session.signal({
         //    data: this.isReadyBal,
         //    to:[],
         //    type: 'game-start'
         // })
      console.log(this.talkStatus)
      },
      startGame2(){
         this.isReadyCat=!this.isReadyCat;
         this.session.signal({
            data: this.isReadyCat,
            to:[],
            type: 'game-start2'
         })
      },
    vidControll(){
      this.voiceStatus = !this.voiceStatus;
         this.vidStatus = !this.vidStatus;
      this.talkStatus = !this.talkStatus
      this.gameStatus = !this.gameStatus
         this.publisher.publishVideo(this.vidStatus);
      console.log(this.vidStatus)
      },

    joinSession() {
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
      this.session.on('signal:catch-start',(event)=>{ // 게임 시작 시그널
      
            this.catchMindStatus=true; // 캐치마인드 진행중으로 변경
            this.count=60; // 시간초 초기화
            this.myTurn = !this.myTurn; // 그림 그리는 턴 변경
            let pb = JSON.parse(event.data); // 주어진 문제 파싱 및 변수 등록
            this.problem = pb.problem;
            if(this.turn > 4){ // 4턴 이상 진행되면 캐치마인드 종료
               this.endCM();
               return;
            }
            if(this.myTurn){ // 그림그리기 차례인 경우
               Swal.fire({ //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
               title:"내 차례에요!",
               showConfirmButton: false,
                      timer: 1500
               })
            }
            else{
               Swal.fire({ //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
               title:"상대방 차례에요!",
               showConfirmButton: false,
                      timer: 1500
               })
            }
         })

      this.session.on('signal:catch-end',(event)=>{ // 게임 종료 시그널
            let val = JSON.parse(event.data); // 턴수 정보
            this.turn = val.turn;
            if(this.timerInit!=null){ // ??? todo
               clearInterval(this.timerInit); 
               this.timerInit=null;
            }
            this.allDelete(); // 캔버스 초기화
            if(this.turn>4){ // 진행된 turn이 4 이상인 경우 게임 종료
               Swal.fire({ // alert 보내기
               title:"게임 끝!",
               showConfirmButton: false,
                      timer: 1500
               }).then(()=>{
                  this.catchMindStatus=false; // 게임중 상태 해제
                  this.turn=1; // 기타 게임 데이터 초기화
                  this.count=60;
               })
               return;
            }
            if(this.turn<5){
               setTimeout(() => {
                  this.startCM(); // 다음 게임 실행
               }, 1000);
            }
         })

      // 그리기 도구 관련 시그널
      this.session.on('signal:drawing-opt',(event)=>{ // 그리기 도구 색상 변경 
            if(event.data=="all"){ // 모두 지우기 이벤트 - allDelete() 
               this.canvas.clearRect(0, 0, 560, 360);
            }
            else if(event.data=="white"){ // 흰색으로 변경 - 지우개
               this.penSize = 4;
               this.color = event.data;
            }
            else{ // 다른 컬러로 변경
               this.color = event.data;
               this.penSize = 2;
            }
         })

      // 그리기 관련 시그널 - 마우스 down
      this.session.on('signal:start-draw',(event)=>{
            let sm = JSON.parse(event.data);
            this.x = sm.x;
            this.y = sm.y;
            this.isDrawing=sm.isDrawing;
         })
      // 그리기 관련 시그널 - 마우스 up, move
      this.session.on('signal:drawing',(event)=>{
            let sm = JSON.parse(event.data);
            this.drawLine(this.x, this.y, sm.x, sm.y, this.color);
            this.x = sm.x;
            this.y = sm.y;
            this.isDrawing=sm.isDrawing;
         })

      //정답 제출 관련
      this.session.on('signal:send-answer', (event)=>{
            let info = JSON.parse(event.data); // name, ans 정보 받기
            if(this.problem==info.ans){ // 정답인경우
               Swal.fire({ // alert 메시지
               title:`${info.name}님이 정답을 맞추셨습니다.`,
               showConfirmButton: false,
                      timer: 1500
               }).then(()=>{   
                  this.allDelete(); // 캔버스 지우기
               })
            }
         })

      // 타이머
      this.session.on('signal:timer',(event)=>{
            this.count = event.data; // count 정보
            // this.count = +this.count || 0; // 카운트 증가 시키기
            this.countView =  this.count; // 10초 남은경우 카운트 뷰를 보여줌
         })

// ----------------  Catch mind end

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend

      this.getCreateToken(this.mySessionId).then((token) => {
        console.log("debug - token Info", token);
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: false, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);

            this.voiceControll();
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

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
            headers:{
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`
          }
          })
          .then((response) => {
            console.log("debuf - getCreateToken", response.data["token"]);
            //this.token = response.data["token"];
            // ----------------  Catch mind start 
            self.initMyTurn();  // 내차례 정하기
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
  voiceControll(){
         // this.voiceStatus=!this.voiceStatus;
         let pitchs = ['0.76', '0.77', '0.78', '0.79', '0.80', '1.3', '1.4', '1.5', '1.6', '1.7']
            let pitch = pitchs[8]
         if(this.voiceStatus){
            this.publisher.stream.applyFilter("GStreamerFilter", {"command": `pitch pitch=${pitch}`});
         }
         else{
            this.publisher.stream.removeFilter();
         }
      },
    // 아마도 FaceOverlay 적용하기
    videoControll() {
      this.publisher.stream.applyFilter("FaceOverlayFilter").then((filter) => {
        filter.execMethod("setOverlayedImage", {
          uri: "https://cdn.pixabay.com/photo/2013/07/12/14/14/derby-148046_960_720.png",
          offsetXPercent: "-0.4F",
          offsetYPercent: "-0.4F",
          widthPercent: "2.0F",
          heightPercent: "2.0F",
        });
      });
    },

    removeFilter(){
      this.publisher.stream.removeFilter();
    },

    // ----------------  Catch mind start   

    // 그리기 도구 관련
    colorChange(col) { // 그리기 도구 - 색상 변경 method
      this.session.signal({
        data: col,
        to:[],
        type: 'drawing-opt'
      })
    },

    allDelete() { // 그리기 도구 - 현재 그림 다 지우기 메소드
      this.session.signal({
        data: "all",
        to:[],
        type: 'drawing-opt'
      })
    },
    
    // 그림 그리기 시작 - 마우스 클릭시
    beginDrawing(e) {
      if(!this.myTurn) return;
      this.x = e.offsetX;
      this.y = e.offsetY;
      this.isDrawing = true;
      const sdata = {
        x : e.offsetX,
        y : e.offsetY,
        isDrawing : true,
      }; 
      this.session.signal({
            data: JSON.stringify(sdata),
            to:[],
            type: 'start-draw'
      })
    },

    // 그림 그리기 - 마우스 이동
    keepDrawing(e) {
      if (this.isDrawing) {
        const sdata = {
          x : e.offsetX,
          y : e.offsetY,
          isDrawing : true,
        }; 
        this.session.signal({
          data: JSON.stringify(sdata),
          to:[],
          type: 'drawing'
        })
      }
    },

    // 그림 그리기 - 마우스 up - 선 끊어짐
    stopDrawing(e) {
      if (this.isDrawing) {
        const sdata = {
          x : e.offsetX,
          y : e.offsetY,
          isDrawing : false,
        };
        this.session.signal({
          data: JSON.stringify(sdata),
          to:[],
          type: 'drawing'
        })
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
    sendAns(){ // 정답 입력하기 메소드
      const sdata = { // 보낼 데이터 
        name: this.myUserName,
        ans : this.answer
      }
      this.session.signal({ // signal로 보내기 
        data: JSON.stringify(sdata), // json 형식으로 정답 보내기
        to:[],
        type: 'send-answer'
      })
      if(this.answer==this.problem){ // 정답인 경우
        this.answer=''; // 내 정답 초기화
        setTimeout(() => {
          this.endCM(); // 캐치마인드 종료
        }, 1000);
      }
      this.answer='';
    },

    // 캐치마인드 게임 시작
    startCM(){ // 캐치마인드 게임 1턴 시작
      if(this.turn>4){ // 4개의 문항이 완료되면 게임 종료???
        return;
      }
      let pb = this.catchProblem[Math.floor(Math.random() * this.catchProblem.length)] // 랜덤 문항 출제
      const sdata={ // 문제 출제용 데이터
        problem : pb,
      }
      this.session.signal({ // 문제 출제 signal 보내기
        data: JSON.stringify(sdata),
        to:[],
        type: 'catch-start'
      })
      this.timerInit = setInterval(()=>{
        this.timer(); // 타이머 동작하도록 
      },1000)
    },

    // 캐치마인드 게임 종료
    endCM(){ // 캐치마인드 게임 1턴 종료
      this.turn++; // 현재 턴 증가
      const sdata = { // 턴 정보 공유
        turn : this.turn,
        }
      this.session.signal({
        data: JSON.stringify(sdata),
        to:[],
        type: 'catch-end'
      })
    },

    // 게임 시간 타이머 
    timer(){
      if(this.count>1){ // 카운트가 아직 남아 있는 경우
        this.count--; //카운트 감소
        this.session.signal({ // 감소한 카운트 정보 배포
          data: this.count,
          to:[],
          type: 'timer'
          })
      }
      else{
        this.session.signal({ // 카운트가 끝난경우
          data: 0,
          to:[],
          type: 'timer'
        })
        this.endCM(); // 현재 진행중인 캐치마인드 한턴 증가 및 종료
      }
    },
    initMyTurn(){ // sessionName이 있는경우 myTurn을 true로 반대의 경우 false로 입력
      console.log("debug - initMyTurn ", this.matchingSessionName);
      this.myTurn = this.matchingSessionName==undefined?true:false;     
    },
// ----------------  Catch mind End  

//---------------- random matching start 
    matchingReqeust(){ //매칭 요청 하기
      let self = this;
      // this.socketConnect();
      setTimeout(() => {
        console.log(localStorage.getItem("accessToken"))
        axios({
        url: `${OPENVIDU_SERVER_URL}/matching`,
        method: 'post',
        data: {userEmail: self.userEmail},
        headers: {
          Authorization: `Bearer ${user.accessToken}`
        },
      })        
        .then((response) => {
          console.log("debuf - matchingReqeust", response);                  
        })
        .catch((err) => {
          console.log(err);
          self.socketClose();
        });
      }, 1000);      
    },
    
    cancleMatchingReqeust(){ //매칭 요청 취소 하기
      let self = this;
       axios({
        url: `${OPENVIDU_SERVER_URL}/exitMatching`,
        method: 'post',
        data: {userEmail: self.userEmail},
        headers: {
          Authorization: `Bearer ${user.accessToken}`
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

    waitMaching(){ // 소켓 연결 후 매칭 대기

    },

    socketConnect(){ // 소켓 연결
      let self = this;
      const serverURL = "https://i7b206.p.ssafy.io:8080/ws"
      this.socket = new SockJS(serverURL);     
      this.stompClient = Stomp.over(this.socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
      this.stompClient.connect(
        {
          Authorization: `Bearer ${user.accessToken}`
        },
        frame => {
          // 소켓 연결 성공          
          console.log('소켓 연결 성공', frame);         
          this.stompClient.subscribe("/sub/matching/"+self.userEmail, res => {
            let data = JSON.parse(res.body);
            console.log('구독으로 받은 메시지 입니다.', data);
            console.log('구독으로 받은 메시지 입니다.', data.token);
            console.log('구독으로 받은 메시지 입니다.', data.sessionName);
            self.matchingTestToken = data.token;
            self.matchingSessionName = data.sessionName;
            self.joinSession2();
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);          
        }
      );  
    },

    socketClose(){ // 소켓 연결 취소
      this.socket.close();
      console.log("소켓 연결 취소");
    },

    matchingRoomEnter(){
      
//---------------- random matching end
    },

    joinSession2() {
      
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
        this.session.on('signal:catch-start',(event)=>{ // 게임 시작 시그널 
            this.catchMindStatus=true; // 캐치마인드 진행중으로 변경
            this.count=60; // 시간초 초기화
        
            // this.myTurn = !this.myTurn; // 그림 그리는 턴 변경
        console.log("myTurn 1" , this.myTurn + " " + this.turn);
        if(this.matchingSessionName == undefined && this.turn%2==1) this.myTurn = true;
        else if(this.matchingSessionName == undefined && this.turn%2==0) this.myTurn = false;
        else if(this.matchingSessionName != undefined && this.turn%2==1) this.myTurn = false;
        else this.myTurn = true;

        console.log("myTurn 2" , this.myTurn + " " + this.turn);
            let pb = JSON.parse(event.data); // 주어진 문제 파싱 및 변수 등록
            this.problem = pb.problem; 

            if(this.turn > 4){ // 4턴 이상 진행되면 캐치마인드 종료
               this.endCM();
               return;
            }
            if(this.myTurn){ // 그림그리기 차례인 경우
               Swal.fire({ //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
               title:"내 차례에요!",
               showConfirmButton: false,
                      timer: 1500
               })
            }
            else{
               Swal.fire({ //alert 메시지 보내기 //import Swal from 'sweetalert2'; 추가 필요
               title:"상대방 차례에요!",
               showConfirmButton: false,
                      timer: 1500
               })
            }
        });
         // ---------------------- abGame Start

      this.session.on('signal:game-start',(event) => {
        console.log("빤빵빵빵");
        console.log(event);
        
            if(event.data){
          console.log("뿡");
               this.gameStart.push(event.data);
          console.log(event.data);
            }
            else{
               this.gameStart.pop();
            }
            if(this.gameStart.length==2){
          console.log("뿡뿡뿡");
               this.startStatus=true;
               this.A_item = this.A_item_list[this.gIndex];
               this.B_item = this.B_item_list[this.gIndex];
            }
         })

      this.session.on('signal:my-game', (event) => {
            this.gamedata.push(JSON.parse(event.data));
            if(this.gamedata.length==2){
               this.nextStatus=true;
            }
         });

      this.session.on('signal:game-next',() =>{ 
        
            if(this.gamedata.length==2){
               if(this.gIndex<this.A_item_list.length-1){
                  this.nextStatus=false;
                  this.isSelected=false;
                  this.gIndex++;
                  this.A_item = this.A_item_list[this.gIndex];
                  this.B_item = this.B_item_list[this.gIndex];
                  this.gamedata=[];
               }
               else{
                  Swal.fire('밸런스 게임 종료!');
                  this.gameStart=[];
                  this.gamedata=[];
                  this.startStatus = false;
                  this.isReadyBal = false;
                  this.gIndex=0;
               }
            }
         });
      // ---------------------- abGame End
         

      this.session.on('signal:catch-end',(event)=>{ // 게임 종료 시그널
            let val = JSON.parse(event.data); // 턴수 정보
            this.turn = val.turn;
            if(this.timerInit!=null){ // ??? todo
               clearInterval(this.timerInit); 
               this.timerInit=null;
            }
            this.allDelete(); // 캔버스 초기화
            if(this.turn>4){ // 진행된 turn이 4 이상인 경우 게임 종료
               Swal.fire({ // alert 보내기
               title:"게임 끝!",
               showConfirmButton: false,
                      timer: 1500
               }).then(()=>{
                  this.catchMindStatus=false; // 게임중 상태 해제
                  this.turn=1; // 기타 게임 데이터 초기화
                  this.count=60;
               })
               return;
            }
            if(this.turn<5){
               setTimeout(() => {
                  this.startCM(); // 다음 게임 실행
               }, 1000);
            }
         })

      // 그리기 도구 관련 시그널
      this.session.on('signal:drawing-opt',(event)=>{ // 그리기 도구 색상 변경 
            if(event.data=="all"){ // 모두 지우기 이벤트 - allDelete() 
               this.canvas.clearRect(0, 0, 560, 360);
            }
            else if(event.data=="white"){ // 흰색으로 변경 - 지우개
               this.penSize = 4;
               this.color = event.data;
            }
            else{ // 다른 컬러로 변경
               this.color = event.data;
               this.penSize = 2;
            }
         })

      // 그리기 관련 시그널 - 마우스 down
      this.session.on('signal:start-draw',(event)=>{
            let sm = JSON.parse(event.data);
            this.x = sm.x;
            this.y = sm.y;
            this.isDrawing=sm.isDrawing;
         })
      // 그리기 관련 시그널 - 마우스 up, move
      this.session.on('signal:drawing',(event)=>{
            let sm = JSON.parse(event.data);
            this.drawLine(this.x, this.y, sm.x, sm.y, this.color);
            this.x = sm.x;
            this.y = sm.y;
            this.isDrawing=sm.isDrawing;
         })

      //정답 제출 관련
      this.session.on('signal:send-answer', (event)=>{
            let info = JSON.parse(event.data); // name, ans 정보 받기
            if(this.problem==info.ans){ // 정답인경우
               Swal.fire({ // alert 메시지
               title:`${info.name}님이 정답을 맞추셨습니다.`,
               showConfirmButton: false,
                      timer: 1500
               }).then(()=>{   
                  this.allDelete(); // 캔버스 지우기
               })
            }
         })

      // 타이머
      this.session.on('signal:timer',(event)=>{
            this.count = event.data; // count 정보
            // this.count = +this.count || 0; // 카운트 증가 시키기
            this.countView =  this.count; // 10초 남은경우 카운트 뷰를 보여줌
         })

      // ----------------  Catch mind end

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend

      console.log("debug - matchingRoomEnter token Info", this.matchingTestToken);
      console.log("debug - matchingRoomEnter token Info", this.matchingSessionName);
      this.session
        .connect(this.matchingTestToken, { clientData: this.userEmail })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;            
            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
            
            console.log("------initMyTurn");
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

    startABGame(){ // 밸런스게임 시작
      console.log("-----뿡뿡");
         this.isReadyBal=!this.isReadyBal;
         this.session.signal({
            data: this.isReadyBal,
            to:[],
            type: 'game-start'
         })
      },
    nextProblem(){
         if(!this.nextStatus && this.isSelected){ 
            Swal.fire('상대방이 선택하지 않았습니다')
            return;
         }
         else if(!this.nextStatus && !this.isSelected){
            Swal.fire('둘 중 하나를 선택해 주세요 ✅')
         }
         this.nextStatus=false;
         this.isSelected=false;

         this.session.signal({
            data: "next",
            to:[],
            type: 'game-next'
         })
         
      },
    selectA(){
         if(this.isSelected) return;
         this.isSelected = true;
         const sdata = {
            userId : this.myUserName,
            select : this.A_item,
         };
         
         this.session.signal({
            data: JSON.stringify(sdata),
            to:[],
            type: 'my-game'
         })
      },
      selectB(){
         if(this.isSelected) return;
         this.isSelected = true;
         const sdata = {
            userId : this.myUserName,
            select : this.B_item,
         };
         
         this.session.signal({
            data: JSON.stringify(sdata),
            to:[],
            type: 'my-game'
         })
         
      },
    // ---------------- abgame End
  },
};
</script>

<style scoped>
.card {
  position: fixed;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.card2 {
  outline-style: solid;
  outline-color: #97cdbd;
  /* height: 40vh; */
  /* background: url("https://s-media-cache-ak0.pinimg.com/564x/cf/1e/c4/cf1ec4b0c96e59657a46867a91bb0d1e.jpg") no-repeat;
  background-size: cover;
  background-position: center center; */
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

.v_box {
  margin-top: 1rem;
  display: flex;
  justify-content: space-between;
  width: 100%;
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

.catDis{
   visibility: hidden;
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
.select-box:hover{
   transform: scale(1.1);
   cursor: pointer;
   border: 1px solid #ff4e7e;
}

.select-comment {
   border: 2px solid #ff4e7e;
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
   color: #ff4e7e;
   border: 2px solid #ff4e7e;
}
</style>