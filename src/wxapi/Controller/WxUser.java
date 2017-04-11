package wxapi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller("/wxuser/*")
public class WxUser {

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView List() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wxuser/list");
		return mv;
	}

}
