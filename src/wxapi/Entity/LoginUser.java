package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class LoginUser extends BeanBase implements IValidate {

	private String loginname;

	private String loginpsw;

	private String rolecode;

	private String wxaccountcodes;

	private boolean isdeleted;

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

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getWxaccountcodes() {
		return wxaccountcodes;
	}

	public void setWxaccountcodes(String wxaccountcodes) {
		this.wxaccountcodes = wxaccountcodes;
	}

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public boolean isUpdate = false;

	@Override
	public Result validate() {
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (!isUpdate && !validateStringIsLN(loginname, false, 1, 20)) {
			result.setMsg("用户名不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!isUpdate && !validateStringIsPassword(loginpsw, false, 1, 20)) {
			result.setMsg("用户密码不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!validateStringIsLN(rolecode, false, 1, 50)) {
			result.setMsg("角色不能为空");
			return result;
		}
		if (!validateStringIsLNs(wxaccountcodes, false, 1, 50)) {
			System.out.println(wxaccountcodes);
			result.setMsg("微信公众号不能为空，且长度不能超过50个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}

}
