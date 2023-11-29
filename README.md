# ssoclient-spring-boot-starter

## step1: add dependency
```xml
<dependency>
	<groupId>cloud.00857.sso</groupId>
	<artifactId>ssoclient-spring-boot-starter</artifactId>
	<version>1.0.0</version>
</dependency>
```
## step2: scan mybatis-plus mapper
```
@MapperScan("cloud.www.xxx.mapper")
```

## step3: scan component
```
@SpringBootApplication(scanBasePackages = {"cloud.www.**", "cloud.om00857.**"})
```

## step4: create a get user info request
```
    @GetMapping("/api/users/current")
    public UserVO getUserInfo() {
        return SsoUserUtil.getUserInfo(StpUtil.getLoginId());
    }
```

## step5: config properties
```properties
server.port=
server.shutdown=graceful
server.tomcat.threads.max=100
server.tomcat.threads.min-spare=5
server.tomcat.connection-timeout=10s
server.tomcat.accept-count=500
server.tomcat.uri-encoding=UTF-8
server.compression.enabled=true
server.compression.min-response-size=1KB
server.compression.excluded-user-agents=gozilla,traviata
server.compression.mime-types=text/html,text/css,text/plain,application/javascript,application/xml,application/json
spring.application.name=@project.name@

spring.datasource.name=db-${spring.application.name}
spring.datasource.driver-class-name=com.p6spy.engine.spy.P6SpyDriver
spring.datasource.url=jdbc:p6spy:mysql://localhost:3306/db_xxx?serverTimezone=Asia/Shanghai&characterEncoding=utf-8&useUnicode=true&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull&useSSL=false&rewriteBatchedStatements=true&tinyInt1isBit=false
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=1

spring.task.execution.thread-name-prefix=biz-thread-executor-
spring.task.execution.pool.queue-capacity=256
spring.task.execution.pool.max-size=16
spring.task.execution.pool.core-size=2
spring.task.execution.shutdown.await-termination=true
spring.task.execution.shutdown.await-termination-period=15s

spring.jackson.default-property-inclusion=non_null

logging.charset.console=utf-8
logging.level.root=info
logging.file.name=/data/logs/${spring.application.name}/app.log
logging.logback.rollingpolicy.max-file-size=10MB
logging.logback.rollingpolicy.max-history=5
logging.logback.rollingpolicy.total-size-cap=1GB
logging.pattern.console=%magenta(%d{yyyy-MM-dd HH:mm:ss.SSS, GMT+8}) %highlight(%-5p) %yellow(%X{RequestId}) - %green([%L][%t][%c]) %m%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss.SSS, GMT+8} %-5p %X{RequestId} - [%L][%t][%c] %m%n

management.endpoints.web.exposure.include=prometheus,health
management.observations.key-values.application=@project.artifactId@

forest.max-connections=200
forest.max-route-connections=100
forest.timeout=10000
forest.connect-timeout=10000
forest.read-timeout=10000
forest.log-enabled=true
forest.log-request=true
forest.log-response-status=true
forest.log-response-content=true

sa-token.token-name=cloud.xxxxx.token
sa-token.token-style=tik
sa-token.timeout=21600
sa-token.active-timeout=900
sa-token.is-concurrent=false
sa-token.is-share=true
sa-token.is-log=true
sa-token.sso.is-http=true
sa-token.curr-domain=your biz domain here
sa-token.sso.server-url=sso server backend url here
sa-token.sso.auth-url=sso frontend login page here
sa-token.sign.secret-key=sso server secret key here
sso.client.application-id=your app id here
```