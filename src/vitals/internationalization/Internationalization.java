package vitals.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalization {
	  
	public static String getMessage(String key) {
		Locale.setDefault(Locale.US);
		ResourceBundle bundle = ResourceBundle.getBundle(");
		return bundle.getString(key);
	}
}
