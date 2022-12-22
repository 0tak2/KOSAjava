package workshop05.mobile;

public class Otab extends Mobile{

	public Otab() {
		
	}

	public Otab(String mobileName, int batterySize, String osType) {
		super(mobileName, batterySize, osType);
	}
	
	@Override
	public int operate(int time) {
		batterySize -= time * 12;
		return batterySize;
	}
	
	@Override
	public int charge(int time) {
		batterySize += time * 8;
		return batterySize;
	}
}
