<template>
  <div class="demo">
    <h2>学生名称：{{ mName }}</h2>
    <h2>学生年龄：{{ mAge }}</h2>
    <h2>学生性别：{{ mSex }}</h2>
    <h2>学生信息：{{ mMsg }}</h2>
    <button @click="showInfo">显示信息</button>
  </div>
</template>

<script>
export default {
  name: 'm-student',
  data() {
    return {
      mName: this.name,
      mAge: this.age,
      mSex: this.sex,
      mMsg: this.msg,
    }
  },
  methods: {
    showInfo() {
      alert(this.name + ' : ' + this.age)
    },
  },
  // 1. 简单配置属性
  // props: [ 'name', 'age', 'sex', 'msg', ],
  // 2. 指定类型配置属性
  // props: {
  //   name: String,
  //   age: Number,
  //   sex: String,
  //   msg: String,
  // },
  // 3. 指定类型和默认值
  props: {
    name: {
      type: String,
      required: true,
    },
    age: {
      type: Number,
      default: 10,
    },
    sex: {
      type: String,
      required: true,
    },
    msg: {
      type: String,
      default: '',
    },
  },
}
</script>

<style>
.demo {
  background-color: lightgrey;
}
</style>
