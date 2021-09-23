import axios from 'axios'

const api = 'http://52.187.10.17/orange-it'
// const get_colors = `${api}/color/list`


// const post_product = `${api}/product/add`
// const put_product = `${api}/product/update`
// const post_image = `${api}/image/add`
// const put_image = `${api}/image/update/`

const state = {
}

const getters = {
    products: state => state.products,
}

const actions = {
    async loadProducts({
        commit
    }) {
        try {
            const response = await axios.get(url)
            commit('SET_PRODUCTS', response.data)
        } catch (error) {
            console.log(error);
        }

        // axios
        //     .get(url)
        //     .then(data => {
        //         let products = data.data
        //         commit('SET_PRODUCTS', products)
        //     })
        //     .catch(error => {
        //         console.log(error)
        //     })
    }
}


const mutations = {
    SET_PRODUCTS(state, payload) {
        state.products = payload
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}