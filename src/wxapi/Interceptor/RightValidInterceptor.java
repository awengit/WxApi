package wxapi.Interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import wxapi.Annotation.RightValidation;
import wxapi.Annotation.RightValidationType;
import wxapi.Controller.ControllerBase;
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
				int type = valid == null ? RightValidationType.NeedUrlRight : valid.value();
				if (type == RightValidationType.NotNeed) {
					return true;
				}
				LoginUserInfo userInfo = (LoginUserInfo) request.getSession().getAttribute("loginuserinfo");
				if (userInfo == null) {
					response.sendRedirect("/manage/login.html");
					return false;
				}
				ControllerBase.SetCurUserInfo(userInfo);
				if (type == RightValidationType.NeedLogin) {
					return true;
				}
				String url = request.getRequestURI().toLowerCase();
				boolean haveRight = false;
				for (wxapi.Entity.View.UserRight right : userInfo.userRight) {
					if (url.equals(right.getUrl())) {
						haveRight = true;
						break;
					}
				}
				if (!haveRight) {
					return false;
				}
				if (type == RightValidationType.NeedUrlRight) {
					return true;
				}
				return true;
			}
		}
		return true;
	}
}
