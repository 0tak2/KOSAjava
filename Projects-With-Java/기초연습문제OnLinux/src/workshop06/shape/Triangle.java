package workshop06.shape;

public class Triangle extends Shape implements Resizable {

	public Triangle() {
		super();
	}

	public Triangle(int width, int height, String colors) {
		super(width, height, colors);
	}

	@Override
	public void setResize(int size) {
		height += size;
	}

	@Override
	public double getArea() {
		return 0.5 * width * height;
	}

}
