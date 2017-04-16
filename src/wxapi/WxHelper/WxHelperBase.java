package wxapi.WxHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxResult;

import com.google.gson.Gson;

import avin.web.HttpRequest;

public class WxHelperBase {

	private static Object tokenLock = new Object();

	private static List<AccessToken> arrayToken = new ArrayList<AccessToken>();

	public WxHelperBase() {
	}

	public WxHelperBase(String accountnum, String appid, String secret) {
		getAccessToken(accountnum, appid, secret);
	}

	protected static String createGet(String url, String accountnum, String module, String operation) {
		String result = HttpRequest.createGet(url);
		// System.out.println(url);
		// System.out.println(result);
		return result;
	}

	protected static String createPost(String url, String body, String accountnum, String module, String operation) {
		String result = "";
		result = HttpRequest.createPost(url, body);
		return result;
	}

	public static Object getAccessToken(String accountnum, String appid, String secret) {
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
		String strUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
		strUrl = String.format(strUrl, appid, secret);
		String strResult = createGet(strUrl, accountnum, "GetAccessToken", "");
		Object obj = initResult(strResult, AccessToken.class);
		if (obj instanceof AccessToken) {
			if (obj != null) {
				token = (AccessToken) obj;
				token.accountnum = accountnum;
				token.appid = appid;
				token.secret = secret;
				Date dateNow = new Date();
				token.get_time = dateNow.getTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateNow);
				cal.set(Calendar.SECOND, token.expires_in - 10);
				token.expire_time = cal.getTime().getTime();
				addToken(token);
			}
			return obj;
		}
		return null;
	}

	protected static Object initResult(String result, Class<?> clazz) {
		if (result == null || result.isEmpty()) {
			return null;
		}
		Gson gson = new Gson();
		if (result.startsWith("{\"errcode\":")) {
			WxResult wxResult = gson.fromJson(result, WxResult.class);
			return wxResult;
		} else {
			Object obj = gson.fromJson(result, clazz);
			return obj;
		}
	}

	private static void addToken(AccessToken token) {
		synchronized (tokenLock) {
			arrayToken.add(token);
		}
	}

	private static void removeToken(String accountnum) {
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
