<template>
    <div class="p-1 sm:p-5">
        <div class="mx-auto bg-white dark:bg-dark_tertiary rounded-md shadow-sm max-w-7xl pb-1">
            <div class="py-4 border-b pb-5 flex flex-wrap justify-between bg-white dark:bg-dark_tertiary">
                <h1 class="text-2xl sm:text-3xl whitespace-nowrap p-2 font-semibold font-sans">Manage products</h1>

                <div class="items-center rounded-3xl inline-flex w-full sm:w-2/5 p-2">
                    <button class="w-full focus:outline-none">
                        <input
                            type="search"
                            v-model="searchText"
                            class="mx-0.5 pt-1 pb-1 pl-10 md:pl-10 rounded-2xl focus:outline-none w-full ring-2 ring-primary focus:bg-gray-50 dark:focus:bg-dark_secondary bg-gray-100 dark:bg-dark_secondary"
                        />
                    </button>
                    <button class="rounded-3xl px-3 absolute">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 0 24 24" width="24px" class="fill-current text-primary">
                            <path d="M0 0h24v24H0V0z" fill="none" />
                            <path
                                d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"
                            />
                        </svg>
                    </button>
                </div>

                <filter-products class="p-2 w-full sm:w-auto z-50" />

                <div class="p-2 w-full sm:w-auto">
                    <button @click="addNewProduct" class="bg-primary rounded-md px-3 py-1 text-white btn shadow flex items-center justify-center w-full">
                        <span class="material-icons-round text-lg">
                            add
                        </span>
                        <span>Add New Product</span>
                    </button>
                </div>
            </div>

            <div class="overflow-auto mx-1 shadow-inner border h-screen height-res" style="">
                <table class="border-collapse table-auto w-full whitespace-no-wrap table-striped relative">
                    <thead>
                        <tr class="text-left sticky top-0 z-10">
                            <th v-for="heading in headings" :key="heading.key" class="bg-secondary border-b border-gray-200 px-6 py-2 text-black font-bold tracking-wider uppercase text-xs whitespace-nowrap">
                                {{ heading.value }}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="product in products" :key="product.id">
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ product.id }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <img :src="`${this.api}/image/get/${product.images[0].source}`" class="object-cover object-center w-14 h-14 sm:w-14 sm:h-14 md:w-20 md:h-20" alt="Product image" />
                            </td>
                            <td class="border-dashed border-b border-gray-200 text-xs w-1/5">
                                <span class="px-6 py-3 flex items-center">{{ product.productName }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ product.brandName }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200 text-xs py-3">
                                <span class="px-6 flex items-center">{{ product.catergories[0].category }}</span>
                                <span v-if="product.catergories.length > 1" class="px-6 flex items-center">{{ product.catergories[1].category }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ product.price }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <div class="flex items-center justify-center">
                                    <span class="px-2 text-lg font-bold">{{ product.quantityStock }}</span>
                                    <button class="material-icons text-md p-1">add_circle_outline</button>
                                </div>
                            </td>
                            <td class="border-dashed border-b border-gray-200 text-center">
                                <label class="switch shadow-sm">
                                    <input type="checkbox" :checked="product.active" @click="changeActiveProduct(product.id)" />
                                    <span class="slider"></span>
                                </label>
                            </td>
                            <td class="border-dashed border-b border-gray-200 text-center space-x-3">
                                <button class="material-icons text-yellow-500" @click="editProduct(product.id)">
                                    edit
                                </button>
                                <button class="material-icons text-red-500" @click="deleteProduct(product.id, product.productName)">
                                    delete
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import FilterProducts from "../../components/admin/FilterProducts.vue";
import axios from "axios";

export default {
    components: {
        FilterProducts,
    },
    data() {
        return {
            api: process.env.VUE_APP_API,
            headings: [
                {
                    key: "id",
                    value: "Id",
                },
                {
                    key: "img",
                    value: "Image",
                },
                {
                    key: "productName",
                    value: "Product name",
                },
                {
                    key: "brandName",
                    value: "Brand name",
                },
                {
                    key: "categories",
                    value: "Categories",
                },
                {
                    key: "price",
                    value: "Price",
                },
                {
                    key: "quantityStock",
                    value: "Stock",
                },
                {
                    key: "active",
                    value: "Active",
                },
                {
                    key: "",
                    value: "",
                },
            ],
            searchText: "",
        };
    },
    computed: {
        products() {
            if (this.searchText.length > 0) {
                return this.$store.getters.products.filter((product) => {
                    return product.productName.toLowerCase().includes(this.searchText.toLowerCase());
                });
            }
            return this.$store.getters.products;
        },
    },
    methods: {
        addNewProduct() {
            // this.$router.push({ name: 'form' })
            // this.$router.push('/admin/form')
            this.$router.push(`/admin/add-product`);
        },
        deleteProduct(id, productName) {
            if (window.confirm("Do you want to delete? \nproduct:   " + productName)) {
                this.$store.dispatch("deleteProduct", id);
            }
        },
        editProduct(id) {
            this.$router.push({ name: "Form", params: { formPath: "edit", productId: id } });
        },
        changeActiveProduct(id) {
            const change_active = `${process.env.VUE_APP_API}/product/changeActive/${id}`;
            let config = {
                method: "put",
                url: change_active,
                headers: {
                    Authorization: this.$store.getters.token,
                },
            };
            axios(config)
        },
    },
    created() {
        this.$store.dispatch("loadProducts");
    },
};
</script>

<style>
.switch {
    position: relative;
    display: inline-block;
    width: 30px;
    height: 17px;
}

.switch input {
    opacity: 0;
    width: 0;
    height: 0;
}

.slider {
    position: absolute;
    cursor: pointer;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: #ccc;
    -webkit-transition: 0.4s;
    transition: 0.4s;
}

.slider:before {
    position: absolute;
    content: "";
    height: 13px;
    width: 13px;
    left: 2px;
    bottom: 2px;
    background-color: white;
    -webkit-transition: 0.4s;
    transition: 0.4s;
}

input:checked + .slider {
    background-color: #4e9f3d;
}

input:checked + .slider:before {
    -webkit-transform: translateX(13px);
    -ms-transform: translateX(13px);
    transform: translateX(13px);
}

/* Rounded sliders */
.slider.round {
    border-radius: 17px;
}

.slider.round:before {
    border-radius: 50%;
}
.height-res {
    height: 80vh;
}

@media screen and (max-width: 640px) {
    .height-res {
        height: 70vh;
    }
}
</style>
