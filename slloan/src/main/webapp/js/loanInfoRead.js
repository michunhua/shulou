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
    if(data && data != 'null' && data != 'undefined') {
		return data
	} else {
		return  result
	}
}


// 添加表格具体数据格
var addTable = function(data) {
    var datas = data
    var len = datas.lists
    var count = document.querySelector('#count')
    count.innerText = data.totalCount
    var pageElement = document.querySelector('.tab-data')
    var totalPage = document.querySelector('.totalPage')
    totalPage.innerText = datas.totalPage
    pageElement.innerHTML = null
    for(var i = 0; i < len.length; i++) {
      log(i)
      var tr = document.createElement('tr')
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
      var span1 = document.createElement('a')
   	  var span2 = document.createElement('a')
   	  var applicationnumber = myFilter(datas.lists[i].applicationnumber)
   	  var ID = myFilter(data.lists[i].notes[0].id)
		var userid = myFilter(datas.lists[i].name)
		var userName = myFilter(datas.lists[i].applyfor[0].amount)
		var phone = myFilter(datas.lists[i].mobile_phone)
		var number = myFilter(datas.lists[i].id_number)
		var limit = myFilter(datas.lists[i].applyfor[0].time_Limit)  + myFilter(datas.lists[i].applyfor[0].time_Limits)
		var state = myFilter(datas.lists[i].notes[0].fallbackname)
		var address = myFilter(datas.lists[i].home_address_now)
		var time = myFilter(datas.lists[i].ctime)


		a.classList.add('mark')
		a.href = '#'
		a.innerText = applicationnumber
		td1.classList.add('flag')
		td1a.innerText = ID
		td1.innerText = userid
		if(userName) {
			td2.innerText = userName  + ' 元'
		} else {
			td2.innerText = userName
		}
		
		td3.innerText = phone
		td4.innerText = number
		td5.innerText = limit
		td6.innerText = state
		td7.innerText = address
		td8.innerText = time
//      td9.innerText = 9
      span1.classList.add('upload')
      span1.href = '#'
//      span1.innerText = '[   上传     ]'
      span2.classList.add('record')
      span2.name = state
      span2.href = '#'  
      span2.innerText = '[ 流转记录   ]'
      td9.classList.add(ID)
      td9.appendChild(span1)
      td9.appendChild(span2)
      td0.appendChild(a)
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

var testData = {
		test: '233'
}

//addTable(testData)


//默认加载方法
var sendAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        addTable(data)
      }
    })
}

//默认查询
var initData = function() {
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/LoanInformation?page='+init.pages+'&limit='+ init.limit
	var datas = {}
	 datas.rolename = localStorage.purrole
	  datas.username = localStorage.purusername
	  datas.city = localStorage.purcity
	  datas.parentnodeId = localStorage.purid
		console.log('初始化加载数据233')
	sendAjax(method, url, datas)
	console.log('执行没有？')
}

//收集数据
var collectData = function() {
    var data = {}
    data.userName = e('.username').value
    data.iphone = e('.iphone').value
    data.IDcard = e('.Idcard').value
    data.numbering = e('.application').value
    data.start = e('.start').value
    data.end = e('.end').value
    data.state = e('.state').value
    data.min = e('.min').value
    data.max = e('.max').value
    return data
}

//查询方法
var inquireAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        addTable(data)
      },
      error: function() {
    	  alert('服务器错误')
      }
    })
}

// 查询按钮事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
    console.log('running', datas)
    var method = 'GET'
    var url = '/slloan/loan/loaninformationselect?page='+init.pages+'&limit='+ init.limit
    var datas = collectData()
    datas.rolename = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
    datas.parentnodeId = localStorage.purid
    inquireAjax(method, url, datas)
  })
}


// 创建贷款按钮
var createBtn = function() {
	var ens = document.querySelector('#createLoan')
	ens.addEventListener('click', function() {
		window.location.href = '../../slloan/loan/loanjoin'
	})
}

//具体查询
var numberSearch = function(element) {
	var envs = e(element)
		envs.addEventListener('click', function(event) {
			var indicate = event.target.classList
			if(indicate == 'mark') {
				localStorage.readID = event.target.parentNode.nextSibling.innerText
				console.log(localStorage.clearInfo)
				window.location.href = '/slloan/loan/loaninfoone'
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
			initData()
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
			initData()
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

//挂起&流转记录位置
var Hang_cirulation = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains("upload")) {   
			console.log('挂起')
		} else if(event.target.classList.contains("record")) {
			console.log('流转记录')
			var method = "GET"
		    var url = "/slloan/sumiteregresses/selectwhole"
		    var datas = {}
			datas.state = event.target.name
			datas.id = event.target.parentNode.classList.value
		    datas.username = localStorage.purusername
		    datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			hang_cirulationAjax(method, url, datas)
		}
	})
}

var __main = function() {
  initData()
  numberSearch(".tab-data")
  envs('#save-data')
  Hang_cirulation('.tab-data')
}

__main()


