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
            label: "form product",
            active: false,
            link: "form-product",
            icon: "insert_drive_file"
        },
        
    ],

}

const getters = {
    menuList: state => state.menuList,
}

const actions = {
    chooseMenu({
        commit
    }, index) {
        commit('activeMenu', index)
    }

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
    }

}

export default {
    state,
    getters,
    actions,
    mutations
}