<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var strRights = ',32,5,15,16,12,13,14,9,10,11,7,2,8,26,25,24,4,1,30,31,6,3,27,23,22,21,17,18,28,29,19,20,33,';
	if (strRights == '') {
		strRights = ',';
	}
	layui.use([ 'element', 'form' ], function() {
		var element = layui.element();
		var form = layui.form();
		form.render('checkbox');
		form.on('checkbox(rights)', function(data) {
			if (data.elem.checked) {
				strRights += data.value + ',';
			} else {
				strRights = strRights.replace(',' + data.value + ',', ',');
			}
		});
	});
	$(document)
			.ready(
					function() {
						$('.btn-selectpart')
								.click(
										function() {
											$(this)
													.parent()
													.next()
													.children(
															'.layui-form-checkbox:not(.layui-form-checked)')
													.trigger("click");
											return false;
										});
						$('.btn-unselectpart').click(
								function() {
									$(this).parent().next().children(
											'.layui-form-checked').trigger(
											"click");
									return false;
								});
					});
	PostDataToServ('postData', 'POST', '/UserRight/EditRoleEx.html',
			'/UserRight/RoleList.html', function(data) {
				data.field.UserRights = strRights;
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
			<input type="hidden" name="Id" value="1" />
			<div class="layui-field-box">
				<div class="layui-form-item">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input name="Title" lay-verify="required" placeholder="标题" autocomplete="off"
							class="layui-input" type="text" value="系统管理员" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input name="OrderNum" lay-verify="required" placeholder="排序" autocomplete="off"
							class="layui-input" type="text" value="999" maxlength="5">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-collapse">
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">微信用户管理 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="23"
									title="关注用户列表" lay-skin="primary" /><input name="UserRights" checked=""
									lay-filter="rights" type="checkbox" value="22" title="更改用户分组" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="21" title="更新用户备注"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="17" title="公众号分组列表" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="18" title="添加分组" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="28"
									title="添加分组（Post接口）" lay-skin="primary" /><input name="UserRights" checked=""
									lay-filter="rights" type="checkbox" value="29" title="编辑分组" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="19" title="编辑分组"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="20" title="删除分组" lay-skin="primary" />
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">微信消息管理 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="3"
									title="发送模板消息" lay-skin="primary" /><input name="UserRights" checked=""
									lay-filter="rights" type="checkbox" value="27" title="发送模板消息（Post接口）" lay-skin="primary" />
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">公众号管理 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="26"
									title="更新所有模板" lay-skin="primary" /><input name="UserRights" checked=""
									lay-filter="rights" type="checkbox" value="25" title="更新所有分组" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="24" title="更新所有用户"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="4" title="公众号列表" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="1" title="添加" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="30"
									title="添加（Post接口）" lay-skin="primary" /><input name="UserRights" checked=""
									lay-filter="rights" type="checkbox" value="31" title="编辑（Post接口）" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="6" title="编辑"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="33" title="删除" lay-skin="primary" />
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">后台权限管理 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="12"
									title="角色列表" lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="13" title="添加角色" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="14" title="编辑角色" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="9" title="权限组列表"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="10" title="添加权限组" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="11" title="编辑权限组" lay-skin="primary" /><input
									name="UserRights" checked="" lay-filter="rights" type="checkbox" value="7" title="权限列表"
									lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="2" title="添加权限" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="8" title="编辑权限" lay-skin="primary" />
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">后台用户管理 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="5"
									title="用户列表" lay-skin="primary" /><input name="UserRights" checked="" lay-filter="rights"
									type="checkbox" value="15" title="添加" lay-skin="primary" /><input name="UserRights"
									checked="" lay-filter="rights" type="checkbox" value="16" title="编辑" lay-skin="primary" />
							</div>
						</div>
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">
								<span class="group_title">用户设置 </span><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-selectpart">全部选中</a><a
									class="layui-btn layui-btn-primary layui-btn-mini btn-unselectpart">取消选中</a>
							</h2>
							<div class="layui-colla-content layui-show">
								<input name="UserRights" checked="" lay-filter="rights" type="checkbox" value="32"
									title="修改密码" lay-skin="primary" />
							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="postData">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<a class="layui-btn layui-btn-primary" href="/UserRight/RoleList.html">返回列表</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
