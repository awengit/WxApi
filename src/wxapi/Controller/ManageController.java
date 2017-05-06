package wxapi.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

import wxapi.Annotation.RightValidation;
import wxapi.Annotation.RightValidationType;

@Controller
@RequestMapping("/manage/*")
public class ManageController {

	@RightValidation(RightValidationType.NotNeed)
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/login");
		return mv;
	}

	@RightValidation(RightValidationType.NotNeed)
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		String strJson = "中国";
		response.getWriter().print(strJson);
	}

	@RightValidation(RightValidationType.NeedLogin)
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/index");
		return mv;
	}

}
