import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

Vue.prototype.x = new Vue.extend({})

new Vue({
  el: '#app',
  render: h=>h(App)
})
