import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import ElementUI from '../components/ElementUI.vue'
import Home from '../components/Home.vue'
import User from '../components/user/user.vue'
import Item from '../components/items/Item.vue'
import ItemCat from '../components/items/ItemCat.vue'
import addItem from '../components/items/addItem.vue'
import Rights from '../components/permission/Rights.vue'
//使用路由机制
Vue.use(VueRouter)
const routes = [{
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/elementUI',
    component: ElementUI
  },
  {
    path: '/home',
    component: Home,
    children: [{
        path: '/user',
        component: User
      },
      {
        path: '/item',
        component: Item
      },
      {
        path: '/itemCat',
        component: ItemCat
      },
      {
        path: '/item/addItem',
        component: addItem
      },
      {
        path: '/rights',
        component: Rights
      }
    ]
  }
  
]

//路由对象的定义
const router = new VueRouter({
  routes
})

//路由导航守卫!!!!!!!(拦截器)
router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    return next()
  }
  let token = window.sessionStorage.getItem("token")
  if (token !== null) {
    return next()
  }
  next('/login')

})

//路有对象的使用
export default router
