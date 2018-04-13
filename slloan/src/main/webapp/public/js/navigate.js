  //  辅助 layui
  layui.use('element', function(){
  var element = layui.element;
})



// 发送数据
var lsendAjax = function(method, url, datas, callback) {
    $.ajax({
        type: method,
        url: url,
        // data: {"username":$("#username").val(),"password":$("#password").val(),"code":$("#code").val()},
        data: datas,
        success: function(data) {
					if((((String(data)).split('<title>')[1]).split('</title>')[0]) === '登录') {
            window.location.href = '../../slloan/user/signin'
          }
				}
    })
}

// 收集数据
var collectData = function() {
    var data = {}
    data.username = localStorage.name
    // data.password  = document.querySelector('#password').value
    return data
}

var logout = function() {
    // var data = {}
    // data.name = localStorage.name
    // data.password = document.querySelector('#password')
    // var datas = JSON.stringify(data)


    var loginBtn = document.querySelector('#logout')
    loginBtn.addEventListener('click', function () {
        var method = 'GET'
        var url = '/slloan/user/exit'
        var data = collectData()
        lsendAjax(method, url, data, null)
        // firstPage()
    })
}

logout()

// 默认加载用户名
var initUsername = function() {
	envs = document.querySelector('#username') 
	envs.innerText = localStorage.username || '未登录'
}

initUsername()