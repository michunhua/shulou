layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/demo/table/user/' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '全选', width:80, sort: true, fixed: 'left'}
      ,{field: 'user', title: '申请编号', width:200}
      ,{field: 'username', title: '姓名', width:80}
      ,{field: 'sex', title: '申请金额', width:200, sort: true}
      ,{field: 'city', title: '手机号码', width:200}
      ,{field: 'sign', title: '证件号码', width: 200}
      ,{field: 'experience', title: '贷款期限', width: 200, sort: true}
      ,{field: 'score', title: '状态', width: 80, sort: true}
      ,{field: 'classify', title: '住房风地址', width: 200}
      ,{field: 'wealth', title: '创建时间', width: 200, sort: true}
      ,{field: 'wealth', title: '操作', width: 135, sort: true}
    ]]
  });

});


//  /slloan/loan/transferaccounts
// /slloan/financevoucher

//设置查询数据
var setSearchData = function(data) {
  log('设置查询数据')
  note = e('.note')
  firstNote = e('.first-note')
  finalNote = e('.final-note')
  financeNote = e('.financeNote')
  
  note.value = data.note_Description1
  firstNote.value = data.note_Description2
  finalNote.value = data.note_Description3
  financeNote.value = data.note_Description4
}


//  默认查询
//查询方法
var searchAjax = function(method, url, datas) {
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		setSearchData(data.obj)
    	} else {
    		alert('服务器错误')
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
	var url = '/slloan/loan/notedescripti'
	var data = {}
	 data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.financialID
	if(url) {
		searchAjax(method, url, data)
	}
}

//收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.note = e('.note').value
  data.recordFirst = e('.first-note').value
  data.recorFinal = e('.final-note').value
  data.recorfore = e('.financeNote').value
  data.state = ''
  return data
}


// 保存
//保存方法
var saveAjax = function(method, url, datas) {
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
//	      				window.location.href = "/slloan/loan/loancrea"
	      			});
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//保存按钮保存数据
var saveBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		var method = 'POST'
		var url = '/slloan/loan/updatenotefore'
		var data = collectData()
			 data.rolename = localStorage.purrole
	  data.username = localStorage.purusername
	  data.city = localStorage.purcity
	  data.parentnodeId = localStorage.purid
	  data.id = localStorage.financialID
		console.log(data)
		if(url) {
			saveAjax(method, url, data)
		}
	})
}

// 取消
//取消按钮事件
var cancelBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		 window.history.back()
	})
}

// 提交
//提交方法
var submitAjax = function(method, url, datas) {
	  $.ajax({
	    type: method,
	    url: url,
	    data: {data:JSON.stringify(datas)},
	    success: function() {
	    	if(data.msg == 'success') {
	    		
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//提交按钮提交数据
var submitBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		layer.confirm('确定通过该贷款?', {icon: 3, title:'注意'}, function(index){
		    var method = ''
			var url = ''
			var data = collectData()
			console.log(data)
			if(url) {
				
			}
		  layer.close(index);
		});
	})
}

// 回退
//回退方法
var backAjax = function(method, url, datas) {
	  $.ajax({
	    type: method,
	    url: url,
	    data: {data:JSON.stringify(datas)},
	    success: function() {
	    	if(data.msg == 'success') {
	    		
	    	} else {
	    		alert('服务器错误')
	    	}
	    },
	    error: function() {
			alert('服务器错误')
		}
	  })
	}

//回退按钮回退数据
var goBackBtn = function(element) {
	var intent = e(element) 
	intent.addEventListener('click', function() {
		layer.confirm('确定回退该贷款?', {icon: 3, title:'注意'}, function(index){
			    var method = ''
				var url = ''
				var data = collectData()
				console.log(data)
				if(url) {
					
				}
			  layer.close(index);
			});
	})
}


var __main = function() {
	searchData()
	saveBtn('#save-data')
	cancelBtn("#cancel")
	submitBtn("#submit")
	goBackBtn("#go-back")
}

__main()
