// ----------------------------------------------------
console.log("# EXAMPLE 1");
const obj = { // 객체 리터럴을 이용한 객체 생성
    // 프로퍼티 (key: value)
    myName: '홍길동',
    myAge: 20
};
// let 대신 const 사용.
// 기본적으로 두 키워드는 동일. 단, const는 재할당 불가
/*
obj = {    
}; // TypeError: Assignment to constant variable.
*/

// 즉, 상수나 객체를 const로 선언
//  *obj 자체에 대해 재할당이 안되는 것이고,
//   객체 내부의 값은 변경 가능
obj.myAge = 25; // 가능

console.log(obj);
console.log(obj.myName);
console.log(obj['[[Prototype]]']); // undefined
console.log(obj.__proto__);


// ----------------------------------------------------
console.log("# EXAMPLE 2");

const person = {
    name: '홍길동',
    age: 20
}

console.log(person);
console.log( Object.getOwnPropertyDescriptors(person) );
// Object.getOwnPropertyDescriptors(OBJ)
// 특정 객체의 모든 프로퍼티의 어트리뷰트를 가져옴



// ----------------------------------------------------
console.log("# EXAMPLE 3");

const person2 = { // 빈 객체 생성
};

// Object.defineProperty()를 이용해 객체에 프로퍼티를 정의할 수 있다.
Object.defineProperty(person2, 'firstName', {
    value: '홍',
    writable: true,
    enumerable: true,
    configurable: true
});

// 위와 같은 작업은 아래와 동일한 객체를 생성한다.
const person2_1 = {
    firstName: '홍'
}

console.log(person2.firstName); // '홍'

// => 객체 리터럴을 통해 쉽게 객체를 만들 수 있으나
// 내부적으로는 이러한 과정이 진행된다

person2.firstName = '최'
// writable이 true임으로 수정 가능하다. false라면 무시됨 (오류 없음)
console.log(person2.firstName);



// ----------------------------------------------------
console.log("# EXAMPLE 3");

const person3 = { // 빈 객체 생성
};

Object.defineProperty(person3, 'firstName', {
    value: '홍',
    writable: true,
    enumerable: false, // enumerable을 false로 지정
    configurable: true
});

for (let key in person2) {
    console.log(key); // person2의 경우 firstName 추가
}

for (let key in person3) {
    console.log(key);
    // person3의 경우 firstName 프로퍼티의 writable이 false이므로
    // 아무것도 출력되지 않음
}