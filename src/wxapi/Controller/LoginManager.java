package wxapi.Controller;

import java.util.ArrayList;

import wxapi.Entity.LoginUser;
import wxapi.Entity.OfficialAccount;

public class LoginManager {

	public static CurUserInfo getCurUserInfo() {
		CurUserInfo curUserInfo = new CurUserInfo();
		curUserInfo.user = new LoginUser();
		curUserInfo.wxaccount = new ArrayList<OfficialAccount>();
		OfficialAccount account = new OfficialAccount();
		account.setAccountname("测试专用");
		account.setAccountnum("gh_ea6695e339cf");
		account.setAppid("wx4fbe4f113e5a0f5e");
		account.setSecret("5f2d349688b0bc74516efb9dc3049fbd");
		account.setIsdeleted(false);
		account.setAccountcode("CSZY");
		curUserInfo.wxaccount.add(account);
		return curUserInfo;
	}

}
