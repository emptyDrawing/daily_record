import Vue from 'vue'
import VueRouter from 'vue-router'
import UserView from '@/views/UserView.vue'
import ItemView from '@/views/ItemView.vue'
import JobsView from '@/views/JobsView.vue'
import createListView from '@/views/CreateListView.js';
import bus from '@/utils/bus'
import { store } from '@/store/index.js'

Vue.use(VueRouter)

export const router = new VueRouter({
    mode: "history", // 이러면 # 라우터 없어짐
    routes: [
        { path: '/',            redirect: '/news'      },
        { path: '/news',        name: 'news',   component: createListView('NewView'),    },
        { path: '/ask',         name: 'ask',    component: createListView('AskView'),    },
        { path: '/jobs',        name: 'jobs',   component: JobsView,  
         beforeEnter: (to, from, next) => {
            bus.$emit('start:spinner')
            store.dispatch('FETCH_DATA', {'name' : to.name})
                .then( (data) => { console.log(data); next();} )
                .catch( (e) => { console.log(e); bus.$emit('end:spinner'); } );
          },
        },
        { path: '/user/:id',    name: 'user',   component: UserView,   },
        { path: '/item/:id',    name: 'item',   component: ItemView,   },
    ],
});