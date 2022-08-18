<template>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/home">
      <img class="navbar-img" src="@/assets/images/wefitlogo.jpg" alt="#">
    </a>
   <button @click="leaveSession" :class="{ 'btn btn-danger btn-moving': (!talkStatus && !gameStatus) || talkStatus, 'btn btn-danger btn-moving2': !talkStatus && gameStatus }">방 나가기</button> 
  </div>
</nav>
</template>

<script>
export default {
  name: 'VideoNavBar',
  methods: {
    leaveSession(){
    this.$emit("leaveSession");
  }
 }
}

</script>

<style>
nav{
  height:50px;
}
.navbar-img {
  margin-bottom: 7px;
  max-height: 20px;
  max-width: 50px;
}
</style>
