<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/manage/LoginEx.html',
			'/manage/Index.html', null, null, null);
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
							<input name="LoginName" lay-verify="required" placeholder="请输入用户名" autocomplete="off"
								class="layui-input" type="text">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">用户密码</label>
						<div class="layui-input-inline">
							<input name="LoginPsw" lay-verify="required" placeholder="请输入用户密码" autocomplete="off"
								class="layui-input" type="password">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">验证码</label>
						<div class="layui-input-inline">
							<input name="checkcode" lay-verify="" placeholder="请输入验证码" autocomplete="off"
								class="layui-input" type="text">
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