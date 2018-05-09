
// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.note = e('.note')
  data.recordFirst = e('.first-trial')
  data.recorFinal = e('.final-trial').value

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
    var method = ''
    var url = ''
    log(data)
    sendAjax(method, url, data, null)
  })
}

//设置页面数据
var searchExport = function(back) {
	recordNote = e('.note')
	recordFirst = e('.first-trial')
	
	recordNote.value = back.id
	recordFirst.value = back.id
}

var initback = {
		id: '17'
}

searchExport(initback)



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
	var url = '/slloan/loan/notedescid'
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
    window.history.back()
  })
}

//提交方法
var submitAjax = function(method, url, datas) {
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
	  				window.location.href = '../../slloan/loan/loancreas'
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
// 提交按钮提交数据
var submitBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		    var method = 'GET'
			var url = '/slloan/loan/loanfinance'
			var data = collectData()
			data.id = localStorage.finalID
			data.rolename = localStorage.purrole
		    data.username = localStorage.purusername
		    data.city = localStorage.purcity
			data.parentnodeId = localStorage.purid
			submitAjax(method, url, data)
	})
}

// 回退方法
var backAjax = function(method, url, datas) {
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
	  				window.location.href = '../../slloan/loan/loancreas'
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
// 回退按钮回退数据
var backBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		    var method = 'GET'
			var url = '/slloan/loan/loannotllback'
			var data = collectData()
				data.id=localStorage.finalID
			submitAjax(method, url, data)
	})
}


//
var __main = function() {
  log( "run")
  sendData('#save-note')
  cancelBtn('#cancel')
  searchData()
  submitBtn("#submit")
  backBtn("#go-back")
}

__main()
