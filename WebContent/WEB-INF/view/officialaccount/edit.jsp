<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/OfficialAccount/EditEx.html',
			'/OfficialAccount/List.html', null, function(data) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				parent.Reflush();
			}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<input name="Id" autocomplete="off" type="hidden" value="1" maxlength="5">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-block">
						<input name="AccountName" lay-verify="required" placeholder="公众号名称" value="测试账号"
							autocomplete="off" class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">公众号账号</label>
					<div class="layui-input-block">
						<input name="AccountNum" readonly="" lay-verify="required" placeholder="公众号账号"
							value="gh_ea6695e339cf" autocomplete="off" class="layui-input" type="text" maxlength="80">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">AppId</label>
					<div class="layui-input-block">
						<input name="AppID" lay-verify="required" placeholder="AppId" value="wx4fbe4f113e5a0f5e"
							autocomplete="off" class="layui-input" type="password" maxlength="80">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">Appsecret</label>
					<div class="layui-input-block">
						<input name="Appsecret" lay-verify="required" placeholder="Appsecret"
							value="5f2d349688b0bc74516efb9dc3049fbd" autocomplete="off" class="layui-input"
							type="password" maxlength="80">
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
	</div>
</body>
</html>
