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


