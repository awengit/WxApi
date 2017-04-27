package wxapi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wxapi.Entity.OfficialAccount;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxGroupArray;
import wxapi.Entity.Wx.WxResult;
import wxapi.Entity.Wx.WxUser;
import wxapi.Entity.Wx.WxUserInfo;
import wxapi.WxHelper.WxGroupHelper;
import wxapi.WxHelper.WxHelperBase;
import wxapi.WxHelper.WxUserHelper;

public class WxUserController extends ControllerBase {

	@RequestMapping(value = "refresh", method = RequestMethod.POST)
	public void refresh(String accountcode, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (accountcode == null || accountcode.isEmpty()) {
			result.setMsg("参数有误");
			response.getWriter().print(result.toJson());
			return;
		}
		OfficialAccount account = accountService.selectByAccountCode(accountcode);
		if (account == null) {
			result.setMsg("account is null");
			response.getWriter().print(result.toJson());
			return;
		}
		Object obj = WxHelperBase.getAccessToken(account.getAccountnum(), account.getAppid(), account.getSecret());
		if (!(obj instanceof AccessToken)) {
			result.setMsg("token is null");
			response.getWriter().print(result.toJson());
			return;
		}
		WxUserHelper wxUserHelper = new WxUserHelper();
		List<String> openids = new ArrayList<String>();
		WxUser wxUser;
		AccessToken accessToken = (AccessToken) obj;
		do {
			obj = wxUserHelper.getWxUser(accessToken, "");
			if (!(obj instanceof WxUser)) {
				break;
			}
			wxUser = (WxUser) obj;
			if (wxUser.getData() != null && wxUser.getData().getOpenid() != null) {
				openids.addAll(wxUser.getData().getOpenid());
			}
		} while (wxUser.getData() != null && wxUser.getData().getOpenid() != null && !wxUser.getNext_openid().isEmpty());
		List<WxUserInfo> infos = wxUserHelper.getWxUserInfoArray(accessToken, openids);
		wxUserService.batchInsert(infos, account.getAccountnum());
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		result.setMsg("成功");
		response.getWriter().print(result.toJson());
	}
}
