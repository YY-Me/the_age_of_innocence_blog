var quotecontents = [
	"心之所向，素履以往；生如逆旅，一苇以航。——七堇年",
	"如果你不出去走走，你就会以为这就是世界。——lancelovelnny",
	"一个人如果能看穿这世界的矫饰，这个世界就是他的。 —猪八戒三弟",
	"一直相信，阴影也是可以很美的，因为那是光的赐予。——雪飘云间",
	"推动你向前的，不是困难，而是梦想。——幻羽阿拉",
	"你不一定是最优秀的，但你一定是最有潜力的！——天使的宠儿",
	"别在最应该努力的年纪，选择了转发锦鲤。",
	"坚持的毅力，就是成功的最美修饰。——佐手那回忆",
	"当你紧握双手，里面什么也没有；当你打开双手，世界就在你手中。——lancelovelnny",
	"我心里一直都在暗暗设想，天堂应该是图书馆的模样。——博尔赫斯",
	"不要因为没有把握，就放弃自己心中的追求。",
	"美好的事情不会降临在傻傻等待的人身上，它只会降临在那些追逐目标和梦想的人身上。",
	"与其在蹉跎中等待，不如放下包袱，勇敢的追求梦想...",
	"愿时光冲不淡我们的友谊，愿岁月带不走我们的轻狂。",
	"生活不是一场赛跑，生活是一场旅行，要懂得好好欣赏每一段的风景。",
	"天空越暗的时候，你越能看到星辰。只要我们肯择善固执，莫忘初衷，善爱善爱。",
	"世界上最远的距离，不是树与树的距离，而是同根生长的树枝，却无法在风中相依",
	"生活就像是跟老天对弈，对你而言，你走棋，那叫选择；老天走棋，那叫挑战",
	"世界会向那些有目标和远见的人让路",
	"我把青春赌昨天，昨天把青春摔了个稀巴烂",
	"生活不乏精彩，只是有时候我们的眼睛盯着乌云不放",
	"用淡然看透俗事，用遗忘解脱往事，用沉默诉说心事",
	"一个人可以被毁灭，但不能被打倒",
	"不要到处宣扬自己的内心，这世上不止你一个人有故事",
	"愿你一生努力，一生被爱，想要的都拥有，得不到的都释怀",
	"你不要那么小心翼翼，爱你的人，不需要你完美无缺",
	"天黑了，黯然低头，才发现水面满是闪烁的星光",
	"把我的悲伤留给自己 你的美丽让你带走",
	"谁都不必失落，适合自己的人生就是就是最好的人生",
	"为了自己想要的未来，无论现在有多难熬，都必须得满怀信心的坚持下去",
	"生活得最有意义的人，并不就是年岁活得最大的人，而是对生活最有感受的人。——卢梭"
]
layui.config({
	base: '../js/web/' //静态资源所在路径
}).extend({
	common: '/common' //公共模块
}).use(['common'], function() {
	var common = layui.common,
		$ = common.$;
	var index = {
		init: function() {
			index.bindEvent();
			index.initQuotecontent();
			index.initTitle();
		},
		bindEvent: function() {
			//showmusic
			$('#music163').click(function() {
				if($(this).hasClass('show')) {
					$(this).css({
						left: '-320px'
					})
					$(this).removeClass('show')
				} else {
					$(this).css({
						left: '-5px'
					})
					$(this).addClass('show')
				}
			})
		},
		initQuotecontent: function() {
			$("#quotecontent").html(quotecontents[common.getNowstrDate()])
		},
		initTitle: function() {
			try {
				var classifyName = ($('#classifyName').html()).trim();
				var classifyList = $('.classify .content a');
				var labels = $('.flag .content a');
				$.each(classifyList, function(idx, obj) {
					if($(this).attr('href') == "/classify/" + classifyName) {
						$('#classifyName').html($(this).attr('title'))
					}
				});
				$.each(labels, function(idx, obj) {
					if($(this).attr('href') == "/label/" + classifyName) {
						$('#classifyName').html($(this).attr('title'))
					}
				});
			} catch(e) {}
		}
	};
	$(function() {
		index.init();
	});
});