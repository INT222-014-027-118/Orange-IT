

const state = {
    userinfo: {}
}

const getters = {
    userinfo: state => state.userinfo,
}

const actions = {


}


const mutations = {
    SET_USERINFO(state, data) {
        state.userinfo = data
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}