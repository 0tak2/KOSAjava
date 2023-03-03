<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Count</title>
</head>
<body>
	<h1>수행 결과</h1>
	
	<span>전체 책 수: ${ result }</span>
	
	<a href="${ header.referer }">뒤로가기</a>
</body>
</html>