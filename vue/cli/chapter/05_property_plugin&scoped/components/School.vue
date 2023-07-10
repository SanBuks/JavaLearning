<template>
  <div class="demo">
    <h2>学校名称：{{ mName }}</h2>
    <h2>学校地址：{{ mAddress }}</h2>
    <button @click="showInfo">显示信息</button>
  </div>
</template>

<script>
import { commonMethods } from '@/mixin'

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
  ],
}
</script>

<style scoped>
.demo {
  background-color: lightgrey;
}
</style>
