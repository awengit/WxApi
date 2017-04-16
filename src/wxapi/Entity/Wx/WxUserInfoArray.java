package wxapi.Entity.Wx;

import java.util.ArrayList;
import java.util.List;

public class WxUserInfoArray {

	public WxUserInfoArray() {
		user_info_list = new ArrayList<WxUserInfo>();
	}

	private List<WxUserInfo> user_info_list;

	public List<WxUserInfo> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<WxUserInfo> user_info_list) {
		this.user_info_list = user_info_list;
	}

}
