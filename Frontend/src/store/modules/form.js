import axios from 'axios'

const api = 'http://52.187.10.17/orange-it'
const get_colors = `${api}/color/list`
const get_categories = `${api}/category/list`

// const post_product = `${api}/product/add`
// const put_product = `${api}/product/update`
// const post_image = `${api}/image/add`
// const put_image = `${api}/image/update/`

const state = {
    colors:[],
    categories:[],
    brand: [
        "Razer",
        "Logitech"
    ]
}

const getters = {
    colors: state => state.colors,
    categories: state => state.categories,
}

const actions = {
    loadColors({commit}) {
        axios
            .get(get_colors)
            .then(data => {
                let colors = data.data
                commit('SET_PRODUCTS', colors)
            })
            .catch(error => {
                console.log(error)
            })
    },
    loadCategories({commit}){
        axios
        .get(get_categories)
        .then(data => {
            let categories = data.data
            commit('SET_PRODUCTS', categories)
        })
        .catch(error => {
            console.log(error)
        })
    },


}


const mutations = {
    SET_COLORS(state, payload) {
        state.colors = payload
    },
    SET_CATEGORIES(state, payload) {
        state.categories = payload
    },
}

export default {
    state,
    getters,
    actions,
    mutations
}