---
title: Callable과 Constructor
---

# Callable과 Constructor
함수는 레퍼런스 타입이며, 그 자체로 객체. 이떄 객체는 프로퍼티의 집합이므로, 아래와 같은 코드도 가능

```js
function foo() {
}

// Creation Phase에서 foo라는 변수가 묵시적으로 생성됨

foo.myName = '홍길동'; // 가능
foo.myFunc = function() {
    console.log(this);
}

console.log(foo.myName);
foo.myFunc();
// Node.js: [Function: foo] { myName: '홍길동', myFunc: [Function (anonymous)] }
```

즉, 함수 자체에도 property를 등록할 수 있음.

또한 특별히 함수 객체는 기본적으로 내부 슬롯 2개, 내부 메서드 2개를 가짐. (\* 항상 내부 메서드 2개를 동시에 가지는 것은 아님)

내부 슬롯은 중요하지 않지만 두 개의 내부 메서드는 기억할 필요가 있음.

- [[Call]]: 모든 함수가 가지고 있음
- [[Construct]]: 모든 함수가 가지는 것은 아님
  - 객체를 만들 수 있는 함수만 가지고 있음

## [[Call]]
[[Call]]을 가지고 있는 함수 객체를 Callable 객체라고 부름. 이때 모든 함수는 Callable 객체임.

```js
function foo() {
}

// foo는 일반함수로 호출 가능하며,
// [[Call]]을 가지고 있는 Callable 객체

foo(); // 내부적으로는 [[Call]]이 호출됨

// -------------------------
let myVar = 100;
let myObj = {};

myVar(); // [[Call]]이 없기 때문에 오류
myObj(); // [[Call]]이 없기 때문에 오류
```

## [[Construct]]
[[Call]]]과 달리 모든 함수가 [[Construct]]를 가지는 것은 아니다.

- 특정 함수가 인터널 메서드 [[Construct]]를 가지는 조건
  - 함수선언문으로 정의된 경우
    ```js
    function foo() {
    }
    ```
  - 함수표현식으로 정의된 경우
    ```js
    let myFunc = function() {
    }
    ```
  - 클래스
    ```js
    class MyClass {
    }
    ```
    - 내부적으로는 생성자 함수(-> 함수선언문)와 동일하게 동작
  - 공통점: 객체를 만들어낼 수 있는 함수 형태. 즉, new 키워드와 함께 사용할 수 있다는 것.
  - 위 세 종류를 Constructor 이라고 지칭.

```js
function myFunc() {
}

// myFunc()는 함수이므로 내부 메서드 [[Call]]을 가지고 있고,
// 함수선언문을 통해 정의되었으므로 [[Construct]]도 가지고 있음

myFunc(); // [[Call]] 호출
new myFunc(); // [[Construct]] 호출
```

- 특정 함수가 인터널 메서드 [[Construct]]를 가지지 않는 경우
  - 메서드인 경우 (ES6의 Method Definitions)
  - 화살표 함수인 경우
  - 이들을 Non-constructor라고 지칭.

```js
// 함수 선언문
function baz() {
}
// [[Call]], [[Construct]]를 가지고 있음
// Callable 객체이자 Constructor

baz();
new baz(); // 둘 다 가능.
```

```js
// 함수 표현식
const qux = function() {
}

qux();
new qux(); // 둘 다 가능.
```

클래스는 뒤에서 다룬다.
