$.ajax({
    url: 'http://notfoundhost/', // 서버 url
    async: true,
    type: 'GET',
    dataType: 'json',
    success: function() { // 어떤 콜백이 먼저 실행될지 할 수 없다.
        console.log('서버 1 > 통신 성공');
    },
    error: function() {
        console.log('서버 1 > 통신 실패');
    }
});

$.ajax({
    url: 'https://www.google.com/', // 서버 url
    async: true,
    type: 'GET',
    dataType: 'json',
    success: function() {
        console.log('서버 2 > 통신 성공');
    },
    error: function() {
        console.log('서버 2 > 통신 실패');
    }
});