package workshop07.shape;

public class Circle extends Shape implements Movable {
	private int radius;
	public static final double PI = 3.141592;
	
	Circle() {
		
	}

	public Circle(int radius, int x, int y) {
		super(new Point(x, y));
		this.radius = radius;
	}

	@Override
	public void move(int x, int y) {
		int prevX = point.getX();
		int prevY = point.getY();
		point.setXY(prevX + x + 1, prevY + y + 1);
	}

	@Override
	public double getArea() {
		return PI * radius * radius;
	}

	@Override
	public double getCircumference() {
		return 2 * PI * radius;
	}
	
	public String printInfo() {
		return String.format("[%s] 반지름: %d\tXPos: %d\tYPos: %d", 
				toString(), radius, point.getX(), point.getY());
	}
	
}
