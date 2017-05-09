package wxapi.Entity.View;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;

public class LoginForm extends BeanBase implements IValidate {

	private String loginname;

	private String loginpsw;

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpsw() {
		return loginpsw;
	}

	public void setLoginpsw(String loginpsw) {
		this.loginpsw = loginpsw;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (!validateStringIsPassword(loginname, false, 1, 20)) {
			result.setMsg("用户名不能为空，并且字符长度在1到20之间");
			return result;
		}
		if (!validateStringIsPassword(loginpsw, false, 1, 20)) {
			result.setMsg("用户密码不能为空，并且字符长度在1到20之间");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}
}
