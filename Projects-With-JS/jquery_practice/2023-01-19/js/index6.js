function handleBtn1() { // 새로운 아이템
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem'); // 클래스 추가
    
    // $('ul').append(li); // 가장 마지막 자식으로 추가
    // $('ul').prepend(li); // 가장 첫번째 자식으로 추가
    // $('ul > li:last').before(li); // 가장 마지막 li의 앞에 있는 형제로 추가
    $('ul > li:first').after(li); // 가장 첫번째 li의 위에 있는 형제로 추가
}

function handleBtn2() { // 새로운 이미지
    // 새로운 이미지를 생성
    // <img src='./some/path/to/img/picture.jpg'> :  끝나는 태그 없음
    let img = $('<img  /><br />')
              .attr('src', './images/painting.jpg')
              .css('width', '300px');
    $('ul').after(img);
}

function handleBtn3() { // 새로운 이벤트 리스너
    $('ul > li:last') // 1. 이벤트 핸들러를 붙일 요소를 찾는다. <li>강감찬</li>
    /* .on('click', function() {
        console.log('아이템 \'강감찬\'이 클릭됨');
    }) // 이벤트 핸들러를 특정 이벤트에 등록 */
    .click(function() {
        console.log('아이템 \'강감찬\'이 클릭됨');
    }) // 이벤트 핸들러를 특정 이벤트에 등록
    console.log('새로운 이벤트 리스너 등록 완료');
}