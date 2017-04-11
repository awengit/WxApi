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
					<a class="layui-btn layui-btn-primary" href="/UserRight/AddGroup.html">添加</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="80">
					<col>
					<col width="100">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>ID</th>
						<th>标题</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>4</td>
						<td>微信用户管理</td>
						<td>5</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=4"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>3</td>
						<td>微信消息管理</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=3"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>公众号管理</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=1"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>后台权限管理</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=2"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>5</td>
						<td>后台用户管理</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=5"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>6</td>
						<td>用户设置</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑" href="/UserRight/EditGroup.html?id=6"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
				</tbody>
			</table>
			<div id="pagelist" style="text-align: center;"></div>
		</form>
	</div>
</body>
</html>
