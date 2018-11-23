function user_login_dialog() {
	$("#user_login_infos").text("每个选项都是必填的。");
	$("#user_login_username").text("");
	$("#user_login_password").text("");
	
	$("#user_login_div").dialog({
		autoOpen:"false",
		title:"学生账户登录",
		width: "600",
		height: "450",
		modal: "true",
		buttons: {
	        "登录": function(){
	        	var user_username = $("#user_login_username").val(),
	    			user_password = $("#user_login_password").val();
		    	
		    	var reg1 = /^[0-9]{11}$/;
		    	var reg2 = /^[A-Za-z0-9_]{3,18}$/;
		    	
		    	if(!reg1.test(user_username)){
		    		$("#user_login_infos").css("color","red");
		    		$("#user_login_infos").text("学号格式错误！");
		    	}
		    	else if(!reg2.test(user_password)){
		    		$("#user_login_infos").css("color","red");
		    		$("#user_login_infos").text("密码格式错误！");
		    	}
		    	else{
		    		var md5_user_password = hex_md5(user_password);
		    		var ajax_data={username:user_username,password:md5_user_password};
		    		$.ajax({
		    			type:"POST",
		    			url:"/CampusPortalSite/userLogin.jhtml",
		    			data:ajax_data,
		    			dataType:'json',
		    			success:function(msg){
		    				if(msg==1){
		    					alert("1");
		    					//window.location.href = "/CampusPortalSite/index.jhtml";
		    				}else{
		    					alert("登录后台出错，无法登录！");
		    				}
		    			}
		    		});
		    	}
	        	
	        },
	        "取消": function(){
	        	$(this).dialog("close");
	        }
	      }
	});
	$("#user_login_div").dialog("open");
}

function user_register_dialog(){
	$("#user_register_infos").text("每个选项都是必填的。");
	$("#user_register_username").text("");
	$("#user_register_password").text("");
	$("#user_register_password_repeat").text("");
	
	$("#user_register_div").dialog({
		autoOpen:"false",
		title:"学生账户注册",
		width: "600",
		height: "450",
		modal: "true",
		buttons: {
	        "注册": function(){
	        	var check_info = check_register_infos();
	        	$("#user_register_infos").css("color","red");
	        	$("#user_register_infos").text(check_info);
	        	
	        	var user_username = $("#user_login_username").val(),
	    		user_password = $("#user_login_password").val(),
	    		user_password_repeat = $("user_register_password_repeat").val();
		    	
		    	var reg1 = /^[0-9]{11}$/;
		    	var reg2 = /^[A-Za-z0-9_]{3,18}$/;
		    	
		    	if(!reg1.test(user_username)){
		    		$("#user_register_infos").css("color","red");
		        	$("#user_register_infos").text("学号格式错误！");
		    	}
		    	else if(!reg2.test(user_password)){
		    		$("#user_register_infos").css("color","red");
		        	$("#user_register_infos").text("密码格式错误！");
		    	}
		    	else if(user_password != user_password_repeat){
		    		$("#user_register_infos").css("color","red");
		        	$("#user_register_infos").text("两次密码输入不一致！");
		    	}
		    	else{
		    		
		    	}
	        	
	        },
	        "取消": function(){
	        	$(this).dialog("close");
	        }
	      }
	});
	$("#user_register_div").dialog("open");
}


