$(function() {
	$('#changeName1').combobox(
			{
				prompt : '输入关键字后自动搜索',
				required : true,
				mode : 'remote',
				url : '/selectCarInfoByName.action',
				valueField: 'cnum',
	            textField: 'cnum',
				editable : true,
				hasDownArrow : false,
				onBeforeLoad : function(param) {
					if (param == null || param.q == null
							|| param.q.replace(/ /g, '') == '') {
						var value = $(this).combobox('getValue');
						if (value) {// 修改的时候才会出现q为空而value不为空
							param.id = value;
							return true;
						}
						return false;
					}
				}
			});
	// 设置弹出窗口的属性
	$('#w').window({
		title : '修改车辆信息',
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
	$('#e').window({
		title : '添加车车辆信息',
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
		title : '车辆基本信息管理',
		iconCls : 'icon-tip',
		loadMsg : '数据加载中...',
		nowrap : false,
		striped : true,
		collapsible : true,
		url : '/carInfoList.action',
		sortName : 'cid',
		sortOrder : 'desc',
		remoteSort : false,
		idField : 'cid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '编号',
			field : 'cid',
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'cnum',
			title : '车牌号码',
		}, {
			field : 'ctype',
			title : '车辆型号',
		}, {
			field : 'crecord',
			title : '车辆记录',
		}, {
			field : 'cplan',
			title : '车辆计划',
		}, {
			field : 'cstate',
			title : '车辆状态',
		}, ] ],
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
		}, '-', {
			id : 'btnsave',
			text : '修改',
			iconCls : 'icon-save',
			handler : function(selectRow) {
				btnEdit(selectRow);
			}
		}, '-', {
			id : 'btncut',
			text : '删除',
			iconCls : 'icon-cut',
			handler : function() {
				del();
			}
		} ]
	});
	//点击查询按钮
	$('#btnSearch').bind('click',function(){
		//把表单数据转换成json对象
		var formData = $('#searchForm').serializeJSON();
		$('#test').datagrid('load',formData);
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
		$('#adduserForm').form('submit', {
			url : '/saveCarInfo.action',
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
			url : '/updateCarInfo.action',
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

// 删除客户

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
			$.post('/deteleCarInfo.action', {
				"cid" : rows
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
		user_ids.push(sels[i].cid);
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
// 清除图片路径
function cleanImg(fileId) {
	var fId = "#" + fileId;
	$(fId).attr("src", "");
}

function img(id, image) {
	if (image == "" || image == null) {
		image = "/images/default.jpg";
	}
	var fId = "#" + id;
	$(fId).attr("src", image);
}
