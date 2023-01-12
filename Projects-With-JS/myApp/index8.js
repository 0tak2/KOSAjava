console.log(x);
    // TDZ (Temporal Dead Zone): 호이스팅은 되었으나 사용 불가
let x = 100;
// Error. 호이스팅은 발생하지만, let으로 선언되었으므로 에러 발생
// let 변수는 x를 초기화한 이후의 시점부터 사용 가능