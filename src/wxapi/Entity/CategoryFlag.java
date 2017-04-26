package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class CategoryFlag extends BeanBase implements IValidate {

	private String title;
	private String flag;
	private int ordernum;
	private boolean isdeleted;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
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
		if (!validateStringRang(title, false, 1, 20)) {
			result.setMsg("标题不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!isUpdate && !validateIntRange(ordernum, 1, 99999)) {
			result.setMsg("排序不能为空，只能为正整数，且大小不能超过99999");
			return result;
		}
		if (!isUpdate && !validateStringIsLN(flag, false, 1, 20)) {
			result.setMsg("标识不能为空，只能字母数字组合，且长度不能超过20个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}

}
