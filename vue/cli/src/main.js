import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

new Vue({
  el: '#app',
  render: h=>h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})
