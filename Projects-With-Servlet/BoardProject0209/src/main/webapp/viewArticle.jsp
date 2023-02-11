<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.vo.Article, board.vo.Comment, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="
https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js
"></script>

<script>
let isEditing = -1;

function deleteComment(commentNum) {
	console.log("DELETE COMMENT:", commentNum);
	$.ajax({
	    url : "comment",
	    type : "DELETE",
	    data : {
	    	
	    },
	    success: function(result) {
	        console.log( result );
	    }
	});

}

function updateComment(commentNum) {
	
}

function cancleEdit(commentNum) {
	if (isEditing === -1) {
		return;
	}
	
	const tdCell = $('table[data-num=' + commentNum + ']').find('td.comment-content-cell');
	tdCell.find('input').remove();
	tdCell.find('span.comment-content').show();
	isEditing = -1;
}

function showUpdateControl(commentNum) {
	console.log("EDIT COMMENT:", commentNum);
	if (isEditing !== -1) {
		cancleEdit(isEditing);
	}
	
	const tdCell = $('table[data-num=' + commentNum + ']').find('td.comment-content-cell');
	$.ajax({
	    url : "ajax/comment",
	    type : "GET",
	    data : {
	    	commentNum: commentNum
	    },
	    success: function(result) {
	    	console.log(result)
	    	tdCell.children('span.comment-content').hide();		
	    	tdCell.append('<input type="text"/>');
	    	tdCell.append('<input type="button" value="완료" onclick="updateComment(' + commentNum + ')" />');
	    	tdCell.append('<input type="button" value="취소" onclick="cancleEdit(' + commentNum + ')" />');
	    	isEditing = commentNum;
	    },
	    error: function(error) {
	    	console.error(error)
	    	alert('자신이 작성한 댓글만 수정할 수 있습니다.');
	    }
	});
	

	/*
	$.ajax({
	    url : "comment",
	    type : "PUT",
	    data : {
	    	commentNum: commentNum
	    },
	    success: function(result) {
	        console.log( result );
	    }
	});
	*/
}
</script>
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
	
	<form action="deleteArticle" method="post">
		<input type="hidden" name="articleNum" value="<%= article.getArticleNum() %>">
		<button type="submit">삭제</button>
	</form>
	<form action="editArticle" method="get">
		<input type="hidden" name="articleNum" value="<%= article.getArticleNum() %>">
		<button type="submit">수정</button>
	</form><br><br>
	
	<h3>댓글</h3>
	<% for(Comment comment : commentsList) { %>
		<table data-num="<%= comment.getCommentNum() %>">
			<tr>
				<td>작성시각</td>
				<td><span><%= comment.getCommentDate() %></span></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><span><%= comment.getMemberName() %>(<%= comment.getCommentAuthor() %>)</span></td>
			</tr>
			<tr>
				<td>내용</td>
				<td class="comment-content-cell"><span class="comment-content"><%= comment.getCommentContent() %></span></td>
			</tr>
		</table>
		<button onClick="showUpdateControl(<%= comment.getCommentNum() %>)">수정</button>
		<button onClick="deleteComment(<%= comment.getCommentNum() %>)">삭제</button>
	<% } %>
	<form action="writeComment" method="POST">
		<input type="hidden" name="articleNum" value="<%= article.getArticleNum() %>">
		<table>
			<tr>
				<td><input type="text" name="commentContent"></td>
				<td><button>등록</button></td>
			</tr>
		</table>
	</form>
	
	<br>
	<a href="main">목록으로</a>
</body>
</html>