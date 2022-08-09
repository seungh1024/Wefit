
import Axios from 'axios';

const kakaoHeader = {
    'Authorization': 'b7d5f791d9c8c1e82b90b2e2987c867b',
    'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
    };
const getKakaoToken = async (code) => {
    console.log('loginWithKakao');
    try {
        const data = {
            grant_type: 'authorization_code',
            client_id: '3a845c92bb113831d32d35810b1969dd',
            redirect_uri: 'http://localhost:8080/kakaocallback',
            code: code,
        };
        const queryString = Object.keys(data)
            .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
            .join('&');
        const result = await Axios.post('https://kauth.kakao.com/oauth/token', queryString, { headers: kakaoHeader });
        console.log('카카오 토큰', result);
        return result;
    } catch (e) {
        console.log(e);
        return e;
    }
};
const refreshToken = async () => {
    try {
        const { result } = (await axios.get('/refreshToken')).data;
        VueCookies.set('access-token', result.access_token);
        console.log('Refresh API 성공', result);
        return result;
    } catch (e) {
        console.log(e);
    }
}

const getKakaoUserInfo = async () => {
    let data = '';
    await window.Kakao.API.request({
        url: "/v2/user/me",
        success: function (response) {
            data = response;
        },
        fail: function (error) {
            console.log(error);
        },
    });
    console.log('카카오 계정 정보', data);
    return data;
    }

export {
    getKakaoToken,
    refreshToken,
    getKakaoUserInfo
};
