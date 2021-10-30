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
        }) : 0,
    isSameCartItem: (state) => {
        (item) => {
            let sameProduct = state.cart.map(element => element.productCart.id).includes(item.productCart.id)
            let sameColor = state.cart.map(element => element.colors.id).includes(item.colors.id)
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
                        userId: localStorage.getItem('userId')
                    }
                    console.log(cartItem);
                    this.dispatch('addCartItem', cartItem)
                }
                localStorage.removeItem('cart')
            }
        } else {
            if (localStorage.getItem('cart')) {
                commit('setCart', JSON.parse(localStorage.getItem('cart')))
            }
        }
    },
    // addCartItem({
    //     commit
    // }, cartItem) {
    //     if (this.getters.isLogin) {
    //         axios
    //             .post(`${process.env.VUE_APP_API}/cartItem/add_item/${localStorage.getItem('userId')}/${cartItem.productId}`, cartItem)
    //             .then(response => {
    //                 commit('increaseCartItem', response.data)
    //             })
    //     } else {
    //         commit('increaseCartItem', cartItem)
    //     }

    // },
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
    checkCartItem({
        state
    }, item) {
        // console.log(item);
        // let itemForCheck = item
        // let checkProduct = state.cart.map(element => element.productCart.id).includes(itemForCheck.productCart)
        // console.log(checkProduct)
        // console.log(itemForCheck)
        if (this.getters.isLogin) {
            let checkProduct = state.cart.map(element => element.productCart.id).includes(item.productId)
            let checkColor = state.cart.map(element => element.colors.id).includes(item.colorId)
            return checkProduct && checkColor
        } else {
            let checkProduct = state.cart.map(element => element.productCart.id).includes(item.productCart.id)
            let checkColor = state.cart.map(element => element.colors.id).includes(item.colors.id)
            return checkProduct && checkColor
        }

    }
}

const mutations = {
    // increaseCartItem(state, item) {
    addCartItem(cartItem) {
        // if(state.cart.length > 0){
        //     if (this.getters.isLogin) {
        //         let checkProduct = state.cart.map(element => element.productCart.id).includes(cartItem.productId)
        //         let checkColor = state.cart.map(element => element.colors.id).includes(cartItem.colorId)
        //         return checkProduct && checkColor
        //     } else {
        //         let checkProduct = state.cart.map(element => element.productCart.id).includes(cartItem.productCart.id)
        //         let checkColor = state.cart.map(element => element.colors.id).includes(cartItem.colors.id)
        //         return checkProduct && checkColor
        //     }

        // }
        // if (this.getters.isLogin) {
        //     axios
        //         .post(`${process.env.VUE_APP_API}/cartItem/add_item/${localStorage.getItem('userId')}/${cartItem.productId}`, cartItem)
        //         .then(response => {
        //             commit('increaseCartItem', response.data)
        //         })
        // } else {
        //     commit('increaseCartItem', cartItem)
        // }
        console.log(cartItem);
        // let checkProduct = state.cart.map(element => element.productCart.id).includes(item.productCart.id)
        // let checkColor = state.cart.map(element => element.colors.id).includes(item.colors.id)
        // if (checkProduct && checkColor) {
        //     let index = state.cart.findIndex(element => element.productCart.id === item.productCart.id)
        //     state.cart[index].quantity = state.cart[index].quantity + item.quantity > 10 ? 10 : state.cart[index].quantity + item.quantity
        //     return false
        // } else {
        //     state.cart.push(item);
        //     return true
        // }
        // console.log(this.dispatch('checkCartItem', item))
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