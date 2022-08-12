<template>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Wefit</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">About us</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">랜덤 매칭</a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">방 만들기</a>
        </li>
      </ul>
        <form class="d-flex" role="search">
        <!-- <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"> -->
        <button class="btn btn-outline-success mx-3" @click.prevent="submit">Logout</button>
        <router-link :to="{name:'ProfileView'}" class="btn btn-outline-success"><v-btn class="mx-1" dark>Profile</v-btn></router-link>
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

</style>