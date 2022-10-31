
## Docker - Container Test

### 준비물
- https://www.testcontainers.org/
```java
@SpringBootTest
@ActiveProfiles("test")
public class StudyServiceTest {

	@Mock
	MemberService memberService;

    // repository 실제객체로 변경
	@Autowired
	StudyRepository repository;
	...

}

// 그외 자잘하게 dto, service 코드 등이 바뀜
```
```properties
#src/resources/application.properties
spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:postgresqlL://localhost:5432/study
spring.datasource.username=study
spring.datasource.password=study

#test/resources/application.properties
spring.datasource.url=jdbc:h2:memory:/studydb
spring.datasource.username=sa
spring.datasource.password=password
spring.datasource.driver-class-name=org.h2.Driver

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```
```xml
<!-- pom.xml -->
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
</dependency>
<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>test</scope>
</dependency>
```
