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
	<h1>testController08.jsp</h1>
	
	EL을 이용해 데이터를 추출
	
	<ol>
		<li>일반 값
			<ul>
				<li>문자열 출력 1: ${ "test" }</li> <!-- test -->
				<li>문자열 출력 2: ${ 'test' }</li> <!-- test -->
				<li>수 출력: ${ 3.141592 }</li> <!-- 3.141592 -->
				<li>논리값 출력: ${ true }</li> <!-- true -->
				<li>널 출력: ${ null }</li> <!-- (아무 것도 출력 안됨) -->
			</ul>
		</li>
		<li>model 객체의 값
			<ul>
				<li>리스트의 특정 값 출력: ${ myList[1] }</li>
				<li>Java Bean (VO): ${ myUser.userName }</li>
				<li>일반 맵: ${ myName }</li>
			</ul>
		</li>
		<li>param 내장 객체의 값
			<ul>
				<li>이름: ${ param.userName }</li>
				<li>나이: ${ param.userAge }</li>
			</ul>
		</li>
		<li>header 내장 객체의 값
			<ul>
				<li>referer: ${ header.referer }</li>
				<li><a href=${ header.referer }>뒤로가기</a></a></li>
			</ul>
		</li>
		<li>산술연산
			<ul>
				<li>userAge + 20: ${ param.userAge + 20 }</li>
			</ul>
		</li>
		<li>논리연산
			<ul>
				<li>!false: ${ !false }</li>
			</ul>
		</li>
		<li>비교연산
		    <ul>
		        <li>유저의 나이가 20세 미만인가?: ${ param.userAge < 20 }</li>
		    </ul>
		</li>
		<li>삼항연산자
		    <ul>
		        <li>유저가 미성년자인가: ${ param.userAge < 20 ? "미성년자" : "성인" }</li>
		    </ul>
		</li>
		<li>empty 연산자
		    <ul>
		        <li>"": ${ empty "" }</li>
   		        <li>"abcd": ${ empty "abcd" }</li>
   		        <li>null: ${ empty null }</li>
   		        <li>myList: ${ empty myList }</li>
   		        <li>{}: ${ empty {} }</li>
		    </ul>
		</li>
	</ol>
</body>
</html>