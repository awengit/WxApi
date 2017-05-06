package wxapi.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import wxapi.Entity.Category;
import wxapi.Entity.UserRight;
import wxapi.Entity.UserRole;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

@Controller
@RequestMapping("/userright/*")
public class UserRightController extends ControllerBase {

	@RequestMapping(value = "rightlist")
	public ModelAndView rightList(Integer categoryid) {
		List<Category> categoryArray = categoryService.select("QXFZ");
		if (categoryid == null && categoryArray != null && categoryArray.size() > 0) {
			categoryid = categoryArray.get(0).getId();
		}
		List<wxapi.Entity.View.UserRight> rightArray = rightService.selectRightByCategoryId(categoryid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("categoryid", categoryid);
		mv.addObject("rightArray", rightArray);
		mv.addObject("category", categoryArray);
		mv.setViewName("userright/rightlist");
		return mv;
	}

	@RequestMapping(value = "rightmodify", method = RequestMethod.GET)
	public ModelAndView rightModify(Integer id) {
		wxapi.Entity.View.UserRight userRight = null;
		if (id != null && id.intValue() > 0) {
			userRight = rightService.selectRightById(id.intValue());
		}
		List<Category> categoryArray = categoryService.select("QXFZ");
		ModelAndView mv = new ModelAndView();
		mv.addObject("right", userRight);
		mv.addObject("category", categoryArray);
		mv.setViewName("userright/rightmodify");
		return mv;
	}

	@RequestMapping(value = "rightmodify", method = RequestMethod.POST)
	public void rightModify(UserRight entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		int affect = 0;
		if (entity.getId() > 0) {
			affect = rightService.updateRight(entity);
		} else {
			affect = rightService.insertRight(entity);
		}
		if (affect > 0) {
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
		} else {
			result.setIssuccess(false);
			result.setCode(ResultCode.PostReturnNotExpected.ordinal());
			result.setMsg("提交异常");
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "rightdelete", method = RequestMethod.POST)
	public void rightDelete(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "rolelist")
	public ModelAndView roleList() {
		List<UserRole> roleArray = rightService.selectUserRole();
		ModelAndView mv = new ModelAndView();
		mv.addObject("roleArray", roleArray);
		mv.setViewName("userright/rolelist");
		return mv;
	}

	@RequestMapping(value = "rolemodify", method = RequestMethod.GET)
	public ModelAndView roleModify(String rolecode) {
		UserRole userRole = null;
		if (rolecode != null) {
			userRole = rightService.selectUserRoleByCode(rolecode);
		}
		List<wxapi.Entity.View.UserRight> rightArray = rightService.selectUserRight();
		ModelAndView mv = new ModelAndView();
		mv.addObject("role", userRole);
		mv.addObject("rightArray", rightArray);
		mv.setViewName("userright/rolemodify");
		return mv;
	}

	@RequestMapping(value = "rolemodify", method = RequestMethod.POST)
	public void roleModify(UserRole entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		int affect = rightService.insertOrUpdateUserRole(entity);
		if (affect > 0) {
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
		} else {
			result.setIssuccess(false);
			result.setCode(ResultCode.PostReturnNotExpected.ordinal());
			result.setMsg("提交异常");
		}
		response.getWriter().print(result.toJson());
	}
}
