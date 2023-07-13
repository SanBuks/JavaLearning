<template>
  <div id="app" class="app">
    <h1>{{msg}}, {{name}}</h1>
    <m-student ref="student"/>

    <!-- 1. vc 上绑定一个事件 v-on -->
<!--    <m-school ref="school" v-on:getNameEvent="getSchoolName"/>-->
    <!-- 2. vc 上绑定一个事件 @ 简写, .native 指定为原生事件 -->
    <m-school ref="school" @getNameEvent.once="getSchoolName" @click.native="show"/>
    <!-- 3. 通过 $on / $once 完成组件事件定义-->
<!--    <m-school ref="school"/>-->
  </div>
</template>

<script>

import MStudent from '@/components/Student'
import MSchool from '@/components/School.vue'

export default {
  name: 'App',
  data() {
    return {
      msg: '组件事件',
      name: ''
    }
  },
  mounted() {
    // 3. 通过 $on / $once 完成组件事件定义
    // setTimeout(()=>{
    //   this.$refs["school"].$once("getNameEvent", this.getSchoolName)
    // }, 3000)
  },
  methods: {
    getSchoolName(name, ...params) {
      this.name = name
    },
    show() {
      alert("自定义事件")
    }
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
