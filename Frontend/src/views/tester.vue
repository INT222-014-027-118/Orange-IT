<template>
    <div class="mx-auto max-w-2xl">
        <div class="inline-block w-full ">
            <input placeholder="Search..." class="inline w-full px-3 py-2 h-9 bg-gray-50 text-sm rounded-md border focus:outline-none focus:shadow-outline border-gray-300" />
        </div>
        <div class="w-full bg-white">
            <div class="w-full flex p-1">
                <div class="flex flex-col w-1/3 bg-yellow-300" v-for="cat in categories" :key="cat">
                    <!-- <div v-for="big in cat.bigTitle" :key="big"> -->
                        {{ cat }}
                    <!-- </div> -->
                </div>
                <div class="flex flex-col w-1/3 bg-green-500" v-for="cat in categories" :key="cat">
                    <div class="" v-for="sub in cat.subTitle" :key="sub">
                        {{ sub }}
                    </div>
                </div>
                <div class="flex flex-col w-1/3 bg-red-500" v-for="cat in categories" :key="cat">
                    <div class="" v-for="third in cat.thirdTitle" :key="third">
                        {{ third }}
                    </div>
                </div>
            </div>
        </div>

        <form name="myform" id="myForm">
            Select Country:
            <select name="state" id="countySel" size="1">
                <option value="" selected="selected">Select Country</option>
            </select>
            <br />
            <br />
            Select State:
            <select name="countrya" id="stateSel" size="1">
                <option value="" selected="selected">Please select Country first</option>
            </select>
            <br />
            <br />
            Select District:
            <select name="district" id="districtSel" size="1">
                <option value="" selected="selected">Please select State first</option> </select
            ><br />
            <input type="submit" />
        </form>
    </div>
</template>

<script>
var stateObject = {
    India: { 
        Delhi: ["new Delhi", "North Delhi"],
        Kerala: ["Thiruvananthapuram", "Palakkad"], 
        Goa: ["North Goa", "South Goa"] 
    },
    Australia: {
        "South Australia": ["Dunstan", "Mitchell"],
        Victoria: ["Altona", "Euroa"],
    },
    Canada: {
        Alberta: ["Acadia", "Bighorn"],
        Columbia: ["Washington", ""],
    },
};
window.onload = function() {
    var countySel = document.getElementById("countySel"),
        stateSel = document.getElementById("stateSel"),
        districtSel = document.getElementById("districtSel");
    for (var country in stateObject) {
        countySel.options[countySel.options.length] = new Option(country, country);
    }
    countySel.onchange = function() {
        stateSel.length = 1; // remove all options bar first
        districtSel.length = 1; // remove all options bar first
        if (this.selectedIndex < 1) return; // done
        for (var state in stateObject[this.value]) {
            stateSel.options[stateSel.options.length] = new Option(state, state);
        }
    };
    countySel.onchange(); // reset in case page is reloaded
    stateSel.onchange = function() {
        districtSel.length = 1; // remove all options bar first
        if (this.selectedIndex < 1) return; // done
        var district = stateObject[countySel.value][this.value];
        for (var i = 0; i < district.length; i++) {
            districtSel.options[districtSel.options.length] = new Option(district[i], district[i]);
        }
    };
};

export default {
    data() {
        return {
            categories: {
                bigTitle1: {
                    bigTitle: ["1"],
                    subTitle: ["2", "3"],
                    thirdTitle: [],
                },
                bigTitle2: {
                    bigTitle: ["4"],
                    subTitle: ["5", "6", "7"],
                    thirdTitle: ["8", "9"],
                },
                bigTitle3: {
                    bigTitle: ["7"],
                    subTitle: [],
                    thirdTitle: [],
                },
            },
            stateObject: {
                India: {
                    Delhi: ["new Delhi", "North Delhi"],
                    Kerala: ["Thiruvananthapuram", "Palakkad"],
                    Goa: ["North Goa", "South Goa"],
                },
                Australia: {
                    "South Australia": ["Dunstan", "Mitchell"],
                    Victoria: ["Altona", "Euroa"],
                },
                Canada: {
                    Alberta: ["Acadia", "Bighorn"],
                    Columbia: ["Washington", ""],
                },
            },
        };
    },
};
</script>

<style></style>
