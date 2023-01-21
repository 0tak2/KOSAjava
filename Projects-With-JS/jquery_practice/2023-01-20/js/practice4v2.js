// 전역 스코프의 API 키를 담은 secret 객체
let kobisKey = secret.kobis_key;
let kakaoKey = secret.kakao_key;

// 이벤트 핸들러 등록
$(function() {
    $('#search-btn').on('click', getData);
    $('#date-field').on('change', getData);
    $('#delete-btn').on('click', deleteMultiple);

    var today = new Date();
    var yesterday = new Date(today.setDate(today.getDate() - 1));

    $('#date-field').val(formatDateStr(yesterday, true));

    $('#search-btn').click();
});

function formatDateStr(dateStr, toISO) {
    const dateObj = new Date(dateStr);

    const year = dateObj.getFullYear();
    const month = ('0' + (dateObj.getMonth() + 1)).slice(-2); // 뒤에서부터 두 글자를 복사하여 새로운 문자열 리턴
    const date = ('0' + dateObj.getDate()).slice(-2);

    if (toISO) {
        return `${year}-${month}-${date}`;
    } else {
        return `${year}${month}${date}`;
    }
}

function deleteMultiple() {
    const targetKeysList = [];

    $('input[type="checkbox"].delete-checkbox:checked').each(function() {
        targetKeysList.push($(this).parents('tr').attr('data-key'));
    })

    targetKeysList.forEach((key) => {
        $(`tr[data-key="${key}"]`).remove();
    })
}

function deleteOne() {
    const targetKey = $(this).parents('tr').attr('data-key');
    $(`tr[data-key="${targetKey}"]`).remove();
}

function isBlocked(url) {
    const blockedHosts = [
        'pstatic.net',
        'daumcdn.net',
        'daum.net'
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

    const targetDate = formatDateStr($('#date-field').val());

    // 영화산업진흥위원회 검색
    $.ajax({
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json', // 서버 url
        async: true,
        data: {
            key: kobisKey,
            targetDt: targetDate
        },
        type: 'GET',
        dataType: 'json',
        success: function() {
            console.log('[KOBIS] 통신 성공');
        },
        error: function() {
            console.log('[KOBIS] 통신 실패');
            alert('서버와 통신하는 중 문제가 발생했습니다.');
        }
    })
    .done(function(data) {
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
                             .addClass('delete-checkbox');
    
            const deleteBtn = $('<button></button>')
                             .text('삭제')
                             .addClass('btn')
                             .addClass('btn-danger')
                             .addClass('btn-sm')
                             .click(deleteOne);
            
            const checkTd = $('<td></td>')
                            .append(checkbox);
    
            const rankTd = $('<td></td>')
                           .text(item.rank + '위');
    
            const posterTd = $('<td></td>');
    
            const titleTd = $('<td></td>')
                            .text(item.movieNm);
    
            const audiTd = $('<td></td>')
                           .text(Number(item.audiAcc).toLocaleString() + '명');
    
            const openDtTd = $('<td></td>')
                             .text(item.openDt);
    
            const delBtnTd = $('<td></td>')
                             .append(deleteBtn);
    
            const tr = $('<tr></tr>');
            tr.append(checkTd);
            tr.append(rankTd);
            tr.append(posterTd);
            tr.append(titleTd);
            tr.append(audiTd);
            tr.append(openDtTd);
            tr.append(delBtnTd);
            tr.attr('data-key', key);
            $('tbody#result-body').append(tr); // 우선 이미지가 없는 상태로 붙여줌
    
            // 카카오 이미지 검색
            $.ajax({
                async: true,
                url: 'https://dapi.kakao.com/v2/search/image',
                type: 'GET',
                headers: {
                    Authorization: 'KakaoAK ' + kakaoKey
                },
                data: {
                    query: item.movieNm
                },
                dataType: 'json',
                success: function(data) {
                    console.log('[KAKAO] 통신 성공');
                    console.dir(data);
                    let img_url;
                    for (const idx in data.documents) { // 접근 불가능한 호스트 필터링
                        currentURL = data.documents[idx].image_url;
                        if (!isBlocked(currentURL)) {
                            img_url = currentURL;
                            break;
                        }
                    }
                    const imgEl = $('<img />').attr('src', img_url);
                    posterTd.append(imgEl);
                },
                error: function(err) {
                    console.log('[KAKAO] 통신 실패');
                    console.err(err);
                    console.err(el);
                }
            });
        });
    });
}

