// 모든 ul > li에 대해 클릭 이벤트에 대한 리스너를 등록

// document는 DOM 트리를 가리킴. document에 대한 ready 이벤트는 페이지의 모든 요소가 준비된 시점에 발생함.
$(document).ready(function() {
    
    $('ul > li').on('click', function() {
        alert($(this).text());
    });
});

// this
// 1. 일반 함수: 전역객체 (window)
// 2. 객체의 프로퍼티로 등록된 함수: 함수를 호출한 객체
// 3. 생성자 함수: 생성자 함수가 생성할 객체
// 그러나,
// jQuery 이벤트 처리 코드 내에서 등장하는 this는 이벤트 소스에 대한 문서객체

function handleAnchor(event) {
    event.preventDefault();
    alert("Link Clicked"); // Alert가 닫히면 페이지 전환 이벤트가 발생
}