// ----------------------------------------------------
console.log("# EXAMPLE 1");

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



// ----------------------------------------------------
console.log("# EXAMPLE 2");

function foo() {
}

// foo는 일반함수로 호출 가능하며,
// [[Call]]을 가지고 있는 Callable 객체

foo(); // 내부적으로는 [[Call]]이 호출됨

// -------------------------

let myVar = 100;
let myObj = {};

// myVar();
// myObj(); // [[Call]]이 없기 때문에 오류 발생.

// ----------------------------------------------------
console.log("# EXAMPLE 3");

// 함수 선언문
function baz() {
}
// [[Call]], [[Construct]]를 가지고 있음
// Callable 객체이자 Constructor

baz();
new baz(); // 둘 다 가능.

// -------------------------

// 함수 표현식
const qux = function() {
}

qux();
new qux(); // 둘 다 가능.

// -------------------------

const obj = {
    x: function() {} // Method 정의가 아님. 함수표현식
}

new obj.x(); // Method가 아니므로 가능 (=Constructor)

// -------------------------

// 화살표 함수
const myFunc = (a, b) => a + b;
console.log(myFunc(1, 2)); // 가능. [[Call]]이 있는 Callable 객체
/* new myFunc(1, 2); // Uncaught TypeError: myFunc is not a constructor */

// -------------------------

let obj2 = {
    // myFunc: function() {}   // 일반적인 정의
    myFunc() {                 // 축약형. 메서드 정의 (ES6)
        console.log('I\'m a method');
    },
    name: '홍길동'
}
/* new myFunc(); // 메서드이므로 불가능. Uncaught TypeError: myFunc is not a constructor */



// ----------------------------------------------------
console.log("# EXAMPLE 4");

// 함수선언문
function add(x, y) {
    return x + y;
}

let instance = new add(); // 가능. 선언문으로 정의된 함수는 Constructor
console.log(instance); // add {} 출력

// -------------------------

function createUser(name, age) { // 함수선언문
    /* this.name = name;
    this.age = age; */   // Normal Usage

    return {name, age};  // Bad Usage

    /* return {
        name: name,
        age: age
    }; */
}

const inst = new createUser('홍길동', 20);
// 객체를 반환받기는 하지만 생성자 함수에 의해 만들어진 객체를
// 반환받은 것은 아님.

console.log(inst);
// Returned `{name: '홍길동', age: 20}` / Not `createUser {name: '홍길동', age: 20}`
// 두 객체는 내용적으로 동일하지만, 내부적으로 다름.
// 내일 Prototype을 다루면서 차이를 이야기할 얘정

// -------------------------

function Circle(radius) { // 함수선언문
    this.radius = radius;
    this.getDiabeter = function() {
        console.log("Testing...");
        return 3.14 * 2 * radius;
    } // 메서드 아님
}

const circle1 = Circle(10); // new 주의. undefined 반환
console.log(radius); // 위에서 this가 window이므로, window.radius 반환
/* circle1.getDiameter() // 오류. Cannot read properties of undefined (reading 'getDiameter') */



// ----------------------------------------------------
console.log("# EXAMPLE 5");

const obj3 = {
    name: '홍길동'
}

console.log(obj3) // 문자열 변환값을 출력
console.dir(obj3) // 객체 자체를 자세하게 출력

// -------------------------

function square(number) {
    return number * number;
}

console.log(square); // 함수 바디를 문자열로 변환하여 출력

console.dir(square); // 객체 내부를 볼 수 있도록 출력
// [[Prototype]]도 있고, prototype도 있음. 내일 다룰 것.