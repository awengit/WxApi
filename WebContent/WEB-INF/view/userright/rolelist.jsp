<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var layform;
	layui.use('form', function() {
	});
</script>
</head>
<body>
	<div class="inner-page">
		<div class="layui-form-item">
			<a class="layui-btn layui-btn-primary" href="/UserRight/AddRole.html">添加</a>
		</div>
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<table class="layui-table">
				<colgroup>
					<col width="100">
					<col width="200">
					<col>
					<col width="150">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>ID</th>
						<th>标题</th>
						<th>权限</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>系统管理员</td>
						<td>,32,5,15,16,12,13,14,9,10,11,7,2,8,26,25,24,4,1,30,31,6,3,27,23,22,21,17,18,28,29,19,20,33,</td>
						<td>999</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditRole.html?id=1"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>微信管理员</td>
						<td>,32,23,22,21,17,18,28,29,19,20,3,27,26,25,24,4,1,30,31,6,</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditRole.html?id=2"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>3</td>
						<td>微信副管理员</td>
						<td>,23,22,21,17,18,28,29,19,20,3,27,4,31,6,32,</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditRole.html?id=3"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
				</tbody>
			</table>
			<div id="pagelist" style="text-align: center;"></div>
		</form>
	</div>
</body>
</html>
