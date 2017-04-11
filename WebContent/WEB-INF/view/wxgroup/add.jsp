<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/Group/AddEx.html', null, null,
			function(data) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				parent.Reflush();
			}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-block">
						<select name="account_num">
							<option value="gh_ea6695e339cf">测试账号</option>
							<option value="gh_70ea95172802">李锐-测试账号</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">分组名称</label>
					<div class="layui-input-block">
						<input name="name" lay-verify="required" placeholder="分组名称" autocomplete="off"
							class="layui-input" type="text" maxlength="25">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
