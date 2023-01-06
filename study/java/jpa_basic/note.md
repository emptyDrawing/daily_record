# 목표

- 객체[와 테이블을 제대로 설계, 맵핑하는 것이 중요함.
- JPA 내부동작방식의 이해 - 어떤 SQL, 언제 실행하는지 중요함.


### 상속 - 슈퍼타입/서브타입

- 객체는 상속, RDB는 슈퍼타입-서브타입인데..
- join 하고.. 저장할때는 두개 SQL 날리고 등등..
- 객체는 상속을 참조  / RDB는 키값이니까 필드(값)
![](assets/2023-01-06-15-06-17.png)

- 객체는 객체 그래프를 탐색할수 있어야됨.
![](assets/2023-01-06-15-09-36.png)

### 발전사

- EJB 엔티티빈(자바표준) -> 하이버네이트(오픈소스) -> JPA(자바표준)
- 트랜잭션 커밋하기전까지 모아두었다가 보낸다. ( 그래서 같은 트랜잭션에서 뽑은 객체-같은키는 같다)
- 지연로딩 / 즉시로딩 : 필요한 부분 즉시로딩하는 편.
![](assets/2023-01-06-15-37-14.png)


### 준비물
- H2 ( docker로 준비함 )
  ```yml
	version: "1"
	services:
			h2-db:
					container_name: h2-db
					image: oscarfonts/h2
					volumes: 
							- ./h2/:/opt/h2-data
					environment:
							H2_OPTIONS: -ifNotExists
					ports: 
							- "1521:1521"
							- "18081:81"
	```
	- localhost:18081
	- jdbc:h2:tcp://localhost:1521/test 
  - sa / (없음)
- h2 버젼확인
![](assets/2023-01-06-16-31-37.png)


- JPA(하이버네이트) 설정
 - persistence.xml
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2"
 xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
 <persistence-unit name="hello">
 <properties>
 <!-- 필수 속성 -->
 <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
 <property name="javax.persistence.jdbc.user" value="sa"/>
 <property name="javax.persistence.jdbc.password" value=""/>
 <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/>
 <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>

 <!-- 옵션 -->
 <property name="hibernate.show_sql" value="true"/>
 <property name="hibernate.format_sql" value="true"/>
 <property name="hibernate.use_sql_comments" value="true"/>
 <!--<property name="hibernate.hbm2ddl.auto" value="create" />-->
 </properties>
 </persistence-unit>
</persistence>
 ```
 ![](assets/2023-01-06-16-35-49.png)

- 여기서 중요시 볼 포인트는 
```
	<property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
```
	- JPA 자체는 특정데이터베이스에 종속적이지 않지만,
  ![](assets/2023-01-06-16-56-46.png)
	
	- 와 같이 각 DB마다 특이점[표준적이지 않는] 이 존재한다.
	![](assets/2023-01-06-16-58-23.png)
  	- H2 : org.hibernate.dialect.H2Dialect
    - Oracle 10g : org.hibernate.dialect.Oracle10gDialect
    - MySQL : org.hibernate.dialect.MySQL5InnoDBDialect 


### JPA 구동방식
![](assets/2023-01-06-17-02-39.png)
- java11 일때 추가할것 ([참고링크](https://www.inflearn.com/questions/13985/java11-javax-xml-bind-jaxbexception-%EC%97%90%EB%9F%AC))
  ```xml
	<dependency>
    <groupId>javax.xml.bind</groupId>
     <artifactId>jaxb-api</artifactId>
    <version>2.3.0</version>
 </dependency>
	```

![](assets/2023-01-06-17-35-27.png)
- 뭔가 되는거 같지만 안됨.
  - Transaction 만들어서 commit 까지 해줘야됨
	![](assets/2023-01-06-17-38-49.png)

![](assets/2023-01-06-17-49-25.png)
![](assets/2023-01-06-17-50-10.png)
- update는 가져온 객체를 수정하면 그걸 JPA가 감지했다가 수정함.

![](assets/2023-01-06-17-51-49.png)
- 그래서 모든데이터 변경은 트랜잭션 안에서 실행되어야 함.


### JPQL 이란
- 그래서 검색조건이 다양할때는 어떻게 해야되냐?
  - 이럴때 쓰는게 JPQL
![](assets/2023-01-06-17-56-13.png)
![](assets/2023-01-06-17-56-24.png)
- 특이점은 쿼리가 생긴건데.. JPQL에서 쓴 `m`은 `엔티티`라는 의미임.

- 이것의 장점은 아래처럼 방언만 바꾸면 쿼리를 알아서 만들어준다.
![](assets/2023-01-06-17-59-58.png)
![](assets/2023-01-06-17-59-40.png)
![](assets/2023-01-06-18-03-46.png)
![](assets/2023-01-06-18-04-28.png)
- GooD
