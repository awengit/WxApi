<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wxapi.Entity.CategoryFlag"%>
<!DOCTYPE html>
<html>
<head>
<title>类别标识列表</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	function Reflush() {
		window.location.reload(true);
	}
	function Delete(flag) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						flag : flag
					}, 'POST', '/categoryflag/delete.html', '/categoryflag/list.html', null, null, null);
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
				<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/categoryflag/modify.html','400px','300px')">添加类别标识</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col>
					<col width="200">
					<col width="100">
					<col width="150">
				</colgroup>
				<thead>
					<tr>
						<th>标题</th>
						<th>标识</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${array}">
						<tr>
							<td>${a.title}</td>
							<td>${a.flag}</td>
							<td>${a.ordernum}</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/categoryflag/modify.html?flag=${a.flag}','400px','200px')" class="layui-btn layui-btn-primary layui-btn-small">编辑</a>
								<a title="删除" onclick="Delete('${a.flag}')" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
