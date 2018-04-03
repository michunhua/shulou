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
  // 固定值
  data.a = new Date()
  data.b = 'b'
  data.cname = e('.ch-name').value
  // data.ename = e('.en-name').value
  data.paperwork = e('.paperwork-type').value
  data.paperNumb = e('.paperwork-numb').value
  // data.country = e('.country').value
  // data.gender = e('.gender').value
  // data.census = e('.census').value
  // data.marriage = e('.marriage').value
  // data.housing = e('.housing').value
  // data.birthday = e('.birthday').value
  // data.currentAddress = e('.current-address').value
  // data.residencePhone = e('.residence-phone').value
  data.mobilePhone = e('.mobile-phone').value
  // data.email = e('.E-mail').value
  // data.code = e('.zip-code').value
  // data.career = e('.career').value
  // data.unit = e('.unit-industry').value
  data.unitName = e('.unit-name').value
  // data.unitAddress = e('.unit-address').value
  // data.companyNumber = e('.company-number').value
  // data.lastyearIncome = e('.lastyear-income').value
  // data.assetSize = e('.asset-size').value
  data.unitPhone = e('.unit-phone').value
  data.housePhone = e('.house-phone').value
  // data.unitCode = e('.unit-code').value
  // data.jobsType = e('.jobs-type').value
  // data.unitTime = e('.unit-time').value
  // data.lastunitName = e('.lastunit-name').value
  // data.lastunitTime = e('.lastunit-time').value
  // data.incomeSource = e('.income-source').value
  data.salary = e('.salary').value
  // data.investment = e('.investment').value
  // data.rent = e('.rent').value
  // data.added = e('.added').value
  // data.supportPeople = e('.support-people').value
  // data.expenses = e('.expenses').value
  // data.communication = e('.communication').value
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
    var method = 'POST'
    var url = '/slloan/loan/commonApplyspouse'
    log(data)
    sendAjax(method, url, data, null)
  })
}

//
var __main = function() {
  log( "run")
  sendData('#save-coMate')
}

__main()
