<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% pageContext.setAttribute("msg",  "오늘은 치킨먹고 싶다!!!"); %>
	<h1>연산에 성공하였습니다.</h1>
	<div>요청 연산: ${ param.firstNum } ${ param.operator } ${ param.secondNum }</div>
	<div>연산 결과: ${ requestScope.msg } </div><!-- requestScope에 달린 msg를 가져옴 -->
	<div>연산 결과: <%= request.getAttribute("msg") %> </div><!-- 내용 동일 -->
	
	<a href="${ header.referer }">뒤로 가기</a>
	<div>하고 싶은 말: ${ msg }</div>
</body>
</html>