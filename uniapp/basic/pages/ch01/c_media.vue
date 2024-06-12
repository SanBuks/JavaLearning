<template>
  <view class="root">
    <h1> {{ title }} </h1>
    <swiper class="swiper"
            circular="true"
            interval="3000"
            duration="300"
            autoplay="true"
            indicator-color="#ffffff"
            indicator-dots="true"
    >
      <swiper-item class="item">
        <image src="/static/images/pic1.jpg" mode="scaleToFill" lazy-load="true"></image>
      </swiper-item>
      <swiper-item class="item">
        <image src="/static/images/pic2.jpg" mode="aspectFill" lazy-load="true"></image>
      </swiper-item>
      <swiper-item class="item">
        <image src="/static/images/pic3.jpg" mode="aspectFill" lazy-load="true"></image>
      </swiper-item>
    </swiper>

    <video src="/static/images/media.mp4" autoplay="false"></video>
  </view>
</template>

<script>

export default {
  data() {
    return {
      title: "媒体组件"
    }
  },
  methods: {}
}
</script>


<style lang="less">
.root {
  width: 750rpx;
  background-color: whitesmoke;
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
