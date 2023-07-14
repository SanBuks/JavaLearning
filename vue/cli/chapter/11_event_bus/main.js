import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

// x 是事件总线对象
// let Event = Vue.extend({})
// Vue.prototype.x = new Event()

new Vue({
  el: '#app',
  render: h=>h(App),
  beforeCreate() {
    // Vue 原型上有一个 事件总线对象为自身 vm 实例
    Vue.prototype.$bus = this
  }
})
