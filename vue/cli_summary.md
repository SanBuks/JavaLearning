# 01 脚手架
## 1.1 基础命令
- 配置镜像 `npm config set registry http://registry.npm.taobao.org`
- 全局安装 `npm install -g @vue/cli`
- 创建项目 `vue create xxx`
- 启动项目 `npm run serve`
- 打包项目 `npm run build`
- 查看版本 `npm view xxxx versions`
- 安装三方 `npm i xxx@version`

## 1.2 项目结构与加载流程
```
.文件目录
├── node_modules         第三方库
├── public
│ ├── favicon.ico        页签图标
│ └── index.html         主页面
├── src
│ ├── assets             静态资源
│ │ └── logo.png
│ │── component          组件
│ │ └── HelloWorld.vue
│ │── App.vue            根组件
│ └── main.js            入口文件
├── .gitignore 
├── babel.config.js      babel配置
├── package.json         应用包配置
├── README.md         
└── package-lock.json    固定版本配置
```
1. 执行 main.js 入口文件
2. 加载 Vue 库, 配置 Vue 属性
3. 加载 App 根组件, 创建 VM 实例, 挂载到 index.html 中的控件 #app 上

## 1.3 HTML相关
```html
<html lang="">
<head>
  <meta charset="UTF-8">
  <!-- 针对IE浏览器的特殊配置，含义是让IE浏览器以最高渲染级别渲染页面 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- 开启移动端的理想端口 -->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 配置页签图标 <%= BASE_URL %>是public所在路径，使用绝对路径 -->
  <link rel="icon" href="<%= BASE_URL %>favicon.ico">
  <!-- 配置网页标题 package.json 中的 name 属性 -->
  <title><%= htmlWebpackPlugin.options.title %></title>
</head>
<body>

<!-- 当浏览器不支持js时，noscript中的元素就会被渲染 -->
<noscript>
  <strong>更新浏览器, 以支持JavaScript</strong>
</noscript>

<!-- 根容器 -->
<div id="app"></div>
</body>
</html>
```

## 1.4 Render
- main.js 中引入的 `vue` 根据 vue 的 package.json 指定为 `"module": "dist/vue.runtime.esm.js"`
- vue.runtime.esm.js 是运行版的 Vue, 只包含核心功能, 没有模板解析器
  - 在 main.js 中的 vm 中不能使用 template 配置项, 需要 render 函数接收到的 createElement 创建虚拟 DOM
  - 组件中的 template 通过第三方库 `vue-template-compiler` 解析
- 为什么需要引入 vue.runtime.esm.js? 开发完成后, 模板已经解析完成, webpack 打包后不需要 模板解析器, 精简资源 

## 1.5 相关配置
- 隐藏了 `webpack.config.js`, 通过 `vue inspect > output.js` 查看 Vue 脚手架的默认配置
- 使用 vue.config.js 对脚手架进行个性化定制, 与 package.json 同级目录
```js
// 将配置项 与 webpack.config.js 中的配置合并
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({ // defineConfig 提供语义支持
  transpileDependencies: true,
  lintOnSave: 'warning',        // 设置语法检查等级
  pages: {
    index: {
      entry: 'src/main.js',     // page 的入口
    }
  }
})
```

# 02 基础属性
## 2.1 ref 
```js
// <m-school ref="school"></m-school>        // 使用 ref 属性
document.getElementById("title").textContent // 通过 id 获取 DOM 对象
this.$refs.school.name                       // 通过 ref 获取原生 DOM 对象 或者 VC 组件对象
```

## 2.2 prop
- 给组件传递参数
```html
<!--通过 v-bind 给组件传递属性-->
<m-student ref="student1" :name="'Bob'" :age="12" :sex="'男'"></m-student>
```

- 配置 props
```js
export default {
  // 1. 简单配置属性
  // props: [ 'name', 'age', 'sex', 'msg', ],
  // 2. 指定类型配置属性
  // props: {
  //   name: String,
  //   age: Number,
  // },
  // 3. 指定类型和默认值
  props: {
    name: {
      type: String,
      required: true,
    },
    age: {
      type: Number,
      default: 10,
    },
  },
}
```

