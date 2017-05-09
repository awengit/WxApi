package wxapi.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wxapi.Annotation.RightValidation;
import wxapi.Annotation.RightValidationType;
import wxapi.Entity.LoginUser;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

@RightValidation(RightValidationType.NeedUrlRight)
@Controller
@RequestMapping("/loginuser/*")
public class LoginUserController extends ControllerBase {

	@RequestMapping(value = "list")
	public ModelAndView list() {
		List<wxapi.Entity.View.LoginUser> users = loginUserService.select();
		ModelAndView mv = new ModelAndView();
		mv.addObject("users", users);
		mv.setViewName("loginuser/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public ModelAndView modify(String loginname) {
		wxapi.Entity.View.LoginUser user = null;
		List<wxapi.Entity.UserRole> userRoles = rightService.selectUserRole();
		List<wxapi.Entity.OfficialAccount> accounts = accountService.select();
		if (loginname != null && !loginname.isEmpty()) {
			user = loginUserService.select(loginname);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.addObject("userRoles", userRoles);
		mv.addObject("accounts", accounts);
		mv.setViewName("loginuser/modify");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(LoginUser entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		if (entity == null) {
			result.setIssuccess(false);
			result.setCode(ResultCode.Null.ordinal());
			result.setMsg("entity is null");
			response.getWriter().print(result.toJson());
			return;
		}
		result = entity.validate();
		if (!result.getIssuccess()) {
			response.getWriter().print(result.toJson());
			return;
		}
		int affect = loginUserService.insertOrUpdate(entity);
		if (affect > 0) {
			result.setMsg(String.valueOf(affect));
		} else {
			result.setIssuccess(false);
			result.setCode(ResultCode.PostReturnNotExpected.ordinal());
			result.setMsg("提交失败");
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(String loginname, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		if (loginname == null || loginname.isEmpty()) {
			result.setIssuccess(false);
			result.setCode(ResultCode.ParamError.ordinal());
			result.setMsg("参数有误");
			response.getWriter().print(result.toJson());
			return;
		}
		int affect = loginUserService.delete(loginname);
		if (affect <= 0) {
			result.setIssuccess(false);
			result.setCode(ResultCode.PostReturnNotExpected.ordinal());
			result.setMsg("系统异常");
		} else {
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
			result.setMsg("");
		}
		response.getWriter().print(result.toJson());
	}
}
