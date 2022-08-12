<template>
   <div class="interestmodal">
    <div class="white-bg">
    <p>관심사를 선택해주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="술" > 술
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="게임">게임
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="넷플릭스">넷플릭스
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="영화감상" >영화감상
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="kpop"> kpop
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="웹툰"> 웹툰
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="여행"> 여행
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="언어교환"> 언어교환
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="드라이브"> 드라이브
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="독서"> 독서
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="외향적"> 외향적
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="내향적"> 내향적
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="감성적인"> 감성적인
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="유머러스"> 유머러스
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="집콕"> 집콕
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="다이어트"> 다이어트
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="전시회관람"> 전시회관람
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="맛집"> 맛집
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="커피"> 커피
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="스시"> 스시
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="와인"> 와인
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="헬스"> 헬스
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="테니스"> 테니스
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="필라테스"> 필라테스
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="골프"> 골프
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="서핑"> 서핑
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="건강"> 건강
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="주식"> 주식
    </td>
    </tr>
        <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="부동산"> 부동산
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="코인"> 코인
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="비건"> 비건
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="환경보호"> 환경보호
    </td>
    </tr> 
        <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="봉사활동"> 봉사활동
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="취준"> 취준
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="건강"> 건강
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="디자인"> 디자인
    </td>
    </tr> 
        <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="IT"> IT
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="스타트업"> 스타트업
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="공기업"> 공기업
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="인"> 인
    </td>
    </tr>  
    </table>
     <button id = "submitbtn" @click.prevent = "SelectInterest">결정하기</button>
   </div>
   </div>
</template>

<script>
export default{
    data() {
      return {
       checkedValues:[],
    }
    },
    methods: {
      SelectInterest(){
      const InterestList = this.checkedValues;
      this.$emit("SelectInterest",InterestList);
      }
    }
    //여기는 아직 틀만 잡고 상세내용은 미구현(페이지네이션 구현해야됨)
};
</script>

<style scoped>
.interestmodal {
  color : white;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.white-bg {
  border-radius: 50px;
  background: rgba(0, 0, 0, 0.8);
  height: 50vh;
  width : 700px;
}

table{
  height : 80%;
  width  : 80%;  
}
table{
    margin-left : 130px
}
p{
    font-size : 20px;
    font :bold;
    color:red;
    position : relative;
    text-align: center;
}
#submitbtn{
    text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: 0;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 100px;
    height: 50px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    background: #ef3e43;
    border: 0;
    margin-top: 30px;
    font-size: 17px;
}
</style>