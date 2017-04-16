package wxapi.DataContext.Base;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetToBean {

	public static Object toBean(ResultSet rs, Class<?> clazz) {
		@SuppressWarnings("unchecked")
		List<Object> array = (List<Object>) toListBean(rs, clazz);
		if (array == null || array.size() == 0) {
			return null;
		}
		return array.get(0);
	}

	public static Object toListBean(ResultSet rs, Class<?> clazz) {
		try {
			List<Object> array = new ArrayList<Object>();
			ResultSetMetaData metadata = rs.getMetaData();
			int columnCount = metadata.getColumnCount();
			Field[] fields = clazz.getDeclaredFields();
			while (rs.next()) {
				Object bean = clazz.newInstance();
				for (int i = 0; i < columnCount; i++) {
					String coulmnName = metadata.getColumnName(i + 1).toLowerCase();
					for (Field f : fields) {
						String fieldName = f.getName().toLowerCase();
						if (fieldName.equals(coulmnName)) {
							Object coulmnData = rs.getObject(i + 1);
							f.setAccessible(true);
							f.set(bean, coulmnData);
							break;
						}
					}
				}
				array.add(bean);
			}
			return array.size() > 0 ? array : null;

		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
