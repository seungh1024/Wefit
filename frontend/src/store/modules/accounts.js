import axios from 'axios'
import router from '@/router'
import drf from '@/api/drf'

export default {
  state: {
    token: localStorage.getItem('token') || '',
    currentUser: {},
    user :{
      userEmail : '',
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
  },
  mutations: {
    Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.token = token,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('token', token)
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
          router.push({ name: 'signupdetail' ,params:{user_id:user_id}})
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    signupdetail({ commit }, userDetailData) {
      axios({
        url: drf.accounts.signup()+user_id,
        method: 'post',
        data: userDetailData,
      })
        .then(
          router.push({ name: 'HomeView' ,params:{username:userData.username}})
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
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
