// console.log(x); // Error!

x = 100; // Not Error! 암묵적 전역... 좋은 케이스는 아님

console.log(x);

function myFunc() {
    console.log(x);
    var x;
}

myFunc();