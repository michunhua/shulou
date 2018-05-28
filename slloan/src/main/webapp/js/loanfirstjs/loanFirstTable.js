//layui.use('table', function() {
//	var table = layui.table;
//
//	// 第一个实例
//	table.render({
//		elem : '#demo',
//		height : 315,
//		url : '/demo/table/user/' // 数据接口
//		,
//		page : true // 开启分页
//		,
//		cols : [ [ // 表头
//		{
//			field : 'id',
//			title : '全选',
//			width : 80,
//			sort : true,
//			fixed : 'left'
//		}, {
//			field : 'user',
//			title : '申请编号',
//			width : 200
//		}, {
//			field : 'username',
//			title : '姓名',
//			width : 80
//		}, {
//			field : 'sex',
//			title : '申请金额',
//			width : 200,
//			sort : true
//		}, {
//			field : 'city',
//			title : '手机号码',
//			width : 200
//		}, {
//			field : 'sign',
//			title : '证件号码',
//			width : 200
//		}, {
//			field : 'experience',
//			title : '贷款期限',
//			width : 200,
//			sort : true
//		}, {
//			field : 'score',
//			title : '状态',
//			width : 80,
//			sort : true
//		}, {
//			field : 'classify',
//			title : '住房风地址',
//			width : 200
//		}, {
//			field : 'wealth',
//			title : '创建时间',
//			width : 200,
//			sort : true
//		}, {
//			field : 'wealth',
//			title : '操作',
//			width : 135,
//			sort : true
//		} ] ]
//	});
//
//});

layui
		.use(
				[ 'form', 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					// 日期
					laydate.render({
						elem : '#date'
					});
					laydate.render({
						elem : '#date1'
					});

					laydate.render({
						elem : '#dates'
					});
					laydate.render({
						elem : '#date1'
					});

					// 创建一个编辑器
					var editIndex = layedit.build('LAY_demo_editor');

					// 自定义验证规则
					form.verify({
						title : function(value) {
							if (value.length < 5) {
								return '标题至少得5个字符啊';
							}
						},
						pass : [ /(.+){6,12}$/, '密码必须6到12位' ],
						content : function(value) {
							layedit.sync(editIndex);
						}
					});

					// 监听指定开关
					form.on('switch(switchTest)', function(data) {
						layer.msg('开关checked：'
								+ (this.checked ? 'true' : 'false'), {
							offset : '6px'
						});
						layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF',
								data.othis)
					});

					// 监听提交
					form.on('submit(demo1)', function(data) {
						layer.alert(JSON.stringify(data.field), {
							title : '最终的提交信息'
						})
						return false;
					});

				});


// 自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
	return document.querySelector(elements)
}

// 依赖库方法
layui.use('laydate', function() {
	var laydate = layui.laydate;

	// 执行一个laydate实例
	laydate.render({
		elem : '#test1' // 指定元素
	});
});

// 过滤数据
var myFilter = function(data) {
	var result = ""
	if (data) {
		return data
	} else {
		return result
	}
}



// 添加表格具体数据格
var addTable = function(data) {
	var datas = data
	var len = (datas.lists).length
	var pageElement = document.querySelector('.tab-data')
	var totalPage = document.querySelector('.totalPage')
	totalPage.innerText = datas.totalPage
	pageElement.innerHTML = null
    var sequence = []
	for (var i = 0; i < len; i++) {
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
		var limit = myFilter(datas.lists[i].applyfor[0].time_Limit) + datas.lists[i].applyfor[0].time_Limits
		var state = myFilter(datas.lists[i].notes[0].fallbackname)
		var state_number = data.lists[i].state
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
		td2.innerText = userName + ' 元'
		td3.innerText = phone
		td4.innerText = number
		td5.innerText = limit
		td6.classList.add(state_number)
		td6.innerText = state
		td7.innerText = address
		td8.innerText = time
		td10.classList.add('up')
		td10.name = state_number
		td10.href = "#"
		td10.innerText = '[ 挂起  ]'
		td10a.classList.add('record')
		td10a.name = state_number
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
		
		  if(datas.lists[i].notes[0].marked) {
			tr.classList.add('marked')
			sequence.unshift(tr)
          } else {
        	  sequence.push(tr)
          }
	}
	if(sequence.length) {
		for(var j = 0; j < sequence.length; j++) {
			pageElement.insertAdjacentElement('beforeend', sequence[j])
		}
	}
}

// var testData = {
// test: '233',
// lists: [1, 2, 3, 4, 5, 6],
// }
//
// addTable(testData)

// 发送数据方法
var initAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			addTable(data)
		},
		error : function() {
			alert('服务器错误')
		}
	})
}

var initData = function() {
	var method = 'GET'
	var url = '/slloan/loan/loanlist?page=' + init.pages + '&limit='+ init.limit
	var datas = {}
	datas.id = ""
	datas.rolename = localStorage.purrole
	datas.username = localStorage.purusername
	datas.city = localStorage.purcity
	datas.parentnodeId = localStorage.purid
	if (datas.parentnodeId) {
		initAjax(method, url, datas)
	} else {
		alert('请登录!')
	}
}

// 收集信息
var collectData = function() {
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
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			addTable(data)
		},
		error : function() {
			alert('服务器错误')
		}
	})
}

// 发送数据
var sendData = function(element) {
	var evs = document.querySelector(element)
	evs.addEventListener('click', function() {
		var method = 'GET'
		var url = '/slloan/loan/firsttriallikeselect?page=' + init.pages + '&limit='+ init.limit
		var data = collectData()
		data.rolename = localStorage.purrole
		data.username = localStorage.purusername
		data.city = localStorage.purcity
		data.parentnodeId = localStorage.purid
		sendAjax(method, url, data)
	})
}

