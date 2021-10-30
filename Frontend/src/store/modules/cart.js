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
            if (localStorage.getItem('cart')) {
                for (let i = 0; i < state.cart.length; i++) {
                    let cartItem = {
                        colorId: state.cart[i].colors.id,
                        id: 1,
                        productId: state.cart[i].productCart.id,
                        quantity: state.cart[i].quantity,
                        userId: Number(localStorage.getItem('userId'))
                    }
                    commit('addCartItem', cartItem)

                }
                localStorage.removeItem('cart')
            }
        } else {
            if (localStorage.getItem('cart')) {
                commit('setCart', JSON.parse(localStorage.getItem('cart')))
            }
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
                })
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
    },

}

const mutations = {
    addCartItem(state, cartItem) {
        state.cartItem = cartItem;
        // if (state.cart.length > 0 && this.getters.isSameCartItem) {
        if (this.getters.isSameCartItem) {
            console.log('in');
            let index = 0
            if (this.getters.isLogin) {
                index = state.cart.findIndex(element => element.productCart.id === cartItem.productId)
                console.log('Login');
            } else {
                index = state.cart.findIndex(element => element.productCart.id === cartItem.productCart.id)
                console.log('NotLogin');
            }
            console.log('Index:' + index);
            let quantity = state.cart[index].quantity + cartItem.quantity > 10 ? 10 : state.cart[index].quantity + cartItem.quantity
            state.cart[index].quantity = quantity
            let payload = {
                index: index,
                quantity: quantity
            };
            this.dispatch('editQuantity', payload)

        } else {
            console.log('out');
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