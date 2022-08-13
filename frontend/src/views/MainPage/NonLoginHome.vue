<template>
<div class="Back">
  <div class="main">
    <div class="background">
      <v-row>
          <div class="homebox">
          </div>
      </v-row>
      <div>
        <h1>WEFIT</h1>
        <button class="btn btn-outline-light loginbtn">
          <router-link :to="{name:'LoginView'}" class="loginstyle">Login</router-link>
        </button>
        <button class="btn btn-outline-light signupbtn">
          <router-link :to="{name:'SignupView'}" class="signupstyle">Signup</router-link>
        </button>
      </div>
      <div class="spacebox"></div>
    </div>
  </div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>
/* 배경색 */
.Back{
  background-color: rgba(6, 6, 6, 6);
}
/* 메인 크기(1200기준으로 남는 부분 검정 채우기) */
/* .main{
  width:1200px; 
  margin:0 auto; 
} */

/* 배경 사진 */
.background{
  height: 100%;
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
@media (min-width: 768px) {
.homebox{
  height: 10rem;
}
h1{
  color:white;
  font-size: 150px;
  margin-left: 7rem;
}
}
@media (min-width: 992px) {
.homebox{
  height: 15rem;
}
h1{
  color:white;
  font-size: 150px;
}
}
@media (min-width: 1200px) {
.homebox{
  height: 25rem;
}
h1{
  color:white;
  font-size: 150px;
}
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

/* 설명상자(사진포함) */
.whitebox{
  display: flex;
  background-color: aliceblue;
}

/* 설명상자(좌우대칭) */
.whitebox-right{
  display: flex;
  background-color: aliceblue;
  justify-content: space-between;
}

/* 부주제? */
.sub-mention{
  font-size: 50px;
  color: aliceblue;
  display: flex;
  justify-content: center;
}

/* 글자 가운데 정렬(margin이용) */
.div-center{
  position: relative ;
  margin-block: auto;
  margin-inline: auto;
  font-size : 50px;
}
</style>