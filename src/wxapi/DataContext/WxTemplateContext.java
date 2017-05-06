package wxapi.DataContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.DataContext.Base.ISQLOperate;
import wxapi.Entity.Wx.WxTemplate;

public class WxTemplateContext extends DBContextBase {

	public int batchInsert(List<WxTemplate> array) {
		executeSqlByBatch("insert into wxtemplate(accountnum,templateid,templatetitle,primaryindustry,deputyindustry,templatecontent,templateexample)values(?,?,?,?,?,?,?)", new Object[] { array }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				@SuppressWarnings("unchecked")
				List<WxTemplate> array = (List<WxTemplate>) param[0];
				for (int i = 0; i < array.size(); i++) {
					ps.setObject(1, array.get(i).getAccountnum());
					ps.setObject(2, array.get(i).getTemplate_id());
					ps.setObject(3, array.get(i).getTitle());
					ps.setObject(4, array.get(i).getPrimary_industry());
					ps.setObject(5, array.get(i).getDeputy_industry());
					ps.setObject(6, array.get(i).getContent());
					ps.setObject(7, array.get(i).getExample());
					ps.addBatch();
					if ((i + 1) % 100 == 0 || i == array.size() - 1) {
						ps.executeBatch();
					}
				}
				return null;
			}
		});
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<WxTemplate> SelectByAccountnum(String accountnum) {
		Object obj = executeSql("select * from wxtemplate where accountnum = ?", new Object[] { accountnum }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<WxTemplate>) obj;
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
