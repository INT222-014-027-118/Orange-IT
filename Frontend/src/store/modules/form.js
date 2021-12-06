import axios from 'axios'
import router from '../../router'

const api = process.env.VUE_APP_API
const get_colors = `${api}/color/list`
// const get_specs = `${api}/spec/list`
const get_attributes = `${api}/attribute/list`

const post_product = `${api}/product/add`
const put_product = `${api}/product/update`
const post_attribute = `${api}/attribute/add`
const put_attribute = `${api}/attribute/update`
const delete_attribute = `${api}/attribute/delete/`
// const post_image = `${api}/image/add`
const post_image_Multiple = `${api}/image/uploadMultipleFiles`
// const put_image = `${api}/image/update/`
const delete_product = `${api}/product/delete/`
const delete_productI = `${api}/product/deleteI/`
const delete_productP = `${api}/product/deleteP/`
const delete_image = `${api}/image/delete/`


const state = {
    colors: [],
    categories: [],
    specs: [],
    brands: [
        'Razer',
        'Logitech'
    ],
    attributes: [],
    token: `Bearer ${localStorage.getItem('token')}`,

}


const getters = {
    colors: state => state.colors,
    attributes: state => state.attributes.map((attribute) => {
        return {
            id: attribute.id,
            attribute: attribute.attribute,
            active: false,
            show: true
        }
    }),
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
    },

    loadAttirbute({
        commit
    }) {
        return axios
            .get(get_attributes)
            .then(data => {
                let attributes = data.data
                commit('SET_ATTRIBUTES', attributes)
            })
            .then(response => {
                return response
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
        return axios
            .post(post_image_Multiple, data, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                console.log("response: ", response)
                return response
            })
    },

    deleteImage(contextm, name) {
        return axios
            .post(delete_image, name, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                console.log("response: ", response)
                return response
            })
    },

    addProduct(context, product) {
        axios
            .post(post_product, product, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                if (response.status === 200) {
                    router.push('/admin/manage-products')
                }
                console.log("response: ", response)
            })
            .catch(error => {
                console.log(error)
            })
    },
    updateProduct(context, payload) {
        if (payload.newImages.length > 0) {


            this.dispatch('uploadImages', payload.newImages).then((response) => {
                console.log(response);
                if (response.status == 200) {
                    axios
                        .put(put_product, payload.product, {
                            headers: {
                                'Authorization': this.getters.token
                            }
                        })
                        .then(response => {
                            if (response.status === 200) {
                                router.push('/admin/manage-products')
                            }
                        })
                }
            })
        } else {
            axios
                .put(put_product, payload.product, {
                    headers: {
                        'Authorization': this.getters.token
                    }
                })
                .then(response => {
                    if (response.status === 200) {
                        router.push('/admin/manage-products')
                    }
                })
        }

    },

    addAttribute(context, attribute) {
        return axios
            .post(post_attribute, attribute, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                return response
            })
            .catch(error => {
                console.log(error)
            })
    },

    deleteAttribute(context, id) {
        return axios
            .delete(`${delete_attribute}${id}`, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                return response
            })
            .catch(error => {
                console.log(error)
            })
    },

    updateAttribute(context, attribute) {
        return axios
            .put(`${put_attribute}`, attribute, {
                headers: {
                    'Authorization': this.getters.token
                }
            })
            .then(response => {
                return response
            })
            .catch(error => {
                console.log(error)
            })
    },

    deleteProduct({
        dispatch
    }, id) {
        axios
            .delete(`${delete_productI}${id}`, {
                headers: {
                    'Authorization': this.getters.token
                }
            }).then((response) => {
                if (response.status === 200) {
                    axios
                        .delete(`${delete_productP}${id}`, {
                            headers: {
                                'Authorization': this.getters.token
                            }
                        }).then((response) => {
                            if (response.status === 200) {
                                axios
                                    .delete(`${delete_product}${id}`, {
                                        headers: {
                                            'Authorization': this.getters.token
                                        }
                                    })
                                    .then(response => {
                                        console.log("response: ", response)
                                        dispatch("loadProducts")
                                    })
                                    .catch(error => {
                                        console.log(error)
                                    })
                            }
                        })



                }
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

    SET_ATTRIBUTES(state, payload) {
        state.attributes = payload
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}