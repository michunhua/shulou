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
