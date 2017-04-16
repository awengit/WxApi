<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wxapi.Entity.OfficialAccount"%>
<!DOCTYPE html>
<html>
<head>
<title>微信公众号列表</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	function Reflush() {
		window.location.reload(true);
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
	function Delete(accountnum) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : ['yes', 'no'],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						accountnum : accountnum
					}, 'POST', '<%=request.getContextPath()%>/officialaccount/delete.html',
							'<%=request.getContextPath()%>/officialaccount/list.html', null, null, null);
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
				<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('<%=request.getContextPath()%>/officialaccount/modify.html','500px','350px')">添加</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col>
					<col width="200">
					<col width="250">
					<col width="300">
					<col width="400">
				</colgroup>
				<thead>
					<tr>
						<th>公众号名称</th>
						<th>公众号</th>
						<th>AppID</th>
						<th>Appsecret</th>
						<th style="text-align: center;">操作(刷新操作是从微信服务器更新到本地)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${account}">
						<tr>
							<td>${a.accountname}</td>
							<td>${a.accountnum}</td>
							<td>${a.appid}</td>
							<td>${a.secret}</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('<%=request.getContextPath()%>/officialaccount/modify.html?accountnum=${a.accountnum}','600px','350px')"
								class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除" onclick="Delete('${a.accountnum}')" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
