<template>
    <div class="p-1 mx-auto max-w-7xl sm:mt-5">
        <div class="p-1 md:mt-0 md:p-5 capitalize">
            <h1 class="py-3 text-2xl font-semibold">compare products</h1>
            <!-- <div class="overflow-auto max-w-7xl"> -->
            <!-- component -->
            <div class="tscroll">
                <table class="rounded-md" style="width:99.8%;">
                    <thead>
                        <tr>
                            <th></th>
                            <th class="sm:whitespace-normal sm:w-2/5"></th>
                            <th class="sm:whitespace-normal sm:w-2/5"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm lg:mb-0">
                            <td class="table_content">
                                Product name
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">RAZER BLACKSHARK V2 PRO</p>
                            </td>
                            <td class="table_content">
                                <p class="w-60 md:w-auto font-semibold text-center">RAZER HUNTSMAN ELITE (LINEAR OPTICAL SWITCH) (EN/TH)</p>
                            </td>
                        </tr>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                <p class="whitespace-nowrap">image</p>
                            </td>
                            <td class="table_content">
                                <!-- <div class="min-w-max">
                                    <img src="http://20.205.201.136/orange-it/image/get/G913-1.png" class="object-cover object-center mx-auto w-60 select-none dark:bg-gray-400 " alt="Product image" />
                                </div>
                                <div>RAZER BLACKSHARK V2 PRO</div> -->
                                <div class="min-w-max self-start">
                                    <img
                                        src="http://20.205.201.136/orange-it/image/get/G913-1.png"
                                        class="w-36 h-36 md:w-60 md:h-60 mx-auto object-contain object-center rounded-md bg-white ring ring-white"
                                        alt="Product image"
                                    />
                                </div>
                            </td>
                            <td class="table_content">
                                <div class="min-w-max self-start">
                                    <img
                                        src="https://kanexkane.com/wp-content/uploads/2020/04/kkblog-cover-review-logitech-g-pro-x-keyboard.jpg"
                                        class="w-36 h-36 md:w-60 md:h-60 mx-auto object-contain object-center rounded-md bg-white ring ring-white"
                                        alt="Product image"
                                    />
                                </div>
                            </td>
                        </tr>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                price
                            </td>
                            <td class="table_content">
                                00000
                            </td>
                            <td class="table_content">
                                00000
                            </td>
                        </tr>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                <p class="whitespace-nowrap">colors</p>
                            </td>
                            <td class="table_content">
                                <div class="w-full flex flex-wrap">
                                    <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                        <div
                                            class="w-6 h-6 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                            :style="{
                                                backgroundColor: `#${color.hexCode}`,
                                            }"
                                        ></div>
                                        <span class="text-xs"> {{ color.label }}</span>
                                    </div>
                                </div>
                            </td>
                            <td class="table_content">
                                <div class="w-full flex flex-wrap">
                                    <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                        <div
                                            class="w-6 h-6 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                            :style="{
                                                backgroundColor: `#${color.hexCode}`,
                                            }"
                                        ></div>
                                        <span class="text-xs"> {{ color.label }}</span>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                spceType 1
                            </td>
                            <td class="table_content">
                                value2
                            </td>
                            <td class="table_content">
                                value2
                            </td>
                        </tr>
                        <tr class="mb-10 bg-white dark:bg-dark_tertiary shadow-sm">
                            <td class="table_content">
                                spceType 2
                            </td>
                            <td class="table_content">
                                value2
                            </td>
                            <td class="table_content">
                                value2
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- </div> -->
            <!-- <div class="flex flex-col overflow-x-auto">
                <div class="flex p-1">
                    <div class="w-1/5"></div>
                    <div class="text-sm w-full">
                        <h1 class="text-base font-semibold sm:text-2xl lg:text-4xl">product 1</h1>
                    </div>
                    <div class="text-sm w-full">
                        <h1 class="text-base font-semibold sm:text-2xl lg:text-4xl">product 2</h1>
                    </div>
                </div>
                <div class="flex sm:p-1">
                    <div class="w-1/5">image</div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <img
                            src="https://kanexkane.com/wp-content/uploads/2020/04/kkblog-cover-review-logitech-g-pro-x-keyboard.jpg"
                            class="object-cover object-center select-none dark:bg-gray-400 "
                            alt="Product image"
                        />
                    </div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <img
                            src="https://kanexkane.com/wp-content/uploads/2020/04/kkblog-cover-review-logitech-g-pro-x-keyboard.jpg"
                            class="object-cover object-center select-none dark:bg-gray-400 "
                            alt="Product image"
                        />
                    </div>
                </div>
                <div class="flex sm:p-1">
                    <div class="w-1/5">color</div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <div class="w-full flex flex-wrap py-1">
                            <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                <div
                                    class="w-4 h-4 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                    :style="{
                                        backgroundColor: `#${color.hexCode}`,
                                    }"
                                ></div>
                                <span class="text-xs"> {{ color.label }}</span>
                            </div>
                        </div>
                    </div>
                    <div class="w-2/5 p-0.5 sm:p-1 md:p-3 flex flex-col justify-center">
                        <div class="w-full flex flex-wrap py-1">
                            <div v-for="color in colors" :key="color" class="flex flex-col items-center">
                                <div
                                    class="w-4 h-4 md:w-8 md:h-8 my-1 md:my-2 mx-2 rounded-full"
                                    :style="{
                                        backgroundColor: `#${color.hexCode}`,
                                    }"
                                ></div>
                                <span class="text-xs"> {{ color.label }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
</template>

<script>
export default {
    components: {},
    data() {
        return {
            dumy: [],
            colors: [
                {
                    id: 1,
                    label: "Red",
                    hexCode: "ff0000",
                },
                {
                    id: 2,
                    label: "Black",
                    hexCode: "000000",
                },
                {
                    id: 6,
                    label: "Gray",
                    hexCode: "9AA0A8",
                },
            ],
            compareProducts: [
                {
                    id: 3,
                    productName: "RAZER DEATHADDER V2",
                    description: "- True 20,000 DPI Focus+ optical sensor\r\n- Up to 650 inches per second\n  (IPS) / 50 G acceleration / industry best 99.6% resolution accuracy",
                    price: 1990,
                    brandName: "Razer",
                    quantityStock: 20,
                    discount: null,
                    colors: [
                        {
                            id: 2,
                            label: "Black",
                            hexCode: "000000",
                        },
                    ],
                    attributes: [
                        {
                            id: 1,
                            attribute: "Wired/Wireless",
                        },
                    ],
                    images: [
                        {
                            id: 9,
                            source: "DeathAdder-V2-1.png",
                            label: "DeathAdder-V2-1",
                            productId: 3,
                        },
                        {
                            id: 10,
                            source: "DeathAdder-V2-2.png",
                            label: "DeathAdder-V2-2",
                            productId: 3,
                        },
                        {
                            id: 11,
                            source: "DeathAdder-V2-3.png",
                            label: "DeathAdder-V2-3",
                            productId: 3,
                        },
                        {
                            id: 12,
                            source: "DeathAdder-V2-4.png",
                            label: "DeathAdder-V2-4",
                            productId: 3,
                        },
                        {
                            id: 13,
                            source: "DeathAdder-V2-5.png",
                            label: "DeathAdder-V2-5",
                            productId: 3,
                        },
                    ],
                    categories: [
                        {
                            id: 2,
                            category: "Mouse",
                            parentId: null,
                        },
                    ],
                    productsHasAttributes: [
                        {
                            id: 11,
                            attributeId: 1,
                            productId: 3,
                            attribute_value: "Wired",
                        },
                    ],
                    ratings: [],
                },
                {
                    id: 2,
                    productName: "RAZER HUNTSMAN ELITE (LINEAR OPTICAL SWITCH) (EN/TH)",
                    description: "- Razerâ„¢ Opto-Mechanical \n  Switch with 45 G actuation force\r\n- 100 million keystroke lifespan\r\n- Chroma backlighting\n  with 16.8 million customizable color options",
                    price: 5990,
                    brandName: "Razer",
                    quantityStock: 40,
                    discount: null,
                    colors: [
                        {
                            id: 2,
                            label: "Black",
                            hexCode: "000000",
                        },
                        {
                            id: 3,
                            label: "White",
                            hexCode: "FFFFFF",
                        },
                    ],
                    attributes: [
                        {
                            id: 1,
                            attribute: "Wired/Wireless",
                        },
                        {
                            id: 2,
                            attribute: "Keyboard type",
                        },
                        {
                            id: 3,
                            attribute: "Keyboard size",
                        },
                        {
                            id: 4,
                            attribute: "Switch",
                        },
                        {
                            id: 5,
                            attribute: "Sound",
                        },
                    ],
                    images: [
                        {
                            id: 5,
                            source: "hmel-1-1.png",
                            label: "hmel-1-1",
                            productId: 2,
                        },
                        {
                            id: 6,
                            source: "hmel-1-2.png",
                            label: "hmel-1-2",
                            productId: 2,
                        },
                        {
                            id: 7,
                            source: "hmel-1-3.png",
                            label: "hmel-1-3",
                            productId: 2,
                        },
                        {
                            id: 8,
                            source: "hmel-1-4.png",
                            label: "hmel-1-4",
                            productId: 2,
                        },
                    ],
                    categories: [
                        {
                            id: 1,
                            category: "Keyboard",
                            parentId: null,
                        },
                    ],
                    productsHasAttributes: [
                        {
                            id: 6,
                            attributeId: 1,
                            productId: 2,
                            attribute_value: "Wired",
                        },
                        {
                            id: 7,
                            attributeId: 2,
                            productId: 2,
                            attribute_value: "Mecha-Optical",
                        },
                        {
                            id: 8,
                            attributeId: 3,
                            productId: 2,
                            attribute_value: "Full-Size",
                        },
                        {
                            id: 9,
                            attributeId: 4,
                            productId: 2,
                            attribute_value: "Razer",
                        },
                        {
                            id: 10,
                            attributeId: 5,
                            productId: 2,
                            attribute_value: "Clicky",
                        },
                    ],
                    ratings: [],
                },
            ],
        };
    },
    created() {
        // this.compareProducts = this.$store.getters.compareProducts;
        // console.log(this.compareProducts);
        // let cp = this.compareProducts;
        // console.log(cp[0]);
        // console.log(cp[1]);

        // let products1 = cp[0];
        // let products2 = cp[1];

        console.log(
            // cp.map((product) => {
            //     return { productname: ["name", product[0].name, product[1].name] };
            //     // product.id;
            // })
        );

        // console.log(Object.assign(cp));
        // console.log(Object.keys(products1.colors));
    },
};
</script>

<style>
.table_content {
    @apply p-2 md:p-3 border border-b dark:border-gray-500 ring-0;
}
.tscroll {
    width: 100%;
    overflow-x: scroll;
}

.tscroll table td:first-child {
    position: sticky;
    left: 0;
    background-color: white;
}

.dark .tscroll table td:first-child {
    background-color: #22303c;
}
</style>
