package workshop07.shape;

public class Square extends Shape implements Movable {
	private int width;
	
	Square() {
		
	}

	public Square(int width, int x, int y) {
		super(new Point(x, y));
		this.width = width;
	}

	@Override
	public void move(int x, int y) {
		int prevX = point.getX();
		int prevY = point.getY();
		point.setXY(prevX + x + 2, prevY + y + 2);
	}

	@Override
	public double getArea() {
		return width * width;
	}

	@Override
	public double getCircumference() {
		return 4 * width;
	}
	
	public String printInfo() {
		return String.format("[%s] 너비: %d\tXPos: %d\tYPos: %d", 
				toString(), width, point.getX(), point.getY());
	}
	
}
