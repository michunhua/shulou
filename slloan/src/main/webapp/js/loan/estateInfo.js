//自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
  return  document.querySelector(elements)
}

//// 依赖库方法
//layui.use('laydate', function(){
//  var laydate = layui.laydate;
//
//  //执行一个laydate实例
//  laydate.render({
//    elem: '#test1' //指定元素
//  });
//});

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  //自定义验证规则
	  form.verify({
		  username: function(value){
	      if(value.length < 2){
	        return '名称至少得2个字符啊';
	      }
	    }
		 ,mustuse: function(value){
			 var contents = document.querySelector('#mustuse').value.length
		     if(value ==  "3" & contents == 0){
		       return '请填写其他用途';
		     }
		   }
	  });
	  
	  //监听提交
	  form.on('submit(demo1)', function(data){
		  if(localStorage.createID || localStorage.commest) {
			  updatevalid()
		  } else {
			    testsend()	  
		  }
	    return false;
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
	  data.usetodo = e('.usetodo').value
	  data.originalBank = e('.original-bank').value
	  data.originalAmount = e('.original-amount').value
	  data.houseAccount = e('.house-account').value
	  data.originalOwed = e('.original-owed').value
	  data.bidPrice = e('.bid-price').value
	  data.deposit = e('.deposit').value
	  data.newBank = e('.new-bank').value
	  data.funds = e('.funds').value
	  data.newAccount = e('.new-account').value
	  data.newApproved = e('.new-approved').value
	  data.state='a'
	  data.ctime='b'
	  data.id = localStorage.createTemporaryId
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
  				localStorage.commest = data.value
  				window.location.href = '../../slloan/loan/loanimag'
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
var sendData = function() {
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    data.temporaryId = localStorage.createTemporaryId
    data.mark = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/housepropertydata'
    if(!data.mark) {
        sendAjax(method, url, data)	
    }
  })
}

//验证通过后提交 
var testsend = function() {
    var data = collectData()
    data.temporaryId = localStorage.createTemporaryId
    data.mark = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/housepropertydata'
    if(!data.mark) {
        sendAjax(method, url, data)	
    }
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
	  owner = e('.owner')
	  accounting = e('.accounting')
	  propertyAddress = e('.property-address')
	  building = e('.building')
	  innerArea = e('.inner-area')
	  contract = e('.contract')
	  certificate = e('.certificate')
	  evaluation = e('.evaluation')
	  property = e('.property')
	  usetodo = e('.usetodo')
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
	  
	  owner.value = back.name
	  accounting.value = back.ownership_And_percentage
	  propertyAddress.value = back.property_Address
	  building.value = back.conStruction_Area
	  innerArea.value = back.inner_Area
	  contract.value = back.sales_Contract_Number
	  certificate.value = back.certificate_of_Title
	  evaluation.value = back.the_Assessed_Value
	  usetodo.value = back.proPerty_for
	  property.value = back.original_Loan_Amount
	  originalBank.value = back.original_Loan_Bank
	  originalAmount.value = back.original_Loan_Amount
	  houseAccount.value = back.house_Account
	  originalOwed.value = back.loan_Outstanding_Balance
	  bidPrice.value = back.transaCtion_Price
	  deposit.value = back.purchase_Deposit
	  newBank.value = back.new_loan_Bank
	  funds.value = back.supervision_of_funds
	  newAccount.value = back.new_Loan_Bank_Account_Number
	  newApproved.value = back.new_Loan_Approval_Amount
	  
		// 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".property").val(back.note_DescriPtion);
		  form.render()
		});
}

var initback = {
		id: '20'
}

//searchExport(initback)

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
	var url = '/slloan/loan/pper'
	var data = {}
	data.id = localStorage.createID
	if(data.id) {
		console.log('默认查询')
		searchAjax(method, url, data)
	}
}

//保存后查询数据
function sendsearchData(result) {
	var method = 'GET'
	var url = '/slloan/loan/pper'
	var data = {}
	data.id = result
	if(data.id & localStorage.commest) {
		console.log('保存后查询')
		searchAjax(method, url, data)
	}
}

// 保存修改数据
var updateData = function() {
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    data.id = localStorage.createID || localStorage.commest
    var method = 'POST'
    var url = '/slloan/loan/proupdate'
    if(data.id) {
			sendAjax(method, url, data)
		}
  })
}

//验证通过后保存修改数据
var updatevalid = function() {
    var data = collectData()
    data.id = localStorage.createID || localStorage.commest
    var method = 'POST'
    var url = '/slloan/loan/proupdate'
    if(data.id) {
			sendAjax(method, url, data)
		}
}

//
var __main = function() {
//  sendData()
  cancelBtn('#cancel')
  searchData()
//  updateData()
  sendsearchData(localStorage.createTemporaryId)
}

__main()
