// ----------------------------------------------------
console.log("# EXAMPLE 1");

function foo() {
    console.log(this);
};
foo(); // 일반함수로 호출. this는 전역객체 window

// -------------------

const obj = {
    foo, // 'foo: foo'의 축약형 (ES6)
    name: '홍길동'
};
// 함수 foo는 객체 obj가 가진 함수.
// 이렇게 "축약형으로 들어간" 함수를 메소드라고 함 (ES6)

obj.foo();

// -------------------

const newObj = new foo();



// ----------------------------------------------------
console.log("# EXAMPLE 2");

// 생성자 함수는 new 키워드와 함께 호출됨
// 생성자 함수는 그 안에 return 구문이 없지만,
// 만들어진 객체가 묵시적으로 리턴됨

function Circle(radius) {
    this.radius = radius;
    // return this; // -> 쓰지 않는다.
}

const circle1 = new Circle(10); // 생성자 함수 호출. 객체를 만들어 리턴
const circle_mistake1 = Circle(10);
// 실수로 new를 빼게 되면, this는 전역객체가 되고
// 리턴 값이 없어 circle2는 undefined가 됨.
//   함수가 호출되었으나 리턴이 되지 않으면 묵시적으로 undefined

// -------------------

function Circle_mistake(radius) {
    this.radius = radius;
    return {};
}
const circle_mistake2 = new Circle_mistake(10); // {}

console.log(circle_mistake2); // {}
// 실수 방지를 위해서라도 return을 적으면 안된다.
// *참고: Primitive Value를 리턴하면 이를 무시하고 객체를 반환
//        객체를 리턴한 경우 무조건 해당 객체 반환