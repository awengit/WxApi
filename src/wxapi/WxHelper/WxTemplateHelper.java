package wxapi.WxHelper;

import wxapi.Entity.View.Result2;
import wxapi.Entity.Wx.AccessToken;
import wxapi.Entity.Wx.WxTemplateArray;

public class WxTemplateHelper extends WxHelperBase {

	private static String ModuleName = "WxTemplate";

	public Result2<WxTemplateArray> getTemplate(AccessToken accessToken) {
		if (accessToken == null) {
			return null;
		}
		String strUrl = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
		strUrl = String.format(strUrl, accessToken.access_token);
		String strResult = createGet(strUrl, accessToken.accountnum, ModuleName, "getTemplate");
		return initResult(strResult, WxTemplateArray.class);
	}
}
