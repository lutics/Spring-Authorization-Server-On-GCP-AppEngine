# Spring-Authorization-Server-On-GCP-AppEngine

[Spring-Authorization-Server](https://github.com/spring-projects-experimental/spring-authorization-server)(0.0.3)의 예제 프로젝트이다

## 구성

- Spring Authorization Server (localhost or App-Engine)
- Spring Security Resource Server
- Spring Security Client Server
- Android

## 설정

Authorization Server 를 테스트하려면 2가지 방법이 있다

1. [예제](https://github.com/lutics/Spring-Boot-On-GCP-AppEngine)와 같이 Google App Engine 에 배포하고 원격 서버를 활용한다 (추천)
2. 원래 프로젝트의 [예제](https://github.com/spring-projects-experimental/spring-authorization-server/tree/master/samples/boot/oauth2-integration)를 따른다

1에 대한 설정을 미리 적용한 상태라 아래의 커맨드로 즉시 배포할 수 있다

```
cd authorization-server && ./gradlew appengineDeploy
```

1이나 2를 택 1한 후 Auth Server 주소를 `resource-server/src/main/resources/application.properties` 와 `client-server/src/main/resources/application.properties`에 알맞게 수정한 뒤 두 프로젝트를 실행한다
