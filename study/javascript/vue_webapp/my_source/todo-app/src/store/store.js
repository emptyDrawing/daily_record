import Vue from 'vue'
import Vuex from 'vuex'
import todoApp from './modules/todoStore'
import demoApp from './modules/demostore'

// 글로벌 하게 쓸 plugin 을 등록함.
Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        tesModule : demoApp,
        todoApp,
    }
});