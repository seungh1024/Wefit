<template>
  <div v-if="streamManager" class="parent-video">
    <ov-video
      :stream-manager="streamManager"
      v-bind:catchMindStatus="this.catchMindStatus"
    />
    <div class="child-name" v-if="!catchMindStatus">
      <p class="mt-1 mx-2">{{ clientData }}</p>
    </div>
  </div>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
    catchMindStatus: Boolean,
  },
  
  computed: {
    clientData() {
      const { clientData } = this.getConnectionData();
	  console.log(clientData);
      return clientData;
    },
  },

  methods: {
    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data.split("%")[0]);
    },
  },
};
</script>

<style scoped>
.parent-video {
  position: relative;
}
.child-name {
	border-radius: 10px;
  padding: none;
	margin: none;
  background-color: rgba(255, 255, 255, 0.8);
  font-family: 'Jua';
  font-size: 2vh;
  position: absolute;
  top: 18%;
  left: 8%;
	max-height: 30px;
}
</style>
