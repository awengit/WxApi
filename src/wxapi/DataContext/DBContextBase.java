package wxapi.DataContext;

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

	protected Object ExecuteSqlScalar(String sql, Object[] params) {
		if (!InitExecuteSql(sql, params)) {
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
			return null;
		} finally {
			JdbcUtils_C3P0.release(conn, st, rs);
		}
	}

	private boolean InitExecuteSql(String sql, Object[] params) {
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
			JdbcUtils_C3P0.release(conn, st, rs);
			return false;
		}
	}

	protected void ExecuteProc() {
		try {
			ct.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected ResultSet ExecuteProcResultSet() {
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

	protected void SetObject(int i, Object obj) {
		try {
			ct.setObject(i, obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void RegisterOutParameter(int i, int type) {
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

	protected boolean InitExecuteProc(String sql) {
		try {
			conn = JdbcUtils_C3P0.getConnection();
			ct = conn.prepareCall(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtils_C3P0.release(conn, ct, rs);
			return false;
		}
	}

	protected void Release() {
		JdbcUtils_C3P0.release(conn, st, rs);
	}
}
