<template>
  <div class="demo">
    <h2>学生名称：{{ mName }}</h2>
    <h2>学生年龄：{{ mAge }}</h2>
    <button @click="transferInfo"> 把学生信息传递给 学校</button>
  </div>
</template>

<script>

export default {
  name: 'm-student',
  data() {
    return {
      mName: 'Job',
      mAge: 12,
    }
  },
  methods: {
    transferInfo() {
      // this.x.$emit("transferStuInfo", this.mName)
      this.$bus.$emit("transferStuInfo", this.mName)
    }
  }
}
</script>

<style scoped>
.demo {
  background-color: lightgreen;
  padding: 5px;
}
</style>
