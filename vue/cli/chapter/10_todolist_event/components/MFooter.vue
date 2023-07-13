<template>
  <div class="todo-footer" v-show="toDoList.length">
    <label> <input type="checkbox" v-model="isAll"/> </label>
    <span> <span>已完成 {{numFinish}}</span> / 全部{{toDoList.length}} </span>
    <button class="btn btn-danger" @click="clearFinished">清除已完成任务</button>
  </div>
</template>

<script>
export default {
  name: 'm-footer',
  data() {
    return {}
  },
  methods: {
    clearFinished() {
      this.$emit("clearAll")
    },
  },
  computed: {
    numFinish() {
      return this.toDoList.reduce((pre, current)=>{
        return pre + (current.done ? 1 : 0)
      }, 0)
    },
    isAll: {
      get() {
        return this.toDoList.length > 0 && this.numFinish === this.toDoList.length
      },
      set(value) {
        this.$emit("checkAll", value)
      },
    },
  },
  props: ['toDoList', 'checkAll', 'clearAll']
}
</script>

<style lang="css" scoped>
/*footer*/
.todo-footer {
  height: 40px;
  line-height: 40px;
  padding-left: 6px;
  margin-top: 5px;
}

.todo-footer label {
  display: inline-block;
  margin-right: 20px;
  cursor: pointer;
}

.todo-footer label input {
  position: relative;
  top: -1px;
  vertical-align: middle;
  margin-right: 5px;
}

.todo-footer button {
  float: right;
  margin-top: 5px;
}
</style>
