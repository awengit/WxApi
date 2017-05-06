package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class UserRole extends BeanBase implements IValidate {

	private String title;
	private String userrights;
	private int ordernum;
	private String rolecode;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserrights() {
		return userrights;
	}

	public void setUserrights(String userrights) {
		this.userrights = userrights;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (!validateStringRang(title, false, 1, 20)) {
			result.setMsg("标题不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!validateUints(userrights, false, 1, 500)) {
			result.setMsg("权限列表不能为空，且长度不能超过500个字符");
			return result;
		}
		if (!validateIntRange(ordernum, 1, 99999)) {
			result.setMsg("排序不能为空，且大小不能超过99999");
			return result;
		}
		if (!validateStringIsLN(rolecode, false, 1, 20)) {
			result.setMsg("角色代码不能为空，且长度不能超过20个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}
}
