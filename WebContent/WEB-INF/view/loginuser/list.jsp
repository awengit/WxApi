<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
	});
	function Refresh() {
		window.location.reload(true);
	}
	function Delete(loginname) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						loginname : loginname
					}, 'POST', '/loginuser/delete.html', '/loginuser/list.html', null, null, null);
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
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/loginuser/modify.html','600px','350px')">添加</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="200">
					<col>
					<col width="150">
				</colgroup>
				<thead>
					<tr>
						<th>角色</th>
						<th>用户名</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${users}">
						<tr>
							<td>${u.userroletitle }</td>
							<td>${u.loginname }</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/loginuser/modify.html?loginname=${u.loginname }','600px','300px')"
								class="layui-btn layui-btn-primary layui-btn-small">编辑</a><a title="编辑" onclick="Delete('${u.loginname }')" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>
