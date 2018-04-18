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
  var data = {}
  data.note = e('.note').value
  data.state = '2'
  data.ctime = 'c'
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
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
  				window.location.href = '../../slloan/loan/loancrea'
  			});
    	}
    }
  })
}

//设置页面数据
var searchExport = function(back) {
	recordNote = e('.note')
	
	recordNote.value = back.id
}

var initback = {
		id: '17'
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
		searchExport(initback)
	}
}
})
}

//
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/jointappliss'
	var data = {}
	data.id = 2
	if(data.id) {
		searchAjax(method, url, data)
	}
}

// 保存按钮点击事件&发送数据
var sendData = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/notedescription'
    log(data)
    sendAjax(method, url, data, null)
  })
}

//提交按钮点击事件&发送数据
var submitData = function(element) {
	var evs = e(element)
	  evs.addEventListener('click', function() {
	    var data = collectData()
	    var method = 'POST'
	    var url = '/slloan/loan/loannotfirsts'
	    log(data)
	    sendAjax(method, url, data, null)
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

//
var __main = function() {
  log( "run")
  sendData('#save-note')
  submitData('#submit')
  cancelBtn('#cancel')
  searchData()
}

__main()
