import axios from 'axios'

// const url = 'http://localhost:5000/products';
const api = 'http://52.187.10.17/orange-it/product'
const get_list = `${api}/list`
// const get_product_by_name = `${api}/getByName/`

// const get_images = `${api}/image/get/`

const state = {
    products: [],
    product: {},
}

const getters = {
    products: state => state.products,
}

const actions = {
    async loadProducts({
        commit
    }) {
        axios
            .get(get_list)
            .then(res => {
                let products = res.data
                commit('SET_PRODUCTS', products)
            })
            .catch(error => {
                console.log(error)
            })
    },
}


const mutations = {
    SET_PRODUCTS(state, payload) {
        state.products = payload
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}