<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use([ 'layer', 'form' ], function() {
		var form = layui.form();
	});
	function Reflush() {
		var form = document.getElementById('form');
		form.submit();
	}
	function Delete(accountNum, id) {
		layui.use('layer', function() {
			layer.open({
				title : '删除',
				content : '确定删除？',
				btn : [ 'yes', 'no' ],
				yes : function(index, layero) {
					layer.close(index);
					AjaxToServ({
						account_num : accountNum,
						id : id,
						name : 'delete'
					}, 'POST', '/Group/Delete.html', null, null,
							function(data) {
								Reflush();
							}, null);
				},
				no : function(index, layero) {
				},
				cancel : function() {
				}
			});
		});
	};
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">公众号</label>
					<div class="layui-input-inline">
						<select name="account_num">
							<option selected=&quot;selected&quot; value="gh_ea6695e339cf">测试账号</option>
							<option value="gh_70ea95172802">李锐-测试账号</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="postData">查询</button>
				</div>
			</div>
			<div class="layui-form-item">
				<a class="layui-btn layui-btn-primary"
					onclick="OpenWindowIframe('/Group/Add.html','400px','250px')">添加</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="120">
					<col>
					<col width="200">
					<col width="200">
					<col width="140">
				</colgroup>
				<thead>
					<tr>
						<th>微信分组ID</th>
						<th>公众号名称</th>
						<th>分组名称</th>
						<th>分组用户数</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>0</td>
						<td>测试账号</td>
						<td>未分组</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/0.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',0)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>测试账号</td>
						<td>黑名单</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/1.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',1)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>2</td>
						<td>测试账号</td>
						<td>星标组</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/2.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',2)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>102</td>
						<td>测试账号</td>
						<td>Tester</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/102.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',102)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>103</td>
						<td>测试账号</td>
						<td>Tester23</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/103.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',103)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>104</td>
						<td>测试账号</td>
						<td>男的</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/104.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',104)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
					<tr>
						<td>105</td>
						<td>测试账号</td>
						<td>女的</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/Group/Edit/105.html?account_num=gh_ea6695e339cf','400px','190px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a> <a title="删除"
							onclick="Delete('gh_ea6695e339cf',105)" class="layui-btn layui-btn-primary layui-btn-small">删除</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
