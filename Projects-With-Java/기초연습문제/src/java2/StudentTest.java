package java2;

class Human {
	protected String name;
	protected int age;
	protected int height;
	protected int weight;
	
	public Human () {
		
	}

	public Human(String name, int age, int height, int weight) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	public void printInformation() {
		System.out.printf("%s\t%d\t%d\t%d\n",
				name, age, height, weight);
	}
}

class Student extends Human {
	String number;
	String major;
	
	public Student() {
		
	}
	
	public Student(String name, int age, int height,
			int weight, String number, String major) {
		super(name, age, height, weight);
		this.number = number;
		this.major = major;
	}

	@Override
	public void printInformation() {
		System.out.printf("%s\t%d\t%d\t%d\t%s\t%s\n",
				name, age, height, weight, number, major);
	}
}

public class StudentTest {

	public static void main(String[] args) {
		Student[] array = new Student[3];
		array[0] = new Student("홍길동", 15, 171, 81,
				"201101", "영문");
		array[1] = new Student("한사람", 13, 183, 72,
				"201102", "건축");
		array[2] = new Student("임걱정", 16, 175, 65,
				"201103", "무영");
		
		for (Student student : array) {
			student.printInformation();
		}
	}
}
