import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
import Home from './components/Home'
import Like from './components/Like'
import All from './components/All'
import Mine from './components/Mine'
import History from './components/History'

const router =new VueRouter({
    routes:[
        {path:'/home',component:Home},
        {path: '/like',component:Like},
        {path: '/all',component:All},
        {path: '/mine',component:Mine},
        {path: '/his',component:History},
        {path: '',component: Home}
    ]
});

export default router

