// let
// ES6에서 도입
// block level scope 
// 기타 자바와 동일

let result = 100;
// let result = 50; // Error. 같은 스코프 내에서 중복 선언 불가

// 따라서 일반적인 상황에서는 var 대신 let을 쓰는 것이
// 오류를 방지하는데 도움이 된다

let num = 50

{
    let num = 100;
    let myVar = 10; // 블록이 끝나면 접근할 수 없음
}
console.log(num);
console.log(myVar); // Error.