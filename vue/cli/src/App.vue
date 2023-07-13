<template>
  <div id="app" class="app">
    <h1>{{msg}}, {{name}}</h1>
    <m-student ref="student"/>
    <m-school ref="school"/>
  </div>
</template>

<script>

import MStudent from '@/components/Student'
import MSchool from '@/components/School.vue'

export default {
  name: 'App',
  data() {
    return {
      msg: '组件事件总线',
      name: ''
    }
  },
  methods: {
  },
  components: {
    MStudent,
    MSchool,
  },
}
</script>
<style>
.app {
  background-color: lightgray;
  padding: 5px;
}
</style>
