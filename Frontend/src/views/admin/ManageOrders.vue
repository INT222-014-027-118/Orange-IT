<template> 

<div>
    {{order}}
</div>
</template>

<script>
export default {
    data() {
        return {
            order: [
                {
                    id: 10,
                    quantity: 1,
                    price: 6490.0,
                    product: {
                        id: 4,
                        productName: "RAZER BLACKSHARK V2 PRO",
                        images: [
                            {
                                id: 14,
                                source: "BlackSharkV2Pro1.png",
                                label: "BlackSharkV2Pro1",
                                productId: 4,
                            },
                            {
                                id: 15,
                                source: "BlackSharkV2Pro2.png",
                                label: "BlackSharkV2Pro2",
                                productId: 4,
                            },
                            {
                                id: 16,
                                source: "BlackSharkV2Pro3.jpg",
                                label: "BlackSharkV2Pro2",
                                productId: 4,
                            },
                            {
                                id: 17,
                                source: "BlackSharkV2Pro4.jpg",
                                label: "BlackSharkV2Pro2",
                                productId: 4,
                            },
                            {
                                id: 18,
                                source: "BlackSharkV2Pro5.jpg",
                                label: "BlackSharkV2Pro2",
                                productId: 4,
                            },
                        ],
                    },
                    orderId: 9,
                    color: {
                        id: 2,
                        label: "Black",
                        hexCode: "000000",
                    },
                },
                {
                    id: 11,
                    quantity: 1,
                    price: 5990.0,
                    product: {
                        id: 2,
                        productName: "RAZER HUNTSMAN ELITE",
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
                    },
                    orderId: 9,
                    color: {
                        id: 3,
                        label: "White",
                        hexCode: "FFFFFF",
                    },
                },
            ],
        };
    },
};
</script>

<style></style>
