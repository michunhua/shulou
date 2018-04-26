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

// 引入验证

layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;

  //日期 起始
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });end

  // 日期 终止
  laydate.render({
    elem: '#end'
  });
  laydate.render({
    elem: '#date1'
  });


  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');

  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });

  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  });

  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });


});


//依赖库方法
layui.use('laydate', function(){
  var laydate = layui.laydate;

  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
});

// 添加表格具体数据格
var addTable = function(data) {
    var datas = data
//    var len = 
    var len = (data.lists).length
    var pageElement = document.querySelector('.tab-data')
    pageElement.innerHTML = null
    for(var i = 0; i < len; i++) {
      log(i)
      var tr = document.createElement('tr')
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
      td0.appendChild(a)
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
		test: '233',
		lists: [1, 2, 3, 4]
}

//addTable(testData)


//默认加载
var initAjax = function(method, url, datas, callback) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        if(data.msg == 'success') {
        	callback(data)
            addTable(data)
            var totalPage = document.querySelector('.totalPage')
			totalPage.innerText = data.totalPage
        } else {
        	alert('服务器错误')
        }
      },
      error: function(){
          alert('服务器错误')
       }      
    })
}


var initData = function() {
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/rolemanagement?page='+init.pages+'&limit='+ init.limit
	var datas = {}
	datas.a = 'a'
	datas.b = 'b'
	datas.id = '9'
    datas.role = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
	datas.id = localStorage.purid
	console.log('初始化加载数据233')
	initAjax(method, url, datas, addTable)
	console.log('执行没有？')
}

// 收集数据
var collectData = function() {
    var data = {}
    data.userName = e('.username').value
    data.iphone = e('.iphone').value
    data.IDcard = e('.IDcard').value
    data.numbering = e('.numbering').value
    data.date = e('#date').value
    data.end = e('#end').value
    data.statu = e('.statu').value
    data.min = e('.min').value
    data.max = e('.max').value
    return data    
}

//查询方法
var sendAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
      },
      error: function(){
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
    var url = '/slloan/loan/vaguelikeselectcreate'
    var datas = collectData()
    sendAjax(method, url, datas)
  })
}


// 创建贷款按钮
var createBtn = function() {
	var ens = document.querySelector('#createLoan')
	ens.addEventListener('click', function() {
		window.location.href = '../../slloan/loan/loanjoin'
	})
}

// 具体查询
var numberSearch = function(element) {
	var envs = e(element)
		envs.addEventListener('click', function(event) {
			var indicate = event.target.classList
			if(indicate == 'mark') {
				console.log(event.target.innerText)
				window.location.href = '../loan/loanjoin'
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

var __main = function() {
  envs('#save-data')
  createBtn()
  initData()
  numberSearch('.tab-data')
}

__main()
