<template>
   <div class="interestmodal">
    <div class="white-bg">
    <p>관심사를 선택해주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
    </td>
    <td>
    <input type = "checkbox" class = "item"> 내용1
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
      let InterestList = this.checkedValues;
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
  background-color: #272724;
  height: 50vh;
  width : 50vw;
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
    width: 25vw;
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