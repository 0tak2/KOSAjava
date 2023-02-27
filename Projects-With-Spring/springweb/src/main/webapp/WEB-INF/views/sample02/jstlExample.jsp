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
	<!-- 변수 만들고 값 할당 -->
	<%-- <% int test = 100; %> --%>
	<c:set var="num1" value="100" />
	
	<!-- if문 -->
	<!-- test에는 결과적으로 true 혹은 false가 들어가도록 -->
	<!-- EL을 넣으면 됨 -->
	<c:if test="${ num1 + myNum > 100 }">
		연산 결과가 true입니다.
	</c:if>
	<!-- else 문이 없다는 단점이 있다. -->
	
	<!-- choose문 -->
	<!-- 조건이 여러개인 경우 사용 -->
	<c:choose>
		<c:when test="${ num1 + myNum > 50 }">
			50 초과 <!-- 만족하면 다음 조건은 평가하지 않는다. switch가 아니라 if-else처럼 동작 -->
		</c:when>
		<c:when test="${ num1 + myNum > 100 }">
			100 초과
		</c:when>
		<c:when test="${ num1 + myNum > 300 }">
			300 초과
		</c:when>
		<c:otherwise>
			만족하는 조건이 없음
		</c:otherwise>
	</c:choose>
	
	<br><br>
	
	<!-- for문 (while문은 없음) -->
	<!-- -- 기본 forEach문 (반복 횟수 지정) -->
	<ul>
	<c:forEach var="tmp" begin="1" end="5" step="2"> <!-- 1, 3, 5 -->
		<li>${ tmp }</li>
	</c:forEach>
	</ul>
	
	<!-- -- 집합 자료 구조 (리스트, 셋, 맵 등)를 반복 -->
	<ol>
	<c:forEach var="name" items="${ myList }">
		<li>${ name }</li>
	</c:forEach>
	</ol>
	
	<!-- ------------------------------------ -->
	<!-- fmt 사용법 -->
	<c:set var="number" value="123456789" />
	<c:set var="number2" value="0.783" />
	<br><br>
	
	<!-- 숫자 표현시 콤마 -->
	<fmt:formatNumber value="${ number }" />
	
	<!-- 통화 표현 -->
	<fmt:formatNumber value="${ number }" type="currency" />
	
	<!-- 백분율 표현 -->
	<fmt:formatNumber value="${ number2 }" type="percent" pattern="0.00%" />
</body>
</html>