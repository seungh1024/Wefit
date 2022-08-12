import { createStore } from 'vuex'
import accounts from './modules/accounts'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
    },
  modules: { accounts,},
  plugins: [
    createPersistedState({
      paths: ["accounts",],
    })
  ]
  
})
