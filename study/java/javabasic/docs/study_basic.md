
## JVM 이해하기


- JVM (Java Virtual Machine)
    - 자바 가상 머신으로 자바 바이트 코드(.class 파일)를 OS에 특화된 코드로 변환(인터프리터와 JIT 컴파일러)하여 실행한다.
    - 바이트 코드를 실행하는 표준(JVM 자체는 표준)이자 구현체(특정 밴더가 구현한 JVM)다.
    - [JVM 스팩](https://docs.oracle.com/javase/specs/jvms/se11/html/)
    - JVM 밴더: 오라클, 아마존, Azul, ...
    - JVM 자체는 OS에 종속적임.

- JRE (Java Runtime Environment): JVM + 라이브러리
    - 자바 애플리케이션을 실행할 수 있도록 구성된 배포판.
    - JVM과 핵심 라이브러리 및 자바 런타임 환경에서 사용하는 프로퍼티 세팅이나 리소스 파일을 가지고 있다.
    - 개발 관련 도구는 포함하지 않는다. (그건 JDK에서 제공)
    - 9 버젼부터는 module - jlink 를 통해 작은 jre 도 만들 수 있음.

- JDK (Java Development Kit): JRE + 개발 툴
    - JRE + 개발에 필요할 툴
    - 소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적.
    - 오라클은 자바 11부터는 JDK만 제공하며 JRE를 따로 제공하지 않는다.
    - Write Once Run Anywhere
    - JDK에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class 파일)로 컴파일 할 수 있다.
    - `javap -c 클래스명` 으로 바이트코드를 확인 할수 있음
    - appletviewer, javadoc, jar, jconsole, extchek 등등...


- JVM 언어 : JAVA, 클로저, 그루비, JRuby, Jython, Kotlin, Scala, ...

