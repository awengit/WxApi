package wxapi.DataContext;

import java.sql.Types;

import wxapi.Entity.OfficialacCount;

public class OfficialAccountContext extends DBContextBase {

	public int Insert(OfficialacCount entity) {
		if (InitExecuteProc("{call pack_officialaccount.InsertData(?,?,?,?,?)}")) {
			SetObject(1, entity.getAccountname());
			SetObject(2, entity.getAccountnum());
			SetObject(3, entity.getAppid());
			SetObject(4, entity.getSecret());
			RegisterOutParameter(5, Types.INTEGER);
			ExecuteProc();
			int newId = (int) getObject(5);
			Release();
			return newId;
		}
		return -1;
	}

	public int Update(OfficialacCount entity) {
		if (InitExecuteProc("{call pack_officialaccount.UpdateData(?,?,?,?,?)}")) {
			SetObject(1, entity.getAccountname());
			SetObject(2, entity.getAccountnum());
			SetObject(3, entity.getAppid());
			SetObject(4, entity.getSecret());
			RegisterOutParameter(5, Types.INTEGER);
			ExecuteProc();
			int affect = (int) getObject(5);
			Release();
			return affect;
		}
		return -1;
	}

	public int Remove(String accountnum) {
		if (InitExecuteProc("{call pack_officialaccount.Remove(?,?)}")) {
			SetObject(1, accountnum);
			RegisterOutParameter(2, Types.INTEGER);
			ExecuteProc();
			int affect = (int) getObject(2);
			Release();
			return affect;
		}
		return -1;
	}
}
