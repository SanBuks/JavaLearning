import Vue from 'vue/dist/vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  el: '#app',
  template: `<h1>不过了</h1>`,

  // 自动调用 render, 通过参数解析 template
  // render(createElement) {
  //   console.log(createElement)
  //   return createElement("h1", "不过了")
  // }

  // 简写形式
  render: h => h("h1", "不过了"),

  // render: h => h(App),
})
