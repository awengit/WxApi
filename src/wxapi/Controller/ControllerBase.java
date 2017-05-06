package wxapi.Controller;

import java.util.List;

import wxapi.DataService.CategoryFlagService;
import wxapi.DataService.CategoryService;
import wxapi.DataService.OfficialAccountService;
import wxapi.DataService.UserRightService;
import wxapi.DataService.WxGroupService;
import wxapi.DataService.WxUserService;
import wxapi.Entity.OfficialAccount;
import wxapi.Entity.View.Result2;
import wxapi.Entity.View.ResultCode;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Web.LoginUserInfo;
import wxapi.WxHelper.WxHelperBase;

public class ControllerBase {

	protected static CategoryFlagService categoryFlagService = new CategoryFlagService();

	protected static CategoryService categoryService = new CategoryService();

	protected static OfficialAccountService accountService = new OfficialAccountService();

	protected static WxGroupService wxGroupService = new WxGroupService();

	protected static WxUserService wxUserService = new WxUserService();

	protected static UserRightService rightService = new UserRightService();

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 */
	public static LoginUserInfo getLoginUserInfo() {
		return null;
	}

	/**
	 * 获取有效的公众号代码
	 * 
	 * @param accountcode
	 * @return
	 */
	public static String getValidWxAccountCode(String accountcode) {
		List<OfficialAccount> accounts = getLoginUserInfo().wxaccount;
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		if (accountcode == null || accountcode.isEmpty()) {
			return accounts.get(0).getAccountcode();
		}
		for (OfficialAccount a : accounts) {
			if (a.getAccountcode().equals(accountcode)) {
				return accountcode;
			}
		}
		return accounts.get(0).getAccountcode();
	}

	/**
	 * 获取有效的公众号账户
	 * 
	 * @param accountcode
	 * @return
	 */
	public static String getValidWxAccountNum(String accountcode) {
		List<OfficialAccount> accounts = getLoginUserInfo().wxaccount;
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		if (accountcode == null || accountcode.isEmpty()) {
			return accounts.get(0).getAccountnum();
		}
		for (OfficialAccount a : accounts) {
			if (a.getAccountcode().equals(accountcode)) {
				return a.getAccountnum();
			}
		}
		return accounts.get(0).getAccountnum();
	}

	/**
	 * 获取有效的公众号
	 * 
	 * @param accountcode
	 * @return
	 */
	public static OfficialAccount getValidWxAccount(String accountcode) {
		List<OfficialAccount> accounts = getLoginUserInfo().wxaccount;
		if (accounts == null || accounts.size() == 0) {
			return null;
		}
		if (accountcode == null || accountcode.isEmpty()) {
			return accounts.get(0);
		}
		for (OfficialAccount a : accounts) {
			if (a.getAccountcode().equals(accountcode)) {
				return a;
			}
		}
		return accounts.get(0);
	}

	/**
	 * 获取有效的AccessToken
	 * 
	 * @param accountcode
	 * @return
	 */
	public static Result2<AccessToken> getAccessToken(String accountcode) {
		Result2<AccessToken> result2 = new Result2<AccessToken>();
		result2.setIssuccess(false);
		OfficialAccount account = getValidWxAccount(accountcode);
		if (account == null || !account.getAccountcode().equals(accountcode)) {
			result2.setCode(ResultCode.NoRole.ordinal());
			result2.setMsg("account is null，请确认你有权限操作");
			return result2;
		}
		AccessToken token = WxHelperBase.getAccessToken(account.getAccountnum(), account.getAppid(), account.getSecret());
		if (token == null) {
			result2.setCode(ResultCode.Null.ordinal());
			result2.setMsg("获取token失败");
		} else {
			result2.setIssuccess(true);
			result2.setCode(ResultCode.Success.ordinal());
			result2.setParam(token);
		}
		return result2;
	}

}
