package wxapi.Controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import wxapi.Entity.Category;
import wxapi.Entity.CategoryFlag;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

@Controller
@RequestMapping("/category/*")
public class CategoryController extends ControllerBase {

	@RequestMapping(value = "list")
	public ModelAndView list(String flag) {
		List<CategoryFlag> arrayFlag = categoryFlagService.select();
		if (flag == null || flag.isEmpty()) {
			if (arrayFlag.size() > 0) {
				flag = arrayFlag.get(0).getFlag();
			}
		}
		List<Category> array = null;
		if (flag != null && !flag.isEmpty()) {
			array = categoryService.select(flag);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("arrayFlag", arrayFlag);
		mv.addObject("array", array);
		mv.addObject("flag", flag);
		mv.setViewName("category/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public ModelAndView modify(Integer id) {
		Category entity = null;
		List<CategoryFlag> arrayFlag = null;
		List<Category> array = null;
		if (id != null && id.intValue() > 0) {
			entity = categoryService.selectById(id);
		}
		if (entity == null) {
			arrayFlag = categoryFlagService.select();
			array = categoryService.select();
		} else {
			array = categoryService.select(entity.getFlag());
			if (array != null && array.size() > 0) {
				Iterator<Category> it = array.iterator();
				while (it.hasNext()) {
					Category flag = (Category) it.next();
					if (flag.getSort().startsWith(entity.getSort().substring(0, entity.getSort().length() - 1))) {
						it.remove();
					}
				}
			}
		}
		Gson gson = new Gson();
		ModelAndView mv = new ModelAndView();
		mv.addObject("model", entity);
		mv.addObject("array", gson.toJson(array));
		mv.addObject("arrayFlag", arrayFlag);
		mv.setViewName("category/modify");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(Category entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		if (entity.getId() > 0) {
			entity.isUpdate = true;
		}
		Result result = entity.validate();
		if (result.getIssuccess()) {
			int affect = 0;
			if (entity.getId() > 0) {
				affect = categoryService.update(entity);
			} else {
				affect = categoryService.insert(entity);
			}
			if (affect > 0) {
				result.setMsg(String.valueOf(affect));
			} else {
				result.setIssuccess(false);
				result.setCode(ResultCode.PostReturnNotExpected.ordinal());
				result.setMsg("提交失败，排序不允许重复");
			}
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		if (id == null || id.intValue() <= 0) {
			result.setIssuccess(false);
			result.setCode(ResultCode.ParamError.ordinal());
			result.setMsg("参数有误");
			response.getWriter().print(result.toJson());
			return;
		}
		int affect = categoryService.delete(id);
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
