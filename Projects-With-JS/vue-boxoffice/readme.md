# 뷰를 이용한 일일박스오피스 웹앱

뷰를 이용해 작성한 간단한 박스오피스 어플리케이션입니다. 영화 정보는 한국영화진흥위원회 API를 통해 받아오며, 영화 포스터는 카카오 API를 통해 받아옵니다.

학습 목적의 미니프로젝트로, vue-cli을 사용하지 않고 cdn을 통해 vue를 직접 불러와 이용합니다. (SFC 방식이 아닌 문자열 템플릿을 활용)

## 프레임워크 및 라이브러리

- Vue 2.7.14
- Vue Router 3.5.3
- Vuetify 2
- axios 최신

## 컴포넌트 구조

- Root Vue Instance (app.js): 뷰 인스턴스; div#app에 마운트됨
  - boxContainer: 메인 컨테이너 컴포넌트
    - boxTable: 일일 영화 순위를 표 형식으로 렌더링하는 컴포넌트
    - detailContainer: 영화 상세 정보를 불러오는 컴포넌트; boxContainer에서 모델로 띄움
    - detailContent: 영화 상세 정보를 카드 형식으로 렌더링하는 컴포넌트

## URL 구조

- '/': 메인 컴포넌트(boxContainer) 마운팅; 바로 어제 일자의 아래 URL로 이동
- '/:date': 메인 컴포넌트(boxContainer) 마운팅; 입력한 일자의 데이터를 조회
- '/:date/:movieCd': 메인 컴포넌트(boxContainer) 마운팅하며, 상세정보 모달 출력. 입력한 코드에 해당하는 영화의 상세정보 조회

## 테스트 방법

1. 현재 디렉토리(vue-boxoffice) 클론
2. secret.js가 현재 디렉토리 상에 없는 경우 secret.js.origin을 secret.js로 복사하여, 한국영화진흥위원회와 카카오 디벨로퍼에서 발급받은 API 키를 파일 내 기입
3. 웹서버를 구동하고, 현재 디렉토리의 index.html 접속 ('/' 또는 '/index.html')