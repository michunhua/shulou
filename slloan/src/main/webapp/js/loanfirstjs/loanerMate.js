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
    var method = 'POST'
    var url = '/slloan/loan/spoupdate'
    	data.id = 1
    log(data)
    sendAjax(method, url, data)
  })
}

// 设置页面数据
var searchExport = function(back) {
	  cname = e('.ch-name')
	  certificate = e('.certificate')
	  certificateType = e('.certificate-type')
	  documents = e('.document-number')
	  untilName = e('.until-name')
	  untilPhone = e('.until-phone')
	  residence = e('.residence-phone')
	  mobile = e('.mobile-phone')
	  salary = e('.salary')
	  
	  cname.value = back.name
//	  certificate.value = back.id_Type
	  certificateType.value = back.paperother
	  documents.value = back.id_Number
	  untilName.value = back.uni_Name
	  untilPhone.value = back.home_Phone
	  residence.value = back.unit_Phone
	  mobile.value = back.mobile_Phone
	  salary.value = back.monthly_Income
	  
	  // 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".certificate").val(back.id_Type);
		  form.render()
		});
	  
}

var initback = {
		id: '23'
}

//searchExport(initback)

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
		searchExport(data.obj)
	}
}
})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id = 1
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  searchData()
}

__main()
