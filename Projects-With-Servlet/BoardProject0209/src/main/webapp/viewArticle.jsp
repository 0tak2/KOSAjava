<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.vo.ArticleExtended,board.vo.Comment,java.util.List" %>

<%
ArticleExtended article = (ArticleExtended)request.getAttribute("article");
%>
<% List<Comment> commentsList = (List<Comment>)request.getAttribute("comments"); %>
<% boolean didLike = (Boolean)request.getAttribute("didLike"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기 - <%= article.getArticleTitle() %></title>

<script src="
https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js
"></script>

<script>
let isEditing = -1;

function deleteComment(commentNum) {
	console.log("DELETE COMMENT:", commentNum);
	
	const commentWrapper = $(event.target).parent();
	
	$.ajax({
	    url : "ajax/deleteComment",
	    type : "POST",
	    data : {
	    	commentNum: commentNum,
	    },
	    success: function(result) {
	    	commentWrapper.remove();
	    },
	    error: function(error) {
	    	console.error(error)
	    	if (error.status === 400) {
	    		alert('자신이 작성한 댓글만 삭제할 수 있습니다.');
	    	} else {
	    		alert('댓글 삭제에 실패했습니다. 오류가 반복되면 관리자에게 문의하십시오.');
	    	}
	    }
	});

}

function updateComment(commentNum) {
	console.log("EDIT COMMENT:", commentNum);
	
	const newCommentContent = 
		$(event.target).parent().children('input[type=text]').val();
	
	console.log(commentNum);
	console.log(newCommentContent);
	
	$.ajax({
	    url : "ajax/editComment",
	    type : "POST",
	    data : {
	    	commentNum: commentNum,
	    	commentContent: newCommentContent
	    },
	    success: function(result) {
	    	cancelEdit(commentNum);
	    	const tdCell = $('table[data-num=' + commentNum + ']').find('td.comment-content-cell');
	    	tdCell.find('span.comment-content').text(result);
	    },
	    error: function(error) {
	    	console.error(error)
	    	if (error.status === 400) {
	    		alert('자신이 작성한 댓글만 수정할 수 있습니다.');
	    	} else {
	    		alert('댓글 수정에 실패했습니다. 오류가 반복되면 관리자에게 문의하십시오.');
	    	}
	    }
	});
}

function cancelEdit(commentNum) {
	if (isEditing === -1) {
		return;
	}
	
	const tdCell = $('table[data-num=' + commentNum + ']').find('td.comment-content-cell');
	tdCell.find('input').remove();
	tdCell.find('span.comment-content').show();
	isEditing = -1;
}

function showUpdateControl(commentNum) {
	console.log('SHOW UPDATE CONTROL:', commentNum);
	
	if (isEditing !== -1) {
		cancelEdit(isEditing);
	}
	
	const tdCell = $('table[data-num=' + commentNum + ']').find('td.comment-content-cell');
	$.ajax({
	    url : "ajax/editComment",
	    type : "GET",
	    data : {
	    	commentNum: commentNum
	    },
	    success: function(result) {
	    	console.log(result)
	    	tdCell.children('span.comment-content').hide();
	    	
	    	const inputText = $('<input type="text" />');
	    	inputText.val(result);
	    	
	    	tdCell.append(inputText);
	    	tdCell.append('<input type="button" value="완료" onclick="updateComment(' + commentNum + ')" />');
	    	tdCell.append('<input type="button" value="취소" onclick="cancelEdit(' + commentNum + ')" />');
	    	isEditing = commentNum;
	    },
	    error: function(error) {
	    	if (error.status === 400) {
	    		alert('자신이 작성한 댓글만 수정할 수 있습니다.');
	    	} else {
	    		alert('댓글 작성자 확인에 실패했습니다. 오류가 반복되면 관리자에게 문의하십시오.');
	    	}
	    }
	});
	
}

function likeArticle(articleNum) {
	console.log('LIKE:', articleNum);
	
	const likeCountWrapper = $('span.like-count-wrapper');
	const likeCount = Number(likeCountWrapper.text());
	const likeWrapper = $('span.like-control-wrapper');
	const cancelBtn = $('<button>좋아요 취소</button>').click(() => unlikeArticle(articleNum));
	
	$.ajax({
	    url : "ajax/setLike",
	    type : "POST",
	    data : {
	    	articleNum
	    },
	    success: function(result) {
	    	console.log(result)
	    	likeWrapper.empty();
	    	likeWrapper.append(cancelBtn);
	    	likeCountWrapper.text(likeCount + 1);
	    },
	    error: function(error) {
	    	alert('좋아요에 실패했습니다.')
	    }
	});
}

function unlikeArticle(articleNum) {
	console.log('UNLIKE:', articleNum);
	const likeCountWrapper = $('span.like-count-wrapper');
	const likeCount = Number(likeCountWrapper.text());
	const likeWrapper = $('span.like-control-wrapper');
	const likeBtn = $('<button>👍</button>').click(() => likeArticle(articleNum));
	
	$.ajax({
	    url : "ajax/unLike",
	    type : "POST",
	    data : {
	    	articleNum
	    },
	    success: function(result) {
	    	console.log(result)
	    	likeWrapper.empty();
	    	likeWrapper.append(likeBtn);
	    	likeCountWrapper.text(likeCount - 1);
	    },
	    error: function(error) {
	    	alert('좋아요 취소에 실패했습니다.')
	    }
	});
}
</script>
</head>
<body>
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
			<td>
				<span class="like-count-wrapper">
					<%= article.getArticleLike() %>				
				</span>
				<span class="like-control-wrapper">
					<% if(didLike) { %>
						<button onClick="unlikeArticle(<%= article.getArticleNum() %>)">좋아요 취소</button>
					<% } else { %>
						<button onClick="likeArticle(<%= article.getArticleNum() %>)">👍</button>
					<% } %>
				</span>
			</td>
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
		<div class="comment-content-wrapper">
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
		</div>
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