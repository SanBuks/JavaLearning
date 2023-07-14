<template>
  <div id="app">
    <custom-form v-model='msg'/>
    <button @click="show">显示 父组件的 信息</button>
  </div>
</template>

<script>
import CustomForm from '@/components/CustomForm'

export default {
  name: 'App',
  data() {
    return {
      msg: 'default'
    }
  },
  methods: {
    show() {
      console.log(this.msg)
    }
  },
  components: {
    CustomForm,
  },
}
</script>

