<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인</h1>
	<span>게시판을 이용하려면 로그인해주세요.</span>
	<form action="login" method="post">
		<% String nextUrl = request.getParameter("nextUrl"); %>
		<input type="hidden" name="nextUrl" value="<%= nextUrl %>">
		ID: <input type="text" name="userID"> <br>
		PW: <input type="password" name="userPW"> <br><br>
		<button type="submit">로그인</button>
	</form>
</body>
</html>