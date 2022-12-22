package workshop08.ship;

import java.util.ArrayList;

public class ShipTest {
	public static void main(String[] args) {
		ArrayList<Ship> shArr = new ArrayList<Ship>(2);
		
		shArr.add(new Boat("Boat01", 500));
		shArr.add(new Cruise("Curise01", 1000));
		
		System.out.println("shipName\t\tfuelTank");
		System.out.println("--------------------------------");
		for (Ship ship : shArr) {
			System.out.printf("%s\t\t%s%n", ship.getShipName(), ship.getFuelTank());
		}
		System.out.println();
		
		System.out.println("10 운항");
		System.out.println("shipName\t\tfuelTank");
		System.out.println("--------------------------------");
		for (Ship ship : shArr) {
			if (ship instanceof Boat) {
				((Boat)ship).sail(10);
			} else if (ship instanceof Cruise) {
				((Cruise)ship).sail(10);
			}
			System.out.printf("%s\t\t%s%n", ship.getShipName(), ship.getFuelTank());
		}
		System.out.println();
		
		System.out.println("50 주유");
		System.out.println("shipName\t\tfuelTank");
		System.out.println("--------------------------------");
		for (Ship ship : shArr) {
			if (ship instanceof Boat) {
				((Boat)ship).refuel(50);
			} else if (ship instanceof Cruise) {
				((Cruise)ship).refuel(50);
			}
			System.out.printf("%s\t\t%s%n", ship.getShipName(), ship.getFuelTank());
		}
	}
}
