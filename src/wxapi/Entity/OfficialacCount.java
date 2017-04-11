package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class OfficialacCount extends BeanBase implements IValidate {

	private int id;

	private String accountname;

	private String accountnum;

	private String appid;

	private String secret;

	private boolean isdeleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isUpdate;

	@Override
	public Result Validate() {
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (!ValidateStringRang(accountname, false, -1, 20)) {
			result.setMsg("公众号名称不能为空");
			return result;
		}
		if (!ValidateStringRang(accountnum, false, 10, 100)) {
			result.setMsg("公众号账号不能为空");
			return result;
		}
		if (!ValidateStringRang(appid, false, 10, 100)) {
			result.setMsg("appid不能为空");
			return result;
		}
		if (!ValidateStringRang(secret, false, 10, 100)) {
			result.setMsg("secret不能为空");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}
}
