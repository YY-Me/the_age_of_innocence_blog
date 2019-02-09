layui.config({
	base: '../js/web/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		$ = common.$;
	var article_list = {
		init: function() {
			article_list.bindEvent();
			$('#article_nums').html($('.layui-collapse ul').children('li').length);
		},
		bindEvent: function() {
			var open = $('.content .layui-collapse .layui-colla-title');
			$('#open-all').click(function() {
				if($(this).hasClass('yes')) {
					//open all
					$.each(open, function(idx, obj) {
						if($(obj).next().hasClass('layui-show')) {
							$(obj).trigger('click')
						}
					});
					$(this).removeClass('yes')
					$(this).html("展开所有月份")
				} else {
					//close all
					$.each(open, function(idx, obj) {
						if(!$(obj).next().hasClass('layui-show')) {
							$(obj).trigger('click')
						}
					});
					$(this).addClass('yes')
					$(this).html("收缩所有月份")
				}
			});
		}
	};
	$(function() {
		article_list.init();
	});
});