<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.vo.Member" %> <!-- 페이지 디렉티브로 VO 클래스 임포트 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- JSP에서는 클라이언트 스레드에 할당된 세션을 바로 'session'으로 참조할 수 있음. -->
	<!-- 즉, request.getSession()과 같은 구문이 필요 없음. -->
	<h1><%= ((Member)session.getAttribute("member")).getMemberName() %>님 환영합니다.</h1>
</body>
</html>