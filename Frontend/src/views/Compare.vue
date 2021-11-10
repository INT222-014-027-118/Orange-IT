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
                                spceType 3
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
        };
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
