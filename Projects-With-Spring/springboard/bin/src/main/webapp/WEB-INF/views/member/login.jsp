<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Login</title>
</head>
<body>
<h1>
	로그인이 필요한 서비스입니다. 
</h1>

<form action="/springboard/member/login" method="post">
	<input type="text" name="memberId" placeholder="아이디"><br>
	<input type="password" name="memberPw" placeholder="비밀번호"><br><br>
	<button type="submit">로그인</button>
</form>
</body>
</html>
