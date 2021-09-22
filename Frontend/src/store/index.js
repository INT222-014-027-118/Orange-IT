import Vuex from 'vuex';
import products from './modules/products';

export default new Vuex.Store({
  modules: {
    products
  },
  state: {
    itemTest: [],
    cart: []
  },
  mutations: {
    addCartItem(state, item) {
      state.cart.push(item);
    },
  },
  actions: {
    addCartItem(context, item) {
      context.commit('addCartItem', item)
    },
  },
  getters: {
    itemTest: state => state.itemTest,
    cart: state => state.cart,
    totalInCart: state => state.cart.length,
    // totalInCart(state) {
    //   return state.cart.length
    // },
  }
});