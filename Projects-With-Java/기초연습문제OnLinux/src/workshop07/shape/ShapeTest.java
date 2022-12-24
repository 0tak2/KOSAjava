package workshop07.shape;

public class ShapeTest {
	public static void main(String[] args) {
		Shape[] shArr = new Shape[4];
		
		shArr[0] = new Square(4, 7, 5);
		shArr[1] = new Square(5, 4, 6);
		shArr[2] = new Circle(6, 6, 7);
		shArr[3] = new Circle(7, 8, 3);
		
		for (Shape sh : shArr) {
			if (sh instanceof Square) {
				System.out.println(((Square) sh).printInfo());
			} else if (sh instanceof Circle) {
				System.out.println(((Circle) sh).printInfo());
			}
		}
		
		System.out.println("After Move ...");
		
		for (Shape sh : shArr) {
			if (sh instanceof Square) {
				((Square) sh).move(10, 10);
				System.out.println(((Square) sh).printInfo());
			} else if (sh instanceof Circle) {
				((Circle) sh).move(10, 10);
				System.out.println(((Circle) sh).printInfo());
			}
		}
	}
}
