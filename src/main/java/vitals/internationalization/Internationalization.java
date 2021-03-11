package vitals.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
	  
	public static String getMessage(String key) {
		Locale.setDefault(Locale.ENGLISH);
		ResourceBundle bundle = ResourceBundle.getBundle("messages");
		return bundle.getString(key);
	}
}
