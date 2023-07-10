<script>
export default {
  name: 'm-student',
  data() {
    return {
      name: 'Bob',
      age: 19,
    }
  },
  methods: {
    showInfo() {
      alert(this.name + ' : ' + this.age)
    }
  }
}
</script>

<template>
  <div class="demo">
    <h2>学生名称：{{ name }}</h2>
    <h2>学生年龄：{{ age }}</h2>
    <button @click="showInfo">显示信息</button>
  </div>
</template>

<style>
.demo {
  background-color:  lightgrey;
}
</style>
