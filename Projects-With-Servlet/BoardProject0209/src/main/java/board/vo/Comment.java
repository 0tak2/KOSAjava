package board.vo;

public class Comment {
	private String commentNum;
	private String commentContent;
	private String commentAuthor;
	private String commentDate;
	private String commentArticle;
	
	public Comment() {
	}

	public Comment(String commentNum, String commentContent, String commentAuthor, String commentDate,
			String commentArticle) {
		super();
		this.commentNum = commentNum;
		this.commentContent = commentContent;
		this.commentAuthor = commentAuthor;
		this.commentDate = commentDate;
		this.commentArticle = commentArticle;
	}

	public Comment(String commentContent, String commentAuthor, String commentArticle) {
		super();
		this.commentContent = commentContent;
		this.commentAuthor = commentAuthor;
		this.commentArticle = commentArticle;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentArticle() {
		return commentArticle;
	}

	public void setCommentArticle(String commentArticle) {
		this.commentArticle = commentArticle;
	}
}
