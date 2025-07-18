스터디 관리 시스템(Spring Boot, MyBatis, Spring Security, MySQL)

📌 프로젝트 개요

🔐 회원 가입 / 로그인 / 로그아웃 (Spring Security, BCrypt 암호화)

📢 스터디 모집 게시판 (생성 / 목록 / 상세 / 검색)

✏️ 스터디 신청 기능 (중복 신청 방지, 정원 체크)

📁 내가 만든 / 신청한 스터디 확인

🚨 비로그인 접근 시 로그인 페이지 리다이렉트 (세션 기반 인증)

🛠️ 기술 스택

Java 17+

Spring Boot 3.x

Spring Security (세션 기반, BCryptPasswordEncoder)

MyBatis

MySQL 8.x

Thymeleaf (HTML 템플릿)

Gradle

🗃️ 프로젝트 구조

src/
├─ main/
│  ├─ java/com/koreait/member/
│  │  ├─ 📂 config/                  # Security 설정
│  │  ├─ 📂 controller/              # 컨트롤러 (Member, Study, Apply)
│  │  ├─ 📂 dto/                     # DTO 클래스 (MemberDTO, StudyDTO, ApplyDTO)
│  │  ├─ 📂 mapper/                  # MyBatis Mapper
│  │  ├─ 📂 service/                 # 서비스/구현체
│  │  ├─ 📂 security/                # Bcrypt 암호화 변환
│  │  └─ 📄 MemberApplication.java
│  └─ resources/
│     ├─ 📂 mapper/                  # MySQL DB 연동
│     ├─ 📂 templates/               # Thymeleaf HTML
│     └─ 📄 application.properties          # DB 및 환경설정
└─ ...

🚀 실행 방법

1️⃣ DB 생성 및 테이블 설정 (MySQL)

CREATE DATABASE studyroom ;
USE studyroom;

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

2️⃣ 설정 파일 구성 (application.yml)

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/studyroom?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
    username: [DB_USERNAME]
    password: [DB_PASSWORD]
    driver-class-name: com.mysql.cj.jdbc.Driver
  mybatis:
    mapper-locations: classpath:mapper/*.xml
    configuration:
      map-underscore-to-camel-case: true
  thymeleaf:
    cache: false
server:
  port: 8080

✨ 주요 기능 안내

🔐 회원가입 / 로그인 / 로그아웃 : BCrypt 암호화, 세션 인증

📌 스터디 개설 : 로그인 후 가능, 제목/설명/정원/마감일 입력

📋 스터디 목록 : 전체 목록 조회 / 검색 / 페이징, 상세 페이지에서 신청

📮 스터디 신청 : 중복 및 정원 초과 신청 불가

📑 내가 신청한 스터디 / 내가 만든 스터디 조회

🌐 페이지 예시

URL

기능 설명

/register

회원가입

/login

로그인

/home

마이페이지로 이동

/studyList

전체 스터디 목록

/study/create

스터디 개설 페이지

/myApply

내가 신청한 스터디 목록

/myStudy

내가 만든 스터디 목록
