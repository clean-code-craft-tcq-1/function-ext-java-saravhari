package vitals;

import vitals.validator.BatteryManagementValidator;

public class Main {

	public static void main(String[] args) {
		assert (BatteryManagementValidator.batteryIsOk(25, 70, 0.7f) == true);
		assert (BatteryManagementValidator.batteryIsOk(25, 15, 0.0f) == false);
		assert (BatteryManagementValidator.batteryIsOk(25, 85, 0.0f) == false);
		assert (BatteryManagementValidator.batteryIsOk(25, 70, 0.9f) == false);
		assert (BatteryManagementValidator.batteryIsOk(50, 21, 0.0f) == false);
		assert (BatteryManagementValidator.batteryIsOk(-50, 21, 0.0f) == false);
		assert (BatteryManagementValidator.batteryIsOk(40, 79, 11.0f) == false);
		assert (BatteryManagementValidator.batteryIsOk(-50, 79, 0.1f) == false);
		assert (BatteryManagementValidator.batteryIsOk(50, 79, 0.1f) == false);
		assert (BatteryManagementValidator.batteryIsOk(50, 12, 0.1f) == false);
		assert (BatteryManagementValidator.batteryIsOk(50, 85, 0.1f) == false);
		assert (BatteryManagementValidator.batteryIsOk(0, 0, 0f) == false);
	}
}
