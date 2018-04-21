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
var sendAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
    	  if(data.lists.length !== undefined) {
    		  	console.log(data)
				var ds = data
				var len = ds.lists.length
				console.log('JSON', ds.lists[0].createDate)
				var Felement = document.querySelector('.tab-data')
				var totalPage = document.querySelector('.totalPage')
				totalPage.innerText = ds.totalPage
				console.log(Felement)
				Felement.innerHTML = null
				for (var i = 0; i < len; i++) {
					console.log('append')
					var flag = '选中'
					var id = ds.lists[i].id
					var role = ds.lists[i].roleName
					var createT = ds.lists[i].createDate
					console.log('id', id)
					var tr = document.createElement('tr')
					var td0 = document.createElement('td')
					var input = document.createElement('input')
					input.type = 'checkbox'
					var td1 = document.createElement('td')
					td1.classList.add('roleId')
					var td2 = document.createElement('td')
					var td3 = document.createElement('td')
					td0.appendChild(input)
					td1.innerText = id
					td2.innerText = role
					td3.innerText = createT
					tr.appendChild(td0)
					tr.appendChild(td1)
					tr.appendChild(td2)
					tr.appendChild(td3)
					Felement.insertAdjacentElement('beforeend', tr)
				}
			}
      }
    })
}

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

// 默认加载数据
var firtLoadlist = function() {
    console.log('firtLoadlist', init.pages, init.limit)
    // var getData = collectData()
    var data = {}
    var method = 'GET'
    var url = '/slloan/role/rolemanagement?page='+init.pages+'&limit='+ init.limit
    sendAjax(method, url, data)
  }


window.onload = function() {
  firtLoadlist()
}


// 以下为按钮操作
// role/batchdelrole.do删除    role/selectbyid.do 点击修改,查询页面跳转  role/update.do 保存修改

// 发送数据方法
var singlesendAjax = function(method, url, datas, callback) {
  // log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}

//添加
  var add = function() {
      var findEl = document.querySelector('.add')
      findEl.addEventListener('click', function() {
        console.log('add')
        window.location.href = '../role/roleadd'
      })
  }

// 删除
  var deletes = function() {
      var findEl = document.querySelector('.delete')
      findEl.addEventListener('click', function() {
        console.log('delete')
        var method = 'POST'
        var url = '/slloan/role/batchdelrole'
        var data = {}
        data.id = result
        singlesendAjax(method, url, data, firtLoadlist)
      })
  }

  
// 修改时查询
  var updates = function() {
      var findEl = document.querySelector('.update')
      findEl.addEventListener('click', function() {
        var method = 'GET'
        var url = '/slloan/role/selectbyid'
        var data = {'id':'150'}
        console.log('update')
        singlesendAjax(method, url, data, null)
      })
  }

  // 修改保存
  var updateSave = function(element) {
    var findEl = document.querySelector(element)
    findEl.addEventListener('click', function() {
      var method = 'GET'
      var url = 'role/update'
      var data = {'id':'150'}
      console.log('update')
      singlesendAjax(method, url, data, null)
    })
  }

  add()
  deletes()
  updates()
  // updateSave()


var result = []

  // 复选框事件
var checkboxEvs = function() {
  var parent = document.querySelector('.tab-data')
  parent.addEventListener('click', function(event) {
      console.log(event.target)
      var flag = event.target.checked
      console.log(flag)
      if(flag) {
        console.log('will go')
        var text = event.target.parentNode.nextSibling.innerText
        console.log('text', text)
        if(!result.includes(text)) {
          result.push(text)
        }
      }
      console.log(result)
      return result
  })
}

checkboxEvs()


// 下一页
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

