layui.use('table', function(){
  var table = layui.table;

  //第一个实例
  table.render({
    elem: '#demo'
    ,height: 315
    ,url: '/slloan/user/userlist' //数据接口
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
