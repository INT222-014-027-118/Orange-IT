import axios from 'axios'

const api = process.env.VUE_APP_API
const get_colors = `${api}/color/list`
const get_specs = `${api}/spec/list`

const post_product = `${api}/product/add`
// const put_product = `${api}/product/update`
// const post_image = `${api}/image/add`
const post_image_Multiple = `${api}/image/uploadMultipleFiles`
// const put_image = `${api}/image/update/`


const state = {
    colors: [],
    categories: [],
    specs: [],
    brands: [
        'Razer',
        'Logitech'
    ],
    token: `Bearer ${localStorage.getItem('token')}`

}


const getters = {
    colors: state => state.colors,

    brands: state => state.brands,
    specs: state => state.specs
}

const actions = {
    loadDataForm({
        commit
    }) {
        axios
            .get(get_colors)
            .then(data => {
                let colors = data.data
                commit('SET_COLORS', colors)
            })
            .catch(error => {
                console.log(error)
            })

        axios
            .get(get_specs)
            .then(data => {
                let specs = data.data
                commit('SET_SPECS', specs)
            })
            .catch(error => {
                console.log(error)
            })
    },

    uploadImages(context, images) {
        images.multiple = true;
        let data = new FormData();
        images.forEach(img => {
            data.append("orange", img)
        })
        console.log(data);
        axios
            .post(post_image_Multiple, data)
            .then(response => {
                console.log("response: ", response)
            })
    },

    addProduct(context, product) {
        axios
            .post(post_product, product)
            .then(response => {
                // if(response.status === 200){

                // }
                console.log("response: ", response)
            })
            .catch(error => {
                console.log(error)
            })
    }

}


const mutations = {
    SET_COLORS(state, payload) {
        state.colors = payload
    },
    SET_CATEGORIES(state, payload) {
        state.categories = payload
    },
    SET_SPECS(state, payload) {
        state.specs = payload
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}