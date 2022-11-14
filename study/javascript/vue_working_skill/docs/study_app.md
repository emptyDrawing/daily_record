### project 설치 옵션
![](assets/2022-11-14-10-23-32.png)


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

