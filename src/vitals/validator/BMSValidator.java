package vitals.validator;

import java.util.function.Function;

import vitals.constant.BMSFactor;
import vitals.internationalization.Internationalization;
import vitals.model.BatteryManagementService;

public class BMSValidator {

	public static boolean batteryIsOk(float temperature, float soc, float chargeRate) {
		BatteryManagementService bms = new BatteryManagementService(temperature, soc, chargeRate);
		if (isValidTemperature(bms))
			return true;
		return false;
	}

	public static Function<BatteryManagementService, Boolean> isValidChargeRate = (BatteryManagementService bms) -> {
		return checkChargeRate(BMSFactor.MAX_CHANGE_RATE, bms.chargeRate, BMSFactor.CHARGE_RATE);
	};

	public static Function<BatteryManagementService, Boolean> isValidStateOfCharge = (BatteryManagementService bms) -> {
		if (checkRange(BMSFactor.MIN_SOC, BMSFactor.MAX_SOC, bms.soc, BMSFactor.SOC)) {
			return isValidChargeRate.apply(bms);
		}
		return false;
	};

	public static Boolean isValidTemperature(BatteryManagementService bms) {
		if (checkRange(BMSFactor.MIN_TEMPERATURE, BMSFactor.MAX_TEMPERATURE, bms.temperature, BMSFactor.TEMPERATURE)) {
			return isValidStateOfCharge.apply(bms);
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
		System.out.println(factorName + " " + Internationalization.getMessage("OUT_OF_RANGE_STATEMENT") + " "
				+ (isHigh ? Internationalization.getMessage("HIGH") : Internationalization.getMessage("LOW")));
	}
}
