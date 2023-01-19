function handleMouseEnter(event) {
    // $(event.target).css('color', 'red');
    // $(event.target).css('background-color', 'yellow');

    $(event.target).addClass('highlighted-block');
}

function handleMouseLeave(event) {
    // $(event.target).css('color', 'black');
    // $(event.target).css('background', 'white');

    $(event.target).removeClass('highlighted-block');
}

function handleBtn() {
    alert("버튼 클릭됨");
}

function handleDiv() {
    alert("div 클릭됨");
}