1. python install check

``` bash
## 파이썬관리 오픈소스
pyenv -v

## 없으면 아래 명령어로 install / mac-homebrew
brew install pyenv

## 설치가능한 패키지 확인
pyenv install --list

## 파이썬 설치
pyenv install 3.9.1

## 파이썬 버전 확인 및 글로벌 버젼 변경
python --version
pyenv global 3.9.1

# python2 버젼이 설치되어 있어 강제로 3번을 쓰기 위해서 환경설정 적용시킴 <<
echo "alias python=/usr/local/bin/python3" >> ~/.zshrc
echo "alias pip=/usr/local/bin/pip3" >> ~/.zshrc

# 위 방법으로 안되면 강제로 넣었던 명령어를 제거하고 아래로 대처
echo "## python - pyenv" >> ~/.zshrc
echo 'eval "$(pyenv init -)"' >> ~/.zshrc
source ~/.zshrc

## 실행시 audfuddj
python3 
pip3

```

2. repo init

```bash
## fastapi
pip3 install "fastapi[all]"

# ASGI 서버 설치
pip3 install "uvicorn[standard]"

## uvicon 실행
# main: the file main.py (the Python "module").
# app: the object created inside of main.py with the line app = FastAPI().
# --reload: make the server restart after code changes. Only use for development.
uvicorn main:app --reload


```


3. 기타 참고

- [셋팅링크1](https://zarazio.tistory.com/11)

- [셋팅링크2](https://www.daleseo.com/python-pyenv/)

- [셋팅링크3-가장도움됨](https://github.com/pyenv/pyenv/issues/849)

- [fastAPI-참고영상](https://www.youtube.com/watch?v=5A67mQ2Pt9s)

- [WSGI란-python스크립트와 웹서버연결](https://yeowool0217.tistory.com/662)

- [ASGI-위에꺼 비동기버젼](https://wookkl.tistory.com/45)

- [vscode 추천 extension](https://inpa.tistory.com/entry/VS-Code-%E2%8F%B1%EF%B8%8F-%EC%BD%94%EB%94%A9%EC%97%90-%EC%9C%A0%EC%9A%A9%ED%95%9C-%EB%8F%84%EA%B5%AC-%EC%B6%94%EC%B2%9C)

4. 공식사이트

- [fastAPI](https://fastapi.tiangolo.com/)

- [uvicon](http://www.uvicorn.org/)