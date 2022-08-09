<template>
<div class="findpw">
		<form class="findpwform">
		<h2>비밀번호찾기</h2>
			<p>
				<div>
				<label for="emailInput" id = "emailtext">입력값1</label>
				</div>
				<input type="text" id="emailInput" name="userEmail" class="input_text" ref="emailInput" v-model.trim="userEmail" placeholder="입력값1을 입력하세요." />
			</p>
			<p>
				<div>
				<label for="passwordInput" id = "passwordtext">입력값2</label>
				</div>
				<input type="password" id="passwordInput" name="userPassword" class="input_text" ref="passwordInput" v-model.trim="userPassword" placeholder="입력값2를 입력하세요." />
			</p>
            <div>
                <button id = "findpwbtn"> 찾기</button>
            </div>
            </form>
</div>
</template>

<script>
export default {
  
}

</script>

<style scoped>
.findpw{
    height: 100vh;
    background-image: url("@/assets/loginpage.jpg");
    background-repeat : no-repeat;
    background-size : cover;
 }
h2{
    color : red;
 }
.findpwform{
  text-align:center;
  position : relative;
  top :25%;
 }
 #emailtext{
  color: white;
 }
 #passwordtext{
  color: white;
 }
 input{
  border-radius: 8px;
  width : 24%;
  height: 40px;
  margin-left: 10px;
 }
 #findpwbtn{
text-size-adjust: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    appearance: none;
    margin: auto;
    padding: 0;
    outline: none;
    user-select: none;
    font-family: -apple-system, BlinkMacSystemFont, Roboto, "Droid Sans", "Helvetica Neue", "Apple SD Gothic Neo", "sans-serif", sans-serif;
    display: block;
    width: 23%;
    height: 46px;
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