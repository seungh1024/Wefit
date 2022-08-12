<template>
    <div class="signupdetail">
        <div class="inner_box">  
            <div class="block-div">
            <div class="signup_table">
            <div class="inputdetail">
                <h2>WEFIT</h2>
            </div>
            <table>
            <tr>
                <td>이름</td> 
                <td v-if ="nameOpen ===false"><span @click = "nameOpen =true" >입력</span></td>
                <td v-if ="nameOpen ===true"><input class="input_long" type="text" v-model ="userName"  maxlength='10' placeholder="10자 이내로 입력해주세요."></td>
            </tr>
            <tr> 
                <td> 이메일 </td> 
                <td><span > {{ this.$store.getters.getUserEmail}}</span> </td>
            </tr>
            <tr> 
                <td> 성별 </td>  
                <td class="select"> 
                    <input type = "radio" name="gender" value = "man" id="select" v-model = "userGender"><label for="select">남자 </label>
                    <input type = "radio" name="gender" value ="woman" id="select2" v-model = "userGender"><label for="select2">여자 </label>
                </td>
            </tr>
            <tr>
                <td> 닉네임</td>  
                <td v-if ="nicknameOpen === false"><span @click= "nicknameOpen = true" >입력</span></td>
                <td v-if ="nicknameOpen === true"><input class="input_long" type="text" v-model ="userNickName"  maxlength='8' placeholder="8자 이내로 입력해주세요."></td>
            </tr>
            <tr>   
                <td>지역</td>
                <td v-if ="userFieldOpen === false"> <span @click = "userFieldOpen = true" > 입력</span> </td>
                <td v-if ="userFieldOpen === true"><input  class="input_long" type="text" v-model ="userField"  maxlength='10' placeholder="시만 입력해주세요"></td>
            </tr>
            <tr>   
                <td>MBTI</td>  
                <td v-if ="userMBTI === ''"><span @click = "mbtiModalOpen = true"> 입력 </span> </td>
                <td v-if ="userMBTI !== ''">{{userMBTI}}</td> 
            </tr>
            <tr>
                <td>전화번호</td>  
                <td v-if="phoneOpen === false"><span @click = "phoneOpen = true"> 입력 </span> </td>
                <td v-if ="phoneOpen === true"><input class="input_long" type="text" v-model ="userPhone" placeholder="'-'를 제외하고 입력해주세요."  maxlength='11'></td>
            </tr>
            <tr>
                <td>관심사</td>  
                <td v-if ="userInterestList == ''"><span @click = "interestModalOpen = true">입력 </span> </td>
                <td v-if ="userInterestList != ''">{{userInterestList}}</td> 
            </tr>
            </table>
            <button id = "submitbtn" @click.prevent = "submit">가입하기</button>
            </div>
                <div class="img_2">
                </div>
            </div>
            <div class="rewrite_mbti" v-if ="userMBTI !== ''">
                <button @click = "mbtiModalOpen = true" class="re_mbti">재입력</button>
            </div>
            <div class="space_box" v-if="userInterestList != '' && userMBTI === ''">qwcqwc</div>
            <div class="rewrite_interest" v-if ="userInterestList != ''">
                <button @click = "interestModalOpen = true" class="re_interest">재입력</button>
            </div>
        </div>
    </div>

    <div>
     <SelectMbtiModal v-if="mbtiModalOpen ==true" v-model ="userMbti" v-click-outside="mbtimodalclose" v-on:selectmbti="selectmbti" >
     </SelectMbtiModal>
    </div>
    <div>
        <SelectInterestModal v-if="interestModalOpen == true" v-model = "userInterestList"  v-click-outside="interestmodalclose" v-on:SelectInterest = "SelectInterest">
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
        phoneOpen:false,
    }
    },
    computed: {
        ...mapGetters([''])
    },
    methods: {
     submit(){
        const userDetailData = {
        "userEmail" : this.$store.getters.getUserEmail,
        "userMBTI": this.userMBTI,
        "userGender": this.userGender,
        "userNickname" : this.userNickName,
        "userName": this.userName,
        "userField" : this.userField,
        "userPhone": this.userPhone,   
      }
       console.log(userDetailData);
       const userInterestList = this.userInterestList
       this.$store.dispatch('signupdetail', userDetailData, userInterestList);
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
        this.userMBTI = value;
        console.log(this.userMBTI);
        //일단 이건 잘돌아감 mbti 모달에서 데이터 잘 받음
    },
    SelectInterest(value){
        this.interestModalOpen = false;
        this.userInterestList = value;
        console.log(this.userInterestList) 
    },
    },


}
</script>

<style scoped>

.signupdetail{
    display: flex;
    background-color: rgba(22, 22, 22, 22);
    min-height: 100vh;
    min-width : 1000px;
    justify-content: center;
}
table{
    width: 450px;
}
tr{

    width:100px;
    height: 70px;
    border-bottom: #777 solid 1px;
    border-top:#777 solid 1px ;
    font :bold;
    color : rgb(211, 222, 120);
}

.inputdetail{
    display: flex;
    justify-content: center;
    padding-top : 100px;
    font-weight: bold;
    color : white;
}
.inner_box{
    width: 1400px;
    height: 100vh;
    border-left: #ffffff solid 1px;
    border-right: #ffffff solid 1px ;
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
    width: 450px;
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
.select {
    padding: 0px;
    
}
.select input[type=radio]{
    display: none;
}
.select input[type=radio]+label{
    display: inline-block;
    cursor: pointer;
    height: 24px;
    width: 90px;
    border: 1px solid #333;
    line-height: 24px;
    text-align: center;
    font-weight:bold;
    font-size:13px;
}
.select input[type=radio]+label{
    background-color: #fff;
    color: #777;
}
.select input[type=radio]:checked+label{
    background-color: #333;
    color: #fff;
}

.img_2{
  height: 586px;
  width: 439px;
  overflow: hidden;
  margin:0;
  margin-top:100px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  background-image:linear-gradient(
    rgba(0, 0, 0, 0),
        rgba(255, 255, 255, 0)
      ), url("@/assets/img.png");
}

.signup_table{
    float:left;
}
.block-div{
    display: flex;
    justify-content: space-around;
}
/* .input_long{
    width:90%;
} */
input[type=text] {
  width: 90%;
  padding: 12px 20px;
  margin: 8px 0;
  height: 8px;
  box-sizing: border-box;
  border: none;
  border-bottom: 2px solid red;
  background-color: rgba(22, 22, 22, 22);
  color:white;
}
.rewrite_mbti{
    position: relative;
    top : -270px;
    left: 500px;
}

.rewrite_interest{
    position: relative;
    bottom : 160px;
    left: 500px;
}

.re_interest{
    color:rgb(211, 222, 120);
    background-color:transparent;
    border : none;
}
.re_mbti{
    color:rgb(211, 222, 120);
    background-color:transparent;
    border : none;
}
.space_box{
    height: 30px;
}
</style>