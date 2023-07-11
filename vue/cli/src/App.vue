<template>
  <div id="app">
    <div id="root">
      <div class="todo-container">
        <div class="todo-wrap">
          <m-header :addItem="addItem"/>
          <m-list :toDoList="toDoList" :handleChange="handleChange" :handleRemove="handleRemove"/>
          <m-footer :toDoList="toDoList"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import MHeader from '@/components/MHeader'
import MList from '@/components/MList'
import MFooter from '@/components/MFooter'

export default {
  name: 'App',
  data() {
    return {
      // 兄弟之间公共数据提升到父组件
      toDoList: [
        { id: '001', title: '抽烟', done: true },
        { id: '002', title: '喝酒', done: true },
        { id: '003', title: '烫头', done: false },
      ],
    }
  },
  methods: {
    // 将子组件传递给父组件的函数传递给子组件
    addItem(item) {
      this.toDoList.push(item)
    },
    handleChange(id) {
      this.toDoList.forEach((item) => {
        if (item.id === id) {
          item.done = !item.done
        }
      })
    },
    handleRemove(id) {
      this.toDoList = this.toDoList.filter((item) => {
        return item.id !== id
      })
    },
  },
  components: {
    MHeader,
    MList,
    MFooter,
  },
}
</script>

<style>

/*base*/
body {
  background: #fff;
}

.btn {
  display: inline-block;
  padding: 4px 12px;
  margin-bottom: 0;
  font-size: 14px;
  line-height: 20px;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
  border-radius: 4px;
}

.btn-danger {
  color: #fff;
  background-color: #da4f49;
  border: 1px solid #bd362f;
}

.btn-danger:hover {
  color: #fff;
  background-color: #bd362f;
}

.btn:focus {
  outline: none;
}

.todo-container {
  width: 600px;
  margin: 0 auto;
}

.todo-container .todo-wrap {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}
</style>
