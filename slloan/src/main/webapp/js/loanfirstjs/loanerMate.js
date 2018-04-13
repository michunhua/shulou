log('loanerMate')

// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.cname = e('.ch-name').value
  data.certificate = e('.certificate').value
  data.certificateType = e('.certificate-type').value
  data.document = e('.document-number').value
  data.untilName = e('.until-name').value
  data.untilPhone = e('.until-phone').value
  data.residence = e('.residence-phone').value
  data.mobile = e('.mobile-phone').value
  data.salary = e('.salary').value
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
  				window.location.href = '../../slloan/loan/loancomms'
  			});
    	}
    }
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
    sendAjax(method, url, data)
  })
}

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
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id =6
	searchAjax(method, url, data)
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  searchData()
}

__main()
