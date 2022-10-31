## Mockito

### 준비물 및 공식 링크
- [java 트렌드](https://www.jetbrains.com/lp/devecosystem-2021/java/) 
- [단위테스트 고찰](https://martinfowler.com/bliki/UnitTest.html)
- [Mockito 공식](https://site.mockito.org/)

- Springboot 를 사용하지 않으면 별도 의존성 추가
   ```xml
   <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>3.1.0</version>
      <scope>test</scope>
   </dependency>

   <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>3.1.0</version>
      <scope>test</scope>
   </dependency>
   ```


### Mockito 시작하기
![](assets/2022-10-31-12-35-36.png)
![](assets/2022-10-31-12-50-47.png)

- 그런데 이렇게만 하면 아직은 Null
![](assets/2022-10-31-12-56-42.png)
```java
// 이럴대 Extension 추가!

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

	@Mock
	MemberService memberService;


	@Test
   // 해당 메소드에서만 쓰고 싶을때
	void create_study_service(@Mock StudyRepository repository) {

		StudyService studyService = new StudyService(memberService, repository);
		assertNotNull(studyService);
	}

}
```
