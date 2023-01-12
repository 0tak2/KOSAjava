// 자바스크립트는 Array를 사용.
// 자바의 ArrayList와 같은 것이 없음.

const myArray = [1, 2, 3, 4]

/* 기본 for 구분
for(var i=0; i<4; i++) {
    // 작업
}
*/
// 일반적으로 사용하지 않음. 대신 forEach 사용

myArray.forEach(function (item) {
    console.log(item)
})
