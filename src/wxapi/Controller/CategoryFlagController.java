package wxapi.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wxapi.Entity.CategoryFlag;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

@Controller
@RequestMapping("/categoryflag/*")
public class CategoryFlagController extends ControllerBase {

	@RequestMapping(value = "list")
	public ModelAndView list() {
		List<CategoryFlag> array = categoryFlagService.select();
		ModelAndView mv = new ModelAndView();
		mv.addObject("array", array);
		mv.setViewName("categoryflag/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public ModelAndView modify(String flag) {
		CategoryFlag entity = null;
		if (flag != null && !flag.isEmpty()) {
			entity = categoryFlagService.selectByFlag(flag);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("model", entity);
		mv.setViewName("categoryflag/modify");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(CategoryFlag entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		if (!entity.getFlag().isEmpty() && entity.getOrdernum() == 0) {
			entity.isUpdate = true;
		}
		Result result = entity.validate();
		if (result.getIssuccess()) {
			int affect = categoryFlagService.insertOrUpdate(entity);
			if (affect > 0) {
				result.setMsg(String.valueOf(affect));
			} else {
				result.setIssuccess(false);
				result.setCode(ResultCode.PostReturnNotExpected.ordinal());
				result.setMsg("提交失败，标识或者排序不允许重复");
			}
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(String flag, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		if (flag == null || flag.isEmpty()) {
			result.setIssuccess(false);
			result.setCode(ResultCode.ParamError.ordinal());
			result.setMsg("参数有误");
			response.getWriter().print(result.toJson());
			return;
		}
		int affect = categoryFlagService.delete(flag);
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
