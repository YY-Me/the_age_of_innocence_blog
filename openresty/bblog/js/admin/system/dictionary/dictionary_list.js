layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common', 'table'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		table = layui.table;
	var dict = {
		init: function() {
			dict.initData();
			dict.initTableData();
			dict.bindEvent();
		},
		initData: function() {

		},
		initTableData: function() {
			//数据表格实例
			table.render({
				id: 'idTest',
				elem: '#demo',
				url: common.IP + '/api/blog-admin/dictionary',
				height: 'full-180',
				method: 'get',
				page: true,
				even: true,
				cols: [
					[{
						type: 'checkbox'
					}, {
						field: 'id',
						title: 'ID',
						sort: true
					}, {
						field: 'type',
						title: 'type'
					}, {
						field: 'k',
						title: 'key'
					}, {
						field: 'v',
						title: 'value',
						sort: true
					}, {
						field: 'createtime',
						title: 'createtime',
						sort: true
					}, {
						field: 'updatetime',
						title: 'updatetime',
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
					msgName: 'msg',
					countName: 'count',
					dataName: 'data'
				},
				done: function(res, curr, count) {
					layer.closeAll('loading');
				}
			});
		},
		bindEvent: function() {
			//监听提交
			form.on('submit(formDemo)', function(data) {
				table.reload('idTest', {
					where: data.field
				});
				return false;
			});
			//数据表格监听工具条(编辑、删除按钮)
			table.on('tool(test)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				if(layEvent === 'del') {
					var ts = data.type;
					var ids = [];
					ids.push(data.id)
					dict.deleteByUids(ts, ids);
				} else if(layEvent === 'edit') {
					window.location = "dictionary_update.html?id=" + data.id;
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
					var ids = [];
					var ts = "";
					$.each(list, function(idx, obj) {
						ids.push(obj.id)
						ts += " " + obj.type
					});
					dict.deleteByUids(ts, ids);
				}
			});
			//添加按钮事件
			$('#adduser').on('click', function() {
				window.location = "dictionary_add.html?type=add";
			});
		},
		deleteByUids: function(ts, ids) {
			layer.confirm('真的删除' + ts + "吗？", {
				icon: 3
			}, function(index) {
				layer.close(index);
				$.ajax({
					url: common.IP + '/api/blog-admin/dictionary',
					type: "DELETE",
					data: JSON.stringify(ids),
					success: function(result, status, xhr) {
						layer.closeAll('loading');
						sessionStorage.removeItem("sex");
						sessionStorage.removeItem("userStatus");
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
	}
	$(function() {
		dict.init();
	});
});