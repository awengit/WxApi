package wxapi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wxapi.Entity.WxGroup;
import wxapi.Entity.Base.BeanBase;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.Result2;
import wxapi.Entity.View.ResultCode;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxGroupArray;
import wxapi.Entity.Wx.WxGroupForCreate;
import wxapi.Entity.Wx.WxResult;
import wxapi.WxHelper.WxGroupHelper;

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
		mv.addObject("account", getLoginUserInfo().wxaccount);
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
		Result2<AccessToken> resultAccessToken = getAccessToken(accountcode);
		if (!resultAccessToken.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
			response.getWriter().print(result.toJson());
			return;
		}
		result.setIssuccess(false);
		WxGroupHelper groupHelper = new WxGroupHelper();
		if (group.getGroupid() <= 2) {
			Result2<WxGroupForCreate> resultWxGroupForCreate = groupHelper.create(resultAccessToken.getParam(), group.getGroupname());
			if (!resultWxGroupForCreate.getIssuccess()) {
				result.setCode(resultWxGroupForCreate.getCode());
				result.setMsg(resultWxGroupForCreate.getMsg());
				response.getWriter().print(result.toJson());
				return;
			}
			group.setGroupid(resultWxGroupForCreate.getParam().getGroup().getId());
		} else {
			Result2<WxResult> resultWxResult = groupHelper.update(resultAccessToken.getParam(), group.getGroupid(), group.getGroupname());
			if (!resultWxResult.getIssuccess()) {
				result.setCode(resultWxResult.getCode());
				result.setMsg(resultWxResult.getMsg());
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
		Result2<AccessToken> resultAccessToken = getAccessToken(accountcode);
		if (!resultAccessToken.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
			response.getWriter().print(result.toJson());
			return;
		}
		WxGroupHelper helper = new WxGroupHelper();
		Result2<WxGroupArray> resultWxGroupArray = helper.get(resultAccessToken.getParam());
		if (resultWxGroupArray.getIssuccess()) {
			wxGroupService.batchInsert(resultWxGroupArray.getParam().getGroups(), getValidWxAccountNum(accountcode));
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
			result.setMsg("成功");
		} else {
			result.setIssuccess(false);
			result.setCode(resultWxGroupArray.getCode());
			result.setMsg(resultWxGroupArray.getMsg());
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
		Result2<AccessToken> resultAccessToken = getAccessToken(accountcode);
		if (!resultAccessToken.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
			response.getWriter().print(result.toJson());
			return;
		}
		WxGroupHelper helper = new WxGroupHelper();
		Result2<WxResult> resultWxResult = helper.delete(resultAccessToken.getParam(), groupid.intValue());
		if (!resultWxResult.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
		} else {
			if (wxGroupService.remove(accountcode, groupid.intValue()) > 0) {
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

	@RequestMapping(value = "batchmovetogroup", method = RequestMethod.POST)
	public void batchMoveToGroup(String accountcode, String openids, Integer togroupid, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		result.setIssuccess(false);
		if (accountcode == null || accountcode.isEmpty() || openids == null || openids.isEmpty() || togroupid == null || togroupid.intValue() < 0 || !BeanBase.validateStringIsPasswords(openids, false, 3, 10000)) {
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
		String[] openidArray = openids.split(",");
		List<String> openidList = new ArrayList<String>(Arrays.asList(openidArray));
		WxGroupHelper wxGroupHelper = new WxGroupHelper();
		Result2<WxResult> resultWxResult = wxGroupHelper.batchMoveToGroup(resultAccessToken.getParam(), openidList, togroupid.intValue());
		if (!resultWxResult.getIssuccess()) {
			result.setIssuccess(false);
			result.setCode(resultAccessToken.getCode());
			result.setMsg(resultAccessToken.getMsg());
		} else {
			wxGroupService.batchMoveToGroup(accountcode, openids, togroupid.intValue());
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
		}
		response.getWriter().print(result.toJson());
	}

}
