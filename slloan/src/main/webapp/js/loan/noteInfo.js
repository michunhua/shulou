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
  data.recordFirst = e('.esnote')
  data.recorFinal = e('.esnote')
  data.recorfore = ''
  data.state = ''
	  data.rolename = localStorage.purrole
		data.username = localStorage.purusername
		data.city = localStorage.purcity
		data.parentnodeId = localStorage.purid
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	localStorage.createID = ""
        localStorage.createTemporaryId = ""
    	if(data.msg == 'success') {
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
//  				window.location.href = "/slloan/loan/loancrea"
  			});
    	} else {
    		alert('服务器错误')
    	}
    },
    error: function(){
        alert('服务器错误')
     }   
  })
}

//设置页面数据
var searchExport = function(back) {
	recordNote = e('.note')
	
	recordNote.value = back.note_Description1
}

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
				searchExport(data.obj)
			} else {
				alert('当前页面无数据,请添加')
			}
		},
	    error: function(){
	        alert('服务器错误')
	     }		
	})
}

//查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/notedescripti'
	var data = {}
	data.id = localStorage.createID
	data.rolename = localStorage.purrole
	data.username = localStorage.purusername
	data.city = localStorage.purcity
	data.parentnodeId = localStorage.purid
	if(data.id) {
		searchAjax(method, url, data)
	}
}


// 提交方法
var submitAjax = function(method, url, datas) {
	log('send data method')
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
		console.log('返回数据', data)
		localStorage.createID = ""
    	if(data.msg == 'success') {
    		layer.msg('提交成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
  				window.location.href = "/slloan/loan/loancrea"
  			});
    	} else {
    		localStorage.createID = ""
    		alert('服务器错误')
    	}
	 },
     error: function(){
    	 localStorage.createID = ""
         alert('服务器错误')
      }	 
	})
}

// 保存按钮点击事件&发送数据
var sendData = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
    var data = collectData()
  data.rolename = localStorage.purrole
	data.username = localStorage.purusername
	data.city = localStorage.purcity
	data.parentnodeId = localStorage.purid
    data.temporaryId = localStorage.createTemporaryId
    var method = 'POST'
    var url = '/slloan/loan/notedescription'
    log(data)
    if(data.temporaryId) {
        sendAjax(method, url, data)	
    }
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
	  document.getElementById('createNote').value = null
	  window.location.href = "/slloan/loan/loancrea"
  })
}

// 提交按钮事件
var submitBtn = function(element) {
	var purpose = e(element)
	purpose.addEventListener('click', function() {
		var method = 'GET'
		var url = '/slloan/loan/loannotfirsts'
		var data = collectData()
		data.rolename = localStorage.purrole
	data.username = localStorage.purusername
    data.city = localStorage.purcity
    data.parentnodeId = localStorage.purid
		data.id = localStorage.createID
		if(data.id) {
			submitAjax(method, url, data)
		} else {
			layer.msg("请先填写完资料保存后提交", {time: 3000})
		}
	})
}

// 修改数据保存
var updateData = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
    var data = collectData()
    data.id = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/updatenote'
    log(data)
    if(data.id) {
			sendAjax(method, url, data)
		}
  })
}

//
var __main = function() {
  log( "run")
  sendData('#save-note')
  cancelBtn('#cancel')
  submitBtn('#submit')
  searchData()
  updateData('#save-note')
}

__main()
