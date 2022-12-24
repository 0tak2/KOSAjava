package workshop07.shape;

public abstract class Shape {
	Point point;
	
	Shape() {
		
	}

	public Shape(Point point) {
		super();
		this.point = point;
	}
	
	public abstract double getArea();
	
	public abstract double getCircumference();
}
