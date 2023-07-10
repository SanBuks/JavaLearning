<script>
export default {
  name: 'm-school',
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
  <div class="demo">
    <h2>学校名称：{{ name }}</h2>
    <h2>学校地址：{{ address }}</h2>
    <button @click="showInfo">显示信息</button>
  </div>
</template>

<style>
.demo {
  background-color: lightgrey;
}
</style>
