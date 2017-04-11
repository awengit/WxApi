<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/Group/EditEx.html', null, null,
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
			<input name="id" type="hidden" value="2"> <input name="account_num" type="hidden"
				value="gh_ea6695e339cf">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">分组名称</label>
					<div class="layui-input-block">
						<input name="name" value="星标组" lay-verify="required" placeholder="分组名称" autocomplete="off"
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
