package workshop06.shape;

public abstract class Shape {
	protected int width;
	protected int height;
	private String colors;
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getColors() {
		return colors;
	}

	Shape() {
		
	}

	public Shape(int width, int height, String colors) {
		super();
		this.width = width;
		this.height = height;
		this.colors = colors;
	}
	
	public abstract double getArea();
}
