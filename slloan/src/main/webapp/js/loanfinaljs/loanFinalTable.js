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

//添加表格具体数据格
var addTable = function(data) {
	console.log('添加表格儿')
    var datas = data
//    var len = 
    var len = (data.lists).length
    var pageElement = document.querySelector('.tab-data')
    pageElement.innerHTML = null
    for(var i = 0; i < len; i++) {
      log(i)
      var tr = document.createElement('tr')
      var td = document.createElement('td')
      var input = document.createElement('input')
      var td0 = document.createElement('td')
      var a = document.createElement('a')
      var td1 = document.createElement('td')
      var td2 = document.createElement('td')
      var td3 = document.createElement('td')
      var td4 = document.createElement('td')
      var td5 = document.createElement('td')
      var td6 = document.createElement('td')
      var td7 = document.createElement('td')
      var td8 = document.createElement('td')
      var td9 = document.createElement('td')
      var td10 = document.createElement('td')
      var id = datas.lists[i].name
      var userName = datas.lists[i].amount
      var phone = datas.lists[i].mobile_phone
      var number = datas.lists[i].id_number
      var limit = datas.lists[i].time_Limit
      var state = datas.lists[i].state
      var address = datas.lists[i].home_address_now
      var time = datas.lists[i].ctime
      
      input.type = 'checkbox'
      input.classList.add('indicate')
      a.classList.add('mark')
      a.href = '#'
      a.innerText = 0
      td1.innerText = id
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

var testData = {
		test: '233'
}

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
    }
  })
}


var initData = function() {
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/rolemanagement?page=1&limit=10'
	var datas = {}
	datas.a = 'a'
	datas.b = 'b'
	datas.id = '1'
	console.log('初始化加载数据233')
	initAjax(method, url, datas, addTable)
	console.log('执行没有？')
}

//收集信息
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
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	console.log(data)
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
			}
		}
	})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/'
	var data = {}
	searchAjax(method, url, data)
}

//分页接口 user/userlist
var init = {
		pages: 1,
		limit: 10,
}

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

//全部数据选中
var fullDataCheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
	console.log('fullcheck', tent, len)
	for(var i = 0; i < len; i++) {
		tent[i].checked = true
	}
}

//全部数据不选中
var fullDataUncheck = function(elements) {
	var tent = es(elements)
	var len = tent.length
	console.log('fullUncheck', tent, len)	
	for(var i = 0; i < len; i++) {
		tent[i].checked = false
	}
}

// 全选按钮
var fullSelection = function(element) {
	var intent = e(element)
	intent.addEventListener('change', function() {
		if(intent.checked) {
			intent.checked = false
			fullDataUncheck('.indicate')
		} else {
			intent.checked = true
			fullDataCheck('.indicate')
		}
	})
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
  initData()
  fullSelection('#full')
}

__main()
