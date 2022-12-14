package java1;

class Tv {
	String name;
	int price;
	String description;
	
	Tv() {
	}

	public Tv(String name, int price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public String toString() {
		return name + price + description;
	}
}

public class TvTest {
	public static void main(String[] args) {
		Tv tvArray[] = new Tv[3];
		tvArray[0] = new Tv("INFINIA", 15000000, "LED TV");
		tvArray[1] = new Tv("XCANVAS", 10000000, "LCD TV");
		tvArray[2] = new Tv("CINEMA", 20000000, "3D TV");
		
		int priceSum = 0;
		
		for(int i=0; i<3; ++i) {
			tvArray[i].toString();
			priceSum += tvArray[i].price;
		}
		System.out.println("가격의 합: " + priceSum);
	}
}
