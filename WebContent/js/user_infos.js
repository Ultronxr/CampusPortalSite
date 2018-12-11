//改变ul中被选中的li的背景
$(function() {
	$("#container_left_ul li a").click(function(){
		$(this).parent().addClass("select_up");
		$(this).parent().siblings().removeClass("select_up");
	});
});

//点击ul左边不同的按钮，显示不同的div内容
function change_ul(num) {
	$("#container_right").children().hide();
	if(num == 1 || num == '1'){
		$("#user_pic_change_div_right").show();
	}
	if(num == 2 || num == '2'){
		$("#user_infos_change_div_right").show();
	}
	if(num == 3 || num =='3'){
		$("#user_pwd_change_div_right").show();
	}
}



//修改用户头像
$(function () {
	if(window.FileReader){
	      bind_img();
	}
});

function bind_img() {
    //console.log("binding...");
       $("#img_select").change(function () {
           var obj = $("#img_select")[0].files[0];
           var fr = new FileReader();
           fr.onload = function () {
               $("#img_preview").attr('src',this.result);
      };
      fr.readAsDataURL(obj);
    })
 }

//提交用户头像
function commit_user_pic() {
	var img_obj = $("#img_preview")[0].src;
	if(img_obj == null || img_obj == ""){
		alert("未选择图片文件！");
		return;
	}
	$.ajax({
		type:"POST",
		url:"/CampusPortalSite/userInfosPic.jhtml",
		data:{img_data:img_obj},
		dataType:'json',
		success:function(msg){
			if(msg.result == "1") alert("修改成功！");
			else alert("无法完成修改！");
			location.reload();
		}
	});
}


var array = new Array(); //记录修改前的个人信息

function infos_change() {
	array.splice(0,array.length);
	array.push($("#name_input").val());
	array.push($("#sex_input").val());
	array.push($("#age_input").val());
	array.push($("#id_id_input").val());
	array.push($("#politics_status_input").val());
	array.push($("#school_id_input").val());
	array.push($("#institute_input").val());
	array.push($("#department_input").val());
	array.push($("#classs_input").val());
	array.push($("#phone_number_input").val());
	array.push($("#qq_number_input").val());
	array.push($("#email_input").val());
	array.push($("#blog_input").val());
	
	$("#infos_change_button").parent().hide();
	$("#infos_cancel_button").parent().show();
	$("#user_infos_form input").removeAttr("disabled");
	$("#id_id_input").attr("disabled","disabled");
	$("#school_id_input").attr("disabled","disabled");
	
}
function infos_submit() {
	var name=$("#name_input").val(), sex=$("#sex_input").val(), age=$("#age_input").val(), politics_status=$("#politics_status_input").val(),
		institute=$("#institute_input").val(), department=$("#department_input").val(), classs=$("#classs_input").val(),
		phone_number=$("#phone_number_input").val(), qq_number=$("#qq_number_input").val(), email=$("#email_input").val(),
		blog=$("#blog_input").val();

	if(name==null || sex==null || age==null || politics_status==null || institute==null || department==null || classs==null
			|| name=="" || sex=="" || age=="" || politics_status=="" || institute=="" || department=="" || classs==""){
		alert("请填写完所有必填信息再保存！")
		return;
	}
	
	var reg1 = /^[0-9]{1,3}$/, //年龄
		reg2 = /^[0-9]{3}$/; //班级
	
	if(!reg1.test(age) || (age<10||age>80)){
		alert("请输入正确的年龄信息！");
		return;
	}
	if(!reg1.test(classs)){
		alert("请输入正确的班级信息！");
		return;
	}
	
	$.ajax({
		type:"POST",
		url:"/CampusPortalSite/userInfosUpdate.jhtml",
		data:{name:name,sex:sex,age:age,politics_status:politics_status,institute:institute,department:department,classs:classs,
			phone_number:phone_number,qq_number:qq_number,email:email,blog:blog},
		dataType:'json',
		success:function(msg){
			if(msg.result == "1") alert("信息修改保存成功！");
			else alert("无法保存信息修改！");
			location.reload();
		}
	});
}
function infos_cancel() {
	$("#infos_cancel_button").parent().hide();
	$("#infos_change_button").parent().show();
	$("#user_infos_form input").attr("disabled","disabled");
	
	$("#name_input").val(array[0]);
	$("#sex_input").val(array[1]);
	$("#age_input").val(array[2]);
	$("#id_id_input").val(array[3]);
	$("#politics_status_input").val(array[4]);
	$("#school_id_input").val(array[5]);
	$("#institute_input").val(array[6]);
	$("#department_input").val(array[7]);
	$("#classs_input").val(array[8]);
	$("#phone_number_input").val(array[9]);
	$("#qq_number_input").val(array[10]);
	$("#email_input").val(array[11]);
	$("#blog_input").val(array[12]);
}








