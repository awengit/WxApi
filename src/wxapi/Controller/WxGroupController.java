package wxapi.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wxapi.Entity.OfficialAccount;
import wxapi.Entity.WxGroup;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxGroupArray;
import wxapi.Entity.Wx.WxGroupForCreate;
import wxapi.Entity.Wx.WxResult;
import wxapi.WxHelper.WxGroupHelper;
import wxapi.WxHelper.WxHelperBase;

@Controller
@RequestMapping("/wxgroup/*")
public class WxGroupController extends ControllerBase {

	@RequestMapping(value = "list")
	public ModelAndView list(String accountcode) {
		List<WxGroup> groups = null;
		if (accountcode == null || accountcode.isEmpty()) {
			accountcode = getValidWxAccountCode(accountcode);
		}
		if (accountcode != null && !accountcode.isEmpty()) {
			groups = wxGroupService.selectByAccountcode(accountcode);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("account", getCurUserInfo().wxaccount);
		mv.addObject("accountcode", accountcode);
		mv.addObject("groups", groups);
		mv.setViewName("wxgroup/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public ModelAndView modify(String accountcode, Integer groupid) {
		WxGroup group = null;
		if (accountcode != null && !accountcode.isEmpty() && groupid != null && groupid.intValue() > 2) {
			group = wxGroupService.select(accountcode, groupid);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("accountcode", accountcode);
		mv.addObject("groupid", groupid);
		mv.addObject("group", group);
		mv.setViewName("wxgroup/modify");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(String accountcode, String groupname, Integer groupid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		WxGroup group = new WxGroup();
		group.setAccountcode(accountcode);
		group.setGroupname(groupname);
		group.setGroupid(groupid == null ? 0 : groupid.intValue());
		group.setUsercount(0);
		Result result = group.validate();
		if (!result.getIssuccess()) {
			response.getWriter().print(result.toJson());
			return;
		}
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		OfficialAccount account = accountService.selectByAccountCode(accountcode);
		if (account == null) {
			result.setMsg("account is null");
			response.getWriter().print(result.toJson());
			return;
		}
		Object obj = WxHelperBase.getAccessToken(account.getAccountnum(), account.getAppid(), account.getSecret());
		if (!(obj instanceof AccessToken)) {
			result.setMsg("获取token失败");
			response.getWriter().print(result.toJson());
			return;
		}
		WxGroupHelper groupHelper = new WxGroupHelper();
		if (group.getGroupid() <= 2) {
			obj = groupHelper.create((AccessToken) obj, group.getGroupname());
			if (!(obj instanceof WxGroupForCreate)) {
				result.setMsg("微信端新建分组失败");
				response.getWriter().print(result.toJson());
				return;
			}
			group.setGroupid(((WxGroupForCreate) obj).getGroup().getId());
		} else {
			WxResult wxResult = groupHelper.update((AccessToken) obj, group.getGroupid(), group.getGroupname());
			if (wxResult.getErrcode() != 0) {
				result.setMsg("微信端更新分组失败");
				response.getWriter().print(result.toJson());
				return;
			}
		}
		if (wxGroupService.insertOrUpdate(group) > 0) {
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
		} else {
			result.setMsg("数据库操作分组失败");
		}
		response.getWriter().print(result.toJson());
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
		WxGroupHelper helper = new WxGroupHelper();
		obj = helper.get((AccessToken) obj);
		if (obj instanceof WxGroupArray) {
			WxGroupArray array = (WxGroupArray) obj;
			wxGroupService.batchInsert(array.getGroups(), account.getAccountnum());
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
			result.setMsg("成功");
		} else {
			if (obj == null) {
				result.setMsg("null from wx");
			} else {
				WxResult wxResult = (WxResult) obj;
				result.setCode(wxResult.getErrcode());
				result.setMsg(wxResult.getErrmsg());
			}
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(String accountcode, Integer groupid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		result.setIssuccess(false);
		result.setCode(ResultCode.ParamError.ordinal());
		if (accountcode == null || accountcode.isEmpty() || groupid == null || groupid.intValue() <= 2) {
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
		WxGroupHelper helper = new WxGroupHelper();
		WxResult wxResult = helper.delete((AccessToken) obj, groupid.intValue());
		if (wxResult == null || wxResult.getErrcode() != 0) {
			result.setMsg("微信端删除分组失败");
		} else {
			if (wxGroupService.remove(accountcode, groupid.intValue()) > 0) {
				result.setIssuccess(true);
				result.setCode(ResultCode.Success.ordinal());
			} else {
				result.setMsg("数据库删除分组失败");
			}
		}
		response.getWriter().print(result.toJson());
	}

}
