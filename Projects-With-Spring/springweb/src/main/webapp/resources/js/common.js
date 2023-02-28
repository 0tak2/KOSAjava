function myFunc() {
	// 사용자가 선택한 URL을 form의 action에 지정
	let url = $('#myForm > select > option:selected').text();
	$('#myForm').attr('action', url);
	// 이후 디폴트 이벤트가 발동되어 submit됨
}