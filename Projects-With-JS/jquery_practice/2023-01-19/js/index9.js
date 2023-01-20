function handleInnerBtn(event) {
    event.stopPropagation();
    alert('버튼 클릭됨');
}

function handleOuterDiv() {
    alert('DIV 클릭됨');
}