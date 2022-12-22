package workshop05;

class Student {
	private String name;
	private int age;
	private int height;
	private int weight;
	
	public Student() {
		
	}

	public Student(String name, int age, int height, int weight) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}

public class Test04 {

	public static void main(String[] args) {
		Student[] stArr = new Student[3];
		int ageSum = 0;
		int heightSum = 0;
		int weightSum = 0;
		
		stArr[0] = new Student("홍길동", 15, 171, 81);
		stArr[1] = new Student("한사람", 13, 183, 72);
		stArr[2] = new Student("임걱정", 16, 175, 65);
		
		System.out.println("이름\t나이\t신장\t몸무게");
		for (Student st : stArr) {
			ageSum += st.getAge();
			heightSum += st.getHeight();
			weightSum += st.getWeight();
			
			System.out.println(st.getName() + "\t" +
					st.getAge() + "\t" +
					st.getHeight() + "\t" +
					st.getWeight());
		}
		
		System.out.println();
		System.out.println("나이의 평균: " + String.format("%.2f", (double)ageSum/stArr.length));
		System.out.println("신장의 평균: " + String.format("%.2f", (double)heightSum/stArr.length));
		System.out.println("몸무게의 평균: " + String.format("%.2f", (double)weightSum/stArr.length));
	}
}
