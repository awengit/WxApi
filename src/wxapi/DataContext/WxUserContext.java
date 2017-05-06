package wxapi.DataContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.DataContext.Base.ISQLOperate;
import wxapi.Entity.DataContainer;
import wxapi.Entity.WxUserInfo;
import wxapi.Entity.Where.WxUserSelectByWhere;

public class WxUserContext extends DBContextBase {

	public int insertOrUpdate(wxapi.Entity.Wx.WxUserInfo entity) {
		Object object = executeProc("{call pack_wxuser.InsertOrUpdate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				WxUserInfo entity = (WxUserInfo) param[0];
				cs.setString(1, entity.getAccountnum());
				cs.setInt(2, entity.getSubscribe());
				cs.setString(3, entity.getOpenid());
				cs.setString(4, entity.getNickname());
				cs.setInt(5, entity.getSex());
				cs.setString(6, entity.getCity());
				cs.setString(7, entity.getProvince());
				cs.setString(8, entity.getCountry());
				cs.setString(9, entity.getLanguage());
				cs.setString(10, entity.getHeadimgurl());
				cs.setInt(11, entity.getSubscribe_time());
				if (entity.getUnionid() == null) {
					cs.setNull(12, Types.VARCHAR);
				} else {
					cs.setString(12, entity.getUnionid());
				}
				cs.setString(13, entity.getRemark());
				cs.setInt(14, entity.getGroupid());
				cs.registerOutParameter(15, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(15);
				return affect;
			}
		}, null);
		if (object == null) {
			return -1;
		} else {
			return (int) object;
		}
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxUserInfo> array, String accountnum) {
		if (array == null || array.size() == 0) {
			return 0;
		}
		executeProc("{call pack_wxuser.BackUp(?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				String accountnum = (String) param[0];
				cs.setString(1, accountnum);
				cs.registerOutParameter(2, Types.INTEGER);
				return null;
			}
		}, new Object[] { accountnum }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(2);
				return affect;
			}
		}, null);
		executeSqlByBatch("insert into wxuser(accountnum,subscribe,openid,nickname,sex,city,province,country,language,headimgurl,subscribe_time,unionid,remark,groupid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] { array, accountnum }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				@SuppressWarnings("unchecked")
				List<wxapi.Entity.Wx.WxUserInfo> array = (List<wxapi.Entity.Wx.WxUserInfo>) param[0];
				String accountnum = (String) param[1];
				for (int i = 0; i < array.size(); i++) {
					ps.setString(1, accountnum);
					ps.setInt(2, array.get(i).getSubscribe());
					ps.setString(3, array.get(i).getOpenid());
					ps.setString(4, array.get(i).getNickname());
					ps.setInt(5, array.get(i).getSex());
					ps.setString(6, array.get(i).getCity());
					ps.setString(7, array.get(i).getProvince());
					ps.setString(8, array.get(i).getCountry());
					ps.setString(9, array.get(i).getLanguage());
					ps.setString(10, array.get(i).getHeadimgurl());
					ps.setInt(11, array.get(i).getSubscribe_time());
					if (array.get(i).getUnionid() == null) {
						ps.setNull(12, Types.VARCHAR);
					} else {
						ps.setString(12, array.get(i).getUnionid());
					}
					ps.setString(13, array.get(i).getRemark());
					ps.setInt(14, array.get(i).getGroupid());
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

	public int updateRemark(String accountnum, String openid, String remark) {
		int affect = executeSqlNonQuery("update wxuser set remark=? where accountnum = ? and openid=?", new Object[] { remark, accountnum, openid });
		return affect <= 0 ? -1 : affect;
	}

	@SuppressWarnings("unchecked")
	public DataContainer<wxapi.Entity.WxUserInfo> selectByWhere(WxUserSelectByWhere entity) {
		Object object = executeProc("{call pack_wxuser.SelectByWhere(?,?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				WxUserSelectByWhere entity = (WxUserSelectByWhere) param[0];
				cs.setString(1, entity.getAccountnum());
				cs.setString(2, entity.getGroupid());
				cs.setString(3, entity.getPageindex());
				cs.setString(4, entity.getPagesize());
				cs.registerOutParameter(5, Types.INTEGER);
				cs.registerOutParameter(6, OracleTypes.CURSOR);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(5);
				DataContainer<wxapi.Entity.WxUserInfo> dc = new DataContainer<wxapi.Entity.WxUserInfo>();
				dc.rowCount = affect;
				dc.data = toList(((ResultSet) cs.getObject(6)));
				return dc;
			}
		}, null);
		return (DataContainer<wxapi.Entity.WxUserInfo>) object;
	}

	private List<wxapi.Entity.WxUserInfo> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<wxapi.Entity.WxUserInfo> array = new ArrayList<wxapi.Entity.WxUserInfo>();
		wxapi.Entity.WxUserInfo into;
		try {
			while (rs.next()) {
				into = new wxapi.Entity.WxUserInfo();
				into.setGroupname(rs.getString("groupname"));
				into.setAccountnum(rs.getString("accountnum"));
				into.setCity(rs.getString("city"));
				into.setCountry(rs.getString("country"));
				into.setGroupid(rs.getInt("groupid"));
				into.setHeadimgurl(rs.getString("headimgurl"));
				into.setLanguage(rs.getString("language"));
				into.setNickname(rs.getString("nickname"));
				into.setOpenid(rs.getString("openid"));
				into.setProvince(rs.getString("province"));
				into.setRemark(rs.getString("remark"));
				into.setSex(rs.getInt("sex"));
				into.setSubscribe(rs.getInt("subscribe"));
				into.setSubscribe_time(rs.getInt("subscribe_time"));
				into.setUnionid(rs.getString("unionid"));
				array.add(into);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() <= 0) ? null : array;
	}
}
