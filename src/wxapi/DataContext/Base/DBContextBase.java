package wxapi.DataContext.Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContextBase {

	private Connection conn = null;

	private PreparedStatement ps = null;

	private CallableStatement cs = null;

	private ResultSet rs = null;

	protected ResultSet executeSql(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return null;
		}
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
			return null;
		}
	}

	protected int executeSqlNonQuery(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return 0;
		}
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			int affect = ps.executeUpdate();
			return affect;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
	}

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

	protected int[] executeSqlByBatch(String sql, Object[][] params) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					for (int j = 0; j < params[i].length; j++) {
						ps.setObject(j + 1, params[i][j]);
					}
					ps.addBatch();
					if (i % 99 == 0 || i == params.length) {
						ps.executeBatch();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
		}
		return null;
	}

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

	protected void executeProc() {
		try {
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected ResultSet executeProcResultSet() {
		try {
			rs = cs.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// JdbcUtils_C3P0.release(conn, st, rs);
		}
	}

	protected void setObject(int i, Object obj) {
		try {
			cs.setObject(i, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void registerOutParameter(int i, int type) {
		try {
			cs.registerOutParameter(i, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Object getObject(int i) {
		try {
			return cs.getObject(i);
		} catch (SQLException e) {
			return null;
		}
	}

	protected boolean initExecuteProc(String sql) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			cs = conn.prepareCall(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, ps, cs, rs);
			return false;
		}
	}

	protected void release() {
		JdbcUtils_C3P0.release(conn, ps, cs, rs);
	}
}
