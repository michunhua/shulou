log('linkInfo')

// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.linkf = e('.linkf').value
  data.linkfMate = e('.linkf-mate').value
  data.linkfPhone = e('.linkf-phone').value
  data.links = e('.links').value
  data.linksMate = e('.links-mate').value
  data.linksPhone = e('.links-phone').value
  data.linkt = e('.linkt').value
  data.linktMate = e('.linkt-mate').value
  data.linktPhone = e('.linkt-phone').value
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
  sendData('#save-data')
}

__main()