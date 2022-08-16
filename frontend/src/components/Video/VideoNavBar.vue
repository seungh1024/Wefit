<template>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/home">
      <img class="navbar-img" src="@/assets/images/wefitlogo.jpg" alt="#">
    </a>
  </div>
</nav>
</template>

<script>
export default {
  name: 'VideoNavBar',
}

</script>

<style>
nav{
  height:20px;
}
.navbar-img {
  margin-bottom: 7px;
  max-height: 20px;
  max-width: 50px;
}
</style>
