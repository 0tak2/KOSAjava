// 호이스팅은 var이든 let이든 const든 모두 일어나는 현상

let myVar = 1; // 전역변수. 블록 내에 갇히지 않음

{
    console.log(myVar); // 전역의 하위 스코프이므로 사용 가능
}

//

let myVar2 = 1;

{
    console.log(myVar2); // 지역변수가 호이스팅되어 오류 발생
    let myVar2 = 100; 
}