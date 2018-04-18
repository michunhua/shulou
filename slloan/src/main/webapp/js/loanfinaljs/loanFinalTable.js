//layui.use('table', function(){
//  var table = layui.table;
//
//  //第一个实例
//  table.render({
//    elem: '#demo'
//    ,height: 315
//    ,url: '/demo/table/user/' //数据接口
//    ,page: true //开启分页
//    ,cols: [[ //表头
//      {field: 'id', title: '全选', width:80, sort: true, fixed: 'left'}
//      ,{field: 'user', title: '申请编号', width:200}
//      ,{field: 'username', title: '姓名', width:80}
//      ,{field: 'sex', title: '申请金额', width:200, sort: true}
//      ,{field: 'city', title: '手机号码', width:200}
//      ,{field: 'sign', title: '证件号码', width: 200}
//      ,{field: 'experience', title: '贷款期限', width: 200, sort: true}
//      ,{field: 'score', title: '状态', width: 80, sort: true}
//      ,{field: 'classify', title: '住房风地址', width: 200}
//      ,{field: 'wealth', title: '创建时间', width: 200, sort: true}
//      ,{field: 'wealth', title: '操作', width: 135, sort: true}
//    ]]
//  });
//
//});


//添加表格具体数据格
var addTable = function(data) {
	console.log('添加表格儿')
    var datas = data
//    var len = 
    var len = 10
    var pageElement = document.querySelector('.tab-data')
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
      var id = "datas.lists[i].userName"
      var userName = "datas.lists[i].userName"
      var role = "datas.lists[i].distribution_Role"
      var employeeName = "datas.lists[i].employeeis_Name"
      var time = "datas.lists[i].createdate"
      input.type = 'checkbox'
      a.classList.add('mark')
      a.href = '#'
      a.innerText = 0
      td1.innerText = 1
      td2.innerText = 2
      td3.innerText = 3
      td4.innerText = 4
      td5.innerText = 5
      td6.innerText = 6
      td7.innerText = 7
      td8.innerText = 8
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

addTable(testData)

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
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = ''
    var url = ''
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

//
var __main = function() {
  log( "run")
  sendData('#save-data')
}

__main()
