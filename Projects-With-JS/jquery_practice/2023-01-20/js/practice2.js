let kobisKey;

$(function() {
    $.get('./js/secret.json').done(function(data) {
        kobisKey = data.kobis_key;
    })
})

function handleAjaxBtn() {
    $.ajax({ // 필요한 여러 정보를 객체로 넣어줌
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json', // 서버 url
        async: true, // 비동기 여부. 기본 값 true
        data: {
            key: kobisKey,
            targetDt: '20230119'
        },
        type: 'GET',
        dataType: 'json',
        success: function(data) { // 성공시 수행, 서버에서 받은 json이 객체로 변환되어 인자로 전달됨
            console.log('성공');

            if($('#result').text() !== '') {
                $('#result').empty();
            }

            data.boxOfficeResult.dailyBoxOfficeList.forEach(element => {
                const el = $('<div></div>')
                .text(`${element.rank}위 ${element.movieNm}`);

                $('#result').append(el);
            });
        },
        error: function() { // 실패시 수행
            console.log('실패');
        }
    });
}