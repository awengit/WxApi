package wxapi.Web;

import java.util.List;

import wxapi.Entity.LoginUser;
import wxapi.Entity.OfficialAccount;
import wxapi.Entity.View.UserRight;

public class LoginUserInfo {

	public LoginUser user;

	public List<OfficialAccount> wxaccount;

	public List<UserRight> userRight;

}
