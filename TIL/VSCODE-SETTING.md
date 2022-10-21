

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
