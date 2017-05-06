package wxapi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wxapi.Entity.DataContainer;
import wxapi.Entity.OfficialAccount;
import wxapi.Entity.WxGroup;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.Result2;
import wxapi.Entity.View.ResultCode;
import wxapi.Entity.Where.WxUserSelectByWhere;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxResult;
import wxapi.Entity.Wx.WxUser;
import wxapi.Entity.Wx.WxUserInfo;
import wxapi.WxHelper.WxUserHelper;

@Controller
@RequestMapping("/wxuser/*")
public class WxUserController extends ControllerBase {

	@RequestMapping(value = "list")
	public ModelAndView list(WxUserSelectByWhere where) {
		OfficialAccount account;
		where.validate();
		account = getValidWxAccount(where.getAccountcode());
		List<WxGroup> groups = null;
		DataContainer<wxapi.Entity.WxUserInfo> dc = new DataContainer<wxapi.Entity.WxUserInfo>();
		if (account != null) {
			where.setAccountnum(account.getAccountnum());
			where.setAccountcode(account.getAccountcode());
			groups = wxGroupService.selectByAccountcode(account.getAccountcode());
			dc = wxUserService.selectByWhere(where);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wxuser/list");
		mv.addObject("pagecount", (int) Math.ceil((float) dc.rowCount / Integer.parseInt(where.getPagesize())));
		mv.addObject("where", where);
		mv.addObject("wxuser", dc.data);
		mv.addObject("group", groups);
		mv.addObject("account", getLoginUserInfo().wxaccount);
		return mv;
	}

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
		Result2<AccessToken> resultAccessToken = getAccessToken(accountcode);
		if (!resultAccessToken.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
			response.getWriter().print(result.toJson());
			return;
		}
		WxUserHelper wxUserHelper = new WxUserHelper();
		List<String> openids = new ArrayList<String>();
		AccessToken accessToken = resultAccessToken.getParam();
		String nextopenid = "";
		Result2<WxUser> resultWxUser;
		do {
			resultWxUser = wxUserHelper.getWxUser(accessToken, nextopenid);
			if (!resultWxUser.getIssuccess()) {
				result.setIssuccess(true);
				result.setCode(resultWxUser.getCode());
				result.setMsg(resultWxUser.getMsg());
				response.getWriter().print(result.toJson());
				return;
			}
			if (resultWxUser.getParam().getData() != null && resultWxUser.getParam().getData().getOpenid() != null) {
				openids.addAll(resultWxUser.getParam().getData().getOpenid());
				nextopenid = resultWxUser.getParam().getNext_openid();
			}
		} while (resultWxUser.getParam().getData() != null && resultWxUser.getParam().getData().getOpenid() != null && !resultWxUser.getParam().getNext_openid().isEmpty());
		List<WxUserInfo> infos = wxUserHelper.getWxUserInfoArray(accessToken, openids);
		wxUserService.batchInsert(infos, getValidWxAccountNum(accountcode));
		result.setIssuccess(true);
		result.setCode(ResultCode.Success.ordinal());
		result.setMsg("成功");
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "updateremark", method = RequestMethod.POST)
	public void updateRemark(String accountcode, String openid, String remark, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (accountcode == null || accountcode.isEmpty() || openid == null || openid.isEmpty() || remark == null || remark.isEmpty()) {
			result.setMsg("参数有误");
			response.getWriter().print(result.toJson());
			return;
		}
		Result2<AccessToken> resultAccessToken = getAccessToken(accountcode);
		if (!resultAccessToken.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
			response.getWriter().print(result.toJson());
			return;
		}
		WxUserHelper wxUserHelper = new WxUserHelper();
		Result2<WxResult> resultWxResult = wxUserHelper.updateWxUserRemark(resultAccessToken.getParam(), openid, remark);
		if (!resultWxResult.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
		} else {
			if (wxUserService.updateRemark(getValidWxAccountNum(accountcode), openid, remark) > 0) {
				result.setIssuccess(true);
				result.setCode(ResultCode.Success.ordinal());
			} else {
				result.setIssuccess(false);
				result.setCode(ResultCode.PostReturnNotExpected.ordinal());
				result.setMsg("数据库删除分组失败");
			}
		}
		response.getWriter().print(result.toJson());
	}

}
