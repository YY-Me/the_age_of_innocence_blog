layui.use(['layer', 'element'], function() {
	var element = layui.element,
		layer = layui.layer,
		$ = layui.$;
	$(function() {
		//网站运行时间

		$('#yunxing').text(timeDifc("2018-9-24 00:00:00", getNowFormatDate()))
		//获取当前的日期时间 格式“yyyy-MM-dd HH:MM:SS”
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if(month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if(strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate +
				" " + date.getHours() + seperator2 + date.getMinutes() +
				seperator2 + date.getSeconds();
			return currentdate;
		}
		function timeDifc(start, end) {
			//是365天:31536000
			var starts = new Date(start),
				ends = new Date(end),
				message = '';
			var times = ends.getTime() - starts.getTime();
			console.log(times)
			if(times < 0) return message = "现在的时间小于以前的时间!";
			if(times < 1) return message = "刚刚";
			times = parseInt(times / 1000)
			var year = parseInt(times / 31536000)
			message += year > 0 ? year + "年" : "";
			var month = parseInt((times - (year * 31536000)) / 2592000)
			message += month > 0 ? month + "个月" : "";
			var day = parseInt((times - (year * 31536000) - (month * 2592000)) / 86400)
			message += day > 0 ? day + "天" : "";
			var hour = parseInt((times - (year * 31536000) - (month * 2592000) - (day * 86400)) / 3600)
			message += hour > 0 ? hour + "小时" : "";
			return message;
		};
	})
});