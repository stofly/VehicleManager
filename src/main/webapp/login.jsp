<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆管理系统-登录</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/utils/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/utils/common.js"></script>
<script type="text/javascript" src="js/login/login.js"></script>
<script type="text/javascript" src="js/utils/jquery.cookie.js"></script>
</head>
<body >
	<div class="second_body">
		<form id="from">
			<div class="logo"></div>
			<div class="title-zh">车辆管理系统</div>
			<div class="title-en">&nbsp;&nbsp;VehicleManager&nbsp;&nbsp;&nbsp;&nbsp;System</div>
			<div class="message" data-bind="html:message"></div>
			<table border="0" style="width: 320px;">
				<tr>
					<td style="white-space: nowrap; padding-bottom: 5px; width: 55px;">用户名：</td>
					<td colspan="2"><input type="text" id="userCode" class="login" /></td>
					<td><span id="msg1" style="color: red; font-size: 10"></span></td>
				</tr>
				<tr>
					<td class="lable" style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle">密码：</td>
					<td colspan="2"><input type="password" id="userPwd" class="login" /></td>
					<td><span id="msg2" style="color: red; font-size: 10"></span></td>
				</tr>
				<tr>
					<td class="lable" style="white-space: nowrap; letter-spacing: 0.5em; vertical-align: middle">角色：</td>
					<td colspan="2"><select name="user_type" class="easyui-combobox" id="user_type" style="border: 0;">
							<option value="超级管理员">超级管理员</option>
							<option value="管理员">管理员</option>
							<option value="申请人">申请人</option>
							<option value="门卫">门卫</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="checkbox" id="checkbox" checked="true" /> <span>系统记住我</span></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3" style="text-align: left"><input type="button" value="登录" class="login_button" id="login_button" onclick="save()" /> <input type="reset" value="重置" class="reset_botton" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
