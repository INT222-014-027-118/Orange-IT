import axios from "axios"
const api = process.env.VUE_APP_API
const get_users_list = `${api}/user/list`
// const change_active = `${api}/product/changeActive/`

const state = {
    menuList: [{
            label: "Manage product",
            active: false,
            link: "manageProducts",
            icon: "warehouse"
        },
        {
            label: "Manage users",
            active: false,
            link: "manageUsers",
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
}

const actions = {
    chooseMenu({
        commit
    }, index) {
        commit('activeMenu', index)
    },
    loadUsers() {
        axios.get(get_users_list, {
            headers: {
                'Authorization': this.getters.token
            }
        }).then((res) => {
            console.log(res);
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


}

export default {
    state,
    getters,
    actions,
    mutations
}