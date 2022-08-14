<template>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/home">
    <img class="navbar-img" src="@/assets/images/wefitlogo.jpg" alt="#">
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/randomvideo">매칭</a>
        </li>
      </ul>
        <form class="d-flex" role="search">
        <!-- <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"> -->
        <button class="btn btn-outline-success mx-3" @click.prevent="submit">로그아웃</button>
        <router-link :to="{name:'ProfileView'}" class="btn btn-outline-success"><v-btn class="mx-1" dark>프로필</v-btn></router-link>
      </form>
    </div>
  </div>
</nav>
</template>

<script>
export default {
  name: 'MainNavbar',
  methods: {
    submit(){
      this.$store.dispatch('logout')
    }
  },
}

</script>

<style>
.navbar-img {
  margin-bottom: 7px;
  max-height: 20px;
  max-width: 50px;
}
</style>