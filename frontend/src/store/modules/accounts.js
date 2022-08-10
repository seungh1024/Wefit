import axios from 'axios'
import router from '@/router'
import drf from '@/api/drf'

export default {
  state: {
    accessToken: '',
    refreshToken: '',
    currentUser: {},
    userEmail : '',
    userEmailCheck : true,
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
    userEmailCheck: state => state.userEmailCheck,
    getAccessoken: state => state.accessToken
  },
  mutations: {
  Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.accessToken = token,
    SET_REFRESHTOKEN: (state, refreshToken) => state.refreshToken = refreshToken,
    SET_CURRENT_USER: (state, user) => state.currentUser = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_USEREMAIL: (state, userEmail) => state.userEmail = userEmail,
    SET_DOUBLE_CHECK: (state, userEmailCheck) => state.userEmailCheck = userEmailCheck,
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
    socialLogin({ commit, dispatch } , user){
      axios({
        url: drf.accounts.socialLogin(),
        method: 'post',
        headers: {
          Authorization: `Bearer ${user.accessToken}`
        },
      })
        .then(res => {
          console.log(res.data)
          commit('SET_CURRENT_USER',user.userEmail)
          if( this.$store.actions.getUserInfo().userField ==''){
            router.push({name : 'signupdetail'})
          }
          else{
            dispatch('saveToken', res.data.token)
            dispatch('saveToken', res.data.refreshToken)
            router.push({ name: 'LoginHome' })
          }
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
          router.push({name : 'signupdetail'})
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    doubleCheck({commit}, userEmail){
      axios({
        url: drf.accounts.doubleCheck()+ userEmail+'/exists',
        method: 'GET',
      })  
        .then( res => {
          commit('SET_DOUBLE_CHECK',res.data)
          console.log(res.data)
         })
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    socialSignup({ commit }, user) {
      console.log(user)
      axios({
        url: drf.accounts.socialSignup(),
        method: 'post',
        headers: {
          Authorization: `Bearer ${user.accessToken}`
        },
      })
        .then(  res => {
          console.log(res),
          commit('SET_USEREMAIL',user.email),
          router.push({name : 'signupdetail'})
        }
        )
        .catch(err => {
          console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
        })
    },
    signupdetail({ commit }, userDetailData, userInterestList) {
      axios({
        url: drf.accounts.userInfo()+userDetailData.userEmail,
        method: 'post',
        data: userDetailData,
      }).then( res=> {
          console.log(res)
          router.push({ name: 'LoginView'})
          })
        .catch(err => {
          console.error(err.res.data)
          commit('SET_AUTH_ERROR')
        })
    },
    logout({ dispatch, state }) {
       axios({
       url: drf.accounts.logout(),
         method: 'GET',
         headers: {
          Authorization: `Bearer ${state.accessToken}`
        },
       })
       .then(() => {
          dispatch('removeToken')
          alert('성공적으로 logout!')
          router.push({ name: 'NonLoginView' })
         })
         .error(err => {
           console.error(err.response)
        })
    },
    getUserInfo({ commit, state}){
      axios({
        url: drf.accounts.userInfo() + state.userEmail,
        method: 'GET',
      }).then(  res =>
          console.log(res),
          //어디서 쓸건가요?
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
