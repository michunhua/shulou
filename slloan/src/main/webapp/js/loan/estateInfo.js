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
  var data = {}
  data.owner = e('.owner').value
  data.accounting = e('.accounting').value
  data.propertyAddress = e('.property-address').value
  data.building = e('.building').value
  data.innerArea = e('.inner-area').value
  data.contract = e('.contract').value
  data.certificate = e('.certificate').value
  data.evaluation = e('.evaluation').value
  data.property = e('.property').value
  data.originalBank = e('.original-bank').value
  data.originalBank = e('.original-amount').value
  data.houseAccount = e('.house-account').value
  data.originalOwed = e('.original-owed').value
  data.bidPrice = e('.bid-price').value
  data.deposit = e('.deposit').value
  data.newBank = e('.new-bank').value
  data.funds = e('.funds').value
  data.newAccount = e('.new-account').value
  data.newApproved = e('.new-approved').value
  data.state='a'
  data.ctiam='b'
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
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
  				window.location.href = '../../slloan/loan/loanimag'
  			});
    	}
    }
  })
}


// 提交按钮点击事件&发送数据
var sendData = function() {
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/housepropertydata'
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
	  owner = e('.owner')
	  accounting = e('.accounting')
	  propertyAddress = e('.property-address')
	  building = e('.building')
	  innerArea = e('.inner-area')
	  contract = e('.contract')
	  certificate = e('.certificate')
	  evaluation = e('.evaluation')
	  property = e('.property')
	  originalBank = e('.original-bank')
	  originalAmount = e('.original-amount')
	  houseAccount = e('.house-account')
	  originalOwed = e('.original-owed')
	  bidPrice = e('.bid-price')
	  deposit = e('.deposit')
	  newBank = e('.new-bank')
	  funds = e('.funds')
	  newAccount = e('.new-account')
	  newApproved = e('.new-approved')
	  
	  owner.value = back.ownership_And_percentage
	  accounting.value = back.property_Address
	  propertyAddress.value = back.conStruction_Area
	  building.value = back.inner_Area
	  innerArea.value = back.sales_Contract_Number
	  contract.value = back.certificate_of_Title
	  certificate.value = back.proPerty_for
	  evaluation.value = back.the_Assessed_Value
	  property.value = back.id
	  originalBank.value = back.id
	  originalAmount.value = back.loan_Outstanding_Balance
	  houseAccount.value = back.id
	  originalOwed.value = back.transaCtion_Price
	  bidPrice.value = back.purchase_Deposit
	  deposit.value = back.supervision_of_funds
	  newBank.value = back.new_loan_Bank
	  funds.value = back.new_Loan_Approval_Amount
	  newAccount.value = back.new_Loan_Bank_Account_Number
	  newApproved.value = back.note_DescriPtion
}

var initback = {
		id: '20'
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
	var url = '/slloan/loan/pper'
	var data = {}
//	data.id = localStorage.createID
	data.id = 2
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//
var __main = function() {
  sendData()
  cancelBtn('#cancel')
  searchData()
}

__main()
