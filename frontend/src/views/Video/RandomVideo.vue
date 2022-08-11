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
                  <label>Session {{ mySessionId }}</label>
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
                  <button class="btn btn-lg btn-success" @click="joinSession()">
                    Join!
                  </button>
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
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import MainNavbar from "@/components/MainPage/MainNavbar.vue";
import UserVideo from "@/components/Video/UserVideo.vue";
import SelectHateMbti from "@/components/Video/SelectHateMbti.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "http://i7b206.p.ssafy.io:8080/api/v1/webrtc";

export default {
  name: "App",

  components: {
    MainNavbar,
    UserVideo,
    SelectHateMbti,
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

      mySessionId: "3333",
      myUserName: "3333",
      userHateMbtitList: [],
      mbtiModalOpen: false,
    };
  },
  computed: {

  },
  created() {
    // this.myUserName = this.getUserInfo.userNickName
    // this.mySessionId = this.getUserInfo.userEmail
  },
  methods: {
    selectmbti(value) {
      this.mbtiModalOpen = false;
      this.userHateMbtitList = value;
    },
    startTalk(){
			this.talkStatus=!this.talkStatus;
			// this.session.signal({
			// 	data: this.isReadyBal,
			// 	to:[],
			// 	type: 'game-start'
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
          })
          .then((response) => {
            console.log("debuf - getCreateToken", response.data["token"]);
            //this.token = response.data["token"];
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
</style>