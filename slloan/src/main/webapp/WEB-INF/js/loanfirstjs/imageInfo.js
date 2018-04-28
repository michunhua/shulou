console.log('影像资料')


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
    		alert(data)
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
	var url = 'slloan/updateftpimage/selectfiletype'
	var data = {}
	data.usernameid = localStorage.purid
	data.city = localStorage.purcity
	data.username = localStorage.purusername
	data.rolename = localStorage.purrole
	data.uploadtype = ['申请表', '身份证明', '房产证明', '批示', '其他类', '凭证类']
	if(data.usernameid) {
		loadImageAjax(method, url, data)
	}
}


//添加默认上传相关的用户值
var userInitUpload = function(element, elements, elementss, elementes) {
	var intent = document.querySelector(element)
	var intents = document.querySelector(elements)
	var intentss = document.querySelector(elementss)
	var intentes = document.querySelector(elementes)

	
	intent.value = localStorage.purusername
	intents.value = localStorage.purcity
	intentss.value = localStorage.purid
	intentes.value = localStorage.purrole
}

userInitUpload("#username6", '#city6', '#id6', '#rolename6')
userInitUpload("#username1", '#city1', '#id1', '#rolename1')
userInitUpload("#username2", '#city2', '#id2', '#rolename2')
userInitUpload("#username3", '#city3', '#id3', '#rolename3')
userInitUpload("#username4", '#city4', '#id4', '#rolename4')
userInitUpload("#username5", '#city5', '#id5', '#rolename5')