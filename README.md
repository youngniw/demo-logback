# demo-logback
[SpringBoot] Logback 활용 예제 프로젝트

---

<h3>- 간략한 실습 프로젝트 소개</h3>

게시물의 추가, 조회, 수정, 삭제의 기능을 수행할 때의 로그를 로그스태시, 파일, 그리고 콘솔 창에 출력하도록 구현

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
1. 루트
   - 레벨: INFO
   - 출력 형식: 콘솔, logstash를 통한 저장
2. controller 패키지
   - 레벨: INFO
   - 출력 형식: 콘솔, 파일

3. 콘솔 출력
   - 패턴: 연-월-일 시:분:초.밀리초(3) 로그레벨(5) [thread이름] 초록색(패키지_제외한_클래스_이름{36}) - 메시지
   - 패턴에 지정한 방식대로 시간과 레벨 등의 설정에 맞춰 콘솔에 메세지 출력

4. logstash로 로그 전달
   - 전달 URL: http://localhost:5000
   - 인코딩 형식: json
   - 로그 저장 내용: 추가적으로 application.properties에 정의한 어플리케이션 이름을 추가함
- logstash로 전달된 로그 예시
  ```json
  {
    "_index": "test-log",
    "_type": "doc",
    "_id": "6o1uwoQBO4RunYbNE8qL",
    "_version": 1,
    "_score": null,
    "_source": {
        "@timestamp": "2022-11-29T08:08:23.875Z",
        "host": "172.21.0.1",
        "@version": "1",
        "appName": "demo-logback",
        "thread_name": "main",
        "level": "INFO",
        "port": 63888,
        "logger_name": "com.example.demologback.DemoLogbackApplication",
        "message": "Started DemoLogbackApplication in 1.428 seconds (JVM running for 6.72)"
    },
    "fields": {
        "@timestamp": [
            "2022-11-29T08:08:23.875Z"
        ]
    },
    "sort": [
        1669709303875
    ]
  }
  ```

5. 파일 출력
   - 파일 저장 위치: demo-logback/logs
   - 파일명: vms_log.연월일_번호.log
   - 파일 최대 크기: 15MB
   - 파일 저장 기한: 7일
   - 로그 저장 형식: json
   - 로그 timestamp 타임존: Asia/Seoul
   - 로그 timestamp 저장 패턴: yyyy-MM-dd'T'HH:mm:ss.SSS'Z'

- 파일 예시
  ```json
  {
    "timestamp": "2022-11-17T17:23:22.368Z",
    "level": "INFO",
    "thread": "http-nio-8084-exec-3",
    "logger": "com.example.demologback.controller.PostController",
    "message": "Http Method (GET) - post list",
    "context": "vms"
  }
  ```

<br/>
<h3>- logstash 사용을 위한 실행 코드</h3>

- Docker를 이용한 ELK Stack 구축
  - 버전 6 클론
    ```
    git clone -b release-6.x --single-branch https://github.com/deviantony/docker-elk.git
    ```
  - 빌드 및 실행 (in 백그라운드)
    ```
    docker-compose build && docker-compose up -d
    ```
  - 종료
    ```
    docker-compose down -v
    ```

  - 키바나에서 확인
    - URL: http://localhost:5601
  - 엘라스틱 서치에서 확인
    - URL: http://localhost:9200/{인덱스명}/_search?pretty
