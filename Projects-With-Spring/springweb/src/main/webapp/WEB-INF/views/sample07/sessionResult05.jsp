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
	<h1>수행된 결과</h1>
	<h2>RequestScope</h2>
	<ul>
		<li>requestScope.data1: ${ requestScope.data1 }</li> <!-- createString1 -->
		<li>requestScope.data2: ${ requestScope.data2 }</li> <!-- createString2 -->
		<li>param.msg: ${ param.msg }</li>
		<li>requestScope.studentVO: ${ requestScope.studentVO }</li>
		<li>requestScope.student: ${ requestScope.student }</li>
		<li>requestScope.newStudent: ${ requestScope.newStudent }</li>
	</ul>
	<h2>SessionScope</h2>
	<ul>
		<li>sessionScope.data1: ${ sessionScope.data1 }</li> <!-- createString1 -->
		<li>sessionScope.data2: ${ sessionScope.data2 }</li> <!-- createString2 -->
		<li>sessionScope.newStudent: ${ sessionScope.newStudent }</li>
		<li>sessionScope.shin.stduentName: ${ sessionScope.shin.studentName }</li>
		<li>sessionScope.shin.studentDept: ${ sessionScope.shin.studentDept }</li>
	</ul>
</body>
</html>