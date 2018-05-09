console.log('这是新增加的页面js')

//重新渲染表单
function renderForm(){
	log("重新渲染表单")
layui.use('form', function(){
var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
form.render('select');
});
}


//角色加入
var obtainRoleAjax = function(method, url, datas) {
 $.ajax({
   type: method,
   url: url,
   data: {data:JSON.stringify(datas)},
   success: function(data) {
     log('join role---')
     var envs = document.querySelector('.role')
     var datas = data
     var len = datas.length
     for(var i = 0; i < len; i++) {
       var options = document.createElement('option')
       options.value = datas[i].id + '&' + datas[i].roleName
       options.innerText = datas[i].roleName
       envs.appendChild(options)
     }
     renderForm()
   },
   error: function(){
       alert('服务器出错')
   }
 })
}


//默认加载数据
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
			note.value  = data.note
			
			// 设置角色和城市
//			role.appendChild(option0)
			var Identification = data.distribution_Role
    		var intent = e(".role")
    		var len = intent.length
    		for(var citys = 0; citys < len; citys++) {
    			log(intent[citys].innerText)
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
		editorAjax(method, url, data)
	}
}



// 保存数据方法
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
		}
	})
}
// 收集数据
var collectData = function() {
	  var data = {}
	  data.username = document.querySelector('.username').value
	  data.password = document.querySelector('.password').value
	  data.employee = document.querySelector('.name').value
	  data.role = document.querySelector('.role').value
	  data.city = document.querySelector('.city').value
	  data.note = document.querySelector('.note').value
	  data.id = localStorage.editorUserName
	  return data
}

// 保存
var saveData = function() {
	var envs = document.querySelector('#save')
	envs.addEventListener('click', function() {
		var method = 'POST'
		var url = '/slloan/user/modifyuser'
		var data = collectData()
		saveAjax(method, url, data)
	})
}

// 取消 
var cancelData = function() {
	var envs = document.querySelector('#cancel')
	envs.addEventListener('click', function() {
		localStorage.editorUserName = null
		window.location.href = '../user/ulist'
	})
}


var log = console.log.bind(console)

//发送 ajax
var sendAjax = function(method, url, datas) {
 $.ajax({
   type: method,
   url: url,
   data: {data:JSON.stringify(datas)},
   success: function(data) {
     if(data === 'success') {
       document.querySelector('form').reset()
       alert('保存成功')
       window.location.href = '../user/ulist'
     } else {
     	alert('服务器出错!')
     }
   },
   error: function(){
       alert('服务器出错!!!')
   }
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
     log('join city---')
     console.log(data.belongs_City)
     var datas = data
     var len = Object.keys(datas).length
     log(Object.keys(datas))
     for(var i = 0; i < len; i++) {
       envs.innerHTML = ''
       var options = document.createElement('option')
       options.value = datas.belongs_City
       options.innerText = datas.belongs_City
       envs.appendChild(options)
     }
     renderForm()
   },
   error: function(){
       alert('服务器错误')
   }
 })
}



//获取分配角色
var obtainRole = function(element) {
log('获取角色')
var evs = document.querySelector(element)
var method = 'GET'
var url = '/slloan/role/initrole'
var datas = {}
obtainRoleAjax(method, url, datas, null)
}



//根据角色确定城市
var linkRoleCity = function() {
var form = layui.form;
form.on('select(role)', function(data){
var datas = {
 id: data.value.split('&')[0],
}
var method = 'GET'
var url = '/slloan/role/initcity'
obtainCityAjax(method, url, datas)
});
}




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
	onloadUser()          // 默认加载用户数据
	saveData()			  // 保存修改数据
	cancelData()		  // 取消按钮
	//linkRoleCity()		  // 获取角色城市
}

_main()