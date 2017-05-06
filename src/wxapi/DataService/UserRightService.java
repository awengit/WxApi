package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.UserRightContext;
import wxapi.Entity.UserRight;
import wxapi.Entity.UserRole;

public class UserRightService {

	private UserRightContext context = new UserRightContext();

	public int insertRight(UserRight entity) {
		return context.insertRight(entity);
	}

	public int updateRight(UserRight entity) {
		return context.updateRight(entity);
	}

	public int deleteRight(int id) {
		return context.deleteRight(id);
	}

	public List<wxapi.Entity.View.UserRight> selectUserRight() {
		return context.selectUserRight();
	}

	public wxapi.Entity.View.UserRight selectRightById(int id) {
		return context.selectRightById(id);
	}

	public List<wxapi.Entity.View.UserRight> selectRightByCategoryId(int id) {
		return context.selectRightByCategoryId(id);
	}

	public int insertOrUpdateUserRole(UserRole entity) {
		return context.insertOrUpdateUserRole(entity);
	}

	public UserRole selectUserRoleByCode(String rolecode) {
		return context.selectUserRoleByCode(rolecode);
	}

	public List<UserRole> selectUserRole() {
		return context.selectUserRole();
	}
}