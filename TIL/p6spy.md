### run
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "TestMain",
      "request": "launch",
      "mainClass": "ecs.ecss.api.alarm.TestMain",
      "projectName": "ecss-api"
    },
    {
      "type": "java",
      "name": "Spring Boot App",
      "console": "integratedTerminal",
      "request": "launch",
      "mainClass": "ecs.ecss.api.Application",
      "projectName": "ecss-api",
      "vmArgs": [
        "-DCONFIG_DIR=${workspaceFolder}/cfg",
        "-DCONFIG_FILE=ecss_api",
        "-Dlogging.config=${workspaceFolder}/log/ecss_api.xml",
        "-Dlogging.file.path=${workspaceFolder}/logfile",
        "-Djava.net.preferIPv4Stack=true"
      ],
      "preLaunchTask": "querydsl"
    }
  ]
}
```

### xml
```xml
<configuration>
	<logger name="p6spy" level="info"/>
</configuration>
```


### build.gralde
```sh
dependencies{
    implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.5.6'
}

```