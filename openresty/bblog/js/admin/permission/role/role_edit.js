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
	$(document).ready(function() {
		var id = common.getUrlParam("id")
		var menus;
		zNodes = common.getMenus("");
		initData(id);
		checkzNodes(zNodes);
		var zTreeObj = Jquery.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//表单监听提交
		form.on('submit(formDemo)', function(data) {
			$("button[lay-filter='formDemo']").attr('disabled', true);
			setTimeout(function() {
				$("button[lay-filter='formDemo']").attr('disabled', false);
			}, 10000);
			var dict_data = data.field;
			var checkedNodes = zTreeObj.getCheckedNodes();
			var permissionIds = [];
			$.each(checkedNodes, function(idx, obj) {
				permissionIds.push(obj.id)
			});
			dict_data.permissionIds = permissionIds;
			dict_data = JSON.stringify(dict_data)
			$.ajax({
				url: common.IP + '/api/blog-admin/role',
				type: 'PUT',
				data: dict_data,
				async: false,
				success: function(result, status, xhr) {
					common.refreshToken();
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
		//初始化页面数据
		function initData(id) {
			//用户数据
			showUser(id);
		}

		function showUser(id) {
			$.ajax({
				url: common.IP + '/api/blog-admin/role/' + id,
				type: 'GET',
				async: false,
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					var data = result.data;
					menus = data.menus;
					$("input[name='id']").val(data.id)
					$("input[name='name']").val(data.name)
					$("input[name='description']").val(data.description)
				}
			});
		}
		//zNodes
		function checkzNodes(data) {
			for(var j = 0; j < data.length; j++) {
				for(var i = 0; i < menus.length; i++) {
					if(data[j].id == menus[i].id) {
						zNodes[j].checked = true;
						break;
					} else {
						zNodes[j].checked = false;
					}
				}
			}
		}
	});
});