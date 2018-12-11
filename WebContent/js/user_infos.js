$(function() {
	$("#container_left_ul li a").click(function(){
		$(this).parent().addClass("select_up");
		$(this).parent().siblings().removeClass("select_up");
	});
});

$(function () {
	if(window.FileReader){
	      bindImg();
	}
});

function bindImg() {
    //console.log("binding...");
       $("#img_select").change(function () {
           var obj = $("#img_select")[0].files[0];
           var fr = new FileReader();
           fr.onload = function () {
               $("#img_preview").attr('src',this.result);
               //console.log(this.result); //输出图片的base64
      };
      fr.readAsDataURL(obj);
    })
 }

function commit_user_pic() {
	//if($("#img_preview")[0].src);
	var img_obj = $("#img_preview")[0].src;
	if(img_obj == null || img_obj == ""){
		alert("未选择图片文件！");
		return;
	}
	//alert(img_obj);
	$.ajax({
		type:"POST",
		url:"/CampusPortalSite/userInfosPic.jhtml",
		data:{img_data:img_obj},
		dataType:'json',
		success:function(msg){
			if(msg.result == "1"){
				alert("修改成功！");
				location.reload();
			}
		}
	});
}

