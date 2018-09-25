var E = window.wangEditor
var editor = new E('#editor')
editor.customConfig.uploadFileName = 'file'
editor.customConfig.uploadImgServer = '/api/blog-admin/article/upImg'
editor.customConfig.uploadImgMaxLength = 1
editor.customConfig.uploadImgHeaders = {
    'Authorization': sessionStorage.getItem("access_token") == null ? "" : "Bearer " + sessionStorage.getItem("access_token")
}
editor.create()
layui.use(['layer', 'element', 'form', 'upload'], function() {
	var layer = layui.layer,
		$ = layui.$,
		form = layui.form,
		element = layui.element,
		upload = layui.upload;
	initData()
	//监听提交
	form.on('submit(formDemo)', function(data) {
		var article_data = data.field;
		//内容 读取 html
		var html = editor.txt.html()
		// 此处进行 xss 攻击过滤
		//article_data.content = filterXSS(html);
		article_data.content = html;
		var labelIds = [];
		var classifyIds = [];
		$.each($('#lables').children('input:checked'), function(idx, obj) {
			labelIds.push($(this).val())
		});
		$.each($('#classifys').children('input:checked'), function(idx, obj) {
			classifyIds.push($(this).val())
		});
		article_data.labelIds = labelIds;
		article_data.classifyIds = classifyIds;
		if(article_data.istop) {
			article_data.istop = "1"
		}
		if(article_data.isvisible) {
			article_data.isvisible = "1"
		}
		article_data = JSON.stringify(article_data)
		submit(article_data)
		return false;
	});
	//执行实例
	var uploadInst = upload.render({
		elem: '#updateS',
		url: '/api/blog-admin/article/upImg',
		size: 5000,
		done: function(res) {
			//上传完毕回调
			$('.layui-upload-img').attr('src',res.data[0])
			$('#layui-upload-img').val(res.data[0])
			layer.closeAll('loading');
		},
		error: function() {
			//请求异常回调
			layer.closeAll('loading');
		}
	});

	function submit(article_data) {
		$.ajax({
			url: IP + '/api/blog-admin/article',
			type: 'POST',
			data: article_data,
			success: function(result, status, xhr) {
				layer.closeAll('loading');
				layer.msg(result.message, {
					icon: 1,
					time: 1300
				}, function() {
					$('#article_list', top.document).trigger('click')
					window.location.reload()
				});
			}
		});
	}

	function initData() {
		showLabelClassify("lables","classifys");
		form.render();
	}
});