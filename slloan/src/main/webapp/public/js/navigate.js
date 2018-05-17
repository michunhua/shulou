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
	var result = power.split(',')
	var len = result.length -1
	result.splice(len, 1)
	return result
}

powerNavgator()

// 导航状态
var navgatorState = {
		off: function(element) {
			var intent = document.querySelector(element)
			intent.style.display = 'none'
		},
		on: function(element) {
			var goal = document.querySelector(element)
			goal.style.display = 'block'
		}
}

// 各个单条目
navgatorState.off("#tree-create")
navgatorState.off("#tree-trival")
navgatorState.off("#tree-final")
navgatorState.off("#tree-read")
navgatorState.off("#tree-confirm")
navgatorState.off("#tree-gain")
navgatorState.off("#tree-relief")
navgatorState.off("#tree-incare")
navgatorState.off("#navfinance-list")
navgatorState.off("#navfinance-trans")
navgatorState.off("#navfinance-clear")
navgatorState.off("#navuserInfo-update")
navgatorState.off("#navuserInfo-password")
navgatorState.off("#navuserManagement-user")
navgatorState.off("#navuserManagement-role")

// 总条目
navgatorState.off("#navloan")
navgatorState.off("#navfinance")
navgatorState.off("#navuserInfo")
navgatorState.off("#navuserManagement")


//根据权限值来显示
var Authority = {
		create: [3], //创建贷款
		trival: [4], // 贷款初审
		finals: [2], // 贷款终审
		read: [5],   // 查看贷款信息
		confirm: [1], // 回款确认
		gain: [6],    // 获取凭证
		relief: [8],   // 解押凭证
		incare: [7],   // 进押凭证
		list: [9, 10],     // 财务审批，放款审批
		trans: [12],    // 转账凭证
		clear: [11],    // 结算凭证
		update: [13],   // 个人信息修改
		password: [14], // 密码修改
		user: [20, 21, 22, 23], //用户权限
		role: [15, 16, 17],  // 角色权限
		
		// 几个谁有区块
		loan: [1, 2, 3, 4, 5, 6, 7, 8],   // 贷款管理
		financial: [9, 10, 11, 12], // 财务管理
		uersInfo: [13, 14],  // 用户信息
		userManage: [15, 16, 17, 18, 19, 20, 21, 22, 23], // 用户管理
}


//权限数据值
var flags = powerNavgator()

// 二级导航
var wantTo = function(arr1, arr2, callback, foo) {
	var len = arr2.length
	var result = []
	for(var i = 0; i < len; i++) {
		if(arr1.includes(String(arr2[i]))) {
			result.push(arr2[i])
		}
	}
	if(result.length > 0) {
		callback(foo)
	}
}

// 一级导航
var wantToFirst = function(arr1, arr2, callback, foo) {
	var len = arr2.length
	var result = []
	for(var i = 0; i < len; i++) {
		if(arr1.includes(String(arr2[i]))) {
			result.push(arr2[i])
		}
	}
	if(result.length > 0) {
		callback(foo)
	} else {
		
	}
}

//一级导航执行
wantToFirst(flags, Authority.loan, navgatorState.on, "#navloan")
wantToFirst(flags, Authority.financial, navgatorState.on, "#navfinance")
wantToFirst(flags, Authority.uersInfo, navgatorState.on, "#navuserInfo")
wantToFirst(flags, Authority.userManage, navgatorState.on, "#navuserManagement")

// 二级执行权限
wantTo(flags, Authority.create, navgatorState.on, "#tree-create")
wantTo(flags, Authority.trival, navgatorState.on, "#tree-trival")
wantTo(flags, Authority.finals, navgatorState.on, "#tree-final")
wantTo(flags, Authority.read, navgatorState.on, "#tree-read")
wantTo(flags, Authority.confirm, navgatorState.on, "#tree-confirm")
wantTo(flags, Authority.gain, navgatorState.on, "#tree-gain")
wantTo(flags, Authority.relief, navgatorState.on, "#tree-relief")
wantTo(flags, Authority.incare, navgatorState.on, "#tree-incare")
wantTo(flags, Authority.list, navgatorState.on, "#navfinance-list")
wantTo(flags, Authority.trans, navgatorState.on, "#navfinance-trans")
wantTo(flags, Authority.clear, navgatorState.on, "#navfinance-clear")
wantTo(flags, Authority.update, navgatorState.on, "#navuserInfo-update")
wantTo(flags, Authority.password, navgatorState.on, "#navuserInfo-password")
wantTo(flags, Authority.user, navgatorState.on, "#navuserManagement-user")
wantTo(flags, Authority.role, navgatorState.on, "#navuserManagement-role")


// 必须登录不然啥都没法看

var mustLogin = function(data) {
	if(data) {
//		 console.log('登录成功登录成功登录成功登录成功登录成功登录成功登录成功')
	} else {
		window.location.href = '/slloan/user/signin'
		console.log('到底是去哪儿')
	}
}

//console.log(localStorage.username, '登录成功')

mustLogin(localStorage.username)
