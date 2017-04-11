<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/Message/TemplateSendEx.html', null,
			null, null, null);
	var jsonTemplateEx = eval('[{"content":"{{first.DATA}}/u000a策略名称：{{invest_product.DATA}}/u000a操作风格：{{invest_style.DATA}}/u000a目前策略收益：{{invest_profit.DATA}}                 /u000a{{remark.DATA}}","deputy_industry":"证券|基金|理财|信托","example":"你所关注的“策略好股”有操作提示   /u000a策略名称：量化交易1号/u000a操作风格：短线/u000a目前策略收益：12.88%/u000a-xx证券股票专家运营团队-","primary_industry":"金融业","template_id":"HhRjQw50T2u4_il2JgEppgDuHbXH5OwjRzyQzjizgwM","title":"投资策略更新提醒","Id":2,"account_num":"gh_70ea95172802","add_date":"2017-03-31 00:00:00","add_datetime":"2017-03-31 15:08:29","is_valided":true,"AccountName":"李锐-测试账号"},{"content":"{{first.DATA}}/u000a客服经理：{{keyword1.DATA}}/u000a时间：{{keyword2.DATA}}/u000a{{remark.DATA}}","deputy_industry":"证券|基金|理财|信托","example":"您好，您已成功办理客服咨询服务/u000d/u000a客服经理：高端黑/u000d/u000a时间：2015-9-29 20：28/u000d/u000a感谢您的办理   点击进入查看客服详细信息>","primary_industry":"金融业","template_id":"VcL5qJNlE9YMG9BLPNsbmbOXqdEs0EafkbZiz1xO-is","title":"操作提醒","Id":3,"account_num":"gh_70ea95172802","add_date":"2017-03-31 00:00:00","add_datetime":"2017-03-31 15:08:29","is_valided":true,"AccountName":"李锐-测试账号"},{"content":"{{first.DATA}}/u000a股票代码：{{keyword1.DATA}}/u000a时间：{{keyword2.DATA}}/u000a{{remark.DATA}}","deputy_industry":"证券|基金|理财|信托","example":"您好，您关注的以下股票有异动：/u000d/u000a股票代码：000002/u000d/u000a时间：2015-05-21 9:40/u000d/u000a敬请留意，谢谢。","primary_industry":"金融业","template_id":"TcGLa-eej3ozaGZgmnLql6K0snM8nq7M8PnJsdopq-w","title":"股票异动提醒","Id":4,"account_num":"gh_70ea95172802","add_date":"2017-03-31 00:00:00","add_datetime":"2017-03-31 15:08:29","is_valided":true,"AccountName":"李锐-测试账号"},{"content":"{{first.DATA}}/u000a策略名称：{{keyword1.DATA}}/u000a操作数量：{{keyword2.DATA}}/u000a{{remark.DATA}}","deputy_industry":"证券|基金|理财|信托","example":"你所关注的“策略好股”有操作提示   /u000d/u000a策略名称：策略好股/u000d/u000a操作数量：20/u000d/u000a-xx证券股票专家运营团队-","primary_industry":"金融业","template_id":"mlvKXMuu6lmc6Ot_wnELRyMDnsh8BVVsWdRbwscHiz4","title":"投资策略更新提醒","Id":5,"account_num":"gh_70ea95172802","add_date":"2017-03-31 00:00:00","add_datetime":"2017-03-31 15:08:29","is_valided":true,"AccountName":"李锐-测试账号"},{"content":"{{first.DATA}}/u000a/u000a交易类型：{{type.DATA}} /u000a交易份额：{{share.DATA}}/u000a{{remark.DATA}}","deputy_industry":"","example":"","primary_industry":"","template_id":"YW5mMfJZ8NUDDEqfe8J_oz9MEqlGGgxp4qHGCqIAM3U","title":"Tester","Id":6,"account_num":"gh_ea6695e339cf","add_date":"2017-04-05 00:00:00","add_datetime":"2017-04-05 15:05:14","is_valided":true,"AccountName":"测试账号"}]');
	var jsonGroup = eval('[{"account_num":"gh_ea6695e339cf","count":0,"groupId":1,"id":0,"name":"未分组","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":2,"id":1,"name":"黑名单","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":3,"id":2,"name":"星标组","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":4,"id":102,"name":"Tester","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":5,"id":103,"name":"Tester23","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":4,"groupId":6,"id":104,"name":"男的","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":3,"groupId":7,"id":105,"name":"女的","AccountName":"测试账号"},{"account_num":"gh_70ea95172802","count":4,"groupId":8,"id":0,"name":"未分组","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":9,"id":1,"name":"黑名单","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":10,"id":2,"name":"星标组","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":5,"groupId":11,"id":114,"name":"测试人员","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":3,"groupId":12,"id":115,"name":"新用户","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":13,"id":116,"name":"已购买用户","AccountName":"李锐-测试账号"}]');
	var form;
	layui.use([ 'form', ], function() {
		form = layui.form();
		form.on('select(account_num)', function(data) {
			var account_num = $.trim(data.value);
			$('.template_id').html('<option value="">请选择</option>');
			$('.group').html('<option value="">请选择</option>');
			if (account_num != '') {
				for (var i = 0; i < jsonTemplateEx.length; i++) {
					if (jsonTemplateEx[i].account_num == account_num) {
						$('.template_id')
								.append(
										'<option  value="' + jsonTemplateEx[i].template_id + '">'
												+ jsonTemplateEx[i].title
												+ '</option>');
					}
				}
				for (var i = 0; i < jsonGroup.length; i++) {
					if (jsonGroup[i].account_num == account_num) {
						$('.group').append(
								'<option  value="' + jsonGroup[i].id + '">'
										+ jsonGroup[i].name + '</option>');
					}
				}
			}
			form.render('select');
		});
		form.on('select(template_id)', function(data) {
			for (var i = 0; i < jsonTemplateEx.length; i++) {
				if (jsonTemplateEx[i].template_id == data.value) {
					$('.template_content').html(jsonTemplateEx[i].content);
					InitTemplateParamInput(jsonTemplateEx[i].content);
					break;
				}
			}
		});
	});
	function InitTemplateParamInput(content) {
		var form = layui.form();
		var result = content.match(/{{\w{1,}.DATA}}/ig);
		$('.tp_input').html('');
		if (result == null) {
			return;
		}
		for (var i = 0; i < result.length; i++) {
			var param = result[i].replace('{{', '').replace('.DATA}}', '');
			var input = '<div class="layui-form-item"><label class="layui-form-label">参数：'
					+ param + '</label><div class="layui-input-block">';
			input += '<input name="tp_' + param + '" lay-verify="required" placeholder="' + param + '" autocomplete="off" class="layui-input" type="text"></div></div>';
			$('.tp_input').append(input);
		}
		form.render('input');
	}
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-inline">
						<select name="account_num" lay-verify="required" lay-filter="account_num">
							<option value="">请选择</option>
							<option value="gh_ea6695e339cf">测试账号</option>
							<option value="gh_70ea95172802">李锐-测试账号</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">模板名称</label>
					<div class="layui-input-inline">
						<select class="template_id" name="template_id" lay-verify="required" lay-filter="template_id">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">模板内容</label>
				<div class="layui-input-block">
					<textarea class="layui-textarea template_content" name="content"
						style="height: 40px; min-height: 40px;" readonly="readonly"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">Url</label>
				<div class="layui-input-inline">
					<input name="url" lay-verify="required" placeholder="url" autocomplete="off"
						class="layui-input" type="text">
				</div>
				<div class="layui-form-mid layui-word-aux">点击后跳转地址</div>
			</div>
			<div class="tp_input"></div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">用户分组</label>
					<div class="layui-input-inline">
						<select class="group" name="GroupId" lay-filter="group">
							<option value="">请选择</option>
						</select>
					</div>
					<div class="layui-form-mid layui-word-aux">不选择为全部发送</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="postData">发送</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
