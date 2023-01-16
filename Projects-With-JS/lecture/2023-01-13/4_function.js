// --------------------------------------
// 함수 선언문
function myfunc1(a, b) {
    return a + b;
}

console.log(myfunc1(10, 20));

(function myfunc2(a, b) {
    return a + b;
}) // 괄호 안에 들어가 리터럴 취급

// console.log(myfunc2(4, 3)); // 변수에 할당되지 않은 리터럴의 함수 명은 참조할 수 없음

// --------------------------------------
console.log(hoistFunc1(10, 20)); // 30
//console.log(hoistFunc2(10, 20)); // Error

function hoistFunc1(a, b) {
    return a + b;
}

const hoistFunc2 = function(a, b) {
    return a + b;
}

// --------------------------------------
// 함수 호출
function add(x, y) {
    return x + y;
}

console.log(add(10)); // NaN
// 인자 개수가 맞지 않음. 10 + undefined -> NaN
// 에러는 안나지만 예측되지 않은 값이 반환됨

console.log(add(10, 20, 30, 40)); // 30
// 인자를 더 많이 넣었음.
// x, y에 정상적으로 값 할당.

function addThree() {
    return arguments[0] + arguments[1] + arguments[2]; // 내장배열 (array-like)
}

console.log(addThree(1, 2, 3)); // 6

function addAll() {
    let sum = 0;
    const args = Array.from(arguments) // 유사배열이므로 변환 필요
    args.forEach(element => {
        sum += element
    });
    return sum;
}

console.log(addAll(1, 2, 3, 4, 5)); // 15


// --------------------------------------
// 즉시 실행 함수 (IIFE)
(function() {
    let x = 10;
    let y = 20;

    console.log(x + y);
}()); // 30



// --------------------------------------
// 중첩함수
function outer() { // 선언적 함수
    let x = 1;

    function inner() { // 중첩함수
        let y = 2;
        console.log(x + y);
    }

    inner();
}

outer();



console.log();
// --------------------------------------
// 콜백 함수

//// 숫자 하나를 입력받아, 0부터 해당 숫자까지 콘솔에 출력하는 프로그램
function repeat(n) {
    for(let i=0; i<n; i++) {
        console.log(i);
    }
}
repeat(3); // 0 1 2
console.log();

//// 기능 추가 요구: 숫자 하나를 입력받아,
//// 0부터 해당 숫자까지 콘솔에 "홀수만" 출력하는 기능
//// 기존 코드의 수정은 지양. 이미 잘 돌아가는 프로그램에서,
//// 기존 코드를 고치면 다른 부분에서 오류 발생 가능성 높음
//// -> 새로운 함수 작성?

function repeat_odd(n) {
    for(let i=0; i<n; i++) {
        if (i%2 == 1) {
            console.log(i);
        }
    }
}
repeat_odd(10); // 0 1 2
//// 중복 코드 양산. 잘못된 예시.

console.log();
/// 함수 합성
//// 공통된 로직을 작성하고, 경우에 따라 분기되는 로직을
//// 각 함수로 만들어 외부에서 받아와 사용
function repeat_enhanced(n, f) { // 함수 f를 인자로 받는다
    for(let i =0; i<n; i++) {
        f(i);
    }
}

let printAll = function(i) {
    console.log(i);
}

let printOdd = function(i) {
    if (i%2 == 1) {
        console.log(i);
    }
}

repeat_enhanced(10, printAll);
console.log();
repeat_enhanced(10, printOdd);

console.log();
//// 이후 짝수를 표시하는 기능을 추가해달라는 요구가 있으면,

let printEven = function(i) {
    if (i%2 == 0) {
        console.log(i);
    }
}
repeat_enhanced(10, printEven);