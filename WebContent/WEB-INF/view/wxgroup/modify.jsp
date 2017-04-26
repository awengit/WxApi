<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="wxapi.Entity.WxGroup"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/wxgroup/modify.html', null, null, function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.Reflush();
	}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<input name="accountcode" type="hidden" value="${accountcode}"> <input name="groupid" type="hidden" value="${groupid}">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">分组名称</label>
					<div class="layui-input-block">
						<input name="groupname" value="${group.groupname}" lay-verify="required" placeholder="分组名称" autocomplete="off" class="layui-input" type="text" maxlength="20">
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
