// 收集信息
var collectData = function() {
  log('收集数据')
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

			}
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/personalp'
	var data = {}
	data.id = 10
	searchAjax(method, url, data)
}

//
var __main = function() {
  log( "run")
  sendData()
}

__main()
