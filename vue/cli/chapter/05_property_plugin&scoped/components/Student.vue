<template>
  <div class="demo">
    <h2>学生名称：{{ mName }}</h2>
    <h2>学生年龄：{{ mAge }}</h2>
    <button @click="showInfo">显示信息</button>
  </div>
</template>

<script>
import { commonMethods } from '@/mixin'

export default {
  name: 'm-student',
  data() {
    return {
      mName: 'Job',
      mAge: 12,
    }
  },
  mixins: [
    commonMethods,
  ],
}
</script>

<style scoped>
.demo {
  background-color: lightgrey;
}
</style>
