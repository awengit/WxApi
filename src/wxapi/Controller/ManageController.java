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

import wxapi.Annotation.RightValidation;
import wxapi.Annotation.RightValidationType;
import wxapi.Entity.View.LoginForm;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;
import wxapi.Web.LoginUserInfo;

@Controller
@RequestMapping("/manage/*")
public class ManageController extends ControllerBase {

	@RightValidation(RightValidationType.NotNeed)
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/login");
		return mv;
	}

	@RightValidation(RightValidationType.NotNeed)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(LoginForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		Result result = form.validate();
		wxapi.Entity.View.LoginUser user = loginUserService.select(form.getLoginname(), form.getLoginpsw());
		if (user == null) {
			result.setIssuccess(false);
			result.setCode(ResultCode.NotExist.ordinal());
			result.setMsg("用户名用户密码不匹配");
		} else {
			LoginUserInfo userInfo = new LoginUserInfo();
			userInfo.user = user;
			userInfo.wxaccount = accountService.select();

			List<wxapi.Entity.View.UserRight> userright = rightService.selectUserRight();
			List<wxapi.Entity.View.UserRight> userrighthave = new ArrayList<wxapi.Entity.View.UserRight>();
			for (wxapi.Entity.View.UserRight right : userright) {
				if (user.getUserrights().contains("," + right.getId() + ",")) {
					userrighthave.add(right);
				}
			}
			userInfo.userRight = userrighthave;

			request.getSession().setAttribute("loginuserinfo", userInfo);
			result.setIssuccess(true);
		}
		response.getWriter().print(result.toJson());
	}

	@RightValidation(RightValidationType.NeedLogin)
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("userright", getCurUserInfo().userRight);
		mv.setViewName("manage/index");
		return mv;
	}

	@RightValidation(RightValidationType.NeedLogin)
	@RequestMapping(value = "logout")
	public void logout(LoginForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		removeCurUserInfo();
		request.getSession().removeAttribute("loginuserinfo");
		response.sendRedirect("/manage/login.html");
	}

}
