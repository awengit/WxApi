package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.OfficialAccountContext;
import wxapi.Entity.OfficialAccount;

public class OfficialAccountService {
	private OfficialAccountContext context = new OfficialAccountContext();

	public int insertOrUpdate(OfficialAccount entity) {
		return context.insertOrUpdate(entity);
	}

	public int remove(String accountcode) {
		return context.remove(accountcode);
	}

	public OfficialAccount selectByAccountCode(String accountcode) {
		return context.selectByAccountCode(accountcode);
	}

	public List<OfficialAccount> select() {
		return context.select();
	}
}
