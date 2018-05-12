// 依赖库方法
layui.use('laydate', function(){
  var laydate = layui.laydate;

  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
	    elem: '#dates'
	  });
});

//过滤数据
var myFilter = function(data) {
	var result = ""
	if(data) {
		return data
	} else {
		return  result
	}
}


//添加表格具体数据格
var addTable = function(data) {
	console.log('添加表格儿')
    var datas = data
    var len = (datas.lists).length
    var pageElement = document.querySelector('.tab-data')
    var totalPage = document.querySelector('.totalPage')
	totalPage.innerText = datas.totalPage
    pageElement.innerHTML = null
    console.log('回写数据长度', len)
    for(var i = 0; i < len; i++) {
    	  log('回写次数' + i)
          var tr = document.createElement('tr')
          var td = document.createElement('td')
          var input = document.createElement('input')
          var td0 = document.createElement('td')
          var a = document.createElement('a')
          var td1 = document.createElement('td')
          var td1a = document.createElement('td')
          var td2 = document.createElement('td')
          var td3 = document.createElement('td')
          var td4 = document.createElement('td')
          var td5 = document.createElement('td')
          var td6 = document.createElement('td')
          var td7 = document.createElement('td')
          var td8 = document.createElement('td')
          var td9 = document.createElement('td')
          var td10 = document.createElement('a')
          var td10a = document.createElement('a')
          var applicationnumber = myFilter(datas.lists[i].applicationnumber)
          var ID = myFilter(datas.lists[i].notes[0].id)
          var userid = myFilter(datas.lists[i].name)
          var userName = myFilter(datas.lists[i].applyfor[0].amount)
          var phone = myFilter(datas.lists[i].mobile_phone)
          var number = myFilter(datas.lists[i].id_number)
          var limit = myFilter(datas.lists[i].applyfor[0].time_Limit)
          var state = myFilter(datas.lists[i].state)
          var address = myFilter(datas.lists[i].home_address_now)
          var time = myFilter(datas.lists[i].ctime)
          
          if(datas.lists[i].marked) {
			tr.classList.add('marked')
		}
          
          input.type = 'checkbox'
          input.classList.add('indicate')
          a.classList.add('mark')
          a.href = '#'
          a.innerText = applicationnumber
          td1a.classList.add('flag')
          td1a.innerText = ID
          td1.innerText = userid
		  td2.innerText = userName
		  td3.innerText = phone
		  td4.innerText = number
		  td5.innerText = limit
		  td6.innerText = state
		  td7.innerText = address
		  td8.innerText = time
  		  td10.classList.add('up')
  		  td10.name = state
		  td10.href = "#"
		  td10.innerText = '[ 挂起  ]'
		  td10a.classList.add('record')
		  td10a.name = state
		  td10a.href = "#"
		  td10a.innerText = '[ 流转记录  ]'
		  td9.classList.add(ID)
          td9.appendChild(td10)
          td9.appendChild(td10a)
          td.appendChild(input)
          td0.appendChild(a)
          tr.appendChild(td)
          tr.appendChild(td0)
           tr.appendChild(td1a)
          tr.appendChild(td1)
         
          tr.appendChild(td2)
          tr.appendChild(td3)
          tr.appendChild(td4)
          tr.appendChild(td5)
          tr.appendChild(td6)
          tr.appendChild(td7)
          tr.appendChild(td8)
          tr.appendChild(td9)
      pageElement.insertAdjacentElement('beforeend', tr)
    }
}

//var testData = {
//		test: '233',
//		lists: [1, 2, 3, 4, 5]
//}
//
//addTable(testData)
//发送数据方法
var initAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data){
    	addTable(data)
    }, 
    error: function(){
        alert('服务器错误')
     }    
  })
}


var initData = function() {
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/loanfinalreviewlist?page='+init.pages+'&limit='+ init.limit
	var datas = {}
    datas.rolename = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
	datas.parentnodeId = localStorage.purid
	console.log('初始化加载数据233')
	initAjax(method, url, datas, addTable)
	console.log('执行没有？')
}

//收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.userName = e('.username').value
  data.iphone = e('.iphone').value
  data.IDcard = e('.Idcard').value
  data.numbering = e('.application').value
  data.start = e('.start').value
  data.end = e('.end').value
  data.min = e('.min').value
  data.max = e('.max').value
  data.state = e('.state').value
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
    	console.log(data)
    	addTable(data)
    },
    error: function(){
        alert('服务器错误')
     }    
  })
}


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var method = 'GET'
    var url = '/slloan/loan/finalreviewselect'
    var data = collectData()
      data.rolename = localStorage.purrole
    data.username = localStorage.purusername
    data.city = localStorage.purcity
	data.parentnodeId = localStorage.purid
    log(data)
    sendAjax(method, url, data, null)
  })
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
				
			} else {
				alert('服务器错误')
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
	var url = '/slloan/loan/'
	var data = {}
	searchAjax(method, url, data)
}


