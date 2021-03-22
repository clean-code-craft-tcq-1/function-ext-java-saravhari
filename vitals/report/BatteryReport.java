package vitals.report;

import java.util.logging.Logger;

public class BatteryReport {
	
	private final static Logger logger = Logger.getLogger(BatteryReport.class.getName());
	
	public static void printLog(String message) {
		logger.info(message);
	}
}
