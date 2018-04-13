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
	data.start = 'a'
	data.catime = 'b'
	data.other = 'c'
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
	})
}

//
var __main = function() {
	sendData('#save-coMate')
	cancelBtn('#cancel')
}

__main()
