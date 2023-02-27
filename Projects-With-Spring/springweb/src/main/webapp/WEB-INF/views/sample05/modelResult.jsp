<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	모델 안에 있는 데이터: 
	${ v1 }<br><br>
	
	VO안의 데이터:
	${ v2.userName }, ${ v2.userNumber }, ${ v2.userDept }<br><br>
	
	숫자 계산<br>
	num1: ${ data1 }<br>
	num2: ${ data2 }<br>
	합: ${ sum }<br>
</body>
</html>