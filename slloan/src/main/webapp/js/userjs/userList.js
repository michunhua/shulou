layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/demo/table/user/' //数据接口
    ,page: true //开启分页
    ,cols: [[ //表头
      {field: 'id', title: '编号', width:200,  fixed: 'left'}
      ,{field: 'username', title: '用户名', width:200}
      ,{field: 'sex', title: '分配角色', width:200, sort: true}
      ,{field: 'city', title: '员工姓名', width:200}
      ,{field: 'city', title: '创建日期', width:200}
      ,{field: 'city', title: '操作', width:200, fixed: 'last'}
    ]]
  });
});

// layui.use('table', function(){
//   var table = layui.table;
//   //监听表格复选框选择
//   table.on('checkbox(demo)', function(obj){
//     console.log(obj)
//   });
//   //监听工具条
//   table.on('tool(demo)', function(obj){
//     var data = obj.data;
//     if(obj.event === 'detail'){
//       layer.msg('ID：'+ data.id + ' 的查看操作');
//     } else if(obj.event === 'del'){
//       layer.confirm('真的删除行么', function(index){
//         obj.del();
//         layer.close(index);
//       });
//     } else if(obj.event === 'edit'){
//       layer.alert('编辑行：<br>'+ JSON.stringify(data))
//     }
//   });
//
//   var $ = layui.$, active = {
//     getCheckData: function(){ //获取选中数据
//       var checkStatus = table.checkStatus('idTest')
//       ,data = checkStatus.data;
//       layer.alert(JSON.stringify(data));
//     }
//     ,getCheckLength: function(){ //获取选中数目
//       var checkStatus = table.checkStatus('idTest')
//       ,data = checkStatus.data;
//       layer.msg('选中了：'+ data.length + ' 个');
//     }
//     ,isAll: function(){ //验证是否全选
//       var checkStatus = table.checkStatus('idTest');
//       layer.msg(checkStatus.isAll ? '全选': '未全选')
//     }
//   };
//
//   $('.demoTable .layui-btn').on('click', function(){
//     var type = $(this).data('type');
//     active[type] ? active[type].call(this) : '';
//   });
// });



// 发送 ajax
var sendAjax = function(method, url, datas, callback) {
  log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        log(data)
        var datas = data
        var len = datas.lists.length
        var pageElement = document.querySelector('.tab-data')
        for(var i = 0; i < len; i++) {
          log(i)
          var tr = document.createElement('tr')
          var td0 = document.createElement('td')
          var td1 = document.createElement('td')
          var td2 = document.createElement('td')
          var td3 = document.createElement('td')
          var td4 = document.createElement('td')
          var td5 = document.createElement('td')
          var id = datas.lists[i].id
          var userName = datas.lists[i].userName
          var role = datas.lists[i].distribution_Role
          var employeeName = datas.lists[i].employeeis_Name
          var time = datas.lists[i].createDate
          td0.innerText = id
          td1.innerText = userName
          td2.innerText = role
          td3.innerText = employeeName
          td4.innerText = time
          tr.appendChild(td0)
          tr.appendChild(td1)
          tr.appendChild(td2)
          tr.appendChild(td3)
          tr.appendChild(td4)
          tr.appendChild(td5)
          pageElement.insertAdjacentElement('beforeend', tr)
        }
      }
    })
}

// function demo(data) {
//   console.log(data)
//   var ds = data
//   var len = ds.lists.length
//   console.log('JSON', ds.lists[0].createDate)
//   var Felement = document.querySelector('.tab-data')
//   console.log(Felement)
//   for(var i = 0; i < len; i++) {
//     console.log('append')
//     var flag = '选中'
//     var id = ds.lists[i].id
//     var role = ds.lists[i].roleName
//     var createT = ds.lists[i].createDate
//     console.log('id', id)
//     var tr = document.createElement('tr')
//     var td0 = document.createElement('td')
//     var input = document.createElement('input')
//     input.type = 'checkbox'
//     var td1 = document.createElement('td')
//     td1.classList.add('roleId')
//     var td2 = document.createElement('td')
//     var td3 = document.createElement('td')
//     td0.appendChild(input)
//     td1.innerText = id
//     td2.innerText = role
//     td3.innerText = createT
//     tr.appendChild(td0)
//     tr.appendChild(td1)
//     tr.appendChild(td2)
//     tr.appendChild(td3)
//     Felement.insertAdjacentElement('beforeend', tr)
//   }
// }

// 分页接口 user/userlist
// 获取页面数据
var getPageData = function() {
  var method = 'GET'
  var url = '/slloan/user/userlist?page=1&limit=10'
  var datas = {}
  sendAjax(method, url, datas, null)
}

getPageData()
