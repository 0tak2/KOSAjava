package workshop05.mobile;

public class Mobile {

	private String mobileName;
	protected int batterySize;
	private String osType;
	
	public Mobile() {
		
	}

	public Mobile(String mobileName, int batterySize, String osType) {
		super();
		this.mobileName = mobileName;
		this.batterySize = batterySize;
		this.osType = osType;
	}
	
	public int operate(int time) {
		batterySize -= time * 10;
		return batterySize;
	}
	
	public int charge(int time) {
		batterySize += time * 10;
		return batterySize;
	}

	public String getMobileName() {
		return mobileName;
	}

	public int getBatterySize() {
		return batterySize;
	}

	public String getOsType() {
		return osType;
	}
}
