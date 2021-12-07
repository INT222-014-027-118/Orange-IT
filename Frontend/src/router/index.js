import {
  createRouter,
  createWebHistory
} from 'vue-router'
import Home from '../views/Home.vue'
import store from '../store'

const routes = [{
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import( /* webpackChunkName: "admin" */ '../views/admin/AdminHome.vue'),
    beforeEnter: (to, from, next) => {
      if (store.getters['isAdmin']) {
        next()
      } else {
        next('/')
      }
    },
    children: [{
        path: ':manage-products',
        name: 'ManageProducts',
        component: () => import( /* webpackChunkName: "manageProducts" */ '../views/admin/ManageProducts.vue')
      },
      {
        path: ':manage-users',
        name: 'ManageUsers',
        component: () => import( /* webpackChunkName: "manageUsers" */ '../views/admin/ManageUsers.vue')
      },
      {
        path: ':manage-orders',
        name: 'ManageOrders',
        component: () => import( /* webpackChunkName: "manageOrders" */ '../views/admin/ManageOrders.vue'),
      },
      {
        path: ':formPath-product',
        name: 'Form',
        props: true,
        component: () => import( /* webpackChunkName: "form" */ '../views/admin/Form.vue'),
      },
    ],
  },
  {
    path: '/compare',
    name: 'Compare',
    component: () => import( /* webpackChunkName: "compare" */ '../views/Compare.vue')
  },
  {
    path: '/cart',
    name: 'Cart',
    component: () => import( /* webpackChunkName: "cart" */ '../views/cart/Cart.vue')
  },
  {
    path: '/checkout',
    name: 'Checkout',
    component: () => import( /* webpackChunkName: "checkout" */ '../views/cart/Checkout.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import( /* webpackChunkName: "login" */ '../views/Login.vue')
  },
  {
    path: '/user',
    name: 'Account',
    component: () => import( /* webpackChunkName: "Account" */ '../views/userAccount/Account.vue'),
    props: true,
    children: [{
        path: ':purchaseDetail',
        name: 'purchase',
        props: true,
        component: () => import( /* webpackChunkName: "purchase" */ '../views/userAccount/Purchase.vue')
      },
      {
        path: ':manage/edit',
        name: 'manageProfile',
        props: true,
        component: () => import( /* webpackChunkName: "manageProfile" */ '../views/userAccount/ManageProfile.vue')
      },
      {
        path: ':address-info',
        name: 'Address',
        props: true,
        component: () => import( /* webpackChunkName: "Address" */ '../views/userAccount/Address.vue')
      },
    ],
  },
  {
    path: '/review',
    name: 'Review',
    component: () => import( /* webpackChunkName: "Review" */ '../views/userAccount/WriteReview.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import( /* webpackChunkName: "register" */ '../views/Register.vue')
  },
  {
    path: '/:productId-:productName',
    name: 'Product',
    props: true,
    component: () => import( /* webpackChunkName: "Product" */ '../views/Product.vue')
  },
  {
    path: '/result=:currentCategoryName',
    name: 'resultProducts',
    props: true,
    component: () => import( /* webpackChunkName: "resultProducts" */ '../views/ResultProductsPage.vue')
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router