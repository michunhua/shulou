layui.use('table', function() {
	var table = layui.table;

	// 第一个实例
	table.render({
		elem : '#demo',
		height : 315,
		url : '/demo/table/user/' // 数据接口
		,
		page : true // 开启分页
		,
		cols : [ [ // 表头
		{
			field : 'id',
			title : '全选',
			width : 80,
			sort : true,
			fixed : 'left'
		}, {
			field : 'user',
			title : '申请编号',
			width : 200
		}, {
			field : 'username',
			title : '姓名',
			width : 80
		}, {
			field : 'sex',
			title : '申请金额',
			width : 200,
			sort : true
		}, {
			field : 'city',
			title : '手机号码',
			width : 200
		}, {
			field : 'sign',
			title : '证件号码',
			width : 200
		}, {
			field : 'experience',
			title : '贷款期限',
			width : 200,
			sort : true
		}, {
			field : 'score',
			title : '状态',
			width : 80,
			sort : true
		}, {
			field : 'classify',
			title : '住房风地址',
			width : 200
		}, {
			field : 'wealth',
			title : '创建时间',
			width : 200,
			sort : true
		}, {
			field : 'wealth',
			title : '操作',
			width : 135,
			sort : true
		} ] ]
	});

});

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

log('table')

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
	console.log('添加表格儿')
	var datas = data
	var len = (datas.lists).length
	var pageElement = document.querySelector('.tab-data')
	var totalPage = document.querySelector('.totalPage')
	totalPage.innerText = datas.totalPage
	pageElement.innerHTML = null
	console.log('回写数据长度', len)
	for (var i = 0; i < len; i++) {
		log('执行次数' +i)
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
		var td10 = document.createElement('td')
		var ID = myFilter(datas.lists[i].notes[0].id)
		var userid = myFilter(datas.lists[i].name)
		var userName = myFilter(datas.lists[i].applyfor[0].amount)
		var phone = myFilter(datas.lists[i].mobile_phone)
		var number = myFilter(datas.lists[i].id_number)
		var limit = myFilter(datas.lists[i].applyfor[0].time_Limit)
		var state = myFilter(datas.lists[i].state)
		var address = myFilter(datas.lists[i].home_address_now)
		var time = myFilter(datas.lists[i].ctime)

		input.type = 'checkbox'
		input.classList.add('indicate')
		a.classList.add('mark')
		a.href = '#'
		a.innerText = '201802241931'
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
		td9.innerText = 9
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

// var testData = {
// test: '233',
// lists: [1, 2, 3, 4, 5, 6],
// }
//
// addTable(testData)

// 发送数据方法
var initAjax = function(method, url, datas) {
	log('send data method')
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
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/loanlist?page=' + init.pages + '&limit='+ init.limit
	var datas = {}
	datas.role = localStorage.purrole
	datas.username = localStorage.purusername
	datas.city = localStorage.purcity
	datas.parentnodeId = localStorage.purid
	console.log('初始化加载数据233')
	if (datas.parentnodeId) {
		initAjax(method, url, datas)
	} else {
		alert('请登录!')
	}
}

// 收集信息
var collectData = function() {
	log('收集数据')
	var data = {}
	data.name = e('.username').value
	data.iphone = e('.iphone').value
	data.Idcard = e('.Idcard').value
	data.application = e('.application').value
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
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			console.log(data)
		},
		error : function() {
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
		var method = 'POST'
		var url = ''
		var data = collectData()
		log(data)
		sendAjax(method, url, data)
	})
}

// 查询
// 发送数据方法
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
		error : function() {
			alert('服务器错误')
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/loanlist'
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
							console
									.log(
											event.target.parentNode.nextSibling.innerText,
											'就这个数据')
							console.log(event.target.innerText, '好的')
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

// 一个个选中直到全部选中

var singleAdd = function() {
	var result = []
	var parentElement = e('.tab-data')
	var intent = e('#full')
	var definite = es('.indicate')
	console.log('一个接一个的点击')
	parentElement.addEventListener('click', function() {
		console.log('执行到这', definite.length)
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

// 初审批量拒绝方法
// 发送数据方法
var refuseAjax = function(method, url, datas) {
	log('send data method')
	$.ajax({
		type : method,
		url : url,
		data : {
			data : JSON.stringify(datas)
		},
		success : function(data) {
			console.log('拒绝返回数据', data)
			if (data.msg == 'success') {
				initData()
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
		console.log('refuse')
		var method = "POST"
		var url = "/slloan/financevoucher/refusegobackmortgage"
		var data = saveSend
		if (data.length > 0) {
			refuseAjax(method, url, data)
		} else {
			alert('至少选中一个')
		}
	})
}

// 初审量通过方法
// 发送数据方法
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
				initData()
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
		console.log('pass')
		var method = "POST"
		var url = "/slloan/financevoucher/pastgobackfinalreview"
		var data = saveSend
		if (data.length > 0) {
			passAjax(method, url, data)
		} else {
			alert('至少选中一个')
		}
	})
}

//
var __main = function() {
	log("run")
	sendData('#save-data')
	initData()
	numberSearch('.tab-data')
	fullSelection('#full')
	single()
	singleAdd()
	batchPass("#batch-pass")
	batchRefuse("#batch-refuse")
}

__main()
