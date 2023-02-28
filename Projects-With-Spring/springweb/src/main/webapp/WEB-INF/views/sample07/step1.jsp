<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 1단계</title>
</head>
<body>
	<h1>사용자 기본 정보</h1>
	<form action="/springweb/memberRegisterStep2" method="post">
		아이디: <input type="text" name="memberId" value="${ memberInfo.memberId }"><br><br>
		이름: <input type="text" name="memberName" value="${ memberInfo.memberName }"><br><br>
		주소: <input type="text" name="memberAddr" value="${ memberInfo.memberAddr }"><br><br>
		<button type="submit">2단계로 이동</button>
	</form>
</body>
</html>