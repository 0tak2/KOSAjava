package java2;

import java.util.HashMap;

abstract class Employee {
	String name;
	int number;
	String department;
	int salary;
	
	Employee() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Employee(String name, int number, String department, int salary) {
		super();
		this.name = name;
		this.number = number;
		this.department = department;
		this.salary = salary;
	}
	
	abstract double tax();
}

interface Bounus {
	public void incentive(int pay);
}

class Secretary extends Employee implements Bounus{
	public Secretary() {
		
	}
	
	public Secretary(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}

	@Override
	public void incentive(int pay) {
		setSalary(getSalary() + (int)(pay * 0.8));
	}

	@Override
	double tax() {
		return getSalary() * 0.10;
	}
	
}

class Sales extends Employee implements Bounus{
	public Sales() {
		
	}
	
	public Sales(String name, int number, String department, int salary) {
		super(name, number, department, salary);
	}

	@Override
	public void incentive(int pay) {
		setSalary(getSalary() + (int)(pay * 1.20));
	}

	@Override
	double tax() {
		return getSalary() * 0.13;
	}
	
}

public class Company {

	public static void main(String[] args) {
		HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();
		
		Secretary hilery = new Secretary("Hilery", 1, "secretary", 800);
		Sales clinten = new Sales("Clinten", 2, "sales", 1200);
		
		map.put(hilery.getNumber(), hilery);
		map.put(clinten.getNumber(), clinten);
		
		System.out.println("name\tdepartment\tsalary");
		System.out.println("-------------------------------");
		for(int key : map.keySet()) {
			Employee employee = map.get(key);
			System.out.println(employee.getName() + "\t" +
					employee.getDepartment() + "\t" +
					employee.getSalary());
		}
		System.out.println();
		
		System.out.println("인센티브 100 지급");
		System.out.println("name\tdepartment\tsalary\ttax");
		System.out.println("------------------------------------");
		for(int key : map.keySet()) {
			Employee employee = map.get(key);
			if (employee instanceof Secretary) {
				Secretary secretary = (Secretary) employee;
				secretary.incentive(100);
				map.replace(key, secretary);
			} else if (employee instanceof Sales) {
				Sales sales = (Sales) employee;
				sales.incentive(100);
				map.replace(key, sales);
			}
			System.out.println(employee.getName() + "\t" +
					employee.getDepartment() + "\t" +
					employee.getSalary() + "\t" +
					employee.tax());
		}
		
	}
}
