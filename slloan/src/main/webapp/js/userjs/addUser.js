var log = console.log.bind(console)

// 发送 ajax
var sendAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        if(data === 'success') {
          document.querySelector('form').reset()
          alert('保存成功')
        }
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
        log('join role---')
        var envs = e('.role')
        var datas = data
        var len = datas.length
        for(var i = 0; i < len; i++) {
          var options = document.createElement('option')
          options.value = datas[i].id + '&' + datas[i].roleName
          options.innerText = datas[i].roleName
          envs.appendChild(options)
        }
        renderForm()
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
      }
    })
}



// 获取分配角色
var obtainRole = function(element) {
  log('获取角色')
  var evs = document.querySelector(element)
  var method = 'GET'
  var url = '/slloan/role/initrole'
  var datas = {}
  obtainRoleAjax(method, url, datas, null)
}

window.onload = obtainRole()

// 根据角色确定城市
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

linkRoleCity()


// 获取城市
var obtainCtiy = function(element) {
  var envs = document.querySelector(element)
  var method = 'GET'
  var url = '/slloan/role/initcity'
  var datas = {}
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
  return data
}

// 保存按钮点击事件&发送数据
var sendData = function() {
  log('发送数据')
  var ev = document.querySelector('#save-data')
  ev.addEventListener('click', function() {
    var datas = colect()
    console.log(datas)
    var url = '/slloan/user/adduser'
    var method = 'POST'
    sendAjax(method, url, datas)
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var forms = document.querySelector('form')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    forms.reset()
  })
}

// 执行函数
var __main = function() {
  sendData()
  cancelBtn('#cancel')
}

__main()
