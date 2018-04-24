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
	log('收集数据')
	var data = {}
	// 固定值
	data.cname = e('.ch-name').value
	data.paperwork = e('.paperwork-type').value
	data.paperNumb = e('.paperwork-numb').value
	data.mobilePhone = e('.mobile-phone').value
	data.unitName = e('.unit-name').value
	data.unitPhone = e('.unit-phone').value
	data.housePhone = e('.house-phone').value
	data.salary = e('.salary').value
	data.state = 'a'
	data.ctime = 'b'
	data.other = e('.document-number').value
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
				console.log('sadas')
				layer.msg('保存成功', {
					icon : 2,
					time : 2000
				}, function() {
					window.location.href = '../../slloan/loan/loanapply'
				});
			}
		}
	})
}

// 提交按钮点击事件&发送数据
var sendData = function(element) {
	var evs = e(element)
	evs.addEventListener('click', function() {
		var data = collectData()
		var method = 'POST'
		var url = '/slloan/loan/commonApplyspouse'
		log(data)
		sendAjax(method, url, data)
	})
}

// 取消按钮事件
var cancelBtn = function(element) {
	var forms = e('form')
	var evs = e(element)
	evs.addEventListener('click', function() {
		forms.reset()
		window.history.back()
	})
}

//设置页面数据
var searchExport = function(back) {
	  cname = e('.ch-name')
	  certificate = e('.paperwork-type')
	  document = e('.document-number')
	  certificateType = e('.paperwork-numb')
	  mobile = e('.mobile-phone')
	  untilName = e('.unit-name')
	  untilPhone = e('.unit-phone')
	  residence = e('.house-phone')
	  salary = e('.salary')
	  
	  cname.value = back.name
	  certificate.value = back.id
	  document.value = back.id_Number
	  certificateType.value = back.id_Number
	  untilName.value = back.unit_Phone
	  untilPhone.value = back.home_Phone
	  residence.value = back.mobile_Phone
	  mobile.value = back.uni_Name
	  salary.value = back.monthly_Income
}

var initback = {
		id: '23'
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
		searchExport(data.obj)
	}
}
})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/coborrowers'
	var data = {}
//	data.id = localStorage.createID
	data.id = 1
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//
var __main = function() {
	sendData('#save-coMate')
	cancelBtn('#cancel')
	searchData()
}

__main()
