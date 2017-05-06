package wxapi.DataContext.Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContextBase {

	protected Connection conn = null;

	protected PreparedStatement ps = null;

	protected CallableStatement cs = null;

	protected ResultSet rs = null;

	/**
	 * 执行SQL，返回Object
	 * 
	 * @param sql
	 * @param params
	 * @param before
	 * @return
	 */
	protected Object executeSql(String sql, Object[] params, ISQLOperate after) {
		if (!initExecuteSql(sql, params)) {
			return null;
		}
		try {
			rs = ps.executeQuery();
			Object object = null;
			if (after != null) {
				object = after.operate(null, conn, ps, cs, rs);
			}
			return object;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
	}

	/**
	 * 执行SQL，返回受影响行数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	protected int executeSqlNonQuery(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return 0;
		}
		try {
			int affect = ps.executeUpdate();
			return affect;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
	}

	/**
	 * 执行SQL，返回第一行第一列
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	protected Object executeSqlScalar(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return null;
		}
		try {
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getObject(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
			return null;
		}
	}

	/**
	 * 批量执行SQL
	 * 
	 * @param sql
	 * @param before
	 * @return
	 */
	protected int[] executeSqlByBatch(String sql, Object[] param, ISQLOperate before) {
		try {
			if (before != null) {
				conn = JdbcUtils_C3P0.getConnection();
				ps = conn.prepareStatement(sql);
				before.operate(param, conn, ps, cs, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
		return null;
	}

	/**
	 * 初始化执行SQL
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	private boolean initExecuteSql(String sql, Object[] params) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
			return false;
		}
	}

	/*
	 * protected void executeProc() { try { cs.execute(); } catch (SQLException
	 * e) { e.printStackTrace(); } }
	 * 
	 * protected ResultSet executeProcResultSet() { try { rs =
	 * cs.executeQuery(); return rs; } catch (SQLException e) {
	 * e.printStackTrace(); return null; } finally { //
	 * JdbcUtils_C3P0.release(conn, st, rs); } }
	 * 
	 * protected void setObject(int i, Object obj) { try { cs.setObject(i, obj);
	 * } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * protected void registerOutParameter(int i, int type) { try {
	 * cs.registerOutParameter(i, type); } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 * 
	 * protected Object getObject(int i) { try { return cs.getObject(i); } catch
	 * (SQLException e) { return null; } }
	 * 
	 * protected boolean initExecuteProc(String sql) { try { conn =
	 * JdbcUtils_C3P0.getConnection(); cs = conn.prepareCall(sql); return true;
	 * } catch (SQLException e) { e.printStackTrace();
	 * JdbcUtils_C3P0.release(conn, ps, cs, rs); return false; } }
	 */

	/**
	 * 执行存储过程
	 * 
	 * @param sql
	 *            存储过程名称
	 * @param before
	 *            执行前回调
	 * @param beforeParam
	 *            执行前回调参数
	 * @param after
	 *            执行后回调
	 * @param afterParam
	 *            执行后回调参数
	 * @return
	 */
	protected Object executeProc(String sql, ISQLOperate before, Object[] beforeParam, ISQLOperate after, Object[] afterParam) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			cs = conn.prepareCall(sql);
			if (before != null) {
				before.operate(beforeParam, conn, ps, cs, rs);
			}
			rs = cs.executeQuery();
			Object obj = null;
			if (after != null) {
				obj = after.operate(afterParam, conn, ps, cs, rs);
			}
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
	}

	protected void release() {
		JdbcUtils_C3P0.release(conn, ps, cs, rs);
	}
}
