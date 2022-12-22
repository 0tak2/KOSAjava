package workshop03;

class Student {
	String name;
	int korean;
	int english;
	int math;
	int science;
	
	Student() {
		
	}

	public Student(String name, int korean, int english, int math, int science) {
		super();
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.science = science;
	}
	
	public int getSum() {
		return korean + english + math + science;
	}
	
	public double getAvg() {
		return (double)getSum() / 4;
	}
	
	public String getGrade() {
		double avg = getAvg();
		
		if (avg >= 90 && avg <= 100) {
			return "A";
		} else if (avg >=70 && avg < 90) {
			return "B";
		} else if (avg >=50 && avg < 70) {
			return "C";
		} else if (avg >=30 && avg < 50) {
			return "D";
		} else {
			return "F";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

public class Test06 {

	public static void main(String[] args) {
		Student kim = new Student("Kim", 100, 90, 95, 89);
		Student lee = new Student("Lee", 60, 70, 99, 98);
		Student park = new Student("Park", 68, 86, 60, 40);
		
		System.out.printf("%s 평균: %.2f 학점: %s학점%n", kim.getName(), kim.getAvg(), kim.getGrade());
		System.out.printf("%s 평균: %.2f 학점: %s학점%n", lee.getName(), lee.getAvg(), lee.getGrade());
		System.out.printf("%s 평균: %.2f 학점: %s학점%n", park.getName(), park.getAvg(), park.getGrade());
	}
}
