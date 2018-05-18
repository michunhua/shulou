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
  data.linkf = e('.linkf-name').value
  data.linkfMate = e('.linkf-relationship').value
  data.linkfPhone = e('.linkf-phone').value
  data.links = e('.links-name').value
  data.linksMate = e('.links-relationship').value
  data.linksPhone = e('.links-phone').value
  data.linkt = e('.linkt-name').value
  data.linktMate = e('.linkt-relationship').value
  data.linktPhone = e('.linkt-phone').value
  data.state = 'a'
  data.ctime = 'b'
  return data
}

//验证填写
var testfocus = function(element, rule) {
	var intent = document.querySelector(element)
	var span = document.createElement('span')
	var flag = false
	span.innerText = '请正确填写'
	span.style.color = 'red'
	intent.addEventListener("keyup", function() {
		console.log('12342134', flag)
		if(intent.value.length < rule) {
			intent.parentNode.appendChild(span)
			intent.parentNode.classList.add('position')
			flag = false
			return flag
		} else {
			if(!flag & intent.parentNode.classList.contains('position')) {
				intent.parentNode.removeChild(span)
			}
			flag = true
		}
	});
	return flag
}

testfocus('.linkf-phone', 11) 
testfocus('.links-phone', 11)
testfocus('.linkt-phone', 11)
testfocus('.linkf-name', 2)
testfocus('.links-name', 2)
testfocus('.linkt-name', 2)

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
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
  				localStorage.link = data.value
  				sendsearchData(localStorage.createTemporaryId)
  				window.location.href = '../../slloan/loan/loannote'
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
  log('send data to server')
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    data.temporaryId = localStorage.createTemporaryId
    data.mark = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/contactinformation'
    log(data)
    if(!data.mark) {
    	sendAjax(method, url, data)	
    }
  })
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
	  linkf = e('.linkf-name')
	  linkfMate = e('.linkf-relationship')
	  linkfPhone = e('.linkf-phone')
	  
	  links = e('.links-name')
	  linksMate = e('.links-relationship')
	  linksencodPhone = e('.links-phone')
	  
	  linkt = e('.linkt-name')
	  linktMate = e('.linkt-relationship')
	  linktPhone = e('.linkt-phone')
	  
	  //设置值
	  linkf.value = back.contacts
	  linkfMate.value = back.relationship
	  linkfPhone.value = back.c_Telephone
	  
	  links.value = back.contacts1
	  linksMate.value = back.relationship1
	  linksencodPhone.value = back.c_Telephone1
	  
	  linkt.value = back.contacts2
	  linktMate.value = back.relationship2
	  linktPhone.value = back.c_Telephone2
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

// 查询
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/contactasss'
	var data = {}
	data.id = localStorage.createID
	if(data.id) {
		searchAjax(method, url, data)	
	}
}

//保存后查询数据
function sendsearchData(result)  {
	var method = 'GET'
	var url = '/slloan/loan/contactasss'
	var data = {}
	data.id = result
	if(data.id & localStorage.link) {
		searchAjax(method, url, data)	
	}
}

//修改数据保存
var updateData = function() {
  log('send data to server')
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    data.id = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/contaupdate'
    log(data)
    if(data.id) {
			sendAjax(method, url, data)
		}
  })
}


//
var __main = function() {
  log( "run")
  sendData()
  cancelBtn('#cancel')
  searchData()
  updateData()
  sendsearchData(localStorage.createTemporaryId)
}

__main()
