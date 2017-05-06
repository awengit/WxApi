package wxapi.Entity;

import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.Base.IValidate;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

public class UserRight extends BeanBase implements IValidate {
	private int id;
	private int categoryid;
	private String title;
	private String url;
	private boolean displayinbanner;
	private int ordernum;
	private boolean isdeleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getDisplayinbanner() {
		return displayinbanner;
	}

	public void setDisplayinbanner(boolean displayinbanner) {
		this.displayinbanner = displayinbanner;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
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
		if (!validateIntRange(ordernum, 1, 99999)) {
			result.setMsg("排序不能为空，且大小不能超过99999");
			return result;
		}
		if (!validateStringRang(url, false, 1, 100)) {
			result.setMsg("url不能为空，且长度不能超过100个字符");
			return result;
		}
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		return result;
	}
}
