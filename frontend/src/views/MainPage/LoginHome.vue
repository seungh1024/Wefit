<template>
<MainNavbar />
<main>
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <!-- <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button> -->
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="@/assets/MainPage/loginhome.png" class="image-1 d-block w-100" alt="loginhome.png">
      <div class="container">
        <div class="carousel-caption text-end">
          <h1>랜덤 매칭</h1>
          <p>당신의 취향에 맞는 친구를 찾아 떠나보아요</p>
          <p><a class="btn btn-lg btn-outline-light" href="/randomvideo">매칭하러 가기</a></p>
        </div>
      </div>
    </div>
    <div class="carousel-item">
      <img src="@/assets/MainPage/loginhome.png" class="d-block w-100" alt="nonLoginPage.jpg">
      <div class="container">
        <div class="carousel-caption text-end">
          <h1>방 만들기</h1>
          <p>방을 직접 만들어서 친구들과 소통해요</p>
          <p><a class="btn btn-lg btn-outline-light" href="#">방 만들기</a></p>
        </div>
      </div>
    </div>
    <!-- <div class="carousel-item">
      <img src="@/assets/MainPage/nonLoginPage.jpg" class="d-block w-100" alt="nonLoginPage.jpg">
      <div class="container">
        <div class="carousel-caption text-end">
          <h1>커뮤니티</h1>
          <p>커뮤니티에서 소통을 해봐요</p>
          <p><a class="btn btn-lg btn-outline-light" href="#">커뮤니티 둘러보기</a></p>
        </div>
      </div>
    </div> -->
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

  <!-- Marketing messaging and featurettes
  ================================================== -->
  <!-- Wrap the rest of the page in another container to center all the content. -->

  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <br>
    <div class="row my-3 py-3">
      <div class="text-center">
        <h1 class="title_font">너랑 나랑 위핏</h1>
        <h4 class="title_font pt-2">당신에게 딱 맞는 친구 위핏</h4>
        <br>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->


    <!-- START THE FEATURETTES -->

    <hr class="featurette-divider">

    <div id="tester" class="row featurette">
      <div class="col-md-7">
        <br><br>
        <h2 class="featurette-heading fw-normal lh-1">가랑비에 옷 젖는 줄 모르는 인연 찾기</h2>
         <!-- <span class="text-muted">It’ll blow your mind.</span> -->
         <br>
        <p class="lead my-3"> 1초 만에 프로필을 스와이프 하는 쇼핑 같은 인연을 즐기신다면, 위핏은 맞지 않아요.</p>
        <p class="lead my-3"> 인연은 첫눈에 결정되는 것이 아니라, '가랑비에 옷 젖는 줄 모른다’라는 속담처럼 어느새 상대방에게 스며드는 것이죠.</p>
        <p class="lead my-3"></p>
      </div>
      <div class="col-md-5">
        <br>
        <img class="bd-placeholder-img 
        bd-placeholder-img-lg 
        featurette-image 
        img-fluid mx-auto" 
        width="500" height="500" 
        src="@/assets/MainPage/wefitmaincat.jpg" 
        role="img" aria-label="Placeholder: 500x500" 
        preserveAspectRatio="xMidYMid slice" 
        focusable="false">
        <div>
          <br><br>
        </div>
      </div>
    </div>
    <hr class="featurette-divider">
    <div class="row featurette" id="tester">
      <div class="col-md-7 order-md-2">
      <br><br>
        <h2 class="featurette-heading fw-normal lh-1">대화 먼저, 얼굴 공개는 천천히</h2>
        <br>
        <p class="lead"> 위핏에서는 대화할수록 프로필이 점점 선명해져요.</p>
        <p class="lead"> 안전하고, 프라이버시에 민감한 사람을 위해 만들었어요. </p>
        <p class="lead"> </p>
      </div>
      <div class="col-md-5 order-md-1">
        <br><br>
        <img class="bd-placeholder-img 
        bd-placeholder-img-lg 
        featurette-image 
        img-fluid mx-auto" 
        width="500" height="500" 
        src="@/assets/MainPage/loginhome2.jpg" 
        role="img" aria-label="Placeholder: 500x500" 
        preserveAspectRatio="xMidYMid slice" 
        focusable="false">
      <div>
        <br><br>
      </div>
      </div>
    </div>

    <!-- <div class="row featurette">
      <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
        <p class="lead">And yes, this is the last block of representative placeholder content. Again, not really intended to be actually read, simply here to give you a better view of what this would look like with some actual content. Your content.</p>
      </div>
      <div class="col-md-5">
        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"/><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>

      </div>
    </div>

    <hr class="featurette-divider"> -->

    <!-- /END THE FEATURETTES -->

  </div><!-- /.container -->


  <!-- FOOTER -->
</main>
<MainFooter />
</template>

<script>
import MainNavbar from '@/components/MainPage/MainNavbar.vue'
import MainFooter from '@/components/MainPage/MainFooter.vue'

export default {
  components: { MainNavbar, MainFooter },
}
</script>

<style>
@font-face {
    font-family: 'RIDIBatang';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/RIDIBatang.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.title_font{
  font-family: 'RIDIBatang';
}
#tester{
  font-family: 'RIDIBatang';
}
h1{
    -ms-user-select: none; 
  -moz-user-select: -moz-none;
  -khtml-user-select: none;
  -webkit-user-select: none;
  user-select: none;
}
  .bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  }

  @media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
  }

  .b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, .1);
  border: solid rgba(0, 0, 0, .15);
  border-width: 1px 0;
  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
  }

  .b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
  }

  .bi {
  vertical-align: -.125em;
  fill: currentColor;
  }

  .nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
  }

  .nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
  }
    </style>