import axios from 'axios'


const state = {
    cart: []
}


const getters = {
    cart: state => state.cart,
    totalInCart: state => state.cart.length > 0 ?
        state.cart
        .map(cartItem => cartItem.quantity)
        .reduce((previouItem, currentItem) => {
            return previouItem + currentItem
        }) : 0,
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
            if (localStorage.getItem('cart')) {
                for (let i = 0; i < state.cart.length; i++) {
                    let cartItem = {
                        colorId: state.cart[i].colors.id,
                        id: 1,
                        productId: state.cart[i].productCart.id,
                        quantity: state.cart[i].quantity,
                        userId: localStorage.getItem('userId')
                    }
                    console.log(cartItem);
                    this.dispatch('addCartItem', cartItem)
                }
                localStorage.removeItem('cart')
            }
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
        if (this.getters.isLogin) {
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
        if (this.getters.isLogin) {
            axios
                .delete(`${process.env.VUE_APP_API}/cartItem/delete/${state.cart[index].id}`)
                .then((response) => {
                    console.log(response);
                    commit('reduceCartItem', index)
                })
        } else {
            commit('reduceCartItem', index)
            localStorage.setItem('cart', JSON.stringify(state.cart))
        }

    },
    editQuantity({
        commit
    }, payload) {
        if (this.getters.isLogin) {
            let cartItem = {
                id: state.cart[payload.index].id,
                quantity: Number(payload.quantity),
                productId: state.cart[payload.index].productCart.id,
                userId: state.cart[payload.index].userId,
                colorId: state.cart[payload.index].colors.id
            }
            axios
                .put(`${process.env.VUE_APP_API}/cartItem/update`, cartItem)
                .then(response => {
                    if (response.status === 200) {
                        commit('setCartItemQuantity', payload)
                    }
                    console.log(response)
                })
            console.log(state.cart);
        } else {
            commit('setCartItemQuantity', payload)
            localStorage.setItem('cart', JSON.stringify(state.cart))
        }

    },
    clearCart({
        commit
    }) {
        commit('setCart', [])
        localStorage.removeItem('cart')
    }
}

const mutations = {
    increaseCartItem(state, item) {
        let checkProduct = state.cart.map(element => element.productCart.id).includes(item.productCart.id)
        let checkColor = state.cart.map(element => element.colors.id).includes(item.colors.id)
        if (checkProduct && checkColor) {
            let index = state.cart.findIndex(element => element.productCart.id === item.productCart.id)
            state.cart[index].quantity = state.cart[index].quantity + item.quantity > 10 ? 10 : state.cart[index].quantity + item.quantity
        } else {
            state.cart.push(item);
        }
    },
    setCart(state, cart) {
        state.cart = cart;
    },
    reduceCartItem(state, index) {
        state.cart.splice(index, 1)
    },
    setCartItemQuantity(state, payload) {
        state.cart[payload.index].quantity = payload.quantity
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}