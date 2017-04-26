var validater = {
	validateInt : function(value) {
		re = /^-?\d+$/;
		return re.test(value);
	},
	validateIntRange : function(value, minValue, maxValue) {
		if (!this.validateInt(value)) {
			return false;
		}
		var newInt = parseInt(value);
		if (typeof (minValue) != undefined && minValue != null && this.validateInt(minValue)) {
			if (newInt < minValue) {
				return false;
			}
		}
		if (typeof (maxValue) != undefined && maxValue != null && this.validateInt(maxValue)) {
			if (newInt > maxValue) {
				return false;
			}
		}
		return true;
	},
	validateUints : function(value) {
		return value.match(/^(,\d+){0,},$/);
	},
	validateStringRang : function(value, canEmpty, minLength, maxLength) {
		if (typeof (value) == undefined || value == null || value == '') {
			return canEmpty;
		}
		var length = value.length;
		if (typeof (minLength) != undefined && minLength != null && this.validateInt(minLength)) {
			if (length < minLength) {
				return false;
			}
		}
		if (typeof (maxLength) != undefined && maxLength != null && this.validateInt(maxLength)) {
			if (length > maxLength) {
				return false;
			}
		}
		return true;
	},
	validateStringIsLN : function(value, canEmpty, minLength, maxLength) {
		if (typeof (value) == undefined || value == null || value == '') {
			return canEmpty;
		}
		if (!value.match(/^[a-zA-Z0-9]{0,}$/)) {
			return false;
		}
		var length = value.length;
		if (typeof (minLength) != undefined && minLength != null && this.validateInt(minLength)) {
			if (length < minLength) {
				return false;
			}
		}
		if (typeof (maxLength) != undefined && maxLength != null && this.validateInt(maxLength)) {
			if (length > maxLength) {
				return false;
			}
		}
		return true;
	},
	test : function(value) {
		alert(value);
	}
};