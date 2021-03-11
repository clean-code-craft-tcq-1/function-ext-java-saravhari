package vitals.validator;

import java.util.function.Function;

import vitals.constant.BatteryManagementFactor;
import vitals.internationalization.Internationalization;
import vitals.model.Battery;

public class BatteryManagementValidator {

	public static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
		Battery battery = new Battery(temperature, soc, chargeRate);
		if (isValidTemperature(battery))
			return true;
		return false;
	}

	public static Function<Battery, Boolean> isValidChargeRate = (Battery battery) -> {
		return checkChargeRate(BatteryManagementFactor.MAX_CHANGE_RATE, battery.chargeRate, BatteryManagementFactor.CHARGE_RATE);
	};

	public static Function<Battery, Boolean> isValidStateOfCharge = (Battery battery) -> {
		if (checkRange(BatteryManagementFactor.MIN_SOC, BatteryManagementFactor.MAX_SOC, battery.soc, BatteryManagementFactor.SOC)) {
			return isValidChargeRate.apply(battery);
		}
		return false;
	};

	public static Boolean isValidTemperature(Battery battery) {
		if (checkRange(BatteryManagementFactor.MIN_TEMPERATURE, BatteryManagementFactor.MAX_TEMPERATURE, battery.temperature, BatteryManagementFactor.TEMPERATURE)) {
			return isValidStateOfCharge.apply(battery);
		}
		return false;
	};

	public static Boolean checkRange(float minVal, float maxVal, float value, String factorName) {
		if (value < minVal || value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		return true;
	}

	static Boolean checkChargeRate(float maxVal, float value, String factorName) {
		if (value > maxVal) {
			printStatus(factorName, (value > maxVal));
			return false;
		}
		return true;
	}

	static void printStatus(String factorName, boolean isHigh) {
		System.out.println(factorName + " " + Internationalization.getMessage(BatteryManagementFactor.KEY_OUT_OF_RANGE_STATEMENT)
				+ " " + (isHigh ? Internationalization.getMessage(BatteryManagementFactor.KEY_HIGH)
						: Internationalization.getMessage(BatteryManagementFactor.KEY_LOW)));
	}
}
