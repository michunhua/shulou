layui.use('upload', function(){
	  var upload = layui.upload;
	   
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#test1' //绑定元素
	    ,url: '/upload/' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	  });
});



$(function () {
    $("#btn_uploadimg").click(function () {
        var fileObj = document.getElementById("FileUpload").files[0]; // js 获取文件对象
        if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
            alert("请选择图片");
            return;
        }
        var formFile = new FormData();
        formFile.append("type", "身份证"); //加入文件类型
        formFile.append("action", "UploadVMKImagePath");  
        formFile.append("file", fileObj); //加入文件对象

        //第一种  XMLHttpRequest 对象
        //var xhr = new XMLHttpRequest();
        //xhr.open("post", "/Admin/Ajax/VMKHandler.ashx", true);
        //xhr.onload = function () {
        //    alert("上传完成!");
        //};
        //xhr.send(formFile);

        //第二种 ajax 提交

        var data = formFile;
        $.ajax({
            url: "/slloan/updateftpimage/imagedatafileupload",
            data: data,
            type: "Post",
            dataType: "json",
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (result) {
                alert("上传完成!");
            },
        })
    })
})


$(function() {
	  function updateUserInfo() {
		  updataImags()
	      var form = new FormData(document.getElementById("frm-reg"));  
	      $.ajax({  
	      url:"../financevoucher/voucherupload",  
	      type:"post",  
	      data:form,  
	      cache: false,  
	      processData: false,  
	      contentType: false,  
	      success:function(data){
	    	  var len = data.length;
	    	  if(len == undefined){
	    		  alert("操作成功！"+data.value); 
	    		  var file = data.obj[0].filepath
  	        	var udata = data.obj[0]
  	        	var dd = "<img  width='200px' height='200px' width='200px' height='200px' src='"+file+"' ><input type='button' onclick='uuuu("+data.obj[0].id+")'  value='提交' />";
  	        	  $("#imagedata").append(dd)
	    	  }else{
	    		  alert(data)
	    	  }
	      },complete:function(msg){ 
	          //请求完成后调用的回调函数（请求成功或失败时均调用）
	          
	      } ,  
	      error:function(e){  
	          alert("网络错误，请重试！！");  
	       }  
	      });          
	    }
    	function uuuu(udata){
    	alert(udata)
    	}
})

//限制上传点击次数
var updataImags = function(select) {
	var intent = document.querySelector(select)
	intent.disabled = 'disabled'
	setTimeout(function() {
		intent.disabled = ''
	}, 3000)
}

// 具体点击按钮
var updataButton = function(element) {
	var intent = document.querySelector(element)
	intent.addEventListener('click', function() {
		updataImags(element)
	})
}

updataButton("#applylist")
updataButton("#personal")
updataButton("#realestate")
updataButton("#upinstructions")
updataButton("#OtherType")
updataButton("#upproof")

// 显示查询图片
var imageDisplay1 = function(element, result) {
	var intent = document.querySelector(element)
	var flag = '申请表'
	if(result.申请表) {
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
			div.classList.add('updateImages')
			img.src = result.申请表[i].filepath
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
	if(result.身份证明) {
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
			div.classList.add('updateImages')
			img.src = result.身份证明[i].filepath
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
	if(result.房产证明) {
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
			div.classList.add('updateImages')
			img.src = result.房产证明[i].filepath
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
	if(result.批示) {
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
			div.classList.add('updateImages')
			img.src = result.批示[i].filepath
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
	if(result.其他类) {
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
			div.classList.add('updateImages')
			img.src = result.其他类[i].filepath
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
	if(result.凭证类) {
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
			div.classList.add('updateImages')
			img.src = result.凭证类[i].filepath
			div.appendChild(img)
			div.appendChild(br)
			div.appendChild(button1)
			div.appendChild(button2)
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
var initonload = function() {
	var method = 'GET'
	var url = '/slloan/updateftpimage/selectfiletype'
	var data = {}
	data.usernameid = localStorage.purid
	data.city = localStorage.purcity
	data.username = localStorage.purusername
	data.rolename = localStorage.purrole
	data.uploadtype = ['申请表', '身份证明', '房产证明', '批示', '其他类', '凭证类']
	data.listid = localStorage.createID || localStorage.createTemporaryId
	if(data.listid) {
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
	if(localStorage.createTemporaryId) {
		userid.value = localStorage.createTemporaryId
	} else {
		userid.value = localStorage.createID
	}
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
    		initonload()
//    		window.location.href = '../../slloan/loan/loanimag'
    	} else if(data.msg == 'fail') {
    		alert('无权限删除')
    	}else {
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
			layer.confirm('确定删除?', {icon: 3, title:'注意'}, function(index){
				imagesAjax(method, url, data)
	    		layer.close(index);
	    	});
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
			data.id = localStorage.createID || localStorage.createTemporaryId
			imagesreadAjax(method, url, data)
		}
	})
}


readImage("#images", "read")
deleteImage("#images", "delete")
