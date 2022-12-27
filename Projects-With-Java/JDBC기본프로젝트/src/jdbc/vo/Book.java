package jdbc.vo;

// VO는 데이터를 표현하는 객체
// 따라서 비즈니스 로직이 포함되면 안된다.
// 테이블의 컬럼을 참고해서 만들어야 한다.
public class Book {
	// 테이블의 컬럼을 확인하고 필드로 잡는다.
	private String bisbn; // 특별한 이유가 없다면 컬럼명과 필드명은 동일한 편이 좋다.
	private String btitle; // 모든 컬럼을 필드로 잡으면 DO, 필요한 컬럼만 필드로 잡으면 VO
	private String bauthor;
	private int bprice; 
	
	public Book() {
		
	}

	public Book(String bisbn, String btitle, String bauthor, int bprice) {
		super();
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.bprice = bprice;
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

	public String getBauthor() {
		return bauthor;
	}

	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}

	public int getBprice() {
		return bprice;
	}

	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
	
	@Override
	public String toString() {
		return bisbn + "\t" + btitle + "\t" + bauthor + "\t" + bprice;
	}
}
