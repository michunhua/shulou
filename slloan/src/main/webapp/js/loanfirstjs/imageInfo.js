console.log('影像资料')


//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
	log('send data method')
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			console.log('返回数据', data)
			if (data.msg == 'success') {
			}
		}
	})
}

// 查询数据
var searchData = function() {
	var method = ''
	var url = ''
	var data = {}
	searchAjax(method, url, data)
}





// 查询图像
var sendAjax = function(method, url, datas) {
  console.log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
      alert(data)
    }
  })
}

var __main = function() {
  var envs = e('#search')
  envs.addEventListener('click', function() {
    var ftype = e('#Ftype').value
    var usernames = e("#userName").value
    var citys = e("#City").value
    var fname = e('#Fname').value
    var id = e('#Id').value
    var method = 'GET'
    var url = '/slloan/updateftpimage/selectfiletype'
    var data = {}
    
    data.upload_type = ftype
    data.note = usernames
    data.city = citys
    data.file = fname
    data.id = id
    
    console.log(data)
    
    sendAjax(method, url, data)
  })
}

__main()