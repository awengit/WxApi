<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>微信公众号管理后台</title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var element;
	$(document).ready(function() {
		SetSize();
		$('.layui-nav-child a').click(function() {
			var url = $(this).attr('href');
			if (url == undefined || url == null || url == '') {
				return false;
			}
			var html = $(this).html();
			AddTab(html, url);
			return false;
		});
		$(window).resize(function() {
			SetSize();
		});
	});
	layui.use('element', function() {
		element = layui.element();
	});
	function LogOut() {
		AjaxToServ(null, 'POST', '/manage/LogOut.html', '/manage/Login.html',
				null, null, null);
	}
	function SetSize() {
		var viewHeight = $(window).height();
		var viewWidth = $(window).width();
		var topHeight = $('.layui-top').height();
		var leftWidth = $('.layui-left').width();
		$('.layui-layout-admin').height(viewHeight + 'px');
		$('.layui-layout-admin').width(viewWidth + 'px');
		$('.layui-left').height((viewHeight - topHeight) + 'px');
		$('.layui-main').height((viewHeight - topHeight) + 'px');
		$('.layui-tab-content').height((viewHeight - topHeight - 41) + 'px');
		$('.layui-main').width((viewWidth - leftWidth) + 'px');
	}
	var newId = 1;
	function AddTab(title, url) {
		element.tabAdd('inner-page', {
			title : title,
			content : '<iframe class="ifMain" src=' + url + ' />',
			id : newId
		});
		element.tabChange('inner-page', newId);
		newId++;
	}
</script>
</head>
<body>
	<form id="form" class="layui-form layui-form-pane" action="" method="post">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-top layui-bg-black">
				<p>
					<a href="javascript:void(0)" title="退出" onclick="LogOut()">退出</a>
				</p>
			</div>
			<div class="layui-left layui-bg-black">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree">
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">微信用户管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="/focususer/list.html">关注用户列表</a>
								</dd>
								<dd>
									<a href="/group/list.html">公众号分组列表</a>
								</dd>
							</dl></li>
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">微信消息管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="/message/templatesend.html">发送模板消息</a>
								</dd>
							</dl></li>
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">公众号管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="/officialaccount/list.html">公众号列表</a>
								</dd>
							</dl></li>
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">后台权限管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="/userright/rolelist.html">角色列表</a>
								</dd>
								<dd>
									<a href="/userright/grouplist.html">权限组列表</a>
								</dd>
								<dd>
									<a href="/userright/rightlist.html">权限列表</a>
								</dd>
							</dl></li>
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">后台用户管理</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="/user/list.html">用户列表</a>
								</dd>
							</dl></li>
						<li class="layui-nav-item layui-nav-itemed"><a href="javascript:void(0);">用户设置</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="manage/alterpsw.html">修改密码</a>
								</dd>
							</dl></li>
					</ul>
				</div>
			</div>
			<div class="layui-main">
				<div class="layui-tab layui-tab-card" lay-filter="inner-page" lay-allowclose="true">
					<ul class="layui-tab-title">
					</ul>
					<div class="layui-tab-content"></div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
