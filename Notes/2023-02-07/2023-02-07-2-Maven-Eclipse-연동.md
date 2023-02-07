---
title: 2. Maven, Eclipse 연동
---

# Maven, Eclipse 연동

## myProject Import

Maven은 CLI 툴로 사용해도 휼륭한 도구이지만, 불편한 것도 사실이다. 이클립스와 연동하여 사용해보자.  
우선 이클립스를 실행하여 아까 Maven으로 만든 프로젝트를 Import 해보자

![5c28ee3dd1aca2e0184eebeafc31f92e.png](Assets/5c28ee3dd1aca2e0184eebeafc31f92e.png)

![d4714be828026b5c03b14fa7ae8f1e5e.png](Assets/d4714be828026b5c03b14fa7ae8f1e5e.png)

서블릿 프로젝트를 위해 pom.xml을 열고, 패키징 타입을 아래와 같이 war로 변경

![087a1ac656c7672264fc47a15993184e.png](Assets/087a1ac656c7672264fc47a15993184e.png)

그럼 아래와 같이 Update Project를 실행하면 프로젝트 구조가 웹 프로젝트로 자동으로 변경된다.

![6e792254fcefd3dede73f494244b402e.png](Assets/6e792254fcefd3dede73f494244b402e.png)

![0f121aa6617919cb9b5663717dc040ce.png](Assets/0f121aa6617919cb9b5663717dc040ce.png)

web.xml을 찾지 못한다는 오류가 발생하지만, 우리는 어노테이션으로 설정해줄 것이기 때문에 일단은 무시해도 무방하다.

이 프로젝트는 테스트용이기 때문에 이제 지운다. 이클립스에서 프로젝트를 바로 생성해보자.

## 이클립스에서 Maven을 이용해 서블릿 프로젝트 생성

어제 Simple Web Project를 만들 때에는, 톰캣 런타임 라이브러리를 Build Path에 수동으로 불러왔다. 그러나 이렇게 되면 나중에 다른 컴퓨터에서 소스 코드를 열면 라이브러리를 찾지 못한다. 라이브러리를 같은 경로에 복사하거나, 새로운 경로로 재지정해줘야 한다.  
메이븐을 쓰면 의존 라이브러리를 편리하게 관리할 수 있다. 또한 버전, 라이브러리 간 충돌 등도 알아서 관리해준다.

이클립스에서 메이블을 연동하여 서블릿 프로젝트를 생성해보자.

우선 JavaEE Perspective를 열어준다.

![4fd7536edcb406610fddf0bc33cc6a62.png](Assets/4fd7536edcb406610fddf0bc33cc6a62.png)

![fe7bd355d7eaf891db63f8ec05fe410f.png](Assets/fe7bd355d7eaf891db63f8ec05fe410f.png)

![98cb09979506eb5b6b9ff26db8794775.png](Assets/98cb09979506eb5b6b9ff26db8794775.png)

pom.xml이 생성된 것을 확인할 수 있다.
이제 프로젝트에 대해 우클릭하면 Maven 메뉴가 생긴다. 여기서 Update Project를 해준다.  



