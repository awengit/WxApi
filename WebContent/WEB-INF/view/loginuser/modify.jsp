<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var strAccounts = '${user.wxaccountcodes}';
	strAccounts = strAccounts == '' ? ',' : strAccounts;
	var layform;
	layui.use('form', function() {
		layform = layui.form();
		layform.on('checkbox(wxaccountcodes)', function(data) {
			if (data.elem.checked) {
				strAccounts += data.value + ',';
			} else {
				strAccounts = strAccounts.replace(',' + data.value + ',', ',');
			}
		});
		layform.verify({
			LoginName : function(value) {
				if (value.length < 4) {
					return '用户名不得少于4个字符';
				}
			},
			LoginPsw : function(value) {
				if (value.length < 6) {
					return '用户密码不得少于6个字符';
				}
			}
		});
		if (strAccounts != ',') {
			init();
		}
	});
	function init() {
		$('input[name="wxaccountcodes"]').each(function() {
			var value = $(this).val();
			if (strAccounts.indexOf(',' + value + ',') > -1) {
				$(this).attr('checked', 'checked');
			}
		});
		layform.render('checkbox');
	}
	PostDataToServ('postData', 'POST', '/loginuser/modify.html', null, function(data) {
		data.field.wxaccountcodes = strAccounts;
		return data;
	}, function(data) {
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
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<c:choose>
							<c:when test="${empty user}">
								<input name="loginname" lay-verify="loginname" placeholder="用户名" autocomplete="off" class="layui-input" type="text" maxlength="20">
							</c:when>
							<c:otherwise>
								<input name="loginname" lay-verify="loginname" placeholder="用户名" autocomplete="off" class="layui-input" type="text" maxlength="20" value="${user.loginname}" readonly="readonly">
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<c:choose>
					<c:when test="${empty user}">
						<div class="layui-form-item">
							<label class="layui-form-label">用户密码</label>
							<div class="layui-input-block">
								<input name="loginpsw" lay-verify="loginpsw" placeholder="用户密码" autocomplete="off" class="layui-input" type="text" maxlength="20">
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<input name="loginpsw" lay-verify="loginpsw" placeholder="用户密码" autocomplete="off" class="layui-input" type="hidden" maxlength="20" value="123456" readonly="readonly">
					</c:otherwise>
				</c:choose>
				<div class="layui-form-item">
					<label class="layui-form-label">角色</label>
					<div class="layui-input-inline">
						<select name="rolecode">
							<c:forEach var="r" items="${userRoles}">
								<option ${user.rolecode.equals(r.rolecode)?"selected=\"selected\"":""} value="${r.rolecode }">${r.title }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">微信公众号</label>
					<div class="layui-input-block">
						<c:forEach var="a" items="${accounts}">
							<input value="${a.accountcode }" name="wxaccountcodes" lay-skin="primary" lay-filter="wxaccountcodes" title="${a.accountname }" type="checkbox" />
						</c:forEach>
					</div>
				</div>
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
