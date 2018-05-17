
// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.note = e('.note').value
  data.recordFirst = e('.first-trial').value
  data.recorFinal = e('.final-trial').value
  data.recorfore = ""
	  
  data.rolename = localStorage.purrole
  data.username = localStorage.purusername
  data.city = localStorage.purcity
  data.parentnodeId = localStorage.purid
  data.state = ""
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
//  				window.location.href = "/slloan/loan/loancreas"
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


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    data.id = localStorage.finalID
    var method = 'POST'
    var url = '/slloan/loan/updatenotethere'
    log(data)
    sendAjax(method, url, data)
  })
}

//设置页面数据
var searchExport = function(back) {
	recordNote = e('.note')
	recordFirst = e('.first-trial')
	recorFinal = e('.final-trial')
	
	recordNote.value = back.note_Description1
	recordFirst.value = back.note_Description2
	recorFinal.value = back.note_Description3
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
	var url = '/slloan/loan/notedescripti'
	var data = {}
	data.id = localStorage.finalID
	  data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
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
	  				 window.location.href = "/slloan/loan/loancreass"
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
		    layer.confirm('确定通过该贷款?', {icon: 3, title:'注意'}, function(index){
			    var method = 'GET'
				var url = '/slloan/loan/loanfinance'
				var data = collectData()
				data.id = localStorage.finalID
				data.rolename = localStorage.purrole
				data.username = localStorage.purusername
				data.city = localStorage.purcity
			    data.parentnodeId = localStorage.purid
				submitAjax(method, url, data)
			  layer.close(index);
			});
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
	    		layer.msg('回退成功', {
	  			  icon: 2,
	  			  time: 2000 
	  			}, function(){
	  				 window.location.href = "/slloan/loan/loancreass"
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
			layer.confirm('确定回退该贷款?', {icon: 3, title:'注意'}, function(index){
			    var method = 'GET'
				var url = '/slloan/loan/loannotllback'
				var data = collectData()
				data.id=localStorage.finalID
				submitAjax(method, url, data)
			  layer.close(index);
			});
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
