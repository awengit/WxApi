package wxapi.DataContext;

import java.sql.Types;
import java.util.List;

import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.WxUserInfo;

public class WxUserContext extends DBContextBase {

	public int insertOrUpdate(WxUserInfo entity) {
		if (initExecuteProc("{call pack_wxuser.InsertOrUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
			setObject(1, entity.getAccountnum());
			setObject(2, entity.getSubscribe());
			setObject(3, entity.getOpenid());
			setObject(4, entity.getNickname());
			setObject(5, entity.getSex());
			setObject(6, entity.getCity());
			setObject(7, entity.getProvince());
			setObject(8, entity.getCountry());
			setObject(9, entity.getLanguage());
			setObject(10, entity.getHeadimgurl());
			setObject(11, entity.getSubscribe_time());
			setObject(12, entity.getUnionid());
			setObject(13, entity.getRemark());
			setObject(14, entity.getGroupid());
			registerOutParameter(15, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(15);
			release();
			return affect;
		}
		return -1;
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxUserInfo> array, String accountnum) {
		if (array == null || array.size() == 0) {
			return 0;
		}
		if (initExecuteProc("{call pack_wxuser.BackUp(?,?)}")) {
			setObject(1, accountnum);
			registerOutParameter(2, Types.INTEGER);
			executeProc();
			release();
		}
		Object[][] data = new Object[array.size()][14];
		for (int i = 0; i < array.size(); i++) {
			data[i][0] = accountnum;
			data[i][1] = array.get(i).getSubscribe();
			data[i][2] = array.get(i).getOpenid();
			data[i][3] = array.get(i).getNickname();
			data[i][4] = array.get(i).getSex();
			data[i][5] = array.get(i).getCity();
			data[i][6] = array.get(i).getProvince();
			data[i][7] = array.get(i).getCountry();
			data[i][8] = array.get(i).getLanguage();
			data[i][9] = array.get(i).getHeadimgurl();
			data[i][10] = array.get(i).getSubscribe_time();
			data[i][11] = array.get(i).getUnionid();
			data[i][12] = array.get(i).getRemark();
			data[i][13] = array.get(i).getGroupid();
		}
		int[] result = executeSqlByBatch("insert into wxuser(accountnum,subscribe,openid,nickname,sex,city,province,country,language,headimgurl,subscribe_time,unionid,remark,groupid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", data);
		if (result == null) {
			return 0;
		}
		int affect = 0;
		for (int i : result) {
			affect += i;
		}
		return affect;
	}

}
