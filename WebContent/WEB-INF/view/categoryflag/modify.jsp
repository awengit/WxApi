<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="wxapi.Entity.CategoryFlag"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
		var form = layui.form();
		form.verify({
			flag : function(value) {
				if (!validater.validateStringIsLN(value, false, 1, 20)) {
					return '标识不能为空，只能字母数字组合，并且长度在1到20之间';
				}
			},
			ordernum : function(value) {
				if (!validater.validateIntRange(value, 1, 99999)) {
					return '排序不能为空，只能为正整数，并且长度在1到99999之间';
				}
			}
		});
	});
	PostDataToServ('postData', 'POST', '/categoryflag/modify.html', '', null, function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.Reflush();
	}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input name="title" lay-verify="required" placeholder="标题" autocomplete="off" class="layui-input" type="text" maxlength="20" value="${model.title}">
					</div>
				</div>
				<c:choose>
					<c:when test="${empty model}">
						<div class="layui-form-item">
							<label class="layui-form-label">标识</label>
							<div class="layui-input-block">
								<input name="flag" lay-verify="required|flag" placeholder="标识" autocomplete="off" class="layui-input" type="text" maxlength="80" value="${model.flag}">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">排序</label>
							<div class="layui-input-block">
								<input name="ordernum" lay-verify="required|ordernum" placeholder="排序" autocomplete="off" class="layui-input" type="text" maxlength="5" value="${model.ordernum}">
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="flag" value="${model.flag}" />
					</c:otherwise>
				</c:choose>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="postData">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
