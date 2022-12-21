package workshop08.mobile;

import java.util.HashMap;
import java.util.Map;

public class Test08 {
	public static void main(String[] args) {
		Map<String, Mobile> map = new HashMap<>();
		double sum =0.0;
		
		Mobile m1 = new Mobile("LU6800", "Optimus 2X", 8000000);
		Mobile m2 = new Mobile("SU6600", "Optimus Black", 9000000);
		Mobile m3 = new Mobile("KU5700", "Optimus Big", 7000000);
		Mobile m4 = new Mobile("SU7600", "Optimus March", 9500000);
		
		map.put(m1.getCode(), m1);
		map.put(m2.getCode(), m2);
		map.put(m3.getCode(), m3);
		map.put(m4.getCode(), m4);
		
		for (String key : map.keySet()) {
			Mobile currentObj = map.get(key);
			System.out.println(currentObj.printInfo());
			sum += currentObj.getPrice();
		}
		System.out.printf("합계: %.1f%n", sum);
		sum = 0.0;
		
		for (String key : map.keySet()) {
			Mobile currentObj = map.get(key);
			currentObj.setPrice(currentObj.getPrice() * 1.1);
		}
		
		System.out.println("가격 변경 후");
		for (String key : map.keySet()) {
			Mobile currentObj = map.get(key);
			System.out.println(currentObj.printInfo());
			sum += currentObj.getPrice();
		}
		System.out.printf("합계: %.1f%n", sum);
	}
}
