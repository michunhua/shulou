//自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
	return document.querySelector(elements)
}

// 依赖库方法
layui.use('laydate', function() {
	var laydate = layui.laydate;

	// 执行一个laydate实例
	laydate.render({
		elem : '#test1' // 指定元素
	});
});

// 收集信息
var collectData = function() {
	var data = {}
	// 固定值
	data.cname = e('.ch-name').value
	data.certificate = e('.paperwork-type').value
	data.certificateType = e('.document-number').value
	data.document = e('.paperwork-numb').value
	data.mobile = e('.mobile-phone').value
	data.untilName = e('.unit-name').value
	data.untilPhone = e('.unit-phone').value
	data.residence = e('.house-phone').value
	data.salary = e('.salary').value
	data.state = 'a'
	data.ctime = 'b'
	return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				layer.msg('保存成功', {
					icon : 2,
					time : 2000
				}, function() {
					localStorage.commmate = data.value
					window.location.href = '../../slloan/loan/loanapply'
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

// 提交按钮点击事件&发送数据
var sendData = function(element) {
	var evs = e(element)
	evs.addEventListener('click', function() {
		var data = collectData()
		data.temporaryId = localStorage.createTemporaryId
		data.mark = localStorage.createID
		var method = 'POST'
		var url = '/slloan/loan/commonApplyspouse'
		if(!data.mark) {
			sendAjax(method, url, data)	
		}
	})
}

// 取消按钮事件
var cancelBtn = function(element) {
	var forms = e('form')
	var evs = e(element)
	evs.addEventListener('click', function() {
		forms.reset()
		window.location.href = "/slloan/loan/loancrea"
	})
}

//设置页面数据
var searchExport = function(back) {
	  cname = e('.ch-name')
	  certificate = e('.paperwork-type')
	  documents = e('.document-number')
	  certificateType = e('.paperwork-numb')
	  mobile = e('.mobile-phone')
	  untilName = e('.unit-name')
	  untilPhone = e('.unit-phone')
	  residence = e('.house-phone')
	  salary = e('.salary')
	  
	  cname.value = back.name
	  certificateType.value = back.id_Number
	  documents.value = back.id_Other
	  untilName.value = back.uni_Name
	  untilPhone.value = back.unit_Phone
	  residence.value = back.home_Phone
	  mobile.value = back.mobile_Phone
	  salary.value = back.monthly_Income
	  
		// 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".paperwork-type").val(back.id_Type);
		  form.render()
		});
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
	var url = '/slloan/loan/coborrowers'
	var data = {}
	data.id = localStorage.createID
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//保存后查询数据
function sendsearchData(result) {
	var method = 'GET'
	var url = '/slloan/loan/coborrowers'
	var data = {}
	data.id = result
	if(data.id & localStorage.commmate) {
		searchAjax(method, url, data)
	}
}

//  修改数据保存
var updateData = function(element) {
	var evs = e(element)
	evs.addEventListener('click', function() {
		var data = collectData()
	    data.id = localStorage.createID		
		var method = 'POST'
		var url = '/slloan/loan/cobupdate'
		if(data.id) {
			sendAjax(method, url, data)
		}
	})
}

//
var __main = function() {
	sendData('#save-coMate')
	cancelBtn('#cancel')
	searchData()
	updateData('#save-coMate')
	sendsearchData(localStorage.createTemporaryId)
}

__main()
