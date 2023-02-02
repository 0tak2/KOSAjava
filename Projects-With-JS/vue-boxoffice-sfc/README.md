# 뷰를 이용한 일일박스오피스 웹앱 (SFC)

뷰를 이용해 작성한 간단한 박스오피스 어플리케이션입니다. 영화 정보는 한국영화진흥위원회 API를 통해 받아오며, 영화 포스터는 카카오 API를 통해 받아옵니다.

이전에 [CDN 임포트 방식으로 진행했던 프로젝트](https://github.com/0tak2/KOSAjava/tree/main/Projects-With-JS/vue-boxoffice)를 싱글 파일 컴포넌트 방식으로 수정한 프로젝트입니다.

## 프레임워크 및 라이브러리

- Vue 2.6.14
- Vue Router 3.5.1
- Vuetify 2.6.0
- axios 1.3.1

## 컴포넌트 구조

- Root Component
  - BoxContainer: 메인 컨테이너 컴포넌트
    - BoxTable: 일일 영화 순위를 표 형식으로 렌더링하는 컴포넌트
    - DetailContainer: 영화 상세 정보를 불러오는 컴포넌트; BoxContainer에서 모델로 띄움
    - DetailContent: 영화 상세 정보를 카드 형식으로 렌더링하는 컴포넌트

## URL 구조

- '/': 메인 컴포넌트(boxContainer) 마운팅; 바로 어제 일자의 아래 URL로 이동
- '/:date': 메인 컴포넌트(boxContainer) 마운팅; 입력한 일자의 데이터를 조회
- '/:date/:movieCd': 메인 컴포넌트(boxContainer) 마운팅하며, 상세정보 모달 출력. 입력한 코드에 해당하는 영화의 상세정보 조회

## 테스트 방법

1. 현재 디렉토리(vue-boxoffice-sfc) 클론
2. secret.js가 현재 디렉토리 상에 없는 경우 secret.js.origin을 secret.js로 복사하여, 한국영화진흥위원회와 카카오 디벨로퍼에서 발급받은 API 키를 파일 내 기입
3. 터미널에서 아래의 명령을 실행; 자세한 사항은 아래의 '빌드 및 테스트 인스트럭션' 참고

```bash
npm install
npm run serve
```

## 빌드 및 테스트 인스트럭션

### Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```
