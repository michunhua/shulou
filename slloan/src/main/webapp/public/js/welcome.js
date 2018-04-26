console.log("欢迎登录")

var welcomeLogin = function() {
	var intent = document.querySelector('.welcome')
	intent.innerText = localStorage.username || '未登录'
}

welcomeLogin()