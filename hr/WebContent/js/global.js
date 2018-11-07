//共同代码
var baseUrl = 'http://localhost:7777/hr';

//全局ajax事件回调，处理ajax请求session超时
$.ajaxSetup({
	complete:function(xhr) {
		var sessinStatus = xhr.getResponseHeader('sessionStatus');
//		alert('sessionStatus=' + sessinStatus);
		if(sessinStatus == 'timeout') {
			alert('登录超时，请重新登录');
			location.href = baseUrl + '/logout.jsp';
		}
	}
})