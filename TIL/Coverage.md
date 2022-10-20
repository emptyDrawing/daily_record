
### JAVA - jacoco 
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
     mvn jacoco:prepare-agent test install jacoco:report
    ```

    - 그래도 안되서 진짜로 찾은 [답](https://stackoverflow.com/questions/25395255/maven-jacoco-not-generating-code-coverage-report)은
    - I had the same issue. The problem was that i had add the jacoco plugin inside the <plugins> that was listed inside <pluginManagment> tag.
    - 라서 그 밑에다 넣으면 된다...
    
    ```xml
    <build>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
        <plugins>
        ...
        </plugins>
    </pluginManagement>
    <plugins>
      <plugin>
        <groupId>org.jacoco</groupId>
        <artifactId>jacoco-maven-plugin</artifactId>
        <executions>
          <execution>
              <id>default-prepare-agent</id>
              <goals>
                  <goal>prepare-agent</goal>
              </goals>
          </execution>
          <execution>
              <id>jacoco-report</id>
              <phase>test</phase>
              <goals>
                  <goal>report</goal>
              </goals>                   
          </execution>
          <execution>
            <id>jacoco-check</id>
            <goals>
                <goal>check</goal>
            </goals>
            <configuration>
                <rules>
                    <rule>
                        <element>PACKAGE</element>
                        <limits>
                            <limit>
                                <counter>LINE</counter>
                                <value>COVEREDRATIO</value>
                                <minimum>0.50</minimum>
                            </limit>
                        </limits>
                    </rule>
                </rules>
            </configuration>
        </execution>

      </executions>
      </plugin>
    </plugins>
```
