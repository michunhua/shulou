//设置查询数据
var setSearchData = function(data) {
  note = e('.note')
  firstNote = e('.first-note')
  finalNote = e('.final-note')
  financeNote = e('.financeNote')
  
  if(data.note_Description1 && data.note_Description1 != "null") {
	  note.value = data.note_Description1  
  }
  
  if(data.note_Description2 && data.note_Description2 != "null") {
	  firstNote.value = data.note_Description2  
  }
  
  if(data.note_Description3 && data.note_Description3 != "null") {
	  finalNote.value = data.note_Description3 
  }
  
  if(data.note_Description4 && data.note_Description4 != "null") {
	  financeNote.value = data.note_Description4
  }

}


//  默认查询
//查询方法
var searchAjax = function(method, url, datas) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		setSearchData(data.obj)
    	} else {
    		alert('这页资料尚未填写')
    	}
    },
    error: function() {
		alert('服务器错误')
	}
  })
}

// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/notedescripti'
	var data = {}
	 data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.financialID
	if(url) {
		searchAjax(method, url, data)
	}
}

//收集信息
var collectData = function() {
  var data = {}
  data.note = e('.note').value
  data.recordFirst = e('.first-note').value
  data.recorFinal = e('.final-note').value
  data.recorfore = e('.financeNote').value
  data.state = ''
	  data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.financialID
  return data
}


// 保存
//保存方法
var saveAjax = function(method, url, datas) {
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
//	      				window.location.href = "/slloan/loan/loancrea"
	      			});
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//保存按钮保存数据
var saveBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		var method = 'POST'
		var url = '/slloan/loan/updatenotefore'
		var data = collectData()
			 data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.financialID
		if(url) {
			saveAjax(method, url, data)
		}
	})
}

// 取消
//取消按钮事件
var cancelBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
//		 window.history.back()
		    window.location.href = '../../slloan/financial/financeapproval'
	})
}

// 提交
//提交方法
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
	      			}, function() {
	      				window.location.href = '../../slloan/financial/financeapproval'
	      			})
	    	}else if(data.msg == 'fail'){
	    		alert(data.value)
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//提交按钮提交数据
var submitBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		layer.confirm('确定通过该贷款?', {icon: 3, title:'注意'}, function(index){
		    var method = 'GET'
			var url = '/slloan/loan/transferaccounts'
			var data = collectData()
			if(url) {
				submitAjax(method, url, data)
			}
		  layer.close(index);
		});
	})
}

// 回退
//回退方法
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
	      			}, function() {
	      				window.location.href = '../../slloan/financial/financeapproval'
	      			})
	    	}else if(data.msg == 'fail'){
	    		alert(data.value)
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//回退按钮回退数据
var goBackBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		layer.confirm('确定回退该贷款?', {icon: 3, title:'注意'}, function(index){
			    var method = 'GET'
				var url = '/slloan/loan/loannotllbacks'
				var data = collectData()
				if(url) {
					backAjax(method, url, data)
				}
			  layer.close(index);
			});
	})
}


var __main = function() {
	searchData()
	saveBtn('#save-data')
	cancelBtn("#cancel")
	submitBtn("#submit")
	goBackBtn("#go-back")
}

__main()
