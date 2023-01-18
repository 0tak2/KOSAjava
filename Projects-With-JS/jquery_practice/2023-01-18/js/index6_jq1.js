/* jQuery의 기본 사용 방법

1. 기본
    $('')
    인자로 Selector를 넘김. 해당 Selector에 해당하는 엘리먼트를 찾는 기능 수행
    CSS의 Selector를 그대로 사용
        $('h1'), $('#text'), $('.container')
    
    엘리먼트를 찾은 후 아래와 같이 원하는 메서드를 찾아 실행
    $('').some_method()
2. 다른 사용법
    $

*/

function handleBtn1() {
    // 1. 전체 선택자 '*'
    $('*').css('color', 'red'); // 모든 요소에 대해 스타일시트의 color 속성을 red로 변경
}

function handleBtn2() {
    // 2. 타입 선택자 / '태그 이름'
    $('li').remove(); // 요소 삭제
}

function handleBtn3() {
    // 3. ID 선택자 / '#ID'
    let name = $('#kang').text(); // 태그 사이에 있는 값을 가져와 반환
    alert(name);
}

function handleBtn4() {
    // 4. 클래스 선택자 / '.CLASS'
    $('.highlight').css('background-color', 'yellow');
}

function handleBtn5() {
    // 5. 구조 선택자 (자식선택자와 후손선택자)
    // 자손선택자: 앞선 선택자와 '>'를 조합. 부모 > 자식
    // 후손선택자: 직계자식이 뿐 아니라 후손을 포함. 공백 문자를 조합.
    $('div > div').text('가장 좋아하는 역사인물은?'); // 값을 가져오는 메서드지만, 인자를 넣어주면 값을 변경
    $('div li').css('font-weight', 'bold');
}

function handleBtn6() {
    $('body > div').remove();
    $('body').append('<marquee>집에 보내주세오</marquee>');
}

function handleBtn7() {
    // 6. 동위 선택자 / +, ~
    // + 바로 뒤에 나오는 형제

    $('#kang + li').text('을지문덕');
}

function handleBtn8() {
    // 6. 동위 선택자 / +, ~
    // ~ 뒤에 나오는 모든 형제

    $('#shin ~ *').remove();
}

function handleBtn9() {
    // 7. 속성 선택자 / []
    console.log(
        $('[type]') // type이라는 어트리뷰트가 있는 모든 엘리먼트를 찾는다
        .attr('value') // 특정 어트리뷰트를 가져온다
    );
}