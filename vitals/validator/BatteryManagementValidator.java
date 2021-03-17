package vitals.validator;

import java.util.Arrays;

import vitals.constant.BatteryManagementFactor;
import vitals.internationalization.Internationalization;

public class BatteryManagementValidator {

	private static boolean isValid = true;

	public static boolean batteryIsOk(float temperature, float soc, float chargeRate) {

		Factor factors[] = { new Factor(BatteryManagementFactor.KEY_SOC, soc),
				new Factor(BatteryManagementFactor.KEY_TEMPERATURE, temperature),
				new Factor(BatteryManagementFactor.KEY_CHARGE_RATE, chargeRate) };

		Arrays.asList(factors).parallelStream()
				.forEach(factor -> isValid = (factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_TEMPERATURE)
						&& isValidTemperature(factor.value)
						|| factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_SOC)
								&& isValidStateOfCharge(factor.value)
						|| factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_CHARGE_RATE)
								&& isValidChargeRate(factor.value)));
		return isValid;

	}

	public static Boolean isValidChargeRate(Float chargeRate) {
		return checkChargeRate(BatteryManagementFactor.MAX_CHANGE_RATE, chargeRate,
				BatteryManagementFactor.CHARGE_RATE);
	};

	public static Boolean isValidStateOfCharge(Float soc) {
		return checkRange(BatteryManagementFactor.MIN_SOC, BatteryManagementFactor.MAX_SOC, soc,
				BatteryManagementFactor.SOC);
	}

	public static Boolean isValidTemperature(Float temperature) {
		return checkRange(BatteryManagementFactor.MIN_TEMPERATURE, BatteryManagementFactor.MAX_TEMPERATURE, temperature,
				BatteryManagementFactor.TEMPERATURE);
	};

	public static Boolean checkRange(float minVal, float maxVal, float value, String factorName) {
		if (value < minVal || value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		System.out.println(printEarlyWarning(factorName, maxVal, minVal, value));
		return true;
	}

	static Boolean checkChargeRate(float maxVal, float value, String factorName) {
		if (value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		System.out.println(printEarlyWarning(factorName, maxVal, 0, value));
		return true;
	}

	static void printStatus(String factorName, boolean isHigh) {
		System.out.println(
				factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_OUT_OF_RANGE_STATEMENT)
						+ " " + (isHigh ? Internationalization.getMessage(BatteryManagementFactor.KEY_HIGH)
								: Internationalization.getMessage(BatteryManagementFactor.KEY_LOW)));
	}

	static String printEarlyWarning(String factorName, float maxVal, float minVal, float value) {
		float warningVal = (float) ((maxVal * BatteryManagementFactor.TOLERANCE_PERCENTAGE)
				/ BatteryManagementFactor.MAX_PERCENTAGE);

		if (value <= lowTolerance(minVal, warningVal)) {
			return (factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_LOW_WARNING));
		}

		if (value >= highTolerance(maxVal, warningVal)) {
			return (factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_HIGH_WARNING));
		}

		return (factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_NORMAL));
	}

	static float highTolerance(float maxVal, float warningVal) {
		return (maxVal - warningVal);
	}

	static float lowTolerance(float minVal, float warningVal) {
		return (minVal + warningVal);
	}

	private static class Factor {
		private String name;
		private Float value;

		public Factor(String name, Float value) {
			super();
			this.name = name;
			this.value = value;
		}
	}
}
