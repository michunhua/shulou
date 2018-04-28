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
	data.id = localStorage.finalID
	data.state = ''
		data.ctime = ''
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
    	console.log('返回数据')
		if (data.msg == 'success') {
			
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
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/modifyusers'
    log(data)
    sendAjax(method, url, data, null)
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
	  
	  amount.value = back.amount
	  term.value = back.time_Limit
	  unit.value = back.repayment
	  variety.value = back.borrowing_Variety
	  repayment.value = back.receiving_Bank_Name
	  beneficiarybank.value = back.receiving_Account
	  bankaccount.value = back.repayment_Bank_name
	  receivingAccount.value = back.repayment_Account_Name
	  repaymenBtank.value = back.repayment_Account_Number
	  repaymentAccount.value = back.id
	  accountNumber.value = back.id
	  

		// 下拉选项
		  layui.use('form', function(){
			  var form = layui.form;
			  $(".unit").val(back.repayment);
			  $(".variety").val(back.borrowing_Variety);
			  $(".repayment").val(back.receiving_Bank_Name);
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
				alert('服务器错误')
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
	data.id = localStorage.finalID
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
    window.history.back()
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
