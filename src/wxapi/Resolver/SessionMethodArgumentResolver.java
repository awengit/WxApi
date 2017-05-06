package wxapi.Resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import wxapi.Annotation.SessionData;

public class SessionMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2, WebDataBinderFactory arg3) throws Exception {

		return null;
	}

	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		if (arg0.hasParameterAnnotation(SessionData.class)) {
			return true;
		}
		return false;
	}

}
