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
					<label class="layui-form-label">权限分组</label>
					<div class="layui-input-inline">
						<select name="categoryid">
							<c:forEach var="c" items="${category}">
								<option <c:if test="${c.id==categoryid}">selected="selected"</c:if> value="${c.id }">${c.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="postData">查询</button>
				</div>
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/userright/rightmodify.html','500px','405px')">添加</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="120">
					<col width="150">
					<col>
					<col width="130">
					<col width="80">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>权限分组</th>
						<th>标题</th>
						<th>url</th>
						<th style="text-align: center;">在导航栏显示</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="r" items="${rightArray}">
						<tr>
							<td>${r.rightgrouptitle }</td>
							<td>${r.title }</td>
							<td>${r.url }</td>
							<td style="text-align: center;">${r.displayinbanner?"是":"否" }</td>
							<td>${r.ordernum }</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/userright/rightmodify.html?id=${r.id}','500px','405px')" class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
