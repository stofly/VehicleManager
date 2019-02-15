<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<script type="text/javascript" src="js/utils/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/utils/jquery.easyui.1.2.6.min.js"></script>
<script type="text/javascript" src='js/utils/index.js'></script>
<script type="text/javascript" src='js/utils/utils.js'></script>
<script type="text/javascript">
$(function() {
	var messageType ="${sessionScope.user.type}";
	if(messageType!=null&&messageType=="超级管理员"&&messageType!=""||messageType=="管理员"){
		$.get("/approvalListWhereNo.action",function(data){
		    $.messager.show({
				title : "待审核消息提醒",
				msg : "亲爱的${sessionScope.user.type}${sessionScope.user.username}，您有"+data+"条待审核信息，请您及时审核！！！",
				timeout : 5000
			});
		});
	}
	if(messageType!=null&&messageType=="申请人"&&messageType!=""){
	    $.messager.show({
			title : "消息查收",
			msg : "亲爱的${sessionScope.user.type}${sessionScope.user.username}，您有新的信息，请您及时查收！！！",
			timeout : 5000
		});
	}
})
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" fit="true" scroll="no">
	<noscript>
		<div style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>

	<div region="north" split="false" border="false" style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<!-- 		<div data-options="iconCls:'icon-help'" onclick="showAbout()">关于</div> -->
		<span style="float: right; padding-right: 20px;" class="head"> ${user.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${user.type}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="editpass">修改密码</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/userlogout.action" id="loginOut">安全退出</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" onclick="showAbout()">联系我们</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</span> <span style="padding-left: 10px; font-size: 16px;"> <img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> 车辆管理系统
		</span>
	</div>
	<div region="south" split="false" style="height: 30px; background: #D2E0F2;">
		<div class="footer">车辆管理系统 V1.0</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;" id="west">
		<div id="nav">
			<!--导航内容 -->
		</div>

	</div>
	<div id="mainPanle" region="center" style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden; color: red;">
				<div>
					<img src="images/welcome.png" style="height: 100%; width: 100%"></img>
				</div>
			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false" maximizable="false" icon="icon-save" style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)"> 确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>
</body>


</html>