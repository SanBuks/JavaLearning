<template>
  <div id="app">
    <m-student ref="student1" :name="'Bob'"  :sex="'男'"></m-student>
    <m-student ref="student2" :name="'Jobs'" :age="13" :sex="'男'"></m-student>
    <m-student ref="student3" :name="'Alice'" :age="11" :sex="'女'"></m-student>
    <hr>
    <button @click="showTitle">点击显示标题</button>
  </div>
</template>

<script>

import MStudent from '@/components/Student.vue'

export default {
  name: 'App',
  data() {
    return {
      msg: '标题内容',
    }
  },
  components: {
    MStudent,
  },
  methods: {
    showTitle() {
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
