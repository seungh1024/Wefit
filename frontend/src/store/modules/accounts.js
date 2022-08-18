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
      userNickname : '',
    },
    authError: '',
  },
  getters: {
    isLoggedIn: state => !!state.accessToken,
    currentUser: state => state.currentUser,
    authError: state => state.authError,
    getUserEmail: state => state.userEmail,
    userEmailCheck: state => state.userEmailCheck,
    getAccessoken: state => state.accessToken,
    getUser: state => state.user
  },
  mutations: {
  Login(state) {
      state.isLogin = true
    },
    SET_TOKEN: (state, token) => state.accessToken = token,
    SET_REFRESHTOKEN: (state, refreshToken) => state.refreshToken = refreshToken,
    SET_CURRENT_USER: (state, user) => state.user = user,
    SET_AUTH_ERROR: (state, error) => state.authError = error,
    SET_USEREMAIL: (state, userEmail) => state.userEmail = userEmail,
    SET_DOUBLE_CHECK: (state, userEmailCheck) => state.userEmailCheck = userEmailCheck,
  },
  actions: {
    saveToken({ commit }, token) {
      commit('SET_TOKEN', token)
      localStorage.setItem('accessToken', token)
    },
    saveRefreshToken({ commit }, refreshToken){
      commit('SET_REFRESHTOKEN', refreshToken)
      localStorage.setItem('refreshToken', refreshToken)
    },
    removeRefreshToken({ commit }){
      commit('SET_REFRESHTOKEN', "")
    },
    removeVuex() {
      localStorage.setItem('vuex', '')  
    }, 
    removeToken({ commit }) {
      commit('SET_TOKEN', '')
      localStorage.setItem('accessToken', '')
    }, 
    login({ commit, dispatch,}, userData) {
      axios({
            url: drf.accounts.login(),
            method: 'post',
            data: userData
          })
            .then((res) => {
              console.log(res);
              console.log(res.data);
              console.log(res.data.token);
              dispatch('saveToken', res.data.token)
              dispatch('saveRefreshToken', res.data.refreshToken)
              commit('SET_USEREMAIL', userData.userEmail)
              dispatch('getUserInfo')

              router.push({ name: 'LoginHome' })
            })
            .catch(err => {
              alert("아이디 또는 비밀번호가 잘못되었습니다.확인하고 다시 입력하세요")
              console.log(err)
              console.error(err)
              commit('SET_AUTH_ERROR', err)
            })
    },
    socialLogin({ commit, dispatch, state } , user){
      console.log(1111)
      axios({
        url: drf.accounts.socialLogin(),
        method: 'post',
        headers: {
          Authorization: `Bearer ${user.accessToken}`
        },
      })
        .then(res => {
          commit('SET_USEREMAIL',user.email)
          dispatch('getUserInfo')
          
          setTimeout(()=>{
            let data = state.user
            if(data.userMbti == ''){
            alert("상세정보가 입력되지 않았습니다. 상세정보로 이동합니다")
            router.push({name : 'SigupDetail'})
          }
          else{
            dispatch('saveToken', res.data.token)
            dispatch('saveRefreshToken', res.data.refreshToken)
            router.push({ name: 'LoginHome' })
          }    
          }, 750)
          
          
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
          router.push({name : 'SigupDetail'})
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
      console.log("se: ",user.se)
      console.log("token: ",user.accessToken)
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
          router.push({name : 'SigupDetail'})
        }
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
      }).then( res=> {
          console.log(res)
          router.push({ name: 'LoginView'})
          })
        .catch(err => {
          console.error(err.res.data)
          commit('SET_AUTH_ERROR')
        })
    },


    logout({ dispatch, commit, state }) {
      axios({
        url: drf.accounts.logout(),
        method: 'GET',
        headers: {
        Authorization: `Bearer ${state.accessToken}`
        }
        }).then(() => {
          const userdata = {
            userMbti   : '',
            userGender : '', 
            userName   : '',
            userField  : '',
            userPhone  : '',
            userNickname : '',
          }
            dispatch('removeToken')
            dispatch('removeVuex')
            dispatch('setUserInfo',userdata)
            commit('SET_USEREMAIL','')
            commit('SET_DOUBLE_CHECK',true)
            alert('성공적으로 logout!')
            router.push({ name: 'NonLoginView' })
        })
        .catch(err => {
          alert("에러!")
          console.log(state.accessToken)
          console.error(err.response)
       })
   },


    getUserInfo({commit,dispatch, state}){
        axios({
          url: drf.accounts.userInfo()+`${state.userEmail}`,
          headers: {
            Authorization: `Bearer ${state.accessToken}`
            },
          method: 'GET',
           }).then((res) =>
            {
            const userdata = {
              userMbti   : res.data.userMBTI,
              userGender : res.data.userGender, 
              userName   : res.data.userName,
              userField  : res.data.userField,
              userPhone  : res.data.userPhone,
              userNickname : res.data.userNickname,
            }
            dispatch('setUserInfo',userdata)
          })
          .catch((err) => {
            console.error(err.response.data)
          commit('SET_AUTH_ERROR', err.response.data)
          });
      },
    setUserInfo({commit},userdata){
      //console.log(userdata)
      commit('SET_CURRENT_USER', userdata)
    },
    getUserToken(){
      //정확히 어떤 함수? 

    },

    editProfile({dispatch,commit,state} ,userDetailData){
      console.log(1111)
      axios({
        url: drf.accounts.userInfo()+state.userEmail,
        method: 'PUT',
        data: userDetailData,
      }).then( res=> {
          dispatch('getUserInfo')
          confirm("회원정보가 수정되었습니다.")
          console.log(res)
          router.push({ name:'ProfileView'})
          })
        .catch(err => {
          console.error(err)
          commit('SET_AUTH_ERROR')
        })
    },
    
  },
  modules: {
  }
}
