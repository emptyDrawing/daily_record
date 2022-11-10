import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from '../views/UserView.vue'
import ItemView from '../views/ItemView.vue'
import createListView from '../views/CreateListView.js';

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: "history", // 이러면 # 라우터 없어짐
    routes: [
        { path: '/',            redirect: '/news'      },
        { path: '/news',        name: 'news',   component: createListView('NewView'),    },
        { path: '/ask',         name: 'ask',    component: createListView('AskView'),    },
        { path: '/jobs',        name: 'jobs',   component: createListView('JobsView'),   },
        { path: '/user/:id',    name: 'user',   component: UserView,   },
        { path: '/item/:id',    name: 'item',   component: ItemView,   },
    ],
});