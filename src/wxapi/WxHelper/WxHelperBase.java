package wxapi.WxHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import avin.web.HttpRequest;

public class WxHelperBase {

	private static Object tokenLock = new Object();

	private static List<AccessToken> arrayToken = new ArrayList<AccessToken>();

	public WxHelperBase() {
	}

	public WxHelperBase(String accountnum, String appid, String secret) {

	}

	/**
	 * 发送Get请求
	 * 
	 * @param url
	 *            url
	 * @param accountnum
	 *            微信公众号
	 * @param module
	 *            模块
	 * @param operation
	 *            操作
	 * @return
	 */
	protected String sendGet(String url, String accountnum, String module, String operation) {
		String result = "";
		result = HttpRequest.sendGet(url);
		return result;
	}

	/**
	 * 发送Post请求
	 * 
	 * @param url
	 *            url
	 * @param body
	 *            请求数据
	 * @param accountnum
	 *            微信公众号
	 * @param module
	 *            模块
	 * @param operation
	 *            操作
	 * @return
	 */
	protected String sendPost(String url, String body, String accountnum, String module, String operation) {
		String result = "";
		result = HttpRequest.sendPost(url, body);
		return result;
	}

	/**
	 * 获取AccessToken
	 * 
	 * @param accountnum
	 * @param appid
	 * @param secret
	 * @return
	 */
	public Object GetAccessToken(String accountnum, String appid, String secret) {
		AccessToken token;
		for (int i = 0; i < arrayToken.size(); i++) {
			if (arrayToken.get(i).accountnum.equals(accountnum)) {
				token = arrayToken.get(i);
				long dateNow = new Date().getTime();
				if (dateNow > token.expire_time) {
					removeToken(token.accountnum);
					break;
				} else {
					return token;
				}
			}
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
		strUrl = String.format(strUrl, appid, secret);
		String strResult = sendGet(strUrl, accountnum, "GetAccessToken", "");

		return null;
	}

	private void addToken(AccessToken token) {
		synchronized (tokenLock) {
			arrayToken.add(token);
		}
	}

	private void removeToken(String accountnum) {
		synchronized (tokenLock) {
			for (int i = 0; i < arrayToken.size(); i++) {
				if (arrayToken.get(i).accountnum.equals(accountnum)) {
					arrayToken.remove(i);
					return;
				}
			}
		}
	}
}
