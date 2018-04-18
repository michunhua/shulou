//自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
  return  document.querySelector(elements)
}

// 依赖库方法
layui.use('laydate', function(){
  var laydate = layui.laydate;

  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
});


// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.cname = e('.ch-name').value
  data.paperwork = e('.paperwork-type').value
  data.paperother = e('.document-number').value
  data.paperNumb = e('.paperwork-numb').value
  data.unitName = e('.unit-name').value
  data.residencePhone = e('.residence-phone').value
  data.unitPhone = e('.unit-phone').value
  data.mobiePhone = e('.mobie-phone').value
  data.salary = e('.salary').value
  data.state = 'a'
  data.ctime = 'b'
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
  				window.location.href = '../../slloan/loan/loancomm'
  			});
    	}
    }
  })
}


// 提交按钮点击事件&发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = e(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/loanApplyspouse'
    log(data)
    sendAjax(method, url, data)
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var forms = e('form')
  var evs = e(element)
  evs.addEventListener('click', function() {
    forms.reset()
  })
}

//设置页面数据
var searchExport = function(back) {
	  cname = e('.ch-name')
	  certificate = e('.paperwork-type')
	  certificateType = e('.paperwork-numb')
	  document = e('.document-number')
	  untilName = e('.unit-name')
	  untilPhone = e('.unit-phone')
	  residence = e('.residence-phone')
	  mobile = e('.mobie-phone')
	  salary = e('.salary')
	  
	  cname.value = back.name
	  certificate.value = back.id
	  certificateType.value = back.id_Number
	  document.value = back.id_Number
	  untilName.value = back.uni_Name
	  untilPhone.value = back.home_Phone
	  residence.value = back.unit_Phone
	  mobile.value = back.mobile_Phone
	  salary.value = back.monthly_Income
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
  cancelBtn('#cancel')
  searchData()
}

__main()
