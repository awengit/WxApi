package wxapi.DataContext.Base;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBContextBase {

	private Connection conn = null;

	private PreparedStatement st = null;

	private CallableStatement ct = null;

	private ResultSet rs = null;

	protected ResultSet executeSql(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return null;
		}
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}
			}
			rs = st.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, st, ct, rs);
			return null;
		}
	}

	protected Object executeSqlScalar(String sql, Object[] params) {
		if (!initExecuteSql(sql, params)) {
			return null;
		}
		try {
			rs = st.executeQuery();
			if (rs.next()) {
				return rs.getObject(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, st, ct, rs);
			return null;
		}
	}

	private boolean initExecuteSql(String sql, Object[] params) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			st = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					st.setObject(i + 1, params[i]);
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, st, ct, rs);
			return false;
		}
	}

	protected void executeProc() {
		try {
			ct.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected ResultSet executeProcResultSet() {
		try {
			rs = ct.executeQuery();
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
			ct.setObject(i, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void registerOutParameter(int i, int type) {
		try {
			ct.registerOutParameter(i, type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Object getObject(int i) {
		try {
			return ct.getObject(i);
		} catch (SQLException e) {
			return null;
		}
	}

	protected boolean initExecuteProc(String sql) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ct = conn.prepareCall(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, st, ct, rs);
			return false;
		}
	}

	protected void release() {
		JdbcUtils_C3P0.release(conn, st, ct, rs);
	}
}
