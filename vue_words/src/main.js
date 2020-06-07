import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import Mint from 'mint-ui';
import { Header } from 'mint-ui';
// import VueResource from 'vue-resource';

import Axios from 'axios';
Vue.prototype.$axios = Axios;
Axios.defaults.baseURL = '/api'
// Vue.use(VueResource);

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

import data from './data';

Vue.prototype.DATA=data;
Vue.component(Header.name, Header);
import 'mint-ui/lib/style.css'
import './assets/layui/css/layui.css'
Vue.use(Mint);
Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
