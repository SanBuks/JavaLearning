<template>
  <div class="demo">
    <h2>学校名称：{{ mName }}</h2>
    <h2>学校地址：{{ mAddress}}</h2>
    <h3> 包含的学生信息: {{mStuName}}</h3>
  </div>
</template>

<script>

export default {
  name: 'm-school',
  data() {
    return {
      mName: 'Saint School',
      mAddress: 'X-Road',
      mStuName: '',
    }
  },
  mounted() {
    // this.x.$on("transferStuInfo", this.getInfo)
    this.$bus.$on("transferStuInfo", this.getInfo)
  },
  methods: {
    getInfo(stuName) {
      this.mStuName = stuName
    }
  },
  beforeDestroy() {
    // 在销毁前解绑, 因为 事件在 bus 上, vc 销毁不会将事件销毁
    // 一般 事件名是 常量对象
    this.$bus.$off('transferStuInfo')
  }
}
</script>

<style scoped>
.demo {
  background-color: lightblue;
  padding: 5px;
}
</style>
