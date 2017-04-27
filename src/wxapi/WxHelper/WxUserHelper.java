package wxapi.WxHelper;

import java.util.ArrayList;
import java.util.List;

import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxResult;
import wxapi.Entity.Wx.WxUser;
import wxapi.Entity.Wx.WxUserInfo;
import wxapi.Entity.Wx.WxUserInfoArray;

public class WxUserHelper extends WxHelperBase {

	private static String ModuleName = "WxUser";

	/**
	 * 获取用户openid
	 * 
	 * @param accessToken
	 * @param nextOpenid
	 * @return
	 */
	public Object getWxUser(AccessToken accessToken, String nextOpenid) {
		if (accessToken == null) {
			return null;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
		strUrl = String.format(strUrl, accessToken.access_token, nextOpenid);
		String strResult = createGet(accessToken.accountnum, strUrl, ModuleName, "getUser");
		return initResult(strResult, WxUser.class);
	}

	/**
	 * 批量获取用户信息
	 * 
	 * @param accessToken
	 * @param openids
	 * @return
	 */
	public List<WxUserInfo> getWxUserInfoArray(AccessToken accessToken, List<String> openids) {
		if (accessToken == null || openids == null || openids.size() == 0) {
			return null;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strTemp = "";
		String strResult = "";
		List<WxUserInfo> userInfoArray = new ArrayList<WxUserInfo>();
		StringBuilder sb = new StringBuilder();
		Object obj;
		for (int i = 0; i < openids.size(); i++) {
			sb.append("{\"openid\": \"" + openids.get(i) + "\",  \"lang\": \"zh-CN\"},");
			if ((i % 99) == 0 || i == (openids.size() - 1)) {
				strTemp = "{\"user_list\": [" + sb.toString().substring(0, sb.length() - 1) + "]}";
				strResult = createPost(strUrl, accessToken.accountnum, ModuleName, "getWxUserInfoArray", strTemp);
				obj = initResult(strResult, WxUserInfoArray.class);
				if (obj instanceof WxUserInfoArray) {
					userInfoArray.addAll(((WxUserInfoArray) obj).getUser_info_list());
				} else {
					System.out.println(strResult);
				}
				sb.delete(0, sb.length());
				strTemp = "";
			}
		}
		return userInfoArray;
	}

	/**
	 * 更新用户备注
	 * 
	 * @param accessToken
	 * @param openid
	 * @param remark
	 * @return
	 */
	public WxResult updateWxUserRemark(AccessToken accessToken, String openid, String remark) {
		WxResult wxResult = new WxResult();
		if (accessToken == null || openid == null) {
			wxResult.setErrmsg("accessToken或者openid 为空");
			return wxResult;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strJson = "{\"openid\":\"" + openid + "\",\"remark\":\"" + remark + "\"}";
		String strResult = createPost(strUrl, strJson, accessToken.accountnum, ModuleName, "updateRemark");
		return (WxResult) initResult(strResult, WxUser.class);
	}
}
