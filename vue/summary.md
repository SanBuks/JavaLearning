# CH01
- Vue 构建用户的渐进式开发框架 
  - 组件式编码
  - 声明式编码
  - 虚拟 DOM 实现响应式
  - Diff 算法 
- Vue Script 引入 
  - 生产版本和开发版本区别 
  - Vue.config.productionTip = false
- VM 实例声明, 插值语法(可以运用 vm 实例上所有属性), 指令语法
  - v-bind 
  - v-model
  - el/$mount 挂载到容器
  - data 两种写法: 配置/函数式

# CH02
- MVVM:
  - model: js-object (data)
  - view: dom (template)
  - view-model: vm 实例 (data-binding, dom-listener)
- 数据代理: 
  - Object.defineProperty 
  - _data, vm 对 _data 的数据代理, _data 对 data 的数据劫持
- 事件:
  - v-on:click
  - methods 配置项
  - .prevent 防止默认事件
- 事件修饰符:
  - prevent
  - stop
  - once
  - capture
  - self
  - passive
- 按键事件: 
  - keyup/keydown
  - 常用 key, 按键组合

# CH03
- 数据改变, 需要重新解析模板, 在此期间会重新调用插值语法中的方法产生效率问题
- 计算属性, 缓存机制, 调用时机
- 

# 技术栈
- html/css
- js BOM/DOM/advance/es6
- ajax/promise/axios
- vue/react/angular
