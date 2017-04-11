package wxapi.Entity.View;

public class Result {
	private boolean issuccess;

	private int code;

	private String msg;

	public boolean getIssuccess() {
		return issuccess;
	}

	public void setIssuccess(boolean issuccess) {
		this.issuccess = issuccess;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String ToJson() {
		return "{issuccess:" + (issuccess ? "true" : "false") + ",code:" + code
				+ ",msg:\"" + msg + "\"}";
	}

}
