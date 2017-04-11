package wxapi.Entity.Base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanBase {

	public boolean ValidateIntRange(int value, int minValue) {
		return value >= minValue;
	}

	public boolean ValidateIntRange(int value, int minValue, int maxValue) {
		return value >= minValue && value <= maxValue;
	}

	public boolean ValidateUints(String value) {
		if (value == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(,\\d+){0,},$");
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public boolean ValidateStringRang(String value, boolean canEmpty,
			int minLength, int maxLength) {
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

	public boolean ValidateStringIsInt(String value, int minValue, int maxValue) {
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

}
