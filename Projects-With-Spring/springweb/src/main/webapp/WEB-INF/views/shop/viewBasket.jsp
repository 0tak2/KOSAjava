<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영택이의 과일가게: 장바구니</title>
</head>
<body>
	<h1>영택이네 과일가게</h1>
	<h2>장바구니</h2>
	<ul>
		<li>바나나: ${ myBasket.bananaCount }개</li>
		<li>사과: ${ myBasket.appleCount }개</li>
		<li>한라봉: ${ myBasket.halabongCount }개</li>
	</ul>
	
	<br>
	<div>
		합계: ${ myBasket.bananaCount + myBasket.appleCount + myBasket.halabongCount}개
	</div>
	<br>
	
	<a href="/springweb/resources/shop/shop.html">쇼핑 계속하기</a><br>
	<a href="/springweb/shop/purchase">구매하고 장바구니 비우기</a>
</body>
</html>