### 프로젝트 생성
```shell
# 2.x 이하
vue init webpack-simple vue-news

# 3.x 이상
vue create vue-news
```
- 2.x / 3.x의 차이점
  - 웹팩파일설정 노출   [  2.x  -> O  / 3.x -> X [(참고링크)](https://cli.vuejs.org/guide/webpack.html#inspecting-the-project-s-webpack-config)]
  - 프로젝트구성        [  2.x  -> 깃헙의 템플릿 다운로드  / 3.x -> 플러그인 기반으로 기능추가 ]
  - ES6 이해도 필요     [  2.x  -> X  / 3.x -> O ]
  

### ESLint
![](assets/2022-11-08-16-03-14.png)
- 그런데 Vue Cli 3.x 이상에서 ESLint 끄는법은..
  - 파일마다
  ![](assets/2022-11-08-16-09-21.png)
  - 설정파일로: [참고문서](https://cli.vuejs.org/core-plugins/eslint.html#configuration)
  ![](assets/2022-11-08-16-12-32.png)

### 라우터 설치 및 설정
```shell
# 강의는 vue 2점대를 쓰기 때문에 아래처럼 router를 버젼 고정시켜줘야됨.
yarn add vue-router@3.5.3
```
![](assets/2022-11-08-16-25-57.png)
  - router/index.js : 라우터 설정
  ```javascript
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
          { path: '/', redirect: '/news'          },
          { path: '/news', component: NewView,    },
          { path: '/ask', component: AskView,     },
          { path: '/jobs', component: JobsView,   },
          { path: '/user', component: UserView,   },
          { path: '/item', component: ItemView,   },
      ],
  });
  ```
  - views/* : router에 연결되는 페이지 Vue-Component

  - main.js
  ```javascript
  import Vue from 'vue'
  import App from './App.vue'
  import router from './routes/index'
  Vue.config.productionTip = false

  new Vue({
    render: h => h(App),
    router,
  }).$mount('#app')
  ```
  
  - App.vue
  ```javascript
  <template>
    <div id="app"> 
      <tool-bar></tool-bar>   
      <router-view></router-view>
    </div>
  </template>

  <script>
  import ToolBar from "./components/ToolBar.vue"

  export default {
    components: {
      ToolBar
    }
  }
  </script>

  <style>
  body {
    padding: 0;
    margin: 0;
  }
  </style>
  ```

  ![](assets/2022-11-08-17-01-07.png)

### axios 관리법
- 컴포넌트마다 api를 불러오면 상당히 복잡해 질 수 있음.
![](assets/2022-11-09-09-17-20.png)
  ```javascript
  import axios from "axios";
  // 1. HttP Request / Response 관련 기본설정
  const config = {
      baseurl : 'https://api.hnpwa.com/v0',
      apiList : ['news', 'jobs', 'ask']
  }
  // 2. API 함수 개별 정의
  const callAPIList = (name) => {
      return new Promise((resolve, reject) => {

          if( config.apiList.includes(name) ){
              resolve(callListByName(name));
          }

          reject(`This [${name}]API is not provided`);
      });
  }
  const callListByName= (name) => {
      return axios.get(`${config.baseurl}/${name}/1.json`)
  }
  // 3. export
  export {
      callAPIList
  }
  ```

  - 이걸 이제 component 나 view 에 그려야되는데
  - [라이프사이클](https://vuejs.org/guide/extras/reactivity-in-depth.html#ad) 을 알면 좀더 효과적으로 보여줄 수 있음.
  
  -------------------------------
  - **화살표함수 this 확인 필**
    - new 차이유무
    ![](assets/2022-11-09-10-06-25.png)

    - 참고링크 : 
      - https://bohyeon-n.github.io/deploy/javascript/this.html
      - https://www.freecodecamp.org/news/when-and-why-you-should-use-es6-arrow-functions-and-when-you-shouldnt-3d851d7f0b26
      - https://www.codementor.io/@niladrisekhardutta/how-to-call-apply-and-bind-in-javascript-8i1jca6jp
      - https://gist.github.com/zcaceres/2a4ac91f9f42ec0ef9cd0d18e4e71262
  -------------------------------

### Vuex 적용
![](assets/2022-11-09-10-25-50.png)
```shell
yarn add vuex@3.6.2
```
![](assets/2022-11-09-10-58-19.png)
- 기존꺼 배운걸로 모듈화 시킴
  ```javascript
  // store/index.js
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
  
  // dataApp.js
  import { callAPIList } from '../../api/index.js'


  const state = {
      newsList : [],
      jobsList : [],
      askList : [],
  }

  const getters =  {}

  const mutations = {
      setAPIData(state, { name, data }) {
          switch (name) {
              case 'news':
                  state.newsList = data;
                  break;
              case 'jobs':
                  state.jobsList = data;
                  break;
              case 'ask':
                  state.askList = data;
                  break;
          }
      }
  }
  
  const actions = {
      FETCH_DATA(context,{ name }) {
          callAPIList(name)
            .then(resp => { 
                  const data = resp.data
                  context.commit('setAPIData',{ name, data }) 
              })
            .catch( err => console.error(err) )
      }
  }

  export default {
      state, 
      getters, 
      mutations,
      actions
  }
  ```
  - 사용할때는..
  ```javascript
  // NewsView.vue
  // helper 함수 안쓰니 조금 번잡하긴 하다;;
  <template>
    <div>
      <div v-for="news in this.$store.state.data.newsList">{{news.title}}</div>
    </div>
  </template>

  <script>

  export default {
    created() {
      this.$store.dispatch('FETCH_DATA',{'name' : 'news'})     
    }
  }
  </script>

  <style>

  </style>
  ```

  - 그런데 강의를 보니까 이렇게도 줄일수 있더라...
  ```javascript
  // dataApp.js 에서.
  const actions = {
      // FETCH_DATA( context, { name })
      FETCH_DATA( { commit }, { name }) {
          callAPIList(name)
            .then( 
                //(resp) => { 
                //    const data = resp.data
                //    context.commit('setAPIData',{ name, data })      
                ({ data }) => {
                commit('setAPIData',{ name, data }) 
              })
            .catch( err => console.error(err) )
      }
  }
  ```
  - 그리고 다시 map 함수로 고치자
  ```javascript
  // NewsView.vue
  <template>
    <div>
      <div v-for="news in news">{{ news.title }}</div>
    </div>
  </template>

  <script>

  import { mapGetters } from 'vuex';

  export default {
    computed : {
      // ...mapState({
      //   news : (state, getters) => state.data.newsList
      // })
      ...mapGetters({
        news : 'getNewsList'
      })
    },
    created() {
      this.$store.dispatch('FETCH_DATA',{'name' : 'news'})     
    }
  }
  </script>

  <style></style>

  ```