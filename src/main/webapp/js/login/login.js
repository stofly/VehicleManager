$(document).ready(function(){ 
	//查找cookies，搜寻是否让系统记住我
	if ($.cookie("rmbUser") == "true") {
		    $("#checkbox").attr("checked", true);
		    $("#userCode").val($.cookie("userCode"));
		    $("#userPwd").val($.cookie("userPwd"));
	 }
	//判断用户名密码不为空，否则让鼠标光标改变
	$("#login_button").hover(function(){
		
		if($("#userCode").val()=="" || $("#userPwd").val()==""){
			$("#login_button").addClass("mouseon");
		}else{
			$("#login_button").removeClass("mouseon");
		}
	},function(){
		$("#login_button").removeClass("mouseon");
	})
	//鼠标移出判断userCode是否为空
	$("#userCode").blur( function () {
		 if($("#userCode").val()==""){
			 $("#msg1").text("用户名不能为空");
			 return false;
		 }else{
			 $("#msg1").text("");
			 return ;
		 }
	});
	//鼠标移出判断userPwd是否为空
	$("#userPwd").blur( function () {
		 if($("#userPwd").val()==""){
			 $("#msg2").text("密码不能为空");
			 return false;
		 }else{
			 $("#msg2").text("");
			 return ;
		 }
	});
	//点击提交判断usercode与userPwd是否为空
	//ajax提交表单并进行判断
	$("#login_button").click( function () { 
		 if($("#userCode").val()==""){
			 $("#msg1").text("用户名不能为空");
			 return false;
		 }else{
			 $("#msg1").text("");
		 }
		 if($("#userPwd").val()==""){
			 $("#msg2").text("密码不能为空");
			 return false;
		 }else{
			 $("#msg2").text("");
		 }
//		 let userCode = $("#userCode").val();
		 let userCode = encodeURI(encodeURI($("#userCode").val()), '');
		 let userPwd = $("#userPwd").val();
//		 let user_type=$("#user_type").val();
		 let user_type = encodeURI(encodeURI($("#user_type").val()), '');
		 $.post(
				 "/userlogin.action?username="+userCode+"&userpassword="+userPwd+"&type="+user_type, function(h) {
						//如果data等于400，则提示用户名密码错误
						if(h.status==400){
							$("#msg1").text(h.msg);
							return;
						}
						//如果data等于300，则提示用户不是普通用户
						if(h.status==300){
							$("#msg1").text(h.msg);
							return;
						}
						//如果data等于200，则成功登录跳转
						if(h.status==200){
							// $(location).attr('href', '/index.jsp');
							window.location.href="/index.jsp";
						}
				});
	});
}); 
//保存用户名密码在cookies中存在7天
function save(){
	if($('#checkbox').is(':checked')){
		var userCode = $("#userCode").val();
	    var userPwd = $("#userPwd").val();
	    $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
	    $.cookie("userCode", userCode, { expires: 7 });
	    $.cookie("userPwd", userPwd, { expires: 7 });
	}else {
	      $.cookie("rmbUser", "false", { expire: -1 });
	      $.cookie("userCode", "", { expires: -1 });
	      $.cookie("userPwd", "", { expires: -1 });
	   }
}	