<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="wxapi.Entity.CategoryFlag"%>
<%@ page import="wxapi.Entity.Category"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var categoryJson = eval('${array}');
	var layform;
	layui.use('form', function() {
		layform = layui.form();
		<c:choose>
		<c:when test="${empty model}">
		InitParentId($('.flag option').eq(0).val(), '');
		</c:when>
		<c:otherwise>
		InitParentId('${model.flag}', '${model.parentid}');
		</c:otherwise>
		</c:choose>
		layform.verify({
			ordernum : function(value) {
				if (!validater.validateIntRange(value, 1, 99999)) {
					return '排序不能为空，只能为正整数，并且长度在1到99999之间';
				}
			},
			cvalue : function(value) {
				if (!validater.validateStringRang(value, true, 1, 100)) {
					return '类别值长度在1到100之间';
				}
			}
		});
		layform.on('select(flag)', function(data) {
			var flag = $.trim(data.value);
			if (flag != '') {
				InitParentId(flag, '');
			}
		});
	});
	PostDataToServ('postData', 'POST', '/category/modify.html', '', null, function(data) {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		parent.Reflush();
	}, null);
	function InitParentId(flag, parentid) {
		if (flag == null || flag == '') {
			return;
		}
		$('.parentid').html('<option value="0">一级类别</option>');
		if (categoryJson != null) {
			for (var i = 0; i < categoryJson.length; i++) {
				if (categoryJson[i].flag == flag) {
					$('.parentid').append(
							'<option ' + (parentid == categoryJson[i].id ? "selected" : "") + '  value="' + categoryJson[i].id + '">' + CreateGradeStr(categoryJson[i].grade) + categoryJson[i].title
									+ '</option>');
				}
			}
		}
		layform.render('select');
	}
	function CreateGradeStr(grade) {
		var str = '';
		for (var i = 2; i <= grade; i++) {
			str += '--';
		}
		return str;
	}
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
								<select class="flag" name="flag" lay-verify="required" lay-filter="flag">
									<c:forEach var="a" items="${arrayFlag}">
										<option <c:if test="${a.flag==flag}">selected="selected"</c:if> value="${a.flag }">${a.title }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="id" value="${model.id}" />
					</c:otherwise>
				</c:choose>
				<div class="layui-form-item">
					<label class="layui-form-label">父级类别</label>
					<div class="layui-input-block">
						<select class="parentid" name="parentid" lay-verify="required">
							<option value="0">一级类别</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input name="ordernum" lay-verify="required|ordernum" placeholder="排序" autocomplete="off" class="layui-input" type="text" maxlength="5" value="${model.ordernum}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">类别值</label>
					<div class="layui-input-block">
						<input name="cvalue" lay-verify="cvalue" placeholder="类别值" autocomplete="off" class="layui-input" type="text" maxlength="100" value="${model.cvalue}">
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
