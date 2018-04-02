// 收集信息
var collectData = function() {
  log('收集数据')
  var data = {}
  data.linkfName = e('.linkf-name').value
  data.linkfRelationship = e('.linkf-relationship').value
  data.linkfPhone = e('.linkf-phone').value
  data.linksName = e('.links-name').value
  data.linksRelationship = e('.links-relationship').value
  data.linksPhone = e('.links-phone').value
  data.linktName = e('.linkt-name').value
  data.linktRelationship = e('.linkt-relationship').value
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
var sendData = function() {
  log('send data to server')
  var evs = document.querySelector('#save-loaner')
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
  sendData()
}

__main()
