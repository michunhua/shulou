layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/demo/table/user/' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '编号', width:200,  fixed: 'left'}
      ,{field: 'username', title: '用户名', width:200}
      ,{field: 'sex', title: '分配角色', width:200, sort: true}
      ,{field: 'city', title: '员工姓名', width:200}
      ,{field: 'city', title: '创建日期', width:200}
      ,{field: 'city', title: '操作', width:200, fixed: 'last'}
    ]]
  });
});

// layui.use('table', function(){
//   var table = layui.table;
//   //监听表格复选框选择
//   table.on('checkbox(demo)', function(obj){
//     console.log(obj)
//   });
//   //监听工具条
//   table.on('tool(demo)', function(obj){
//     var data = obj.data;
//     if(obj.event === 'detail'){
//       layer.msg('ID：'+ data.id + ' 的查看操作');
//     } else if(obj.event === 'del'){
//       layer.confirm('真的删除行么', function(index){
//         obj.del();
//         layer.close(index);
//       });
//     } else if(obj.event === 'edit'){
//       layer.alert('编辑行：<br>'+ JSON.stringify(data))
//     }
//   });
//
//   var $ = layui.$, active = {
//     getCheckData: function(){ //获取选中数据
//       var checkStatus = table.checkStatus('idTest')
//       ,data = checkStatus.data;
//       layer.alert(JSON.stringify(data));
//     }
//     ,getCheckLength: function(){ //获取选中数目
//       var checkStatus = table.checkStatus('idTest')
//       ,data = checkStatus.data;
//       layer.msg('选中了：'+ data.length + ' 个');
//     }
//     ,isAll: function(){ //验证是否全选
//       var checkStatus = table.checkStatus('idTest');
//       layer.msg(checkStatus.isAll ? '全选': '未全选')
//     }
//   };
//
//   $('.demoTable .layui-btn').on('click', function(){
//     var type = $(this).data('type');
//     active[type] ? active[type].call(this) : '';
//   });
// });



// 发送 ajax
var sendAjax = function(method, url, datas, callback) {
  log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(d) {
    	  if(d.lists.length !== undefined) {
    	        log(d)
    	        var datas = d
    	        var len = datas.lists.length
    	        var pageElement = document.querySelector('.tab-data')
    	        var totalPage = document.querySelector('.totalPage')
    	        totalPage.innerText = datas.totalPage
    	        pageElement.innerHTML = null
    	        for(var i = 0; i < len; i++) {
    	          log(i)
    	          var tr = document.createElement('tr')
    	          var td0 = document.createElement('td')
    	          var td1 = document.createElement('td')
    	          var td2 = document.createElement('td')
    	          var td3 = document.createElement('td')
    	          var td4 = document.createElement('td')
    	          var td5 = document.createElement('td')
    	          var span0 = document.createElement('a')
    	          var span1 = document.createElement('a')
    	          var span2 = document.createElement('a')
    	          var id = datas.lists[i].id
    	          var userName = datas.lists[i].userName
    	          var role = datas.lists[i].distribution_Role
    	          var employeeName = datas.lists[i].employeeis_Name
    	          var time = datas.lists[i].create_Date
    	          td0.innerText = id
    	          td0.classList.add('flag')
    	          td1.innerText = userName
    	          td2.innerText = role
    	          td3.innerText = employeeName
    	          td4.innerText = time
    	          span0.href = '#'
    	          span1.href = '#'
    	          span2.href = '#'
    	          span0.innerText = '  编辑  '
    	          span1.innerText = '  删除  '
    	          span2.innerText = '  查看  '
    	          td5.appendChild(span0)
    	          td5.appendChild(span1)
    	          td5.appendChild(span2)
    	          tr.appendChild(td0)
    	          tr.appendChild(td1)
    	          tr.appendChild(td2)
    	          tr.appendChild(td3)
    	          tr.appendChild(td4)
    	          tr.appendChild(td5)
    	          pageElement.insertAdjacentElement('beforeend', tr)
    	        }
    	  }
      }
    })
}

