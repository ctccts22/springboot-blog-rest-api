# Spring Boot 블로그 REST API

이 프로젝트는 Spring Boot를 사용하여 구현된 간단한 블로깅 플랫폼으로, 블로그 글, 댓글, 사용자 인증 및 권한 부여와 관련된 기본 API 기능을 제공합니다.

## 목차
- [기능](#기능)
- [사용된 기술](#사용된-기술)
- [시작하기](#시작하기)
- [필수 조건](#필수-조건)
- [설치](#설치)
- [API 엔드포인트](#api-엔드포인트)
- [인증](#인증)
- [권한 부여](#권한-부여)

## 기능
- **사용자 인증:** 사용자는 가입하고 로그인하여 보호된 엔드포인트에 액세스하는 데 필요한 JWT 토큰을 받을 수 있습니다.
- **게시물 관리:** 블로그 게시물을 생성, 업데이트, 삭제 및 검색하는 CRUD 작업을 수행할 수 있습니다.
- **댓글 시스템:** 사용자는 블로그 게시물에 댓글을 생성, 업데이트, 삭제 및 검색할 수 있습니다.
- **역할 기반 접근 제어:** 관리자는 게시물 생성 및 삭제와 같은 특정 엔드포인트에 대한 독점적인 액세스 권한을 가집니다.

## 사용된 기술
- **Spring Boot:** Java 애플리케이션을 구축하기 위한 프레임워크.
- **Spring Security:** 인증 및 권한 부여를 제공하는 프레임워크.
- **JSON Web Tokens (JWT):** 상태를 유지하지 않는 인증에 사용됩니다.
- **Hibernate Validator:** 요청 페이로드를 유효성 검사하는 데 사용됩니다.
- **Lombok:** 보일러플레이트 코드를 줄이기 위한 라이브러리.
- **Java Persistence API (JPA):** Java 애플리케이션의 ORM을 위한 표준 명세.

## 시작하기
### 필수 조건
- Java Development Kit (JDK) 8 이상
- Maven
- MySQL (또는 Spring Boot가 지원하는 다른 관계형 데이터베이스)

### 설치
1. **저장소 복제:**
   ```bash
   git clone https://github.com/your-username/spring-boot-blog.git
2. **프로젝트 디렉터리로 이동:**
   ```bash
   cd spring-boot-blog
3. **Maven을 사용하여 프로젝트 빌드:**
   ```bash
   mvn clean install
4. **어플리케이션 실행:**
   ```bash
   java -jar target/spring-boot-blog.jar

## API 엔드포인트
다음 엔드포인트를 사용할 수 있습니다:

- **인증:**
  - `POST /api/auth/login` - 사용자 로그인
  - `POST /api/auth/register` - 사용자 등록
- **게시물:**
  - `GET /api/posts` - 모든 게시물 가져오기
  - `GET /api/posts/{id}` - ID로 게시물 가져오기
  - `POST /api/posts` - 새 게시물 생성 (관리자 전용)
  - `PUT /api/posts/{id}` - ID로 게시물 업데이트 (관리자 전용)
  - `DELETE /api/posts/{id}` - ID로 게시물 삭제 (관리자 전용)
- **댓글:**
  - `GET /api/posts/{postId}/comments` - 게시물의 댓글 가져오기
  - `GET /api/posts/{postId}/comments/{id}` - 게시물의 ID로 댓글 가져오기
  - `POST /api/posts/{postId}/comments` - 게시물에 새 댓글 생성
  - `PUT /api/posts/{postId}/comments/{id}` - 댓글의 ID로 댓글 업데이트
  - `DELETE /api/posts/{postId}/comments/{id}` - 댓글의 ID로 댓글 삭제

## 인증
인증은 JSON Web Tokens (JWT)를 사용하여 수행됩니다. 사용자는 로그인하거나 등록함으로써 토큰을 받을 수 있습니다. 이 토큰은 보호된 엔드포인트에 액세스하기 위해 후속 요청의 Authorization 헤더에 포함되어야 합니다.

## 권한 부여
권한 부여는 Spring Security 주석을 사용하여 구현됩니다. 관리자와 같은 특정 역할을 가진 사용자만 특정 엔드포인트에 액세스할 수 있습니다.
