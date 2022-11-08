### java rut config
```json
// vscode run config에 잘안된거
        {
            "type": "java",
            "name": "bytecode with option",
            "request": "launch",
            "mainClass": "my.sskim.App",
            "projectName": "bytecode",
            "vmArgs": "-javaagent /home/ecsuser/study/daily_record/study/java/javabasic/bytecode2/target/bytecode2-1.0-SNAPSHOT.jar"
        }
// 잘된거 - 옵션사용법을 잘 확인하자
        {
            "type": "java",
            "name": "bytecode with option",
            "request": "launch",
            "mainClass": "my.sskim.App",
            "projectName": "bytecode",
            "vmArgs": "-javaagent:/home/ecsuser/study/daily_record/study/java/javabasic/bytecode2/target/bytecode2-1.0-SNAPSHOT.jar"
        }

```

### Makrdown 이미지 복사

- window - wsl2를 사용하는 환경이었는데 makrdown 에 이미지 넣기 어려워서 어려 extendsion 을 찾아봄
- 비슷한 문제 질문 - [링크](https://github.com/mushanshitiancai/vscode-paste-image/issues/56)
  - [windosw-wsl2 도 되는 애](https://marketplace.visualstudio.com/items?itemName=dzylikecode.md-paste-enhanced) : Extendsion 이것저것 깔았는데 애가 최고
  - [애도 나중에 더 보자](https://github.com/telesoho/vscode-markdown-paste-image)
  - [이건 공식같은데 wsl2 에서 잘안되던데](https://marketplace.visualstudio.com/items?itemName=dendron.dendron-paste-image)


### Markdown 목차 자동생성
  - //@import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} 라는걸 입력하면 되는데
  - 해당 플러그인이 필요 : [Markdown Preview Enhanced](https://marketplace.visualstudio.com/items?itemName=shd101wyy.markdown-preview-enhanced)


### vscode annotation processor enable
  - [해결책 유튜브](https://www.youtube.com/watch?v=cN9FNVtJeR8) : [google auto-value](https://mvnrepository.com/artifact/com.google.auto.value/auto-value) / [google auto-annotations](https://mvnrepository.com/artifact/com.google.auto.value/auto-value-annotations)
   ```xml
  <!-- https://mvnrepository.com/artifact/com.google.auto.value/auto-value -->
  <dependency>
      <groupId>com.google.auto.value</groupId>
      <artifactId>auto-value</artifactId>
      <version>1.9</version>
      <scope>provided</scope>
  </dependency>


  <!-- https://mvnrepository.com/artifact/com.google.auto.value/auto-value-annotations -->
  <dependency>
      <groupId>com.google.auto.value</groupId>
      <artifactId>auto-value-annotations</artifactId>
      <version>1.9</version>
  </dependency>

  ```


### static Import 셋팅
- [참고링크](https://bingbingpa.github.io/vscode-static-import/) 
```json

"java.completion.favoriteStaticMembers": [
    "org.junit.Assert.*",
    "org.junit.Assume.*",
    "org.junit.jupiter.api.Assertions.*",
    "org.junit.jupiter.api.Assumptions.*",
    "org.junit.jupiter.api.DynamicContainer.*",
    "org.junit.jupiter.api.DynamicTest.*",
    "org.mockito.Mockito.*",
    "org.mockito.ArgumentMatchers.*",
    "org.mockito.Answers.*",
    "org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*",
    "org.springframework.test.web.servlet.result.MockMvcResultMatchers.*"
]

```

### Code Snippet 만들기
-- [vscode code-snippet 만들기](https://snippet-generator.app/?description=inputstyle&tabtrigger=my-input-style&snippet=++input%3Afocus+%7B%0A++++outline%3A+none%3B%0A++%7D%0A++.inputBox+%7B%0A++++background%3A+white%3B%0A++++height%3A+50px%3B%0A++++line-height%3A+50px%3B%0A++++border-radius%3A+5px%3B%0A++%7D%0A++.inputBox+input+%7B%0A++++border-style%3A+none%3B%0A++++font-size%3A+0.9rem%3B%0A++%7D%0A++.addContainer+%7B%0A++++float+%3A+right%3B%0A++++background+%3A+linear-gradient%28to+right%2C+%236478FB%2C+%238763F8%29%3B%0A++++display%3A+block%3B%0A++++width+%3A+3rem%3B%0A++++border-radius%3A+0+5px+5px+0%3B%0A++%7D%0A++.addBtn+%7B%0A++++color%3A+white%3B%0A++++vertical-align%3A+middle%3B%0A++%7D&mode=vscode)