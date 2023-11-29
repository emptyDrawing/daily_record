
### swwpfile 생성 및 재시작시 자동 마운트

```shell
## 생성
sudo dd if=/dev/zero of=/var/spool/swap/swapfile4G bs=1MiB count=4096
sudo chmod 600 /var/spool/swap/swapfile4G

## 스왑으로변경
sudo mkswap /var/spool/swap/swapfile4G
## 스왑설설정
sudo swapon /var/spool/swap/swapfile4G

# 확인
swapon -s

# 기존사용 제거
swapoff /var/spool/swap/swapfile
# 확인
swapon -s

# 부팅시 셋팅
sudo vim /etc/fstab
### UUID=09934798-af72-4741-b12f-aa84a5850aa1 /                       ext4    defaults        1 1
###  /var/spool/swap/swapfile4G    none    swap    defaults    0 0
```


### 윈도우에서 SSH 접속 시 pem 파일 권한변경

```shell
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@         WARNING: UNPROTECTED PRIVATE KEY FILE!          @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Permissions 0644 for 'amazonec2.pem' are too open.
It is recommended that your private key files are NOT accessible by others.
This private key will be ignored.
bad permissions: ignore key: amazonec2.pem
Permission denied (publickey).
```
- 위처럼 뜨면 window에서 권한설정을 따로 해줘야 하는데 cmd 명령어로 해야함.

```shell
# cmd에서만 되고 powershell에서는 안됨
# myec2.pem 자리에 본인의 pem 파일 명을 대입하면 됨
# cmd는 pem 파일이 있는 폴더에서 실행해야 함

icacls.exe myec2.pem /reset
icacls.exe myec2.pem /grant:r %username%:(R)
icacls.exe myec2.pem /inheritance:r
```

- 출처 : [참고링크](https://dabid.tistory.com/11)



### 적용

https://velog.io/@coastby/Nginx-SSL-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0