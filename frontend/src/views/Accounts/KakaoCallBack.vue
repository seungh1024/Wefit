<template>
<div>
    test
</div>
</template>

<script>
import { getKakaoToken, getKakaoUserInfo, refreshToken } from "@/services/login.js"
export default {
    name: 'kakaoCallBackView',
    components: {
    },
    data() {
        return {
            code : '',
        }
    },
    mounted() {
       // let temp = new URL(window.location.href).searchParams.get('code')
        //console.log(temp);
        //getKakaoToken(temp);  
    },
    computed: {
    },
    methods: {

    }

}
</script>

<style>

</style>