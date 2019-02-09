layui.config({
	base: '../js/web/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		$ = common.$;
	var about = {
		init: function() {
			//网站运行时间
			about.show_date_time();
		},
		show_date_time: function() {
			setTimeout(about.show_date_time, 1000)
			BirthDay = new Date("09/24/2018 00:00:00");
			today = new Date();
			timeold = (today.getTime() - BirthDay.getTime());
			sectimeold = timeold / 1000;
			secondsold = Math.floor(sectimeold);
			msPerDay = 24 * 60 * 60 * 1000;
			e_daysold = timeold / msPerDay;
			daysold = Math.floor(e_daysold);
			e_hrsold = (e_daysold - daysold) * 24;
			hrsold = Math.floor(e_hrsold);
			e_minsold = (e_hrsold - hrsold) * 60;
			minsold = Math.floor((e_hrsold - hrsold) * 60);
			seconds = Math.floor((e_minsold - minsold) * 60);
			$("#yunxing").html(daysold + "天" + hrsold + "小时" + minsold + "分" + seconds + "秒");
		}
	};
	$(function() {
		about.init();
	});
});