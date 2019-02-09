layui.config({
	base: '../../js/admin/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['colorpicker', 'common'], function() {
	var common = layui.common,
		layer = common.layer,
		form = common.form,
		colorpicker = layui.colorpicker,
		$ = common.$;
	var class_label = {
		init: function() {
			class_label.initData();
			class_label.bindEvent();
			//颜色选择器
			colorpicker.render({
				elem: '#mycolorpicker',
				done: function(color) {
					$("#add_lable_classify input[name='color']").val(color)
				}
			});
			colorpicker.render({
				elem: '#mycolorpicker_1',
				done: function(color) {
					$("#upd_lable_classify input[name='color']").val(color)
				}
			});
		},
		bindEvent: function() {
			//编辑
			$("#lablesManager,#classifysManager").on('click', 'span i.layui-icon-edit', function() {
				//获取当前id，type
				var id = $(this).parent('span').data('id');
				var type = $(this).parent('span').attr('type');
				var name = $(this).parent('span').text();
				var color = $(this).parent('span').attr('color');
				form.val("upd_lable_classify", {
					"id": id,
					"name": name,
					"type": type,
					"color": color
				})
				layer.open({
					type: 1,
					area: ['500px', '300px'],
					title: ['编辑标签、分类'],
					content: $('#upd_lable_classify')
				});
			})

			//删除
			$("#lablesManager,#classifysManager").on('click', 'span i.layui-icon-close-fill', function() {
				//获取当前id，type
				var id = $(this).parent('span').data('id');
				var type = $(this).parent('span').attr('type');
				var name = $(this).parent('span').text();
				layer.confirm('真的删除' + name + "吗？", {
					icon: 3
				}, function(index) {
					layer.close(index);
					class_label.del(type, id);
				});
			})
			//添加
			$("#adduser").click(function() {
				layer.open({
					type: 1,
					area: ['500px', '300px'],
					title: ['添加标签、分类'],
					content: $('#add_lable_classify')
				});
			})
			//监听(添加)提交
			form.on('submit(addForm)', function(data) {
				var add_data = data.field;
				class_label.add_update(add_data);
				return false;
			});
			//监听(编辑)提交
			form.on('submit(updForm)', function(data) {
				var add_data = data.field;
				class_label.add_update(add_data);
				return false;
			});
		},
		initData: function() {
			$("#lablesManager").html("");
			$("#classifysManager").html("");
			$.ajax({
				url: common.IP + '/api/blog-admin/labelClassify',
				type: 'GET',
				async: false,
				success: function(result, status, xhr) {
					layer.closeAll();
					//标签
					$.each(result.data.label, function(idx, obj) {
						var html = '<span color="' + obj.color + '" data-id="' + obj.id + '" type="' + obj.type + '" class="layui-unselect" style="background:' + obj.color + '">' + obj.name + '</i><i class="layui-icon layui-icon-edit"></i><i class="layui-icon layui-icon-close-fill"></i></span>'
						$("#lablesManager").append(html)
					});
					//分类
					$.each(result.data.classify, function(idx, obj) {
						var html = '<span color="' + obj.color + '" data-id="' + obj.id + '" type="' + obj.type + '" class="layui-unselect" style="background:' + obj.color + '">' + obj.name + '</i><i class="layui-icon layui-icon-edit"></i><i class="layui-icon layui-icon-close-fill"></i></span>'
						$("#classifysManager").append(html)
					});
				}
			});
		},
		add_update: function(user_data) {
			$.ajax({
				url: common.IP + '/api/blog-admin/labelClassify',
				type: 'POST',
				data: JSON.stringify(user_data),
				success: function(result, status, xhr) {
					layer.closeAll();
					class_label.initData();
					sessionStorage.removeItem("LabelClassify");
					layer.msg(result.message, {
						icon: 1,
						time: 1300
					});
				}
			});
		},
		del: function(type, id) {
			$.ajax({
				url: common.IP + '/api/blog-admin/labelClassify/' + type + "/" + id,
				type: 'DELETE',
				success: function(result, status, xhr) {
					layer.closeAll();
					sessionStorage.removeItem("LabelClassify");
					class_label.initData();
					layer.msg(result.message, {
						icon: 1,
						time: 1300
					});
				}
			});
		}
	};
	$(function() {
		class_label.init();
	});
});