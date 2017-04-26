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
	function Delete(accountcode) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						accountcode : accountcode
					}, 'POST', '/officialaccount/delete.html', '/officialaccount/list.html', null, null, null);
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
				<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/officialaccount/modify.html','500px','400px')">添加公众号</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="150">
					<col>
					<col width="200">
					<col width="250">
					<col width="300">
					<col width="150">
				</colgroup>
				<thead>
					<tr>
						<th>公众号代码</th>
						<th>公众号名称</th>
						<th>公众号</th>
						<th>AppID</th>
						<th>Appsecret</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${account}">
						<tr>
							<td>${a.accountcode}</td>
							<td>${a.accountname}</td>
							<td>${a.accountnum}</td>
							<td>${a.appid}</td>
							<td>${a.secret}</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/officialaccount/modify.html?accountcode=${a.accountcode}','600px','350px')"
								class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除" onclick="Delete('${a.accountcode}')" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
