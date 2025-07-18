# Study Management System

스터디 관리 시스템  
(Spring Boot, MyBatis, Spring Security, MySQL)

---

## 🗂️ 프로젝트 개요

- **회원 가입/로그인/로그아웃** (Spring Security, BCrypt 암호화)
- **스터디 모집 게시판** (생성/목록/상세/검색)
- **스터디 신청 기능** (동일 스터디 중복 신청 방지, 정원 체크)
- **내가 만든/신청한 스터디 확인**
- **비로그인 접근 시 로그인 페이지 리다이렉트 (세션 기반 인증)**

---

## 🛠️ 기술 스택

- Java 17+
- Spring Boot 3.x
- Spring Security (세션 기반, BCryptPasswordEncoder)
- MyBatis
- MySQL 8.x
- Thymeleaf (HTML 템플릿)
- Gradle (or Maven)

---

## 📁 프로젝트 구조

src/
├─ main/
│ ├─ java/com/koreait/member/
│ │ ├─ config/ # Security 설정
│ │ ├─ controller/ # 컨트롤러 (Member, Study, Apply)
│ │ ├─ dto/ # DTO 클래스 (MemberDTO, StudyDTO, ApplyDTO)
│ │ ├─ mapper/ # MyBatis Mapper
│ │ ├─ service/ # 서비스/구현체
│ │ └─ MemberApplication.java
│ └─ resources/
│ ├─ static/ # CSS/JS
│ ├─ templates/ # Thymeleaf HTML
│ └─ application.yml # DB 및 환경설정
└─ ...

sql
복사
편집

---

## ⚡️ 실행 방법

### 1. **DB 생성 및 테이블 생성**
MySQL에 아래 스키마 실행 (예시)
```sql
CREATE DATABASE study_db DEFAULT CHARACTER SET utf8mb4;
USE study_db;

CREATE TABLE member (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE study (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    max_members INT NOT NULL,
    deadline DATE NOT NULL,
    created_by INT NOT NULL,
    FOREIGN KEY (created_by) REFERENCES member(id)
);

CREATE TABLE study_apply (
    id INT AUTO_INCREMENT PRIMARY KEY,
    study_id INT NOT NULL,
    member_id INT NOT NULL,
    apply_at DATETIME NOT NULL,
    UNIQUE (study_id, member_id),
    FOREIGN KEY (study_id) REFERENCES study(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);
2. application.yml (DB 연결 정보)
yaml
복사
편집
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/study_db?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: [DB유저명]
    password: [DB비번]
    driver-class-name: com.mysql.cj.jdbc.Driver
  mybatis:
    mapper-locations: classpath:mapper/*.xml
    configuration:
      map-underscore-to-camel-case: true
  thymeleaf:
    cache: false
server:
  port: 8080
3. 빌드 & 실행
Gradle
bash
복사
편집
./gradlew build
./gradlew bootRun
Maven
bash
복사
편집
mvn clean install
mvn spring-boot:run
🚀 주요 기능 안내
회원가입/로그인/로그아웃 : BCrypt 암호화, 세션 인증

스터디 개설 : 로그인 후 가능, 제목/설명/정원/마감일 입력

스터디 목록 : 전체/검색/페이징, 상세에서 신청 가능

스터디 신청 : 중복/정원 초과 불가, 내가 신청한 스터디/만든 스터디 확인

👀 화면 예시
/register : 회원가입

/login : 로그인

/home : 마이페이지/이동

/studyList : 전체 스터디 목록

/study/create : 스터디 개설

/myApply : 내가 신청한 스터디

/myStudy : 내가 만든 스터디

