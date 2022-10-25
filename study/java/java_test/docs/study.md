## 다루는 내용
- Junit5 / Mockito

## JUnit5

### 개요
- 대체제 : TestNG, Spock, ...
- 4와 다르게 세부모듈로 나눠짐. [ Jupiter / Vintage / JUnit Platform ]
    - Platform:  테스트를 실행해주는 런처 제공. TestEngine API 제공.
    - Jupiter: TestEngine API 구현체로 JUnit 5를 제공.
    - Vintage: JUnit 4와 3을 지원하는 TestEngine 구현
    - [공식가이드](https://junit.org/junit5/docs/current/user-guide/)

### 시작

- springboot 2.2.1 버젼 이상은 JUnit5 가 기본
- 5버젼부터는 @Test 를 붙이는 메소드에 굳이 public 을 붙이지 않아도 됨.
    ```xml
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.5.2</version>
        <scope>test</scope>
    </dependency>
    ```
    ![](assets/2022-10-24-17-14-58.png)

    ```java

    public class StudyTest {

        // 5 버젼부터는 굳이 public 을 안달아도 됩니다.
        @Test
        void create() {
            Study study = new Study();
            assertNotNull(study);
            System.out.println("create");
        };

        @Test
        void create1() {
            System.out.println("create1");
        };

        @Test
        @Disabled
        void crash() {
            System.out.println("crash");
        }


        //All은 private 안되며 void 이외를 리턴하면 안됨, 반드시 static 형이어야함.
        @BeforeAll
        static void beforeAll() {
            System.out.println("beforeAll");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("afterAll");
        }

        // Each 는 굳이 static 일필요는 없음음 
        @BeforeEach
        void beforeEach() {
            System.out.println("beforeEach");
        }

        @AfterEach
        void afterEach() {
            System.out.println("afterEach");
        }
    }


    // 결과
    // beforeAll
    // beforeEach
    // create
    // afterEach
    // beforeEach
    // create1
    // afterEach
    // afterAll
    ```

- @DisplayNameGeneration / @DisplayName
  - 기본적으로 메소드 이름으로 테스트 이름이 적혀짐
  ![](assets/2022-10-24-17-30-25.png)
  - @DisplayNameGeneration : class, metho에 달수 있으며, 전략에 해당하는 구현체를 넣어주어야함.
  - @DisplayName : 원하는 문자 모든지 넣어도 됨.
  ![](assets/2022-10-24-17-41-56.png)

### Assertion
- 실제 테스트에서 검증하고자 하는 내용
![](assets/2022-10-24-17-48-45.png)
```java
// 기대값 먼저, 실제 나오는 값, 메세지- 람다로도 가능함.
// 람다로 만들면 실패했을때만 메세지 연산을 함.
assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 Status 는 DRAFT");

```

