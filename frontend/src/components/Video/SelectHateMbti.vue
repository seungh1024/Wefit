<template>
   <div class="mbti">
   <div class="white-bg">
    <br>
    <p>싫어하는 MBTI를 선택해 주세요</p>
    <table>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues'  value = "istj" @change = "clickFunc"> ISTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "istp"  @change = "clickFunc"> ISTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "isfj"  @change = "clickFunc"> ISFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="isfp"  @change = "clickFunc"> ISFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "intj" @change = "clickFunc"> INTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "intp" @change = "clickFunc"> INTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "infj" @change = "clickFunc"> INFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "infp" @change = "clickFunc"> INFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "estj" @change = "clickFunc"> ESTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "estp" @change = "clickFunc"> ESTP
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "esfj" @change = "clickFunc"> ESFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "esfp" @change = "clickFunc"> ESFP
    </td>
    </tr>
    <tr>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "entj" @change = "clickFunc"> ENTJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "entp" @change = "clickFunc"> ENTP 
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value = "enfj" @change = "clickFunc"> ENFJ
    </td>
    <td>
    <input type = "checkbox" class = "item" v-model = 'checkedValues' value ="enfp" @change = "clickFunc"> ENFP
    </td>
    </tr>
    </table>
    <p class="text-center">
        <button id = "submitbtn" @click.prevent = "selectmbti">결정하기</button>
    </p>
   </div>
   </div>
</template>

<script>
import axios from 'axios'
import { mapActions } from 'vuex';
export default{
  data() {
      return {
       checkedValues:[],
       userEmail: ''
    }
    },
    created() {
      const payload = JSON.parse(window.localStorage.vuex)
      this.getUserInfo(payload.accounts.userEmail)
      console.log(this.$store.state)
      this.userEmail = this.$store.state.accounts.userEmail
      console.log(this.userEmail)
    },
    methods: {
    ...mapActions(['getUserInfo']),

    selectmbti(){
      const mbti = this.checkedValues;
      this.$emit("selectmbti",mbti);
      console.log(mbti)
      let formData = []
      for (var i = 0; i < mbti.length; i++) {
        formData.push(
         {
            'mbtiName':mbti[i]
          })
          
      }
      console.log(formData)
      axios.post(`http://i7b206.p.ssafy.io/api/v1/mbti/${this.userEmail}`, formData)
      .then( res => {
        console.log(res)
        })
      .catch(err => {
        console.error(err)
      })
    }    
  }
};
</script>

<style scoped>
.mbti {
  color : #343434;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.white-bg {
  background-color: #F1F4F3;
  height: 65vh;
  width : 60vw;
  border-radius: 50px;
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
    color:#97CDBD;
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
    /* display: block; */
    width: 25vw;
    height: 50px;
    padding-bottom: 1px;
    border-radius: 10px;
    cursor: pointer;
    color: #fff;
    font-weight: bold;
    background: #97CDBD;
    border: 0;
    margin-top: 30px;
    font-size: 17px;
}
</style>