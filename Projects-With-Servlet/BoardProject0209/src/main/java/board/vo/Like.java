package board.vo;

public class Like {
	private int likeNum;
	private String likeMemberId;
	private int likeArticle;
	
	public Like() {
	}

	public Like(int likeNum, String likeMemberId, int likeArticle) {
		super();
		this.likeNum = likeNum;
		this.likeMemberId = likeMemberId;
		this.likeArticle = likeArticle;
	}

	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}

	public String getLikeMemberId() {
		return likeMemberId;
	}

	public void setLikeMemberId(String likeMemberId) {
		this.likeMemberId = likeMemberId;
	}

	public int getLikeArticle() {
		return likeArticle;
	}

	public void setLikeArticle(int likeArticle) {
		this.likeArticle = likeArticle;
	}
}
