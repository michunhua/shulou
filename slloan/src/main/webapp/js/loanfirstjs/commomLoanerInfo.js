log('commomLoanerInfo')

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
  				window.location.href = '../../slloan/loan/loanermas'
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
	  
	  cname.value = back.id
	  ename.value = back.id
}

var initback = {
		id: '25'
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
	var url = '/slloan/loan/jointappli'
	var data = {}
	data.id = 7
	searchAjax(method, url, data)
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  searchData()
}

__main()
