<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wxapi.Entity.Category"%>
<!DOCTYPE html>
<html>
<head>
<title>类别列表</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
	});
	function Reflush() {
		var form = document.getElementById('mainform');
		form.submit();
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
					}, 'POST', '/category/delete.html', '/category/list.html', null, null, null);
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
				<div class="layui-inline">
					<label class="layui-form-label">类别标识</label>
					<div class="layui-input-inline">
						<select name="flag" lay-verify="required">
							<c:forEach var="a" items="${arrayFlag}">
								<option <c:if test="${a.flag==flag}">selected="selected"</c:if> value="${a.flag }">${a.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="">查询</button>
				</div>
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/category/modify.html','500px','405px')">添加类别</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col>
					<col width="200">
					<col width="150">
				</colgroup>
				<thead>
					<tr>
						<th>标题</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${array}">
						<tr>
							<td><c:forEach begin="2" end="${a.grade}" step="1">--</c:forEach>${a.title}</td>
							<td>${a.ordernum}</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/category/modify.html?id=${a.id}','500px','355px')" class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a
								title="删除" onclick="Delete('${a.id}')" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
