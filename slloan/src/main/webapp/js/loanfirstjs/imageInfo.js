console.log('影像资料')

//显示查询图片
var imageDisplay1 = function(element, result) {
	var intent = document.querySelector(element)
	var flag = '申请表'
	console.log('执行次数', result.申请表)
	if(result.申请表) {
		console.log('开始加入图片')
		var len = result.申请表.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '申请表'
			button2.name = '申请表'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.申请表[i].filepath)
			div.classList.add('updateImages')
			img.src = result.申请表[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
}
var imageDisplay2 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.身份证明)
	if(result.身份证明) {
		console.log('开始加入图片')
		var len = result.身份证明.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '身份证明'
			button2.name = '身份证明'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')	
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.身份证明[i].filepath)
			div.classList.add('updateImages')
			img.src = result.身份证明[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
}

var imageDisplay3 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.房产证明)
	if(result.房产证明) {
		console.log('开始加入图片')
		var len = result.房产证明.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '房产证明'
			button2.name = '房产证明'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.房产证明[i].filepath)
			div.classList.add('updateImages')
			img.src = result.房产证明[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
}

var imageDisplay4 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.批示)
	if(result.批示) {
		console.log('开始加入图片')
		var len = result.批示.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '批示'
			button2.name = '批示'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.批示[i].filepath)
			div.classList.add('updateImages')
			img.src = result.批示[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
}


var imageDisplay5 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.其他类)
	if(result.其他类) {
		console.log('开始加入图片')
		var len = result.其他类.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '其他类'
			button2.name = '其他类'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.其他类[i].filepath)
			div.classList.add('updateImages')
			img.src = result.其他类[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
}


var imageDisplay6 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.凭证类)
	if(result.凭证类) {
		console.log('开始加入图片')
		var len = result.凭证类.length
		intent.innerHTML = ''
		for(var i = 0; i < len; i++) {
			var button1 = document.createElement('button')
			var button2 = document.createElement('button')
			var br = document.createElement('br')
			button1.classList.add("read")
			button2.classList.add("delete")
			button1.name = '凭证类'
			button2.name = '凭证类'
			button1.innerText = '查看'
			button2.innerText = '删除'
			var div = document.createElement('div')
			var img = document.createElement('img')
			img.classList.add('images')
			console.log(result.凭证类[i].filepath)
			div.classList.add('updateImages')
			img.src = result.凭证类[i].filepath
			console.log(img)
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
			intent.appendChild(div)
		}
	}
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

// 查询数据
var searchData = function() {
	var method = ''
	var url = ''
	var data = {}
	searchAjax(method, url, data)
}





// 查询图像
var sendAjax = function(method, url, datas) {
  console.log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
      alert(data)
    }
  })
}

var __main = function() {
  var envs = e('#search')
  envs.addEventListener('click', function() {
    var ftype = e('#Ftype').value
    var usernames = e("#userName").value
    var citys = e("#City").value
    var fname = e('#Fname').value
    var id = e('#Id').value
    var method = 'GET'
    var url = '/slloan/updateftpimage/selectfiletype'
    var data = {}
    
    data.upload_type = ftype
    data.note = usernames
    data.city = citys
    data.file = fname
    data.id = id
    
    console.log(data)
    
    sendAjax(method, url, data)
  })
}

//__main()




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
var initonload = function() {
	var method = 'GET'
	var url = '/slloan/updateftpimage/selectfiletype'
	var data = {}
	data.usernameid = localStorage.purid
	data.city = localStorage.purcity
	data.username = localStorage.purusername
	data.rolename = localStorage.purrole
	data.uploadtype = ['申请表', '身份证明', '房产证明', '批示', '其他类', '凭证类']
	data.listid = localStorage.firstID
	if(localStorage.firstID) {
		loadImageAjax(method, url, data)
	}
}

initonload()

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
	userid.value = localStorage.firstID
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
    		layer.msg('删除成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
//  				window.location.href = '../../slloan/loan/loanimag'
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

//删除 
var deleteImage = function(element, select) {
	var intent = document.querySelector(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains(select)) {
			console.log(event.target.parentNode.querySelector('img').src)
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
			data.id = localStorage.firstID
	        imagesAjax(method, url, data)
		}
	})
}

// 查看 
var readImage = function(element, select) {
	var intent = document.querySelector(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains(select)) {
			console.log(event.target.parentNode.querySelector('img').src)
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
			data.id = localStorage.firstID
			imagesreadAjax(method, url, data)
		}
	})
}


readImage("#images", "read")
deleteImage("#images", "delete")