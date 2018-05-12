var log = console.log.bind(console)

// 判别名字
var judeName = function(data) {
	var str = new RegExp("^[A-Za-z]+$")
	return str.test(data)
}


// 发送 ajax
var sendAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
    	  console.log(data)
        if(data.msg === 'success') {
          document.querySelector('form').reset()
          alert('保存成功')
          window.location.href = '../user/ulist'
        } else if(data.msg === 'fail') {
        	alert(data.value)
        } else {
        	alert('服务器出错!')
      }
    },
      error: function(){
          alert('服务器出错!!!')
      }
    })
}

// 重新渲染表单
function renderForm(){
  layui.use('form', function(){
   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
   form.render();
  });
 }

// 角色加入
var obtainRoleAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
//    	console.log(data.obj[0].id)
//        log('join role---')
        var envs = e('.role')
        var datas = data
        if(data.obj) {
            var len = datas.obj.length
            for(var i = 0; i < len; i++) {
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

// 城市加入
var obtainCityAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        var envs = e('.city')
        log('join city---')
        console.log(data.obj.length)
        var datas = data
        var len = datas.obj.length
        log(Object.keys(datas))
        for(var i = 0; i < len; i++) {
//          envs.innerHTML = ''
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


// 获取分配角色
var obtainRole = function(element) {
//  log('获取角色')
  var evs = document.querySelector(element)
  var method = 'GET'
  var url = '/slloan/role/initrole'
  var datas = {}
  datas.parentid = localStorage.roleUseID
  datas.username = localStorage.purusername
  datas.city = localStorage.purcity
  obtainRoleAjax(method, url, datas, null)
}

window.onload = obtainRole()


// 根据用户显示城市方法
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


// 根据角色确定城市
var linkRoleCity = function() {
  var form = layui.form;
  form.on('select(role)', function(data){
  var datas = {
    id: data.value.split('&')[0],
  }
  console.log(datas)
  var method = 'GET'
  var url = '/slloan/role/initcity'
  cityRoleAjax(method, url, datas)
});
}

linkRoleCity()


// 获取城市
var obtainCtiy = function(element) {
//	console.log("获取想对应的城市")
  var envs = document.querySelector(element)
  var method = 'GET'
  var url = '/slloan/user/selectrolecity'
  var datas = {}
  datas.parentid = localStorage.roleUseID
  datas.username = localStorage.purusername
  datas.city = localStorage.purcity
  obtainCityAjax(method, url, datas)
}

// 收集数据
var colect = function() {
  var data = {}
  data.userName = e('.userName').value
  data.password = e('.password').value
  data.employee = e('.employeeName').value
  data.role = e('.role').value.split('&')[1]
  data.city = e('.city').value
  data.note = e('.text-note').value
  data.parentid = localStorage.roleUseID
  data.id = localStorage.purid
  return data
}

// 保存按钮点击事件&发送数据
var sendData = function() {
//  log('发送数据')
  var ev = document.querySelector('#save-data')
  ev.addEventListener('click', function() {
    var datas = colect()
    var flag = judeName(datas.userName)
    console.log(datas)
    if(flag) {
        if(datas.userName.length > 2 && datas.password.length > 2) {
        	console.log(datas)
            var url = '/slloan/user/adduser'
            var method = 'POST'
            sendAjax(method, url, datas)
        } else {
        	alert('用户名和密码都需要2位以上。')
        }
    } else {
    	alert('用户名必须为字母')
    }
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var forms = document.querySelector('form')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    forms.reset()
    window.location.href = '../user/ulist'
  })
}

// 执行函数
var __main = function() {
  obtainCtiy()
  sendData()
  cancelBtn('#cancel')
}

__main()
