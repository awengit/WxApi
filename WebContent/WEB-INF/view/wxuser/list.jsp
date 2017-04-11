<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="/WEB-INF/view/script.jsp"%>
<script type="text/javascript">
	var jsonGroup = eval('[{"account_num":"gh_ea6695e339cf","count":0,"groupId":1,"id":0,"name":"未分组","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":2,"id":1,"name":"黑名单","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":3,"id":2,"name":"星标组","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":4,"id":102,"name":"Tester","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":0,"groupId":5,"id":103,"name":"Tester23","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":4,"groupId":6,"id":104,"name":"男的","AccountName":"测试账号"},{"account_num":"gh_ea6695e339cf","count":3,"groupId":7,"id":105,"name":"女的","AccountName":"测试账号"},{"account_num":"gh_70ea95172802","count":4,"groupId":8,"id":0,"name":"未分组","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":9,"id":1,"name":"黑名单","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":10,"id":2,"name":"星标组","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":5,"groupId":11,"id":114,"name":"测试人员","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":3,"groupId":12,"id":115,"name":"新用户","AccountName":"李锐-测试账号"},{"account_num":"gh_70ea95172802","count":0,"groupId":13,"id":116,"name":"已购买用户","AccountName":"李锐-测试账号"}]');
	var layform;
	layui.use([ 'form', 'laypage' ], function() {
		layform = layui.form();
		InitGroup($('.account_num option[selected]').val(), '');
		var laypage = layui.laypage;
		laypage({
			cont : 'pagelist',
			curr : '1',
			pages : '1', //总页数    
			last : '1',
			groups : 10,//连续显示分页数
			jump : function(obj, first) {
				if (!first) {
					$('.PageIndex').val(obj.curr);
					var curform = document.getElementById('form');
					curform.submit();
				}
			}
		});
		layform.on('select(account_num)', function(data) {
			var account_num = $.trim(data.value);
			if (account_num != '') {
				InitGroup(account_num, '');
			}
			layform.render('select');
		});
	});
	function InitGroup(account_num, cur_id) {
		if (account_num == '') {
			return;
		}
		$('.group').html('<option value=" ">请选择</option>');
		for (var i = 0; i < jsonGroup.length; i++) {
			if (jsonGroup[i].account_num == account_num) {
				$('.group').append(
						'<option '
								+ (cur_id == jsonGroup[i].id + "" ? "selected"
										: "") + '  value="' + jsonGroup[i].id
								+ '">' + jsonGroup[i].name + '</option>');
			}
		}
		layform.render('select');
	}
	$(document)
			.ready(
					function() {
						$('.btn-search').click(function() {
							$('.PageIndex').val('1');
						});
						$('.btn_selectAll')
								.click(
										function() {
											var length = $('.layui-table .layui-unselect').length;
											var selectedlength = $('.layui-table .layui-form-onswitch').length;
											if (selectedlength < length) {
												$(
														'.layui-table .layui-unselect:not(.layui-form-onswitch)')
														.trigger("click");
												$(this).html('全部取消');
											} else {
												$(
														'.layui-table .layui-form-onswitch')
														.trigger("click");
												$(this).html('全部选中');
											}
										});
						$('.btn_alterRemark')
								.click(
										function() {
											if (!Check(0)) {
												return;
											}
											var accountnum = $(
													'.default .layui-table .layui-form-onswitch')
													.parent().parent().attr(
															'accountnum');
											var openid = $(
													'.default .layui-table .layui-form-onswitch')
													.parent().parent().attr(
															'openid');
											if (accountnum == ''
													|| openid == '') {
												return;
											}
											$('.alterRemark .accountNum').val(
													accountnum);
											$('.alterRemark .openid').val(
													openid);
											layui.use('layer', function() {
												layer.open({
													title : '修改备注',
													type : 1,
													content : $('.alterRemark')
												});
											});
										});
						$('.btn_moveToGroup')
								.click(
										function() {
											if (!Check(1)) {
												return;
											}
											var accountnum = $(
													'.layui-table .layui-form-onswitch')
													.parent().parent().attr(
															'accountnum');
											var openid = '';
											$(
													'.layui-table .layui-form-onswitch')
													.each(
															function() {
																openid += $(
																		this)
																		.parent()
																		.parent()
																		.attr(
																				'openid')
																		+ ',';
															});
											var newselect = '';
											$('.group option')
													.each(
															function() {
																newselect += '<option value="'
																		+ $(
																				this)
																				.attr(
																						'value')
																				.replace(
																						accountnum
																								+ '-',
																						'')
																		+ '">'
																		+ $(
																				this)
																				.html()
																		+ '</option>';
															});
											$('.moveGroup .toGroupId').html(
													newselect);
											layform.render('select');
											if (accountnum == ''
													|| openid == '') {
												return;
											}
											$('.moveGroup .accountNum').val(
													accountnum);
											$('.moveGroup .openids')
													.val(openid);
											layui.use('layer', function() {
												layer.open({
													title : '移动分组',
													type : 1,
													content : $('.moveGroup')
												});
											});
										});
					});
	PostDataToServ('alterRemark', 'POST', '/FocusUser/UpdateRemark.html', null,
			null, function(data) {
				var curform = document.getElementById('form');
				curform.submit();
			}, null);
	PostDataToServ('moveGroup', 'POST', '/Group/BatchMoveToGroup.html', null,
			null, function(data) {
				var curform = document.getElementById('form');
				curform.submit();
			}, null);
	function Check(flag) {
		var selectedlength = $('.layui-table .layui-form-onswitch').length;
		if (selectedlength == 0) {
			layui.use('layer', function() {
				layer.msg('请选择要操作的用户');
			});
			return false;
		}
		if (selectedlength > 1) {
			if (flag == 0) {//修改备注
				layui.use('layer', function() {
					layer.msg('不允许选择多个用户');
				});
				return false;
			}
		}
		return true;
	}
