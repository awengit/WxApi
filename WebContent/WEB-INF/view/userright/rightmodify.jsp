<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
		var layform = layui.form();
		layform.verify({
			ordernum : function(value) {
				if (!validater.validateIntRange(value, 1, 99999)) {
					return '排序不能为空，只能为正整数，并且长度在1到99999之间';
				}
			},
			url2 : function(value) {
				if (!value.match(/^(\/[a-zA-Z0-9]+){1,}\.html$/)) {
					return '请输入正确的URL';
				}
			}
		});
	});
	PostDataToServ('postData', 'POST', '/userright/rightmodify.html', null, null, function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.Refresh();
	}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<c:choose>
				<c:when test="${not empty right}">
					<input type="hidden" name="id" value="${right.id }" />
				</c:when>
			</c:choose>
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">权限分组</label>
					<div class="layui-input-block">
						<select name="categoryid">
							<c:forEach var="c" items="${category}">
								<option <c:if test="${c.id==right.categoryid}">selected="selected"</c:if> value="${c.id }">${c.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input value="${right.title}" name="title" lay-verify="required" placeholder="标题" autocomplete="off" class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">url</label>
					<div class="layui-input-block">
						<input value="${right.url}" name="url" lay-verify="required|url2" placeholder="url" autocomplete="off" class="layui-input" type="text" maxlength="100">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">在导航栏显示</label>
					<div class="layui-input-block">
						<input ${right.displayinbanner?"checked=\"checked\"":"" } name="displayinbanner" lay-skin="switch" title="在导航栏显示" type="checkbox">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input value="${right.ordernum}" name="ordernum" lay-verify="required|ordernum" placeholder="排序" autocomplete="off" class="layui-input" type="text" value="1" maxlength="5">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
