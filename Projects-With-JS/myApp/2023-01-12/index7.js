let i = 100 // i는 전역 변수. 스코프: ln 1 ~ END

function myFunc() {
    let i = 10 // i는 지역 변수. 스코프: 블록 내

    for (let i = 0; i<3; i++) { // i: 지역 변수. 스코프: for문 내
        console.log(i) // 0 1 2
    }

    console.log(10) // 10
}

myFunc();

console.log(i);