<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
	});
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" href="/User/Add.html">添加</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="60">
					<col>
					<col width="150">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>角色</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>Liww</td>
						<td>系统管理员</td>
						<td style="text-align: center;"><a title="编辑" href="/User/Edit/1.html"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>XiaoSe</td>
						<td>微信管理员</td>
						<td style="text-align: center;"><a title="编辑" href="/User/Edit/2.html"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>4</td>
						<td>lirui</td>
						<td>微信管理员</td>
						<td style="text-align: center;"><a title="编辑" href="/User/Edit/4.html"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>3</td>
						<td>XiaoSeg</td>
						<td>微信副管理员</td>
						<td style="text-align: center;"><a title="编辑" href="/User/Edit/3.html"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>
