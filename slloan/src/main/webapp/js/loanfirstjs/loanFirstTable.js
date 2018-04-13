layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/demo/table/user/' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '全选', width:80, sort: true, fixed: 'left'}
      ,{field: 'user', title: '申请编号', width:200}
      ,{field: 'username', title: '姓名', width:80}
      ,{field: 'sex', title: '申请金额', width:200, sort: true}
      ,{field: 'city', title: '手机号码', width:200}
      ,{field: 'sign', title: '证件号码', width: 200}
      ,{field: 'experience', title: '贷款期限', width: 200, sort: true}
      ,{field: 'score', title: '状态', width: 80, sort: true}
      ,{field: 'classify', title: '住房风地址', width: 200}
      ,{field: 'wealth', title: '创建时间', width: 200, sort: true}
      ,{field: 'wealth', title: '操作', width: 135, sort: true}
    ]]
  });

});

layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;

  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });

  laydate.render({
    elem: '#dates'
  });
  laydate.render({
    elem: '#date1'
  });

  //创建一个编辑器
  var editIndex = layedit.build('LAY_demo_editor');

  //自定义验证规则
  form.verify({
    title: function(value){
      if(value.length < 5){
        return '标题至少得5个字符啊';
      }
    }
    ,pass: [/(.+){6,12}$/, '密码必须6到12位']
    ,content: function(value){
      layedit.sync(editIndex);
    }
  });

  //监听指定开关
  form.on('switch(switchTest)', function(data){
    layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
      offset: '6px'
    });
    layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
  });

  //监听提交
  form.on('submit(demo1)', function(data){
    layer.alert(JSON.stringify(data.field), {
      title: '最终的提交信息'
    })
    return false;
  });


});

log('table')

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
  data.name = e('.username').value
  data.iphone = e('.iphone').value
  data.Idcard = e('.Idcard').value
  data.application = e('.application').value
  data.start = e('.start').value
  data.end = e('.end').value
  data.min = e('.min').value
  data.max = e('.max').value
  data.state = e('.state').value
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

//查询
//发送数据方法
var searchAjax = function(method, url, datas) {
log('send data method')
$.ajax({
type: method,
url: url,
data: {data:JSON.stringify(datas)},
success: function(data) {
	console.log('返回数据', data)
	if(data.msg == 'success') {
	}
}
})
}

var searchData = function() {
	var method = 'GET'
	var url = '/slloan/loan/'
	var data = {}
	searchAjax(method, url, data)
}

//
var __main = function() {
  log( "run")
  sendData('#save-data')
}

__main()
