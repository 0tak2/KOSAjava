function handleBtn() {
    // console.log($('ul > li').text()); // 홍길동신사임당강감찬
    $('ul > li').each(function(idx, item) { // 각 li 요소마다 넘겨준 콜백함수가 실행됨
        // idx: 순번(0부터 1 증가).
        // item: 현재 문서 객체 (DOM 입력됨. jQuery 객체가 아님.)
        console.log($(item).text());
    });
}