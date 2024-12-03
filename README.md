# 공통모듈 (common-modules)

Spring Boot 프로젝트에서 필요한 공통 모듈과 유틸리티를 제공하는 저장소입니다. 이 저장소는 재사용 가능한 컴포넌트를 제공하여 코드 중복을 줄이고 개발 효율성을 높이는 것을 목표로 합니다.


## 주요 기능

### 1. **엑셀 처리**
- **엑셀 다운로드**: 데이터를 기반으로 엑셀 파일 생성.
- **엑셀 업로드**: 업로드된 엑셀 파일을 파싱하고 유효성 검사.

### 2. **공통 로깅**
- **Custom Logger**: 프로젝트 전반에서 활용 가능한 로깅 유틸리티.
- **HTTP 요청/응답 로깅**: API 요청과 응답을 자동으로 로깅.

### 3. **API 응답 표준화**
- **응답 객체 제공**: 통일된 응답 구조 제공 (`status`, `message`, `data`).
- **글로벌 예외 처리**: 공통 예외 처리 로직 통합.

### 4. **유틸리티 클래스**
- **DateUtils**: 날짜/시간 포맷팅 및 변환 기능.
- **StringUtils**: 문자열 처리 기능.
- **FileUtils**: 파일 처리 유틸리티.

### 5. **JPA 관련 기능**
- **BaseEntity**: 생성일/수정일 등 공통 필드를 포함한 엔티티 제공.
- **Auditing**: 엔티티 변경사항 추적.
- **동적 쿼리 작성**: Querydsl 또는 JPA Criteria API를 활용한 동적 쿼리.

### 6. **보안 기능**
- **JWT 인증/인가**: JSON Web Token 기반 인증/인가 지원.
- **데이터 암호화/복호화**: AES, RSA 기반 암호화 유틸리티.

### 7. **파일 처리**
- **파일 업로드/다운로드**: S3와 로컬 파일 처리 지원.
- **이미지 처리**: 리사이즈, 포맷 변환 등.

### 8. **메일 발송**
- **SMTP 연동**: 텍스트 및 HTML 이메일 발송 지원.
- **템플릿 기반 이메일**: Thymeleaf 또는 FreeMarker 템플릿 활용.

---

## 프로젝트 구조
springboot-common-modules/
├── src/main/java/com/taehui/common/
│   ├── excel/          # 엑셀 업로드/다운로드 관련 클래스
│   ├── logging/        # 공통 로깅 유틸리티
│   ├── utils/          # 유틸리티 클래스 (StringUtils, DateUtils 등)
│   ├── security/       # 보안 관련 (JWT, 암호화)
│   ├── response/       # API 응답 표준화
│   ├── file/           # 파일 처리 관련 클래스
│   ├── jpa/            # JPA 공통 설정 및 엔티티
│   └── mail/           # 메일 발송 관련 기능
├── src/main/resources/
│   ├── application.yml # 공통 설정 파일
│   └── templates/      # 메일 템플릿
├── pom.xml             # Maven 의존성 관리
├── README.md           # 프로젝트 소개 문서
└── LICENSE             # 라이센스

---

## 환경

- **Java 17**
- **Spring Boot 3.4.0** (Parent Dependency)

`spring-boot-starter-parent`를 사용하여 Spring Boot 프로젝트의 설정을 표준화했습니다. 관련 설정은 아래와 같습니다:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.0</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```
## 설치 방법

### Maven 의존성 추가
`pom.xml`에 다음 내용을 추가하세요:
```xml
<dependency>
    <groupId>com.taehui</groupId>
    <artifactId>springboot-common-modules</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 사용 방법

1. 공통 기능을 프로젝트에 추가하여 바로 사용 가능합니다.

## 기여 방법

- 이 저장소를 개선하고 싶다면, Pull Request를 통해 기여해주세요.
- 이슈가 발견되면 [Issues](https://github.com/taehui8260/springboot-common-modules/issues) 탭에 등록해주세요.

---

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.
