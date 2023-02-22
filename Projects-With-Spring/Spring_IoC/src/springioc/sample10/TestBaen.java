package springioc.sample10;

public class TestBaen {

	private DataBean data1;
	private DataBean data2;
	
	public TestBaen() {
		System.out.println("[TestBean] 기본 생성자 호출");
	}

	public TestBaen(DataBean data1, DataBean data2) {
		System.out.println("[TestBean] 생성자 호출: " + data1 + ", " + data2);
		this.data1 = data1;
		this.data2 = data2;
	}

	public DataBean getData1() {
		return data1;
	}

	public void setData1(DataBean data1) {
		this.data1 = data1;
		System.out.println("[TestBean] setData1 메서드 호출");
	}

	public DataBean getData2() {
		return data2;
	}

	public void setData2(DataBean data2) {
		this.data2 = data2;
		System.out.println("[TestBean] setData2 메서드 호출");
	}
}
