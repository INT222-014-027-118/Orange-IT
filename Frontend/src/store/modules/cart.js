import axios from 'axios'


const state = {
    cart: []
}


const getters = {
    cart: state => state.cart,
    totalInCart: state => state.cart.length,
    orderTotalPrice: state => state.cart.length > 0 ?
        state.cart
        .map(cartItem => cartItem.productCart.price * cartItem.quantity)
        .reduce((previousPrice, currentPrice) => {
            return previousPrice + currentPrice
        }) : 0

}
const actions = {
    loadCartData({
        commit
    }) {
        if (this.getters.isLogin) {
            axios
                .get(`${process.env.VUE_APP_API}/cartItem/findByUserId/${localStorage.getItem('userId')}`)
                .then(response => {
                    commit('setCart', response.data)
                })
        } else {
            if (localStorage.getItem('cart')) {
                commit('setCart', JSON.parse(localStorage.getItem('cart')))
            }
        }

    },
    addCartItem({
        commit
    }, cartItem) {
        if (this.state.loginStatus) {
            axios
                .post(`${process.env.VUE_APP_API}/cartItem/add_item/${localStorage.getItem('userId')}/${cartItem.productId}`, cartItem)
                .then(response => {
                    commit('increaseCartItem', response.data)
                })
        } else {
            commit('increaseCartItem', cartItem)
        }

    },
    removeCartItem({
        commit
    }, index) {
        axios
            .delete(`${process.env.VUE_APP_API}/cartItem/delete/${state.cart[index].id}`)
            .then((response) => {
                console.log(response);
                commit('reduceCartItem', index)
            })
    },
    // mergeCart({
    //     commit
    // }){


    // }
}

const mutations = {
    increaseCartItem(state, item) {
        state.cart.push(item);
    },
    setCart(state, cart) {
        state.cart = cart;
    },
    reduceCartItem(state, index) {
        state.cart.splice(index, 1)
    },
    cartItemQuantityPlus(state, index) {
        state.cart[index].quantity++

        //สำหรับติดต่อฐานข้อมูล
        // console.log(state.cart.map((cartItem) => {
        //     return {
        //         id: cartItem.id,
        //         quantity: cartItem.quantity,
        //         productId: cartItem.productCart.id,
        //         userId: cartItem.userId,
        //         colorId:cartItem.colors.id
        //     }
        // })[index]);
        // axios
        //     .put(`${process.env.VUE_APP_API}/cartItem/update`, state.cart[index])
        //     .then(response => console.log(response))
    },
    cartItemQuantityMinus(state, index) {
        if (state.cart[index].quantity > 1) {
            --state.cart[index].quantity
        } else {
            this.dispatch('removeCartItem', index)
        }
    },
    //ยังไม่ใช้ดูก่อน
    // cartItemQuantitySet(state, index, number) {
    //     state.cart[index].quantity = number
    // }
}

export default {
    state,
    getters,
    actions,
    mutations
}