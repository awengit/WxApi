package wxapi.Entity.Wx;

public class WxResult {

	public WxResult() {
		errcode = -1;
	}

	private int errcode;

	private String errmsg;

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getErrmsg() {
		return errmsg;
	}
}
