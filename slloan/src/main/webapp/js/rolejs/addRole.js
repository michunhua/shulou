// 权限选择
var powerShow = []
var Opower = {
  "回款确认": "1",
  "贷款终审": "2",
  "贷款创建": "3",
  "贷款初审": "4",
  "贷款信息查看": "5",
  "取证凭证上传": "6",
  "进押凭证上传": "7",
  "解押凭证上传": "8",
  "财务审批": "9",
  "放款审批": "10",
  "结算凭证上传": "11",
  "转账凭证上传": "12",
  "个人信息修改": "13",
  "密码修改": "14",
  "添加角色": "15",
  "修改角色信息": "16",
  "删除角色": "17",
  "分配权限": "18",
  "删除权限": "19",
  "添加用户": "20",
  "分配角色": "21",
  "删除用户": "22",
  "修改用户信息": "23",
}

// 对应的发送的权限数字
var getEachData = function(arr, obj) {
  var len = arr.length
  var getArr = {}
  if(len > 0) {
    for (var i = 0; i < len; i++) {
      var numb = obj[arr[i]]
      getArr['power' + numb] = numb
    }
  }
  return getArr
}

// 添加权限
var addPower = function(data) {
  var area = e('.role-power')
  var len = data.length
  if(len >= 0) {
    area.innerText = powerShow.join()
  }
}

// 添加权限函数
var addsomePower = function(element) {
  var loan = e(element)
  var info = loan.querySelectorAll('.layui-form-checked')
  var into = loan.querySelectorAll('.layui-form-checkbox')

  // 删除权限
  for (var i = 0; i < into.length; i++) {
    var factor = powerShow.indexOf(into[i].querySelector('span').innerText)
    if(factor >= 0) {
      powerShow.splice(factor, 1)
      addPower(powerShow)
    }
  }

  // 添加显示权限
  for (var i = 0; i < info.length; i++) {
    if (!powerShow.includes(info[i].querySelector('span').innerText)) {
      powerShow.push(info[i].querySelector('span').innerText)
      addPower(powerShow)
    }
  }
}

// 删除选中权限
var removeSelectPower = function(element) {
  var loan = e(element)
  loan.addEventListener('click', function(event) {
    addsomePower(element)
  })
}



// 错误处理
var errorProcess = function(code) {
  if(code == 404) {
    alert('请求错误')
  } else if(code == 504) {
    alert('请求超时')
  }
}

// 收集数据
var collectData = function() {
  var data = {}
   data.name = e('.role-name').value
   data.description = e('.role-description').value
   data.city = e('.role-city').value
   data.note = e('.role-note').value
   // data.setPurview = document.querySelector('.role-power').value
   data.setPurview = getEachData(powerShow, Opower)
   return data
}

// 发送 ajax
var sendAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
          if(data.msg === 'success')
        	  alert('添加成功')
              window.location.href = '../role/rolelist'
          },
      error: function(){
        alert('服务器错误')
       }         
    })
}

//  提交表单数据
var sendForm = function() {
  var btn = document.querySelector('#send-data')
  btn.addEventListener('click', function() {
    var getData = collectData()
    if(getData.name.length > 0) {
        var data = getData
        var url = '/slloan/role/addpowerlimit'
        var method = 'POST'
        sendAjax(method, url, data)
    } else {
    	alert('请正确填写')
    }
  })
}

// 取消按钮事件
var cancelBtn = function(element) {
  var forms = e('form')
  var roleText = e('.role-power')
  var evs = e(element)
  evs.addEventListener('click', function() {
    forms.reset()
    roleText.innerText = ''
    window.location.href = '../role/rolelist'
  })
}

// 重新渲染表单
function renderForm(){
  layui.use('form', function(){
   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
   form.render();
  });
 }

// 获取城市 ajax
var obtainAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        var evs = document.querySelector('.role-city')
        var datas = data
        var len = datas.length
        evs.appendChild(initOptions)
        for(var i = 0; i < len; i++) {
          var options = document.createElement('option')
          options.value = datas[i].name
          options.innerText = datas[i].name
          evs.appendChild(options)
        }
        renderForm()
      },
      error: function(){
          alert('服务器错误')
      }
    })
}



// 获取城市
var obtainCity = function() {
  var method = 'GET'
  var url = '/slloan/role/getallcity'
  var datas = ''
  obtainAjax(method, url, datas)
}


var __main = function() {
  obtainCity()                        // 获取并渲染城市
  removeSelectPower('.role-loan')     // 贷款权限 按钮
  removeSelectPower('.role-finance')  // 财务权限 按钮
  removeSelectPower('.role-info')     // 个人信息权限 按钮
  removeSelectPower('.role-set')      // 角色设定权限 按钮
  removeSelectPower('.role-handle')   // 权限设置 按钮
  removeSelectPower('.role-manage')   // 用户管理 按钮
  sendForm()                          // 保存按钮点击发送&数据
  cancelBtn('#cancel')                // 取消按钮点击重置表单
}

__main()
