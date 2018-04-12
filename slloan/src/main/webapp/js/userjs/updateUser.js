console.log('这是新增加的页面js')


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
			
			console.log(option0)
			
			username.value = data.userName
			pwd.value = data.passWord
			name.value  = data.employeeis_Name
			role.appendChild(option0)
			city.appendChild(option1)
			note.value  = data.note
		}
	})
}


window.onload = function() {
	if (localStorage.editorUserName != null) {
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
	  return data
}

// 保存
var saveData = function() {
	var envs = document.querySelector('#save')
	envs.addEventListener('click', function() {
		localStorage.editorUserName = null
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

var _main = function() {
	saveData()
	cancelData()
}

_main()