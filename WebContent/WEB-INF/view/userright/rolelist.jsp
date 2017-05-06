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
		var form = document.getElementById('form');
		form.submit();
	}
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" href="/userright/rolemodify.html">添加角色</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="120">
					<col>
					<col width="80">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>角色代码</th>
						<th>标题</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="r" items="${roleArray}">
						<tr>
							<td>${r.rolecode }</td>
							<td>${r.title }</td>
							<td>${r.ordernum }</td>
							<td style="text-align: center;"><a title="编辑" href="/userright/rolemodify.html?rolecode=${r.rolecode}" class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
