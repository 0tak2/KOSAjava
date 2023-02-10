<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.vo.Article, board.vo.Comment, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% Article article = (Article)request.getAttribute("article"); %>
	<% List<Comment> commentsList = (List<Comment>)request.getAttribute("comments"); %>
	<table border=1>
		<tr>
			<td>제목</td>
			<td><%= article.getArticleTitle() %></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%= article.getMemberName() %>(<%= article.getArticleAuthor() %>)</td>
		</tr>
		<tr>
			<td>게시일</td>
			<td><%= article.getArticleDate() %></td>
		</tr>
		<tr>
			<td>좋아요</td>
			<td><%= article.getArticleLike() %> <a href="">나도 좋아요!</a></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%= article.getArticleContent() %></td>
		</tr>
	</table>
	<a href="main">삭제</a>
	<a href="main">수정</a><br><br>
	
	<% for(Comment comment : commentsList) { %>
		<%= comment.getCommentAuthor() %>:
		<%= comment.getCommentContent() %>
		<br>
	<% } %>
	
	<br>
	<a href="main">목록으로</a>
</body>
</html>