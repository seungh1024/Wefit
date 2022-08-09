<template>
<!-- <MainNav /> -->
<main class="main">
  <aside class="sidebar">
    <nav class="nav">
      <ul>
        <li class="active"><a href="#">프로필</a></li>
        <li><router-link :to="{name:'SignupView'}" class="signupstyle">임시로 로그인으로 이동</router-link></li>
        <li><router-link :to="{name:'SignupView'}" class="signupstyle">임시로 로그인으로 이동</router-link></li>
      </ul>
    </nav>
  </aside>

  <section class="profile_section">
    <div class="content">
      <div class="border-box">
      <div class="profile-box">
        <div class="title">
          <h3>회원 정보</h3>
        </div>
        <ul class="myInfo_area">
          <li>닉네임</li>
          <li>닉네임(암튼 들어가고)</li>
          <li><button>수정</button></li>
        </ul>
        <hr>
        <ul class="myInfo_area">
          <li>이메일</li>
          <li>Email0101@Email.com</li>
          <li></li>
        </ul>      
         <hr>  
        <ul class="myInfo_area">
          <li>핸드폰 번호</li>
          <li>010-0000-0000</li>
          <li></li>
        </ul>
         <hr>
        <ul class="myInfo_area">
          <li>MBTI</li>
          <li>CUTE</li>
          <li><button>수정</button></li>
        </ul>
        <hr>
        <ul class="myInfo_area">
          <li>성향</li>
          <li>아무튼 씐남 ㅋ</li>
          <li><button>수정</button></li>
        </ul>
        
      </div>
    </div>
    <div class="space_box"/>
    <div class="border-box">
      <div class="profile-box">
        <div class="title">
          <h3>추가정보</h3>
        </div>
        <ul class="myInfo_area">
          <li>필요하면</li>
          <li>그때 사용 ㄱ</li>
          <li><button>수정</button></li>
        </ul>
      </div>
    </div>
    <div>
      <button class="pass_change btn btn-outline-danger">비밀번호 변경</button>
    </div>
    </div>
  </section>
</main>
</template>

<script>
export default {

}
</script>

<style scoped>
/* 폰트 */
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

/* 공백div */
.space_box{
  height:50px;
}

/* 사이드바 기본 */
.sidebar {
  position: fixed;
  width: 20%;
  height: 100vh;
  font-size: 0.75em;
  background: white;
  box-shadow: 1px 1px 16px 0 rgb(0 0 0 / 16%);
}

/* 프로필 공간 */
.content{
  margin-top : 5rem;
  padding-inline: 5rem;
}

/* 제목 */
.title{
  margin-bottom: 2rem;
}

/* 비밀번호 변경 버튼 위치 조정 */
.pass_change{
  margin-top : 3rem;
  margin-bottom : 3rem;
}

/* 사이드바 내용물 위치 */
.nav {
  position: relative;
  margin: 0 15%;
  text-align: center;
  top: 30%;
  transform: translateY(-50%);
  font-weight: bold;
}

/* 사이드바 ul */
.nav ul {
  list-style: none;
}

/* 사이드바 ul il */
.nav ul li {
  position: relative;
  margin: 2em 0;
}

/* 사이드바 a */
.nav ul li a {
  line-height: 5em;
  text-transform: uppercase;
  text-decoration: none;
  letter-spacing: 0.4em;
  color: rgba(0, 0, 0, 0.2);
  display: block;
  transition: all ease-out 300ms;
}
/* active설정된 사이드바 */
.nav ul li.active a {
  color: black;
}

/* active아닐시 */
.nav ul li:not(.active)::after {
  opacity: 0.2;
}

/* active 아닌 버튼에 hover시 */
.nav ul li:not(.active):hover a {
  /* color: rgba(255, 255, 255, 0.75); */
  color : black
}

/* li 설정 */
.nav ul li::after {
  content: "";
  position: absolute;
  width: 100%;
  height: 0.2em;
  background: black;
  left: 0;
  bottom: 0;
  background-image: linear-gradient(to right, #175bee, #0f0e0f);
}

/* 프로필 박스 */
.profile_section {
  /* background-color: rgba(240,255,240,233); */
  position: relative;
  font-family: 'Noto Sans KR', sans-serif;
  width: 80%;
  float: right;
  height: 100vh;
}

/* 컨테이너(현재 안씀) */
.profile_section .container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

/* 프로필 a */
.profile_section a {
  position: relative;
}

/* 프로필 이미지 */
.profile_section a img {
  width: 48px;
  height: 48px;
}

/* p태그 */
.profile_section p {
  text-transform: uppercase;
  font-size: 1em;
  letter-spacing: 0.1em;
  color: black;
  font-weight: bold;
  margin-top: 20px;
}


/* 프로필 세션 ul */
.profile_section ul{
  position: relative;
  display: table;
  table-layout: fixed;
  width: 100%;
  padding: 12px 0;
}

/* 프로필 내용 */
.myInfo_area{
    position: relative;
    display: table;
    table-layout: fixed;
    width: 100%;
    padding: 12px 0;
}

/* 프로필 내용 정리 */
.myInfo_area li:nth-child(1) {
  width: 15%;
  text-align: left;
}

.myInfo_area li:nth-child(2) {
    width: 75%;
    text-align: left;
}
.myInfo_area li:nth-child(3) {
    width: 10%;
    text-align: left;
}

/* 프로필 li설정 */
.profile_section li{
  list-style: none;
  width: 68px;
  text-align: left;
  display: table-cell;
  vertical-align: middle;
  margin: 0;
  padding: 0;
  -webkit-text-size-adjust: none;
}

/* 보더 박스 */
.border-box{
  padding: 16px 17px 0;
  border-radius: 12px;
  box-shadow: 2px 2px 14px 0 rgb(0 164 73 / 8%);
  border: solid 1px rgba(3,213,128,.8);
  background-color: #fff;
  box-sizing: border-box;
}

/* 줄 설정 */
hr{
  color: gray;
  width: 95%;
}

</style>