import Vue from 'vue';
import Vuex from 'vuex';
import dataApp from './module/dataApp'
import userApp from './module/userApp'


Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        data : dataApp,
        user : userApp
    }
});

export default {
    store
}
