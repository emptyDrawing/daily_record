## ArchUnit

- 공식문서 : https://www.archunit.org/
  - 애플리케이션의 아키텍처를 테스트 할 수 있는 오픈 소스 라이브러리로, 패키지, 클래스, 레이어, 슬라이스 간의 의존성을 확인할 수 있는 기능을 제공한다.

- 아키텍처 테스트 유즈 케이스
  - A 라는 패키지가 B (또는 C, D) 패키지에서만 사용 되고 있는지 확인 가능.
  - *Serivce라는 이름의 클래스들이 *Controller 또는 *Service라는 이름의 클래스에서만
참조하고 있는지 확인.
  - *Service라는 이름의 클래스들이 ..service.. 라는 패키지에 들어있는지 확인.
  - A라는 애노테이션을 선언한 메소드만 특정 패키지 또는 특정 애노테이션을 가진 클래스를 호출하고 있는지 확인.
  - 특정한 스타일의 아키텍처를 따르고 있는지 확인.
- 참고
  - https://blogs.oracle.com/javamagazine/unit-test-your-architecture-with-archunit
  - https://www.archunit.org/userguide/html/000_Index.html
  - [Moduliths](https://github.com/moduliths/moduliths)


### 준비물
```xml
<!-- pom.xml -->
<dependency>
    <groupId>com.tngtech.archunit</groupId>
    <artifactId>archunit-junit5-engine</artifactId>
    <version>0.12.0</version>
    <scope>test</scope>
</dependency>
```

### 적용법
```java
@Test
public void Services_should_only_be_accessed_by_Controllers() {
    
    // 1. 특정 패키지에 해당하는 클래스-바이트코드를 읽어온다.
    JavaClasses importedClasses = new 
        ClassFileImporter().importPackages("com.mycompany.myapp");

    // 2. 확인할 규칙을 정의
    ArchRule myRule = classes()
        .that().resideInAPackage("..service..")
        .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    // 3. 읽어들인 클래스가 규칙을 따르고 있는지 확인
    myRule.check(importedClasses);
}
```
- JUnit 5 확장팩 제공
  - `@AnalyzeClasses` : 클래스를 읽어들여서 확인할 패키지 설정
  - `@ArchTest` : 확인할 규칙 정의


### 예제1 : 패키지 의존성 확인
![](assets/2022-11-02-14-41-43.png)

- 1번 문제 예제
```java
@Test
public void packageDependencyTests() {
    // 이 프로젝트의 기본 패키지
    JavaClasses importedClass = new ClassFileImporter().importPackages("test.sskim.junit"); 
        
    // 1) ..domain.. 패키지에 있는 클래스는 ..study.., ..member.., ..domain..에서 참조 가능.
    ArchRule domainPackageRule = ArchRuleDefinition.classes().that()
        .resideInAPackage("..domain..")
        .should().onlyBeAccessed().byClassesThat()
        .resideInAnyPackage("..study..","..member..","..domain..");
    
    domainPackageRule.check(importedClass);
}
```
![](assets/2022-11-02-14-57-11.png)
- 왜그랬는지 보니까 전에 만들던 테스트 때문에 그렇다.. 그래서 이걸 위치를 옮겨줌.
![](assets/2022-11-02-14-58-07.png)
![](assets/2022-11-02-14-59-35.png)
    - GOOD!!

- 2번 문제 예제
```java
// 2) ..member.. 패키지에 있는 클래스는 ..study..와 ..member..에서만 참조 가능.
ArchRule domainPackageRule2 = ArchRuleDefinition.classes().that()
    .resideInAPackage("..member..")
    .should().onlyBeAccessed().byClassesThat()
    .resideInAnyPackage("..study..","..member..");

domainPackageRule2.check(importedClass);
```
- 3번 문제 예제
```java
// 3) (반대로) ..domain.. 패키지는 ..member.. 패키지를 참조하지 못한다.
// noClasses() 를 주의
ArchRule domainPackageRule3ReverseCheck = ArchRuleDefinition.noClasses().that()
    .resideInAPackage("..domain..")
    .should().accessClassesThat().resideInAPackage("..member..");

domainPackageRule3ReverseCheck.check(importedClass);
```

- 4번 문제
```java
// 4) ..study.. 패키지에 있는 클래스는 ..study.. 에서만 참조 가능
ArchRule studyPackageRule4 = ArchRuleDefinition.noClasses().that()
    .resideOutsideOfPackage("..study..")
    .should().accessClassesThat()
    .resideInAPackage("..study..");
// 로 하거나
ArchRule studyPackageRule4 = ArchRuleDefinition.classes().that()
    .resideInAPackage("..study..")
    .should().onlyBeAccessed().byClassesThat()
    .resideInAPackage("..study..");
// 하거나 인데

// 실제 검증해보면
studyPackageRule4.check(importedClass);
```
  ![](assets/2022-11-02-15-26-16.png)
  - Enum이 문제였다...
  ![](assets/2022-11-02-15-26-55.png)
  - 그래서 위처럼 고치고
  ![](assets/2022-11-02-15-27-54.png)
  - GOOD!!!

- 일부러 순환참조 하게 코드를 만들어보자
![](assets/2022-11-02-15-44-51.png)
  - 주석 된 채로 두면 5번에 걸림
  ![](assets/2022-11-02-15-46-58.png)
  - 주석 살리면 4번에 걸림
  ![](assets/2022-11-02-15-57-11.png)


### 예제2 : JUnit5와 연동해서 간추리기

- 읽어들이 패키지-class 셋팅
```java
// App.class 가 있는 위치에 있는 Bytecode 를 읽어오겠다.
import test.sskim.junit.App;
@AnalyzeClasses(packagesOf = App.class)

public class ArchTests {
    // 그리고 룰 배치
    @ArchTest
    // 1) ..domain.. 패키지에 있는 클래스는 ..study.., ..member.., ..domain..에서 참조 가능.
    ArchRule domainPackageRule = ArchRuleDefinition.classes().that()
        .resideInAPackage("..domain..")
        .should().onlyBeAccessed().byClassesThat()
        .resideInAnyPackage("..study..","..member..","..domain..");

    @ArchTest
    // 2) ..member.. 패키지에 있는 클래스는 ..study..와 ..member..에서만 참조 가능.
    ArchRule domainPackageRule2 = ArchRuleDefinition.classes().that()
        .resideInAPackage("..member..")
        .should().onlyBeAccessed().byClassesThat()
        .resideInAnyPackage("..study..","..member..");
    ...
}
```
![](assets/2022-11-02-16-12-49.png)

- 그런데 애넨 기존의 @Test 를 확장해서 만든게 아니라 
- 별도의 jupiter 엔진을 만들어서 돌림
![](assets/2022-11-02-16-13-36.png)
![](assets/2022-11-02-16-14-12.png)


### 예제3 : Class 간 의존성 확인

![](assets/2022-11-02-16-16-44.png)

```java
@ArchTest
// 1) ..Controller는 ..Service와 ..Repository를 사용할 수 있다.
ArchRule controllerClassRule = ArchRuleDefinition.classes().that()
    .haveSimpleNameEndingWith("Controller")
    .should().accessClassesThat().haveSimpleNameEndingWith("Service")
    .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");


@ArchTest
// 2) ..Repository는 ...Service와 ...Controller를 사용할 수 없다.
ArchRule repositoryClassRule = ArchRuleDefinition.noClasses().that()
    .haveSimpleNameEndingWith("Repository")
    .should().accessClassesThat().haveSimpleNameEndingWith("Service")
    .orShould().accessClassesThat().haveSimpleNameEndingWith("Controller");


@ArchTest
// 3) Study* 로 시작하는 클래스는 ..study.. 패키지에 있어야 한다.
// 그냥하니까 domain에 있는 Study, StudyStatus 때문에 안됨.
ArchRule studyNameRule = ArchRuleDefinition.classes().that()
    .haveSimpleNameStartingWith("Study")
    .and().areNotAnnotatedWith(Entity.class)     // domain.study 제외
    .and().areNotEnums()                                        // StudyStatus 제외
    .should().resideInAPackage("..study..");

```
  - 일부러 테스트 실패하게 만들었는데..
  ![](assets/2022-11-02-16-33-01.png)
  ![](assets/2022-11-02-16-33-48.png)
  - vscode 에서는 에노테이션 쓰니까 원인 찾기가 어렵다.. -> `ctrl + ;` + `ctrl + 0`
  ![](assets/2022-11-03-09-17-18.png)


### 더 찾아볼 것
  
- [Freezing Arch Rules](https://www.archunit.org/userguide/html/000_Index.html#_freezing_arch_rules) 를 쓰면 현재 패키지에서 깨진걸 두고 새롭게 만들어지는 애한테도 적용가능하다.

- [PlantUML](https://www.archunit.org/userguide/html/000_Index.html#_plantuml_component_diagrams_as_rules) 과도 연동가능함.

- 공식 가이드 문서 : https://www.archunit.org/userguide/html/000_Index.html
- 사용법 공식 : https://www.archunit.org/use-cases