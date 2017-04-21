package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.Wx.WxTemplate;

public class WxTemplateContext extends DBContextBase {

	public int[] batchInsert(Object[][] params) {
		int[] result = executeSqlByBatch("insert into wxtemplate(accountnum,templateid,templatetitle,primaryindustry,deputyindustry,templatecontent,templateexample)values(?,?,?,?,?,?,?)", params);
		release();
		return result;
	}

	public List<WxTemplate> SelectByAccountnum(String accountnum) {
		ResultSet rs = executeSql("select * from wxtemplate where accountnum = ?", new Object[] { accountnum });
		List<WxTemplate> array = toList(rs);
		release();
		return array;
	}

	private List<WxTemplate> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<WxTemplate> array = new ArrayList<WxTemplate>();
		WxTemplate template;
		try {
			while (rs.next()) {
				template = new WxTemplate();
				template.setAccountnum(rs.getString("accountnum"));
				template.setContent(rs.getString("content"));
				template.setDeputy_industry(rs.getString("deputy_industry"));
				template.setExample(rs.getString("example"));
				template.setPrimary_industry(rs.getString("primary_industry"));
				template.setTemplate_id(rs.getString("template_id"));
				template.setTitle(rs.getString("title"));
				array.add(template);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() <= 0) ? null : array;
	}

}
