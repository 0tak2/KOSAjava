package workshop08.ship;

public class Boat extends Ship {

	Boat() {
		
	}

	public Boat(String shipName, int fuelTank) {
		super(shipName, fuelTank);
	}


	@Override
	public void sail(int dist) {
		this.fuelTank -= dist * 10;
	}

	@Override
	public void refuel(int fuel) {
		this.fuelTank += fuel * 10;
	}
	
	
}
