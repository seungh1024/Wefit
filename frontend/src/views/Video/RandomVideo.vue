<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>랜덤매칭 시작하기</h1>
        <div class="form-group">
          <p>
            <label>Participant</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>Session</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              Join!
            </button>
          </p>
        </div>
      </div>
    </div>
    <div v-if="session" class="container-fluid">
      <div class="header">
        <div class="video-header">위핏</div>
      </div>
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
          <!-- <div class="col-3">
            <RightPanel />
          </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/Video/UserVideo.vue";
// import RightPanel from "./RightPanel.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "http://i7b206.p.ssafy.io:8080";


export default {
  name: "App",

  components: {
    UserVideo,
    // RightPanel,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      micStatus:false,
      gameStatus:false,

      mySessionId: '',
      myUserName: '',
    };
  },
  computed: {

  },
  created(){
    // this.myUserName = this.getUserInfo.userNickName
    // this.mySessionId = this.getUserInfo.userEmail
  }
  ,
  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        console.log('stream테스트')
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
        this.session.connect(token, { clientData: this.myUserName })
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
    getCreateToken(mySessionId){
    return new Promise((resolve, reject) => {
      axios.post(`${OPENVIDU_SERVER_URL}/api/v1/createSession`, {"userEmail":mySessionId})
      .then((response => {
		console.log("debuf - getCreateToken", response.data["token"]);
        //this.token = response.data["token"];
        resolve(response.data["token"]);        
      }))
      .catch((err => {
        console.log(err);
        reject(err.response);
      }));  
      });
    },

	getjoinToken(){
      axios.post(`${OPENVIDU_SERVER_URL}/api/v1/joinSession`,{
        "sessionNameParam" : this.sessionName
      })
      .then((response => {
		console.log("getjoinToken", response);
        this.token = response.data["token"];
        return this.token;        
      }))
      .catch((err => {
        console.log(err);        
      }));    
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server    (POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    // getToken(mySessionId) {
    //   return this.createSession(mySessionId).then((sessionId) =>
    //     this.createToken(sessionId)
    //   );
    // },

    // // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    // createSession() {
    //   return new Promise((resolve, reject) => {
    //     axios
    //       .post(
    //         `${OPENVIDU_SERVER_URL}api/v1/createSession`,
    //         JSON.stringify({
    //           // customSessionId: sessionId,
    //               "userEmail":this.myUserName
    //         }),
    //         {
    //           auth: {
    //             username: "OPENVIDUAPP",
    //             password: OPENVIDU_SERVER_SECRET,
    //           },
    //         }
    //       )
    //       .then((response) =>{
    //         response.data
    //         console.log(response.data)
    //       } 
    //       )
    //       .then((data) => resolve(data.token))
    //       .catch((error) => {
    //         if (error.response.status === 409) {
    //          // resolve(sessionId);
    //         } else {
    //           console.warn(
    //             `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
    //           );
    //           if (
    //             window.confirm(
    //               `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
    //             )
    //           ) {
    //             location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
    //           }
    //           reject(error.response);
    //         }
    //       });
    //   });
    // },

    // // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    // createToken(myUserName) {
    //   return new Promise((resolve, reject) => {
    //     axios
    //       .post(
    //         `${OPENVIDU_SERVER_URL}/api/v1/createSession`,
    //         {"userEmail":myUserName},
    //         {
    //           auth: {
    //             username: "OPENVIDUAPP",
    //             password: OPENVIDU_SERVER_SECRET,
    //           },
    //         }
    //       )
    //       .then((response) => {response.data;
    //       console.log("createSessionData", response.data);
    //       })
    //       .then((data) => resolve(data.token))
    //       .catch((error) => reject(error.response));
    //   });
    // },
  },
};
</script>

<style scoped>
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