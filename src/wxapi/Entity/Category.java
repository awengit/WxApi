package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class Category extends BeanBase implements IValidate {

	private int id;
	private String title;
	private int parentid;
	private int ordernum;
	private int grade;
	private String sort;
	private String flag;
	private String cvalue;
	private boolean isdeleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
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
		if (!validateStringRang(title, false, 1, 20)) {
			result.setMsg("标题不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!isUpdate && !validateIntRange(ordernum, 1, 99999)) {
			result.setMsg("排序不能为空，且大小不能超过99999");
			return result;
		}
		if (!isUpdate && parentid == 0 && !validateStringRang(flag, false, 1, 20)) {
			result.setMsg("标识不能为空，且长度不能超过20个字符");
			return result;
		}
		if (!validateStringRang(cvalue, true, 1, 100)) {
			result.setMsg("类别值长度不能超过100个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}

}
