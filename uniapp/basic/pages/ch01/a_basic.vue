<template>
  <view class="root">
    <h1>{{ title }}</h1>
    <text> icon </text>
    <view class="icon" v-for="(value, index) in iconType" :key="index">
      <icon :type="value"></icon>
      <text>{{ value }}</text>
    </view>
  </view>
</template>

<script>

export default {
  data() {
    return {
      title: '基础组件',
      iconType: ['success']
    }
  },
  onLoad() {
    this.iconType = ['success', 'success_no_circle', 'info', 'warn', 'waiting', 'cancel', 'download', 'search', 'clear']
  },
  methods: {}
}
</script>


<style lang="less">
.root {
  width: 750rpx;
  background-color: whitesmoke;
}
</style>
