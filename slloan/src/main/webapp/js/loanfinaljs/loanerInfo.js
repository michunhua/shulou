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
var sendData = function() {
  log('send data to server')
  var evs = document.querySelector('#save-loaner')
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
var searchExport = function(data) {
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
	expenses = e('.expenses').value
	communication = e('.communication')
	
    //设置具体值
	cname.value = data.cname
	ename.value = data.ename
	paperwork.value = data.paperwork
	otherPaperwork.value = data.otherPaperwork
	paperNumb.value = data.paperNumb
	country.value = data.country
	otherCountry.value = data.otherCountry
	gender.value = data.gender
	census.value = data.census
	otherCensus.value = data.otherCensus
	marriage.value = data.marriage
	housing.value = data.housing
	otherHousing.value = data.otherHousing
	birthday.value = data.birthday
	currentAddress.value = data.currentAddress
	residencePhone.value = data.residencePhone
	mobilePhone.value = data.mobilePhone
	email.value = data.email
	code.value = data.code
	career.value = data.career
	unit.value = data.unit
	unitName.value = data.unitName
	unitAddress.value = data.unitAddress
	companyNumber.value = data.companyNumber
	lastyearIncome.value = data.lastyearIncome
	assetSize.value = data.assetSize
	unitPhone.value = data.unitPhone
	unitCode.value = data.unitCode
	jobsType.value = data.jobsType
	unitTime.value = data.unitTime
	lastunitName.value = data.lastunitName
	lastunitTime.value = data.lastunitTime
	incomeSource.value = data.incomeSource
	salary.value = data.salary
	investment.value = data.investment
	rent.value = data.rent
	added.value = data.added
	supportPeople.value = data.supportPeople
	expenses.value =  data.expenses 
	communication.value = data.communication 
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
  sendData()
}

__main()
