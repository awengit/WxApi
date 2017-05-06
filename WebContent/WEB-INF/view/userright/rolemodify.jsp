<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var strRights = '${role.userrights}';
	strRights = strRights == '' ? ',' : strRights;
	var layform;
	layui.use([ 'element', 'form' ], function() {
		var element = layui.element();
		layform = layui.form();
		layform.on('checkbox(right)', function(data) {
			if (data.elem.checked) {
				strRights += data.value + ',';
			} else {
				strRights = strRights.replace(',' + data.value + ',', ',');
			}
		});
		layform.verify({
			ordernum : function(value) {
				if (!validater.validateIntRange(value, 1, 99999)) {
					return '排序不能为空，只能为正整数，并且长度在1到99999之间';
				}
			},
			rolecode : function(value) {
				if (!validater.validateStringIsLN(value, false, 1, 20)) {
					return '角色代码不能为空，只能为数字字母组合，并且长度在1到20之间';
				}
			}
		});
		if (strRights != ',') {
			init();
		}
	});
	function init() {
		$('input[name="userrights"]').each(function() {
			var value = $(this).val();
			if (strRights.indexOf(',' + value + ',') > -1) {
				$(this).attr('checked', 'checked');
			}
		});
		layform.render('checkbox');
	}
	PostDataToServ('postData', 'POST', '/userright/rolemodify.html', '/userright/rolelist.html', function(data) {
		data.field.userrights = strRights;
		return data;
	}, null, null);
</script>
<style type="text/css">
.group_title {
	width: 100px;
	display: inline-block;
}
</style>
</head>
<body>
	<div class="inner-page">
		<form class="layui-form layui-form-pane" action="">
			<div class="layui-field-box">
				<c:choose>
					<c:when test="${not empty role}">
						<input type="hidden" name="rolecode" value="${role.rolecode }" />
					</c:when>
					<c:otherwise>
						<div class="layui-form-item">
							<label class="layui-form-label">角色代码</label>
							<div class="layui-input-block">
								<input value="" name="rolecode" lay-verify="required|rolecode" placeholder="角色代码" autocomplete="off" class="layui-input" type="text" maxlength="20">
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input value="${role.title}" name="title" lay-verify="required" placeholder="标题" autocomplete="off" class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input value="${role.ordernum}" name="ordernum" lay-verify="required|ordernum" placeholder="排序" autocomplete="off" class="layui-input" type="text" maxlength="5">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-collapse">
						<%
							@SuppressWarnings("unchecked")
							List<wxapi.Entity.View.UserRight> rightArray = (List<wxapi.Entity.View.UserRight>) request.getAttribute("rightArray");
							int categoryid = -1;
							StringBuilder sb = new StringBuilder();
							for (wxapi.Entity.View.UserRight r : rightArray) {
								if (r.getCategoryid() != categoryid) {
									if (categoryid != -1) {
										sb.append("</div></div>");
									}
									sb.append("<div class=\"layui-colla-item\"><h2 class=\"layui-colla-title\"><span class=\"group_title\">" + r.getRightgrouptitle() + "</span></h2><div class=\"layui-colla-content layui-show\">");
								}
								sb.append("<input name=\"userrights\" lay-filter=\"right\" type=\"checkbox\" value=\"" + r.getId() + "\" title=\"" + r.getTitle() + "\" lay-skin=\"primary\" />");
								categoryid = r.getCategoryid();
							}
							if (categoryid != -1) {
								sb.append("</div></div>");
							}
							out.println(sb.toString());
						%>
					</div>
				</div>
				<div class="layui-form-item">
					<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					<a class="layui-btn layui-btn-primary" href="/userright/rolelist.html">返回列表</a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