- 참고
    - [JIT 컴파일러](https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/)
    - [JDK, JRE 그리고 JVM](https://howtodoinjava.com/java/basics/jdk-jre-jvm/)
    - [https://en.wikipedia.org/wiki/List_of_JVM_languages]

```shell
$ korlinx HellKt -include-runtime -d hellokt.jar 
$ java hellokt.jar 로 실행가능
```

### JVM 구조
- 클래스 로더 시스템 : 컴파일을 한 class 에서 바이트코드를 읽고 저장
    - 로딩 : class 읽어오고 
    - 링크 : 래퍼런스 연결하고
    - 초기화 : static, 값 초기화 및 변수 할당

- 메모리 : 5가지 영역으로 나눠짐
    - 메소드(Method) : 클래스이름, 부모클래스 이름, 메소드, 변수 등을 저장
    - 힙(Heap) : 객체를 저장, 공유함.
    - 스택(Stack) / PC-registers / 네이티드 메소드 스택 : 쓰레드 마다 공유하는 자원이 들어감. [참고링크](https://javapapers.com/core-java/java-jvm-run-time-data-areas/#Program_Counter_PC_Register)

- 실행엔진
    - 인터프리터 : 바이트코드를 한줄 한줄 읽어서 네이티브로 컴파일 해서 실행.
    - JIT (Just in Time) 컴파일러 : 인터프리터의 효율을 올리기 위해서 반복되는 바이트코드를 네이티브로 변환시켜둔다.
    - GC (Garbage Collector) : 더이상 참조되지 않는 객체 / 메모리르 정리한다.

- JNI ( Javaeeee Natvie Intergert)
- [네이트브 메소드만드는 링크](https://medium.com/@bschlining/a-simple-java-native-interface-jni-example-in-java-and-scala-68fdafe76f5f )


### 클래스로더 좀더 자세히

- 로딩 -> 링크 -> 초기화 순으로 진행됨

- 로딩
    - 클래스 로더가 .class 파일을 읽고 그 내용에 따라 적절한 바이너리 데이터를 만들고 “메소드” 영역에 저장.
    - 이때 메소드 영역에 저장하는 데이터
        - FQCN ( Fully-Qualified Class Name )
        - 클래스 | 인터페이스 | 이늄
        - 메소드와 변수
    - 로딩이 끝나면 해당 클래스 타입의 Class 객체[ Class<MyApp> 같은 ] 를 생성하여 “힙" 영역에 저장.

- 링크 :Verify, Prepare, Reolve(optional) 세 단계로 나눠져 있다.
    - 검증: .class 파일 형식이 유효한지 체크한다.
    - Preparation: 클래스 변수(static 변수)와 기본값에 필요한 메모리
    - Resolve: 심볼릭 메모리 레퍼런스(힙영역에 있는) 를 메소드 영역에 있는 실제 레퍼런스로 교체한다.

- 초기화 : Static 변수의 값을 할당한다. (static 블럭이 있다면 이때 실행된다.)
    - 클래스 로더는 계층 구조로 이뤄져 있으면 기본적으로 세가지 클래스 로더가 제공된다.
    - 부트 스트랩 클래스 로더 -  JAVA_HOME\lib에 있는 코어 자바 API를 제공한다. 최상위 우선순위를 가진 클래스 로더
    - 플랫폼 클래스로더 - JAVA_HOME\lib\ext 폴더 또는 java.ext.dirs 시스템 변수에 해당하는 위치에 있는 클래스를 읽는다.
    - 애플리케이션 클래스로더 - 애플리케이션 클래스패스(애플리케이션 실행할 때 주는 -classpath 옵션 또는 java.class.path 환경 변수의 값에 해당하는 위치)에서 클래스를 읽는다.


## 바이트코드

- jacoco 수업자료
    - [https://www.eclemma.org/jacoco/trunk/doc/index.html]
    - [http://www.semdesigns.com/Company/Publications/TestCoverage.pdf]


- jacoco 삽질기 [참고링크](https://medium.com/@karlrombauts/setting-up-unit-testing-for-java-in-vs-code-with-maven-3dc75579122f)
    ```xml
    <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <version>0.8.4</version>
        <executions>
            <execution>
                <goals>
                    <goal>prepare-agent</goal>
                </goals>
            </execution>
            <execution>
                <id>report</id>
                <phase>prepare-package</phase>
                <goals>
                    <goal>report</goal>
                </goals>
            </execution>
        </executions>
    </plugin>

    ```
    ```shell
    # 
     mvn jacoco:prepare-agent test install jacoco:report
    ```

- 커버리지를 어떻게 채워지는지는 바이트코드 수를 세고 


### 바이트코드 조작

- 바이트코드 조작 라이브러리
    - ASM: https://asm.ow2.io/
    - Javassist: https://www.javassist.org/
    - [추천] ByteBuddy: https://bytebuddy.net/#/

- code
```java
    // 이렇게 하면 println 하기전에 EmptyMoja를 한번 불러(조작)와서 값을 빼낼수 있는데 다른 곳에서 먼저 
    // EmptyMoja 를 불러로 오면 잘 안된다.
    public static void main(String[] args) {
        ClassLoader classLoader = App.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);

        try {
            new ByteBuddy()
                    .redefine(
                            typePool.describe("my.sskim.EmptyMoja").resolve(),
                            ClassFileLocator.ForClassLoader.of(classLoader))
                    .method(named("pullout")).intercept(FixedValue.value("Rabbit"))
                    .make().saveIn(new File("/home/ecsuser/study/daily_record/study/java/javabasic/target/classes"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(new EmptyMoja().pullout());
    }
```

### agent-jar 로 만들어서 조작

- Javaagent JAR 파일 만들기 \[[오라클문서](https://docs.oracle.com/javase/8/docs/api/java/lang/instrument/package-summary.html)\]
    - 붙이는 방식은 시작시 붙이는 방식 premain과 런타임 중에 동적으로 붙이는 방식 agentmain이 있다.
    - Instrumentation을 사용한다.

- Javaagent 붙여서 사용하기
    - 클래스로더가 클래스를 읽어올 때 javaagent를 거쳐서 변경된 바이트코드를 읽어들여 사용한다.


```java
// 새로운 프로젝트를 만들어서 아래와 같이 작업한다.
package my.sskim;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteCodeAgent {

    // premain 을 오버라이딩 한거
    public static void premain(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
            .type(ElementMatchers.any())
            // 조작을  transform 쪽에 넣어주면 되는데...
            .transform((builder, typeDescription, classLoader, module) -> builder.method(ElementMatchers.named("pullout")).intercept(FixedValue.value("NotEmpty"))).installOn(inst);
    }
}
```
```xml
<!-- pom.xml 에 다음과 같이 수정함.-->
<!-- google에 `maven jar manifest` 로 검색 링크 참고 : https://maven.apache.org/plugins/maven-jar-plugin/examples/manifest-customization.html -->

<project>
  ...
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <archive>
            <index>true</index>
            <manifest>
              <addClasspath>true</addClasspath>
            </manifest>
            <manifestEntries>
              <mode>development</mode>
              <url>${project.url}</url>
              <key>value</key>
            <!-- 추가분--> 
        
              <Premain-class>my.sskim.ByteCodeAgent</Premain-class>
              <Can-Redefine-classes>true</Can-Redefine-classes>
              <Can-REtransform-classes>true</Can-REtransform-classes>
            <!-- 추가분 끝 -->
              
            </manifestEntries>
          </archive>
        </configuration>
        ...
      </plugin>
    </plugins>
  </build>
  ...
</project>

```
```shell
#  그리고 package
mvn clean package -f "/home/ecsuser/study/daily_record/study/java/javabasic/bytecode2/pom.xml"
```
```json
// vscode 에서 실행할때 javaagent 추가
        {
            "type": "java",
            "name": "bytecode with option",
            "request": "launch",
            "mainClass": "my.sskim.App",
            "projectName": "bytecode",
            "vmArgs": "-javaagent:/home/ecsuser/study/daily_record/study/java/javabasic/bytecode2/target/bytecode2-1.0-SNAPSHOT.jar"
        }
```

### 바이트코드 조작툴 활용 예

- 코드에서 버그 찾거나 복잡도 계산
- 클래스 파일 생성 -> 프록시, 특정 API 호출 제한 가능

- 프로파일러 ( 특정 Agent ) : Thread 활성도 정도 등 성능 분석시 쓰임.

- 스프링이 컴포넌트스캔 시 ASM 을 사용함.
  - @ComponentSacn  빈으로 등록할 후보 클래스 ( @Component )  정보를 찾는데 사용
  - ClassPathScanningCandidateComponentProvider -> SimpleMetadataReader
    - 
    - ClassReader와 Visitor 사용해서 클래스에 있는 메타 정보를 읽어온다.
  - [참고영상](https://www.youtube.com/watch?v=39kdr1mNZ_s)
