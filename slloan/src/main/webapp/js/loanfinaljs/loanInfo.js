// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.amount = e('.amount').value
  data.deadline = e('.deadline').value
  data.unit = e('.unit').value
  data.variety = e('.variety').value
  data.manner = e('.manner').value
  data.bank = e('.bank').value
  data.accountName = e('.account-name').value
  data.account = e('.account').value
  data.repayBank = e('.repay-bank').value
  data.repayCcount = e('.repay-ccount').value
  data.reapyAccountbank = e('.reapy-accountbank').value
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}


// 发送数据
var sendData = function(element) {
  log('send data to server')
  var evs = document.querySelector(element)
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = ''
    var url = ''
    log(data)
    sendAjax(method, url, data, null)
  })
}

//
var __main = function() {
  log( "run")
  sendData('#save-loaner')
}

__main()