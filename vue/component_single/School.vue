<script>
// 组件交互代码
export default {
  name: 'School',
  data() {
    return {
      name: 'Saint School',
      address: 'x-road',
    }
  },
  methods: {
    showInfo() {
      alert(this.name + ' : ' + this.address)
    }
  }
}
</script>

<template>
  <!-- 组件结构 -->
  <div class="demo">
    <h2>学校名称：{{ name }}</h2>
    <h2>学校地址：{{ address }}</h2>
    <button @click="showName">显示信息</button>
  </div>
</template>

<style>
// 组件样式
.demo {
  background-color: orange;
}
</style>