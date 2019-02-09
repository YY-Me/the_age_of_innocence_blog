var IP = "https://www.bblog.vip";
var jQuery;
layui.use(['layer'], function() {
	var $ = layui.$,
		layer = layui.layer;
	jQuery = $;
	$(function() {
		
	})
});

function showCheckbox(id, type) {
	var html_r = "";
	jQuery.each(getDictionary(type), function(idx, obj) {
		html_r += "<input type='checkbox' value='" + obj.id + "' data-id='" + obj.k + "' title='" + obj.v + "' lay-skin='primary'>";
	});
	jQuery('#' + id).html(html_r);
}

function showRolesCheckbox(id) {
	var html_r = "";
	jQuery.each(getRoles(), function(idx, obj) {
		html_r += "<input type='checkbox' data-id='" + obj.id + "' title='" + obj.name + "' lay-skin='primary'>"
	});
	jQuery('#' + id).html(html_r);
}

function showDictRadio(id, type) {
	var html_d = "";
	jQuery.each(getDictionary(type), function(idx, obj) {
		html_d += "<input type='radio' name='sex' value='" + obj.k + "' title='" + obj.v + "'>";
	});
	jQuery('#' + id).html(html_d);
}

function showLabelClassify(lables, classifys) {
	var labelHtml = "";
	var classifyHtml = "";
	var lcs = getLabelClassify();
	jQuery.each(lcs.label, function(idx, obj) {
		labelHtml += "<input type='checkbox' value='" + obj.id + "' data-id='" + obj.id + "' title='" + obj.name + "' lay-skin='primary'>";
	});
	jQuery.each(lcs.classify, function(idx, obj) {
		classifyHtml += "<input type='checkbox' value='" + obj.id + "' data-id='" + obj.id + "' title='" + obj.name + "' lay-skin='primary'>";
	});
	jQuery('#' + lables).html(labelHtml);
	jQuery('#' + classifys).html(classifyHtml);
}
//获取所有标签、分类
function getLabelClassify() {
	var v = sessionStorage.getItem("LabelClassify");
	if(v == null || v == "" || v == undefined) {
		jQuery.ajax({
			url: IP + '/api/blog-admin/labelClassify',
			type: 'GET',
			async: false,
			success: function(result, status, xhr) {
				layer.closeAll('loading');
				sessionStorage.setItem("LabelClassify", JSON.stringify(result.data));
			}
		});
	}
	return JSON.parse(sessionStorage.getItem("LabelClassify"));
}
//获取全部角色数据
function getRoles() {
	var v = sessionStorage.getItem("roles");
	if(v == null || v == "" || v == undefined) {
		jQuery.ajax({
			url: IP + '/api/blog-admin/role',
			type: 'GET',
			async: false,
			success: function(result, status, xhr) {
				layer.closeAll('loading');
				sessionStorage.setItem("roles", JSON.stringify(result.data));
			}
		});
	}
	return JSON.parse(sessionStorage.getItem("roles"));
}

//获取类型为type字典数据
function getDictionary(type) {
	var v = sessionStorage.getItem(type);
	if(v == null || v == "" || v == undefined) {
		jQuery.ajax({
			url: IP + '/api/blog-admin/dictionary',
			type: 'GET',
			async: false,
			data: {
				type: type
			},
			success: function(result, status, xhr) {
				layer.closeAll('loading');
				sessionStorage.setItem(type, JSON.stringify(result.data));
			}
		});
	}
	try {
		return JSON.parse(sessionStorage.getItem(type));
	} catch(e) {
		return [];
	}
}
//菜单类型，1：菜单是个url，2:是一个按钮(int)
function getMenus(type) {
	var menus;
	jQuery.ajax({
		url: IP + '/api/blog-admin/menus',
		type: 'GET',
		data: {
			type: type
		},
		async: false,
		success: function(result, status, xhr) {
			layer.closeAll('loading');
			menus = result.data;
		}
	});
	return MenuSort(menus);
}
//菜单递归排序后返回
function MenuSort(menuArry) {
	var mymenus = [];
	GetData(0, menuArry);

	function GetData(id, arry) {
		var childArry = GetParentArry(id, arry);
		if(childArry.length > 0) {
			for(var i in childArry) {
				mymenus.push(childArry[i])
				GetData(childArry[i].id, arry);
			}
		}
	}

	function GetParentArry(id, arry) {
		var newArry = new Array();
		for(var i in arry) {
			if(arry[i].parentid == id)
				newArry.push(arry[i]);
		}
		return newArry;
	}
	return mymenus;
}
//生成菜单树
function toTree(data) {
	data.forEach(function(item) {
		delete item.children;
	});
	var map = {};
	data.forEach(function(item) {
		map[item.id] = item;
	});
	var val = [];
	data.forEach(function(item) {
		var parent = map[item.parentid];
		if(parent) {
			(parent.children || (parent.children = [])).push(item);
		} else {
			val.push(item);
		}
	});
	return val;
}
//根据sort升序排序
function bubbleSort(arr) {
	var i = arr.length,
		j;
	var tempExchangVal;
	while(i > 0) {
		for(j = 0; j < i - 1; j++) {
			if(arr[j].sort > arr[j + 1].sort) {
				tempExchangVal = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tempExchangVal;
			}
		}
		i--;
	}
	return arr;
}
