<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보</h1>
	
	<c:choose>
		<c:when test="${ !empty requestScope.member }">  <!-- member가 있으면 -->
			<h2>member</h2>
			<ul>
				<li>이름: ${ member.name }</li>
				<li>전화번호: ${ member.phone }</li>
				<li>아이디: ${ member.id }</li>
				<li>패스워드: ${ member.password }</li>
				<li>주소: ${ address }</li>
			</ul>
		</c:when>
		<c:when test="${ !empty requestScope.memberVO }">  <!-- memberVO가 있으면 -->
			<h2>memberVO</h2>
			<ul>
				<li>이름: ${ memberVO.name }</li>
				<li>전화번호: ${ memberVO.phone }</li>
				<li>아이디: ${ memberVO.id }</li>
				<li>패스워드: ${ memberVO.password }</li>
			</ul>
		</c:when>
		<c:otherwise>
			<h2>model</h2>
			<ul>
				<li>이름: ${ name }</li>
				<li>전화번호: ${ phone }</li>
				<li>아이디: ${ id }</li>
				<li>패스워드: ${ password }</li>
			</ul>
		</c:otherwise>
	</c:choose>
	
	<a href="${ header.referer }">뒤로가기</a>
</body>
</html>