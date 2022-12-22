package workshop08.ship;

public class Cruise extends Ship {

	Cruise() {
		
	}

	public Cruise(String shipName, int fuelTank) {
		super(shipName, fuelTank);
	}


	@Override
	public void sail(int dist) {
		this.fuelTank -= dist * 13;
	}

	@Override
	public void refuel(int fuel) {
		this.fuelTank += fuel * 8;
	}
	
	
}
