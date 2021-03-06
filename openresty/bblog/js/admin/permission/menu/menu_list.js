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
	var menu_list = {
		init: function() {
			menu_list.initMenu();
			menu_list.bindEvent();
			$("#menus").treetable({
				expandable: true
			});
		},
		initMenu: function() {
			var data = common.getMenus();
			$.each(data, function(idx, obj) {
				var type = "按钮";
				var href = obj.href;
				var ico = obj.ico;
				if(obj.type == 1) {
					type = "菜单"
				}
				if(href == null) {
					href = ""
				}
				if(ico == null) {
					ico = ""
				}
				var html = "<tr data-tt-id='" + obj.id + "' data-tt-parent-id='" + obj.parentid + "'>" +
					"<td>" + obj.name + "</td>" +
					"<td>" + ico + "</td>" +
					"<td>" + href + "</td>" +
					"<td>" + type + "</td>" +
					"<td>" + obj.permission + "</td>" +
					"<td>" + obj.sort + "</td>" +
					"<td><button class='layui-btn layui-btn-xs edit' data-id='" + obj.id + "'>编辑</button><button class='layui-btn layui-btn-xs layui-btn-danger delete' data-id='" + obj.id + "'>删除</button></td>" +
					"</tr>";
				$('#menus').children('tbody').append(html);
			});
		},
		bindEvent: function() {
			$('#adduser').on('click', function() {
				window.location = 'menu_add.html?type=add'
			});
			$('#menus tbody tr td').on('click', '.edit', function() {
				window.location = 'menu_edit.html?id=' + $(this).data('id')
			});
			$('#menus tbody tr td').on('click', '.delete', function() {
				var that = this;
				layer.confirm('真的删除吗？', {
					icon: 3
				}, function(index) {
					layer.close(index);
					$.ajax({
						url: common.IP + '/api/blog-admin/menus/' + $(that).data('id'),
						type: 'DELETE',
						success: function(result, status, xhr) {
							layer.closeAll('loading');
							layer.msg(result.message, {
								icon: 1,
								time: 1300
							}, function() {
								window.location.reload()
							});
						}
					});
				});
			});
		}
	};
	$(function() {
		menu_list.init();
	});
})