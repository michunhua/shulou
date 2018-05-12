console.log('影像信息')


layui.use('upload', function(){
	console.log('上传照片')
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
            	console.log(result)
                alert("上传完成!");
            },
        })
    })
})


$(function() {
	  function updateUserInfo() {
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
	    	  console.log(len)
	    	  if(len == undefined){
	    		  alert("操作成功！"+data.value); 
	    		  var file = data.obj[0].filepath
  	        	console.log( data.obj[0])
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

var imageDisplay4 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.房产证明)
	if(result.房产证明) {
		console.log('开始加入图片')
		var len = result.房产证明.length
		console.log(result.房产证明[0].filepath)
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

var imageDisplay5 = function(element, result) {
	var intent = document.querySelector(element)
	console.log('执行次数', result.其他类)
	if(result.其他类) {
		console.log('开始加入图片')
		var len = result.其他类.length
		console.log(result.其他类[0].filepath)
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

// 加载图像方法
var loadImageAjax = function(method, url, datas) {
	console.log('加载图片')
	 $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		console.log(data.obj)
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
	if(data.usernameid) {
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
	userid.value = localStorage.createTemporaryId
}

userInitUpload("#username6", '#city6', '#id6', '#rolename6', '#uid1')
userInitUpload("#username1", '#city1', '#id1', '#rolename1', '#uid2')
userInitUpload("#username2", '#city2', '#id2', '#rolename2', '#uid3')
userInitUpload("#username3", '#city3', '#id3', '#rolename3', '#uid4')
userInitUpload("#username4", '#city4', '#id4', '#rolename4', '#uid5')
userInitUpload("#username5", '#city5', '#id5', '#rolename5', '#uid6')
