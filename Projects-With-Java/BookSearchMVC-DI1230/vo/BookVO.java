package lecture.bookstore.vo;

public class BookVO {
	private String bisbn;
	private String btitle;
	private int bprice;
	private String bauthor;
	private String btranslator;
	private String bpublisher;
	private String bdate;
	private String bpage;
	private String bsupplement;
	
	public BookVO() {
	}
	

	public BookVO(String bisbn, String btitle, String bauthor, int bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
	}


	public BookVO(String bisbn, String btitle, int bprice, String bauthor, String btranslator, String bpublisher,
			String bdate, String bpage, String bsupplement) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bprice = bprice;
		this.bauthor = bauthor;
		this.btranslator = btranslator;
		this.bpublisher = bpublisher;
		this.bdate = bdate;
		this.bpage = bpage;
		this.bsupplement = bsupplement;
	}

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public String getBtranslator() {
		return btranslator;
	}

	public void setBtranslator(String btranslator) {
		this.btranslator = btranslator;
	}

	public String getBpublisher() {
		return bpublisher;
	}

	public void setBpublisher(String bpublisher) {
		this.bpublisher = bpublisher;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getBpage() {
		return bpage;
	}

	public void setBpage(String bpage) {
		this.bpage = bpage;
	}

	public String getBsupplement() {
		return bsupplement;
	}

	public void setBsupplement(String bsupplement) {
		this.bsupplement = bsupplement;
	}

}
