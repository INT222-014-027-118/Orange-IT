import axios from 'axios'


const state = {
    orders: []
}


const getters = {
    orders: state => state.orders.reverse(),
}
const actions = {
    loadOrders({
        commit
    }) {
        axios
            .get(`${process.env.VUE_APP_API}/order/getByUserId/${localStorage.getItem('userId')}`, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                commit('setOrders', response.data)
            })
    }

}

const mutations = {
    setOrders(state, data) {
        state.orders = data
    },
}

export default {
    state,
    getters,
    actions,
    mutations
}