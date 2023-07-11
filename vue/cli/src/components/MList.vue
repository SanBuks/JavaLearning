<template>
  <ul class="todo-main">
    <m-item v-for="item in toDoList" :key="item.id" :title="item.title" :todo="item"
            :handleChange="handleChange" :handleRemove="handleRemove"></m-item>
  </ul>
</template>

<script>
import MItem from '@/components/MItem'

export default {
  name: 'm-list',
  data() {
    return {
    }
  },
  props: ['toDoList', 'handleChange', 'handleRemove'],
  components: {
    MItem,
  },
}
</script>

<style lang="css" scoped>
/*main*/
.todo-main {
  margin-left: 0px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding: 0px;
}

.todo-empty {
  height: 40px;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding-left: 5px;
  margin-top: 10px;
}
</style>
