import axios from "axios"
const api = process.env.VUE_APP_API
const get_users_list = `${api}/user/list`
// const change_active = `${api}/product/changeActive/`

const state = {
    menuList: [{
            label: "Manage orders",
            active: false,
            link: ":manage-orders",
            icon: "shopping_basket"
        }, {
            label: "Manage product",
            active: false,
            link: "manage-products",
            icon: "warehouse"
        },
        {
            label: "Manage users",
            active: false,
            link: "manage-users",
            icon: "manage_accounts"
        },
        {
            label: "Form product",
            active: false,
            link: "add-product",
            icon: "insert_drive_file"
        },
    ],
    users: []


}

const getters = {
    menuList: state => state.menuList,
    users: state => state.users,
}

const actions = {
    chooseMenu({
        commit
    }, index) {
        commit('activeMenu', index)
    },
    loadUsers({
            commit
        }

    ) {
        axios.get(get_users_list, {
            headers: {
                'Authorization': this.getters.token
            }
        }).then((res) => {
            commit('SET_USERS', res.data)
        })
    },





}

const mutations = {
    activeMenu(state, index) {
        state.menuList[index].active = true
        for (let i = 0; i < state.menuList.length; i++) {
            if (i != index) {
                state.menuList[i].active = false
            }
        }
    },
    clearActiveMenu(state) {
        for (let i = 0; i < state.menuList.length; i++) {
            state.menuList[i].active = false
        }
    },
    SET_USERS(state, payload) {
        state.users = payload
    },

}

export default {
    state,
    getters,
    actions,
    mutations
}