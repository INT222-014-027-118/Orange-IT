import Vuex from 'vuex';
import products from './modules/products';
import form from './modules/form';
import user from './modules/user.js';
import cart from './modules/cart.js'

export default new Vuex.Store({
  modules: {
    products,
    form,
    user,
    cart
  },

  state: {
    itemTest: [],
    showAccountPage: true,
    changeMode: false,
    countHistory: 0,
    loginStatus: false,
    compareProducts: [],
    compareProductsWithId: 0
  },

  mutations: {
    setShowAccountPage(state, value = !state.showAccountPage) {
      state.showAccountPage = value
    },
    setChangeMode(state, value = !state.changeMode) {
      state.changeMode = value
    },
    setCompareProducts(state, value) {
      console.log(value.id);
      state.compareProducts.push(value)
      state.compareProductsWithId = value.id
    },
    resetCompareProducts(state, value = []) {
      state.compareProducts = value
    },

  },

  actions: {},

  getters: {
    itemTest: state => state.itemTest,
    showAccountPage: state => state.showAccountPage,
    changeMode: state => state.changeMode,
    isLogin: state => state.loginStatus = localStorage.getItem('userId') ? true : false,
    compareProducts: state => state.compareProducts,
    countCompareProducts: state => state.compareProducts.length,
    compareProductsWithId: state => state.compareProductsWithId,
  }

});