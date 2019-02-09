var setting = {
	check: {
		enable: true
	},
	data: {
		key: {
			title: "title"
		},
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentid",
			rootPId: 0
		}
	}
};
var Jquery = $;
var zNodes;
layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form;
	var role_add = {
		zTreeObj: null,
		init: function() {
			role_add.initData();
			role_add.initSubmit();
		},
		initData: function() {
			zNodes = common.getMenus();
			role_add.zTreeObj = Jquery.fn.zTree.init($("#treeDemo"), setting, zNodes);
		},
		initSubmit: function() {
			//表单监听提交
			form.on('submit(formDemo)', function(data) {
				var dict_data = data.field;
				sessionStorage.removeItem("roles")
				var checkedNodes = role_add.zTreeObj.getCheckedNodes();
				var permissionIds = [];
				$.each(checkedNodes, function(idx, obj) {
					permissionIds.push(obj.id)
				});
				dict_data.permissionIds = permissionIds;
				dict_data = JSON.stringify(dict_data)
				$.ajax({
					url: common.IP + '/api/blog-admin/role',
					type: 'POST',
					data: dict_data,
					success: function(result, status, xhr) {
						layer.closeAll('loading');
						layer.msg(result.message, {
							icon: 1,
							time: 1000
						}, function() {
							window.history.back(-1);
						});
					}
				});
				return false;
			});
		}
	};
	$(function() {
		role_add.init();
	});
});