let kobisKey;

$(function() {
    $('#search-btn').on('click', getData);
    $('#date-field').on('change', getData);

    var today = new Date();
    var yesterday = new Date(today.setDate(today.getDate() - 1));

    $('#date-field').val(yesterday.toISOString().split('T')[0]);

    $.get('./js/secret.json').done(function(data) {
        kobisKey = data.kobis_key;
        $('#search-btn').click();
    })
})

function dateStrToYYMMDD(dateStr) {
    const dateObj = new Date(dateStr);

    const year = dateObj.getFullYear();
    const month = ('0' + (dateObj.getMonth() + 1)).slice(-2);
    const date = ('0' + dateObj.getDate()).slice(-2);

    return `${year}${month}${date}`;
}

function getData(event) {
    event.preventDefault();

    const targetDate = dateStrToYYMMDD($('#date-field').val());
    $.ajax({ // 필요한 여러 정보를 객체로 넣어줌
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json', // 서버 url
        async: true, // 비동기 여부. 기본 값 true
        data: {
            key: kobisKey,
            targetDt: targetDate
        },
        type: 'GET',
        dataType: 'json',
        success: function(data) { // 성공시 수행, 서버에서 받은 json이 객체로 변환되어 인자로 전달됨
            console.log('성공');
            
            if (!data.hasOwnProperty('boxOfficeResult')) {
                alert('불러올 수 있는 데이터가 없습니다. \n날짜를 잘못 입력한 것이 아닌지 확인해보십시오.');
                return;
            }
            
            const list = data.boxOfficeResult.dailyBoxOfficeList;

            if (list.length === 0) {
                alert('불러올 수 있는 데이터가 없습니다.');
                return;
            }

            if($('tbody#result-body').text() !== '') {
                $('tbody#result-body').empty();
            }

            list.forEach((item, key) => {
                const rankTd = $('<td></td>').text(item.rank + '위');
                const posterTd = $('<td></td>').append('<img src="https://img.cgv.co.kr/Movie/Thumbnail/StillCut/000086/86217/86217206692_727.jpg" width="120px" />');
                const titleTd = $('<td></td>').text(item.movieNm);
                const audiTd = $('<td></td>').text(Number(item.audiAcc).toLocaleString() + '명');
                const openDtTd = $('<td></td>').text(item.openDt);

                const tr = $('<tr></tr>');
                tr.append(rankTd);
                tr.append(posterTd);
                tr.append(titleTd);
                tr.append(audiTd);
                tr.append(openDtTd);
                tr.attr('id', key)

                $('tbody#result-body').append(tr);
            });
        },
        error: function() { // 실패시 수행
            console.log('실패');
            alert('서버와 통신하는 중 문제가 발생했습니다.');
        }
    });
}