layui.config({
	base: '../js/'
}).extend({
	common: '/web/common',
	edit: '/common/edit'
}).use(['common', 'edit', 'flow'], function() {
	var common = layui.common,
		layer = common.layer,
		$ = common.$,
		edit = layui.edit,
		flow = layui.flow;
	var leave_msg = {
		toId: null,
		parentId: null,
		init: function() {
			leave_msg.bindEvent();
			leave_msg.initEditor();
			$('#article_editor').val("");
		},
		bindEvent: function() {

			//提交回复
			$('.layui-submit').click(function() {
				//token，cookie验证登录
				if(!common.checkLogin()) {
					layer.msg("请先登录", {
						icon: 5,
						time: 2000
					});
					return;
				}
				var data = {};
				data.parentId = $.trim(leave_msg.parentId);
				data.toId = $.trim(leave_msg.toId);
				var content = $.trim($('.article_editor').val());
				if(content.length < 3) {
					layer.msg('留言内容不能小于3个非空格字符', {
						icon: 2,
						time: 2000
					});
					return;
				}
				//内容转义(后期由后台转义)
				data.content = edit.content(content);
				//按钮禁用
				$(".layui-submit").attr('disabled', true);
				setTimeout(function() {
					$(".layui-submit").attr('disabled', false);
				}, 10000);
				var url = "/api/blog-web/leaveMsg";
				common.ajaxJson(url, data, function(result, status, xhr) {
					$('#article_editor').val("")
					layer.msg("留言成功", {
						icon: 1,
						time: 1300
					}, function() {
						window.location.reload()
					});
				})
			});

			//评论中点击回复
			$('#comment_list').on('click', 'li .info .reply', function() {
				leave_msg.parentId = $.trim($(this).attr('parentId'));
				var toName = $(this).parent('.info').siblings('.pad-btm').children('.user_nickname');
				var nick_name = toName.attr('title');
				var text = '@' + nick_name + " ";
				leave_msg.toId = toName.attr('uid')
				$('#article_editor').val(text)
				location.hash = "nextinfo"
				$('#article_editor')[0].focus()
				$('html , body').animate({
					scrollTop: 0
				}, 0);
			});
			//加载更多
			flow.load({
				elem: '#comment_list',
				isAuto: false,
				done: function(page, next) {
					var lis = [];
					$.get(common.IP + '/api/blog-web/leaveMsg/list?page=' + page, function(res) {
						layer.closeAll('loading');
						var html = leave_msg.pHtml(res.data, false)
						lis.push(html);
						next(lis.join(''), page < res.pages);
					});
				}
			});
		},
		initEditor: function() {
			//建立编辑器
			edit.layEditor({
				elem: '.article_editor'
			});
		},
		pHtml: function(list,isChild) {
			var html = "";
			layui.each(list, function(idx, obj) {
				var parent = obj.parent;
				var childs = obj.child;
				//父类
				var clsss = ""
				if(isChild) {
					clsss = "class='childs'"
				}
				var c = ": ";
				if(parent.toName) {
					c = " <a style='color:#999'>回复</a><a class='user_nickname'> " + parent.toName + "</a>:";
				}
				html += "<li " + clsss + ">" +
					"<a class='user_header_img'><img alt='" + parent.fromName + "' title='" + parent.fromName + "' src='" + parent.fromHeadImg + "'/></a>" +
					"<div class='pad-btm'>" +
					"<p class='user_nickname' title='" + parent.fromName + "' uid='" + parent.fromId + "'>" + parent.fromName + "" + c + "</p>" +
					"<p class='min-font' uid='001'>来自" + parent.area + "客户端-" + common.diaplayTime(parent.createTime) + "</p>" +
					"</div>" +
					"<div class='comment_content layui-text'>" +
					parent.content +
					"</div>" +
					"<div class='info'>" +
					"<span>" + parent.createTime + "</span>" +
					"<a class='reply' parentId='" + parent.leaveId + "'>回复</a>" +
					"</div>" +
					"<div class='childs'>" +
					leave_msg.pHtml(childs, true) +
					"</div>" +
					"</li>";
			});
			return html;
		}
	};
	$(function() {
		leave_msg.init();
	});
});