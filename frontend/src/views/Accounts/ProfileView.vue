<template>

<MainNav />
<main class="main">
  <aside class="sidebar">
    <nav class="nav">
      <ul class="ul-top">
        <li class="active"><a href="#">프로필</a></li>
        <li><router-link :to="`#`" class="signupstyle">친구목록</router-link></li>
        <li><router-link :to="`#`" class="signupstyle">유저설정</router-link></li>
      </ul>
    </nav>
  </aside>

  <div class="profile_section">
    <div class="content">
      <div class="border-box">
      <div class="profile-box">
        <div class="title">
          <h3>회원 정보</h3>
        </div>
        <ul class="myInfo_area">
          <li>닉네임</li>
          <li v-if ="nicknameOpen !=true">{{userNickname}}</li>
          <li v-if ="nicknameOpen ===true"> <input type="text" v-model ="userNickname"></li>
          <li v-if ="nicknameOpen !=true"><button @click.prevent = "nicknameOpen = true" class="update_btn"> 수정하기 </button></li>
          <li v-if ="nicknameOpen ===true"><button @click.prevent = "nicknameOpen = false" class="update_btn"> 수정하기 </button></li>
        </ul>
        <hr>
        <ul class="myInfo_area">
          <li>이메일</li>
          <li>{{this.$store.getters.getUserEmail}}</li>
          <li></li>
        </ul>      
         <hr>  
        <ul class="myInfo_area">
          <li>핸드폰 번호</li>
          <li v-if="phoneOpen != true"><span>{{userPhone}}</span></li>
          <li v-if="phoneOpen === true"><input  type="text" v-model ="userPhone" ></li>
          <li v-if="phoneOpen != true"><button @click = "phoneOpen = true" class="update_btn"> 수정하기 </button></li>
          <li v-if="phoneOpen === true"><button @click = "phoneOpen = false" class="update_btn"> 수정하기 </button></li>
        </ul>
          <hr>
          <ul class="myInfo_area">
          <li>지역</li>
          <li v-if="userFieldOpen != true"><span>{{userField}}</span></li>
          <li v-if="userFieldOpen === true"><input  v-if="userFieldOpen === true"  type="text" v-model ="userField" ></li>
          <li v-if="userFieldOpen != true"> <button @click ="userFieldOpen = true" class="update_btn"> 수정하기 </button></li>
          <li v-if="userFieldOpen === true"> <button @click ="userFieldOpen = false" class="update_btn"> 수정하기 </button></li>
        </ul>
         <hr>
        <ul class="myInfo_area">
          <li>MBTI</li>
          <li>{{userMBTI}}</li>
          <li><button @click = "mbtiModalOpen =true" class="update_btn"> 수정하기 </button></li>
        </ul>
        <hr>
        <ul class="myInfo_area">
          <li>성향</li>
          <li>{{userInterestList}}</li>
          <li><button @click = "interestModalOpen =true" class="update_btn"> 수정하기 </button></li>
        </ul>
        <ul>
          <li><button @click.prevent = "submit" class="btn btn-success" >수정</button></li>
        </ul>
      </div>
    </div>
    </div>
  </div>
</main>

        <SelectMbtiModal v-if="mbtiModalOpen ===true" v-model ="userMbti"  v-on:selectmbti = "selectmbti" />
        <SelectInterestModal v-if="interestModalOpen === true" v-model = "userInterestList"   v-on:SelectInterest = "SelectInterest" />

</template>

<script>
import { mapGetters, } from 'vuex'
import MainNav from '@/components/MainPage/MainNavbar.vue'
import SelectMbtiModal from '@/components/SelectMbtiModal.vue';
import SelectInterestModal from '@/components/SelectInterestModal.vue';
export default {
  components: {
    SelectMbtiModal,
    SelectInterestModal,
    MainNav
  },
  data(){
  return{
        userEmail :  '',
        userMBTI   : this.$store.getters.getUser.userMbti,
        userGender : this.$store.getters.getUser.userGender, 
        userName   : this.$store.getters.getUser.userName,
        userField  : this.$store.getters.getUser.userField,
        userPhone  : this.$store.getters.getUser.userPhone,
        userNickname : this.$store.getters.getUser.userNickname,
        userInterestList : [],
        mbtiModalOpen : false,
        interestModalOpen: false,
        nameOpen: false,
        nicknameOpen: false,
        userFieldOpen: false,
        phoneOpen:false,
        genderOpen:false,
  }
  },
  computed: {
    ...mapGetters(['getUser']),
  },
  methods:{
   submit(){
     const userDetailData = {
        "userMbti": this.userMBTI,
        "userGender": this.userGender,
        "userNickname" : this.userNickname,
        "userName": this.userName,
        "userField" : this.userField,
        "userPhone": this.userPhone,   
      }
      
      this.$store.dispatch('editProfile', userDetailData)
      this.$store.dispatch('setUserInfo', userDetailData)      
  },
   selectmbti(value){
        this.mbtiModalOpen = false;
        this.userMBTI = value;
        console.log(this.userMBTI);
        //일단 이건 잘돌아감 mbti 모달에서 데이터 잘 받음
    },
    SelectInterest(value){
        this.interestModalOpen = false;
        this.userInterestList = value;
        console.log(this.userInterestList) 
    },
}
}
</script>

<style scoped>
/* 폰트 */

main{
  min-width: 950px;
}
/* 폰트 */
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);

/* 공백div */
.space_box{
  height:50px;
}

/* 사이드바 기본 */
.sidebar {
  float: left;
  width: 20%;
  height: 90vh;
  font-size: 0.75em;
  background: white;
  box-shadow: 1px 1px 16px 0 rgb(0 0 0 / 16%);
  min-width: 200px;
}

/* 프로필 공간 */
.content{
  margin-top : 1rem;
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
.ul-top{
  margin-top:0px;
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
  background-image: linear-gradient(to right, #2bb23f, #00ffb3);
}

/* 프로필 박스 */
.profile_section {
  /* background-color: rgba(240,255,240,233); */
  position: relative;
  font-family: 'Noto Sans KR', sans-serif;
  width: 75%;
  float: right;
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
    width: 70%;
    text-align: left;
}
.myInfo_area li:nth-child(3) {
    width: 15%;
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
.update_btn{
  min-width: 75px;
  border-radius: 8px;
  border: green 2px solid;
  color : green;
  background-color: white;
}

</style>


