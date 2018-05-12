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
  data.state = ''
	  data.ctime = ''
data.id =  localStorage.finalID
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function() {
    	callback()
    },
    error: function(){
        alert('服务器错误')
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
    var url = '/slloan/loan/spoupdatea'
    log(data, data.id)
    sendAjax(method, url, data, null)
  })
}

//设置页面数据
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
	} else {
		alert('服务器错误')
	}
},
error: function(){
    alert('服务器错误')
 }
})
}

// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id = localStorage.finalID
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//取消按钮事件
var cancelBtn = function(element) {
  var forms = e('form')
  var evs = e(element)
  evs.addEventListener('click', function() {
    forms.reset()
    window.location.href = "/slloan/loan/loancreass"
  })
}

//
var __main = function() {
  log( "run")
  searchData()
  sendData('#save-data')
  cancelBtn('#cancel')
}

__main()
