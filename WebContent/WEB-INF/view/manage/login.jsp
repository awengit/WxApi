<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
		var layform = layui.form();
		layform.verify({
			loginname : function(value) {
				if (!validater.validateStringIsPassword(value, false, 1, 20)) {
					return '用户名不能为空，并且字符长度在1到20之间';
				}
			},
			loginpsw : function(value) {
				if (!validater.validateStringIsPassword(value, false, 1, 20)) {
					return '用户密码不能为空，并且字符长度在1到20之间';
				}
			}
		});
	});
	PostDataToServ('postData', 'POST', '/manage/login.html', '/manage/index.html', null, null, null);
</script>
</head>
<body>
	<div class="login-panel">
		<fieldset class="layui-elem-field">
			<legend>登录</legend>
			<form class="layui-form layui-form-pane" action="">
				<div class="layui-field-box">
					<div class="layui-form-item">
						<label class="layui-form-label">用户名</label>
						<div class="layui-input-inline">
							<input name="loginname" lay-verify="required|loginname" placeholder="请输入用户名" autocomplete="off" class="layui-input" type="text" maxlength="20">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户密码</label>
						<div class="layui-input-inline">
							<input name="loginpsw" lay-verify="required|loginpsw" placeholder="请输入用户密码" autocomplete="off" class="layui-input" type="password" maxlength="20">
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</div>
			</form>
		</fieldset>
	</div>
</body>
</html>