var log = console.log.bind(console)

var firstPage = function() {
  window.location.href = '/slloan/loan/happy'
}


//发送数据
var lsendAjax = function(method, url, datas) {
  $.ajax({
      type: method,
      url: url,
      data: {"username":$("#username").val(),"password":$("#password").val(),"code":$("#code").val()},
      success: function(data) {
        console.log(data)
		console.log(data.msg)
		if(data.msg === 'success') {
		  localStorage.username = ''
          localStorage.purview = ''
          localStorage.purrole = ''
          localStorage.purusername = ''
          localStorage.purcity = ''
          localStorage.purid = ''
          localStorage.roleUseID = ''
          var len = data.obj.lists.length
          for(var i = 0; i < len; i++) {
            console.log(data.obj.lists[i].checkboxID)
            if(data.obj.lists[i].checkboxID !== undefined) {
               localStorage.purview += data.obj.lists[i].checkboxID + ','
            }
            console.log(data.obj.lists[i].belongs_City)
            if(data.obj.lists[i].belongs_City !== undefined ) {
            	localStorage.purcity = data.obj.lists[i].belongs_City
            	localStorage.roleUseID = data.obj.lists[i].r_id
            }
            console.log(data.obj.lists[i].id)
            if(data.obj.lists[i].id !== undefined && data.obj.lists[i].id !== null) {
            	localStorage.purid = data.obj.lists[i].id
            }
            console.log(data.obj.lists[i].userName)
            if(data.obj.lists[i].userName !== undefined ) {
            	localStorage.purusername = data.obj.lists[i].userName
            	localStorage.username = data.obj.lists[i].userName
            }
            console.log(data.obj.lists[i].roleName)
            if(data.obj.lists[i].roleName !== undefined ) {
            	localStorage.purrole = data.obj.lists[i].roleName
            }
          }
						// layer.msg('玩命提示中')
          firstPage()
		} else if(data.msg === 'fail') {
          localStorage.purview = ''
          alert(data.obj)
        } else {
          localStorage.purview = ''
          var errorData = data.split(':')
          console.log(errorData)
          var showinfo = errorData[3].split(' ')[0]
          alert(showinfo)
        }
	},
	error: function(){
        alert('服务器出错!!!')
    }
  })
}

//收集数据
var collectData = function() {
  var data = {}
  data.username = document.querySelector('#username').value
  data.password  = document.querySelector('#password').value
  data.code = document.querySelector('#code').value
  return data
}

// 填写验证 后登录
var verification = function(data, next) {
	console.log("执行登录验证")
	if(!data.username){
		alert('请填写用户名')
		document.querySelector('#username').focus()
		return false
	} 
	
	if(!data.password) {
		alert('请填写密码')
		document.querySelector('#password').focus()
		return false
	} 
	
	if(!data.code) {
		alert('请填写验证码')
		document.querySelector('#code').focus()
		return false
	}
	
	next()
}

// 页面监听回车键
var mainPage = function() {
	var intent = document.querySelector('body')
	intent.addEventListener('keydown', function(e) {
		console.log("监听回车键")
	    if(e.keyCode == 13) {
	    	var valid = collectData()
			verification(valid, submit)
			} else {
				console.log('keydown')
			}
	  })
}

//页面监听点击登录按钮
var listenLoginBtn = function() {
	var intent = document.querySelector('body')
	intent.addEventListener('click', function(e) {
		console.log("监听点击登录按钮", e.target.id)
	    if(e.target.id == "login-btn") {
	    	var valid = collectData()
			verification(valid, submit)	 
			} else {
				console.log('click')
			}
	  })
}

var submit = function() {
	var method = 'GET'
		var url = '/slloan/user/login'
		var data = collectData()
		lsendAjax(method, url, data)
}

//var login = function() {
//    var loginBtn = document.querySelector('#login-btn')
//        loginBtn.addEventListener('click', function() {
//    		var valid = collectData()
//    		verification(valid, submit)
//        })
//}

//增加用户体验
var useruse = function() {
  var usernameInput = document.querySelector('#username')
  var passwordInput = document.querySelector('#password')
  var codefirstInput = document.querySelector('#code')
  var loginbtn = document.querySelector('#login-btn')
  usernameInput.focus()
  usernameInput.addEventListener('keydown', function(e) {
    if(e.keyCode == 13) {
      passwordInput.focus()
    }
  })
  passwordInput.addEventListener('keydown', function(e) {
    if(e.keyCode == 13) {
      codefirstInput.focus()
    }
  })
  codefirstInput.addEventListener('keydown', function(e) {
    if(e.keyCode == 13) {
//      loginbtn.focus()
    }
  })
}

var __main = function() {
	useruse()
	mainPage()
	listenLoginBtn()
}

__main()