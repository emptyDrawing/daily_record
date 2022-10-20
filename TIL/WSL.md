### WSL 에서 Eclispe  설치

```shell
################ 다운로드 설치 ###############
wget https://ftp.yz.yamagata-u.ac.jp/pub/eclipse/technology/epp/downloads/release/2022-09/R/eclipse-jee-2022-09-R-linux-gtk-x86_64.tar.gz

tar xvzf eclipse-jee-2022-09-R-linux-gtk-x86_64.tar.gz

#### 실행용 alias
alias ecpr='~/util/eclipse/eclipse'
#

#### 아래 에러 발생시 로 의존성 파일 설치 - ubuntu v20.4
sudo apt install libswt-gtk-4-jni
###
# cat /home/ecsuser/util/eclipse/configuration/1666225081445.log
###
# eclipse.buildId=4.25.0.I20220831-1800
# java.version=17.0.4.1
# java.vendor=Eclipse Adoptium
# BootLoader constants: OS=linux, ARCH=x86_64, WS=gtk, NL=en
# Framework arguments:  -product org.eclipse.epp.package.jee.product
# Command-line arguments:  -os linux -ws gtk -arch x86_64 -product org.eclipse.epp.package.jee.product
# !ENTRY org.eclipse.osgi 4 0 2022-10-20 09:18:02.520
# !MESSAGE Application error
# !STACK 1
# java.lang.UnsatisfiedLinkError: Could not load SWT library. Reasons:
#         no swt-pi4-gtk-4954r7 in java.library.path: /usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib
#         no swt-pi4-gtk in java.library.path: /usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib
#         no swt-pi4 in java.library.path: /usr/java/packages/lib:/usr/lib64:/lib64:/lib:/usr/lib
#         Can't load library: /home/ecsuser/.swt/lib/linux/x86_64/libswt-pi4-gtk-4954r7.so
##

```
