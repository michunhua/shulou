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
  log('收集数据')
  var data = {}
  data.amount = e('.amount').value
  data.deadline = e('.deadline').value
  data.unit = e('.unit').value
  data.variety = e('.variety').value
  data.manner = e('.manner').value
  data.bank = e('.bank').value
  data.accountName = e('.account-name').value
  data.account = e('.account').value
  data.repayBank = e('.repay-bank').value
  data.repayCcount = e('.repay-ccount').value
  data.reapyAccountbank = e('.reapy-accountbank').value
  data.state = 'a'
  data.ctime = 'b'
	  data.role = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.id = localStorage.purid
		  return data
}
	
// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
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
  				window.location.href = '../../slloan/loan/loanesta'
  			});
    	}
    }
  })
}


// 提交按钮点击事件&发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = e(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/ApplyLoaninformations'
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
	  amount = e('.amount')
	  deadline = e('.deadline')
	  unit = e('.unit')
	  variety = e('.variety')
	  manner = e('.manner')
	  bank = e('.bank')
	  accountName = e('.account-name')
	  account = e('.account')
	  repayBank = e('.repay-bank')
	  repayCcount = e('.repay-ccount')
	  reapyAccountbank = e('.reapy-accountbank')
	  
	  amount.value = back.amount
	  deadline.value = back.time_Limit
	  unit.value = back.id
	  variety.value = back.id
	  manner.value = back.id
	  bank.value = back.receiving_Account_Name
	  accountName.value = back.receiving_Account
	  account.value = back.repayment_Bank_name
	  repayBank.value = back.repayment_Account_Name
	  repayCcount.value = back.repayment_Account_Number
	  reapyAccountbank.value = back.id
}

var initback = {
		id: '21'
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
			}
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/loanlinkfab'
	var data = {}
//	data.id = localStorage.createID
   data.id= 3
	if(data.id) {
		searchAjax(method, url, data)	
	}
}

//
var __main = function() {
  log( "run")
  sendData('#save-loaner')
  cancelBtn('#cancel')
  searchData()
}

__main()