// function demo(data) {
//   console.log(data)
//   var ds = data
//   var len = ds.lists.length
//   console.log('JSON', ds.lists[0].createDate)
//   var Felement = document.querySelector('.tab-data')
//   console.log(Felement)
//   for(var i = 0; i < len; i++) {
//     console.log('append')
//     var flag = '选中'
//     var id = ds.lists[i].id
//     var role = ds.lists[i].roleName
//     var createT = ds.lists[i].createDate
//     console.log('id', id)
//     var tr = document.createElement('tr')
//     var td0 = document.createElement('td')
//     var input = document.createElement('input')
//     input.type = 'checkbox'
//     var td1 = document.createElement('td')
//     td1.classList.add('roleId')
//     var td2 = document.createElement('td')
//     var td3 = document.createElement('td')
//     td0.appendChild(input)
//     td1.innerText = id
//     td2.innerText = role
//     td3.innerText = createT
//     tr.appendChild(td0)
//     tr.appendChild(td1)
//     tr.appendChild(td2)
//     tr.appendChild(td3)
//     Felement.insertAdjacentElement('beforeend', tr)
//   }
// }

// 分页接口 user/userlist
var init = {
		pages: 1,
		limit: 10,
}

var currpages = function() {
	var pages = document.querySelector('.currtPage')
	pages.innerText = init.pages
}

currpages()

// 获取页面数据
var getPageData = function() {
  var method = 'GET'
  var url = '/slloan/user/userlist?page=' + init.pages + '&limit=' + init.limit
  var datas = {}
  sendAjax(method, url, datas, null)
}

getPageData()

// 下一页
var nextpage = function() {
	var envs = document.querySelector('.next')
	envs.addEventListener('click', function() {
		var flag = Number(document.querySelector('.totalPage').innerText)
		if(init.pages >= 1 && init.pages < flag) {
			init.pages = init.pages + 1
			getPageData()
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
			getPageData()
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


// 添加用户按钮
var addUser = function() {
	var envs = document.querySelector('#addUser')
	envs.addEventListener('click', function() {
		window.location.href = '../user/addu'
	})
}

addUser()


// 删除按钮发送数据
    var deleteAjax = function(method, url, datas) {
	log(' send data deleteajax')
	$.ajax({
		type : method,
		url : url,
		data : {datas:JSON.stringify(datas)},
		success : function(data) {
			if(data.msg == 'success') {
	    		alert('删除成功')
	    		getPageData()
	    	} else {
	    		alert('服务器发生了一个错误')
	    	}
		},
		error: function(){
            alert('服务器错误')
        }
	})
}
// 查看按钮发送数据
    var readAjax = function(method, url, datas) {
	log(' send data readajax')
	$.ajax({
		type : method,
		url : url,
		data : {datas:JSON.stringify(datas)},
		success : function(data) {
			console.log(data)
			layer.open({
				  title: '用户信息'
				  ,content: '用户名: '  + data.userName + '<br>' + 
				  			'密码: ' + data.passWord + '<br>' + 
				  			'员工姓名: '  + data.employeeis_Name + '<br>' +
				  			'分配角色: ' + data.distribution_Role + '<br>' +
				  			'所属城市: '  + data.belongs_City + '<br>' +
				  			'备注: ' + data.note,
				});
		},
		error: function(){
	          alert('服务器错误')
	      }
	})
}
// 编辑 删除 查看

var groupBtn = function() {
	var envs = document.querySelector('.tab-data')
	envs.addEventListener('click',
			function(e) {
				var mark = e.target.innerText.trim()
				console.log(mark)
				if (mark == '编辑') {
					localStorage.editorUserName = null
					localStorage.editorUserName = e.target.parentNode.parentNode.querySelector('.flag').innerText
					window.location.href = '../user/updateuser'
				} else if (mark == '删除') {
					layer.confirm('确定删除?', {icon: 3, title:'注意'}, function(index){
						console.log('测试一下')
						var method = 'POST'
						var url = '/slloan/user/deluser'
						var data = {}
						data.id = e.target.parentNode.parentNode
								.querySelector('.flag').innerText
						console.log(data)
						deleteAjax(method, url, data)
						  layer.close(index);
						});
				} else if (mark == '查看') {
					var method = 'GET'
					var url = '/slloan/user/modifselect'
					var data = {}
					data.id = e.target.parentNode.parentNode
							.querySelector('.flag').innerText
					console.log(data)
					readAjax(method, url, data)
				}
			})
}

groupBtn()

