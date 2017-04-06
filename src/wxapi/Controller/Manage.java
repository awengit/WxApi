package wxapi.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller("/manage/*")
public class Manage {

	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("manage/login");
		return mv;
	}

	@RequestMapping(value = "loginex", method = RequestMethod.GET)
	public void loginex(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=utf-8");
		// response.setContentType("text/html;charset=utf-8");
		String strJson = "中国";
		response.getWriter().print(strJson);
	}

}
