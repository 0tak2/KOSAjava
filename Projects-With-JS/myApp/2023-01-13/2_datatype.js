// Primitive Type

// 1. number type
// 정수와 실수의 구분이 없음
let myVar = 1.0; // 실수 -> number type

console.log(myVar == 1); // true

console.log(3 /2); // 1.5 (1이 아님)

console.log(10 / 0); // Infinity. 무한대를 의미하는 상수값. 에러가 나지 않음.

console.log(10 * 'Hello'); // NaN (Not A Number). 산술 연산이 불가함을 의미. 그러나 에러가 발생하지는 않음.



// 2. string type
// 문자열 타입
let myStr = '소리 없는 아우성!'; // 일반적인 형태

myStr = "이렇게도 사용 가능"; // 가능은 하지만 관용적으로 Single Quotes가 일반적

myStr = `백틱을 쓰면
여러 줄에 걸쳐서
문자열을 입력할 수 있음
`;
console.log(myStr);

let num = 100;
myStr = `현재 num의 값은 ${num}입니다.`; // 백틱을 사용할 떄에는 문자열 중간에 다른 변수의 값을 삽입 가능
console.log(myStr);



// 3. boolean type
// true, false

let boolVal = true
if (boolVal) {
    console.log('트루');
}


// 4. undefined type
// 변수가 가리키는 객체가 없음을 의미.
// 개발자가 임의로 사용할 수 없으며, 자바스크립트 엔진이 사용



// 5. null type
// null 값 가능
num = null;
if (num === null) {
    console.log('할당된 값 없음');
}



// 6. Symbol type
// ES6 (ECMAScript 2015)에서 추가됨
// Primitive Type
// 다른 값과 중복되지 않는 Unique한 값을 표현

let mySymbol = Symbol(); // 유일한 값 할당
console.log(mySymbol); // Symbol() - 실제로 어떤 값인지는 알 수 없음. 유일성은 보장됨

let mySymbol1 = Symbol("소리없는 아우성!");
let mySymbol2 = Symbol("소리없는 아우성!");

console.log(mySymbol1 === mySymbol2); // false. 같은 값을 인자로 주더라도 유일성은 보장됨

// Wrapper Object
let str = 'hello';
console.log(str[0]); // h -> 이게 왜 돼...? string은 배열(객체)이 아니라 Primitive Type인데...
console.log(str.length); // 5               배열로 쓸 수 있는건가?
console.log(str.toUpperCase()); // 이건 또.. 왜 객체처럼 쓸 수 있는 거지...?

str[0] = 'H'; // 잠시 만들어진 Array-like wrapper가 수정될 뿐이다. 수정 내용은 라인이 넘어가면 소멸된다.
console.log(str); // Hello가 아닌 hello 출력


// Reference Type(함수, 객체, 배열)은 수업이 진행되면서 다룬다.