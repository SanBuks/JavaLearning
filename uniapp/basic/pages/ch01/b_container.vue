<template>
  <view class="root">
    <h1>{{ title }}</h1>
    <text> scroll-view </text>
    <scroll-view class="scroll" scroll-x="true" scroll-y="true">
      <view class="box">
        <view class="item">1</view>
        <view class="item">2</view>
        <view class="item">3</view>
      </view>
    </scroll-view>

    <text> swiper </text>
    <swiper class="swiper"
            circular="true"
            interval="3000"
            duration="300"
            autoplay="true"
            indicator-color="#ffffff"
            indicator-dots="true"
    >
      <swiper-item class="item">1</swiper-item>
      <swiper-item class="item">2</swiper-item>
      <swiper-item class="item">3</swiper-item>
    </swiper>
  </view>
</template>

<script>

export default {
  data() {
    return {
      title: '容器组件'
    }
  },
  methods: {}
}
</script>


<style lang="less">
.root {
  width: 750rpx;
  background-color: whitesmoke;

  .scroll {
    width: 750rpx;
    height: 300rpx;
    border: 1px solid red;
    box-sizing: border-box;

    white-space: nowrap;      // 不换行
    .item {
      width: 750rpx;
      height: 300rpx;
      background-color: mistyrose;
      display: inline-block;  // 行级元素
      margin-right: 10rpx;
    }
  }

  .swiper {
    .item {
      width: 750rpx;
      height: 300rpx;
      background-color: mistyrose;

      image{
        width: 100%;
        height: 100%;
      }
    }
  }

}
</style>
