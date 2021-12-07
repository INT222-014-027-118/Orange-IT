<template>
    <div class="p-1 sm:p-5">
        <div class="mx-auto bg-white dark:bg-dark_tertiary rounded-md shadow-sm max-w-7xl pb-1">
            <div class="py-4 border-b pb-5 flex flex-wrap justify-between bg-white dark:bg-dark_tertiary">
                <h1 class="text-2xl sm:text-3xl whitespace-nowrap p-2 font-semibold font-sans">Manage orders</h1>

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
            </div>

            <div class="overflow-auto mx-1 shadow-inner border h-screen height-res" style="">
                <table class="border-collapse table-auto w-full whitespace-no-wrap table-striped relative">
                    <thead>
                        <tr class="text-left sticky top-0 z-10">
                            <th
                                v-for="heading in headings"
                                :key="heading.key"
                                class="bg-secondary border-b border-gray-200 px-6 py-2 text-black font-bold tracking-wider uppercase text-xs whitespace-nowrap"
                            >
                                {{ heading.value }}
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="order in orders" :key="order.id">
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.id }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.users.username }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.orderDate }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.status }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.shippings.companyShipping }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200 ">
                                <tracking-manage :order="order"/>
                            </td>
                            <td class="border-dashed border-b border-gray-200">
                                <span class="px-6 py-3 flex items-center">{{ order.shippings.status }}</span>
                            </td>
                            <td class="border-dashed border-b border-gray-200 text-center">
                                <div class="flex items-center justify-center mr-1">
                                    <button class="bg-blue-400 py-1 px-2 rounded text-xs font-semibold text-white shadow-md" @click="editProduct(product.id)">
                                        More
                                    </button>
                                    <!-- <button class="material-icons text-red-500" @click="deleteProduct(product.id, product.productName)">
                                        delete
                                    </button> -->
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- <div>
        {{ $store.getters.ordersForAdmin }}
    </div> -->
</template>

<script>
import TrackingManage from '../../components/admin/TrackingManage.vue'

export default {
    components: {
        TrackingManage,
    },
    data() {
        return {
            headings: [
                {
                    key: "id",
                    value: "Id",
                },
                {
                    key: "username",
                    value: "Username",
                },
                {
                    key: "orderDate",
                    value: "Date",
                },
                {
                    key: "status",
                    value: "Order status",
                },
                {
                    key: "companyShipping",
                    value: "Company Shipping",
                },
                {
                    key: "trackingNumber",
                    value: "Tracking number",
                },
                {
                    key: "shippings status",
                    value: "Shipping status",
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
        orders() {
            if (this.searchText.length > 0) {
                return this.$store.getters.ordersForAdmin.filter((order) => {
                    return order.users.username.toLowerCase().includes(this.searchText.toLowerCase()) || this.searchText == order.id;
                });
            }
            return this.$store.getters.ordersForAdmin;
        },
    },
    created() {
        this.$store.dispatch("loadOrdersForAdmin");
    },
};
</script>

<style></style>
