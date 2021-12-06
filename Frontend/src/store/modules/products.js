import axios from 'axios'

const api = process.env.VUE_APP_API
const get_products_list = `${api}/product/list`
// const get_products_list_admin = `${api}/product/listAdmin`
const get_by_category = `${api}/product/getByCategory`
// const get_by_name = `${api}/product/getByBrandName`
const get_categories = `${api}/category/list`
// const get_product_by_name = `${api}/getByName/`

// const get_images = `${api}/image/get/`

const state = {
    products: [],
    categories: [],
    // productForSearch: [],
}

const getters = {
    products: state => state.products,
    categories: state => state.categories,
    rootCategories: state => {
        return state.categories.filter((element) => element.parentId === null)
    },
    childCategories: state => (id) => {
        return state.categories.filter((element) => element.parentId === id)
    },
    // productForSearch: state => state.productForSearch
    setCategories: (state, getters) => {
        return getters.rootCategories.map((category) => {
            let upperId = category.id
            return {
                id: category.id,
                category: category.category,
                child: [state.categories.filter((category) => {
                    return category.parentId == upperId
                })],
                active: false
            }
        })
    },
    sortCategories: (state, getters) => {
        let allproduct = "all product";
        let categoriesArray = []
        for (let i = 0; i < getters.setCategories.length; i++) {
            categoriesArray.push(getters.setCategories[i].category)
            for (let j = 0; j < getters.setCategories[i].child.length; j++) {
                categoriesArray.push(getters.setCategories[i].child[j][0].category)
            }
        }
        return categoriesArray = [allproduct].concat(categoriesArray);
    },
}

const actions = {
    async loadProducts({
        commit
    }) {
        axios
            .get(get_products_list)
            .then(res => {
                let products = res.data.filter((product)=>{return product.active})
                commit('SET_PRODUCTS', products)
            })
            .catch(error => {
                console.log(error)
            })

    },
    async loadcategories({
        commit
    }) {
        axios
            .get(get_categories)
            .then(data => {
                let categories = data.data
                commit('SET_CATEGORIES', categories)
            })
            .catch(error => {
                console.log(error)
            })
    },
    async loadProductsByCategory({
        commit
    }, category) {
        axios
            .get(`${get_by_category}/${category}`)
            .then(res => {
                let products = res.data.filter((product)=>{return product.active})
                commit('SET_PRODUCTS', products)
            })
            .catch(error => {
                console.log(error)
            })
    },
    // async loadProductForSearch({
    //     commit
    // }) {
    //     axios
    //         .get(`${get_products_list}`)
    //         .then(res => {
    //             let products = res.data
    //             commit('SET_FOR_SEARCH_PRODUCTS', products)
    //         })
    //         .catch(error => {
    //             console.log(error)
    //         })
    // },
}


const mutations = {
    SET_PRODUCTS(state, payload) {
        state.products = payload
    },
    SET_CATEGORIES(state, payload) {
        state.categories = payload
    },
    // SET_FOR_SEARCH_PRODUCTS(state, payload) {
    //     state.productForSearch = payload
    // },

}

export default {
    state,
    getters,
    actions,
    mutations
}