package wxapi.DataService;

import java.util.List;

import wxapi.DataContext.WxGroupContext;
import wxapi.Entity.WxGroup;

public class WxGroupService {

	private WxGroupContext context = new WxGroupContext();

	public int insertOrUpdate(WxGroup entity) {
		return context.insertOrUpdate(entity);
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxGroup> array, String accountnum) {
		return context.batchInsert(array, accountnum);
	}

	public int remove(String accountcode, int groupId) {
		return context.remove(accountcode, groupId);
	}

	public int batchMoveToGroup(String accountcode, String openIds, int toGroupId) {
		return context.batchMoveToGroup(accountcode, openIds, toGroupId);
	}

	public WxGroup select(String accountcode, int groupid) {
		return context.select(accountcode, groupid);
	}

	public List<WxGroup> selectByAccountcode(String accountcode) {
		return context.selectByAccountcode(accountcode);
	}
}
