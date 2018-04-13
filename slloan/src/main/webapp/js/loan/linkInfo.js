//自定义
var log = console.log.bind(console)

// 自定义
var e = function(elements) {
  return  document.querySelector(elements)
}

// 依赖库方法
layui.use('laydate', function(){
  var laydate = layui.laydate;

  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
});


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
  data.start = 'a'
	  data.ctime = 'b'
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
  log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: function(data) {
    	if(data.msg == 'success') {
    		layer.msg('保存成功', {
  			  icon: 2,
  			  time: 2000 
  			}, function(){
  				window.location.href = '../../slloan/loan/loannote'
  			});
    	}
    }
  })
}


// 提交按钮点击事件&发送数据
var sendData = function() {
  log('send data to server')
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    log('data to send at time')
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/contactinformation'
    log(data)
    sendAjax(method, url, data)
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

//
var __main = function() {
  log( "run")
  sendData()
  cancelBtn('#cancel')
}

__main()
