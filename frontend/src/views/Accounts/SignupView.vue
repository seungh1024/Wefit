<template>
	<div class="signup" >
    <form class="signupform">
		<h2>회원가입 페이지</h2>
      <form class = "buttons">
        <div>
				<button @click.prevent="$router.push('signupbyemail')" id = "signupbtn">이메일로 가입하기</button>
        </div>
        <div>
				<button class="button" id = "googlebtn"  @click.prevent = "googleSignup">구글 계정으로 가입</button>
        </div>
        <div>
				<button class="button" id = "kakaobtn"  @click.prevent = "kakaoSignup">카카오로 시작하기</button>
        </div>
        <div>
				<button class="button" id = "naverbtn" @click.prevent = "naverSignup">네이버로 시작하기</button>
        </div>
        <div>이미 회원이신가요? <span id = "login" @click.prevent = "$router.push('login')">로그인</span></div>
      </form>
		</form>
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
    const errorCode = error.code;
    const errorMessage = error.message;
    // The email of the user's account used.
    const email = error.customData.email;
    // The AuthCredential type that was used.
    const credential = GoogleAuthProvider.credentialFromError(error);
    // ...
     });
      }
    }
}
</script>

<style scoped>
h2{
  text-align:center;
  color : red;
}
.signupform{
  text-align:center;
  position : relative;
  top :25%;
}
.buttons{

  }
#naverbtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 23%;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    background: #2db400;
    border: 0;
    margin-top: 10px;
    font-size: 17px;
}

#kakaobtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 23%;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: black;
    font-weight: bold;
    background: #fde102;
    border: 0;
    margin-top: 10px;
    font-size: 17px;
}
#googlebtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 23%;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: black;
    font-weight: bold;
    background : white;
    border: 0;
    margin-top: 10px;
    font-size: 17px;
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
    width: 23%;
    height: 46px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    background: #ef3e43;
    border: 0;
    margin-top: 50px;
    font-size: 17px;
}
#login{
  font-weight: bold;
  color : red;
  cursor:pointer;
}
.signup{
    width : 100vw;
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
    object-fit: cover;
 }

</style>