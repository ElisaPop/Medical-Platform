import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userData: [],
    userRole: -1,
    accessToken: 0
  },
  getters:{
    userData: state =>{
      return state.userData
    },
    userRole: state =>{
      return state.userRole
    },
    getAuthorization: state =>{
      return {Authorization: "Bearer" + state.accessToken};
    }
  },
  mutations: {
    setUserData(state,data){
      state.userData = data
    },
    setUserRole(state,role){
      state.userRole = role
    },
    setAuthorization(state,token){
      state.accessToken = token
    }
  }
})
