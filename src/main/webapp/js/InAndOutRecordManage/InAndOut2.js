$(function() {
	// 设置弹出窗口的属性
	$('#e').window({
		title : '添加记录',
		closed : false,
		cache : false,
		collapsible : true,
		maximizable : false, // 不可最大化
		resizable : false,
		shadow : true,
		left : 500,
		top : 80,
		modal : true, // 模态窗
	});
	$('#w').window('close');
	$('#e').window('close');
	$('#test').datagrid({
		title : '信息管理',
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
			checkbox : false
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
			id : 'btnadd',
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				// 打开前清空表单
				$('#adduserForm').form('clear');
				// 打开表单窗口
				$('#e').window('open');
			}
		}]
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

	// 添加用户
	$("#addUser").click(function() {
		alert(1111)
		$('#adduserForm').form('submit', {
			url : '/saveIoRecord.action',
			success : function(data) {
				var jsonData = JSON.parse(data);
				if (jsonData.status == 200) {
					$.messager.alert("提示", jsonData.msg, 'info', function() {
						// 刷新表格数据
						$('#test').datagrid('reload');
						$('#e').window('close');
						cleanImg("imgHeadPhoto2");
					});

				} else if (jsonData.status == 400) {
					$.messager.alert("提示", jsonData.msg, 'info', function() {
					});
				}
			}
		});
	});
	// 修改用户
	$("#updateUser").click(function() {
		$('#updateUserForm').form('submit', {
			url : '/updateDriverInfo.action',
			onSubmit : function() {
			},
			success : function(data) {// {"status":200,"msg":"用户信息更新成功","data":null}
				var jsonData = JSON.parse(data);
				console.log(jsonData);
				if (jsonData.status == 200) {
					$.messager.alert("提示", jsonData.msg, 'info', function() {
						// 刷新表格数据
						$('#test').datagrid('reload');
						$('#w').window('close');
						cleanImg("imgHeadPhoto1");
					});
				} else if (jsonData.status == 400) {
					$.messager.alert("提示", jsonData.msg, 'info', function() {
					});
				}
			}
		});
	})
});

// 双击修改用户
function edit(selectRow) {
	var r = $("#test").datagrid("getRows");// 获取本页有多少行
	row = r[selectRow];// 获得当前行
// img("imgHeadPhoto1", row.user_image);
	$('#updateUserForm').form('load', row);
	$('#w').dialog('open');
}
function getSelectionsIds() {
	var itemList = $("#test");
	var sels = itemList.datagrid('getSelections');
	var user_ids = [];
	for ( var i in sels) {
		user_ids.push(sels[i].did);
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
// 日期转换
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


