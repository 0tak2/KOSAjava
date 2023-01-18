/* ##################### Example 1 ##################### */
console.log("\nEXAMPLE 1");

// 일반 객체
var obj = {
    name: '홍길동'
}

// 함수 선언문
function myFunc(number) {
    return 2 * number;
}

console.dir(myFunc);
console.dir(obj);

// ----------------------

function myFunc2() {
    // arguments 유사배열 객체 사용
    let result = 0;

    /* for (let i=0; i<arguments.length; i++) {
        result += arguments[i];
    } */

    for (let i in arguments) {
        result += arguments[i];
    }

    return result;
}

console.log(myFunc2(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21)); // 121



/* ##################### Example 2 ##################### */
console.log("\nEXAMPLE 2");

function restParam(...args) {
    let result = 0;
    args.forEach(item => { // 배열이므로 forEach 사용 가능
        result += item;
    })
    return result;
}

console.log(restParam(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21)); // 121

// 온전한 배열이므로 ES6부터 arguments 보다 잘 활용되며,
// Arrow Function에서는 그 자체로 arguments 프로퍼티가 없어,
// Rest Parameters를 사용해야 한다.



/* ##################### Example 3 ##################### */
console.log("\nEXAMPLE 3");

function Circle(radius) {
    this.radius = radius; // 프로퍼티
    this.getArea = function() {
        return Math.PI * this.radius ** 2;
        // Math는 빌트인 오브젝트
        // ** 는 제곱 연산자
    }
}

const circle1 = new Circle(2);
const circle2 = new Circle(5);



/* ##################### Example 4 ##################### */
console.log("\nEXAMPLE 4");

function CircleEnhanced(radius) {
    this.radius = radius; // 프로퍼티
    CircleEnhanced.prototype.getArea = function() { // 프로토타입
        return Math.PI * this.radius ** 2;
    }
}

const circle_new1 = new CircleEnhanced(2);
const circle_new2 = new CircleEnhanced(5);

// 참고:
// == 내용이 같은지 비교
//    단 타입이 다르면 일단 타입을 변환하여 통일시키고 비교
// === 타입이 같고 내용도 같은지 비교
//     완전히 동일한지를 알 수 있음

console.log(circle_new1.radius === circle_new2.radius); // false
console.log(circle_new1.getArea === circle_new2.getArea); // true



/* ##################### Example 5 ##################### */
console.log("\nEXAMPLE 5");

function CircleEnhanced2(radius) {
    this.radius = radius;
    CircleEnhanced2.prototype.getArea = function() {
        return Math.PI * this.radius ** 2;
    }; // 공유 프로퍼티 (함수)
    CircleEnhanced2.prototype.name = '홍길동'; // 공유 프로퍼티 (변수)
}

const circle_enhanced1 = new CircleEnhanced2(2);
const circle_enhanced2 = new CircleEnhanced2(5);

console.log(circle_enhanced1.name, circle_enhanced2.name); // 홍길동 홍길동

circle_enhanced1.name = '신사임당';
console.log(circle_enhanced1.name, circle_enhanced2.name); // 신사임당 홍길동
// 원래 circle_enhanced의 프로퍼티에는 name이 없었으므로,
// circle_enhanced1.name은 Prototype 객체의 name 프로퍼티를 가리킴
//
// 그러나 circle_enhanced1.name에 새로운 값을 할당하게 되면,
// circle_enhanced1에 name 프로퍼티를 만들고 새로운 값을 할당
//
// 이후부터는 circle_enhanced1.name 참조시 객체 자체에
// 새롭게 할당된 name 프로퍼티가 있으므로, 해당 값을 가리키게 됨.

// ----------------------

delete circle_enhanced1.name;
circle_enhanced1.__proto__.name = '강감찬';

console.log(circle_enhanced1.name, circle_enhanced2.name); // 강감찬 강감찬

// ----------------------

console.log(circle_enhanced1.constructor === CircleEnhanced2); // true



/* ##################### Example 6 ##################### */
console.log("\nEXAMPLE 6");

console.dir(circle_enhanced1);
/*
CircleEnhanced2
radius: 2
[[Prototype]]: Object
    getArea: ƒ ()
    name: "강감찬"
    constructor: ƒ CircleEnhanced2(radius)
    [[Prototype]]: Object
*/



/* ##################### Example 7 ##################### */
console.log("\nEXAMPLE 7");

// 프로토타입은 결국 상속구조를 나타내기 위한 것임
const anotherObj = {};
const parent = {
    x: 1
};

anotherObj.__proto__ = parent;  // 상위 프로토타입을 바꾸었다

console.log(anotherObj.x); // 1
// 클래스 기반의 자바에서는 상상할 수 없는 일이다.
// 생성된 객체의 프로퍼티를 수정하여
// 상속 관계를 인스턴스 레벨에서 임의로/동적으로 설정한 것이다.
// 자바는 클래스 레벨에서 상속 구조가 결정되고, 인스턴스 생성 후에는 변경할 수 없다.



/* ##################### Example 8 ##################### */
console.log("\nEXAMPLE 8");

var newAnotherObj = {
    name: "홍길동"
}

console.dir(newAnotherObj);
// 'new Object()'가 아닌 리터럴로 만든 객체임에도,
// [[Prototype]]의 constructor가 Object()

// 자바스크립트의 Object()를 통해 Protorype이 상속되어
// 객체에 대한 갖가지 기능을 사용 가능한 것.
// __proto__ 역시 Object()의 prototype 프로퍼티가 가리키는
// Prototype 객체에 위치하고 있음.

// ----------------------

const circle_test = new CircleEnhanced2(3);

console.dir(circle_test);



/* ##################### Example 9 ##################### */
console.log("\nEXAMPLE 9");

// const obj = new Object(); // 생성자 함수가 아닌,

// 프로퍼티 함수로 객체를 생성하는 경우,
// 임의로 상위 프로토타입 지정 가능
// Object.create(상위_프로토타입_객체)
//
// 만약 인자로 null을 넘기면 상위 프로토타입이 없는 객체가 생성됨
// __proto__ 등 상속받지 못해 사용 불가
const someWhatObj = Object.create(null);
console.dir(someWhatObj);