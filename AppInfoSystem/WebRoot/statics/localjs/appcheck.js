$("#back").on("click",function(){
	window.location.href = "applist";
});
$("#status").on("click",function(){
	$.ajax({
		type:"post",
		url:path+"/info/checkApp",
		data:{},
	});
	var status=$(this).val();	
	if (status==2) {
		window.location.href="checkApp";
	}else if(status==3){
		window.location.href="applist";
	}
});
