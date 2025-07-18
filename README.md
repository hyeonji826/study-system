# 스터디 관리 시스템 (Spring Boot & MyBatis)

---

## 📌 프로젝트 개요

Spring Boot, Spring Security, MyBatis, MySQL을 기반으로 한 스터디 그룹 관리 및 모집 웹 애플리케이션입니다. 사용자는 회원가입 후 스터디를 개설하거나 다른 스터디에 참여할 수 있습니다.

---

## ✨ 주요 기능

* **회원 관리**: Spring Security와 BCrypt를 이용한 안전한 회원가입, 로그인, 로그아웃 기능
* **스터디 모집**: 스터디 생성, 전체 목록 조회, 상세 보기, 검색 기능
* **스터디 신청**: 정원 체크 및 중복 신청 방지 로직이 포함된 스터디 참여 기능
* **마이페이지**: 내가 개설한 스터디와 신청한 스터디 목록 확인
* **인증 관리**: 비로그인 사용자의 접근 제한 및 로그인 페이지 리다이렉트 (세션 기반)

---

## 🛠️ 기술 스택

* **Language**: Java 17+
* **Framework**: Spring Boot 3.x, Spring Security
* **Persistence**: MyBatis, MySQL 8.x
* **Template Engine**: Thymeleaf
* **Build Tool**: Gradle

---

## 🗃️ 프로젝트 구조

src/

├─ main/

│  ├─ java/com/koreait/member/

│  │  ├─ config/         # Security 설정

│  │  ├─ controller/     # 컨트롤러 (Member, Study, Apply)

│  │  ├─ dto/            # DTO 클래스 (MemberDTO, StudyDTO, ApplyDTO)

│  │  ├─ mapper/         # MyBatis Mapper 인터페이스

│  │  ├─ service/        # 서비스 및 구현체

│  │  ├─ security/       # BCrypt 암호화 관련 클래스

│  │  └─ MemberApplication.java

│  └─ resources/

│     ├─ mapper/         # MyBatis Mapper XML

│     ├─ templates/      # Thymeleaf HTML 템플릿

│     └─ application.properties # DB 및 환경설정

└─ ...


---

## 🚀 실행 방법

1.  **데이터베이스 설정**
    * `database.sql` 파일을 사용하여 데이터베이스 및 테이블을 생성합니다.

2.  **설정 파일 구성**
    * `src/main/resources/application.properties` 파일에 아래 내용을 작성합니다.
    * `[DB_USERNAME]`과 `[DB_PASSWORD]`는 실제 DB 계정 정보로 변경해주세요.

    ```properties
    # DataSource
    spring.datasource.url=jdbc:mysql://localhost:3306/studyroom?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    spring.datasource.username=[DB_USERNAME]
    spring.datasource.password=[DB_PASSWORD]
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # MyBatis
    mybatis.mapper-locations=classpath:mapper/*.xml
    mybatis.configuration.map-underscore-to-camel-case=true

    # Thymeleaf
    spring.thymeleaf.cache=false

    # Server
    server.port=8080
    ```
3.  **애플리케이션 실행**
    * `MemberApplication.java` 파일을 실행하여 서버를 시작합니다.

---

## 🌐 페이지 안내

| URL             | 기능 설명                  |
| --------------- | -------------------------- |
| `/register`     | 회원가입 페이지            |
| `/login`        | 로그인 페이지              |
| `/home`         | 마이페이지로 이동          |
| `/studyList`    | 전체 스터디 목록           |
| `/study/create` | 스터디 개설 페이지         |
| `/myApply`      | 내가 신청한 스터디 목록    |
| `/myStudy`      | 내가 만든 스터디 목록      |
