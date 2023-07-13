<template>
  <div class="demo">
    <h2>学生名称：{{ mName }}</h2>
    <h2>学生年龄：{{ mAge }}</h2>
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
}
</script>

<style scoped>
.demo {
  background-color: lightgreen;
  padding: 5px;
}
</style>
