<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
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
	function SetSize() {
		var viewHeight = $(window).height();
		var viewWidth = $(window).width();
		var topHeight = 0;//$('.layui-top').height();
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
		if (title == '退出登录') {
			window.location.href = url;
			return;
		}
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
			<!-- <div class="layui-top layui-bg-black">
				<p>
					<a href="javascript:void(0)" title="退出" onclick="LogOut()">退出</a>
				</p>
			</div> -->
			<div class="layui-left layui-bg-black">
				<div class="layui-side-scroll">
					<ul class="layui-nav layui-nav-tree">
						<%
							@SuppressWarnings("unchecked")
							List<wxapi.Entity.View.UserRight> rightArray = (List<wxapi.Entity.View.UserRight>) request.getAttribute("userright");
							if (rightArray != null) {
								int categoryid = -1;
								StringBuilder sb = new StringBuilder();
								for (wxapi.Entity.View.UserRight r : rightArray) {
									if (!r.getDisplayinbanner()) {
										continue;
									}
									if (r.getCategoryid() != categoryid) {
										if (categoryid != -1) {
											sb.append("</dl></li>");
										}
										sb.append("<li class=\"layui-nav-item layui-nav-itemed\"><a href=\"javascript:void(0);\">" + r.getRightgrouptitle() + "</a><dl class=\"layui-nav-child\">");
									}
									sb.append("<dd><a href=\"" + r.getUrl() + "\">" + r.getTitle() + "</a></dd>");
									categoryid = r.getCategoryid();
								}
								if (categoryid != -1) {
									sb.append("</dl></li>");
								}
								out.println(sb.toString());
							}
						%>
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