- 接受 传递参数
```js
export default {
  name: 'm-student',
  data() {
    return {
      // 接受传递参数, 避免对 props 内属性进行修改, Vue 底层会监测 props
      // 如果发生改变会发出警告
      mName: this.name,
      mAge: this.age,
      mSex: this.sex,
      mMsg: this.msg,
    }
  },
  // props 内的属性查找优先于 data 中的属性查找
  props: [ 'name', 'age', 'sex', 'msg',],
}
```

## 2.3 mixin
- 配置混合
```js
// 函数 和 数据 以组件为主
export const commonMethods= {
  methods: {
    showInfo() {
      alert(this.mName)
    },
  },
}
export const commonData = {
  data() {
    return {
      x: 10,
      y: 'abc',
    }
  },
}

// 钩子函数直接合并, 先执行混合再执行组件内部钩子
// vm(1) + app(1) + vc(n)
export const commonMounted= {
  mounted() {
    console.log("已挂载")
  }
}
```

- 使用混合
```js
// main.js 全局混合
Vue.mixin(commonMounted)

// 组件 使用 混合
import { commonData, commonMethods } from '@/mixin'
export default {
  name: 'm-school',
  data() {
    return {
      mName: 'Saint School',
      mAddress: 'X-Road',
    }
  },
  mixins: [
    commonMethods,
    commonData,
  ],
}
```


## 2.4 install
```js
// plugin.js 定义插件
import {commonMounted} from '@/mixin'

export default {
  install(Vue, a, b, c) {
    console.log("@@" + "开始")
    Vue.mixin(commonMounted)
    Vue.prototype.hello = () => {alert('原型方法')}
  }
}

// main.js 使用插件
import plugin from '@/plugin'
Vue.use(plugin, '1', '2', '3')
```

## 2.5 组件局部样式
```vue
<!-- 局部样式 防止冲突, 实际生成如下一一对应 -->
<!-- data-v-22321ebb -->
<!--.demo[data-v-22321ebb] -->
<!-- lang 设定 css 样式写法 -->
<style lang="css" scoped>
.demo {
  background-color: lightgrey;
}
</style>

<!-- App 中 scoped 会限制全局样式的使用, 不推荐使用 -->
```

# 03 ToDoList 案例
## 3.1 组件化编码流程
- 拆分静态组件：组件要按照功能点拆分，命名不要与 html 元素冲突
- 实现动态组件：考虑好数据的存放位置，数据是一个组件在用，还是一些组件在用
  - 一个组件在用：放在组件自身即可
  - 一些组件在用：放在他们共同的父组件上(状态提升)
- 实现交互：从绑定事件开始 

## 3.2 实现过程
- 静态结构 
- 初始化列表 v-for
- 兄弟间数据传递 数据提升到父组件
- 子组件传递数据给父组件 调用父组件传递给子组件的函数, 数据在哪改变数据的函数在哪

## 3.3 注意
- 不要直接修改 props 中的数据, 而是通过传递过来的函数进行修改, 如果数据是对象, 可以修改对象属性但不推荐
```vue
<!-- todo 是 props 中的数据, props 中数据是只读数据, 只做浅层监视, 不建议使用 -->
<input type="checkbox" v-model="todo.done"/>
```

- 数组条件统计
```js
return this.toDoList.reduce((pre, current)=>{
  return pre + (current.done ? 1 : 0);
}, 0)
```

- 当一个空间的对象既涉及到查询又涉及到事件修改可以考虑计算属性
```js
let vm = {
  //..
  computed: {
    isAll: {
      get() {
        return this.toDoList.length > 0 && this.numFinish === this.toDoList.length
      },
      set(value) {
        this.checkAll(value)
      },
    },
  }
}
```

- 注意使用 WebStorage 来保存刷新过程中的数据

# 04 WebStorage
- 区别
  - SessionStorage 存储的内容会随着浏览器窗口关闭而消失
  - LocalStorage 存储的内容，需要手动清除才会消失
- 相关API
  - xxxStorage.setItem('key', 'value') 值为字符串
  - xxxStorage.getItem('key') 
  - xxxStorage.removeItem('key')
  - xxxStorage.clear() 


# 05 组件自定义事件
## 5.1 定义事件
```html
<!-- vc 上绑定一个事件 -->
<m-school ref="school" v-on:getName="demo"></m-school>

this.$emit("getName")
```

## 5.2 解绑销毁
## 5.3 function / => this 的问题

