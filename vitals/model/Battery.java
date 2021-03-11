package vitals.model;

public class Battery {
	public float temperature;
	public float soc;
	public float chargeRate;

	public Battery(float temperature, float soc, float chargeRate) {
		this.temperature = temperature;
		this.soc = soc;
		this.chargeRate = chargeRate;
	}
}
