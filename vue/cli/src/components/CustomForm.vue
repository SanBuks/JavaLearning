<template>
  <form>
    <label>子组件信息</label>
    <input type="text" placeholder="请输入信息" v-model="msg" @input="$emit('input', msg)">
  </form>
</template>

<script>
export default {
  name: "CustomForm",
  data() {
    return {
      msg: this.value
    }
  },
  watch: {
    value: {
      deep: true,
      handler(newValue) {
        this.msg = newValue
      }
    },
  },
  props:["value"]
}
</script>

