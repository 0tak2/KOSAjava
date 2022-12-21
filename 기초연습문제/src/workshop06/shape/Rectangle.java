package workshop06.shape;

public class Rectangle extends Shape implements Resizable {

	public Rectangle() {
		super();
	}

	public Rectangle(int width, int height, String colors) {
		super(width, height, colors);
	}

	@Override
	public void setResize(int size) {
		width += size;
	}

	@Override
	public double getArea() {
		return height * width;
	}

}
