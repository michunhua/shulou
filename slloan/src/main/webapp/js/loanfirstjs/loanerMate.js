// 收集信息
var collectData = function() {
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
  data.id = localStorage.firstID	  
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
  				window.location.href = '../../slloan/loan/loancomms'
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
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/spoupdatea'
    	data.id =localStorage.firstID
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
	  certificateType.value = back.id_Other
	  documents.value = back.id_Number
	  untilName.value = back.uni_Name
	  untilPhone.value = back.unit_Phone
	  residence.value = back.home_Phone
	  mobile.value = back.mobile_Phone
	  salary.value = back.monthly_Income
	  
	  // 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".certificate").val(back.id_Type);
		  form.render()
		});
	  
}

//婚姻状态
var MarryState = ''
//查询
//发送数据方法
var MarryStateAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				MarryState = data.obj.marital_status
				searchData()
			} else {
				alert('服务器错误')
			}
		},
		error: function() {
			alert('服务器错误')
		}
	})
}

//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
$.ajax({
type: method,
url: url,
data: {data:JSON.stringify(datas)},
success: function(data) {
	if(data.msg == 'success' || MarryState == "1") {
		if(data.msg == 'success') {
			searchExport(data.obj)
		}
	}else {
		alert('这页资料尚未填写')
	}
},
error: function(){
    alert('服务器错误')
 }
})
}

//查询是否已婚
var searchMarry = function() {
	var method = 'GET'
	var url = '/slloan/loan/personalpmake'
	var data = {}
	data.id = localStorage.firstID
	if(data.id) {
		MarryStateAjax(method, url, data)
	}
}

// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id = localStorage.firstID
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

//
var __main = function() {
  searchMarry()
  sendData('#save-data')
  cancelBtn("#cancel")
//  searchData()
}

__main()
