package workshop05;

class Company {
	private double salary;
	private double annualIncome;
	private double afterTaxAnnualIncome;
	private double bonus;
	private double afterTaxBonus;
	
	public Company() {
		
	}

	public Company(double salary) {
		super();
		this.salary = salary;
	}

	public double getIncome() {
		return salary * 12;
	}

	public double getAfterTaxAnnualIncome() {
		return getIncome() * 0.9;
	}

	public double getBonus() {
		return salary * 0.2 * 4;
	}

	public double getAfterTaxBonus() {
		return getBonus() * 0.945;
	}
	
	
}

public class Test02 {

	public static void main(String[] args) {
		int baseSalary = Integer.parseInt(args[0]);
		
		Company company = new Company(baseSalary);
		
		System.out.print("연 기본급 합: " + company.getIncome());
		System.out.println(" 세후: " + company.getAfterTaxAnnualIncome());
		System.out.print("연 보너스 합: " + company.getBonus());
		System.out.println(" 세후: " + company.getAfterTaxBonus());
		System.out.println("연 지급액 합: " + (company.getAfterTaxAnnualIncome() + company.getAfterTaxBonus()));
	}
}
