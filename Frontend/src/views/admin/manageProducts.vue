<template>
    <div class="m-10 max-w-7xl bg-white rounded-md shadow-sm">
        <h1 class="text-3xl py-4 border-b m-5 font-semibold font-sans text-gray-700">Manage products</h1>
        <table class="border-collapse table-auto w-full whitespace-no-wrap bg-white table-striped relative">
            <thead>
                <tr class="text-left">
                    <th v-for="heading in headings" :key="heading.key" class="bg-secondary sticky top-0 border-b border-gray-200 px-6 py-2 text-black font-bold tracking-wider uppercase text-xs">
                        {{ heading.value }}
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="product in $store.getters.products" :key="product.id">
                    <td class="border-dashed border-t border-gray-200">
                        <span class="text-gray-700 px-6 py-3 flex items-center">{{ product.id }}</span>
                    </td>
                    <td class="border-dashed border-t border-gray-200">
                        <img :src="`${this.api}/image/get/${product.images[0].source}`" class="object-cover object-center w-14 h-14 sm:w-14 sm:h-14 md:w-20 md:h-20" alt="Product image" />
                    </td>
                    <td class="border-dashed border-t border-gray-200 text-xs">
                        <span class="text-gray-700 px-6 py-3 flex items-center">{{ product.productName }}</span>
                    </td>
                    <td class="border-dashed border-t border-gray-200">
                        <span class="text-gray-700 px-6 py-3 flex items-center">{{ product.brandName }}</span>
                    </td>
                    <td class="border-dashed border-t border-gray-200">
                        <span class="text-gray-700 px-6 py-3 flex items-center">{{ product.price }}</span>
                    </td>
                    <td class="border-dashed border-t border-gray-200 flex items-center">
                        <span class="text-gray-600 px-2 py-6 text-lg font-bold">{{ product.quantityStock }}</span>
                        <button class="material-icons text-md">
                            add_circle_outline
                        </button>
                    </td>
                    <td class="border-dashed border-t border-gray-200 text-center">
                        <label class="switch shadow-sm">
                            <input type="checkbox" checked />
                            <span class="slider"></span>
                        </label>
                    </td>
                    <td class="border-dashed border-t border-gray-200 text-center space-x-3">
                        <button class="material-icons text-yellow-500">
                            edit
                        </button>
                        <button class="material-icons text-red-500">
                            delete
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    components: {},
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
                    key: "feature",
                    value: "Feature",
                },
            ],
        };
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
</style>
