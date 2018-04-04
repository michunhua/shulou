log('updatePassword')

// 收集数据
var collectData = function() {
    var data = {}
    data.username = e('.username').value
    data.oldpassword  = e('.lastPassword').value
    data.newpassword = e('.newPassword').value
    return data
}

// 发送数据
var sendAjax = function(method, url, datas, callback) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: callback
    })
}


// 事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
    var datas = collectData()
    console.log('running', datas)
    var method = 'POST'
    var url = '/slloan/user/updatepwd'
    var data = datas
    sendAjax(method, url, data, null)
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var forms = e('form')
  var evs = e(element)
  evs.addEventListener('click', function() {
    forms.reset()
  })
}

var __main = function() {
  envs('#save-data')
  cancelBtn('#cancel')
}

__main()
