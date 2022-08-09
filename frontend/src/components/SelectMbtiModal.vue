<template>
   <div class="mbtimodal">
   <div class="white-bg">
    <p>MBTI를 선택해 주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues'  value = "ISTJ" @change = "clickFunc"> ISTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ISTP"  @change = "clickFunc"> ISTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ISFJ"  @change = "clickFunc"> ISFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="ISFP"  @change = "clickFunc"> ISFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "INTJ" @change = "clickFunc"> INTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "INTP" @change = "clickFunc"> INTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "INFJ" @change = "clickFunc"> INFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "INFP" @change = "clickFunc"> INFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ESTJ" @change = "clickFunc"> ESTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ESTP" @change = "clickFunc"> ESTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ESHJ" @change = "clickFunc"> ESHJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ESFP" @change = "clickFunc"> ESFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ENTJ" @change = "clickFunc"> ENTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ENTP" @change = "clickFunc"> ENTP 
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "ENFJ" @change = "clickFunc"> ENFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="ENFP" @change = "clickFunc"> ENFP
    </td>
    </tr>
    </table>
    <button id = "submitbtn" @click.prevent = "selectmbti">결정하기</button>
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
    computed: {
      
    },
    methods: {
     // 체크박스 1개만 선택하게 하는 함수(value값 비교)
     clickFunc(event) {
          for(let i=0; i<this.checkedValues.length;i++){
            if(this.checkedValues[i] !== event.target.value){
            this.checkedValues.splice(i,1);
            }
          }
        },
    selectmbti(){
      let mbti = this.checkedValues[0];
      this.$emit("selectmbti",mbti);
    }    
  }
};
</script>

<style scoped>
.mbtimodal {
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