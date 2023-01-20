let kakaoKey;

$(function() {
    $.get('./js/secret.json').done(function(data) {
        kakaoKey = data.kakao_key;
    })
})

function handleBtn() {
    // 버튼을 누르면 카카오 이미지 검색 API에 요청
    // 반환 결과의 첫번째 이미지를 DIV에 붙인다
    $.ajax({
        async: true,
        url: "https://dapi.kakao.com/v2/search/image",
        type: "GET",
        headers: {
            Authorization: 'KakaoAK ' + kakaoKey
        },
        data: {
            query: '설현'
        },
        dataType: 'json',
        success: function(data) {
            const img_url = data.documents[0].image_url;
            const imgEl = $('<img />').attr('src', img_url);
            $('div.result').append(imgEl);
        },
        error: function() {
            console.log("에러");
        }
    });

}