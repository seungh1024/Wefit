<template>
    <div class = "signupdetail" >
    <div id = "inputdetail">
    <h2>We Fit</h2>
    <table>
    <hr>
    <tr>
    <td>이름</td> 
    <td> <span @click ="nameOpen =true" v-if ="nameOpen ===false"> 입력</span></td>
    <td><input v-if ="nameOpen ===true" v-model ="userName"></td>
    </tr>
    <hr>
    <tr> <td> 이메일 </td> 
    <td><span > {{ this.$store.getters.getUserEmail}}</span> </td>  </tr>
    <hr> 
    <tr> 
    <td> 성별 </td>  
    <td> <input type = "radio" name="gender" value = "man" v-model = "userGender">남자 <input type = "radio" name="gender" value ="woman" v-model = "userGender">여자 </td>
    </tr>
    <hr>   
    <tr>
    <td> 닉네임</td>  
    <td><span @click = "nicknameOpen =true" v-if ="nicknameOpen ===false">입력</span></td>
    <td><input v-if ="nicknameOpen ===true" v-model ="userNickName"></td>
    </tr>
    <hr>
    <tr>   
    <td>지역</td> 
    <td> <span @click = "userFieldOpen =true" v-if ="userFieldOpen ===false"> 입력</span> </td>
    <td><input v-if ="userFieldOpen ===true" v-model ="userField"></td>
    </tr>
    <hr>
    <tr>   
    <td>MBTI</td>  
    <td><span @click = "mbtiModalOpen = true"> 입력 </span> </td> 
     </tr>
    <hr>
    <tr>
    <td> 관심사 </td>  
    <td><span @click = "interestModalOpen = true">입력 </span> </td> 
    </tr>
    <hr>
    <button id = "submitbtn" @click.prevent = "submit">가입하기</button>
    </table>
    </div>  
    <div>
        <img src = "@/assets/img.png" id = "img">
    </div>
    </div>

    <div>
     <SelectMbtiModal v-if="mbtiModalOpen ==true" v-model ="userMbti" v-click-outside="mbtimodalclose" v-on:selectmbti="selectmbti" >
     </SelectMbtiModal>
    </div>
    <div>
     <SelectInterestModal v-if="interestModalOpen == true"  v-click-outside="interestmodalclose">
     </SelectInterestModal>
    </div>
</template>

<script>
import { mapGetters } from 'vuex'
import SelectMbtiModal from '@/components/SelectMbtiModal.vue';
import SelectInterestModal from '@/components/SelectInterestModal.vue';
import vClickOutside from 'v-click-outside';
import store from '@/store';
export default {
    directives: {
    clickOutside: vClickOutside.directive
    },
    name: 'SignUpdetail',
    components: {
    SelectMbtiModal,
    SelectInterestModal
    },
    data() {
      return {
        userEmail : store.state.userEmail,
        userMBTI   : '',
        userGender : '', 
        userName   : '',
        userField  : '',
        userPhone  : '',
        userNickName : '',
        userInterestList : [],
        mbtiModalOpen : false,
        interestModalOpen: false,
        nameOpen: false,
        nicknameOpen: false,
        userFieldOpen: false,
    }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
     submit(){
        const userDetailData = {
        "userEmail" : this.$store.getters.getUserEmail,
        "userMBTI": this.userMbti,
        "userGender": this.userGender,
        "userNickname" : this.userNickName,
        "userName": this.userName,
        "userField" : this.userField,
        "userPhone": this.userPhone,   
      }
       console.log(userDetailData);
       this.$store.dispatch('signupdetail', userDetailData);
       //잘되는듯?
     },
    mbtimodalclose(event){
        this.mbtiModalOpen = false;
        //왜안될까...
    },
    interestmodalclose(event){
        this.interestModalOpen = false;
        //왜안될까...
    },
    selectmbti(value){
        this.mbtiModalOpen = false;
        this.userMbti =value;
        console.log(this.userMbti);
        //일단 이건 잘돌아감 mbti 모달에서 데이터 잘 받음
    },
    SelectInterest(value){
        this.interestModalOpen = false;
        this.userInterestList = value; 
        //이부분은 mbti 모달에서 쓴 그대로 쓰면되는데 아직 페이지네이션 구현 덜함...
        //interest모달만 조금 건드리면될듯  
    },
    },


}
</script>

<style scoped>
.signupdetail{
    width : 100vw;
    height : 100vh;
    display: flex;
    background-color: rgba(22, 22, 22, 22);
}
table{
   padding : 0px;
   border-spacing: 0px;
   border-collapse: separate;
}
td{
    font :bold;
    color : #FFFA85;
}
#inputdetail{
    padding-top : 6%;
    padding-left : 20%;
    font-weight: bold;
    color : white;
}
#img{
  padding-top : 100px;
  padding-left : 250px; 
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
span{
    cursor:pointer;
}

</style>