<template>
  <div id="app">
    <h1 v-text="msg" id="title" ref="title"></h1>
    <m-school ref="school"></m-school>
    <m-student ref="student"></m-student>
    <hr>
    <button @click="showTitle">点击显示标题</button>
  </div>
</template>

<script>

import MSchool from '@/components/School.vue'
import MStudent from '@/components/Student.vue'

export default {
  name: 'App',
  data() {
    return {
      msg: '标题内容',
    }
  },
  msg: 'Welcome!',
  components: {
    MStudent,
    MSchool,
  },
  methods: {
    showTitle() {
      // alert(document.getElementById("title").textContent) // 通过 id 获取 DOM 对象
      alert(this.$refs.title.textContent) // 通过 ref 获取原生 DOM 对象 或者 VC 组件对象
      alert(this.$refs.student.age)       // 通过 ref 获取原生 DOM 对象 或者 VC 组件对象
      alert(this.$refs.school.name)       // 通过 ref 获取原生 DOM 对象 或者 VC 组件对象
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
