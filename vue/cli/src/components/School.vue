<template>
  <div class="demo">
    <h2>学校名称：{{ mName }}</h2>
    <h2>学校地址：{{ mAddress}}</h2>
  </div>
</template>

<script>

export default {
  name: 'm-school',
  data() {
    return {
      mName: 'Saint School',
      mAddress: 'X-Road',
    }
  },
}
</script>

<style scoped>
.demo {
  background-color: lightblue;
  padding: 5px;
}
</style>
