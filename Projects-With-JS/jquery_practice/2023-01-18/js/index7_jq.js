function handleButton() {
    $('ul > *').each(function() {
        console.log($(this).text());
    });
    console.log($('form > input').attr('id'));
    $('ol > .myList').each(function() {
        console.log($(this).text());
    });
}

function handleButton_beomsu() {
    $('body').append('<br><br><textarea rows="16"></textarea>') ;

    let array = [];

    $('ul > *').each(function() {
        array.push($(this).text());
    });
    array.push($('form > input').attr('id'));
    $('ol > .myList').each(function() {
        array.push($(this).text());
    });

    array.forEach((item) => {
        const prevText = $('textarea').text()
        $('textarea').text(prevText + item + '\n');
    })
}

function handleButton_ans() {
    console.log($('#apple').text());
    console.log($('#apple + li').text());
    console.log($('ul > li.myList').text());
    console.log($('form > input').attr('id'));
    console.log($('ol > li.myList:first').text()); // :first는 첫번쨰라는 의미의 선택자
    console.log($('ol > li.myList:first + li').text());
    //console.log($('ol > li.myList:nth-child(2)').text());
    console.log($('ol > li.myList:last').text());
}