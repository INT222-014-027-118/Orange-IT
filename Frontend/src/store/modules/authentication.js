import axios from "axios"

const state = {
    userinfo: {},
    jwt: ''
}

const getters = {}

const actions = {
    async login({commit},data){
        axios
            .post(`${process.env.VUE_APP_API}/authenticate`,data)
            .then(res => {
                console.log(data)
                console.log(res)
                let userinfo = res.data
                commit('SET_USERINFO', userinfo)
                commit('SET_JWT', userinfo.jwtToken)
            })
            .catch(error => {
                console.log(error)
            })
    }
}


const mutations = {
    SET_USERINFO(state, payload) {
        state.userinfo = payload
    },
    SET_JWT(state, payload) {
        state.jwt = payload
    },
}

export default {
    state,
    getters,
    actions,
    mutations
}