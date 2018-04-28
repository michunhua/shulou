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
						localStorage.username = ''
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



//导航栏权限
var powerNavgator = function() {
	var power = localStorage.purview
	
}

// 导航状态
var navgatorState = {
		off: function(element) {
			var intent = document.querySelector(element)
			intent.style.display = 'none'
		},
		on: function(element) {
			var goal = document.querSelector(element)
			goal.style.display = 'block'
		}
}

// 各个单条目
//navgatorState.off("#tree-create")
//navgatorState.off("#tree-trival")
//navgatorState.off("#tree-final")
//navgatorState.off("#tree-read")
//navgatorState.off("#tree-confirm")
//navgatorState.off("#tree-gain")
//navgatorState.off("#tree-relief")
//navgatorState.off("#tree-incare")
//navgatorState.off("#navfinance-list")
//navgatorState.off("#navfinance-trans")
//navgatorState.off("#navfinance-clear")
//navgatorState.off("#navuserInfo-update")
//navgatorState.off("#navuserInfo-password")
//navgatorState.off("#navuserManagement-user")
//navgatorState.off("#navuserManagement-role")

// 总条目
//navgatorState.off("#navloan")
//navgatorState.off("#navfinance")
//navgatorState.off("#navuserInfo")
//navgatorState.off("#navuserManagement")


//根据权限值来显示
var Authority = {
		create: [], 
		trival: [],
		finals: [],
		read: [],
		confirm: [],
		gain: [],
		relief: [],
		incare: [],
		list: [],
		trans: [],
		clear: [],
		update: [],
		password: [],
		user: [],
		role: [],
}





