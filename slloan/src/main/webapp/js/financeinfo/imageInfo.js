// 下一页    
var sendData = function() {
  var evs = document.querySelector('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/modifyuserod'
    window.location.href = '../../slloan/loan/financeapplink'
  })
}

sendData()

// 返回
var backlist = function() {
	  var evs = document.querySelector('#cancel')
	  evs.addEventListener('click', function() {
	    var data = collectData()
	    var method = 'POST'
	    var url = '/slloan/loan/modifyuserod'
	    window.location.href = '../../slloan/financial/financeapproval'
	  })
	}

backlist()



// 显示查询图片
var imageDisplay1 = function(element, result) {
	var intent = document.querySelector(element)
	var flag = '申请表'
	if(result.申请表) {
		var len = result.申请表.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '申请表'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.申请表[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}
var imageDisplay2 = function(element, result) {
	var intent = document.querySelector(element)
	if(result.身份证明) {
		var len = result.身份证明.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '身份证明'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.身份证明[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}

var imageDisplay3 = function(element, result) {
	var intent = document.querySelector(element)
	if(result.房产证明) {
		var len = result.房产证明.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '房产证明'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.房产证明[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}

var imageDisplay4 = function(element, result) {
	var intent = document.querySelector(element)
	if(result.批示) {
		var len = result.批示.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '批示'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.批示[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}


var imageDisplay5 = function(element, result) {
	var intent = document.querySelector(element)
	if(result.其他类) {
		var len = result.其他类.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '其他类'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.其他类[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}


var imageDisplay6 = function(element, result) {
	var intent = document.querySelector(element)
	if(result.凭证类) {
		var len = result.凭证类.length
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '凭证类'
			button1.innerText = '查看'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			div.classList.add('updateImages')
			img.src = result.凭证类[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			intent.appendChild(div)
		}
	}
}


// 加载图像方法
var loadImageAjax = function(method, url, datas) {
	 $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		imageDisplay1("#imagedata1",  data.obj)
    		imageDisplay2("#imagedata2",  data.obj)
    		imageDisplay3("#imagedata3",  data.obj)
    		imageDisplay4("#imagedata4",  data.obj)
    		imageDisplay5("#imagedata5",  data.obj)
    		imageDisplay6("#imagedata6",  data.obj)
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
	data.uploadtype = ['申请表', '身份证明', '房产证明', '批示', '其他类', '凭证类']
	data.listid = localStorage.financialID
	if(localStorage.financialID) {
		loadImageAjax(method, url, data)
	}
}

//添加默认上传相关的用户值
var userInitUpload = function(element, elements, elementss, elementes, userid) {
	var intent = document.querySelector(element)
	var intents = document.querySelector(elements)
	var intentss = document.querySelector(elementss)
	var intentes = document.querySelector(elementes)
	var userid = document.querySelector(userid)

	
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

// 查看成功显示信息
var testmessage = function(data) {
	var user = data.obj.spare
	var note = data.obj.note
	var fileType = data.obj.uploadtype
	var src = data.obj.filepath
	layer.open({
		  title: '查看',
		  skin: 'layui-layer-rim',
		  area: ['1020px', '840px'],
		  content: '<div>' + '<img style = "width: 970px; height: 680px;" src = '+ src + '>'  + '<br>' + '<p>上传者:<span>' + user + '</span></p>' + '<br>' + '<p>备注信息:<span>' + note + '</span></p>' + '<br>' + '<p>文件类型:<span>' + fileType +'</span></p>' + '</div>',
		});
}

//查看 
var imagesreadAjax = function(method, url, datas) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		testmessage(data)
    	} else {
    		alert('服务器错误')
    	}
    },
    error: function(){
        alert('服务器错误')
     }
  })
}


// 删除 方法
var imagesAjax = function(method, url, datas) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		alert('删除成功')
    		window.location.href = '../../slloan/loan/financeappimg'
    	} else {
    		alert('服务器错误')
    	}
    },
    error: function(){
        alert('服务器错误')
     }
  })
}

//删除 
var deleteImage = function(element, select) {
	var intent = document.querySelector(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains(select)) {
			var positionName = event.target.name
			var gainfile = event.target.parentNode.querySelector('img').src
			var method = 'POST'
			var url = '/slloan/updateftpimage/imagedatadel'
	        var data = {}
			data.file = gainfile
			data.type = positionName
			data.usernameid = localStorage.purid
			data.city = localStorage.purcity
			data.username = localStorage.purusername
			data.rolename = localStorage.purrole
			data.id = localStorage.createID || localStorage.createTemporaryId
	        imagesAjax(method, url, data)
		}
	})
}

// 查看 
var readImage = function(element, select) {
	var intent = document.querySelector(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains(select)) {
			var positionName = event.target.name
			var gainfile = event.target.parentNode.querySelector('img').src
			var method = 'get'
			var url = '/slloan/updateftpimage/selectupdateinformation'
	        var data = {}
			data.filepath = gainfile
			data.uploadtype = positionName
			data.usernameid = localStorage.purid
			data.city = localStorage.purcity
			data.username = localStorage.purusername
			data.rolename = localStorage.purrole
			data.id = localStorage.financialID
			imagesreadAjax(method, url, data)
		}
	})
}


readImage("#images", "read")
deleteImage("#images", "delete")

