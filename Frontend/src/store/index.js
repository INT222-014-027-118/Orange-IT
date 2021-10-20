import Vuex from 'vuex';
import products from './modules/products';
import form from './modules/form';
import authentication from './modules/authentication.js';
import cart from './modules/cart.js'

export default new Vuex.Store({
  modules: {
    products,
    form,
    authentication,
    cart
  },
  state: {
    itemTest: [],
    showAccountPage: true,
    changeMode: false,
    countHistory: 0,
    loginStatus:false
  },
  mutations: {
    setShowAccountPage(state, value = !state.showAccountPage) {
      state.showAccountPage = value
    },
    setChangeMode(state, value = !state.changeMode) {
      state.changeMode = value
    }
  },
  actions: {
  },
  getters: {
    itemTest: state => state.itemTest,
    showAccountPage: state => state.showAccountPage,
    changeMode: state => state.changeMode,
    isLogin: state => state.loginStatus = localStorage.getItem('userId')?true:false

  }
});