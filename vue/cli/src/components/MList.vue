<template>
  <ul class="todo-main">
    <m-item></m-item>
    <m-item></m-item>
    <m-item></m-item>
    <m-item></m-item>
  </ul>
</template>

<script>
import MItem from '@/components/MItem'

export default {
  name: 'm-list',
  data() {
  },
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
