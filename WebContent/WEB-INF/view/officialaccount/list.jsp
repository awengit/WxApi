<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信公众号列表</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	function Reflush() {
		var form = document.getElementById('mainform');
		form.submit();
	}
	function FlushUser(accountnum) {
		AjaxToServ({
			accountnum : accountnum
		}, 'POST', '/FocusUser/Flush.html', null, null, function(data) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('成功');
			});
		}, null);
	}
	function FlushGroup(accountnum) {
		AjaxToServ({
			accountnum : accountnum
		}, 'POST', '/Group/Flush.html', null, null, function(data) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('成功');
			});
		}, null);
	}
	function FlushTemplate(accountnum) {
		AjaxToServ({
			accountnum : account_num
		}, 'POST', '/Template/Flush.html', null, null, function(data) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('成功');
			});
		}, null);
	}
	function Delete(id) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						id : id
					}, 'POST', '/officialaccount/delete.html',
							'/officialaccount/list.html', null, null, null);
				},
				no : function(index, layero) {
				},
				cancel : function() {
				}
			});
		});
	}
</script>
</head>
<body>
	<div class="inner-page">
		<form id="mainform" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<a class="layui-btn layui-btn-primary"
					onclick="OpenWindowIframe('<%=request.getContextPath()%>/officialaccount/modify.html','500px','330px')">添加</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="80">
					<col>
					<col width="200">
					<col width="250">
					<col width="300">
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th>Id</th>
						<th>公众号名称</th>
						<th>公众号</th>
						<th>AppID</th>
						<th>Appsecret</th>
						<th style="text-align: center;">操作(刷新操作是从微信服务器更新到本地)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>测试账号</td>
						<td>gh_ea6695e339cf</td>
						<td></td>
						<td></td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/OfficialAccount/modify.html?id=1','600px','350px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf')" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>李锐-测试账号</td>
						<td>gh_70ea95172802</td>
						<td></td>
						<td></td>
						<td style="text-align: center;"><a title="刷新用户" onclick="FlushUser('gh_70ea95172802')"
							class="layui-btn layui-btn-primary layui-btn-small">刷新用户</a> <a title="刷新分组"
							onclick="FlushGroup('gh_70ea95172802')" class="layui-btn layui-btn-primary layui-btn-small">刷新分组</a>
							<a title="刷新模板" onclick="FlushTemplate('gh_70ea95172802')"
							class="layui-btn layui-btn-primary layui-btn-small">刷新模板</a> <a title="编辑"
							onclick="OpenWindowIframe('/OfficialAccount/Edit.html?account_num=gh_70ea95172802','600px','350px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_70ea95172802')" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
