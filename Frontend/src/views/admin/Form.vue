<template>
    <div class="px-2 md:px-5 py-2 md:py-5">
        <div class="mx-auto sm:max-w-5xl px-3 lg:px-6 bg-white dark:bg-dark_tertiary rounded-md shadow-sm">
            <h1 class="text-2xl sm:text-3xl whitespace-nowrap px-2 py-6 font-semibold font-sans capitalize">{{ formPath }} products {{ productId == undefined ? "" : "ID:" + productId }}</h1>
            <hr />
            <form @submit.prevent="submitForm" class="py-5">
                <div class="px-1 md:px-3">
                    <label class="label-css" for="grid-state">Category *</label>
                    <div class="input-form input-theme" ref="categories" :class="[invalid.categories ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']">
                        <div class="w-full flex text-xs p-2">
                            <p class="w-1/3">category</p>
                            <p class="hidden md:inline-block w-1/3">sub category</p>
                        </div>
                        <div class="bg-white dark:bg-gray-700 border w-full shadow-md flex flex-col md:flex-row mb-5 rounded-md h-full overflow-y-auto">
                            <div class="flex flex-col bg-white dark:bg-gray-700 w-full md:w-1/3 p-1 border-b border-r">
                                <div
                                    class="px-2 py-1 cursor-pointer flex justify-between rounded-sm"
                                    :class="[selectRootCat.category === category.category ? 'bg-primary text-white hover:bg-primaryfocus hover:text-white' : 'hover:bg-gray-200 dark:hover:bg-dark_tertiary']"
                                    v-for="category in $store.getters.rootCategories"
                                    :key="category.id"
                                    @click="chooseRootCategory(category)"
                                >
                                    <span>{{ category.category }}</span>
                                    <span class="material-icons " v-if="selectRootCat.category === category.category"> navigate_next </span>
                                </div>
                            </div>
                            <div class="flex flex-col bg-white dark:bg-gray-700 w-full md:w-1/3 p-1 pt-6 md:p-1 border-b border-r relative">
                                <span class="absolute top-0 text-xs md:hidden whitespace-nowrap">sub category</span>
                                <div
                                    class="px-2 py-1 cursor-pointer flex justify-between rounded-sm"
                                    :class="[selectChildCat.category === childcat.category ? 'bg-primary text-white hover:bg-primaryfocus hover:text-white' : 'hover:bg-gray-200 dark:hover:bg-dark_tertiary']"
                                    v-for="childcat in $store.getters.childCategories(`${this.selectRootCat.id}`)"
                                    :key="childcat.id"
                                    @click="chooseSubCategory(childcat)"
                                >
                                    <span> {{ childcat.category }}</span>
                                    <span class="material-icons " v-if="selectChildCat.category === childcat.category"> check </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="px-1 md:px-3 lg:w-full">
                    <label class="label-css" for="brand">Brand *</label>
                    <select class="input-form input-theme" id="brandName" v-model="product.brandName" :class="[invalid.brandName ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']">
                        <option value="" disabled selected>[ Select Brand ]</option>
                        <option v-for="brand in $store.getters.brands" :key="brand" :value="brand" class="text-lg">{{ brand }}</option>
                    </select>
                </div>

                <div class="px-1 md:px-3 lg:w-full">
                    <div class="flex justify-between">
                        <label class="label-css" for="" ref="name">Product Name *</label>
                        <p class="label-css">{{ countProductName }}/100</p>
                    </div>
                    <input
                        v-model="product.productName"
                        class="input-form input-theme"
                        id=""
                        type="text"
                        placeholder="Please input product name"
                        maxlength="100"
                        :class="[invalid.productName ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']"
                    />
                    <!-- <span v-if="invalid.name" class="absolute font-mono text-sm text-red-500 transform select-none -bottom-3 left-3 sm:bottom-2 sm:left-1/2 sm:-translate-x-1/2">Please input product name</span> -->
                </div>

                <div class="flex flex-col lg:flex-row lg:w-full">
                    <div class="relative px-1 md:px-3 w-full lg:w-1/2">
                        <div class="flex justify-between">
                            <label class="label-css" for="price">Price *</label>
                            <span v-if="!invalid.price" class="text-sm text-red-500 select-none">Please input price</span>
                        </div>
                        <input
                            v-model.number="product.price"
                            step="0.00"
                            class="input-form input-theme"
                            id="price"
                            type="number"
                            placeholder=""
                            min="0"
                            max="99999"
                            :class="[invalid.price ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']"
                        />
                    </div>

                    <div class="relative px-1 md:px-3 w-full lg:w-1/2">
                        <div class="flex justify-between">
                            <label class="label-css" for="stock">quantity stock *</label>
                            <span v-if="!invalid.quantityStock" class="text-sm text-red-500 select-none ">Please input stock quantity</span>
                        </div>
                        <input
                            v-model.number="product.quantityStock"
                            step="1"
                            class="input-form input-theme"
                            id="stock"
                            type="number"
                            placeholder=""
                            min="0"
                            max="9999"
                            :class="[invalid.quantityStock ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']"
                        />
                    </div>
                </div>

                <div class="relative px-1 md:px-3 lg:w-full">
                    <label class="label-css">color *</label>
                    <div class="input-form input-theme flex flex-wrap" :class="[invalid.colors ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']">
                        <label :for="color.id" v-for="color in $store.getters.colors" :key="color.id" class="flex flex-col items-center cursor-pointer">
                            <input
                                :id="color.id"
                                type="checkbox"
                                name="color"
                                class="w-6 h-6 md:w-8 md:h-8 my-2 mx-5 cursor-pointer rounded-full border-1 form-checkbox ring-transparent ring ring-offset-2 ring-offset-white dark:ring-offset-dark_tertiary focus:border-gray-500 focus:ring focus:ring-offset-2 focus:ring-secondary active:ring-secondary checked:ring-primary"
                                :style="{
                                    backgroundColor: `#${color.hexCode}`,
                                }"
                                :value="color"
                                v-model="product.colors"
                            />
                            <span class="text-sm">{{ color.label }}</span>
                        </label>
                    </div>
                    <!-- <span v-if="invalid.Color" class="absolute font-mono text-sm text-red-500 transform select-none -bottom-6 left-3 sm:-bottom-1 sm:left-1/2 sm:-translate-x-1/2"
                        >Please select product color</span
                    > -->
                </div>

                <div class="px-1 md:px-3 lg:w-full">
                    <label class="label-css">Upload Image *</label>
                    <div class="relative input-form input-theme flex flex-wrap overflow-hidden" :class="[invalid.images ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']">
                        <div v-for="(item, index) in preview_list" :key="index" class="m-2 md:m-5 relative ring-1 ring-primary">
                            <div class="bg-white h-40 w-40 md:h-64 md:w-64 rounded-md">
                                <img :src="item" class="object-contain object-center w-full h-full rounded-md" />
                            </div>
                            <!-- <p class="text-sm font-light truncate w-40 md:w-64 cursor-text px-3 py-3" v-if="formPath !== 'edit'">file name: {{ imageInfo[index].name }}</p>
                            <p class="text-sm font-light truncate w-40 md:w-64 cursor-text px-3 py-3" v-else>
                                file name:
                                {{ typeof item.split("http://20.212.33.246/orange-it/image/get/")[1] == "string" ? item.split("http://20.212.33.246/orange-it/image/get/")[1] : imageInfo[index].name }}
                            </p> -->
                            <!-- <p class="text-sm font-light truncate w-40 md:w-64 cursor-text">size: {{ imageInfo[index].size / 1024 }}KB</p> -->
                            <div
                                @click="deleteImg(index)"
                                class="bg-red-600 absolute text-center pt-0.5 cursor-pointer -top-3 -right-3 sm:-right-2 md:-right-3 text-base md:text-xl rounded-full h-7 w-7 md:h-8 md:w-8 material-icons text-white"
                            >
                                delete_forever
                            </div>
                        </div>
                        <div class="self-start m-2 md:m-5" v-show="preview_list.length < 6">
                            <label
                                class="h-40 w-40 md:h-64 md:w-64 flex flex-col items-center px-4 justify-center bg-white dark:bg-gray-700 rounded-md shadow-md tracking-wide uppercase border dark:border-gray-500 cursor-pointer transition hover:bg-primary dark:hover:bg-primary hover:text-white"
                                form="file"
                            >
                                <span class="material-icons p-0.5 rounded-full border-2 border-current">
                                    add
                                </span>
                                <span class="mt-2 text-base">Select a file</span>
                                <input type="file" class="hidden" id="file" accept="image/x-png,image/gif,image/jpeg" @change="previewMultiImage" multiple />
                            </label>
                        </div>
                    </div>
                </div>

                <div class="px-1 md:px-3 lg:w-full rounded-md">
                    <label class="label-css">attribute *</label>
                    <div class="input-form input-theme" :class="[invalid.productsHasAttributes ? '' : 'ring-2 ring-opacity-60 border border-red-500 ring-red-500']">
                        <div class="grid grid-cols-2 grid-rows-2">
                            <div class="col-span-2 lg:col-span-1 p-1">
                                <h2 class="text-center font-semibold relative">Attribute</h2>
                                <RichSelect class="" @selectAttribute="selectAttribute" ref="childComponent" />
                            </div>
                            <div class="col-span-2 lg:col-span-1 p-1">
                                <h2 class="text-center font-semibold">Value</h2>
                                <input class="input-theme " type="text" maxlength="40" placeholder="Please input value" v-model="attributeValue" />
                            </div>
                            <div class="col-span-2 p-1 mt-3">
                                <button
                                    type="button"
                                    class="bg-green-600 text-white select-none px-3 py-2 text-center w-full rounded hover:shadow-md hover:bg-green-700 cursor-pointer flex justify-center"
                                    @click="Addattribute()"
                                >
                                    <span class="material-icons text-base mr-1">add</span> <span>Add</span>
                                </button>
                            </div>
                        </div>
                        <table class="w-full">
                            <thead>
                                <tr>
                                    <th class="w-5/12"></th>
                                    <th class="w-5/12"></th>
                                    <th class="w-2/12"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-show="1 !== 0">
                                    <td colspan="3" class="font-semibold p-2">Attributes list</td>
                                </tr>
                                <tr v-for="(att, index) in product.productsHasAttributes" :key="att.key" :class="index % 2 == 0 ? 'bg-white' : 'bg-gray-50'" class="border">
                                    <td class="">
                                        <p class="px-3 py-2">{{ att.attributeName }}</p>
                                    </td>
                                    <td class="">
                                        <p class="px-3 py-2">{{ att.attribute_value }}</p>
                                    </td>
                                    <td class="p-1">
                                        <div
                                            class="bg-red-500 text-white select-none block px-3 py-2 text-center w-full rounded hover:shadow-md hover:bg-red-600 cursor-pointer"
                                            @click="removeAddattribute(index)"
                                        >
                                            remove
                                        </div>
                                    </td>
                                </tr>
                                <br />
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="relative px-1 md:px-3 lg:w-full">
                    <div class="flex justify-between">
                        <label class="label-css" for="description">Description</label>
                        <p class="label-css">{{ countDescription }}/1000</p>
                    </div>
                    <textarea class="input-form input-theme vertical" id="description" v-model="product.description" type="text" placeholder="Please enter text up to 1000 characters." maxlength="1000" />
                </div>
                <button type="submit" class="self-end rounded shadow-md cursor-pointer btn text-lg py-3 px-6 mx-1 sm:mx-3 mb-3 w-full max-w-xs">
                    Submit
                </button>
            </form>
        </div>
    </div>
    <!-- <div v-show="isLoad" class="flex items-center justify-center w-full h-screen">
        <i class="text-4xl material-icons animate-spin" v-show="isLoad"> autorenew </i>
    </div> -->
