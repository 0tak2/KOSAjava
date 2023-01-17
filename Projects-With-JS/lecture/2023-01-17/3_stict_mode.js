/* ##################### Example 1 ##################### */
console.log("\nEXAMPLE 1");

function foo() {
    x = 10;
}

foo();

console.log(x); // ?
// [암묵적 전역 Implict Global]
// 호출된 foo에서 선언되지 않은 변수 x에 10을 할당
// 자바스크립트는 여기에서 에러를 발생시키지 않고
// 전역변수로 할당. (window 객체의 Property가 됨)\
// 따라서 오류 없이 10이 출력됨
//
// 자유도를 높일 수는 있지만 오히려 유지보수를 해칠 염려
