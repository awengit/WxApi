package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.WxUserContext;
import wxapi.Entity.WxUserInfo;

public class WxUserService {

	private WxUserContext context = new WxUserContext();

	public int insertOrUpdate(WxUserInfo entity) {
		return context.insertOrUpdate(entity);
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxUserInfo> array, String accountnum) {
		return context.batchInsert(array, accountnum);
	}

}
