package wxapi.Entity.View;

public enum ResultCode {
	/*
	 * 成功
	 */
	Success(0),

	/*
	 * 参数错误
	 */
	ParamError(1),

	/*
	 * 提交成功，返回不符合预期
	 */
	PostReturnNotExpected(2),

	/*
	 * 需要登录
	 */
	NeedLogin(3),

	/*
	 * 权限不足
	 */
	NoRole(4),

	/*
	 * 不存在
	 */
	NotExist(5),

	/*
	 * 无效
	 */
	UnValidate(6),

	/**
	 * Null
	 */
	Null(7);

	private int value = 0;

	private ResultCode(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	public static ResultCode valueOf(int value) {
		switch (value) {
		case 0:
			return Success;
		case 1:
			return ParamError;
		case 2:
			return PostReturnNotExpected;
		case 3:
			return NeedLogin;
		case 4:
			return NoRole;
		case 5:
			return NotExist;
		case 6:
			return UnValidate;
		case 7:
			return Null;
		default:
			return null;
		}
	}
}
