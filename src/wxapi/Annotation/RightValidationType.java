package wxapi.Annotation;

public class RightValidationType {

	/**
	 * 不需要权限
	 */
	public final static int NotNeed = 0;

	/**
	 * 登录权限
	 */
	public final static int NeedLogin = 1;

	/**
	 * URL权限
	 */
	public final static int NeedUrlRight = 2;

	/**
	 * 公众号权限
	 */
	public final static int NeedWxRight = 3;
}
