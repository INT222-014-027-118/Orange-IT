<template>
    <div class="bg-white">
        <h1 class="text-3xl font-semibold p-3 capitalize">purchase</h1>
        <hr />
        <div class="flex justify-between sm:justify-around overflow-x-auto">
            <button
                v-for="tab in tabs"
                :key="tab"
                @click="selectTab = tab"
                class="text-gray-600 text-xs md:text-base px-4 py-2 md:py-4 md:px-6 inline-block hover:text-primary focus:outline-none "
                :class="[selectTab.id == tab.id ? 'text-primary border-b-2 font-medium border-primary' : '']"
            >
                {{ tab.title }}
            </button>
        </div>
        <div></div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            tabs: [
                { id: 1, title: "all" },
                { id: 2, title: "to be delivered" },
                { id: 3, title: "to be receive" },
                { id: 4, title: "done" },
            ],
            selectTab: { id: 1, title: "all" },
        };
    },
};
</script>

<style></style>
