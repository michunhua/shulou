log('loanInfo')

// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.amount = e('.amount').value
  data.term = e('.term').value
  data.unit = e('.unit').value
  data.variety = e('.variety').value
  data.repayment = e('.repayment').value
  data.beneficiarybank = e('.beneficiary-bank').value
  data.bankaccount = e('.bank-account').value
  data.receivingAccount = e('.receiving-account').value
  data.repaymenBtank = e('.repayment-bank').value
  data.repaymentAccount = e('.repayment-account').value
  data.accountNumber = e('.account-number').value
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas) {
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
  				window.location.href = '../../slloan/loan/loanestas'
  			});
    	}
    }
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
    sendAjax(method, url, data)
  })
}

//设置页面数据
var searchExport = function(back) {
	  amount = e('.amount')
	  term = e('.term')
	  unit = e('.unit')
	  variety = e('.variety')
	  repayment = e('.repayment')
	  beneficiarybank = e('.beneficiary-bank')
	  bankaccount = e('.bank-account')
	  receivingAccount = e('.receiving-account')
	  repaymenBtank = e('.repayment-bank')
	  repaymentAccount = e('.repayment-account')
	  accountNumber = e('.account-number')
	  
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
type: method,
url: url,
data: {data:JSON.stringify(datas)},
success: function(data) {
	console.log('返回数据', data)
	if(data.msg == 'success') {
	}
}
})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/applyforts'
	var data = {}
	data.id = 4
	searchAjax(method, url, data)
}


//
var __main = function() {
  log( "run")
  sendData('#save-data')
  searchData()
}

__main()
