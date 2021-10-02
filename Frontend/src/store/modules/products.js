import axios from 'axios'

// const url = 'http://localhost:5000/products';
const api = 'http://52.187.10.17/orange-it/product'
const get_list = `${api}/list`
const get_product = `${api}/`
// const get_product_by_name = `${api}/getByName/`

// const get_images = `${api}/image/get/`

const state = {
    products: [],
    product: [],
}

const getters = {
    products: state => state.products,
    product: state => state.product,
}

const actions = {
    async loadProducts({
        commit
    }) {
        try {
            const response = await axios.get(get_list)
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
    },
    async loadProduct(contex, productId) {
        try {
            const response = await axios.get(`${get_product}${productId}`).then((res)=> res.data)
            contex.commit('SET_PRODUCT', response)
        } catch (error) {
            console.log(error);
        }
    }

}


const mutations = {
    SET_PRODUCTS(state, payload) {
        state.products = payload
    },
    SET_PRODUCT(state, payload) {
        state.product = payload
    }
}

export default {
    state,
    getters,
    actions,
    mutations
}