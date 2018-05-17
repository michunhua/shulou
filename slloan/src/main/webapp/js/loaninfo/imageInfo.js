// 下一页    
var sendData = function() {
  log('send data to server')
  var evs = document.querySelector('#save-loaner')
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/modifyuserod'
    log(data)
//    sendAjax(method, url, data, null)
    window.location.href = '../../slloan/loan/loanlinkInfo'
  })
}

sendData()

// 返回
var backlist = function() {
	  log('send data to server')
	  var evs = document.querySelector('#cancel')
	  evs.addEventListener('click', function() {
	    log('data to send at time')
	    var data = collectData()
	    var method = 'POST'
	    var url = '/slloan/loan/modifyuserod'
	    log(data)
//	    sendAjax(method, url, data, null)
	    window.location.href = '../../slloan/loan/anin'
	  })
	}

backlist()



// 显示查询图片
var imageDisplay1 = function(element, result) {
	var intent = document.querySelector(element)
	var flag = '申请表'
	console.log('执行次数', result.申请表)
	if(result.申请表) {
		console.log('开始加入图片')
		var len = result.申请表.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.申请表[i].filepath)
			img.src = result.申请表[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}
var imageDisplay2 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.身份证明)
	if(result.身份证明) {
		console.log('开始加入图片')
		var len = result.身份证明.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.身份证明[i].filepath)
			img.src = result.身份证明[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}

var imageDisplay3 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.房产证明)
	if(result.房产证明) {
		console.log('开始加入图片')
		var len = result.房产证明.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.房产证明[i].filepath)
			img.src = result.房产证明[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}


var imageDisplay4 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.批示)
	if(result.批示) {
		console.log('开始加入图片')
		var len = result.批示.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.批示[i].filepath)
			img.src = result.批示[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}

var imageDisplay5 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.其他类)
	if(result.其他类) {
		console.log('开始加入图片')
		var len = result.其他类.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.其他类[i].filepath)
			img.src = result.其他类[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}


var imageDisplay6 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.凭证类)
	if(result.凭证类) {
		console.log('开始加入图片')
		var len = result.凭证类.length
		console.log(result.凭证类[0].filepath)
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.凭证类[i].filepath)
			img.src = result.凭证类[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}

var imageDisplay7 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result)
	if(result) {
		console.log('开始加入图片')
		var len = result.length
		for(var i = 0; i < len; i++) {
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result[i].filepath)
			img.src = result[i].filepath
			console.log(img)
			intent.appendChild(img)
		}
	}
}

// 加载图像方法
var loadImageAjax = function(method, url, datas) {
	console.log('加载图片')
	 $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		console.log(data)
    		imageDisplay1("#imagedata1",  data.obj)
    		imageDisplay2("#imagedata2",  data.obj)
    		imageDisplay3("#imagedata3",  data.obj)
    		imageDisplay4("#imagedata4",  data.obj)
    		imageDisplay5("#imagedata5",  data.obj)
    		imageDisplay6("#imagedata6",  data.obj)
    		imageDisplay7("#imagedata6",  data.obj.转账凭证)
    		imageDisplay7("#imagedata6",  data.obj.结算凭证)
    		imageDisplay7("#imagedata6",  data.obj.取证凭证)
    		imageDisplay7("#imagedata6",  data.obj.解押凭证)
    		imageDisplay7("#imagedata6",  data.obj.进押凭证)
    		imageDisplay7("#imagedata6",  data.obj.回款确认)
    		imageDisplay7("#imagedata6",  data.obj.贷款信息查看)
    	} else {
    		alert('服务器错误')
    	}
    },
    error: function(){
        alert('服务器错误')
     }
  })
}

// 执行加载图片
window.onload = function() {
	var method = 'GET'
	var url = '/slloan/updateftpimage/selectfiletype'
	var data = {}
	data.usernameid = localStorage.purid
	data.city = localStorage.purcity
	data.username = localStorage.purusername
	data.rolename = localStorage.purrole
	data.uploadtype = ['申请表', '身份证明', '房产证明', '批示', '其他类', '凭证类', '转账凭证', '结算凭证', '取证凭证','解押凭证','进押凭证','回款确认', '贷款信息查看']
	data.listid = localStorage.readID
	if(localStorage.readID) {
		loadImageAjax(method, url, data)
	}
}

//添加默认上传相关的用户值
var userInitUpload = function(element, elements, elementss, elementes, userid) {
	var intent = document.querySelector(element)
	var intents = document.querySelector(elements)
	var intentss = document.querySelector(elementss)
	var intentes = document.querySelector(elementes)
//	var userid = document.querySelector(userid)

	
	intent.value = localStorage.purusername
	intents.value = localStorage.purcity
	intentss.value = localStorage.purid
	intentes.value = localStorage.purrole
//	userid.value = localStorage.createTemporaryId
}

userInitUpload("#username6", '#city6', '#id6', '#rolename6', '#uid1')
userInitUpload("#username1", '#city1', '#id1', '#rolename1', '#uid2')
userInitUpload("#username2", '#city2', '#id2', '#rolename2', '#uid3')
userInitUpload("#username3", '#city3', '#id3', '#rolename3', '#uid4')
userInitUpload("#username4", '#city4', '#id4', '#rolename4', '#uid5')
userInitUpload("#username5", '#city5', '#id5', '#rolename5', '#uid6')

