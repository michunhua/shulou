log('updatePassword')
$(function(){
	$('.username').val(localStorage.purusername);
})
// 收集数据
var collectData = function() {
    var data = {}
    data.username = e('.username').value
    data.oldpassword  = e('.lastPassword').value
    data.newpassword = e('.newPassword').value
    data.id = localStorage.purid
    return data
}

// 发送数据
var sendAjax = function(method, url, datas, callback) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
//    	  alert(localStorage.purusername);
    	 var username =  e('.username').value
    	 var oldpassword  = e('.lastPassword').value
    	 var newpassword = e('.newPassword').value
    	 if(oldpassword == "" || oldpassword == null){
    		  alert("请输入旧密码")
    	  }else if(newpassword == "" || newpassword == null){
    		  alert("请输入新密码")
    	  }else{
    		  alert(data.value)
    		  $('.lastPassword').val("")
    		  $('.newPassword').val("")
    		  document.querySelector('#logout').click()
    	  }
    	 
      }
      
    })
}

// 提交数据
var update = function() {
    var datas = collectData()
    console.log('running', datas)
    var method = 'POST'
    var url = '/slloan/user/updatepwd'
    var data = datas
    sendAjax(method, url, data, null)
}

// 提交事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
	  update()
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
//  var forms = e('form')
  var evs = e(element)
  evs.addEventListener('click', function() {
//    forms.reset()
	  $('.lastPassword').val("")
	  $('.newPassword').val("")
	  window.history.back()
  })
}

var __main = function() {
  envs('#save-data')
  cancelBtn('#cancel')
}

__main()
