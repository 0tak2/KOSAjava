function handleEvent(event) { // event: 이벤트에 대한 자세한 정보
    // const index = event.target.selectedIndex; // event.target: 이벤트가 발생한 DOM 요소
    // $('#result').text(event.target[index].text);
    $('#result').text($('select > option:selected').text());
}