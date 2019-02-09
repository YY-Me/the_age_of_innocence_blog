layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['table', 'common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		table = layui.table;
	var qqUser = {
		init: function() {
			qqUser.initTableData();
			qqUser.bindEvent();
		},
		initTableData: function() {
			//数据表格实例
			table.render({
				id: 'idTest',
				elem: '#demo',
				url: common.IP + '/api/blog-admin/qqUser',
				height: 'full-180',
				method: 'get',
				page: true,
				even: true,
				size: 'lg',
				cols: [
					[{
						type: 'checkbox'
					}, {
						field: 'openid',
						title: 'openid',
						hide: true
					}, {
						field: 'figureurlQq1',
						title: '头像',
						width: 100
					}, {
						field: 'nickname',
						title: '昵称'
					}, {
						field: 'area',
						title: '地区'
					}, {
						field: 'gender',
						title: '性别',
						width: 100
					}, {
						field: 'status',
						title: '状态',
						width: 100
					}, {
						field: 'updateTime',
						title: '最近登录时间'
					}, {
						fixed: 'right',
						title: '操作',
						align: 'center',
						toolbar: '#barDemo'
					}]
				],
				request: {
					pageName: 'page',
					limitName: 'pageSize'
				},
				response: {
					statusName: 'code',
					statusCode: 200,
					msgName: 'message',
					countName: 'count',
					dataName: 'data'
				},
				done: function(res, curr, count) {
					layer.closeAll('loading');
				},
				parseData: function(res) {
					return qqUser.parseResultData(res);
				}
			});
		},
		parseResultData: function(res) {
			var data = res.data;
			var paseData = [];
			$.each(data, function(idx, obj) {
				var qid = obj.openid;
				obj.figureurlQq1 = "<img src='" + obj.figureurlQq1 + "' class='layui-circle' />";
				//男女
				if(obj.gender == "男") {
					obj.gender = '<i class="layui-icon layui-icon-male" style="color:#1E9FFF"></i>';
				} else if(obj.gender == "女") {
					obj.gender = '<i class="layui-icon layui-icon-female" style="color:#FF5722"></i>';
				}
				//0:禁用，1:正常
				if(obj.status == 1) {
					obj.status = "<input openid='" + qid + "' lay-filter='status' type='checkbox' name='status' lay-skin='switch' lay-text='正常|禁用' checked>";
				} else if(obj.status == 0) {
					obj.status = "<input openid='" + qid + "' lay-filter='status' type='checkbox' name='status' lay-skin='switch' lay-text='正常|禁用'>";
				}
				paseData.push(obj);
			});
			return {
				"code": res.code,
				"message": res.message,
				"count": res.count,
				"data": paseData
			};
		},
		bindEvent: function() {
			//监听提交
			form.on('submit(formDemo)', function(data) {
				table.reload('idTest', {
					where: data.field
				});
				return false;
			});
			//数据表格监听工具条(查看、编辑、删除按钮)
			table.on('tool(test)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				if(layEvent === 'del') {
					var ts = data.nickname;
					var uids = [];
					uids.push(data.openid)
					qqUser.deleteByUids(ts, uids);
				}
			});
			//复选框批量操作
			table.on('checkbox(test)', function(obj) {
				var checkStatus = table.checkStatus('idTest');
				if(checkStatus.data.length > 0) {
					$('#deleteall').show()
				} else {
					$('#deleteall').hide()
				}
			});
			//更改状态（switch）
			form.on('switch(status)', function(data) {
				var obj = data.elem;
				var openid = obj.getAttribute("openid");
				var data = JSON.stringify({
					openid: openid,
					//0:禁用，1:正常
					status: obj.checked ? '1' : '0'
				})
				obj.setAttribute('disabled', 'disabled');
				form.render();
				qqUser.updateByCheckBox(obj, data)
			});
			//批量删除按钮
			$('#deleteall').click(function() {
				var checkStatus = table.checkStatus('idTest');
				if(checkStatus.data.length > 0) {
					var list = checkStatus.data;
					var uids = [];
					var ts = "";
					$.each(list, function(idx, obj) {
						uids.push(obj.openid)
						ts += " " + obj.nickname
					});
					qqUser.deleteByUids(ts, uids);
				}
			});
		},
		updateByCheckBox: function(obj, data) {
			$.ajax({
				url: common.IP + '/api/blog-admin/qqUser',
				type: "PUT",
				data: data,
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					layer.msg(result.message, {
						icon: 1,
						time: 1300
					}, function() {
						obj.removeAttribute('disabled', 'disabled');
						form.render();
					});
				}
			});
		},
		deleteByUids: function(ts, uids) {
			layer.confirm('真的删除' + ts + "吗？", {
				icon: 3
			}, function(index) {
				layer.close(index);
				$.ajax({
					url: common.IP + '/api/blog-admin/qqUser',
					type: "DELETE",
					data: JSON.stringify(uids),
					success: function(result, status, xhr) {
						layer.closeAll('loading');
						layer.msg(result.message, {
							icon: 1,
							time: 1300
						}, function() {
							table.reload('idTest', {});
						});
					}
				});
			});
		}
	};
	$(function() {
		qqUser.init();
	});
});