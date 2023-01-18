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
console.log(dateObj.toLocaleString()); // 2023. 1. 16. 오후 12:31:18
console.log(dateObj.getHours()); // 12



// ----------------------------------------------------
console.log("# EXAMPLE 4");

// 생성자 함수는 관용적으로 식별자의 첫 글자를 대문자로 한다.
function Person() {
}

const person1 = new Person();
// 생성자 함수는 일반 함수와 동일하게 생겼다. 
// 이름이 관용적으로 대문자로 시작할 뿐이다.

const person2 = {};

console.log(person1); // Person {}
console.log(person2); // {}
// 콘솔에 조금 다르게 출력되며, 내부 구조도 약간 다르다.
// 내용이 비어있다는 것은 동일하다.



// ----------------------------------------------------
console.log("# EXAMPLE 5");

function Circle(radius) {
    this.radius = radius; // 객체의 프로퍼티에 반지름 할당
    this.getDiameter = function() { // 객체의 프로퍼티에 함수 할당
        return 2 * 3.14 * radius;
    }

    // 생성자 함수의 형태
    // 1. this keyword
    // 2. return이 없음
    //    대신 묵시적으로 생성된 객체를 리턴.
    //    따라서 return을 쓰지 않는 것임.
}

const circle1 = new Circle(5); // 객체 생성
const circle2 = new Circle(10);

console.log(circle1);
console.log(circle1.getDiameter());