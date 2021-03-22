package vitals.validator;

import java.util.Arrays;
import java.util.function.Function;

import vitals.constant.BatteryManagementFactor;
import vitals.internationalization.Internationalization;
import vitals.report.BatteryReport;

public class BatteryManagementValidator {

	private static boolean isValid = true;

	public static boolean batteryIsOk(float temperature, float soc, float chargeRate) {

		Factor factors[] = { new Factor(BatteryManagementFactor.KEY_SOC, soc),
				new Factor(BatteryManagementFactor.KEY_TEMPERATURE, temperature),
				new Factor(BatteryManagementFactor.KEY_CHARGE_RATE, chargeRate) };

		Arrays.asList(factors).parallelStream().forEach(factor -> {
			isValidTemperature(factor);
		});

		return isValid;
	}

	public static Function<Factor, Void> isValidChargeRate = (Factor factor) -> {
		if (factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_CHARGE_RATE))
			checkChargeRate(BatteryManagementFactor.MAX_CHANGE_RATE, factor.value, BatteryManagementFactor.CHARGE_RATE);
		return null;
	};

	public static Function<Factor, Void> isValidStateOfCharge = (Factor factor) -> {
		if (factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_SOC)) {
			checkRange(BatteryManagementFactor.MIN_SOC, BatteryManagementFactor.MAX_SOC, factor.value,
					BatteryManagementFactor.SOC);
			return null;
		}
		return isValidChargeRate.apply(factor);
	};

	public static Void isValidTemperature(Factor factor) {
		if (factor.name.equalsIgnoreCase(BatteryManagementFactor.KEY_TEMPERATURE)) {
			checkRange(BatteryManagementFactor.MIN_TEMPERATURE, BatteryManagementFactor.MAX_TEMPERATURE, factor.value,
					BatteryManagementFactor.TEMPERATURE);
			return null;
		}
		return isValidStateOfCharge.apply(factor);
	};

	public static Boolean checkRange(float minVal, float maxVal, float value, String factorName) {
		if (value < minVal || value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		BatteryReport.printLog(checkEarlyWarning(factorName, maxVal, minVal, value));
		return true;
	}

	static Boolean checkChargeRate(float maxVal, float value, String factorName) {
		if (value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		BatteryReport.printLog(checkEarlyWarning(factorName, maxVal, 0, value));
		return true;
	}

	static void printStatus(String factorName, boolean isHigh) {
		isValid = false;
		BatteryReport.printLog(
				factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_OUT_OF_RANGE_STATEMENT)
						+ " " + (isHigh ? Internationalization.getMessage(BatteryManagementFactor.KEY_HIGH)
								: Internationalization.getMessage(BatteryManagementFactor.KEY_LOW)));
	}

	static String checkEarlyWarning(String factorName, float maxVal, float minVal, float value) {
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
