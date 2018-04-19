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
  return data
}


// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = ''
    var url = ''
    log(data)
    sendAjax(method, url, data, null)
  })
}

//设置页面数据
var searchExport = function(back) {
	amount = e('.amount')
	term = e('.deadline')
	unit = e('.unit')
	variety = e('.variety')
	repayment = e('.manner')
	beneficiarybank = e('.bank')
	bankaccount = e('.account-name')
	receivingAccount = e('.account')
	repaymenBtank = e('.repay-bank')
	repaymentAccount = e('.repay-ccount')
	accountNumber = e('.reapy-accountbank')
	  
	  amount.value = back.id
	  term.value = back.id
	  unit.value = back.id
	  variety.value = back.id
	  repayment.value = back.id
	  beneficiarybank.value = back.id
	  bankaccount.value = back.id
	  receivingAccount.value = back.id
	  repaymenBtank.value = back.id
	  repaymentAccount.value = back.id
	  accountNumber.value = back.id
}

var initback = {
		id: '21'
}

searchExport(initback)

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

			}
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = 10
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//
var __main = function() {
  log( "run")
  sendData('#save-loaner')
}

__main()
