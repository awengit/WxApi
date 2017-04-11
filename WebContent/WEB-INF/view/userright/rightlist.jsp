<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	layui.use('form', function() {
	});
	function Reflush() {
		var form = document.getElementById('form');
		form.submit();
	}
</script>
</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane" action="" method="post">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">分组</label>
					<div class="layui-input-inline">
						<select name="groupId">
							<option value=" ">请选择</option>
							<option value="4">微信用户管理</option>
							<option value="3">微信消息管理</option>
							<option value="1">公众号管理</option>
							<option value="2">后台权限管理</option>
							<option value="5">后台用户管理</option>
							<option value="6">用户设置</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="" lay-filter="postData">查询</button>
				</div>
				<div class="layui-inline">
					<a class="layui-btn layui-btn-primary"
						onclick="OpenWindowIframe('/UserRight/AddRight.html','400px','420px')">添加</a>
				</div>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="60">
					<col width="120">
					<col width="150">
					<col>
					<col width="130">
					<col width="80">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th>ID</th>
						<th>分组</th>
						<th>标题</th>
						<th>URL</th>
						<th style="text-align: center;">在导航栏显示</th>
						<th>排序</th>
						<th style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>23</td>
						<td>微信用户管理</td>
						<td>关注用户列表</td>
						<td>/focususer/list.html</td>
						<td style="text-align: center;">是</td>
						<td>7</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/23.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>22</td>
						<td>微信用户管理</td>
						<td>更改用户分组</td>
						<td>/group/batchmovetogroup.html</td>
						<td style="text-align: center;">否</td>
						<td>6</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/22.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>21</td>
						<td>微信用户管理</td>
						<td>更新用户备注</td>
						<td>/focususer/updateremark.html</td>
						<td style="text-align: center;">否</td>
						<td>5</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/21.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>17</td>
						<td>微信用户管理</td>
						<td>公众号分组列表</td>
						<td>/group/list.html</td>
						<td style="text-align: center;">是</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/17.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>18</td>
						<td>微信用户管理</td>
						<td>添加分组</td>
						<td>/group/add.html</td>
						<td style="text-align: center;">否</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/18.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>28</td>
						<td>微信用户管理</td>
						<td>添加分组（Post接口）</td>
						<td>/group/addex.html</td>
						<td style="text-align: center;">否</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/28.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>29</td>
						<td>微信用户管理</td>
						<td>编辑分组</td>
						<td>/group/edit</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/29.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>19</td>
						<td>微信用户管理</td>
						<td>编辑分组</td>
						<td>/group/edit</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/19.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>20</td>
						<td>微信用户管理</td>
						<td>删除分组</td>
						<td>/group/delete.html</td>
						<td style="text-align: center;">否</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/20.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>3</td>
						<td>微信消息管理</td>
						<td>发送模板消息</td>
						<td>/message/templatesend.html</td>
						<td style="text-align: center;">是</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/3.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>27</td>
						<td>微信消息管理</td>
						<td>发送模板消息（Post接口）</td>
						<td>/message/templatesendex.html</td>
						<td style="text-align: center;">否</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/27.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>26</td>
						<td>公众号管理</td>
						<td>更新所有模板</td>
						<td>/template/flush.html</td>
						<td style="text-align: center;">否</td>
						<td>7</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/26.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>25</td>
						<td>公众号管理</td>
						<td>更新所有分组</td>
						<td>/group/flush.html</td>
						<td style="text-align: center;">否</td>
						<td>6</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/25.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>24</td>
						<td>公众号管理</td>
						<td>更新所有用户</td>
						<td>/focususer/flush.html</td>
						<td style="text-align: center;">否</td>
						<td>5</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/24.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>4</td>
						<td>公众号管理</td>
						<td>公众号列表</td>
						<td>/officialaccount/list.html</td>
						<td style="text-align: center;">是</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/4.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>1</td>
						<td>公众号管理</td>
						<td>添加</td>
						<td>/officialaccount/add.html</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/1.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>30</td>
						<td>公众号管理</td>
						<td>添加（Post接口）</td>
						<td>/officialaccount/addex.html</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/30.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>31</td>
						<td>公众号管理</td>
						<td>编辑（Post接口）</td>
						<td>/officialaccount/edit</td>
						<td style="text-align: center;">否</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/31.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>6</td>
						<td>公众号管理</td>
						<td>编辑</td>
						<td>/officialaccount/edit</td>
						<td style="text-align: center;">否</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/6.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>33</td>
						<td>公众号管理</td>
						<td>删除</td>
						<td>/officialaccount/delete.html</td>
						<td style="text-align: center;">否</td>
						<td>0</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/33.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>12</td>
						<td>后台权限管理</td>
						<td>角色列表</td>
						<td>/userright/rolelist.html</td>
						<td style="text-align: center;">是</td>
						<td>12</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/12.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>13</td>
						<td>后台权限管理</td>
						<td>添加角色</td>
						<td>/userright/addrole</td>
						<td style="text-align: center;">否</td>
						<td>11</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/13.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>14</td>
						<td>后台权限管理</td>
						<td>编辑角色</td>
						<td>/userright/editrole</td>
						<td style="text-align: center;">否</td>
						<td>10</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/14.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>9</td>
						<td>后台权限管理</td>
						<td>权限组列表</td>
						<td>/userright/grouplist.html</td>
						<td style="text-align: center;">是</td>
						<td>8</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/9.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>10</td>
						<td>后台权限管理</td>
						<td>添加权限组</td>
						<td>/userright/addgroup</td>
						<td style="text-align: center;">否</td>
						<td>7</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/10.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>11</td>
						<td>后台权限管理</td>
						<td>编辑权限组</td>
						<td>/userright/editgroup</td>
						<td style="text-align: center;">否</td>
						<td>6</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/11.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>7</td>
						<td>后台权限管理</td>
						<td>权限列表</td>
						<td>/userright/rightlist.html</td>
						<td style="text-align: center;">是</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/7.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>2</td>
						<td>后台权限管理</td>
						<td>添加权限</td>
						<td>/userright/addright</td>
						<td style="text-align: center;">否</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/2.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>8</td>
						<td>后台权限管理</td>
						<td>编辑权限</td>
						<td>/userright/editright</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/8.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>5</td>
						<td>后台用户管理</td>
						<td>用户列表</td>
						<td>/user/list.html</td>
						<td style="text-align: center;">是</td>
						<td>4</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/5.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>15</td>
						<td>后台用户管理</td>
						<td>添加</td>
						<td>/user/add</td>
						<td style="text-align: center;">否</td>
						<td>3</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/15.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>16</td>
						<td>后台用户管理</td>
						<td>编辑</td>
						<td>/user/edit</td>
						<td style="text-align: center;">否</td>
						<td>2</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/16.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
					<tr>
						<td>32</td>
						<td>用户设置</td>
						<td>修改密码</td>
						<td>manage/alterpsw.html</td>
						<td style="text-align: center;">是</td>
						<td>1</td>
						<td style="text-align: center;"><a title="编辑"
							onclick="OpenWindowIframe('/UserRight/AddRight/32.html','400px','420px')"
							class="layui-btn layui-btn-primary layui-btn-small">编辑</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
