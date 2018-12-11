$(function() {
	$("#container_left_ul li a").click(function(){
		$(this).parent().addClass("select_up");
		$(this).parent().siblings().removeClass("select_up");
	});
});






