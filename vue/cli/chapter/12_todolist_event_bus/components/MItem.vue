<template>
  <li>
    <label>
      <input type="checkbox" :checked="todo.done" @change="change(todo.id)"/>
      <span>{{todo.title}}</span>
    </label>
    <button class="btn btn-danger" @click="remove(todo.id)">删除</button>
  </li>
</template>

<script>
import { EVENT_CHANGE_ITEM, EVENT_REMOVE_ITEM } from '@/constants/bus_event_name'

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
