### plugin 사용법

- 의존하는 모든 라이브러리를 하나의 jar로 통합하려면 ![maven-assembly-plugin](https://maven.apache.org/plugins/maven-assembly-plugin/plugin-info.html) 를 사용하면 되는데..
- 이걸 `pom.xml` 추가해서 하면 편리하게 사용가능하지만,,
- 내가 했던 실수는
```xml
  <build>
	<pluginManagement>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
					<encoding>UTF-8</encoding>
				</configuration>
			</plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.5.0</version>
                <configuration>
                  <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                  </descriptorRefs>
                </configuration>
                <executions>
                  <execution>
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                      <goal>single</goal>
                    </goals>
                  </execution>
                </executions>
              </plugin>
		</plugins>
	</pluginManagement>
  </build>
```

- 이렇게 `pluginManagement` 에만 넣었다는 거, 그래서 강제로 `mvn assembly:single` 이라는 명령어가 아니면 `mvn package` 과정에 플러그인 사용되지 않는 문제가 발생
- 실사용은 아래처럼 설정하면 됨
```xml
  <build>
	<pluginManagement>
~~~ 중략 ~~~
	</pluginManagement>
    <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-assembly-plugin</artifactId>
          <version>3.5.0</version>
        </plugin>
      </plugins>
  </build>
```

- TODO : `pluginManagement`, `plugins` 의 차이점 정리 필요.