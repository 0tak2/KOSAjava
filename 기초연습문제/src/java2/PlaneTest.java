package java2;

abstract class Plane {
	protected String planeName;
	protected int fuelSize;

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public int getFuelSize() {
		return fuelSize;
	}

	public void setFuelSize(int fuelSize) {
		this.fuelSize = fuelSize;
	}
	
	Plane () {
		
	}

	public Plane(String planeName, int fuelSize) {
		super();
		this.planeName = planeName;
		this.fuelSize = fuelSize;
	}
	
	void refuel(int fuel) {
		setFuelSize(getFuelSize() + fuel);
	}
	
	abstract void flight(int distance);
}

class Airplane extends Plane {
	
	Airplane() {
		
	}
	
	Airplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	@Override
	void flight(int distance) {
		setFuelSize(getFuelSize() - distance * 3);
	}
	
}

class Cargoplane extends Plane {
	
	Cargoplane() {
		
	}
	
	Cargoplane(String planeName, int fuelSize) {
		super(planeName, fuelSize);
	}

	@Override
	void flight(int distance) {
		setFuelSize(getFuelSize() - distance * 5);
	}
	
}

public class PlaneTest {
	public static void main(String[] args) {
		Airplane airplane = new Airplane("L747", 1000);
		Cargoplane cargoplane = new Cargoplane("C40", 1000);
		
		System.out.println("Plane \t fuelSize");
		System.out.println("---------------------------");
		System.out.println(airplane.getPlaneName()
				+ "\t" + airplane.getFuelSize());
		System.out.println(cargoplane.getPlaneName()
				+ "\t" + cargoplane.getFuelSize());
		System.out.println();
		
		airplane.flight(100);
		cargoplane.flight(100);
		System.out.println("100 운항");
		System.out.println("Plane \t fuelSize");
		System.out.println("---------------------------");
		System.out.println(airplane.getPlaneName()
				+ "\t" + airplane.getFuelSize());
		System.out.println(cargoplane.getPlaneName()
				+ "\t" + cargoplane.getFuelSize());
		System.out.println();
		
		airplane.refuel(200);
		cargoplane.refuel(200);
		System.out.println("200 주유");
		System.out.println("Plane \t fuelSize");
		System.out.println("---------------------------");
		System.out.println(airplane.getPlaneName()
				+ "\t" + airplane.getFuelSize());
		System.out.println(cargoplane.getPlaneName()
				+ "\t" + cargoplane.getFuelSize());
		System.out.println();
	}
}
