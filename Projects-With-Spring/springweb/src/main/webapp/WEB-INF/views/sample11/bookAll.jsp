<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Count</title>
</head>
<body>
	<h1>수행 결과</h1>
	
	<h2>전체 책 목록</h2>
	
	<ol>
		<c:forEach var="book" items="${result}">
	    	<li>
	    		<ul>
	    			<li>
	    				<c:out value="${ book.bookIsbn }"></c:out>
	    			</li>
				    <li>
	    				<c:out value="${ book.bookTitle }"></c:out>
	    			</li>
				    <li>
	    				<c:out value="${ book.bookAuthor }"></c:out>
	    			</li>
				    <li>
	    				<c:out value="${ book.bookPrice }원"></c:out>
	    			</li>
	    		</ul>
	    	</li>
		</c:forEach>
	</ol>
	
	<a href="${ header.referer }">뒤로가기</a>
</body>
</html>