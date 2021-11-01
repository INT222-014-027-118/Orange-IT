import axios from 'axios'


const state = {
    cart: [],
    cartItem: null
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
        }) : 0,
    isSameCartItem: (state, getters) => {
        if (getters.isLogin) {
            let sameProduct = state.cart.map(element => element.productCart.id).includes(state.cartItem.productId)
            let sameColor = state.cart.map(element => element.colors.id).includes(state.cartItem.colorId)
            return sameProduct && sameColor
        } else {
            let sameProduct = state.cart.map(element => element.productCart.id).includes(state.cartItem.productCart.id)
            let sameColor = state.cart.map(element => element.colors.id).includes(state.cartItem.colors.id)
            return sameProduct && sameColor
        }
    }
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
                .then(() => {
                    if (localStorage.getItem('cart')) {
                        let cart = JSON.parse(localStorage.getItem('cart'))
                        for (let i = 0; i < cart.length; i++) {
                            let cartItem = {
                                colorId: cart[i].colors.id,
                                id: 1,
                                productId: cart[i].productCart.id,
                                quantity: cart[i].quantity,
                                userId: Number(localStorage.getItem('userId'))
                            }
                            commit('addCartItem', cartItem)
                        }
                        localStorage.removeItem('cart')
                    }

                })
        } else {
            if (localStorage.getItem('cart')) {
                commit('setCart', JSON.parse(localStorage.getItem('cart')))
            }
        }
    },
    clearCart({
        commit
    }) {
        commit('setCart', [])
        localStorage.removeItem('cart')
    },

}

const mutations = {
    addCartItem(state, cartItem) {
        state.cartItem = cartItem;
        if (this.getters.isSameCartItem) {
            let index = 0
            if (this.getters.isLogin) {
                index = state.cart.findIndex(element => element.productCart.id === cartItem.productId)
            } else {
                index = state.cart.findIndex(element => element.productCart.id === cartItem.productCart.id)
            }
            let quantity = state.cart[index].quantity + cartItem.quantity > 10 ? 10 : state.cart[index].quantity + cartItem.quantity
            state.cart[index].quantity = quantity
            let payload = {
                index: index,
                quantity: quantity
            };
            this.commit('editQuantity', payload)

        } else {
            if (this.getters.isLogin) {
                axios
                    .post(`${process.env.VUE_APP_API}/cartItem/add_item/${localStorage.getItem('userId')}/${cartItem.productId}`, cartItem)
                    .then(response => {
                        state.cart.push(response.data);
                    })
            } else {

                state.cart.push(cartItem);
            }
        }

    },
    setCart(state, cart) {
        state.cart = cart;
    },
    removeCartItem(state, index) {
        if (this.getters.isLogin) {
            axios
                .delete(`${process.env.VUE_APP_API}/cartItem/delete/${state.cart[index].id}`)
                .then(() => {
                    state.cart.splice(index, 1)
                })
        } else {
            state.cart.splice(index, 1)
            localStorage.setItem('cart', JSON.stringify(state.cart))
        }
    },
    editQuantity(state, payload) {
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
                        state.cart[payload.index].quantity = payload.quantity
                    }
                })
        } else {
            state.cart[payload.index].quantity = payload.quantity
            localStorage.setItem('cart', JSON.stringify(state.cart))
        }

    },
}

export default {
    state,
    getters,
    actions,
    mutations
}