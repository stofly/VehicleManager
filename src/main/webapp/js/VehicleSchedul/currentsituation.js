$(function() {
	$("#sj").combobox({
		url : '/findAllDriverInfoName.action',
		valueField : 'did',
		textField : 'drivername',
		panelHeight : '150',
	});
	// 设置弹出窗口的属性
	$('#w').window({
		title : '申请调度',
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
		title : '车辆当前状态',
		iconCls : 'icon-tip',
		loadMsg : '数据加载中...',
		nowrap : false,
		striped : true,
		collapsible : true,
		url : '/carInfoListByAble.action',
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
			text : '若您想申请调度，请您双击可用行！！！',
			iconCls : 'icon-help',
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

	$("#updateUser").click(
		function() {
			let acnum =encodeURI(encodeURI($("#acnum").val()), '');
			let actype = encodeURI(encodeURI($("#actype").val()), '');
			let sj = encodeURI(encodeURI($('#sj').combobox('getText')), '');
			let sjid = encodeURI(encodeURI($('#sj').combobox('getValue')), '');
			let reason = $("#reason").val();
			let cid = $("#cid").val();
			let startime = $("#startime").datetimebox("getValue");
			let endtime = $("#endtime").datetimebox("getValue");
			$('#updateUserForm').form(
				'submit',
				{
					url : "/addApproval.action?sjid="+sjid+"&cid="+cid+"&acnum="+acnum+"&actype="+actype+"&adrivername="+sj+"&startime="+startime+"&endtime="+endtime+"&reason="+reason,
					onSubmit : function() {
					},
					success : function(data) {//
						// {"status":200,"msg":"用户信息更新成功","data":null}
						var jsonData = JSON.parse(data);
						console.log(jsonData);
						if (jsonData.status == 200) {
							$.messager.alert("提示", jsonData.msg,
									'info', function() {
//										// // 刷新表格数据
//										$('#test').datagrid('reload');
										$('#w').window('close');
									});
						} else if (jsonData.status == 400) {
							$.messager.alert("提示", jsonData.msg,
									'info', function() {
							});
						}
					}
				});
		})
});

// 双击用户
function edit(selectRow) {
	var r = $("#test").datagrid("getRows");// 获取本页有多少行
	row = r[selectRow];// 获得当前行
	// img("imgHeadPhoto1", row.user_image);
	$('#updateUserForm').form('load', row);
	$('#w').dialog('open');
	$(function() {
		var curr_time = new Date();
		var strDate = curr_time.getFullYear() + "-";
		strDate += curr_time.getMonth() + 1 + "-";
		strDate += curr_time.getDate() + "-";
		strDate += " " + curr_time.getHours() + ":";
		strDate += curr_time.getMinutes() + ":";
		strDate += curr_time.getSeconds();
		$("#startime").datetimebox("setValue", strDate);
	});
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
