<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 2단계</title>
</head>
<body>
	<h1>사용자 세부 정보</h1>
	<form action="/springweb/memberRegisterStep3" method="post">
		취미: <input type="text" name="memberHobby" value="${ memberInfo.memberHobby }"><br><br>
		자기소개: <input type="text" name="memberContent" value="${ memberInfo.memberContent }"><br><br>
		<button type="submit">저장</button>
	</form>
	<form action="/springweb/memberRegisterStep1" method="post">
		<button type="submit">이전 단계로 이동</button>
	</form>
</body>
</html>