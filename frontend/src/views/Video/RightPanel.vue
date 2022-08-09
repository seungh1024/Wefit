<template>
<div class="card">
  <!-- 처음 진입시 -->
  <div class="card-body" v-if="!startTalk && !gameStatus">
    <h5 class="card-title">대화카드</h5>
    <h6 class="card-subtitle mb-2 text-muted">대화카드</h6>
    <p class="card-text">대화카드를 통해 서로를 추측해보아요</p>
    <button href="#" class="card-link">시작하기</button>
  </div>
  <!-- 시작하기 버튼을 클릭한 후 -->
  <div class="card-body" v-if="startTalk && !gameStatus">
    <h5 class="card-title">대화카드 주제</h5>
    <p class="card-text">다음 주제를 통해 자신을 소개해 보아요</p>
    <p class="card-text">주제에 대한 설명 주저리주저리</p>
    <button href="#" class="card-link">대화 완료</button>
  </div>
  <!-- 게임 선택 버튼 -->
  <div class="card-body" v-if="!startTalk && gameStatus">
    <h5 class="card-title">게임을 골라주세요!</h5>
    <button @click="startGame1">
      <div v-if="!isReadyCatch">캐치마인드</div>
      <div v-else>준비완료</div>
    </button>
    <button @click="startGame2">ㅉ
      <div v-if="!isReadyCatch">옛날옛적에</div>
      <div v-else>준비완료</div>
    </button>
  </div>
  <!-- 캐치마인드 -->
  <!-- <CatchMind /> -->
</div>
</template>

<script>
// import CatchMind from './CatchMind.vue'
export default {
  name: 'RightPanel',
  components: {
    // CatchMind
  }
}
</script>

<style>

</style>