package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.OfficialAccountContext;
import wxapi.Entity.OfficialAccount;

public class OfficialAccountService {
	private OfficialAccountContext context = new OfficialAccountContext();

	public int insertOrUpdate(OfficialAccount entity) {
		return context.insertOrUpdate(entity);
	}

	public int remove(String accountnum) {
		return context.remove(accountnum);
	}

	public OfficialAccount selectByAccountNum(String accountnum) {
		return context.selectByAccountNum(accountnum);
	}

	public List<OfficialAccount> select() {
		return context.select();
	}
}
