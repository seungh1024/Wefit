<template>
    <div class="background">
      <v-row>
          <div class="homebox">
          </div>
      </v-row>
      <div>
        
        <h1><div class="wefit_logo" />EFIT</h1>
        <button class="btn btn-outline-light loginbtn">
          <router-link :to="{name:'LoginView'}" class="loginstyle">Login</router-link>
        </button>
        <button class="btn btn-outline-light signupbtn">
          <router-link :to="{name:'SignupView'}" class="signupstyle">Signup</router-link>
        </button>
      </div>
      <div class="spacebox"></div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>

/* 배경 사진 */
.background{
  height: 100vh;
  width:100%;
  overflow: hidden;
  margin:0;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image:linear-gradient(
    rgba(6, 6, 6, 0),
        rgba(255, 255, 255, 0)
      ), url("@/assets/images/Home.png");
      min-width: 1500px;
}

/* 상단 margin 처리 */
.homebox{
  height: 10rem;
}

/* 중간 상자(공백 만들기) */
.spacebox{
  height:25rem;
}

/* 기본 폰트 */
h1{
  color:white;
  font-size: 150px;
  margin-left: 7rem;
}

/* 반응형 */
.homebox{
  height: 25rem;
}
h1{
  color:white;
  font-size: 150px;
}

.wefit_logo{
  display: inline-block;
  width:216px;
  height: 130px;
  background-image: url("@/assets/WeFIt_title_Logo.PNG");
}
/* 아랫배경(색동일) */
.sec-background{

  background-color: rgba(6, 6, 6, 6);
  height : 100%;
}

/* 버튼 */
button {
  margin-top:30px;
  margin-bottom: 8rem;
}
 .loginbtn{
  margin-left: 10rem;
}

.signupbtn{
  margin-left: 1rem;
}

.loginstyle{
  color : white;
  text-decoration: none;
}

.loginstyle:hover{
  color: black;
}

.signupstyle{
  color : white;
  text-decoration: none;
}

.signupstyle:hover{
  color : black;
}
</style>