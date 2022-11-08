import Vue from 'vue'
import Vuex from 'vuex'

// 글로벌 하게 쓸 plugin 을 등록함.
Vue.use(Vuex);

const storage = {
    fetch() {
        const arr = [];
        if(localStorage.length > 0) {
          Object.keys(localStorage).sort().forEach(function(key){
            arr.push( JSON.parse(localStorage.getItem(key)) )
          });
        }
        return arr;
      },
}


export const store = new Vuex.Store({
    state: {
        headerText : "TODO-IT!",
        todoItems : storage.fetch()
    },
    getters: {
        getTodoItems(state){
            return state.todoItems
        },
        getHeaderText(state){
            return state.headerText
        }
    },
    mutations: {
        addTodoEvent(state, newTodoItem) {
            const obj = { completed : false, item : newTodoItem }
            localStorage.setItem(newTodoItem, JSON.stringify(obj))
            state.todoItems.push(obj)
        },
        removeTodoEvent(state, {todoItem, index}) {
            localStorage.removeItem(todoItem.item)
            state.todoItems.splice(index, 1)
        },
        toggleTodoEvent(state, {todoItem, index}) {
            state.todoItems[index].completed = !state.todoItems[index].completed;  
            localStorage.removeItem(todoItem.item);
            localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
        },
        clearAllEvent(state) {
            localStorage.clear();
            state.todoItems = [];
        },
    }
});