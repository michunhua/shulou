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
  data.state = localStorage.createID
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
    	if(data.msg == 'success') {
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
  				localStorage.loanernote = data.value
  				sendsearchData(localStorage.createTemporaryId)
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
var searchExport = function(data) {
	recordNote = e('.note')
	recordNotes = e('.first-note')
	recordNotess = e('.final-note')
	recordNotees = e('.financeNote')
	
	if(data.note_Description1 != "undefined" & data.note_Description1 != undefined || data.note_Description1 != "null" & data.note_Description1 == null){
		recordNote.value = data.note_Description1
	  }else if(data.note_Description1 != "undefined" & data.note_Description1 == undefined || data.note_Description1 == "null" & data.note_Description1 == null){
		  recordNote.value = ""
	  }
	if(data.note_Description2 && data.note_Description2 != "null") {
		e('#record-first').style.display = 'block'
		recordNotes.value = data.note_Description2
	}
	if(data.note_Description3 && data.note_Description3 != "null") {
		e('#record-final').style.display = 'block'
		recordNotess.value = data.note_Description3
	}
	if(data.note_Description4 && data.note_Description4 != "null") {
		e('#record-fiance').style.display = 'block'
		recordNotees.value = data.note_Description4
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


//保存后查询数据
function sendsearchData(result) {
	var method = 'GET'
	var url = '/slloan/loan/notedescripti'
	var data = {}
	data.id = result
	data.rolename = localStorage.purrole
	data.username = localStorage.purusername
	data.city = localStorage.purcity
	data.parentnodeId = localStorage.purid
	if(data.id & localStorage.loanernote) {
		searchAjax(method, url, data)
	}
}

// 依据可否提交
var submitForm = []

//查询已填列表方法
var searchListAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
    	if(data.amount !="null" && data.contacts!="null" && data.name !="null") {
    		submitForm.push(data.amount.amount) 
    		submitForm.push(data.contacts.contacts) 
    		submitForm.push(data.name.name) 
    	} 
	 },
     error: function(){
         alert('服务器错误')
      }	 
	})
}

// 查询已填列表
var searchList = function() {
	var method = 'GET'
	var url = '/slloan/loan/notedescriptis'
	var data = {}
	data.id = localStorage.createTemporaryId || localStorage.createID
	if(data.id) {
		console.log('可提交查询')
		searchListAjax(method, url, data)
	}
}

//判断提交的依据必须存在
var judgment = function(arr) {
	var i = ''
	arr.every(function(index) {
		if(index && index != 'null' && index != 'undefined'){
			i++
		} else {
			return false
		}
		return true
	})
	return i
}

// 提交方法
var submitAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if(data.msg == 'success') {
	    		layer.msg('提交成功', {
	  			  icon: 2,
	  			  time: 2000 
	  			}, function(){
	  				localStorage.createID = ""
	  				localStorage.createTemporaryId = ""
	  				window.location.href = "/slloan/loan/loancrea"
	  			});
	    	} else if(data.msg == 'fail') { 
	    		alert(data.value)
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
    if(data.temporaryId && (localStorage.createID == "")) {
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
		data.id = localStorage.createID || localStorage.createTemporaryId
		data.amount = submitForm[0]
		data.contacts = submitForm[1]
		data.name = submitForm[2]
		if(data.id) {
			if(!data.amount) {
				alert("请先填写申请借款信息必填资料后方可提交")
				return false
			}
			
			if(!data.contacts) {
				alert("请先填写完联系人必填资料后方可提交")
				return false
			}
			
			if(!data.name) {
				alert("请先填写完房产资料必填资料后方可提交")
				return false
			}
			
			if(judgment(submitForm)) {
			    layer.confirm('确定提交?', {icon: 3, title:'注意'}, function(index){
				  submitAjax(method, url, data)
				  layer.close(index);
				});
			} else {
				layer.msg("请先填写完必填资料后方可提交", {time: 3000})
			}
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
    if(data.id) {
			sendAjax(method, url, data)
		}
  })
}

//
var __main = function() {
  sendData('#save-note')
  cancelBtn('#cancel')
  submitBtn('#submit')
  searchData()
  updateData('#save-note')
  sendsearchData(localStorage.createTemporaryId)
  searchList()
}

__main()
