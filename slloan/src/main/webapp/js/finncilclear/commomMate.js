$('input,select,textarea').prop('readonly',true);

// 收集信息
var collectData = function() {
  var data = {}
  // 固定值
  data.cname = e('.ch-name').value
  data.certificate = e('.certificate').value
  data.certificateType = e('.certificate-type').value
  data.documents = e('.document-number').value
  data.untilName = e('.until-name').value
  data.untilPhone = e('.until-phone').value
  data.residence = e('.residence-phone').value
  data.mobile = e('.mobile-phone').value
  data.salary = e('.salary').value
  data.id = 2
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
  				window.location.href = '../../slloan/loan/loancommss'
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
    var url = '/slloan/loan/save'
    window.location.href = '../../slloan/loan/finncilclearthree'
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


// 查询
// 发送数据方法
var searchAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				searchExport(data.obj)
			} else {
				alert('这页资料尚未填写')
			}
		},
	    error: function(){
	        alert('服务器错误')
	     }		
	})
}

// 查询
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/coborrowers'
	var data = {}
	data.id = localStorage.clearID
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
    window.location.href = '../../slloan/financial/settlementvoucher'
  })
}

//
var __main = function() {
  searchData()
  sendData('#save-coMate')
  cancelBtn('#cancel')
}

__main()
