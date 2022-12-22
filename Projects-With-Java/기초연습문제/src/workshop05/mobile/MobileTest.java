package workshop05.mobile;

public class MobileTest {

	public static void main(String[] args) {
		Ltab ltab = new Ltab("Ltab", 500, "AP-01");
		Otab otab = new Otab("Otab", 1000, "AND-20");
		System.out.println("Mobile\tBattery\tOS");
		System.out.println("------------------------------------");
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
		
		
		ltab.charge(10);
		otab.charge(10);
		System.out.println("------------------------------------");
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
		
		ltab.operate(5);
		otab.operate(5);
		System.out.println("------------------------------------");
		System.out.println(ltab.getMobileName() + "\t" + ltab.getBatterySize() + "\t" + ltab.getOsType());
		System.out.println(otab.getMobileName() + "\t" + otab.getBatterySize() + "\t" + otab.getOsType());
		
	}
}
