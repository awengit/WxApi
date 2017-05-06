package wxapi.Entity.Where;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;

public class WxUserSelectByWhere extends BeanBase implements IValidate {

	private String accountcode;

	private String accountnum;

	private String groupid;

	private String pageindex;

	private String pagesize;

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getPageindex() {
		return pageindex;
	}

	public void setPageindex(String pageindex) {
		this.pageindex = pageindex;
	}

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@Override
	public Result validate() {
		if (!validateStringIsLN(accountcode, false, 1, 20)) {
			accountcode = "";
		}
		if (groupid == null || !validateStringIsInt(groupid, 0, 99999)) {
			groupid = "";
		}
		if (pageindex == null || !validateStringIsInt(pageindex, 0, 99999)) {
			pageindex = "1";
		}
		if (pagesize == null || !validateStringIsInt(pagesize, 0, 50)) {
			pagesize = "10";
		}
		return null;
	}

}
