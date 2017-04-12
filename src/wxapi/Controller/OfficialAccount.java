package wxapi.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller("/officialaccount/*")
public class OfficialAccount {

	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("officialaccount/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String add() {
		return "officialaccount/modify";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=utf-8");
		// response.getWriter().print();
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit() {
		return "officialaccount/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json;charset=utf-8");
		// response.getWriter().print(strJson);
	}
}
