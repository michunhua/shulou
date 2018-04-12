var log = console.log.bind(console)
function loadXMLDoc()
{
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		// IE6, IE5 浏览器执行代码
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET","/try/ajax/ajax_info.txt",true);
	xmlhttp.send();
}

var sendLogin = function(url, data, callback) {
  var xmlhttp = new XMLHttpRequest()
  xmlhttp.onreadystatechange = function() {
    if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      console.log(xmlhttp.responseText)
      callback()
    }
  }

  xmlhttp.open('POST', url)
  xmlhttp.send(data)
}

var firstPage = function() {
  window.location.href = './home/firstPage.html'
}

var verification = function(data) {
    if(data.name.length){
      if(data.password.length) {
        console.log('to login')
      } else {
        alert('填写密码')
      }
    } else {
      alert('填写用户名')
    }
}

var sendAjax = function(method, url, data) {
	var xhr = new XMLHttpRequest()
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
		}
	}
	xhr.open(method, url)
	xhr.send(data)
}

// 获取验证码
var code = function() {
	var method = 'GET'
	var url = '/slloan/verificationcode'
	var data = ''
	sendAjax(method, url, data)
}

//code()

// 发送数据
var lsendAjax = function(method, url, datas, callback) {
    $.ajax({
      type: method,
      url: url,
      data: {"username":$("#username").val(),"password":$("#password").val(),"code":$("#code").val()},
      success: callback
    })
}

// 收集数据
var collectData = function() {
    var data = {}
    data.username = document.querySelector('#username').value
    data.password  = document.querySelector('#password').value
    return data
}

var login = function() {
    // var data = {}
    // data.name = document.querySelector('#userName')
    // data.password = document.querySelector('#password')
    // var datas = JSON.stringify(data)


    var loginBtn = document.querySelector('#login-btn')
    loginBtn.addEventListener('click', function () {
				var method = 'GET'
				var url = '/slloan/user/login'
				var data = collectData()
				lsendAjax(method, url, data, null)
        // firstPage()
    })
}

login()




// debugger

var btn1 = function() {
  var findEle = document.querySelector('#admin')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = "admin"
  })
}

var btn2 = function() {
  var findEle = document.querySelector('#city')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = '城市经理'
  })
}

var btn3 = function() {
  var findEle = document.querySelector('#mortage')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = '按揭员'
  })
}

var btn4 = function() {
  var findEle = document.querySelector('#first')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = '初审'
  })
}

var btn5 = function() {
  var findEle = document.querySelector('#final')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = '终审'
  })
}

var btn6 = function() {
  var findEle = document.querySelector('#finance')
  findEle.addEventListener('click', function() {
    firstPage()
    localStorage.name = null
    localStorage.name = '审批财务'
  })
}


btn1()
btn2()
btn3()
btn4()
btn5()
btn6()

function sumitlong(){
	var name=	$("#username").val()
	var pas=	$("#password").val()
	var code =$("#code").val()
	if(name == null || name==""){
		alert("请输入用户名")
		return ;
	}else if(pas == null || pas == ""){
		alert("请输入密码")
		return ;
	}else if(code == null || code == ""){
		alert("请输入验证码")
		return ;
	}
}