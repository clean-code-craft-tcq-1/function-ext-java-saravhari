package vitals.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {

	public static String getMessage(String key) {
		Locale.setDefault(Locale.GERMAN);
		ResourceBundle bundle = ResourceBundle.getBundle("vitals/messages/messages");
		return bundle.getString(key);
	}
}
