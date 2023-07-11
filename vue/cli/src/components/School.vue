<template>
  <div class="demo">
    <h2>学校名称：{{ mName }}</h2>
    <h2>学校地址：{{ mAddress}}</h2>
    <button @click="sendName"> 点击获取名称 </button>
    <button @click="unbind"> 解绑 </button>
    <button @click="death"> 销毁 </button>
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
  methods: {
    sendName() {
      this.$emit("getNameEvent", this.mName)
    },
    unbind() {
      // 解绑 数组中的事件
      this.$off(["getNameEvent"])
      // 全部 解绑
      // this.$off()
    },
    death() {
      // 同时会销毁组件的自定义事件
      this.$destroy()
    }
  }
}
</script>

<style scoped>
.demo {
  background-color: lightblue;
  padding: 5px;
}
</style>
