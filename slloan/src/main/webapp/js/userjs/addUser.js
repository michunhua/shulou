var log = console.log.bind(console)

// 收集数据
var colect = function() {
  log('收集数据')
  var data = {}
  data.userName = document.querySelector('.userName').value
  data.password = document.querySelector('.password').value
  data.employee = document.querySelector('.employeeName').value
  data.role = document.querySelector('.role').value
  data.city = document.querySelector('.city').value
  data.note = document.querySelector('.text-note').value
  return data
}

// 发送数据
var sendData = function() {
  log('发送数据')
  var ev = document.querySelector('#save-data')
  ev.addEventListener('click', function() {
    var datas = colect()
    console.log(datas)
  })
}

// 执行函数
var __main = function() {
  sendData()
}

__main()
