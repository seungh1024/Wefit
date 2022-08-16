<template>
  <div class="Back">
    <div class="login">
      <div class="background">
      <div class="top-box"></div>
      <div class="d-flex justify-content-center">
        <h2>로그인 페이지</h2>
      </div>
      <div class="bottom-box"></div>
      <form class="loginform">
        <div>
          <div>
            <label for="emailInput" id = "emailtext">아이디</label>
          </div>
            <input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
          </div>
        <div>
          <div class="mt-3">
            <label for="passwordInput" id = "passwordtext">비밀번호</label>
          </div>
				    <input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			  </div>
        <div class="buttons">
          <button @click.prevent="login" class="button blue" id = "loginbtn">로그인</button>
          <div class="center_box">
            <div class="fw_su_box mt-2">
              <div @click.prevent="$router.push('findid')" id = "findid">아이디찾기</div>  
              <div @click.prevent="$router.push('findpw')" id = "findpw" >비밀번호찾기</div>  
              <div @click.prevent="$router.push('signup')" id = "signup">회원가입</div>
            </div>
          </div> 
          <div>
            <button @click.prevent="google" id = "googlebtn" type="button" class="mt-2"></button>
          </div>
        </div>
		  </form>
    </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getAuth, signInWithPopup, GoogleAuthProvider } from "firebase/auth";
export default {
    name: 'LoginView',
    components: {
    },
    data() {
        return {
            userEmail: '',
            userPassword: ''
        }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
    login(event) {
      event.preventDefault()
      const userData = {
        "userEmail": this.userEmail,
        "userPassword": this.userPassword
      }
      // dispatch
      this.$store.dispatch('login', userData);
    },
	google(){
		const provider = new GoogleAuthProvider();
		provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
		provider.setCustomParameters({
       'login_hint': 'user@example.com'
        });
		const auth = getAuth();
    signInWithPopup(auth, provider).then((result) => {
    // This gives you a Google Access Token. You can use it to access the Google API.
    // The signed-in user info.
    //const user = result.user;
		//console.log(token);
		this.$store.dispatch('socialLogin', result.user)
		
    // ...
     }).catch((error) => {
    // Handle Errors here.
    const errorCode = error.code;
    const errorMessage = error.message;
    // The email of the user's account used.
     const email = error.customData.email;
    // The AuthCredential type that was used.
    //const credential = GoogleAuthProvider.credentialFromError(error);
    // ...
     });
	},
    },
}
</script>

<style scoped>
@font-face {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h2{
  font-family: 'GmarketSansMedium';
	color : white;
  -ms-user-select: none; 
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
 }
.title{
  color:white;
  width: 100%;
}
.top-box{
  height: 65px;
}
.bottom-box{
  height: 60px;
}

.fw_su_box{
  width: 55%;
  display: flex;
  justify-content: space-between;
}
.center_box{
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.background{
  height: 660px;
  width: 500px;
  overflow: hidden;
  margin:0;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image:linear-gradient(
    rgba(0, 0, 0, 0),
        rgba(255, 255, 255, 0)
      ), url("@/assets/MainPage/menu_bg4.png");
}
.login{
  width:80%; 
  min-width: 500px;
  margin:0 auto; 
  display: flex;
  justify-content: center;
  align-items: center;
}

.Back{
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: rgba(30, 30, 30, 6);
}

 .loginform{
  text-align:center;
}

 #signup {
  font-weight: bold;
  color: white;
  cursor:pointer;
 }
 #findid {
  font-weight: bold;	
  color: white;
  cursor:pointer;
 }
 #findpw{
  font-weight: bold;
  color: white;
  cursor:pointer;
 }
 #emailtext{
  color: white;
 }
 #passwordtext{
  color: white;
 }
 input{
  border-radius: 8px;
  width: 315px;
  height: 40px;
 }
 input::placeholder{
  padding-left: 10px;
 }
 #loginbtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 315px;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: rgb(30,30,30);
    font-weight: bold;
    background: rgb(217, 188, 128);
    border: 0;
    margin-top: 30px;
    font-size: 17px;
    margin-bottom : 10px;
 }

 /* #kakaobtn{
  background-image: url("@/assets/Kakao_Login.png");
  margin-top:5px;
  width: 315px;
  height: 47px;
  border-radius: 8px;
 }

 #naverbtn{
  background-image: url("@/assets/Kakao_Login.png");
  width: 315px;
  height: 47px;

  border-radius: 8px;
 } */
 #googlebtn{
  background-image: url("@/assets/Google_Login2.png");
  width: 315px;
  height: 49px;
  border-radius : 7px;
 } 
</style>