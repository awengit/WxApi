package wxapi.DataService;

import java.util.List;

import wxapi.DataContext.WxUserContext;
import wxapi.Entity.DataContainer;
import wxapi.Entity.WxUserInfo;
import wxapi.Entity.Where.WxUserSelectByWhere;

public class WxUserService {

	private WxUserContext context = new WxUserContext();

	public int insertOrUpdate(WxUserInfo entity) {
		return context.insertOrUpdate(entity);
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxUserInfo> array, String accountnum) {
		return context.batchInsert(array, accountnum);
	}

	public int updateRemark(String accountnum, String openid, String remark) {
		return context.updateRemark(accountnum, openid, remark);
	}

	public DataContainer<wxapi.Entity.WxUserInfo> selectByWhere(WxUserSelectByWhere entity) {
		return context.selectByWhere(entity);
	}

}
