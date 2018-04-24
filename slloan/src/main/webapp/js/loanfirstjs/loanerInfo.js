log('loanInfo')

//依赖库方法
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
  				window.location.href = '../../slloan/loan/loancoms'
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
    var method = 'POST'
    var url = '/slloan/loan/perupdate'
    	  data.id = 4
    log(data)
    sendAjax(method, url, data)
  })
}

//设置页面数据
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

// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = 4
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
  sendData('#save-data')
  cancelBtn("#cancel")
  searchData()
}

__main()
