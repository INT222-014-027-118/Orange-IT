import axios from "axios"
const api = process.env.VUE_APP_API
const get_rating_list = `${api}/rating/list`

const state = {
    ratingTitleList: []

}

const getters = {
    ratingTitleList: state => state.ratingTitleList,
}

const actions = {
    loadRatingTitle({
        commit
    }) {
        axios
            .get(get_rating_list)
            .then((response) => {
                console.log(response);
                commit('SET_RATING_TITLE_LIST', response.data)
            })


    }

}

const mutations = {
    SET_RATING_TITLE_LIST(state, data) {
        state.ratingTitleList = data;
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}