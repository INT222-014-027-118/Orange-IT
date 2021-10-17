import axios from 'axios'

const state = {
    userInfo: null,
    loginStatus:false
}

const getters = {
    userInfo: state => state.userInfo,
    isLogin: state => state.loginStatus = localStorage.getItem('userId')?true:false
}

const actions = {
    loadUserData({
        commit
    }) {
        let userId = localStorage.getItem('userId')
        if (userId) {
            axios
                .get(`${process.env.VUE_APP_API}/user/${userId}`)
                .then(response => {
                    commit('setUserInfo', response.data)
                })
        }
    },
    logout({
        commit
    }) {
        if (window.confirm("Are you sure?")) {
            localStorage.removeItem("token")
            localStorage.removeItem("userId")
            commit('setUserInfo', null)
            commit('setLoginStatus',false)
            return true
        }
        return false
    },
}


const mutations = {
    setUserInfo(state, data) {
        state.userInfo = data
    },
    setLoginStatus(state, data) {
        state.loginStatus = data
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}