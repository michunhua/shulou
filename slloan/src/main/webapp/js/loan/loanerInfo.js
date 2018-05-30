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
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#test1' //指定元素
	  }); 
	  
	  //自定义验证规则
	  form.verify({
		  username: function(value){
	      if(value.length < 2){
	        return '名称至少得2个字符啊';
	      }
	    }
		  ,IDnumber: function(value){
		      if(value.length > 18){
		        return '证件号码最多为18位';
		      }
		    }
		 ,mustWrite: function(value){
			 var contents = document.querySelector('#matherland').value.length
		     if(value ==  6 & contents == 0){
		       return '已选其他,请在已选中的右边输入框填写.';
		     }
		   }
		 ,mustland: function(value){
			 var contents = document.querySelector('#haveland').value.length
		     if(value ==  3 & contents == 0 ){
		       return '已选其他,请在已选中的右边输入框填写.';
		     }
		   }
		 ,mustcensus: function(value){
			 var contents = document.querySelector('#havecensus').value.length
		     if(value ==  2 & contents == 0){
		       return '已选其他,请在已选中的右边输入框填写.';
		     }
		   }
		 ,musthouse: function(value){
			 var contents = document.querySelector('#havehouse').value.length
		     if(value ==  6 & contents == 0){
		       return '已选其他,请在已选中的y右边输入框填写。';
		     }
		   }
	  });
	  
	  
	  //监听提交
	  form.on('submit(demo1)', function(data){
		  if(localStorage.createID || localStorage.createTemporaryId) {
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
  data.cname = e('.ch-name').value
  data.ename = e('.en-name').value
  data.paperwork = e('.paperwork-type').value
  data.otherPaperwork = e('.other-paperwork').value
  data.paperNumb = e('.paperwork-numb').value
  data.country = e('.country').value
  data.otherCountry = e('.other-country').value
  data.gender = e('.gender').value
  data.census = e('.census').value
  data.otherCensus = e('.other-census').value
  data.marriage = e('.marriage').value
  data.housing = e('.housing').value
  data.otherHousing = e('.other-housing').value
  data.birthday = e('.birthday').value
  data.currentAddress = e('.current-address').value
  data.residencePhone = e('.residence-phone').value
  data.mobilePhone = e('.mobile-phone').value
  data.email = e('.E-mail').value
  data.code = e('.zip-code').value
  data.career = e('.career').value
  data.unit = e('.unit-industry').value
  data.unitName = e('.unit-name').value
  data.unitAddress = e('.unit-address').value
  data.companyNumber = e('.company-number').value
  data.lastyearIncome = e('.lastyear-income').value
  data.assetSize = e('.asset-size').value
  data.unitPhone = e('.unit-phone').value
  data.unitCode = e('.unit-code').value
  data.jobsType = e('.jobs-type').value
  data.unitTime = e('.unit-time').value
  data.lastunitName = e('.lastunit-name').value
  data.lastunitTime = e('.lastunit-time').value
  data.incomeSource = e('.income-source').value
  data.salary = e('.salary').value
  data.investment = e('.investment').value
  data.rent = e('.rent').value
  data.added = e('.added').value
  data.supportPeople = e('.support-people').value
  data.expenses = e('.expenses').value
  data.communication = e('.communication').value
  data.state = localStorage.createID
  data.ctime ='d'
  data.hi = 'hi'	  
  return data
}


// 发送数据方法
var sendAjax = function(method, url, datas) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	localStorage.createTemporaryId = ""
    	if(data.msg == 'success') {
    		localStorage.createTemporaryId = data.value
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000, 
  			}, function(){
//  				sendsearchData(localStorage.createTemporaryId)
  				window.location.href = '../../slloan/loan/loancom'
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

// 录单提交按钮&发送数据
var sendData = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
  var data = collectData()
  data.rolename = localStorage.purrole
  data.username = localStorage.purusername
  data.city = localStorage.purcity
  data.parentnodeId = localStorage.purid
  data.id = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/loanApplypersonaldata'
    if(!data.id) {
    	 sendAjax(method, url, data)	
    } else {
//    	layer.msg('请登录！', function(){
//			window.location.href = '/slloan/user/signin'
//			}); 
    }
  })
}

