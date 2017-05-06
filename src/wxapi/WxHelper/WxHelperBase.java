package wxapi.WxHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import wxapi.Entity.View.Result2;
import wxapi.Entity.View.ResultCode;
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
		System.out.println(url);
		System.out.println(result);
		return result;
	}

	protected static String createPost(String url, String body, String accountnum, String module, String operation) {
		String result = HttpRequest.createPost(url, body);
		System.out.println(url);
		System.out.println(result);
		return result;
	}

	public static AccessToken getAccessToken(String accountnum, String appid, String secret) {
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
		Result2<AccessToken> result2 = initResult(strResult, AccessToken.class);
		if (result2.getIssuccess()) {
			token = result2.getParam();
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
			return token;
		}
		return null;
	}

	protected static <T> Result2<T> initResult(String result, Class<T> clazz) {
		Result2<T> result2 = new Result2<T>();
		result2.setIssuccess(false);
		if (result == null || result.isEmpty()) {
			result2.setCode(ResultCode.Null.ordinal());
			result2.setMsg("response is null");
			return result2;
		}
		Gson gson = new Gson();
		if (result.startsWith("{\"errcode\":")) {
			WxResult wxResult = gson.fromJson(result, WxResult.class);
			if (wxResult.getErrcode() == 0) {
				result2.setIssuccess(true);
			}
			result2.setCode(wxResult.getErrcode());
			result2.setMsg(wxResult.getErrmsg());
		} else {
			T t = gson.fromJson(result, clazz);
			if (t == null) {
				result2.setCode(ResultCode.Null.ordinal());
				result2.setMsg("t is null");
			} else {
				result2.setIssuccess(true);
				result2.setParam(t);
			}
		}
		return result2;
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
