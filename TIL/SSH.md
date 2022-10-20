### SSH Config

- ssh config 시 쉽게 key 복사하는 법 [링크](https://www.cyberciti.biz/faq/how-to-set-up-ssh-keys-on-rhel-8/)

```shell
### 키생성
ssh-keygen

### 복사
ssh-copy-id 

### ssh-config 설정
Host myTest
    HostName 10.0.55.221
    User root
    IdentityFile /home/ecsuser/.ssh/id_rsa.pub
    
### 그런데 자꾸 
# Load key "/home/ecsuser/.ssh/id_rsa.pub": invalid format 
# 라고 뜨는데 확인 필요
```