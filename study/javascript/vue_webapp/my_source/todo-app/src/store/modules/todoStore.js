
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

const state = {
    headerText : "TODO-IT!",
    todoItems : storage.fetch()
}

const getters =  {
    getTodoItems(state){
        return state.todoItems
    },
    getHeaderText(state){
        return state.headerText
    }
}

const mutations = {
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

export default {
    state, 
    getters, 
    mutations 
}