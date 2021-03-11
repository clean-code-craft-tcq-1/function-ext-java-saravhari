package vitals.constant;

import vitals.internationalization.Internationalization;

public class BMSFactor {
	public final static float MIN_TEMPERATURE = 0;
	public final static float MAX_TEMPERATURE = 45;
	public final static float MIN_SOC = 20;
	public final static float MAX_SOC = 80;
	public final static float MAX_CHANGE_RATE = 0.8f;
	public final static String TEMPERATURE = Internationalization.getMessage("TEMPERATURE");
	
	public final static String SOC = Internationalization.getMessage("SOC");
	public final static String CHARGE_RATE = Internationalization.getMessage("CHARGE_RATE");
}
