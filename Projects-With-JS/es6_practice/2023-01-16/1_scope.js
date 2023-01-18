var x = 'Hello';

// # 스코프 일반
// 함수 선언: foo라는 참조변수에 바인딩된 메모리 공간에
// 함수 바디가 위치한 메모리 공간의 주소값 할당됨
function foo() { 
    var x = 'World';

    console.log(x);
    // [Identifier Resolution 식별자 결정]
    // Engine Thought: 어떤 x를 말하는 거지?
    // 스코프를 기준으로 결정
    //    ln 1 x: 전역변수 (Global Scope)
    //    ln 6 x: 지역변수/var (Local Scope)
    // => 서로 다른 식별자임을 스코프를 기준으로 판단했으므로
    //    스코프는 [Namespace]라고 지칭되기도 함

}

console.log(x); // Hello
foo(); // World


// # 중첩된 함수의 스코프
function bar() { 
    var x = 'World';

    function baz() {
        var x = 'Haha';

        console.log(x);
        console.log(y);
        // baz() 함수 스코프 확인 -> bar() 함수 스코프 확인 -> 전역스코프 확인
        // 현재 스코프에서 상위 스코프로 올라가면서 해당 식별자를 탐색
        // 즉, 스코프는 중첩될 수 있음. => [스코프 체인]
    }

    console.log(x); // x ?

}


// # 렉시컬 스코프
var z = 1;

function qux() {
    var z = 10;
    quux();
}

function quux() {
    console.log(z);
}

qux(); // ?
// quux의 호출 시점을 기준으로 하면 출력값이 10으로 기대되고,
// quux의 선언 시점을 기준으로 하면 출력값이 1로 기대된다.
// 자바스크립트의 경우 후자이며, 따라서 1이 출력된다. => 렉시컬 스코프