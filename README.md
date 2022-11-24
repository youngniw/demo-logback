# demo-logback
[SpringBoot] Logback 활용 예제 프로젝트

---

<h3>- 간략한 실습 프로젝트 소개</h3>

게시물의 추가, 조회, 수정, 삭제의 기능을 수행할 때의 로그를 파일과 콘솔 창에 출력하도록 구현

<br/>
<h3>- 스택</h3>

- Spring Boot
  - Lombok
  - Spring Web
  - Spring Data JPA
- Gradle
- Java 11
- Database
  - H2

<br/>
<h3>- 로그 출력 관련 코드</h3>

- logback-spring.xml
1. 루트 레벨: INFO

2. 콘솔 출력
   - 패턴: 연-월-일 시:분:초.밀리초(3) 로그레벨(5) [thread이름] 초록색(패키지_제외한_클래스_이름{36}) - 메시지
   - 패턴에 지정한 방식대로 시간과 레벨 등의 설정에 맞춰 콘솔에 메세지 출력

3. 파일 출력
   - 파일 저장 위치: demo-logback/logs
   - 파일명: vms_log.연월일_번호.log
   - 파일 최대 크기: 15MB
   - 파일 저장 기한: 7일
   - 로그 저장 형식: json
   - 로그 timestamp 타임존: Asia/Seoul
   - 로그 timestamp 저장 패턴: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'

- 파일 예시
  ```
  {
    "timestamp": "2022-11-17T17:23:22.368Z",
    "level": "INFO",
    "thread": "http-nio-8084-exec-3",
    "logger": "com.example.demologback.controller.PostController",
    "message": "Http Method (GET) - post list",
    "context": "vms"
  }
  ```
