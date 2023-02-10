package board.vo;

public class Comment {
	private int commentNum;
	private String commentContent;
	private String commentAuthor;
	private String memberName;
	private String commentDate;
	private int commentArticle;
	
	public Comment() {
	}

	public Comment(int commentNum, String commentContent, String commentAuthor, String memberName,
			String commentDate, int commentArticle) {
		super();
		this.commentNum = commentNum;
		this.commentContent = commentContent;
		this.commentAuthor = commentAuthor;
		this.memberName = memberName;
		this.commentDate = commentDate;
		this.commentArticle = commentArticle;
	}

	public Comment(String commentContent, String commentAuthor, int commentArticle) {
		super();
		this.commentContent = commentContent;
		this.commentAuthor = commentAuthor;
		this.commentArticle = commentArticle;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
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

	public int getCommentArticle() {
		return commentArticle;
	}

	public void setCommentArticle(int commentArticle) {
		this.commentArticle = commentArticle;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