// 查询
// 发送数据方法
var searchAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				
			} else {
				alert('服务器错误')
			}
		},
		error : function() {
			alert('服务器错误')
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/loanlist?page='+init.pages+'&limit='+ init.limit
	var data = {}
	searchAjax(method, url, data)
}

// 点击列表的具体信息查询
var numberSearch = function(element) {
	var envs = e(element)
			envs.addEventListener(
					'click',
					function(event) {
						var indicate = event.target.classList
						if (indicate == 'mark') {
							localStorage.firstID = ''
							localStorage.firstID = event.target.parentNode.nextSibling.innerText
							window.location.href = '../loan/loanjoins'
						}
					})
}

// 分页接口 user/userlist
var init = {
	pages : 1,
	limit : 10,
}

var currpages = function() {
	var pages = document.querySelector('.currtPage')
	pages.innerText = init.pages
}

currpages()

// 下一页
var nextpage = function() {
	var envs = document.querySelector('.next')
	envs.addEventListener('click', function() {
		var flag = Number(document.querySelector('.totalPage').innerText)
		if (init.pages >= 1 && init.pages < flag) {
			init.pages = init.pages + 1
			firtLoadlist()
			currpages()
		} else {
			layer.open({
				title : '注意',
				content : '已经没有下一页!'
			});
		}
	})
}

nextpage()

// 上一页
var previoupage = function() {
	var envs = document.querySelector('.previous')
	envs.addEventListener('click', function() {
		if (init.pages > 1) {
			init.pages = init.pages - 1
			firtLoadlist()
			currpages()
		} else {
			layer.open({
				title : '注意',
				content : '已经没有上一页!'
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
}

// 全部数据选中
var fullDataCheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
	for (var i = 0; i < len; i++) {
		tent[i].checked = true
	}
	findSave()
}

// 全部数据不选中
var fullDataUncheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
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
		if (event.target.classList.contains('indicate')) {
			findSave()
		}
	})
}

//监听选中的个数等于全部时全选按钮为选中状态
var listenSingle = function(select, element, arr) {
	var flag = arr.length
	var intent = es(element)
	var mark = intent.length
	if(flag == mark) {
		e(select).checked = true
	} else {
		e(select).checked = false
	}
}

// 一个个选中直到全部选中
var singleAdd = function() {
	var result = []
	var parentElement = e('.tab-data')
	var intent = e('#full')
	var definite = es('.indicate')
	parentElement.addEventListener('click', function() {
		listenSingle('#alls', '.indicate', saveSend) 
		for (var i = 0; i < definite.length; i++) {
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
	var intent = e(item)
	var flag = e(index)
	intent.addEventListener('click', function() {
		var run = es(element)
		var mark = flag.checked
		if(mark) {
			for(var i = 0; i < run.length; i++) {
				run[i].checked = true
			}
			findSave()
		} else {
			for(var i = 0; i < run.length; i++) {
				run[i].checked = false
			}
			findSave()
		}
	})
}

// 批量后全选按钮不选中
var unselected = function(element) {
	var intent = document.querySelector(element)
	var flag = intent.checked 
	if(flag) {
		intent.checked = false
	}
}


// 初审批量拒绝方法
// 发送数据方法
var refuseAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				saveSend.length = 0
				initData()
				unselected('#alls')
			}
		},
		error: function() {
			alert('服务器错误')
		}
	})
}

// 初审批量拒绝事件
var batchRefuse = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function() {
		var method = "POST"
		var url = "/slloan/financevoucher/refusegobackmortgage"
		var datas = {}
		datas.rolename = localStorage.purrole
		datas.username = localStorage.purusername
		datas.city = localStorage.purcity
		datas.parentnodeId = localStorage.purid
		var data = saveSend
		if (data.length > 0) {
			data.push(datas)
			layer.confirm('确定批量审批通过?', {icon: 3, title:'注意'}, function(index){
				refuseAjax(method, url, data)
	    		layer.close(index);
	    	});
		} else {
			alert('至少选中一个')
		}
	})
}

// 初审量通过方法
// 发送数据方法
var passAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			if (data.msg == 'success') {
				saveSend.length = 0
				initData()
				unselected('#alls')
			}
		},
		error: function() {
			alert('服务器错误')
		}
	})
}

// 初审量通过事件
var batchPass = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function() {
		var method = "POST"
		var url = "/slloan/financevoucher/pastgobackfinalreview"
		var datas = {}
		datas.rolename = localStorage.purrole
		datas.username = localStorage.purusername
		datas.city = localStorage.purcity
		datas.parentnodeId = localStorage.purid
		var data = saveSend
		if (data.length > 0) {
			data.push(datas)
			layer.confirm('确定批量审批通过?', {icon: 3, title:'注意'}, function(index){
				passAjax(method, url, data)
	    		layer.close(index);
	    	});
		} else {
			alert('至少选中一个')
		}
	})
}


//流转记录查询
var hang_cirulationAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
          var content = data.obj.b.applicationnumber
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
          } else {
              tableString += "<tr><td>" + content + "</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><tr>"
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
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
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
			var method = "GET"
		    var url = "/slloan/loan/loanlist?page=" + init.pages + '&limit='+ init.limit
		    var datas = {}
			datas.state = event.target.name
			datas.id = event.target.parentNode.classList.value
			datas.rolename = localStorage.purrole
			datas.username = localStorage.purusername
			datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			HangAjax(method, url, datas)
		} else if(event.target.classList.contains("record")) {
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
	sendData('#save-data')
	initData()
	listenAllSel('#full', "#alls", '.indicate')
	numberSearch('.tab-data')
//	fullSelection('#full')
	single()
	singleAdd()
	batchPass("#batch-pass")
	batchRefuse("#batch-refuse")
	Hang_cirulation('.tab-data')
}

__main()
