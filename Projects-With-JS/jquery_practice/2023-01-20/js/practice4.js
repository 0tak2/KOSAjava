let kobisKey;
let kakaoKey;

$(function() {
    $('#search-btn').on('click', getData);
    $('#date-field').on('change', getData);
    $('#delete-btn').on('click', deleteMultiple);

    var today = new Date();
    var yesterday = new Date(today.setDate(today.getDate() - 1));

    $('#date-field').val(yesterday.toISOString().split('T')[0]);

    $.get('./js/secret.json').done(function(data) {
        kobisKey = data.kobis_key;
        kakaoKey = data.kakao_key;
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

function deleteMultiple() {
    const targetKeysList = [];

    $('input.delete-checkbox:checked').each(function() {
        targetKeysList.push($(this).attr('data-key'));
    })

    targetKeysList.forEach((key) => {
        $('#tr' + key).remove();
    })
}

function deleteOne() {
    const targetKey = $(this).attr('data-key');
    $('#tr' + targetKey).remove();
}

function isBlocked(url) {
    const blockedHosts = [
        'postfiles.pstatic.net',
        't1.daumcdn.net/cafeattach/'
    ]

    let result = false;
    blockedHosts.forEach((item) => {
        if(url.includes(item)) {
            result = true;
        }
    });

    return result;
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
        success: function() {
            console.log('[KOBIS] 통신 성공');
        },
        error: function() { // 실패시 수행
            console.log('[KOBIS] 통신 실패');
            alert('서버와 통신하는 중 문제가 발생했습니다.');
        }
    })
    .then(function(data) { // 성공시 수행, 서버에서 받은 json이 객체로 변환되어 인자로 전달됨
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
            const checkbox = $('<input />')
                             .attr('type', 'checkbox')
                             .attr('data-key', key)
                             .addClass('delete-checkbox');

            const deleteBtn = $('<button></button>')
                             .text('삭제')
                             .addClass('btn')
                             .addClass('btn-danger')
                             .addClass('btn-sm')
                             .attr('data-key', key)
                             .click(deleteOne);
            
            const checkTd = $('<td></td>')
                            .append(checkbox)
                            .addClass('check-td');
            const rankTd = $('<td></td>').text(item.rank + '위')
                            .addClass('rank-td');
            const posterTd = $('<td></td>')
                             .addClass('poster-td');
            const titleTd = $('<td></td>').text(item.movieNm)
                            .addClass('title-td');
            const audiTd = $('<td></td>').text(Number(item.audiAcc).toLocaleString() + '명')
                           .addClass('audiAcc-td');
            const openDtTd = $('<td></td>')
                             .text(item.openDt)
                             .addClass('openDt-td');
            const delBtnTd = $('<td></td>').append(deleteBtn);

            const tr = $('<tr></tr>');
            tr.append(checkTd);
            tr.append(rankTd);
            tr.append(posterTd);
            tr.append(titleTd);
            tr.append(audiTd);
            tr.append(openDtTd);
            tr.append(delBtnTd);
            tr.attr('id', 'tr' + key)
            tr.hide();

            $('tbody#result-body').append(tr);
        });
    })
    .then(function() {
        $('tbody > tr').each(function(idx, el) {
            $.ajax({
                async: true,
                url: 'https://dapi.kakao.com/v2/search/image',
                type: 'GET',
                headers: {
                    Authorization: 'KakaoAK ' + kakaoKey
                },
                data: {
                    query: $(el).children('td.title-td').text()
                },
                dataType: 'json',
                success: function(data) {
                    console.log('[KAKAO] 통신 성공');
                    let img_url = data.documents[0].image_url;
                    for (const idx in data.documents) {
                        currentURL = data.documents[idx].image_url;
                        if (!isBlocked(currentURL)) {
                            img_url = currentURL;
                            break;
                        }
                    }
                    const imgEl = $('<img />').attr('src', img_url);
                    $(el).children('td.poster-td').append(imgEl);
                    $(el).show();
                },
                error: function(err) {
                    console.log('[KAKAO] 통신 성공');
                    console.err(err);
                    console.err(el);
                }
            });
        })
    });
}