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
  data.cname = e('.ch-name').value
  data.ename = e('.en-name').value
  data.paperwork = e('.paperwork-type').value
  data.paperNumb = e('.paperwork-numb').value
  data.country = e('.country').value
  data.gender = e('.gender').value
  data.census = e('.census').value
  data.marriage = e('.marriage').value
  data.housing = e('.housing').value
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
  data.a =  12.5
  data.b='b'
  data.state = 'c'
  data.ctime ='d'
	  data.abc = 'qwe'
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
    	console.log('12316546465')
    	if(data.msg == 'success') {
    		console.log('666666')
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
  				window.location.href = '../../slloan/loan/loancom'
  			});
    	}
    }
  })
}


// 提交按钮&发送数据
var sendData = function() {
  log('send data to server')
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/loanApplypersonaldata'
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

// 设置页面数据
var searchExport = function(back) {
	cname = e('.ch-name')
	ename = e('.en-name')
	paperwork = e('.paperwork-type')
	paperNumb = e('.paperwork-numb')
	country = e('.country')
	gender = e('.gender')
	census = e('.census')
	marriage = e('.marriage')
	housing = e('.housing')
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
	expenses = e('.expenses').value
	communication = e('.communication')

	cname.value = back.name
	ename.value = back.phoneticize
	paperwork.value = back.id_type
	paperNumb.value = back.id_number
	country.value = back.country_and_region
	gender.value = back.sex
	census.value = back.Local_domicile
	marriage.value = back.marital_status
	housing.value = back.housing_condition_now
	birthday.value = back.marital_status
	currentAddress.value = back.housing_condition_now
	residencePhone.value = back.birthday
	mobilePhone.value = back.home_address_now
	email.value = back.home_phone
	code.value = back.mobile_phone
	career.value = back.email
	unit.value = back.present_address_zip_code
	unitName.value = back.vocation
	unitAddress.value = back.unit_industry
	companyNumber.value = back.uni_name
	lastyearIncome.value = back.unit_address
	assetSize.value = back.enterprise_scale
	unitPhone.value = back.revenue_in_the_previous_year
	unitCode.value = back.postCode
	jobsType.value = back.unit_phone
	unitTime.value = back.postCode
	lastunitName.value = back.job_category
	lastunitTime.value = back.seniority
	incomeSource.value = back.former_unit_name
	salary.value = back.former_seniority
	investment.value = back.source_of_income
	rent.value = back.monthly_income
	added.value = back.income_from_investment
	supportPeople.value = back.seniority
	expenses.value = back.other_income
	communication.value = back.postal_address
}

var initback = {
	id : '25'
}

//searchExport(initback)

// 查询
// 发送数据方法
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
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = 1
	if(data.id) {
		searchAjax(method, url, data)	
	}
}

// 提交表单
var sendFormData = function() {
  log('send data to server')
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/loanApplypersonaldata'
    log(data)
    sendAjax(method, url, data)
}


//开启验证 后提交表单
$.validator.setDefaults({
    submitHandler: function() {
//      alert("提交事件!");
    	sendFormData()
    }
});

$().ready(function() {
    $("#loaner").validate({
    	rules: {
    	      ctitle: {
    	        required: true,
    	        minlength: 2
    	      },
    	      etitle: {
    	        required: true,
    	        minlength: 3
    	      },
    	      CardId: {
    	        required: true,
    	        minlength: 5
    	      },
    	      currentAddress: {
    	    	required: true,
      	        minlength: 12
    	      }
    	    },
    	    messages: {
    	      ctitle: {
    	    	required:  "请输入用户名",
    	        minlength: "用户名必需由两个字母组成"
    	      },
    	      etitle: {
    	    	required:  "请输证件号码",
    	        minlength: "密码长度不能小于 3 个字母"
    	      },
    	      CardId: {
    	    	required:  "请输证件号码",
    	        minlength: "证件长度不能小于 16 个"
    	      },
    	      currentAddress: {
    	    	required:  "请输入现住址",
      	        minlength: "请正确输入现住址"
    	      }
    	     }
    });
});

//
var __main = function() {
  log( "run")
  cancelBtn('#cancel')
  searchData()
}

__main()
