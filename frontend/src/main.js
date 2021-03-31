import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue';

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);

import ApiPlugin from './plugins/api'
Vue.use(ApiPlugin)

export const eventBus = new Vue();

new Vue({
  render: h => h(App),
}).$mount('#app')
