<template>
    <div>
        <h1 class="text-3xl font-semibold p-3 capitalize">address</h1>
        <hr />
        <div class="px-3 py-4">
            <div class="">
                <button type="button" class="px-4 py-2 text-white bg-primary rounded-md focus:bg-secondary focus:outline-none">+ add</button>
            </div>
            <p>first name :</p>
            <p>last name :</p>
            <p>phone :</p>
            <p>province :</p>
            <p>district :</p>
            <p>sub district :</p>
            <p>postal :</p>
            <p>address :</p>
        </div>
        <form @submit.prevent="submitForm" class="px-3 py-4 ">
            <div class="space-y-5">
                <div class="sm:flex items-center mb-2">
                    <label for="fname" class="font-semibold block sm:w-2/6 sm:text-right pr-2">first name :</label>
                    <input type="text" id="fname" maxlength="50" name="fname" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="lname" class="font-semibold block sm:w-2/6 sm:text-right pr-2">last name :</label>
                    <input type="text" id="lname" maxlength="50" name="lname" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="phone" class="font-semibold block sm:w-2/6 sm:text-right pr-2">phone :</label>
                    <input type="tel" id="phone" minlength="10" maxlength="10" pattern="[0]{1}[0-9]{9}" name="phone" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="province" class="font-semibold block sm:w-2/6 sm:text-right pr-2">province :</label>
                    <input type="text" id="province" maxlength="50" name="address-level1" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="city" class="font-semibold block sm:w-2/6 sm:text-right pr-2">district / city :</label>
                    <input type="text" id="city" maxlength="50" name="address-level2" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="suburb" class="font-semibold block sm:w-2/6 sm:text-right pr-2">Sub-district :</label>
                    <input type="text" id="suburb" maxlength="50" name="suburb" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="address" class="font-semibold block sm:w-2/6 sm:text-right pr-2">address :</label>
                    <input type="text" id="address" maxlength="50" name="address-line1" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
                <div class="sm:flex items-center mb-2">
                    <label for="postal" class="font-semibold block sm:w-2/6 sm:text-right pr-2">postal :</label>
                    <input type="text" id="postal" maxlength="50" name="postal-code" class="w-full sm:w-3/6 border px-3 py-2 border-gray-300 rounded-md focus:outline-none" />
                </div>
            </div>

            <div class="mt-7">
                <button type="submit" class="px-4 py-2 text-white bg-primary rounded-md focus:bg-secondary focus:outline-none">Save</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {};
    },
};
</script>

<style></style>
