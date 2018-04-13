console.log('影像资料')


//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
log('send data method')
$.ajax({
type: method,
url: url,
data: {data:JSON.stringify(datas)},
success: function(data) {
	console.log('返回数据', data)
	if(data.msg == 'success') {
	}
}
})
}

var searchData = function() {
	var method = ''
	var url = ''
	var data = {}
	searchAjax(method, url, data)
}