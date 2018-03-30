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
  var area = document.querySelector('.role-power')
  var len = data.length
  if(len >= 0) {
    area.innerText = powerShow.join()
  }
}

// 添加权限函数
var addsomePower = function(element) {
  var loan = document.querySelector(element)
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
  var loan = document.querySelector(element)
  loan.addEventListener('click', function(event) {
    addsomePower(element)
  })
}

removeSelectPower('.role-loan')
removeSelectPower('.role-finance')
removeSelectPower('.role-info')
removeSelectPower('.role-set')
removeSelectPower('.role-handle')
removeSelectPower('.role-manage')

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
   data.name = document.querySelector('.role-name').value
   data.description = document.querySelector('.role-description').value
   data.city = document.querySelector('.role-city').value
   data.note = document.querySelector('.role-note').value
   // data.setPurview = document.querySelector('.role-power').value
   data.setPurview = getEachData(powerShow, Opower)
   return data
}

// 发送 ajax
var sendAjax = function(method, url, datas, callback) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: callback
    })
}

//  提交表单数据
var sendForm = function() {
  var btn = document.querySelector('#send-data')
  btn.addEventListener('click', function() {
    var getData = collectData()
    var data = getData
    var url = '/slloan/role/addpowerlimit'
    var method = 'POST'
    sendAjax(method, url, data, null)
  })
}

sendForm()
