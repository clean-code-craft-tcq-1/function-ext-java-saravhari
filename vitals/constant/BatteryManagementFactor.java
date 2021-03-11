package vitals.constant;

import vitals.internationalization.Internationalization;

public class BatteryManagementFactor {
	public final static String KEY_TEMPERATURE = "TEMPERATURE";
	public final static String KEY_SOC = "SOC";
	public final static String KEY_CHARGE_RATE = "CHARGE_RATE";
	public final static String KEY_OUT_OF_RANGE_STATEMENT = "OUT_OF_RANGE_STATEMENT";
	public final static String KEY_HIGH = "HIGH";
	public final static String KEY_LOW = "LOW";
	public final static String KEY_HIGH_WARNING = "HIGH_WARNING";
	public final static String KEY_LOW_WARNING = "LOW_WARNING";
	public final static String KEY_NORMAL = "NORMAL";

	public final static float MIN_TEMPERATURE = 0;
	public final static float MAX_TEMPERATURE = 45;
	public final static float MIN_SOC = 20;
	public final static float MAX_SOC = 80;
	public final static float MAX_CHANGE_RATE = 0.8f;
	public final static float TOLERANCE_PERCENTAGE = 5.0f;
	public final static float MAX_PERCENTAGE = 100.0f;
	
	public final static String TEMPERATURE = Internationalization.getMessage(KEY_TEMPERATURE);
	public final static String SOC = Internationalization.getMessage(KEY_SOC);
	public final static String CHARGE_RATE = Internationalization.getMessage(KEY_CHARGE_RATE);
}