//验证通过后提交
var testsend = function() {
	 var data = collectData()
	  data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.createID
	    var method = 'POST'
	    var url = '/slloan/loan/loanApplypersonaldata'
	    if(!data.id) {
	    	 sendAjax(method, url, data)	
	    } else {
//	    	layer.msg('请登录！', function(){
//				window.location.href = '/slloan/user/signin'
//				}); 
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

// 设置页面数据
var searchExport = function(back) {
    // 获取页面元素
	cname = e('.ch-name')
	ename = e('.en-name')
	paperwork = e('.paperwork-type')
	otherPaperwork = e('.other-paperwork')
	paperNumb = e('.paperwork-numb')
	country = e('.country')
	otherCountry = e('.other-country')
	gender = e('.gender')
	census = e('.census')
	otherCensus = e('.other-census')
	marriage = e('.marriage')
	housing = e('.housing')
	otherHousing = e('.other-housing')
	birthday = e('.birthday')
	currentAddress = e('.current-address')
	residencePhone = e('.residence-phone')
	mobilePhone = e('.mobile-phone')
	email = e('.E-mail')
	code = e('.zip-code')
	career = e('.career')
	unit = e('.unit-industry')
	unitName = e('.unit-name')
	unitAddress = e('.unit-address')
	companyNumber = e('.company-number')
	lastyearIncome = e('.lastyear-income')
	assetSize = e('.asset-size')
	unitPhone = e('.unit-phone')
	unitCode = e('.unit-code')
	jobsType = e('.jobs-type')
	unitTime = e('.unit-time')
	lastunitName = e('.lastunit-name')
	lastunitTime = e('.lastunit-time')
	incomeSource = e('.income-source')
	salary = e('.salary')
	investment = e('.investment')
	rent = e('.rent')
	added = e('.added')
	supportPeople = e('.support-people')
	expenses = e('.expenses')
	communication = e('.communication')
	
    //设置具体值
	cname.value = back.name
	ename.value = back.phoneticize
	paperwork.value = back.id_type
	otherPaperwork.value = back.other_identity_types
	paperNumb.value = back.id_number
	country.value = back.country_and_region
	otherCountry.value = back.other_Countries
	gender.value = back.sex
	census.value = back.Local_domicile
	otherCensus.value = back.household_registration
	marriage.value = back.marital_status
	housing.value = back.housing_condition_now
	otherHousing.value = back.otherCensus
	birthday.value = back.birthday
	currentAddress.value = back.home_address_now
	residencePhone.value = back.home_phone
	mobilePhone.value = back.mobile_phone
	email.value = back.email
	code.value = back.present_address_zip_code
	career.value = back.vocation
	unit.value = back.unit_industry
	unitName.value = back.uni_name
	unitAddress.value = back.unit_address
	companyNumber.value = back.enterprise_scale
	lastyearIncome.value = back.revenue_previous_year
	assetSize.value = back.asset_scale
	unitPhone.value = back.unit_phone
	unitCode.value = back.postCode
	jobsType.value = back.job_category
	unitTime.value = back.seniority
	lastunitName.value = back.former_unit_name
	lastunitTime.value = back.former_seniority
	incomeSource.value = back.source_of_income
	salary.value = back.monthly_income
	investment.value = back.income_from_investment
	rent.value = back.supportPeople
	added.value = back.other_income
	supportPeople.value = back.family_number
	expenses.value =  back.monthly_expenditure
	communication.value = back.monthly_expenditure
	
	// 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".paperwork-type").val(back.id_type);
		  $(".country").val(back.country_and_region);
		  $(".gender").val(back.sex);
		  $(".census").val(back.local_domicile);
		  $(".marriage").val(back.marital_status);
		  $(".housing").val(back.housing_condition_now);
		  $(".communication").val(back.postal_address);
		  form.render()
		});
}

// 查询
// 发送数据方法
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

//查询
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = localStorage.createID
	if(data.id) {
		searchAjax(method, url, data)	
	}
}

//保存后查询
function sendsearchData(result) {
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = result
	if(data.id) {
		searchAjax(method, url, data)	
	}
}



// 修改保存数据
var updateData = function(element) {
  var evs = e(element)
  evs.addEventListener('click', function() {
    var data = collectData()
    data.id = localStorage.createID 
    var method = 'POST'
    var url = '/slloan/loan/perupdate'
    if(data.id || localStorage.createTemporaryId) {
    	 sendAjax(method, url, data)	
    }
  })
}

//修改保存验证
var updatevalid = function() {
    var data = collectData()
    data.id = localStorage.createID || localStorage.createTemporaryId
    var method = 'POST'
    var url = '/slloan/loan/perupdate'
    if(data.id || localStorage.createTemporaryId) {
    	 sendAjax(method, url, data)	
    }
}

// 自定义验证
var validateContent = function() {
	  var data = {}
	  data.cname = e('.ch-name').value
	  data.ename = e('.en-name').value
	  data.paperwork = e('.paperwork-type').value
	  data.otherPaperwork = e('.other-paperwork').value
	  data.paperNumb = e('.paperwork-numb').value
	  data.country = e('.country').value
	  data.otherCountry = e('.other-country').value
	  data.gender = e('.gender').value
	  data.census = e('.census').value
	  data.otherCensus = e('.other-census').value
	  data.marriage = e('.marriage').value
	  data.housing = e('.housing').value
	  data.otherHousing = e('.other-housing').value
	  data.birthday = e('.birthday').value
	  data.currentAddress = e('.current-address').value
	  data.residencePhone = e('.residence-phone').value
	  data.mobilePhone = e('.mobile-phone').value
	  data.email = e('.E-mail').value
	  data.code = e('.zip-code').value
	  data.career = e('.career').value
	  data.unit = e('.unit-industry').value
	  data.unitName = e('.unit-name').value
	  data.unitAddress = e('.unit-address').value
	  data.companyNumber = e('.company-number').value
	  data.lastyearIncome = e('.lastyear-income').value
	  data.assetSize = e('.asset-size').value
	  data.unitPhone = e('.unit-phone').value
	  data.unitCode = e('.unit-code').value
	  data.jobsType = e('.jobs-type').value
	  data.unitTime = e('.unit-time').value
	  data.lastunitName = e('.lastunit-name').value
	  data.lastunitTime = e('.lastunit-time').value
	  data.incomeSource = e('.income-source').value
	  data.salary = e('.salary').value
	  data.investment = e('.investment').value
	  data.rent = e('.rent').value
	  data.added = e('.added').value
	  data.supportPeople = e('.support-people').value
	  data.expenses = e('.expenses').value
	  data.communication = e('.communication').value
	  data.state = 'c'
	  data.ctime ='d'
	  data.hi = 'hi'	  
	  return data
}



//
var __main = function() {
  cancelBtn('#cancel')
//  sendData('#save-data')
  searchData()
//  updateData('#save-data')
  sendsearchData(localStorage.createTemporaryId)
}

__main()
