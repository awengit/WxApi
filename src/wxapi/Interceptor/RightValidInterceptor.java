package wxapi.Interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import wxapi.Annotation.RightValidation;
import wxapi.Web.LoginUserInfo;

public class RightValidInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			Class<?> clazz = hm.getBeanType();
			Method m = hm.getMethod();
			if (clazz != null && m != null) {
				boolean isClazzAnnotation = clazz.isAnnotationPresent(RightValidation.class);
				boolean isMethodAnnotation = m.isAnnotationPresent(RightValidation.class);
				RightValidation valid = null;
				if (isMethodAnnotation) {
					valid = m.getAnnotation(RightValidation.class);
				} else if (isClazzAnnotation) {
					valid = clazz.getAnnotation(RightValidation.class);
				}
				// int type = valid == null ? RightValidType.NeedUrlRight :
				// valid.value();
				// if (type == RightValidType.NotNeed) {
				// return true;
				// }
				// LoginUserInfo info = null;
				// if (info == null) {
				// response.sendRedirect("/manage/login.html");
				// return false;
				// }
				// if (type == RightValidType.NeedLogin || type ==
				// RightValidType.NeedUrlRight || type ==
				// RightValidType.NeedWxRight) {
				// return true;
				// }
				// String url = request.getRequestURI().toLowerCase();
				// System.out.println(url);
			}
		}
		return true;
	}
}
