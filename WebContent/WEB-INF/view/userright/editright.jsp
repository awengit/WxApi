<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	PostDataToServ('postData', 'POST', '/UserRight/EditRightEx.html',
			'/UserRight/RightList.html', null, function(data) {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				parent.Reflush();
			}, null);
</script>

</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<input type="hidden" name="Id" value="17" />
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">分组</label>
					<div class="layui-input-inline">
						<select name="GroupId">
							<option selected=&quot;selected&quot; value="4">微信用户管理</option>
							<option value="3">微信消息管理</option>
							<option value="1">公众号管理</option>
							<option value="2">后台权限管理</option>
							<option value="5">后台用户管理</option>
							<option value="6">用户设置</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input name="Title" lay-verify="required" placeholder="标题" autocomplete="off"
							class="layui-input" type="text" value="公众号分组列表" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">Url</label>
					<div class="layui-input-block">
						<input name="Url" lay-verify="required" placeholder="Url" autocomplete="off"
							class="layui-input" type="text" value="/group/list.html" maxlength="200">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">在导航栏显示</label>
					<div class="layui-input-block">
						<input name="DisplayInBanner" lay-skin="switch" title="在导航栏显示" type="checkbox"
							checked=&quot;checked&quot;>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-inline">
						<input name="OrderNum" lay-verify="required" placeholder="排序" autocomplete="off"
							class="layui-input" type="text" value="4" maxlength="5">
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
