package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class WxGroup extends BeanBase implements IValidate {
	private String accountcode;
	private String accountname;
	private String accountnum;
	private int groupid;
	private String groupname;
	private int usercount;

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

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public int getUsercount() {
		return usercount;
	}

	public void setUsercount(int usercount) {
		this.usercount = usercount;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (!validateStringIsLN(accountcode, false, 1, 20)) {
			result.setMsg("公众号标识不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!validateStringRang(groupname, false, 1, 20)) {
			result.setMsg("分组名称不能为空，且长度不能超过20个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}

}
