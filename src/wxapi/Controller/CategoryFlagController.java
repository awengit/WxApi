package wxapi.Controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wxapi.DataService.CategoryFlagService;
import wxapi.Entity.CategoryFlag;

@Controller
@RequestMapping("/categoryflag/*")
public class CategoryFlagController {

	CategoryFlagService service = new CategoryFlagService();

	@RequestMapping(value = "list")
	public ModelAndView list() {
		List<CategoryFlag> array = service.select();
		ModelAndView mv = new ModelAndView();
		mv.addObject("account", array);
		mv.setViewName("categoryflag/list");
		return mv;
	}

}