//具体查询
var numberSearch = function(element) {
	var envs = e(element)
		envs.addEventListener('click', function(event) {
			var indicate = event.target.classList
			if(indicate == 'mark') {
				console.log(event.target.parentNode.nextSibling.innerText, '就这个数据')
				console.log(event.target.innerText, '好的')
				localStorage.finalID = event.target.parentNode.nextSibling.innerText
				window.location.href = '../loan/loanjoinss'
			}
		})
}

//分页接口 user/userlist
var init = {
		pages: 1,
		limit: 10,
}

var currpages = function() {
	var pages = document.querySelector('.currtPage')
	pages.innerText = init.pages
}

currpages()

//下一页
var nextpage = function() {
	var envs = document.querySelector('.next')
	envs.addEventListener('click', function() {
		var flag = Number(document.querySelector('.totalPage').innerText)
		if(init.pages >= 1 && init.pages < flag) {
			init.pages = init.pages + 1
			firtLoadlist()
			currpages()
		} else {
			layer.open({
				  title: '注意'
				  ,content: '已经没有下一页!'
				});
		}
	})
}

nextpage()

// 上一页
var previoupage = function() {
	var envs = document.querySelector('.previous')
	envs.addEventListener('click', function() {
		if(init.pages > 1) {
			init.pages = init.pages - 1
			firtLoadlist()
			currpages()
		} else {
			layer.open({
				  title: '注意'
				  ,content: '已经没有上一页!'
				});
		}
	})
}

previoupage()

// 存储要发送的数据
var saveSend = []

// 找到需要存储的数据
var findSave = function() {
	var parentElement = e('.tab-data')
	var definite = parentElement.querySelectorAll('.indicate')
	var flags = es('.flag')
	console.log(definite.length)
	// console.log(flags[1].innerText)
	for (var i = 0; i < definite.length; i++) {
		if (definite[i].checked == true) {
			if(!saveSend.includes(flags[i].innerText)) {
				saveSend.push(flags[i].innerText)	
			}
		} else {
			if (saveSend.includes(flags[i].innerText)) {
				for (var j = 0; j < saveSend.length; j++) {
					if (saveSend[j] == flags[i].innerText) {
						saveSend.splice(j, 1)
					}
				}
			}
		}
	}
	console.log(saveSend)
}

// 全部数据选中
var fullDataCheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
	console.log('fullcheck', tent, len)
	for (var i = 0; i < len; i++) {
		tent[i].checked = true
	}
	findSave()
}

// 全部数据不选中
var fullDataUncheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
	console.log('fullUncheck', tent, len)
	for (var i = 0; i < len; i++) {
		tent[i].checked = false
	}
	findSave()
}

// 全选按钮
var fullSelection = function(element) {
	var intent = e(element)
	intent.addEventListener('change', function() {
		if (intent.checked) {
			intent.checked = false
			fullDataUncheck('.indicate')
		} else {
			intent.checked = true
			fullDataCheck('.indicate')
		}
	})
}

// 单选按钮
var single = function() {
	var parentElement = e('.tab-data')
	parentElement.addEventListener('click', function(event) {
		console.log('单击')
		if (event.target.classList.contains('indicate')) {
			findSave()
		}
	})
}

//监听选中的个数等于全部时全选按钮为选中状态
var listenSingle = function(select, element, arr) {
	console.log('监听全部的选择')
	var flag = arr.length
	var intent = es(element)
	var mark = intent.length
	console.log(flag, mark, "长度相等吗？？")
	if(flag == mark) {
		console.log('相等执行 true')
		e(select).checked = true
	} else {
		console.log('不相等执行 false')
		e(select).checked = false
	}
}

// 一个个选中直到全部选中
var singleAdd = function() {
	var result = []
	var parentElement = e('.tab-data')
	var intent = e('#full')
	var definite = es('.indicate')
	console.log('一个接一个的点击')
	parentElement.addEventListener('click', function() {
		console.log('执行到这', definite.length)
		listenSingle('#alls', '.indicate', saveSend) 
		for (var i = 0; i < definite.length; i++) {
			console.log('有没有选中的')
			if (!definite[i].checked) {
				result.push('1')
			}
		}
		if (result.length == definite.length) {
			intent.checked = true
		} else {
			intent.checked = false
		}
	})
}

//全选按钮的状态
var listenAllSel = function(item, index, element) {
	console.log('这能执行吗？')
	var intent = e(item)
	var flag = e(index)
	intent.addEventListener('click', function() {
		var run = es(element)
		var mark = flag.checked
//		console.log("响应事件")
//		console.log(mark, '这到底如何')
		if(mark) {
//			console.log("有选中的时间执行")
//			console.log('执行选中几个', run)
			for(var i = 0; i < run.length; i++) {
				run[i].checked = true
			}
			findSave()
		} else {
//			console.log("不选中的时候只想")
//			console.log('执行bu选中几个', run)
			for(var i = 0; i < run.length; i++) {
				run[i].checked = false
			}
			findSave()
		}
	})
}

