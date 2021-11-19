import axios from 'axios'
import router from '../../router'

const state = {
    userInfo: null,
    userId: localStorage.getItem('userId'),
    addresses: [],
    isAdmin: false,
    token: `Bearer ${localStorage.getItem('token')}`
}

const getters = {
    userInfo: state => state.userInfo,
    addresses: state => state.addresses,
    defaultAddress: state => state.addresses[0],
    isAdmin: state => state.isAdmin,
    token: state => state.token,
}

const actions = {
    loadUserData({
        commit
    }) {
        if (state.userId) {
            axios
                .get(`${process.env.VUE_APP_API}/user/${state.userId}`, {
                    headers: {
                        'Authorization': state.token
                    }
                })
                .then(response => {
                    console.log(response)
                    commit('setUserInfo', response.data)
                    commit('setIsAdmin', response.data.role[0].name === 'Admin' ? true : false)
                    if (state.isAdmin) {
                        router.push('/admin')
                        this.dispatch("loadProducts",state.isAdmin);
                    }else{
                        this.dispatch("loadUserAddresses");
                    }
                })
        }
    },
    loadUserAddresses({
        commit
    }) {
        if (!state.isAdmin) {
            axios
                .get(`${process.env.VUE_APP_API}/delivery/findByUserId/${state.userId}`, {
                    headers: {
                        'Authorization': state.token
                    }
                })
                .then(response => {
                    commit('setAddresses', response.data)
                })
        }
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
    setIsAdmin(state, data) {
        state.isAdmin = data
    },
    setLoginStatus(state, data) {
        state.loginStatus = data
    },
    setAddresses(state, data) {
        state.addresses = data
    },
    addAddress(state, address) {
        axios
            .post(`${process.env.VUE_APP_API}/delivery/add`, address, {
                headers: {
<<<<<<< HEAD
                    'Authorization': this.getters.token
=======
                    'Authorization': state.token
>>>>>>> a67ad5a0dedad12d87d1897663729cda092a7e55
                }
            })
            .then(response => {
                state.addresses.push(response.data);
            })
    },
    removeAddress(state, index) {
        axios
            .delete(`${process.env.VUE_APP_API}/delivery/${state.addresses[index].id}`, {
                headers: {
<<<<<<< HEAD
                    'Authorization': this.getters.token
=======
                    'Authorization': state.token
>>>>>>> a67ad5a0dedad12d87d1897663729cda092a7e55
                }
            })
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