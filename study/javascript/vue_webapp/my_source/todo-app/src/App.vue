<template>
  <div id="app">
      <TodoHeader></TodoHeader>
      <TodoInput v-on:addTodoEvent="addOneItem"></TodoInput>
      <TodoList v-bind:todolist="todoItems" 
        v-on:removeTodoEvent="removeOneItem" v-on:toggleTodoEvent="toggleOneItem"></TodoList>
      <TodoFooter v-on:clearAllEvent="clearAllItems"></TodoFooter>
  </div>
</template>

<script>

import TodoHeader from './components/TodoHeader'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {
  
  data() {
    return {
      todoItems : []
    }
  },
  // life-cycle 이 있는데 그중 하나
  created() {
    if(localStorage.length > 0) {
      const that = this
      Object.keys(localStorage).forEach(function(key){
        that.todoItems.push( JSON.parse(localStorage.getItem(key)) )
      });
    }
  },
  methods: {
    addOneItem(newTodoItem) {
      const obj = { completed : false, item : newTodoItem }
      localStorage.setItem(newTodoItem, JSON.stringify(obj))
      this.todoItems.push(obj)
    },
    removeOneItem(todoItem, index) {
      localStorage.removeItem(todoItem.item)
      this.todoItems.splice(index, 1)
    },
    toggleOneItem(todoItem, index) {
      // todoItem.completed = !todoItem.completed // 이렇게 하면 여러번 이벤트버스가 생겨서 안티패턴
      this.todoItems[index].completed = !this.todoItems[index].completed;  
      localStorage.removeItem(todoItem.item);
      localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
    },
    clearAllItems() {
      localStorage.clear();
      this.todoItems = [];
    }
  },
  
  components : {
    TodoHeader, TodoInput, TodoList, TodoFooter,
  }
}
</script>

<style>
  body {
    text-align: center;
    background-color: #f6f6f6;
  }
  input {
    border-style: groove;
    width: 200px;
  }
  button {
    border-style: groove;
  }
  .shadow {
    box-shadow: 5px 10px 10px rgba(0,0,0,0.3);
  }
</style>
