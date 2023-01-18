/* ##################### Example 1 ##################### */
console.log("\nEXAMPLE 1");

const x = 1; // 전역1

function foo() { // 전역1
    const y = 2; // 함수1

    function bar() { // 함수1
        const z = 3; // 함수2
        console.log(x + y + z); // 함수2
    }
    bar(); // 함수1
}

foo();  // 전역1

// 각 타입의 소스코드는 타입별 실행 환경에서 실행됨



/* ##################### Example 2 ##################### */
console.log("\nEXAMPLE 2");


const xx = 1;

function outer(){
    const xx = 10;

    const inner = function() {
        console.log(xx);
    }
    
    //inner();
    return inner; // 함수가 함수를 리턴
}

const result = outer();

result(); // ?



/* ##################### Example 3 ##################### */
console.log("\nEXAMPLE 3");

function foo() {
    const x = 1;
    const y = 2;

    function bar() { // 클로져가 아닌 상태. x, y는 foo가 종료되면 소멸
        const z = 3;
        console.log(z)
    }

    return bar;
}

const result2 = foo();
result2();

// ---------------------------

function foo2() {
    const x = 1;
    const y = 2;

    function bar2() { // 클로져
        const z = 3;
        console.log(x + y + z)
    }

    return bar2;
}

const result3 = foo2();
result3();



/* ##################### Example 4 ##################### */
console.log("\nEXAMPLE 4");

// 클로져 응용
// ------- 간단한 카운터 프로그램 --------
let num = 0;

const increase = function() {
    return ++num;
}

console.log(increase());
console.log(increase());
console.log(increase());

// mistake!
num = 10;
console.log(increase());
console.log(increase());


// ----------- 정보 은닉 시도 ------------

const increase2 = function() {
    let num = 0;
    return ++num;
}

console.log(increase2()); // 1
console.log(increase2()); // 1
console.log(increase2()); // 1
// num이 매번 초기화됨

// ------- 클로져를 통한 정보 은닉 --------
// 지역변수임에도 계속 유지될 수 있게 하려면?
// 클로져 사용

const increase3 = (function() {
    let num = 0;

    return function() {
        return ++num;
    }
})(); // IIFE. increase3에는 Nested Function이 할당. 이때 유지된 Lexical Environment는 유지됨

console.log(increase3()); // 1
console.log(increase3()); // 2
console.log(increase3()); // 3