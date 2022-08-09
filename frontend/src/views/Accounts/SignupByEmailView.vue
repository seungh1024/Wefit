<template>
 <!-- <div class="signupbyemail">
		<form class="signupbyemailform">
		<h2>회원가입 페이지</h2>
			<p>
				<div>
				<label for="emailInput" id = "emailtext" class = "text">email</label>
				</div>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="아이디를 입력하세요." />
			</p>
			<p>
				<div>
				<label for="passwordInput" id = "passwordtext" class = "text">password</label>
				</div>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			</p>
            <p>
				<div>
				<label for="passwordInput" id = "passwordtext" class = "text">password confirmation</label>
				</div>
				<input type="password" id="passwordInput" name="userPasswordCheck" class="input_text" ref="passwordInput" v-model.trim="userPasswordCheck" placeholder="패스워드를 확인하세요." />
			</p>
            <div>                
                <input type = "checkbox"> <label>i agree 어쩌구</label>
            </div>
            <div>
                <button @click.prevent = "emailSignup" id = "btn"> create your account</button>
            </div>
        </form>
 </div> -->
<div class="Back">
    <div class="login">
      <div class="background">
      <div class="top-box"></div>
      <div class="d-flex justify-content-center">
        <h2>회원가입 페이지</h2>
      </div>
      <div class="bottom-box"></div>
      <form class="loginform">

          <div>
            <label for="emailInput" id = "emailtext" class = "text">이메일</label>
            </div>
            <input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="이메일을 입력하세요." />
        <div>
          <div>
				<label for="passwordInput" id = "passwordtext" class = "text">비밀번호</label>
				</div>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="패스워드를 입력하세요." />
			  </div>
              <div>
                <div>
				<label for="passwordInput" id = "passwordtext" class = "text">비밀번호 확인</label>
				</div>
				<input type="password" id="passwordInput" name="userPasswordCheck" class="input_text" ref="passwordInput" v-model.trim="userPasswordCheck" placeholder="패스워드를 확인하세요." />
              </div>
        <div class="buttons">
          <button @click.prevent="login" class="button blue" id = "loginbtn">회원가입</button>
          <div class="center_box">

          </div>
        </div>
		  </form>
    </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'SignupbyemailView',
    components: {

    },
     props : {

    },
    data() {
        return {
            userEmail: '',
            userPassword: '',
            userPasswordCheck:''
        }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
      emailSignup(event) {
      event.preventDefault();
      if(this.userEmail != '')
      {
      const userData = {
        "userEmail": this.userEmail,
        "userPassword": this.userPassword,
      }
	    if(this.userPassword !='' &&this.userPassword == this.userPasswordCheck)
	    {
         this.$store.dispatch('signup', userData);
	   	 //this.$router.push('signupdetail');
	    }
	    else{
	  	alert('비밀번호를 확인하세요');
	     }
      }
      else{
      alert('아이디를 확인하세요');
      }
    },
    }
}
</script>
<style scoped>
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

 h2{
	color : white;
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
checkbox{
    padding:0px;
    margin:0px
}
.checkbox_1{
    color: #fff;
}
</style>