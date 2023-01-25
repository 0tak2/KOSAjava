function searchBtn() {
    // 테이블 내용 지우기
    $('tbody').empty();

    // 날짜를 가져와 AJAX 호출
    $.ajax({
        async: true,
        url: 'http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json',
        type: 'GET',
        timeout: 3000, // 3000ms(=3s) 동안만 기다림. 오래 걸리면 실패로 간주
        data: {
            key: secret.kobis_key,
            targetDt: ($('input[type=date]').val()).replace(/\-/g, '')
        },
        dataType: 'json', // 디폴트가 json이라 안써도 됨
        success: function(data) {
            // 서버 응답은 JSON 형식의 평문으로 오지만,
            // jQuery는 이를 자동으로 객체에 맵핑하여 data 객체로 전달해줌

            let arr = data.boxOfficeResult.dailyBoxOfficeList;
            $.each(arr, function(idx, item) { // 인덱스와 현재 원소
                // console.log(idx, item.movieNm); // 확인 완료
                // <tr>
                //     <td>1</td>
                //     <td><img src="https://" width="120px"></td>
                //     <td>아바타</td>
                //     <td>900만명</td>
                //     <td>2023-01-01</td>
                //     <td><button>삭제</button></td>
                // </tr>
                let rankTd = $("<td></td>").text(item.rank);

                let posterImg = $("<img />")
                let posterTd = $("<td></td>").append(posterImg);
                // 이미지를 가져오기 위한 AJAX 호출
                $.ajax({
                    async: true,
                    url: 'https://dapi.kakao.com/v2/search/image',
                    type: 'GET',
                    headers: {
                        Authorization: 'KakaoAK ' + secret.kakao_key
                    },
                    data: {
                        query: item.movieNm
                    },
                    dataType: 'json',
                    success: function(data) {
                        console.log('[KAKAO] 통신 성공');
                        let imgUrl = data.documents[0].thumbnail_url;
                        posterImg.attr('src', imgUrl); // 클로져.
                        // $.ajax()는 Non-blocking 이므로 호출 직후 다음으로 넘어감.
                        // 따라서 이미 상위 스코프가 사라진 시점에도 렉시컬 환경이 유지되어
                        // posterImg에 참조 가능
                    },
                    error: function() {
                        console.log('[KAKAO] 통신 실패');
                    }
                });

                let titleTd = $("<td></td>").text(item.movieNm);

                let audTd = $("<td></td>").text(item.audiCnt);

                let opendTd = $("<td></td>").text(item.openDt);

                let delBtn = $("<button></button>").text('삭제')
                                .addClass('btn btn-danger')
                                .click(function() { // 클릭 이벤트 처리 함수 등록
                                    $(this) // 자신(button)의 부모(td)의 부모(tr)를 찾아야 함
                                    .parent()
                                    .parent()
                                    .remove();
                                });
                let delTd = $("<td></td>")
                                .append(delBtn);
                
                let tr = $("<tr></tr>").append(rankTd)
                            .append(posterTd)
                            .append(titleTd)
                            .append(audTd)
                            .append(opendTd)
                            .append(delTd);

                $('tbody').append(tr);
            });
        },
        error: function() {
            alert('error')
        }
    });

    // 그런데 클릭한 것이 <a>이므로
    // 디폴트 이벤트를 막아야 새로고침을 방지할 수 있음
    event.preventDefault();
}