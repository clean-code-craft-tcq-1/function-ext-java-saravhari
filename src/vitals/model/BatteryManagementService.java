package vitals.model;

public class BatteryManagementService {
	public float temperature;
	public float soc;
	public float chargeRate;

	public BatteryManagementService(float temperature, float soc, float chargeRate) {
		this.temperature = temperature;
		this.soc = soc;
		this.chargeRate = chargeRate;
	}
}
