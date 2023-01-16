// ----------------------------------------------------
console.log("# EXAMPLE 1");

// new 키워드를 통해 생성자 함수 호출 가능
const person = new Object(); // 내용적으로는 {}와 같으나, 내부 구조는 다름

person.name = '홍길동';
person.age = 20; // 동적할당 가능. 이때 attribute는 default값

console.log(person);



// ----------------------------------------------------
console.log("# EXAMPLE 2");

const str = '소리없는 아우성!';
const strObj = new String('소리없는 아우성!');
console.log(typeof str); // string
console.log(typeof strObj); // object
console.log(strObj); // '소리없는 아우성!'이 아닌 String {'소리없는 아우성!'}



// ----------------------------------------------------
console.log("# EXAMPLE 3");

const numObj = new Number(100);
console.log(numObj);



// ----------------------------------------------------
console.log("# EXAMPLE 3");

const dateObj = new Date(); // 현재 시간
console.log(dateObj); // Mon Jan 16 2023 12:29:19 GMT+0900
console.log(dateObj.toLocaleString());
console.log(dateObj.getHours()); // 12