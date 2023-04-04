## Intro

### project 설치 옵션
![](assets/2022-11-14-10-23-32.png)


### 필요 vue 라이브러리 설치
- 나 같은 경우는 `nvm use v18.12.0` 에 맞춘 profile 로 셋팅했음
- vue router : ` yarn add vue-router@3.5.3 && yarn add vue-router@3.5.3:`
- axios : `yarn add axios`
- vuex : ` yarn add vuex@3.6.2`


## 각 기능

### 화면을 덮는 ESLint 에러 없애기
- https://joshua1988.github.io/webpack-guide/devtools/webpack-dev-server.html
- `./vue.config.js` 파일에서 아래 내용 작성
  ```js
    const { defineConfig } = require("@vue/cli-service");
    module.exports = defineConfig({
    transpileDependencies: true,
    devServer : {
            client : {
            overlay : false,
            },
        },
    });
  ```
  ![](assets/2022-11-14-10-40-26.png)

### ESLint 설정안내
- no-console : error 로 셋팅하면 console 있으면 에러처럼 표현
![](assets/2022-11-14-10-44-49.png)
![](assets/2022-11-14-10-44-09.png)
- off 로 셋팅시
![](assets/2022-11-14-10-45-54.png)

- 좀더 원할한 설정을 위해 `prettier` 도 [적용하자](https://prettier.io/)
![](assets/2022-11-14-10-50-04.png)
    - 이렇게 개별적으로 만들수 있지만 이러면 ESLint 와 충돌날수 있어
    ![](assets/2022-11-14-10-53-29.png)
    - 이렇게 넣자 `.eslintrc.js`
- 그리고 수정이 편하게 `vscode` 에도 적용하자
![](assets/2022-11-14-11-03-10.png)
  - eslint 로 검색해서 위 메뉴 찾고 아래 내용 추가
  ```json
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true
    },
    "eslint.workingDirectories": [
        {"mode": "auto"}
    ],
  ```
  ![](assets/2022-11-14-11-16-38.png)
  - 그리고 이번 워크스페이스에서만 사용하지 않게 하게
  ![](assets/2022-11-14-11-17-37.png)
  - 이 포맷도 꺼둔다.

### 파일경로 절대경로로 바꾸기
- vscode `jsconfig.json` 의 설정인데 [이 링크](https://code.visualstudio.com/docs/languages/jsconfig)를 참고하자
```json
{
  "compilerOptions": {
    "target": "es5",
    "module": "esnext",
    "baseUrl": "./",
    "moduleResolution": "node",
    "paths": {
      "@/*": [
        "src/*"
      ]
    },
    "lib": [
      "esnext",
      "dom",
      "dom.iterable",
      "scripthost"
    ]
  }
}
```

### code splitting [ router 설정 ]
- 참고링크 :
  - [window.history](https://developer.mozilla.org/en-US/docs/Web/API/Window/history)
  - [webpack-code-splitting](https://webpack.js.org/guides/code-splitting/)
  - [vue.js 다이나믹 임포트](https://vuejs.org/guide/built-ins/keep-alive.html#ad) 
![](assets/2022-11-14-13-47-33.png)
- 처음 로딩하면 이렇게 모든 페이지가 다 `app.js`가 들어간다.
- 그래서 이걸 링크 들어갈때만 해당 페이지를 로딩하게 하는게 `code splitting`
```js
// /route/index.js
// redirect 까지 끝낸 소스
// 그런데 없는 페이지 호출시 반응하는 것도 필요하니 그것도 설정
export default new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      path: '/login',
      component: () => import('@/views/LoginPage.vue'),
    },
    {
      path: '/signup',
      component: () => import('@/views/SignupPage.vue'),
    },
    {
      path: '*',
      component: () => import('@/views/NotFoundPage.vue'),
    },
  ],
});

```
![](assets/2022-11-14-13-49-44.png)

- router 에서 [history 모드를 켰을 때](https://router.vuejs.org/guide/essentials/history-mode.html) 
  서버에서 이게 `라우터에서 관리하는건지 아닌지`를 알려주어야 됨.


### axios 셋팅
```js
// api/index.js
import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:3000/',
});

const registerUser = userData => instance.post('signup', userData);

export { registerUser };

////////////////////////////////
// components/SigunupForm.vue

<script>
import { registerUser } from '@/api/index';

export default {
  data() {
    return {
      username: '',
      password: '',
      nickname: '',
      //log
      logMessage: '',
    };
  },
  methods: {
    async submitForm() {
      const userData = {
        username: this.username,
        password: this.password,
        nickname: this.nickname,
      };

      const { data } = await registerUser(userData);

      this.logMessage = `${data?.username}님이 가입되었습니다.`;
      this.initForm();
    },
    initForm() {
      this.username = '';
      this.password = '';
      this.nickname = '';
    },
  },
};
</script>
```

- 그런데 endpoint를 환경변수로도 써보자.
  - ![](assets/2022-11-14-15-40-28.png)
  - `VUE_APP_` 접두사 활용!
- 그런데 이것도[ 환경별로 도 나눌수 있음](https://cli.vuejs.org/guide/mode-and-env.html)
![](assets/2022-11-14-15-45-12.png)
![](assets/2022-11-14-15-46-37.png)

### axios err 처리해보자
- async-await 했으니 간단히 try-catch 해보자
```js
methods: {
  async loginTry() {
    try {
      const userData = {
        username: this.username,
        password: this.password,
      };

      const { data } = await loginUser(userData);
      this.logMessage = `${data.user?.username}님 환영합니다.`;
      this.initForm();
    } catch ({ response }) {
      this.logMessage = response.data;
    }
  },
  initForm() {
    this.username = '';
    this.password = '';
  },
}
```

### Validation
- 간단한 emailVaildate 를 넣고
```js
const validateEmail = email => {
  const re =
    /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
  return re.test(String(email).toLowerCase());
};

export { validateEmail };
```
- computed에 넣어서 처리한다.
```js
  computed: {
    isUsernameValid() {
      return validateEmail(this.username);
    },
  },

  // template 에서 사용한 예
  <div class="contents">
    <div class="form-wrapper form-wrapper-sm">
      <form @submit.prevent="loginTry" class="form">
        <div>
          <label for="username">id: </label>
          <input id="username" type="text" v-model="username" />
          <p class="validation-text">
            <span class="warning" v-if="!isUsernameValid && username">
              Please enter an email address
            </span>
          </p>
        </div>
        <div>
          <label for="password">pw: </label>
          <input id="password" type="text" v-model="password" />
        </div>
        <button
          :disabled="!(isUsernameValid && password)"
          type="submit"
          class="btn"
        >
          로그인
        </button>
      </form>
      <p class="log">{{ logMessage }}</p>
    </div>
  </div>
```


### Login 후 페이지이동 에 관하여

- [Programmatic Navigation](https://router.vuejs.org/guide/essentials/navigation.html)
  ![](assets/2022-11-15-12-43-43.png)

- 그런데 로그인 form  에서 정보를 App-header 로 옮겨야되는데
  ![](assets/2022-11-15-12-52-42.png)
  1. emit -> props 를 하는 방법인데...
  2. event-bus로 바로 찔러주거나
  3. 아니면 Store[`vuex`, `pinia`]

- 실습에서는 `vuex`를 씀
  ![](assets/2022-11-15-13-02-50.png)
  ![](assets/2022-11-15-13-03-10.png)
  - state : data
  - mutations : methods(동기)
  ![](assets/2022-11-15-13-14-19.png) 
    ```js 
    // 호출시
    this.$store.commit('setUsername', { username: this.username });
    ```
    ![](assets/2022-11-15-13-13-50.png)
  - getters : computed
  ![](assets/2022-11-15-13-24-55.png)
  ![](assets/2022-11-15-13-28-47.png)
------------------------
- 그럼 로그아웃도 구현하자
![](assets/2022-11-15-13-37-37.png)

