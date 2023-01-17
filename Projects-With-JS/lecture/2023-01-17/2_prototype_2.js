/* ##################### Example 1 ##################### */
console.log("\nEXAMPLE 1");

const obj = {};

const parent = {
    x: 1
};

/* obj.__proto__.DO_SOMETHING // -> Bad. '__proto__' is deprecated. */
const property = Object.getPrototypeOf(obj);

Object.setPrototypeOf(obj, parent); // set the property of obj to 'parent'

console.log(obj.x);



/* ##################### Example 2 ##################### */
console.log("\nEXAMPLE 2");

// Non-constructor 함수에는 정말 property 객체가 생성되지 않는지 검증

const person = (name) => {
    this.name = name
};

console.log(person.prototype) // undefined



/* ##################### TEST ##################### */
console.log("\nTEST");

function CircleTest(radius) {
    this.radius = radius;
}

CircleTest.prototype.getArea = function() {
    return Math.PI * this.radius ** 2;
}

const circleTest = new CircleTest(5);
console.log(circleTest.getArea()); // 78.53981633974483



/* ##################### Example 3 ##################### */
console.log("\nEXAMPLE 3");

function Circle(radius) {
    this.radius = radius;
}

Circle.prototype.getArea = function() {
    return Math.PI * this.radius ** 2;
}

const circle1 = new Circle(5);
console.dir(circle1);