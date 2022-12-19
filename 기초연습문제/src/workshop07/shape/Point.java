package workshop07.shape;

public class Point {
	int x;
	int y;
	
	Point() {
		
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int[] getXY() {
		int[] arr = new int[2];
		arr[0] = x;
		arr[1] = y;
		return arr;
	}
}
