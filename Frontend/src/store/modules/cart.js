import axios from 'axios'

const state = {
    cart: []
}


const getters = {
    cart: state => state.cart,
    totalInCart: state => state.cart.length,
    orderTotalPrice: state => state.cart.length > 0 ?
        state.cart
        .map(cartItem => cartItem.productCart.price)
        .reduce((previousPrice, currentPrice) => {
            return previousPrice + currentPrice
        }) : 0

}
const actions = {
    loadCartData({
        commit
    }) {
        axios
            .get(`${process.env.VUE_APP_API}/cartItem/findByUserId/${localStorage.getItem('userId')}`)
            .then(response => {
                commit('setCart', response.data)
            })
    },
    addCartItem({
        commit
    }, cartItem) {
        axios
            .post(`${process.env.VUE_APP_API}/cartItem/add_item/${localStorage.getItem('userId')}/${cartItem.productId}`, cartItem)
            .then(response => {
                commit('increaseCartItem', response.data)
            })
    },
    removeCartItem({
        commit
    },index){
        axios
            .delete(`${process.env.VUE_APP_API}/cartItem/delete/${state.cart[index].id}`)
            .then((response)=>{
                console.log(response);
                commit('reduceCartItem', index) 
            })
    }

}

const mutations = {
    increaseCartItem(state, item) {
        state.cart.push(item);
    },
    setCart(state, cart) {
        state.cart = cart;
    },
    reduceCartItem(state, index){
        state.cart.splice(index,1)
    }

}

export default {
    state,
    getters,
    actions,
    mutations
}