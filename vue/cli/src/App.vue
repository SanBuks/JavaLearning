<template>
  <div id="app" @click="hide">
    <custom-form v-model='msg' v-show='open' ref="form"/>
    <button @click.stop="show">显示 父组件的 信息</button>
  </div>
</template>

<script>
import CustomForm from '@/components/CustomForm'

export default {
  name: 'App',
  data() {
    return {
      msg: 'default',
      open: true
    }
  },
  methods: {
    show() {
      console.log(this.msg)
    },
    hide() {
      this.open = false
      console.log(this.open)
    }
  },
  components: {
    CustomForm,
  },
}
</script>

