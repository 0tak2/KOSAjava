<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.vo.ArticleExtended" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<%
	 ArticleExtended article = (ArticleExtended)request.getAttribute("article");
	%>
	<form action="editArticle" method="post">
		<input type="hidden" name="articleNum" value="<%= article.getArticleNum() %>">
		<table border=1>
			<tr>
				<td>제목</td>
				<td><input type="text" name="articleTitle" value="<%= article.getArticleTitle() %>"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="articleContent"><%= article.getArticleContent() %></textarea></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>