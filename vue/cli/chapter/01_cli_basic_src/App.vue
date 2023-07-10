<template>
  <div id="app">
    <m-school></m-school>
    <m-student></m-student>
  </div>
</template>

<script>

import MSchool from '@/components/School.vue'
import MStudent from '@/components/Student.vue'

export default {
  name: 'App',
  components: {
    MStudent,
    MSchool,
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
