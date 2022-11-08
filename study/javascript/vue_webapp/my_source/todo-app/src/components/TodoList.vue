<template>
  <div>
    <transition-group name="list" tag="ul">
      
      <li v-for="(todoItem, index) in this.todoItems" v-bind:key="todoItem.item" class="shadow">
        <i class="checkBtn fas fa-check" 
          v-bind:class="{checkBtnCompleted: todoItem.completed}" 
          @click="toggleComplete({todoItem, index})"></i>
        <span v-bind:class="{textCompleted: todoItem.completed}">{{ todoItem.item }}</span>
        <span class="removeBtn" 
          @click="removeTodo({todoItem, index})">
          <i class="fas fa-trash-alt"></i>
        </span>
      </li>

    </transition-group>
  </div>

</template>

<script>
 import { mapGetters, mapMutations } from 'vuex'

 export default { 
  computed: { 
    ...mapGetters(
      // ['storeTodoItems'] 이름 같이 쓸때 
      {'todoItems' : 'getTodoItems'} // 다르게 쓸때
    )
  },

  methods : { 
    ...mapMutations({
      'toggleComplete' : 'toggleTodoEvent'
    }),
    ...mapMutations({
      'removeTodo' : 'removeTodoEvent'
    }),
  },
}
</script>

<style scoped>
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}

li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background-color: white;
  border-radius: 5px;
}

.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}

.checkBtnCompleted {
  color: #b3adad;
}

.textCompleted {
  text-decoration: line-through;
}

.removeBtn {
  margin-left: auto;
  color: #de4343;
}
/** list tranactions */
.list-item {
  display: inline-block;
  margin-right: 10px;
}
.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
  opacity: 0;
  transform: translateY(30px);
}
</style>
