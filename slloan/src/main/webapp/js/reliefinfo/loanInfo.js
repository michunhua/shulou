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
  data.id = 2
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
  				window.location.href = '../../slloan/loan/loanapplyss'
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
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
//    var data = collectData()
    var method = 'POST'
    var url = ''
//    log(data)
//    sendAjax(method, url, data, null)
    window.location.href = '../../slloan/loan/relieinfoestateInfo'
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
	  unit.value = back.receiving_Bank_Name
	  variety.value = back.receiving_Account_Name
	  manner.value = back.receiving_Account
	  bank.value = back.receiving_Bank_Name
	  accountName.value = back.receiving_Account_Name
	  account.value = back.receiving_Account
	  repayBank.value = back.repayment_Bank_Name
	  repayCcount.value = back.repayment_Account_Name
	  reapyAccountbank.value = back.repayment_Account_Number
	  

		// 下拉选项
		  layui.use('form', function(){
			  var form = layui.form;
			  $(".unit").val(back.time_Limits);
			  $(".variety").val(back.borrowing_Variety);
			  $(".manner").val(back.repayment);
			  form.render()
			});
}

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
	var url = '/slloan/loan/loanlinkfab'
	var data = {}
	data.id = localStorage.relieID
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
//    window.location.href = "/slloan/loan/loancreass"
    window.location.href = '../../slloan/loan/reli'
  })
}

//
var __main = function() {
  log( "run")
  searchData()
  sendData('#save-loaner')
  cancelBtn('#cancel')
}

__main()
