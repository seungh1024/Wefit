import axios from 'axios'
import router from '@/router'
import drf from '@/api/drf'

export default {
  state: {
    accessToken: '',
    refreshToken: '',
    currentUser: {},
    userEmail : '',
    user :{
      userMbti   : '',
      userGender : '', 
      userName   : '',
      userField  : '',
      userPhone  : '',
      userNickName : '',
      userInterestList : [],
    }
  },
  getters: {
    isLoggedIn: state => !!state.token,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
    getUserEmail: state => state.userEmail,
  },
  mutations: {
  Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.accessToken = token,
    SET_REFRESHTOKEN: (state, refreshToken) => state.refreshToken = refreshToken,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_USEREMAIL: (state, userEmail) => state.userEmail = userEmail
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
    },
    saveRefreshToken({ commit }, refreshToken){
      commit('SET_REFRESHTOKEN', refreshToken)
      localStorage.setItem('token', refreshToken)
    },
    removeToken({ commit }) {
      console.log('good')
      commit('SET_TOKEN', '')
      localStorage.setItem('token', '')
    }, 
    login({ commit, dispatch }, userData) {
      axios({
            url: drf.accounts.login(),
            method: 'post',
            data: userData
          })
            .then(res => {
              dispatch('saveToken', res.data.token)
              dispatch('saveToken', res.data.refreshToken)
              //dispatch('fetchCurrentUser')
              router.push({ name: 'LoginHome' })
            })
            .catch(err => {
              console.log(err)
              console.error(err)
              commit('SET_AUTH_ERROR', err)
            })
    },
    socialLogin({ } , socialLoginData){
      axios({
        url: drf.accounts.socialLogin(),
        method: 'post',
        data: socialLoginData
      })
        .then(res => {
          const token = res.data.token        
          dispatch('saveToken', token)
          dispatch('fetchCurrentUser')
          router.push({ name: 'HomeView' })
        })
        .catch(err => {
          console.log(err)
          console.error(err)
          commit('SET_AUTH_ERROR', err)
        })
    },
    signup({ commit }, userData) {
      axios({
        url: drf.accounts.signup(),
        method: 'post',
        data: userData
      })
        .then(  res =>
          console.log(res),
          commit('SET_USEREMAIL',userData.userEmail),
          router.push({name : 'signupdetail', params:{userEmail: userData.userEmail}})
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    signupdetail({ commit }, userDetailData) {
      axios({
        url: drf.accounts.userInfo()+userDetailData.userEmail,
        method: 'post',
        data: userDetailData,
      })
        .then( res=> {
          console.log(res)
          router.push({ name: 'LoginView'})
          })
        .catch(err => {
          //console.error(err.response.data)
          //commit('SET_AUTH_ERROR')
        })
    },
    logout({ dispatch }) {
      // axios({
      //   url: drf.accounts.logout(),
      //   method: 'post',
      //   headers: getters.authHeader,
      // })
        // .then(() => {
          dispatch('removeToken')
          alert('성공적으로 logout!')
          router.push({ name: 'LoginView' })
        // })
        // .error(err => {
        //   console.error(err.response)
        // })
    },
    getUserInfo() {
      //잘됨
      axios({
        url: drf.accounts.userInfo()+`${state.user.userEmail}`,
        method: 'GET',
      }).then(  res =>
          console.log(res),
          // 보낼 곳은 프로파일 뷰?
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    getUserToken(){
      //정확히 어떤 함수? 

    }

    // fetchCurrentUser({ commit, getters, dispatch }) {
    //   if (getters.isLoggedIn) {
    //     axios({
    //       url: drf.accounts.currentUserInfo(),
    //       method: 'get',
    //       headers: getters.authHeader,
    //     })
    //       .then(res => commit('SET_CURRENT_USER', res.data))
    //       .catch(err => {
    //         if (err.response.status === 401) {
    //           dispatch('removeToken')
    //           router.push({ name: 'LoginView' })
    //         }
    //       })
    //     }
    //   },
    },
  modules: {
  }
}
