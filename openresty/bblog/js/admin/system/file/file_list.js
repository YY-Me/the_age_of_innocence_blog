layui.config({
	base: '../../../js/admin/' //静态资源所在路径
}).extend({
	common: 'common' //公共模块
}).use(['common', 'laydate', 'table', 'upload'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		form = common.form,
		laydate = layui.laydate,
		table = layui.table,
		upload = layui.upload;
	var file = {
		init: function() {
			//设置当前目录
			$('#folder_address').html(common.getUrlParam('path').trim() == "" ? "/" : common.getUrlParam('path').trim());
			file.bindEvent();
			file.getFolder();
			file.initUpload();
			file.checkCheckBox();
		},
		bindEvent: function() {
			$('#upload').click(function() {
				layer.open({
					type: 1,
					title: '<b>上传文件</b>',
					area: ['600px', '340px'], //宽高
					content: $('#upload_content')
				});
			});
			$('#create').click(function() {
				//页面层
				layer.open({
					type: 1,
					title: '<b>新建目录</b>',
					area: ['600px', '340px'], //宽高
					content: $('#create_folder')
				});
				$('#create_folder_yes').click(function() {
					var create_folder_name = $('#create_folder_name').val().trim()
					if(create_folder_name === "") {
						layer.msg("请填写文件夹", {
							icon: 2,
							time: 1300
						});
						return false;
					}
					var path = common.getUrlParam('path').trim() == "" ? "" : common.getUrlParam('path').trim();
					file.createFolder(path + (create_folder_name) + "/");
				});
			});
			//删除
			$('#delete').click(function() {
				var c = $('#file_table tbody tr:not(.head) td').children('input:checked');
				console.log(c)
				var dels = [];
				$.each(c, function(idx, obj) {
					dels.push($(obj).val())
				});
				file.deleteChecked(dels);
			});
			$('#file_table tbody').on('click', 'tr:not(.head)', function() {
				var that = this;
				if($(that).hasClass('checked')) {
					$(that).children('td').eq(0).children('input').prop('checked', false)
					$(that).removeClass('checked')
				} else {
					$(that).children('td').eq(0).children('input').prop('checked', true)
					$(that).addClass('checked')
				}
				file.checkCheckBox();
			})
			//全选、非全选
			form.on('checkbox(selectAll)', function(data) {
				var tr = $('#file_table tbody tr:not(.head) td').children('input');
				if(data.elem.checked) {
					tr.prop('checked', true)
					$(tr).parent('td').parent('tr').addClass('checked')
				} else {
					tr.prop('checked', false)
					$(tr).parent('td').parent('tr').removeClass('checked')
				}
				file.checkCheckBox();
			});
			//选择点击事件
			$('#file_table tbody').on('click', 'tr:not(.head) td .filename', function() {
				var fileName = $(this).html().trim()
				if(common.checkHouZui(fileName)) {
					file.getFileInfo(fileName);
				}
			});
		},
		initUpload: function() {
			upload.render({
				elem: '#test10',
				headers: {
					'Authorization': localStorage.getItem("access_token") == null ? "" : "Bearer" + localStorage.getItem("access_token")
				},
				url: common.IP + "/api/file-center/file/upload_file",
				multiple: true,
				accept: 'file',
				number: 50,
				data: {
					prefix: $('#folder_address').html() == "/" ? "" : $('#folder_address').html()
				},
				done: function(res, index, upload) {
					layer.closeAll('loading');
					layer.msg("上传成功", {
						icon: 1,
						time: 2000
					}, function() {
						layer.closeAll();
						file.getFolder();
					});
				},
				error: function() {
					layer.closeAll('loading');
					layer.msg("上传异常", {
						icon: 2,
						time: 2000
					});
				}
			});
		},
		getFolder: function() {
			//获取folderName下的文件及文件夹
			var folderName = common.getUrlParam('path').trim();
			var folders = folderName.split("/");
			var hrefs = "<a href='file_list.html'>/</a>";
			$.each(folders, function(idx, obj) {
				if(obj != "") {
					hrefs += "<a href='file_list.html?path=" + obj + "/'>" + obj + "/</a>";
				}
			});
			var html = "";
			if(folderName.length > 0) {
				html += "<tr class='head'>" +
					"<td></td>" +
					"<td><i class='layui-icon layui-icon-return' onclick='javascript:window.history.back(-1);'></i><span class='filename'>" + hrefs + "</span></td>" +
					"<td></td><td></td><td></td>" +
					"</tr>";
			}
			$.ajax({
				url: common.IP + '/api/file-center/file',
				type: "GET",
				data: {
					folderName: folderName
				},
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					$.each(result.commonPrefixes, function(idx, obj) {
						html += "<tr><td><input type='checkbox' lay-skin='primary' value='" + obj + "'></td>" +
							"<td><i class='layui-icon layui-icon-file'></i><a href='file_list.html?path=" + obj + "' class='filename'>" + obj + "</a></td>" +
							"<td></td><td></td><td></td></tr>";
					});
					$.each(result.objectSummaries, function(idx, obj) {
						if(obj.size != 0) {
							html += "<tr><td><input type='checkbox' lay-skin='primary' value='" + obj.key + "'></td>" +
								"<td><i class='layui-icon layui-icon-file'></i><span class='filename'>" + obj.key + "</span></td>" +
								"<td>" + common.bytesToSize(obj.size) + "</td><td>" + obj.storageClass + "</td><td>" + obj.lastModified + "</td></tr>";
						}
					});
					$('#file_table tbody').html(html)
					form.render(); //更新全部
				}
			});
		},
		deleteChecked: function(dels) {
			layer.confirm('真的删除吗？', {
				icon: 3
			}, function(index) {
				layer.close(index);
				$.ajax({
					url: common.IP + '/api/file-center/file',
					type: "DELETE",
					data: JSON.stringify(dels),
					success: function(result, status, xhr) {
						layer.closeAll('loading');
						layer.msg("删除成功", {
							icon: 1,
							time: 2000
						}, function() {
							layer.closeAll();
							file.getFolder();
						});
					}
				});
			});
		},
		checkCheckBox: function() {
			var c = $('#file_table tbody tr:not(.head) td').children('input:checked');
			if(c.length > 0) {
				$('#delete').removeClass('layui-btn-disabled')
			} else {
				$('#delete').addClass('layui-btn-disabled')
			}
			form.render(); //更新全部
		},
		getFileInfo: function(fileName) {
			$.ajax({
				url: common.IP + '/api/file-center/file/get_info',
				type: "GET",
				data: {
					name: fileName
				},
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					var img = "<p>图片：<a target='view_window' href='" + result.url + "'><img src='" + result.url + "' style='max-height: 50px;'/></a></p>";
					var text = "<p>链接：<a target='view_window' href='" + result.url + "'><b>" + result.url + "</b></a></p>";
					layer.confirm(text + img, {
						btn: ['复制链接', '取消'],
						yes: function(index, layero) {
							window.clipboardData.setData("Text", result.url);
							layer.close(index);
						}
					}, function(index, layero) {

					});
				}
			});
		},
		createFolder: function(create_folder_name) {
			$.ajax({
				url: common.IP + '/api/file-center/file/create_folder',
				type: "POST",
				contentType: "application/x-www-form-urlencoded",
				data: {
					folderName: create_folder_name,
				},
				success: function(result, status, xhr) {
					layer.closeAll('loading');
					layer.msg("创建成功", {
						icon: 1,
						time: 2000
					}, function() {
						layer.closeAll();
						file.getFolder();
					});
				}
			});
		}
	};
	$(function() {
		file.init();
	})
});