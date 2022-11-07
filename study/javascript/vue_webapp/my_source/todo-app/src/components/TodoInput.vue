<template>
  <div class="inputBox shadow"> 
    <input type="text" v-model="newTodoItem" v-on:keyup.enter="addTodo"/>
    <span class="addContainer" v-on:click="addTodo">
      <i class="fas fa-plus addBtn"></i>
    </span>
    <AlertModal v-if="showModal" @close="showModal = false">
      <h3 slot="header">
        경고
        <i class="fas fa-times closeModalBtn" @click="showModal = false"></i>
      </h3>
      <div slot="body">
        무언가를 입력해주세요
      </div>
    </AlertModal>
  </div>
</template>

<script>
import AlertModal from "./common/AlertModal.vue"

export default {
  data() {
    return {
      newTodoItem: "",
      showModal : false
    }
  },
  methods : {
    addTodo(){
      if(!this.newTodoItem) { 
        this.showModal = true;
        return;
      }
      this.$emit("addTodoEvent",this.newTodoItem);
      this.clearInput()
    },
    clearInput() {
      this.newTodoItem = ''
    },
  },
  components: {
    AlertModal
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
  .closeModalBtn {
    color: #42b983;

  }
</style>