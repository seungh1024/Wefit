<template>
	<video class="webcam" :class="{ 'isCatchMind': catchMindStatus, 'isNotCatchMind': !catchMindStatus}" autoplay/>
</template>

<script>
export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
		catchMindStatus: Boolean
	},

	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
};
</script>
<style scoped>
/* 캐치마인드인 경우 */
.isCatchMind{
	width: 180px;
	height: 150px;
}
/* 기본옵션 */
.isNotCatchMind{  
	width: 500px;
	height: 650px;
}

.webcam {
	border-radius: 40px;
}

</style>
