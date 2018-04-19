
// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.note = e('.note').value
  data.firstTrial = e('.first-trial').value
  data.finalTrial = e('.final-trial').value
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
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
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
var searchExport = function(back) {
	recordNote = e('.note')
	recordFirst = e('.first-trial')
	
	recordNote.value = back.id
	recordFirst.value = back.id
}

var initback = {
		id: '17'
}

searchExport(initback)



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
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//
var __main = function() {
  log( "run")
  sendData('#save-note')
  searchData()
}

__main()
