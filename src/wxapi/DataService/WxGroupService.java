package wxapi.DataService;

import wxapi.DataContext.WxGroupContext;
import wxapi.Entity.WxGroup;

public class WxGroupService {

	private WxGroupContext context = new WxGroupContext();

	public int insertOrUpdate(WxGroup entity) {
		return context.insertOrUpdate(entity);
	}

	public int remove(String accountnum, int groupId) {
		return context.remove(accountnum, groupId);
	}

	public int batchMoveToGroup(String accountnum, String openIds, int toGroupId) {
		return context.batchMoveToGroup(accountnum, openIds, toGroupId);
	}
}
