<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var wxgroupArray = [];
	var wxgroup;
	<c:forEach var="g" items="${group}">
	wxgroup = new Object();
	wxgroup.accountcode = '${g.accountcode}';
	wxgroup.groupid = '${g.groupid}';
	wxgroup.groupname = '${g.groupname}';
	wxgroupArray.push(wxgroup);
	</c:forEach>
	layui.use([ 'form', 'laypage' ], function() {
		var layform = layui.form();
		var laypage = layui.laypage;
		laypage({
			cont : 'pagelist',
			curr : '${where.pageindex}',
			pages : '${pagecount}', //总页数    
			last : '${pagecount}',
			groups : 10,//连续显示分页数
			jump : function(obj, first) {
				if (!first) {
					$('.pageindex').val(obj.curr);
					var curform = document.getElementById('form');
					curform.submit();
				}
			}
		});
		//查询
		layform.on('submit(search)', function(data) {
			$('.pageindex').val('1');
			return true;
		});
		//全选
		layform.on('checkbox(allChoose)', function(data) {
			var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
			child.each(function(index, item) {
				item.checked = data.elem.checked;
			});
			layform.render('checkbox');
		});
		//修改备注
		$('.btn-alterRemark').click(function() {
			var chooseLength = $('.default tbody .layui-unselect[class*=layui-form-checked]').length;
			if (chooseLength == 0) {
				layer.msg('请选中要操作的用户');
				return;
			}
			if (chooseLength > 1) {
				layer.msg('不允许选中多个用户');
				return;
			}
			$('.form-alterremark .accountcode').val($('.default tbody .layui-unselect[class*=layui-form-checked]').eq(0).parent().parent().attr('accountcode'));
			$('.form-alterremark .openid').val($('.default tbody .layui-unselect[class*=layui-form-checked]').eq(0).parent().parent().attr('openid'));
			$('.form-alterremark .remark-modify').val($('.default tbody .layui-unselect[class*=layui-form-checked]').eq(0).parent().parent().children('.remark').html());
			layer.open({
				title : '修改备注',
				type : 1,
				content : $('.form-alterremark')
			});
		});
		//移动分组
		$('.btn-moveToGroup').click(function() {
			var chooseLength = $('.default tbody .layui-unselect[class*=layui-form-checked]').length;
			if (chooseLength == 0) {
				layer.msg('请选中要操作的用户');
				return;
			}
			var accountcode = $('.default tbody .layui-unselect[class*=layui-form-checked]').eq(0).parent().parent().attr('accountcode');
			$('.form-movegroup .accountcode').val(accountcode);
			var openids = ',';
			$('.default tbody .layui-unselect[class*=layui-form-checked]').each(function() {
				openids += $(this).parent().parent().attr('openid') + ',';
			});
			$('.form-movegroup .openids').val(openids);
			var html = '';
			for (var i = 0; i < wxgroupArray.length; i++) {
				if (wxgroupArray[i].accountcode == accountcode) {
					html += '<option value="'+wxgroupArray[i].groupid+'">' + wxgroupArray[i].groupname + '</option>';
				}
			}
			$('.form-movegroup .togroupid').html(html);
			layform.render('select');
			layer.open({
				title : '移动分组',
				type : 1,
				content : $('.form-movegroup')
			});
		});
	});
	PostDataToServ('alterRemark', 'POST', '/wxuser/updateremark.html', null, null, function(data) {
		var curform = document.getElementById('form');
		curform.submit();
	}, null);
	PostDataToServ('moveGroup', 'POST', '/wxgroup/batchmovetogroup.html', null, null, function(data) {
		var curform = document.getElementById('form');
		curform.submit();
	}, null);
</script>
<style type="text/css">
.layui-form-pane .layui-form-checkbox {
	margin: 0px;
}
</style>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane default" action="" method="post">
			<input name="pageindex" class="pageindex" type="hidden" value="${where.pageindex}">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-inline">
						<select class="accountcode" name="accountcode" lay-verify="required" lay-filter="accountcode">
							<c:forEach var="a" items="${account}">
								<option ${a.accountcode.equals(where.accountcode)?"selected=\"selected\"":"" } value="${a.accountcode}">${a.accountname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">用户分组</label>
					<div class="layui-input-inline">
						<select class="groupid" name="groupid">
							<option value=" ">请选择</option>
							<c:forEach var="g" items="${group}">
								<option ${g.groupid.toString().equals(where.groupid)?"selected=\"selected\"":"" } value="${g.groupid}">${g.groupname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="search">查询</button>
				</div>
			</div>
			<div class="layui-form-item">
				<a title="修改备注" class="layui-btn layui-btn-primary layui-btn-small btn-alterRemark">修改备注</a> <a title="移动分组" class="layui-btn layui-btn-primary layui-btn-small btn-moveToGroup">移动分组</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="50">
					<col width="170">
					<col width="170">
					<col width="170">
					<col>
					<col width="60">
					<col width="60">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th><input name="" lay-skin="primary" lay-filter="allChoose" type="checkbox"></th>
						<th>微信分组</th>
						<th>昵称</th>
						<th>备注</th>
						<th>OpenId</th>
						<th>性别</th>
						<th>头像</th>
						<th style="text-align: center;">是否关注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="u" items="${wxuser}">
						<tr openid="${u.openid}" accountcode="${where.accountcode}">
							<td><input lay-skin="primary" type="checkbox"></td>
							<td>${u.groupname}</td>
							<td>${u.nickname}</td>
							<td class="remark">${u.remark}</td>
							<td>${u.openid}</td>
							<td><c:choose>
									<c:when test="${u.sex==1}">男</c:when>
									<c:when test="${u.sex==2}">女</c:when>
									<c:otherwise>未知</c:otherwise>
								</c:choose></td>
							<td><img width="40px" height="40px" alt="" src="${u.headimgurl}" /></td>
							<td style="text-align: center;">${u.subscribe==1?"是":"否"}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pagelist" style="text-align: center;"></div>
		</form>
		<form class="layui-form layui-form-pane form-alterremark" action="" method="post" style="display: none;">
			<div class="layui-field-box">
				<input type="hidden" value="" name="accountcode" class="accountcode" /> <input type="hidden" value="" name="openid" class="openid" />
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input name="remark" lay-verify="required" placeholder="备注" autocomplete="off" class="layui-input remark-modify" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" style="float: right;">
						<button class="layui-btn" lay-submit="" lay-filter="alterRemark">修改</button>
					</div>
				</div>
			</div>
		</form>
		<form class="layui-form layui-form-pane form-movegroup" action="" method="post" style="display: none;">
			<div class="layui-field-box">
				<input type="hidden" value="" name="accountcode" class="accountcode" /> <input type="hidden" value="" name="openids" class="openids" />
				<div class="layui-form-item">
					<label class="layui-form-label">用户分组</label>
					<div class="layui-input-block">
						<select class="togroupid" name="togroupid" lay-verify="required">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" style="float: right;">
						<button class="layui-btn" lay-submit="" lay-filter="moveGroup">移动</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
