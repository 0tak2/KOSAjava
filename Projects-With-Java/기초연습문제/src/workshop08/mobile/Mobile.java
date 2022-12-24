package workshop08.mobile;

public class Mobile {
	private String code;
	private String name;
	private double price;
	
	Mobile() {
		
	}

	public Mobile(String code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}

	public String printInfo() {
		return String.format("%s\t%s\t%.1f", code, name, price);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
