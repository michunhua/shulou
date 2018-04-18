log('linkInfo')

// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.linkf = e('.linkf').value
  data.linkfMate = e('.linkf-mate').value
  data.linkfPhone = e('.linkf-phone').value
  data.links = e('.links').value
  data.linksMate = e('.links-mate').value
  data.linksPhone = e('.links-phone').value
  data.linkt = e('.linkt').value
  data.linktMate = e('.linkt-mate').value
  data.linktPhone = e('.linkt-phone').value
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
  				window.location.href = '../../slloan/loan/loannotes'
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

// 设置页面数据
var searchExport = function(back) {
	  linkf = e('.linkf')
	  linkfMate = e('.linkf-mate')
	  linkfPhone = e('.linkf-phone')
	  
	  links = e('.links')
	  linksMate = e('.links-mate')
	  linksencodPhone = e('.links-phone')
	  
	  linkt = e('.linkt')
	  linktMate = e('.linkt-mate')
	  linktPhone = e('.linkt-phone')
	  
	  //设置值
	  linkf.value = back.id
	  linkfMate.value = back.id
	  linkfPhone.value = back.id
	  
	  links.value = back.start
	  linksMate.value = back.start
	  linksencodPhone.value = back.start
	  
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
	var url = '/slloan/loan/contactss'
	var data = {}
	data.id = 2
	searchAjax(method, url, data)
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  searchData()
}

__main()
