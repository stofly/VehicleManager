$(function() {
	$('#w').window('close');
	$('#e').window('close');
	$('#test').datagrid({
		title : '进出车记录管理',
		iconCls : 'icon-tip',
		loadMsg : '数据加载中...',
		nowrap : false,
		striped : true,
		collapsible : true,
		url : '/inAndOutList.action',
		sortName : 'rid',
		sortOrder : 'desc',
		remoteSort : false,
		idField : 'rid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '编号',
			field : 'rid',
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'inum',
			title : '车牌号码',
		}, {
			field : 'adrivername',
			title : '司机姓名',
		}, {
			field : 'idrivernum',
			title : '司机驾驶证编号',
		}, {
			field : 'keynum',
			title : '通行证编号',
		}, {
			field : 'intime',
			title : '进入时间',
			formatter: formatDatebox
		}, {
			field : 'outtime',
			title : '开出时间',
			formatter: formatDatebox
		}, {
			field : 'registerp',
			title : '登记人',
		}] ],
		onDblClickRow : function(selectRow) {
			edit(selectRow);
		},
		pagination : true,
		rownumbers : true,
		toolbar : [ {
			id : 'btncut',
			text : '删除',
			iconCls : 'icon-cut',
			handler : function() {
				del();
			}
		} ]
	});
	var p = $('#test').datagrid('getPager');
	$(p).pagination({
		onBeforeRefresh : function() {
		}
	});

	function btnEdit(selectRow) {
		let rows = $("#test").datagrid('getSelections');
		let row = $("#test").datagrid('getSelected');
		if (rows.length <= 0) {
			$.messager.alert("提示", "请先选中要修改的行!");
			return false;
		}
		if (rows.length > 1) {
			$.messager.alert("提示", "不能同时修改多行!!");
			return false;
		}
		img("imgHeadPhoto1", row.user_image);
		$('#updateUserForm').form('load', row);
		$('#w').dialog('open');
	}
});

// 删除信息

function del() {
	var rows = getSelectionsIds();
	alert(rows)
	if (rows <= 0) {
		$.messager.alert('提示', '请先选中要删除的行!', 'info');
		return;
	}
	// 向后台返回user_id字符串，需要进行字符串拆分，返回sucess表示删除完成，返回error表示删除失败
	$.messager.confirm("提示", "确定要删除吗？", function(value) {
		if (value) {
			$.post('/deteleInAndOut.action', {
				"rid" : rows
			}, function(data) {// {status: 200, msg: "用户删除成功", data: null}
				if (data.status == 200) {
					$.messager.alert("提示", data.msg, 'info', function() {
						// 刷新表格数据
						$('#test').datagrid('reload');
						$("#test").datagrid('clearSelections');

					});
				} else if (data.status == 400) {
					$.messager.alert("提示", data.msg, 'info', function() {
						// 刷新表格数据
						$('#test').datagrid('reload');
					});
				}
			}, "json");
		}
	});
}

function getSelectionsIds() {
	var itemList = $("#test");
	var sels = itemList.datagrid('getSelections');
	var user_ids = [];
	for ( var i in sels) {
		user_ids.push(sels[i].rid);
	}
	user_ids = user_ids.join(",");
	return user_ids;
}

// 查询
function query() {
	var formData = getFormData('searchForm');
	$('#test').datagrid('load', formData);
}
function getFormData(form) {
	var user_code = $("#user_code").val();
	var user_type = $("#user_type").combobox('getValue');
	var queryCompany = $("#queryCompany").combobox('getValue');
	var user_politics = $("#user_politics").combobox('getValue');
	if (user_code == "") {
		user_code = null;
	}
	if (user_type == "---请选择---") {
		user_type = null;
	}
	if (queryCompany == "") {
		queryCompany = null;
	}
	if (user_politics == "---请选择---") {
		user_politics = null;
	}
	var formValues = "user_code=" + user_code + "&" + "user_type=" + user_type
			+ "&" + "user_companyid=" + queryCompany + "&" + "user_politics="
			+ user_politics;
	// 关于jquery的serialize方法转换空格为+号的解决方法
	formValues = formValues.replace(/\+/g, " "); // g表示对整个字符串中符合条件的都进行替换
	var temp = decodeURIComponent(JSON
			.stringify(conveterParamsToJson(formValues)));
	var queryParam = JSON.parse(temp);
	return queryParam;
}
function conveterParamsToJson(paramsAndValues) {
	var jsonObj = {};
	var param = paramsAndValues.split("&");
	for (var i = 0; param != null && i < param.length; i++) {
		var para = param[i].split("=");
		jsonObj[para[0]] = para[1];
	}
	return jsonObj;
}


/**
 * 日期转换
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd hh:mm:ss"); // 扩展的Date的format方法(上述插件实现)
}

