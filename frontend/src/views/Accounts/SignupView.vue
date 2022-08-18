<template>
  <div class="Back">
    <div class="signup">
      <div class="background">
      <div class="top-box"></div>
      <div class="d-flex justify-content-center">
        <h2>회원가입 페이지</h2>
      </div>
      <div class="bottom-box"></div>
      <div class="center_box">
        <div>
          <button @click.prevent="$router.push('signupbyemail')" id = "signupbtn">이메일로 가입하기</button>
        </div>
      </div>
      <div class="center_box">
        <div>
          <button @click.prevent="googleSignup" id = "googlebtn" type="button"></button>
        </div>
      </div>
      <div class="center_box">
        <div>
          <button @click.prevent = "$router.push('login')" id = "signupbtn">로그인하러가기</button>
          </div>
        </div>
    </div>
    </div>
  </div>
</template>


<script>
import { mapGetters } from 'vuex'
import { getAuth, signInWithPopup, GoogleAuthProvider } from "firebase/auth";
export default {
    name: 'SignupView',
    components: {

    },
    data() {
        return {

        }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
    googleSignup(){
      const provider = new GoogleAuthProvider();
      provider.addScope('https://www.googleapis.com/auth/contacts.readonly');
      provider.setCustomParameters({
        'login_hint': 'user@example.com'
          });
      const auth = getAuth();
      signInWithPopup(auth, provider).then((result) => {
      // This gives you a Google Access Token. You can use it to access the Google API.
      const credential = GoogleAuthProvider.credentialFromResult(result);
      console.log(result.user);
      const user = result.user;
    
    // The signed-in user info.
    //const user = result.user;
		
		this.$store.dispatch('socialSignup', user)
    // ...
     }).catch((error) => {
    // Handle Errors here.
    // const errorCode = error.code;
    // const errorMessage = error.message;
    // // The email of the user's account used.
    // const email = error.customData.email;
    // // The AuthCredential type that was used.
    // const credential = GoogleAuthProvider.credentialFromError(error);
    // // ...
      console.error(error)
     });
      }
    }
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
.signup{
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

 #signupbtn{
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
    height: 58px;
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

.center_box{
  display: flex;
  margin-bottom: 10px;
  justify-content: center;
}
 #googlebtn{
  background-image: url("@/assets/google_signup.png");
  width: 315px;
  height: 58px;
  border-radius : 7px;
 } 

</style>
