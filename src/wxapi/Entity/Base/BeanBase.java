package wxapi.Entity.Base;

import java.util.regex.Pattern;

public class BeanBase {

	public static boolean validateIntRange(int value, int minValue) {
		return value >= minValue;
	}

	public boolean validateIntRange(int value, int minValue, int maxValue) {
		return value >= minValue && value <= maxValue;
	}

	public static boolean validateUints(String value, boolean canEmpty, int minLength, int maxLength) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(,\\d+){0,},$");
		if (!pattern.matcher(value).matches()) {
			return false;
		} else {
			return validateStringRang(value, false, minLength, maxLength);
		}
	}

	public static boolean validateStringRang(String value, boolean canEmpty, int minLength, int maxLength) {
		if (value == null || value.isEmpty()) {
			return canEmpty;
		}
		if (minLength < 0 && maxLength < 0) {
			return true;
		}
		int length = value.length();
		if (minLength > 0 && maxLength < 0) {
			return length >= minLength;
		}
		if (minLength < 0 && maxLength > 0) {
			return length <= maxLength;
		}
		return length >= minLength && length <= maxLength;
	}

	public static boolean validateStringIsInt(String value, int minValue, int maxValue) {
		if (value == null || value.isEmpty()) {
			return false;
		}
		try {
			int newValue = Integer.parseInt(value);
			return newValue >= minValue && newValue <= maxValue;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean validateStringIsLN(String value, boolean canEmpty, int minLength, int maxLength) {
		if (value == null || value.isEmpty()) {
			return canEmpty;
		}
		String strRegex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(strRegex);
		if (!pattern.matcher(value).matches()) {
			return false;
		} else {
			return validateStringRang(value, false, minLength, maxLength);
		}
	}

	public static boolean validateStringIsPassword(String value, boolean canEmpty, int minLength, int maxLength) {
		if (value == null || value.isEmpty()) {
			return canEmpty;
		}
		String strRegex = "^[a-zA-Z0-9!@#+_-]+$";
		Pattern pattern = Pattern.compile(strRegex);
		if (!pattern.matcher(value).matches()) {
			return false;
		} else {
			return validateStringRang(value, false, minLength, maxLength);
		}
	}

	public static boolean validateStringIsPasswords(String value, boolean canEmpty, int minLength, int maxLength) {
		if (value == null || value.isEmpty()) {
			return canEmpty;
		}
		String strRegex = "^(,[a-zA-Z0-9!@#+_-]+){0,},$";
		Pattern pattern = Pattern.compile(strRegex);
		if (!pattern.matcher(value).matches()) {
			return false;
		} else {
			return validateStringRang(value, false, minLength, maxLength);
		}
	}
}
