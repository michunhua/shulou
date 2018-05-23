//自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
  return  document.querySelector(elements)
}

//// 依赖库方法
//layui.use('laydate', function(){
//  var laydate = layui.laydate;
//
//  //执行一个laydate实例
//  laydate.render({
//    elem: '#test1' //指定元素
//  });
//});

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  //自定义验证规则
	  form.verify({
		  username: function(value){
	      if(value.length < 2){
	        return '名称至少得2个字符啊';
	      }
	    }
	    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
	    ,content: function(value){
	      layedit.sync(editIndex);
	    }
	  });
	  
	  //监听提交
	  form.on('submit(demo1)', function(data){
		  if(localStorage.createID || localStorage.loaner) {
			  updatevalid()
		  } else {
			  testsend()  
		  }
	    return false;
	  });
	});



// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.cname = e('.ch-name').value
  data.certificate = e('.paperwork-type').value
  data.certificateType = e('.document-number').value
  data.document = e('.paperwork-numb').value
  data.untilName = e('.unit-name').value
  data.residence = e('.residence-phone').value
  data.untilPhone = e('.unit-phone').value
  data.mobile = e('.mobie-phone').value
  data.salary = e('.salary').value
  data.state = ''
  data.ctime = ''
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
  				localStorage.loaner = data.value
//  				sendsearchData(localStorage.createTemporaryId)
  				window.location.href = '../../slloan/loan/loancomm'
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
var sendData = function(element) {
  log('send data to server')
  var evs = e(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    data.temporaryId = localStorage.createTemporaryId	
	data.mark = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/loanApplyspouse'
    log(data)
    if(!data.mark) {
        sendAjax(method, url, data)	
    }
  })
}

//验证后提交
var testsend = function() {
	log('data to send at time')
    var data = collectData()
    data.temporaryId = localStorage.createTemporaryId	
	data.mark = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/loanApplyspouse'
    log(data)
    if(!data.mark) {
        sendAjax(method, url, data)	
    }
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
	  cname = e('.ch-name')
	  certificate = e('.paperwork-type')
	  documents = e('.document-number')
	  certificateType = e('.paperwork-numb')
	  untilName = e('.unit-name')
	  untilPhone = e('.unit-phone')
	  residence = e('.residence-phone')
	  mobile = e('.mobie-phone')
	  salary = e('.salary')
	  
	  cname.value = back.name
	  certificate.value = back.id_Other
	  documents.value = back.id_Other
	  certificateType.value = back.id_Number
	  untilName.value = back.uni_Name
	  untilPhone.value = back.unit_Phone
	  residence.value = back.home_Phone
	  mobile.value = back.mobile_Phone
	  salary.value = back.monthly_Income
	  
		// 下拉选项
	  layui.use('form', function(){
		  var form = layui.form;
		  $(".paperwork-type").val(back.id_Type);
		  form.render()
		});
}

var initback = {
		id: '23'
}

//searchExport(initback)

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
				searchExport(data.obj)
			} else {
				alert('这页资料尚未填写')
			}
		},
		error: function() {
			alert('服务器错误')
		}
	})
}



// 查询数据
var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id = localStorage.createID
	if(data.id) {
		searchAjax(method, url, data)
	}
}

//查询数据
function sendsearchData(result) {
	var method = 'GET'
	var url = '/slloan/loan/spouses'
	var data = {}
	data.id = result
	if(data.id &localStorage.loaner) {
		searchAjax(method, url, data)
	}
}

//修改数据保存
var updateData = function(element) {
  log('send data to server')
  var evs = e(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    data.id = localStorage.createID
    var method = 'POST'
    var url = '/slloan/loan/spoupdatea'
    log(data)
    if(data.id || localStorage.loaner) {
			sendAjax(method, url, data)
		}
  })
}

//验证后保存修改数据
var updatevalid = function() {
    log('data to send at time')
    var data = collectData()
    data.id = localStorage.createID || localStorage.loaner
    var method = 'POST'
    var url = '/slloan/loan/spoupdatea'
    log(data)
    if(data.id || localStorage.loaner) {
			sendAjax(method, url, data)
		}
}

//
var __main = function() {
  log( "run")
//  sendData('#save-data')
  cancelBtn('#cancel')
  searchData()
//  updateData('#save-data')
  sendsearchData(localStorage.createTemporaryId)
}
__main()
