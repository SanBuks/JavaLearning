<template>
  <div id="app">
    <m-student ref="student"></m-student>
    <m-school ref="school"></m-school>
    <hr>
    <button @click="showTitle">点击显示标题</button>
  </div>
</template>

<script>

import MStudent from '@/components/Student'
import MSchool from '@/components/School.vue'

export default {
  name: 'App',
  data() {
    return {
      msg: '标题内容',
    }
  },
  components: {
    MStudent,
    MSchool,
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
