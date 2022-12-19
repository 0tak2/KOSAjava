package workshop08.ship;

public abstract class Ship {
	private String shipName;
	protected int fuelTank;
	
	Ship() {
		
	}

	public Ship(String shipName, int fuelTank) {
		super();
		this.shipName = shipName;
		this.fuelTank = fuelTank;
	}
	
	public abstract void sail(int dist);
	public abstract void refuel(int fuel);

	public String getShipName() {
		return shipName;
	}

	public int getFuelTank() {
		return fuelTank;
	}
}
