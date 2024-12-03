# 공통모듈 (common-modules)

Spring Boot 프로젝트에서 필요한 공통 모듈과 유틸리티를 제공하는 저장소입니다. 이 저장소는 재사용 가능한 컴포넌트를 제공하여 코드 중복을 줄이고 개발 효율성을 높이는 것을 목표로 합니다.

## 주요 기능

- **재사용 가능한 유틸리티 함수**: Spring Boot 프로젝트에서 자주 사용되는 헬퍼 메서드 제공.
- **글로벌 예외 처리**: 사전에 정의된 예외 클래스와 처리기.
- **커스텀 애너테이션**: 반복적인 작업을 단순화하기 위한 애너테이션.
- **표준화된 API 응답 구조**: 일관된 API 응답 포맷 제공.
- **보안 관련 유틸리티**: 보안 설정에 필요한 공통 도구 제공.
- **커스텀 Validator**: 다양한 검증 시나리오를 위한 유효성 검사 도구.

## 설치 방법

1. Git 저장소를 복제합니다.
   ```bash
   git clone https://github.com/username/SharedModule.git
   ```
2. `pom.xml` 또는 `build.gradle`에 의존성을 추가합니다.
   - Maven:
     ```xml
     <dependency>
         <groupId>com.example</groupId>
         <artifactId>shared-module</artifactId>
         <version>1.0.0</version>
     </dependency>
     ```
   - Gradle:
    ```gradle
    implementation 'com.example:shared-module:1.0.0'
    ```

## 사용 방법

1. 공통 기능을 프로젝트에 추가하여 바로 사용 가능합니다.
2. 필요한 유틸리티 클래스나 Validator는 `com.example.shared` 패키지에서 찾을 수 있습니다.

## 기여 방법

- 이 저장소를 개선하고 싶다면, Pull Request를 통해 기여해주세요.
- 이슈가 발견되면 [Issues](https://github.com/taehui8260/springboot-common-modules/issues) 탭에 등록해주세요.

---

## 라이선스

이 프로젝트는 MIT 라이선스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.
