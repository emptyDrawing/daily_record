### SSH Config

- ssh config 시 쉽게 key 복사하는 법 [링크](https://www.cyberciti.biz/faq/how-to-set-up-ssh-keys-on-rhel-8/)

```shell
### 키생성
ssh-keygen
#  ssh-keygen -t rsa -f ~/.ssh/my_vm_key

### 복사
ssh-copy-id 
#  ssh-copy-id -i ~/.ssh/my_vm_key root@10.0.55.221


### ssh-config 설정
Host myTest
    HostName 10.0.55.221
    User root
    IdentityFile ~/.ssh/my_vm_key
    
```