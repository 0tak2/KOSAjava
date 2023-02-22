package springioc.anno.sample1;

public class Food {

	private String foodName;
	private String foodPrice;
	
	public Food() {
		System.out.println("[Food] 기본 생성자 호출됨");
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
		System.out.println("[Food] setFoodName 세터 호출됨");
	}

	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
		System.out.println("[Food] setFoodPrice 세터 호출됨");
	}
	
	@Override
	public String toString() {
		return foodName + ": " + foodPrice +"원";
	}
}
