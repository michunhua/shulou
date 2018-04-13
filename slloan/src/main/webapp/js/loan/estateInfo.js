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
  var data = {}
  data.owner = e('.owner').value
  data.accounting = e('.accounting').value
  data.propertyAddress = e('.property-address').value
  data.building = e('.building').value
  data.innerArea = e('.inner-area').value
  data.contract = e('.contract').value
  data.certificate = e('.certificate').value
  data.evaluation = e('.evaluation').value
  data.property = e('.property').value
  data.originalBank = e('.original-bank').value
  data.originalBank = e('.original-amount').value
  data.houseAccount = e('.house-account').value
  data.originalOwed = e('.original-owed').value
  data.bidPrice = e('.bid-price').value
  data.deposit = e('.deposit').value
  data.newBank = e('.new-bank').value
  data.funds = e('.funds').value
  data.newAccount = e('.new-account').value
  data.newApproved = e('.new-approved').value
  data.start='a'
  data.ctiam='b'
  return data
}

// 发送数据方法
var sendAjax = function(method, url, datas, callback) {
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
  				window.location.href = '../../slloan/loan/loanimag'
  			});
    	}
    }
  })
}


// 提交按钮点击事件&发送数据
var sendData = function() {
  var evs = e('#save-loaner')
  evs.addEventListener('click', function() {
    var data = collectData()
    var method = 'POST'
    var url = '/slloan/loan/housepropertydata'
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
  sendData()
  cancelBtn('#cancel')
}

__main()
