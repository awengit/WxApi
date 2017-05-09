<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wxapi.Entity.WxGroup"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use([ 'layer', 'form' ], function() {
		var form = layui.form();
	});
	function Reflush() {
		var form = document.getElementById('form');
		form.submit();
	}
	function Delete(accountcode, groupid) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						accountcode : accountcode,
						groupid : groupid,
					}, 'POST', '/wxgroup/delete.html', null, null, function(data) {
						Reflush();
					}, null);
				},
				no : function(index, layero) {
				},
				cancel : function() {
				}
			});
		});
	};
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">公众号</label>
					<div class="layui-input-inline">
						<select name=accountcode lay-verify="required">
							<c:forEach var="a" items="${account}">
								<option <c:if test="${a.accountcode==accountcode}">selected="selected"</c:if> value="${a.accountcode }">${a.accountname }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="postData">查询</button>
				</div>
			</div>
			<div class="layui-form-item">
				<a class="layui-btn layui-btn-primary" onclick="OpenWindowIframe('/wxgroup/modify.html?accountcode=${accountcode}','400px','190px')">添加</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="200">
					<col width="160">
					<col>
					<col width="160">
					<col width="140">
				</colgroup>
				<thead>
					<tr>
						<th>公众号名称</th>
						<th>微信分组ID</th>
						<th>分组名称</th>
						<th>分组用户数</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="g" items="${groups}">
						<tr>
							<td>${g.accountname}</td>
							<td>${g.groupid}</td>
							<td>${g.groupname}</td>
							<td>${g.usercount}</td>
							<td style="text-align: center;"><a title="编辑" onclick="OpenWindowIframe('/wxgroup/modify.html?accountcode=${g.accountcode}&groupid=${g.groupid}','400px','190px')"
								class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除" onclick="Delete('${g.accountcode}',${g.groupid})" class="layui-btn layui-btn-primary layui-btn-small">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
