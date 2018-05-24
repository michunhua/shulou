//重新渲染表单
function renderForm(){
layui.use('form', function(){
var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
form.render('select');
});
}

var log = console.log.bind(console)

//角色加入方法
var obtainRoleAjax = function(method, url, datas) {
 $.ajax({
   type: method,
   url: url,
   data: {data:JSON.stringify(datas)},
   success: function(data) {
     var envs = document.querySelector('.role')
     var datas = data
     if(datas.obj) {
         console.log(data.obj.length)
         var len = datas.obj.length
         for(var i = 0; i < len; i++) {
           onloadUser()  
           var options = document.createElement('option')
           options.value = datas.obj[i].id + '&' + datas.obj[i].roleName
           options.innerText = datas.obj[i].roleName
           envs.appendChild(options)
         }
         renderForm()
     }
   },
   error: function(){
       alert('服务器出错')
   }
 })
}

//获取分配角色请求
var obtainRole = function(element) {
	 log('获取角色')
	var evs = document.querySelector(element)
	var method = 'GET'
	var url = '/slloan/role/initrole'
	var datas = {}
	datas.parentid = localStorage.roleUseID
	datas.username = localStorage.purusername
	datas.city = localStorage.purcity
	obtainRoleAjax(method, url, datas)
}


//默认加载用户名及密码和备注的数据方法
var editorAjax = function(method, url, datas) {
	$.ajax({
		type : method,
		url : url,
		data : {datas:JSON.stringify(datas)},
		success : function(data) {
			var username = document.querySelector('.username')
			var pwd = document.querySelector('.password')
			var name = document.querySelector('.name')
			var role = document.querySelector('.role')
			var city = document.querySelector('.city')
			var note = document.querySelector('.note')
			var option0 = document.createElement('option')
			var option1 = document.createElement('option')
			
			option0.value = data.distribution_Role
			option1.value = data.belongs_City
			option0.innerText = data.distribution_Role
			option1.innerText = data.belongs_City
			
			
			username.value = data.userName
			pwd.value = data.passWord
			name.value  = data.employeeis_Name
			name.name = data.id
			console.log(data.id)
			note.value  = data.note
			
			// 设置角色和城市
//			role.appendChild(option0)
			var Identification = data.distribution_Role
    		var intent = e(".role")
    		var len = intent.length
    		for(var citys = 0; citys < len; citys++) {
//    			log(intent[citys].innerText)
    			if(intent[citys].innerText == Identification) {
    				console.log('相等吗？')
    				console.log(citys)
    				intent[citys].selected = true
    			}
    		}
			
			// 设置城市
			city.appendChild(option1)
			
			renderForm()
		},
		error: function(){
	          alert('服务器错误')
	      }
	})
}

//默认加载用户数据
var onloadUser = function() {
	if (localStorage.editorUserName) {
		var method = 'GET'
		var url = '/slloan/user/modifselect'
		var data = {}
		data.id = localStorage.editorUserName
		data.parentid = localStorage.roleUseID
		data.username = localStorage.purusername
		if(data.id) {
			editorAjax(method, url, data)
		}
	}
}

// 收集数据
var collectData = function() {
	  var data = {}
	  data.username = document.querySelector('.username').value
	  data.password = document.querySelector('.password').value
	  data.employee = document.querySelector('.name').value
	  data.urolename = document.querySelector('.role').value.split('&')[1]
	  data.city = document.querySelector('.city').value
	  data.note = document.querySelector('.note').value
	  data.id = document.querySelector('.role').value.split('&')[0]
	  data.uid = document.querySelector('.name').name
	  return data
}

////发送 ajax
//var sendAjax = function(method, url, datas) {
// $.ajax({
//   type: method,
//   url: url,
//   data: {data:JSON.stringify(datas)},
//   success: function(data) {
//     if(data === 'success') {
//       document.querySelector('form').reset()
//       alert('保存成功')
//       window.location.href = '../user/ulist'
//     } else {
//     	alert('服务器出错!')
//     }
//   },
//   error: function(){
//       alert('服务器出错!!!')
//   }
// })
//}

//保存数据方法
var saveAjax = function(method, url, datas){
	$.ajax({
		type : method,
		url : url,
		data : {datas:JSON.stringify(datas)},
		success : function(data) {
			console.log(data)
			alert('修改成功')
			localStorage.editorUserName = null
			window.location.href = '../user/ulist'
		},
		error: function() {
			alert('服务器错误')
		}
	})
}

// 修改保存事件及发送数据
var saveData = function() {
	var envs = document.querySelector('#save')
	envs.addEventListener('click', function() {
		var method = 'POST'
		var url = '/slloan/user/modifyuser'
		var data = collectData()
		saveAjax(method, url, data)	
	})
}

// 取消回到列表
var cancelData = function() {
	var envs = document.querySelector('#cancel')
	envs.addEventListener('click', function() {
		localStorage.editorUserName = null
		window.location.href = '../user/ulist'
	})
}


//城市加入
var obtainCityAjax = function(method, url, datas) {
 $.ajax({
   type: method,
   url: url,
   data: {data:JSON.stringify(datas)},
   success: function(data) {
     var envs = e('.city')
//     log('join city---')
     console.log(data.obj.length)
     var datas = data
     var len = datas.obj.length
     log(len)
     for(var i = 0; i < len; i++) {
       envs.innerHTML = ''
       var options = document.createElement('option')
       options.value = datas.obj[i].belongs_City
       options.innerText = datas.obj[i].belongs_City
       envs.appendChild(options)
     }
     renderForm()
   },
   error: function(){
       alert('服务器错误')
   }
 })
}

//根据用户显示城市方法
var cityRoleAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        var envs = e('.city')
        log('join city---')
          envs.innerHTML = ''
          var options = document.createElement('option')
          options.value = data.belongs_City
          options.innerText = data.belongs_City
          envs.appendChild(options)        
        renderForm()
      },
      error: function(){
          alert('服务器错误')
      }
    })
}


//根据角色确定城市
var linkRoleCity = function() {
	var form = layui.form;
	form.on('select(role)', function(data) {
		var datas = {
			id : data.value.split('&')[0],
		}
		console.log(data)
		var method = 'GET'
		var url = '/slloan/role/initcity'
		cityRoleAjax(method, url, datas)
	});
}

linkRoleCity()

//获取城市
var obtainCtiy = function(element) {
var envs = document.querySelector(element)
var method = 'GET'
var url = '/slloan/role/initcity'
var datas = {}
obtainCityAjax(method, url, datas)
}



var _main = function() {
	obtainRole()		   // 默认载入角色
//	onloadUser()          // 默认加载用户数据
	saveData()			  // 保存修改数据
	cancelData()		  // 取消按钮
//	linkRoleCity()		  // 获取角色城市
}

_main()