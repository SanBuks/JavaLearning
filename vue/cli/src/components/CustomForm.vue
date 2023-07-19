<template>
  <div class="div" @click.stop="noHide">
    <el-form>
      <label>子组件信息</label>
      <el-input type="text" placeholder="请输入信息" v-model="msg" @input="$emit('input', msg)"/>
      <el-calendar></el-calendar>
    </el-form>
  </div>
</template>

<script>

export default {
  name: "CustomForm",
  data() {
    return {
      msg: this.value
    }
  },
  methods: {
    noHide() {

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


<style scoped>
.div {
  background-color: aquamarine;
}

</style>
