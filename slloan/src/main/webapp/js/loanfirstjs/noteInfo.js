// 收集信息
var collectData = function() {
  var data = {}
  data.note = e('.record-note').value
  data.recordFirst = e('.first-trial').value
  data.recorFinal = ''
  data.recorfore = ''
  
  data.rolename = localStorage.purrole
  data.username = localStorage.purusername
  data.city = localStorage.purcity
  data.parentnodeId = localStorage.purid
  data.state = localStorage.firstID
  data.ctime = ''
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas) {
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


// 修改发送数据
var sendData = function(element) {
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    var data = collectData()
    data.id = localStorage.firstID
    var method = 'POST'
    var url = '/slloan/loan/updatenotetwo'
    if(data.id) {
        sendAjax(method, url, data)
    }
  })
}

//设置页面数据
var searchExport = function(back) {
	recordNote = e('.record-note')
	recordFirst = e('.first-trial')
	recordNotes = e('.final-note')
	recordFirsts = e('.financeNote')
	
	  if(back.note_Description1 && back.note_Description1 != "null") {
		  recordNote.value =  back.note_Description1
	  }
	
	  if(back.note_Description2 && back.note_Description2 != "null") {
		  recordFirst.value =  back.note_Description2
	  }
	
	  if(back.note_Description3 && back.note_Description3 != "null") {
		  e('#final-back').style.display = 'block'
		  recordNotes.value =  back.note_Description3
	  } 
	  if(back.note_Description4 && back.note_Description4 != "null") {
		  e('#final-record').style.display = 'block'
		  recordFirsts.value = back.note_Description4
	  }
}

//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				if(data.obj != null) {
					searchExport(data.obj)
				}
			} else {
				alert('这页资料尚未填写')
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
	data.id = localStorage.firstID
	data.rolename = localStorage.purrole
	  data.userName = localStorage.purusername
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
    window.location.href = "/slloan/loan/loancreas"
  })
}


// 提交方法
var submitAjax = function(method, url, datas) {
	  $.ajax({
	    type: method,
	    url: url,
	    data: {data:JSON.stringify(datas)},
	    success: function(data) {
	    	if(data.msg == 'success') {
	    		layer.msg('提交成功', {
	  			  icon: 2,
	  			  time: 2000 
	  			}, function(){
	  				window.location.href = "/slloan/loan/loancreas"
	  			});
	    	} else if(data.msg == 'fail'){
	    		alert(data.value)
	    	}else {
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
			layer.confirm('通过该贷款?', {icon: 3, title:'注意'}, function(index){
			    var method = 'GET'
				var url = '/slloan/loan/loannotsubmit'
				var data = collectData()
				data.id = localStorage.firstID
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
	  				window.location.href = "/slloan/loan/loancreas"
	  			});
	    	}else if(data.msg == 'fail'){
	    		alert(data.value)
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
				var url = '/slloan/loan/loannotFallback'
				var data = collectData()
			data.id = localStorage.firstID
			data.rolename = localStorage.purrole
		    data.username = localStorage.purusername
		    data.city = localStorage.purcity
			data.parentnodeId = localStorage.purid
			backAjax(method, url, data)
			  layer.close(index);
			});
	})
}

//
var __main = function() {
  sendData('#save-data')
  cancelBtn("#cancel")
  searchData()
  submitBtn('#submit')
  backBtn("#go-back")
}

__main()
