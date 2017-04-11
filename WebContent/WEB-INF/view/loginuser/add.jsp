<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var strAccounts = ',';
	layui.use('form', function() {
		var form = layui.form();
		form.on('checkbox(WxAccounts)', function(data) {
			if (data.elem.checked) {
				strAccounts += data.value + ',';
			} else {
				strAccounts = strAccounts.replace(',' + data.value + ',', ',');
			}
		});
		form.verify({
			LoginName : function(value) {
				if (value.length < 4) {
					return '用户名不得少于4个字符';
				}
			},
			LoginPsw : function(value) {
				if (value.length < 6) {
					return '用户密码不得少于6个字符';
				}
			}
		});
	});
	PostDataToServ('postData', 'POST', '/User/AddEx.html', '/User/List.html',
			function(data) {
				data.field.WxAccounts = strAccounts;
				return data;
			}, null, null);
</script>

</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<input name="LoginName" lay-verify="LoginName" placeholder="用户名" autocomplete="off"
							class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用户密码</label>
					<div class="layui-input-block">
						<input name="LoginPsw" lay-verify="LoginPsw" placeholder="用户密码" autocomplete="off"
							class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色</label>
					<div class="layui-input-inline">
						<select name="RoleId">
							<option value="1">系统管理员</option>
							<option value="2">微信管理员</option>
							<option value="3">微信副管理员</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">微信公众号</label>
					<div class="layui-input-block">
						<input value="1" lay-filter="WxAccounts" name="WxAccounts" lay-skin="primary" title="测试账号"
							type="checkbox" /> <input value="2" lay-filter="WxAccounts" name="WxAccounts"
							lay-skin="primary" title="李锐-测试账号" type="checkbox" /> <input value="3"
							lay-filter="WxAccounts" name="WxAccounts" lay-skin="primary" title="1" type="checkbox" /> <input
							value="6" lay-filter="WxAccounts" name="WxAccounts" lay-skin="primary" title="2"
							type="checkbox" />
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="postData">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a class="layui-btn layui-btn-primary" href="/User/List.html">返回列表</a>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
