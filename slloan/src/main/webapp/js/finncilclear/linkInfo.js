$('input,select,textarea').prop('readonly',true);

// 收集信息
var collectData = function() {
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
  data.state = 'b'
  data.ctime = 'c'
	
  data.id = ''
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas) {
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
  				window.location.href = '../../slloan/loan/loanapplyss'
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


// 发送数据
var sendData = function() {
  var evs = document.querySelector('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/modifyuserod'
    window.location.href = '../../slloan/loan/finncilclearnoteInfo'
  })
}


//设置页面数据
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
	  linkf.value = back.contacts
	  linkfMate.value = back.relationship
	  linkfPhone.value = back.c_Telephone
	  
	  links.value = back.contacts1
	  linksMate.value = back.relationship1
	  linksencodPhone.value = back.c_Telephone1
	  
	  linkt.value = back.contacts2
	  linktMate.value = back.relationship2
	  linktPhone.value = back.c_Telephone2
	  
	  
	// 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".linkf-mate").val(back.relationship);
		  $(".links-mate").val(back.relationship1);
		  $(".linkt-mate").val(back.relationship2);
		  form.render()
		});
}

//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
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

// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/contactasss'
	var data = {}
	data.id = localStorage.clearID
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//取消按钮事件
var cancelBtn = function(element) {
  var forms = e('form')
  var evs = e(element)
  evs.addEventListener('click', function() {
    forms.reset()
    window.location.href = '../../slloan/financial/settlementvoucher'
  })
}

//
var __main = function() {
  searchData()
  sendData()
  cancelBtn('#cancel')
}

__main()
