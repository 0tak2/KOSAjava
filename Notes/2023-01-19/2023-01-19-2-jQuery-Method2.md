---
title: jQuery 메서드 2
---

# jQuery 메서드 2

앞에서 이미 생성되어있는 DOM을 읽는 작업을 하였다. 이번에는 DOM 새로 만들거나 자식 DOM으로 추가하는 방법을 알아본다.

## DOM 만들기

```html
<ul>
  <li>홍길동</li>
  <li>신사임당</li>
  <li>강감찬</li>
</ul>
<button class="btn btn-primary" onclick="handleBtn()">만들기</button>
```

```js
function handleBtn() {
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem') // 클래스 추가

    // use variable li
}
```

## DOM 붙이기

새롭게 만든 요소를 문서 어딘가에 붙일 수 있다.

### 1. append

가장 마지막 자식으로 붙인다.

```js
function handleBtn() {
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem'); // 클래스 추가

        $('ul').append(li);
}
```

### 2. prepend

가장 첫번째 자식으로 붙인다.

```js
function handleBtn() {
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem'); // 클래스 추가

    $('ul').prepend(li); // 가장 첫번째 자식으로 추가
}
```

### 3. before

앞의 형제로 붙인다.

```js
function handleBtn() {
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem'); // 클래스 추가

    $('ul > li:last').before(li); // 가장 마지막 li의 앞에 있는 형제로 추가
}
```

### 4. after

뒤의 형제로 붙인다.

```js
function handleBtn1() {
    // 새로운 요소를 jQuery를 이용해 생성
    let li = $('<li></li>') // 선택자가 아닌 태그 자체. 해당 태그에 해당하는 요소를 반환
        .text('이순신') // 태그 사이의 이너 텍스트 지정
        .attr('id', 'myID') // 해당 어트리뷰트 지정
        .addClass('listItem'); // 클래스 추가

    $('ul > li:first').after(li); // 가장 첫번째 li의 위에 있는 형제로 추가
}
```

## \<img\>와 같이 닫히지 않는 태그를 만들려면

```js
function handleBtn2() {
    // 새로운 이미지를 생성
    // <img src='./some/path/to/img/picture.jpg'> :  끝나는 태그 없음
    let img = $('<img  /><br />')
              .attr('src', './images/painting.jpg')
              .css('width', '300px');
    $('ul').after(img);
}
```