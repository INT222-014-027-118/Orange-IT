import axios from 'axios'

const state = {
    userInfo: null,
    userId: localStorage.getItem('userId'),
    addresses: [],

}

const getters = {
    userInfo: state => state.userInfo,
    addresses: state => state.addresses,
    defaultAddress: state => state.addresses[0]
}

const actions = {
    loadUserData({
        commit
    }) {
        if (state.userId) {
            axios
                .get(`${process.env.VUE_APP_API}/user/${state.userId}`)
                .then(response => {
                    commit('setUserInfo', response.data)
                })
        }
    },
    loadUserAddresses({
        commit
    }) {
        axios
            .get(`${process.env.VUE_APP_API}/delivery/findByUserId/${state.userId}`)
            .then(response => {
                commit('setAddresses', response.data)
            })
    },

    logout({
        commit
    }) {
        localStorage.removeItem("token")
        localStorage.removeItem("userId")
        this.dispatch('clearCart')
        commit('setUserInfo', null)
        commit('setLoginStatus', false)
    },
}


const mutations = {
    setUserInfo(state, data) {
        state.userInfo = data
    },
    setLoginStatus(state, data) {
        state.loginStatus = data
    },
    setAddresses(state, data) {
        state.addresses = data
    },
    addAddress(state, address) {
        axios
            .post(`${process.env.VUE_APP_API}/delivery/add`, address)
            .then(response => {
                state.addresses.push(response.data);
            })
    },
    removeAddress(state, index) {
        axios
            .delete(`${process.env.VUE_APP_API}/delivery/${state.addresses[index].id}`)
            .then(response => {
                if (response.status === 200) {
                    state.addresses.splice(index, 1)
                }
            })
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}