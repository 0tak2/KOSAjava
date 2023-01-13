// ----------------------------------
let myObj = {
    'name': '홍길동', // Property 1
    'age': 20, // Property 2
    'addr': '서울', // Property 3
    'myInfo': function() {
        console.log('소리 없는 아우성!');
    } // Property 4: 익명 함수. 이름 대신 키를 지정한 일반적인 형태
};

console.log(myObj.name); // 홍길동

console.log(myObj.myInfo); // [Function: myInfo]
myObj.myInfo(); // '소리 없는 아우성!'
console.log();

// ----------------------------------
let anotherObj = {
    'name': '홍길동'
}

console.log(anotherObj);
// { name: '홍길동' } -> 키는 기본 문자열 처리.
// Single Quote로 묶지 않아도 됨.
/*
let anotherObj = {
    name: '홍길동'
}
*/

// ----------------------------------
let anotherObj2 = {
    name: '홍길동',
    10: 200
}
console.log(anotherObj2);
// { '10': 200, name: '홍길동' }
// 키는 문자열이기 떄문에, 수를 키로 지정하더라도
// 문자로 형변환되어 처리됨

// console.log(anotherObj2.10);
// console.log(anotherObj2.'10'); // 에러. '.' 연산자로 접근할 수 없음
console.log(anotherObj2['10']); // 대신 배열처럼 접근 가능. [연관배열] 인덱스로는 문자열을 정확히 넘겨야 함.
anotherObj2.address = '서울'; // 객체에 정의되지 않은 프로퍼티도 객체 외부에서 할당 가능


// ----------------------------------
// Malfunction
let anotherObj3 = {
    name: '홍길동',
    address: '인천'
}

anotherObj3.addrass = '서울' // 오타 발생. 그러나 오류 발생되지 않고 새로운 프로퍼티 추가됨