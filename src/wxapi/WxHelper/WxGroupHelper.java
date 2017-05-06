package wxapi.WxHelper;

import java.util.List;

import wxapi.Entity.View.Result2;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxGroupArray;
import wxapi.Entity.Wx.WxGroupForCreate;
import wxapi.Entity.Wx.WxResult;

public class WxGroupHelper extends WxHelperBase {

	private static String ModuleName = "WxGroup";

	public Result2<WxGroupForCreate> create(AccessToken accessToken, String name) {
		if (accessToken == null || name == null || name.isEmpty()) {
			return null;
		}
		String strJson = "{\"group\":{\"name\":\"" + name + "\"}}";
		String strUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strResult = createPost(strUrl, strJson, accessToken.accountnum, ModuleName, "creaet");
		return initResult(strResult, WxGroupForCreate.class);
	}

	public Result2<WxGroupArray> get(AccessToken accessToken) {
		if (accessToken == null) {
			return null;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strResult = createGet(strUrl, accessToken.accountnum, ModuleName, "get");
		return initResult(strResult, WxGroupArray.class);
	}

	public Result2<WxResult> update(AccessToken accessToken, int id, String name) {
		if (accessToken == null || name == null || name.isEmpty()) {
			return null;
		}
		String strJson = "{\"group\":{\"id\":" + id + ",\"name\":\"" + name + "\"}}";
		String strUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strResult = createPost(strUrl, strJson, accessToken.accountnum, ModuleName, "update");
		return initResult(strResult, WxResult.class);
	}

	public Result2<WxResult> delete(AccessToken accessToken, int id) {
		if (accessToken == null) {
			return null;
		}
		String strJson = "{\"group\":{\"id\":" + id + "}}";
		String strUrl = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strResult = createPost(strUrl, strJson, accessToken.accountnum, ModuleName, "Delete");
		return initResult(strResult, WxResult.class);
	}

	public Result2<WxResult> batchMoveToGroup(AccessToken accessToken, List<String> openids, int groupId) {
		if (accessToken == null || openids == null || openids.size() <= 0) {
			return null;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strJson = "";
		StringBuilder sb = new StringBuilder();
		for (String openid : openids) {
			if (openid.isEmpty()) {
				continue;
			}
			sb.append("\"" + openid + "\",");
		}
		strJson = sb.toString();
		strJson = "{\"openid_list\":[" + strJson.substring(0, strJson.length() - 1) + "],\"to_groupid\":" + groupId + "}";
		String strResult = createPost(strUrl, strJson, accessToken.accountnum, ModuleName, "batchMoveToGroup");
		return initResult(strResult, WxResult.class);
	}
}
