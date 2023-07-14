<template>
  <li>
    <label>
      <input type="checkbox" :checked="todo.done" @change="change(todo.id)"/>
      <span v-show="!todo.isEdit">{{todo.title}}</span>
      <input type="text"
             :value="todo.title"
             v-show="todo.isEdit"
             ref="inputTitles"
             @blur="handleBlur(todo, $event)" />
    </label>
    <button class="btn btn-danger" @click="remove(todo.id)">删除</button>
    <button class="btn btn-edit" v-show="!todo.isEdit" @click="edit(todo)">编辑</button>
  </li>
</template>

<script>
import { EVENT_CHANGE_ITEM, EVENT_EDIT_ITEM, EVENT_REMOVE_ITEM } from '@/constants/bus_event_name'

export default {
  name: 'm-item',
  data() {
    return {}
  },
  methods: {
    change(id) {
      this.$bus.$emit(EVENT_CHANGE_ITEM, id)
    },
    remove(id) {
      if (confirm('确认删除吗?')) {
        this.$bus.$emit(EVENT_REMOVE_ITEM, id)
      }
    },
    edit(todo) {
      if (todo.hasOwnProperty("isEdit")) {
        todo.isEdit = true
      } else {
        this.$set(todo, "isEdit", true)
      }
      // 获取焦点, 但是为了效率执行完后才重新解析模板, 此时 input 控件仍然处于隐藏状态
      // this.$refs['inputTitles'].focus()

      // nextTick 处于新 DOM 渲染完成后时间
      this.$nextTick(() => {
        this.$refs['inputTitles'].focus()
      })
    },
    handleBlur(todo, e) {
      todo.isEdit = false
      if (!e.target.value.trim()) {
        alert("输入不能为空!")
        return
      }
      this.$bus.$emit(EVENT_EDIT_ITEM, todo.id, e.target.value)
    }
  },
  props: ['todo']
}
</script>

<style lang="css" scoped>
/*item*/
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}

li:hover {
  background-color: #dddddd;
}

li:hover button {
  display: block;
}
</style>
