### 오라클

- [알아두면 좋은 명령어](https://m.blog.naver.com/deersoul6662/221466474481)

- [start/down 명령어](https://docs.oracle.com/en/database/oracle/oracle-database/18/xeinl/starting-and-stopping-oracle-database.html)

- UNION ALL 할때 한 실수들
  - VARCHAR 인지 NVARCHAR 인지 확인해야됨 -> UNISTR(칼럼) 으로 맞추자
  - SORT 가 있으면 안된다 
  - ```sql

	 ```


- docker-compose 로 만드는 19c
  - [참고영여기사](https://arno-schots.medium.com/building-and-running-oracle-database-19-3-0-ee-docker-containers-8147b5a00a51)
  - [docker-image생성-GITHUB](https://github.com/steveswinsburg/oracle19c-docker)
  - [dockcer-compose 파일셋팅](https://growupcoding.tistory.com/27)
  ```shell
  mkdir -p ~/dev/compose/oradata/ora19_data
  chmod -Rf 777 ~/dev/compose/oradata/ora19_data
  touch ~/dev/compose/or19env
  touch ~/dev/compose/docker-compose.ora19c.yml  
  ```
  ```properties
  ORACLE_PWD=oracle
  ORACLE_SID=orclcdb
  ```
  ```yaml
  version: '3.8'
  services:
      ora19c:
          container_name: ora19c
          env_file:
              - ora19env
          image: oracle/database:19.3.0-ee
          ports:
              - 1521:1521
              - 5500:5500
          volumes:
              - ./oradata/ora19_data:/opt/oracle/oradata
          privileged: true
  ```

