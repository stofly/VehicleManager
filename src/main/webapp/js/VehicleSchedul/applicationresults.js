$(function() {
	$('#w').window('close');
	$('#e').window('close');
	$('#test').datagrid({
		title : '审批结果管理',
		iconCls : 'icon-tip',
		loadMsg : '数据加载中...',
		nowrap : false,
		striped : true,
		collapsible : true,
		url : '/approvalListByName.action',
		sortName : 'aid',
		sortOrder : 'desc',
		remoteSort : false,
		idField : 'aid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '编号',
			field : 'aid',
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'acnum',
			title : '车牌号码',
		}, {
			field : 'actype',
			title : '车辆型号',
		}, {
			field : 'adrivername',
			title : '驾驶员姓名',
		}, {
			field : 'approvalperson',
			title : '申请人',
		}, {
			field : 'reason',
			title : '外出事由',
		}, {
			field : 'startime',
			title : '开始时间',
			formatter: formatDatebox
		}, {
			field : 'endtime',
			title : '结束时间',
			formatter: formatDatebox
		}, {
			field : 'astate',
			title : '当前审核状态',
		}, {
			field : 'auditperson',
			title : '审核人',
		}, {
			field : 'adesc',
			title : '审核附言',
		} ] ],
		onDblClickRow : function(selectRow) {
			edit(selectRow);
		},
		pagination : true,
		rownumbers : true,
		toolbar : [ {
			id : 'btnadd',
			text : '若有疑问，请您致电110或到学院车辆审批中心',
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
});
//日期转换
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


