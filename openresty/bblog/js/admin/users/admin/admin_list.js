layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['laydate', 'table', 'common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		laydate = layui.laydate,
		table = layui.table;
	var adminUser = {
		init: function() {
			//用户状态select
			common.showDictSelect("userStatus", "userStatus", true);
			form.render('select');
			//执行一个laydate实例
			laydate.render({
				elem: '#start',
				max: 0
			});
			laydate.render({
				elem: '#end',
				max: 0
			});
			adminUser.initTableData();
			adminUser.bindEvent();
		},
		initTableData: function() {

			//数据表格实例
			table.render({
				id: 'idTest',
				elem: '#demo',
				url: common.IP + '/api/blog-admin/sysUser',
				height: 'full-180',
				method: 'get',
				page: true,
				even: true,
				cols: [
					[{
						type: 'checkbox'
					}, {
						field: 'uid',
						title: 'UID',
						hide: true
					}, {
						field: 'username',
						title: '用户名'
					}, {
						field: 'nickname',
						title: '昵称'
					}, {
						field: 'sex',
						title: '性别',
						width: 80
					}, {
						field: 'status',
						title: '状态',
						width: 100
					}, {
						field: 'createtime',
						title: '创建时间',
						sort: true
					}, {
						field: 'updatetime',
						title: '修改时间',
						sort: true
					}, {
						fixed: 'right',
						width: 200,
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
					return adminUser.parseResultData(res);
				}
			});
		},
		parseResultData: function(res) {
			var data = res.data;
			var paseData = [];
			var sexs = common.getDictionary("sex");
			var userStatus = common.getDictionary("userStatus");
			$.each(data, function(idx, obj) {
				//性别
				$.each(sexs, function(idx_1, obj_sex) {
					if(obj.sex == obj_sex.k) {
						obj.sex = obj_sex.v;
					}
				});
				//状态
				$.each(userStatus, function(idx_2, obj_status) {
					if(obj.status == obj_status.k) {
						obj.status = obj_status.v;
					}
				});
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
				if(layEvent === 'detail') {
					adminUser.viewUserDetail(data.uid);
				} else if(layEvent === 'del') {
					var ts = data.username;
					var uids = [];
					uids.push(data.uid)
					adminUser.deleteByUids(ts, uids);
				} else if(layEvent === 'edit') {
					window.location = "admin_update.html?uid=" + data.uid;
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
			//批量删除按钮
			$('#deleteall').click(function() {
				var checkStatus = table.checkStatus('idTest');
				if(checkStatus.data.length > 0) {
					var list = checkStatus.data;
					var uids = [];
					var ts = "";
					$.each(list, function(idx, obj) {
						uids.push(obj.uid)
						ts += " " + obj.username
					});
					adminUser.deleteByUids(ts, uids);
				}
			});
			//添加按钮事件
			$('#adduser').on('click', function() {
				window.location = 'admin_add.html?type=add'
			});
		},
		deleteByUids: function(ts, uids) {
			layer.confirm('真的删除' + ts + "吗？", {
				icon: 3
			}, function(index) {
				layer.close(index);
				$.ajax({
					url: common.IP + '/api/blog-admin/sysUser',
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
		},
		viewUserDetail: function(uid) {
			layer.open({
				type: 2,
				skin: 'layui-layer-molv',
				area: ['600px', '340px'],
				maxmin: true,
				content: 'admin_query.html?uid=' + uid
			});
		}
	};
	$(function() {
		adminUser.init();
	});
});