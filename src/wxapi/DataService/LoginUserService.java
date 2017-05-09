package wxapi.DataService;

import java.util.List;

import wxapi.DataContext.LoginUserContext;
import wxapi.Entity.LoginUser;

public class LoginUserService {

	public LoginUserContext context = new LoginUserContext();

	public int insertOrUpdate(LoginUser entity) {
		return context.insertOrUpdate(entity);
	}

	public int updatePsw(String loginName, String oldPsw, String newPsw) {
		return context.updatePsw(loginName, oldPsw, newPsw);
	}

	public int delete(String loginName) {
		return context.delete(loginName);
	}

	public List<wxapi.Entity.View.LoginUser> select() {
		return context.select();
	}

	public wxapi.Entity.View.LoginUser select(String loginname) {
		return context.select(loginname);
	}

	public wxapi.Entity.View.LoginUser select(String loginname, String loginPsw) {
		return context.select(loginname, loginPsw);
	}

}
