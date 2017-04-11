<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/UserRight/AddGroupEx.html',
			'/UserRight/GroupList.html', null, null, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input name="Title" lay-verify="required" placeholder="标题" autocomplete="off"
							class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input name="OrderNum" lay-verify="required" placeholder="排序" autocomplete="off"
							class="layui-input" type="text" value="1" maxlength="5">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a class="layui-btn layui-btn-primary" href="/UserRight/GroupList.html">返回列表</a>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
