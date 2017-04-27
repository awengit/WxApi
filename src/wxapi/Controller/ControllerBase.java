package wxapi.Controller;

import java.util.List;

import wxapi.DataService.CategoryFlagService;
import wxapi.DataService.CategoryService;
import wxapi.DataService.OfficialAccountService;
import wxapi.DataService.WxGroupService;
import wxapi.DataService.WxUserService;
import wxapi.Entity.OfficialAccount;

public class ControllerBase {

	protected static CategoryFlagService categoryFlagService = new CategoryFlagService();

	protected static CategoryService categoryService = new CategoryService();

	protected static OfficialAccountService accountService = new OfficialAccountService();

	protected static WxGroupService wxGroupService = new WxGroupService();

	protected static WxUserService wxUserService = new WxUserService();

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	public CurUserInfo getCurUserInfo() {
		return LoginManager.getCurUserInfo();
	}

	/**
	 * 获取有效的公众号代码
	 * 
	 * @param accountcode
	 * @return
	 */
	public String getValidWxAccountCode(String accountcode) {
		List<OfficialAccount> accounts = getCurUserInfo().wxaccount;
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		for (OfficialAccount a : accounts) {
			if (a.getAccountcode().equals(accountcode)) {
				return accountcode;
			}
		}
		return accounts.get(0).getAccountcode();
	}

}
