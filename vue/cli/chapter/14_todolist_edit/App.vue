<template>
  <div id="app">
    <div id="root">
      <div class="todo-container">
        <div class="todo-wrap">
          <m-header @addItem="addItem"/>
          <m-list :toDoList="toDoList"/>
          <m-footer :toDoList="toDoList" @checkAll="checkAll" @clearAll="clearAll"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import MHeader from '@/components/MHeader'
import MList from '@/components/MList'
import MFooter from '@/components/MFooter'
import { EVENT_CHANGE_ITEM, EVENT_REMOVE_ITEM, EVENT_EDIT_ITEM } from '@/constants/bus_event_name'

export default {
  name: 'App',
  data() {
    return {
      toDoList: JSON.parse(localStorage.getItem('toDoList')) || [],
      toDoListInit: [
        { id: '001', title: '抽烟', done: true },
        { id: '002', title: '喝酒', done: true },
        { id: '003', title: '烫头', done: false },
      ],
    }
  },
  mounted() {
    this.$bus.$on(EVENT_CHANGE_ITEM, this.handleChange)
    this.$bus.$on(EVENT_REMOVE_ITEM, this.handleRemove)
    this.$bus.$on(EVENT_EDIT_ITEM, this.handleEdit)
  },
  beforeDestroy() {
    this.$bus.$off(EVENT_CHANGE_ITEM)
    this.$bus.$off(EVENT_REMOVE_ITEM)
    this.$bus.$off(EVENT_EDIT_ITEM)
  },
  methods: {
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
    handleEdit(id, title) {
      this.toDoList.forEach((item) => {
        if (item.id === id) {
          item.title = title
        }
      })
    },
    checkAll(status) {
      this.toDoList.forEach((item)=> {
        item.done = status
      })
    },
    clearAll() {
      this.toDoList = this.toDoList.filter((item)=> {
        return item.done === false
      })
    }
  },
  components: {
    MHeader,
    MList,
    MFooter,
  },
  watch: {
    toDoList: {
      deep: true,
      handler(newValue) {
        localStorage.setItem('toDoList', JSON.stringify(newValue))
      },
    },
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

.btn-edit {
  color: #fff;
  background-color: #88d2a9;
  border: 1px solid #04600c;
  margin-right: 5px;
}
.btn-edit:hover {
  color: #fff;
  background-color: #04600c;
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
