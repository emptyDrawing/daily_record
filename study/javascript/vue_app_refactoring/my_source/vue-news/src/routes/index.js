import Vue from 'vue'
import VueRouter from 'vue-router'
import AskView from '../views/AskView.vue'
import JobsView from '../views/JobsView.vue'
import NewView from '../views/NewsView.vue'
import UserView from '../views/UserView.vue'
import ItemView from '../views/ItemView.vue'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: "history", // 이러면 # 라우터 없어짐
    routes: [
        { path: '/',            redirect: '/news'      },
        { path: '/news',        component: NewView,    },
        { path: '/ask',         component: AskView,    },
        { path: '/jobs',        component: JobsView,   },
        { path: '/user/:id',    component: UserView,   },
        { path: '/item/:id',    component: ItemView,   },
    ],
});