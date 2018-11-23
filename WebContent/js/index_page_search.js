//页面跳转：向后
function nextPage(keyword, pageNumber, totalPageNumber){
	var pageNumber_next = parseInt(pageNumber)+1;
	if(pageNumber_next > totalPageNumber){
		alert("当前已是最后一页！");
		return;
	}
	else{
		window.location.href="/CampusPortalSite/index.jhtml?page="+pageNumber_next+"&keyword="+keyword;
	}
}

//页面跳转：向前
function lastPage(keyword, pageNumber){
	var pageNumber_last = parseInt(pageNumber)-1;
	if(pageNumber_last < 1){
		alert("当前已是最前一页！");
		return;
	}
	else{
		window.location.href="/CampusPortalSite/index.jhtml?page="+pageNumber_last+"&keyword="+keyword;
	}
}

//页面跳转：尾页
function nextFinalPage(keyword, totalPageNumber){
	window.location.href="/CampusPortalSite/index.jhtml?page="+totalPageNumber+"&keyword="+keyword;
}

//页面跳转：首页
function lastFinalPage(keyword){
	window.location.href="/CampusPortalSite/index.jhtml?page=1&keyword="+keyword;
}

//首页搜索功能
function doSearch(){
	//var keyword = encodeURI($(".search_keyword").val());
	var keyword = $(".search_keyword").val();
	window.location.href="/CampusPortalSite/index.jhtml?page=1&keyword="+keyword;
}

