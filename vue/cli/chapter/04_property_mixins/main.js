import Vue from 'vue'
import App from './App.vue'
import {commonMounted} from '@/mixin'

Vue.config.productionTip = false
// 全局混合
Vue.mixin(commonMounted)

new Vue({
  el: '#app',
  render: h=>h(App)
})