</script>

</head>
<body>
	<div class="inner-page">
		<form id="form" class="layui-form layui-form-pane default" action="" method="post">
			<input name="PageIndex" class="PageIndex" type="hidden" value="1">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">公众号名称</label>
					<div class="layui-input-inline">
						<select class="account_num" name="account_num" lay-verify="required" lay-filter="account_num">
							<option selected=selected value="gh_ea6695e339cf">测试账号</option>
							<option value="gh_70ea95172802">李锐-测试账号</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">用户分组</label>
					<div class="layui-input-inline">
						<select class="group" name="GroupId" lay-filter="group">
							<option accountnum="" value=" ">请选择</option>
						</select>
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn btn-search" lay-submit="">查询</button>
				</div>
			</div>
			<div class="layui-form-item">
				<a title="全部选中" class="layui-btn layui-btn-primary layui-btn-small btn_selectAll">全部选中</a> <a
					title="修改备注" class="layui-btn layui-btn-primary layui-btn-small btn_alterRemark">修改备注</a> <a
					title="移动分组" class="layui-btn layui-btn-primary layui-btn-small btn_moveToGroup">移动分组</a>
			</div>
			<table class="layui-table">
				<colgroup>
					<col width="100">
					<col width="130">
					<col width="150">
					<col width="150">
					<col>
					<col width="80">
					<col width="280">
					<col width="100">
					<col width="100">
				</colgroup>
				<thead>
					<tr>
						<th style="text-align: center;">选中</th>
						<th>公众号名称</th>
						<th>微信分组</th>
						<th>昵称</th>
						<th>OpenId</th>
						<th>性别</th>
						<th>地区</th>
						<th>头像</th>
						<th style="text-align: center;">是否关注</th>
					</tr>
				</thead>
				<tbody>
					<tr openid="oIKZrxDbmOcK6aOIISxFHHkHSEL4" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>女的</td>
						<td>花生</td>
						<td>oIKZrxDbmOcK6aOIISxFHHkHSEL4</td>
						<td>女性</td>
						<td>Iceland</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/icoxn6PjBGIiaC26Z2T59Mj3ia0cfxr4eh0pxRRYTic7IypWQZhI7xQR6edWiaePAhaMlBUmqjL7VGwDTUicpz2VVbIRInSNZJXxXy/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxLlxOvX1o05TaGJ7wb7o2HQ" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>男的</td>
						<td>Dony (海大师)</td>
						<td>oIKZrxLlxOvX1o05TaGJ7wb7o2HQ</td>
						<td>男性</td>
						<td>China Guangdong Guangzhou</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/icoxn6PjBGIiaC26Z2T59MjxBSpqvJRzib9d9ibtPVdZsicGtCZvdQgdZjEuBs9TCXdCicsIk3A5Xz5YlJIKJwJyb0aFDCSLA1ia3JQ/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxABEs8ACXTbymlY58KXBi_E" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>男的</td>
						<td>伟</td>
						<td>oIKZrxABEs8ACXTbymlY58KXBi_E</td>
						<td>男性</td>
						<td>China Guangdong Guangzhou</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/icoxn6PjBGIiaC26Z2T59MjzqqIiaecHGFMJicQy6Y0kZZXNV1gLp3DJYrzdyr0MamyZZe7uRfoFevgElsBicXZqcB3dhbUKbWbnr/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxOtj0pZSPo-_Rntilt2Gv-k" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>女的</td>
						<td>穿堂風 &#176;</td>
						<td>oIKZrxOtj0pZSPo-_Rntilt2Gv-k</td>
						<td>女性</td>
						<td>China Guangdong Guangzhou</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/VibgXFMwtD25KXPE8ESvbwcetAR9jys2s0rkpl6LibhGw8SSm3icklM7O5rCpLRo7ExO7lgKVa4H2ZpiaJhOzBvyq3P3uiaEB0xvO/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxJ5XAGaAxWBhMjvKZh2yuAE" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>女的</td>
						<td>LCH</td>
						<td>oIKZrxJ5XAGaAxWBhMjvKZh2yuAE</td>
						<td>女性</td>
						<td>China Guangdong Guangzhou</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/sjxzHjuTbKDgnuz2icVQ2HM8TesU1NBXGOZPY8J5qJ6AogelLSx8gn9Ch1xPVPy5fcEb5kC4HDJvu4Trj0hHawZepmzLbLt3s/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxH8WJuH3Aq3zV_AToTR3szc" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>男的</td>
						<td>路人李</td>
						<td>oIKZrxH8WJuH3Aq3zV_AToTR3szc</td>
						<td>男性</td>
						<td>China Guangdong Guangzhou</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/Cp0Dgwt6e6Rrv23JyA3Y0FYHkmYibOS1m7jnVS4arj0A1sS5MVBjr3mDsln3FBH0dIUX6W13VkMzRTCKQYpqvRkpQIKvGNCMH/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
					<tr openid="oIKZrxAOtSIPcScNhBpiLqpPJI-Q" accountNum="gh_ea6695e339cf">
						<td><input type="checkbox" name="is_checked" lay-skin="switch" lay-text="是|否"></td>
						<td>测试账号</td>
						<td>男的</td>
						<td>K1mi</td>
						<td>oIKZrxAOtSIPcScNhBpiLqpPJI-Q</td>
						<td>男性</td>
						<td>China Guangdong Zhanjiang</td>
						<td><img width="50px" height="50px" alt=""
							src="http://wx.qlogo.cn/mmopen/VibgXFMwtD27mGspgp2nrpb2eial3RQNfpObD5lvq3j2icJbbEsQLMTibTfS1NICzCLPHSgqYnDKfc4s3k7Oe7z50LonnbBwCfEic/0" />
						</td>
						<td style="text-align: center;">是</td>
					</tr>
				</tbody>
			</table>
			<div id="pagelist" style="text-align: center;"></div>
		</form>
		<form class="layui-form layui-form-pane alterRemark" id="alterRemark" action="" method="post"
			style="display: none;">
			<div class="layui-field-box">
				<input type="hidden" value="" name="account_num" class="accountNum" /> <input type="hidden"
					value="" name="openid" class="openid" />
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input name="remark" lay-verify="required" placeholder="备注" autocomplete="off"
							class="layui-input" type="text" maxlength="20">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="alterRemark">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
		<form class="layui-form layui-form-pane moveGroup" id="moveGroup" action="" method="post"
			style="display: none;">
			<div class="layui-field-box">
				<input type="hidden" value="" name="account_num" class="accountNum" /> <input type="hidden"
					value="" name="openids" class="openids" />
				<div class="layui-form-item">
					<label class="layui-form-label">用户分组</label>
					<div class="layui-input-block">
						<select class="toGroupId" name="toGroupId" lay-verify="required">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="moveGroup">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</div>
		</form>
	</div>

</body>
</html>
