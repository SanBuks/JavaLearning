<template>
  <div class="demo">
    <h2>学校名称：{{ mName }}</h2>
    <h2>学校地址：{{ mAddress}}</h2>
    <h3> 包含的学生信息: {{mStuName}}</h3>
  </div>
</template>

<script>
import pubsub from 'pubsub-js'

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
    // 第一个参数为 msgName
    this.pubId = pubsub.subscribe('transferInfo', (_, stuName)=>{
      console.log(stuName)
      console.log(this)
      this.mStuName = stuName
    })
  },
  beforeDestroy() {
    pubsub.unsubscribe(this.pubId)
  },
  methods: {
    getInfo(stuName) {
      this.mStuName = stuName
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
