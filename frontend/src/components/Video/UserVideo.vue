<template>
<div v-if="streamManager" class="parent-video">
	<ov-video :stream-manager="streamManager"/>
	<div class="child-name"><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {			
			const { connection } = this.streamManager.stream;	
			return JSON.parse(connection.data.split("%")[0]);
		},
	},
};
</script>

<style scoped>
.parent-video {
	position:relative;
}
.child-name {
	color: white;
	position:absolute; left: 45%; top: 90%; /* 위치 수정 필요 */
}
</style>