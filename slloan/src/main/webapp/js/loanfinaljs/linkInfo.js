// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.linkfName = e('.linkf-name').value
  data.linkfRelationship = e('.linkf-relationship').value
  data.linkfPhone = e('.linkf-phone').value
  data.linksName = e('.links-name').value
  data.linksRelationship = e('.links-relationship').value
  data.linksPhone = e('.links-phone').value
  data.linktName = e('.linkt-name').value
  data.linktRelationship = e('.linkt-relationship').value
  data.linktPhone = e('.linkt-phone').value
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
var searchExport = function(back) {
	  linkf = e('.linkf-name')
	  linkfMate = e('.linkf-relationship')
	  linkfPhone = e('.linkf-phone')
	  
	  links = e('.links-name')
	  linksMate = e('.links-relationship')
	  linksPhone = e('.links-phone')
	  
	  linkt = e('.linkt-name')
	  linktMate = e('.linkt-relationship')
	  linktPhone = e('.linkt-phone')
	  
	  //设置值
	  linkf.value = back.id
	  linkfMate.value = back.id
	  linkfPhone.value = back.id
	  
	  links.value = back.start
	  linksMate.value = back.start
	  linksPhone.value = back.start
	  
	  linkt.value = back.id
	  linktMate.value = back.id
	  linktPhone.value = back.id
	  
	  
//	  console.log('电话号码', linksencodPhone)
}

var initback = {
		id: '19',
		start: '100'
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
  sendData()
}

__main()
