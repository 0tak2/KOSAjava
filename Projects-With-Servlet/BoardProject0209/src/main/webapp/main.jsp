<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.vo.Member, board.vo.Article, java.util.List " %> <!-- 페이지 디렉티브로 임포트 -->
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
	<a href="logout">로그아웃</a>
	
	<h3>게시글 목록</h3>
	<a href="writeArticle.html">글쓰기</a>
	<table border=1>
		<thead>
			<tr>
				<th>글 번호</th>
				<th>글 제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>댓글 수</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
			<%
				List<Article> list = (List<Article>)request.getAttribute("boardList"); // 세션과 마찬가지로 request 바로 참조
				for(Article article : list) { %>
			<tr>
				<td><%= article.getArticleNum() %></td>
				<td><a href="viewArticle?articleId=<%= article.getArticleNum() %>"><%= article.getArticleTitle() %></a></td>
				<td><%= article.getMemberName() %></td>
				<td><%= article.getArticleDate() %></td>
				<td>0</td>
				<td><%= article.getArticleLike() %></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	
</body>
</html>