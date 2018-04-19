// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.cname = e('.ch-name').value
  data.paperwork = e('.paperwork-type').value
  data.paperNumb = e('.paperwork-numb').value
  data.unitName = e('.until-name').value
  data.unitPhone = e('.until-phone').value
  data.residencePhone = e('.residence-phone').value
  data.mobiePhone = e('.mobile-phone').value
  data.salary = e('.salary').value
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = ''
    var url = ''
    log(data)
    sendAjax(method, url, data, null)
  })
}

//设置页面数据
var searchExport = function(back) {
	  cname = e('.ch-name')
	  certificate = e('.certificate')
	  certificateType = e('.certificate-type')
	  document = e('.document-number')
	  untilName = e('.until-name')
	  untilPhone = e('.until-phone')
	  residence = e('.residence-phone')
	  mobile = e('.mobile-phone')
	  salary = e('.salary')
	  
	  cname.value = back.id
	  certificate.value = back.id
	  certificateType.value = back.id
	  document.value = back.id
	  untilName.value = back.id
	  untilPhone.value = back.id
	  residence.value = back.id
	  mobile.value = back.id
	  salary.value = back.id
}

var initback = {
		id: '23'
}

searchExport(initback)

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
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = 10
	if(data.id) {
		searchAjax(method, url, data)
	}
}



//
var __main = function() {
  log( "run")
  sendData('#save-data')
}

__main()
