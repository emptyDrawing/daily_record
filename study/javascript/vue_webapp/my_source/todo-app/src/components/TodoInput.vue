<template>
  <div class="inputBox shadow"> 
    <input type="text" v-model="newTodoItem" v-on:keyup.enter="addTodo"/>
    <span class="addContainer" v-on:click="addTodo">
      <i class="fas fa-plus addBtn"></i>
    </span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newTodoItem: ""
    }
  },
  methods : {
    addTodo(){
      if(!this.newTodoItem) { return;}

      var obj = {
        completed : false,
        item : this.newTodoItem
      }
      
      localStorage.setItem(this.newTodoItem, JSON.stringify(obj))
      this.clearInput()
    },
    clearInput() {
      this.newTodoItem = ''
    }
  }
}
</script>

<style scoped>
  input:focus {
    outline: none;
  }
  .inputBox {
    background: white;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;
  }
  .inputBox input{
    border-style: none;
    font-size: 1.1rem;
    width: calc(100% - 4rem);
    text-align: left;

  }
  .addContainer {
    float : right;
    background : linear-gradient(to right, #6478FB, #8763F8);
    display: block;
    width : 3rem;
    border-radius: 0 5px 5px 0;
  }
  .addBtn {
    color: white;
    vertical-align: middle;
  }
</style>