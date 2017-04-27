<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
		var form = layui.form();
		form.verify({
			accountcode : function(value) {
				if (!validater.validateStringIsLN(value, false, 1, 20)) {
					return '代码不能为空，只能字母数字组合，并且长度在1到20之间';
				}
			}
		});
	});
	PostDataToServ('postData', 'POST', '/officialaccount/modify.html', '', null, function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.Refresh();
	}, null);
</script>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<c:choose>
					<c:when test="${empty model}">
						<div class="layui-form-item">
							<label class="layui-form-label">公众号代码</label>
							<div class="layui-input-block">
								<input name="accountcode" lay-verify="required|accountcode" placeholder="公众号代码" autocomplete="off" class="layui-input" type="text" maxlength="20" value="${model.accountcode}">
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="accountcode" value="${model.accountcode}" />
					</c:otherwise>
				</c:choose>
				<div class="layui-form-item">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-block">
						<input name="accountname" lay-verify="required" placeholder="公众号名称" autocomplete="off" class="layui-input" type="text" maxlength="20" value="${model.accountname}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">公众号账号</label>
					<div class="layui-input-block">
						<input name="accountnum" lay-verify="required" placeholder="公众号账号" autocomplete="off" class="layui-input" type="text" maxlength="80" value="${model.accountnum}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">appid</label>
					<div class="layui-input-block">
						<input name="appid" lay-verify="required" placeholder="AppId" autocomplete="off" class="layui-input" type="password" maxlength="80" value="${model.appid}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">secret</label>
					<div class="layui-input-block">
						<input name="secret" lay-verify="required" placeholder="secret" autocomplete="off" class="layui-input" type="password" maxlength="80" value="${model.secret}">
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
