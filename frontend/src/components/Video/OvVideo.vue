<template>
	<video :class="{ 'isCatchMind': catchMindStatus}" autoplay/>
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
.isCatchMind{
	width: 700px;
	height: 700px;
}
</style>
