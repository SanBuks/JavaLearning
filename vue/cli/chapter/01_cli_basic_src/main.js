import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

// import Vue from 'vue/dist/vue.js'
// 1. 用 template 指定结构需要引入完整 vue
// new Vue({
//   el: '#app',
//   template: `<h1>测试脚手架</h1>`,  // 需要模板解析器
// })

// import Vue from 'vue'
// 2. 一般开发需要使用 render 函数进行模板解析
// new Vue({
//   el: '#app',
//   // 创建 VM 时, 自动调用 render, 函数参数 createElement 用来解析模板创建 VNode
//   render(createElement) {
//     return createElement("h1", "title-content") // 返回一个 VNode
//   }
// })

// 3. 简写形式, 解析根组件
new Vue({
  el: '#app',
  render: h=>h(App)
// render: h => h("h1", "title-content"),
})
