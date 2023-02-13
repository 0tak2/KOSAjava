package board.vo;

public class ArticleExtended {
	private int articleNum;
	private String articleTitle;
	private String articleContent;
	private String articleAuthor;
	private String memberName;
	private String articleDate;
	private int articleLike;
	private int articleComments;
	
	public ArticleExtended() {
	}

	public ArticleExtended(int articleNum, String articleTitle, String articleContent, String articleAuthor,
			String memberName, String articleDate, int articleLike, int articleComments) {
		super();
		this.articleNum = articleNum;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleAuthor = articleAuthor;
		this.memberName = memberName;
		this.articleDate = articleDate;
		this.articleLike = articleLike;
		this.articleComments = articleComments;
	}

	public ArticleExtended(String articleTitle, String articleContent, String articleAuthor) {
		super();
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleAuthor = articleAuthor;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getArticleDate() {
		return articleDate;
	}

	public void setArticleDate(String articleDate) {
		this.articleDate = articleDate;
	}

	public int getArticleLike() {
		return articleLike;
	}

	public void setArticleLike(int articleLike) {
		this.articleLike = articleLike;
	}

	public int getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(int articleComments) {
		this.articleComments = articleComments;
	}
}
