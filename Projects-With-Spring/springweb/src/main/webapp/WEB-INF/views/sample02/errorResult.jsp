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
	<h1>연산 중 오류가 발생했습니다.</h1>
	<div>오류 메시지: ${ msg } </div><!-- requestScope에 달린 msg를 가져옴 -->
	<div>오류 메시지: ${ requestScope.msg } </div> <!-- requestScope에 달린 msg를 가져옴 -->
	<div>오류 메시지: <%= request.getAttribute("msg") %> </div> <!-- 내용 동일 -->
</body>
</html>