</template>

<script>
import RichSelect from "../../components/form/RichSelect.vue";
import axios from "axios";

export default {
    components: {
        RichSelect,
    },
    data() {
        return {
            api: `${process.env.VUE_APP_API}/product`,
            activeClose: true,
            productIds: [],

            selectRootCat: {},
            selectChildCat: {},

            product: {
                id: 1,
                productName: "",
                description: "",
                price: 0,
                brandName: "",
                quantityStock: 0,
                discount: null,
                colors: [],
                attributes: [],
                images: [],
                categories: [],
                productsHasAttributes: [],
            },

            selectAttributes: {},
            attributeValue: "",

            invalid: {
                productName: true,
                price: true,
                brandName: true,
                quantityStock: true,
                discount: true,
                colors: true,
                images: true,
                categories: true,
                productsHasAttributes: true,
            },

            isLoad: true,
            imageFile: null,

            preview_list: [],
            imageInfo: [],
        };
    },
    props: {
        itemId: String,
        formPath: String,
        productId: String,
    },
    methods: {
        submitForm() {
            this.invalid.quantityStock = this.product.quantityStock === 0 ? false : true;
            this.invalid.colors = this.product.colors.length === 0 ? false : true;
            // this.invalid.images = this.imageInfo.length === 0 || this.imageInfo.length >= 7 ? false : true;
            this.invalid.productName = this.product.productName === "" ? false : true;
            this.invalid.productsHasAttributes = this.product.productsHasAttributes.length === 0 ? false : true;
            this.invalid.price = this.product.price === 0 ? false : true;
            this.invalid.brandName = this.product.brandName === "" ? false : true;
            this.invalid.categories = Object.keys(this.selectChildCat).length === 0 ? false : true;

            if (this.validate) {
                this.product.categories = [this.selectRootCat, this.selectChildCat];
                this.product.productsHasAttributes = this.product.productsHasAttributes.map((att) => {
                    return { id: att.id, attributeId: att.attributeId, productId: att.productId, attribute_value: att.attribute_value };
                });
                if (this.formPath === "edit") {
                    // this.$store.dispatch("uploadImages", this.imageInfo).then((response) => {
                    //     if (response.status == 200) {
                    this.product.id = this.productId;
                    this.product.attributes = [];
                    this.$store.dispatch("updateProduct", this.product);
                    console.log(this.product);
                    this.resetForm();
                    //     }
                    // });
                } else {
                    var imagesArray = this.imageInfo.map((image) => {
                        return { id: 1, source: image.name, label: image.name.split(".")[0], product_id: 1 };
                    });
                    this.product.images = imagesArray;
                    this.$store.dispatch("uploadImages", this.imageInfo).then((response) => {
                        if (response.status == 200) {
                            this.$store.dispatch("addProduct", this.product);
                            // console.log(this.product);
                        }
                    });
                }
            }
        },
        chooseRootCategory(category) {
            this.selectRootCat = category;
            this.selectChildCat = {};
        },
        chooseSubCategory(category) {
            this.selectChildCat = category;
        },
        selectAttribute(choosed) {
            this.selectAttributes = {
                id: choosed.id,
                attribute: choosed.attribute,
            };
        },

        Addattribute() {
            if (Object.keys(this.selectAttributes).length !== 0 && this.attributeValue !== "") {
                let attributeValue = {
                    id: 1,
                    attributeId: this.selectAttributes.id,
                    attributeName: this.selectAttributes.attribute,
                    productId: 1,
                    attribute_value: this.attributeValue,
                };
                this.product.productsHasAttributes.push(attributeValue);
                this.attributeValue = "";
                this.selectAttributes = {};
                this.$refs.childComponent.clearSelected();
            }
        },

        removeAddattribute(index) {
            this.product.productsHasAttributes.splice(index, 1);
        },

        previewMultiImage(event) {
            let imgName = event.target.files[0].name;
            var input = event.target;
            var count = input.files.length;
            var index = 0;
            if (imgName.length > 30) {
                alert("The file name cannot exceed 30 characters.!!!");
            } else if (input.files) {
                while (count--) {
                    var reader = new FileReader();
                    reader.onload = (e) => {
                        this.preview_list.push(e.target.result);
                    };
                    this.imageInfo.push(input.files[index]);
                    reader.readAsDataURL(input.files[index]);
                    index++;
                }
            }
        },

        deleteImg(index) {
            this.imageInfo.splice(index, 1); //?????????????????????????????????????????
            this.preview_list.splice(index, 1);
        },

        resetForm() {
            this.selectRootCat = {};
            this.selectChildCat = {};
            this.product.id = "";
            this.product.productName = "";
            this.product.description = "";
            this.product.price = 0;
            this.product.brandName = "";
            this.product.quantityStock = 0;
            this.product.colors = [];
            this.product.images = [];
            this.product.productsHasAttributes = [];
        },
    },
    computed: {
        countProductName() {
            return this.product.productName.length;
        },
        countDescription() {
            return this.product.description.length;
        },
        validate() {
            return (
                this.product.quantityStock !== 0 &&
                this.product.colors.length !== 0 &&
                // this.product.images !== 0 &&
                this.product.productName !== "" &&
                this.product.productsHasAttributes.length !== 0 &&
                this.product.price !== 0 &&
                this.product.brandName !== "" &&
                Object.keys(this.selectChildCat).length !== 0
            );
        },
    },
    mounted() {},
    async created() {
        this.$store.dispatch("loadDataForm");
        this.$store.dispatch("loadcategories");
        this.$store.dispatch("loadAttirbute");
        if (this.formPath === "edit") {
            let loadProduct = await axios.get(`${this.api}/${this.productId}`).then((res) => {
                return res.data;
            });
            this.selectRootCat = loadProduct.categories[0] == null ? "" : loadProduct.categories[0];
            this.selectChildCat = loadProduct.categories[1] == null ? "" : loadProduct.categories[1];
            this.preview_list = await loadProduct.images.map((img) => {
                return `${process.env.VUE_APP_API}/image/get/${img.source}`;
            });
            this.product.id = loadProduct.id;
            this.product.productName = loadProduct.productName;
            this.product.description = loadProduct.description;
            this.product.price = loadProduct.price;
            this.product.brandName = loadProduct.brandName;
            this.product.quantityStock = loadProduct.quantityStock;
            this.product.colors = loadProduct.colors;
            // this.product.images = loadProduct.images;
            this.product.productsHasAttributes = loadProduct.productsHasAttributes.map((att) => {
                return {
                    id: att.id,
                    attributeId: att.attributeId,
                    attributeName: loadProduct.attributes.find((attribute) => {
                        return attribute.id == att.attributeId;
                    }).attribute,
                    productId: att.productId,
                    attribute_value: att.attribute_value,
                };
            });
        }
    },
};
</script>

<style scoped>
[type="checkbox"]:checked {
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='rgb(256, 256, 256)' stroke-width='5%' stroke-linecap='round' stroke='rgb(236, 105, 7)' xmlns='http://www.w3.org/2000/svg'%3e%3cpath d='M12.207 4.793a1 1 0 010 1.414l-5 5a1 1 0 01-1.414 0l-2-2a1 1 0 011.414-1.414L6.5 9.086l4.293-4.293a1 1 0 011.414 0z'/%3e%3c/svg%3e");
}

[type="radio"]:checked {
    background-image: url("data:image/svg+xml,%3csvg viewBox='0 0 16 16' fill='red' xmlns='http://www.w3.org/2000/svg'%3e%3ccircle cx='8' cy='8' r='3'/%3e%3c/svg%3e");
}

textarea.vertical {
    resize: vertical;
    min-height: 10rem;
}
</style>