//批量后全选按钮不选中
var unselected = function(element) {
	log("_______改变选中状态______")
	var intent = document.querySelector(element)
	var flag = intent.checked 
	if(flag) {
		intent.checked = false
	}
}


//终审批量拒绝方法
//发送数据方法
var refuseAjax = function(method, url, datas) {
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
				saveSend.length = 0
				initData()
				unselected('#alls')
			} else {
				alert('服务器错误')
			}
		}, 
		error: function() {
			alert('服务器错误')
		}
	})
}
//终审批量拒绝事件
var batchRefuse  = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function() {
		console.log('refuse')
		var method = "POST"
		var url = "/slloan/financevoucher/loaninalreviewbatch"
		var datas = {}
		datas.rolename = localStorage.purrole
		datas.username = localStorage.purusername
		datas.city = localStorage.purcity
		datas.parentnodeId = localStorage.purid
		var data = saveSend
		data.push(datas)
	    if(data.length > 0) {
			refuseAjax(method, url, data)	
	    } else {
	    	alert('至少选中一个')
	    }
	})
}

//终审批量通过方法
//发送数据方法
var passAjax = function(method, url, datas) {
	log('send data method')
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			console.log('通过返回数据', data)
			if (data.msg == 'success') {
				saveSend.length = 0
				initData()
				unselected('#alls')
			} else {
				alert('服务器错误')
			}
		}, 
		error: function() {
			alert('服务器错误')
		}
	})
}

//终审批量通过事件
var batchPass = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function() {
		console.log('pass')
		var method = "POST"
		var url = "/slloan/financevoucher/loaninalreviewbatchpast"
		var datas = {}
		datas.rolename = localStorage.purrole
		datas.username = localStorage.purusername
		datas.city = localStorage.purcity
		datas.parentnodeId = localStorage.purid
		var data = saveSend
		data.push(datas)
	    if(data.length > 0) {
	    	passAjax(method, url, data)
	    } else {
	    	alert('至少选中一个')
	    }
	})
}

//流转记录查询
var hang_cirulationAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
          console.log(data.obj.a.length)
          var content = data.obj.b.applicationnumber
          console.log(content)
          var tableString = ''
          if(data.obj.a.length) {
          	var flag = data.obj.a.length
          	for(var i = 0; i < flag; i++) {
                  var rolename = data.obj.a[i].rolename
                  var username = data.obj.a[i].username
                  var circulation = data.obj.a[i].circulation
                  var updatedata = data.obj.a[i].updatedata
                  tableString += "<tr><td>" + content + "</td><td>"+ circulation +"</td><td>"+ rolename +"</td><td>"+ username +"</td><td>"+ updatedata +"</td><tr>" 
                  
          	}
          }
  		layer.open({
  			  title: '流转记录',
  			  area: ['830px', '560px'],
  			  content: "<table border='1'><tr><th>申请编号</th><th>状态</th><th>负责角色</th><th>负责人</th><th>处理时间</th></tr>" +
  			  		 tableString + "</table>",
  			});
      }, 
      error: function() {
    	  layer.msg('服务器错误')
      }
    })
}

//挂起方法
var HangAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        initData()
      }, 
      error: function() {
    	  layer.msg('服务器错误')
      }
    })
}

//挂起&流转记录位置
var Hang_cirulation = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains("up")) {   
			console.log('挂起')
			var method = "GET"
		    var url = "/slloan/loan/loanfinalreviewlist?page="+ init.pages + '&limit='+ init.limit
		    var datas = {}
			datas.state = event.target.name
			datas.id = event.target.parentNode.classList.value
			datas.rolename = localStorage.purrole
			datas.username = localStorage.purusername
			datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			HangAjax(method, url, datas)
		} else if(event.target.classList.contains("record")) {
			console.log('流转记录')
			var method = "GET"
		    var url = "/slloan/sumiteregresses/selectwhole"
		    var datas = {}
			datas.state = event.target.name
			datas.id = event.target.parentNode.classList.value
			datas.rolename = localStorage.purrole
			datas.username = localStorage.purusername
			datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			hang_cirulationAjax(method, url, datas)
		}
	})
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  initData()
//  fullSelection('#full')
  listenAllSel('#full', "#alls", '.indicate')
  numberSearch('.tab-data')
  single()
  singleAdd()
  batchPass("#batch-pass")
  batchRefuse("#batch-refuse")
  Hang_cirulation('.tab-data')
}

__main()
