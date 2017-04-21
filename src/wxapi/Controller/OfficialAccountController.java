package wxapi.Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import wxapi.DataService.OfficialAccountService;
import wxapi.Entity.OfficialAccount;
import wxapi.Entity.View.Result;
import wxapi.Entity.View.ResultCode;

@Controller
@RequestMapping("/officialaccount/*")
public class OfficialAccountController {

	private OfficialAccountService accountService = new OfficialAccountService();

	@RequestMapping(value = "list")
	public ModelAndView list() {
		List<OfficialAccount> array = accountService.select();
		ModelAndView mv = new ModelAndView();
		mv.addObject("account", array);
		mv.setViewName("officialaccount/list");
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public ModelAndView modify(String accountnum) {
		OfficialAccount account = null;
		if (accountnum != null && !accountnum.isEmpty()) {
			account = accountService.selectByAccountNum(accountnum);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("officialaccount/modify");
		mv.addObject("model", account);
		return mv;
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public void modify(OfficialAccount entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = entity.validate();
		if (result.getIssuccess()) {
			int affect = accountService.insertOrUpdate(entity);
			if (affect > 0) {
				result.setMsg(String.valueOf(affect));
			} else {
				result.setIssuccess(false);
				result.setCode(ResultCode.PostReturnNotExpected.ordinal());
				result.setMsg("提交失败");
			}
		}
		response.getWriter().print(result.toJson());
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(String accountnum, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		Result result = new Result();
		if (accountnum == null || accountnum.isEmpty()) {
			result.setIssuccess(false);
			result.setCode(ResultCode.ParamError.ordinal());
			result.setMsg("参数异常");
			response.getWriter().print(result.toJson());
			return;
		}
		int affect = accountService.remove(accountnum);
		if (affect > 0) {
			result.setIssuccess(true);
			result.setCode(ResultCode.Success.ordinal());
			result.setMsg(String.valueOf(affect));
		} else {
			result.setIssuccess(false);
			result.setCode(ResultCode.PostReturnNotExpected.ordinal());
			result.setMsg("提交失败");
		}
		response.getWriter().print(result.toJson());
	}

}
