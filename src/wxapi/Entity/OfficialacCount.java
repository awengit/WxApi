package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class OfficialAccount extends BeanBase implements IValidate {

	private String accountcode;

	private String accountname;

	private String accountnum;

	private String appid;

	private String secret;

	private boolean isdeleted;

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public boolean getIsdeleted() {
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
		if (!isUpdate && !validateStringIsLN(accountcode, false, 0, 20)) {
			result.setMsg("公众号代码不能为空，并且长度不大于20");
			return result;
		}
		if (!validateStringRang(accountname, false, 0, 20)) {
			result.setMsg("公众号名称不能为空，并且长度不大于20");
			return result;
		}
		if (!validateStringRang(accountnum, false, 10, 100)) {
			result.setMsg("公众号账号不能为空，并且长度在10到100之间");
			return result;
		}
		if (!validateStringRang(appid, false, 10, 100)) {
			result.setMsg("appid不能为空，并且长度在10到100之间");
			return result;
		}
		if (!validateStringRang(secret, false, 10, 100)) {
			result.setMsg("secret不能为空，并且长度在10到100之间");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}
}
