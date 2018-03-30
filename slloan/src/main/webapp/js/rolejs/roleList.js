// layui.use('table', function(){
//   var table = layui.table;
//
//   //第一个实例
//   table.render({
//     elem: '#demo'
//     ,height: 315
//     // ,url: '/demo/table/user/' //数据接口
//     ,url: '/slloan/role/rolemanagement.do'
//     ,page: true //开启分页
//     ,cols: [[ //表头
//       {checkbox: false, width:200,  fixed: 'left'}
//       ,{field: 'totalCount', title: '序号', width:200}
//       ,{field: 'roleName', title: '角色', width:200, sort: true}
//       ,{field: 'createDate', title: '创建时间', width:200}
//     ]]
//   });
// });





// 发送 ajax
var sendAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        var ds = data
        var len = ds.lists.length
        console.log('JSON', ds.lists[0].createDate)
        var Felement = document.querySelector('.tab-data')
        console.log(Felement)
        for(var i = 0; i < len; i++) {
          console.log('append')
          var flag = '选中'
          var id = ds.lists[i].id
          var role = ds.lists[i].roleName
          var createT = ds.lists[i].createDate
          console.log('id', id)
          var tr = document.createElement('tr')
          var td0 = document.createElement('td')
          var input = document.createElement('input')
          input.type = 'checkbox'
          var td1 = document.createElement('td')
          td1.classList.add('roleId')
          var td2 = document.createElement('td')
          var td3 = document.createElement('td')
          td0.appendChild(input)
          td1.innerText = id
          td2.innerText = role
          td3.innerText = createT
          tr.appendChild(td0)
          tr.appendChild(td1)
          tr.appendChild(td2)
          tr.appendChild(td3)
          Felement.insertAdjacentElement('beforeend', tr)
        }
      }
    })
}


//  提交表单数据
var sendForm = function() {
  var btn = document.querySelector('#send-data')
  btn.addEventListener('click', function() {
    console.log('12343')
    // var getData = collectData()
    var data = null
    var url = '/slloan/role/rolemanagement?page=1&limit=10'
    var method = 'GET'
    sendAjax(method, url, data)
  })
}

sendForm()


var firtLoadlist = function() {
    console.log('firtLoadlist')
    // var getData = collectData()
    var data = {}
    var url = '/slloan/role/rolemanagement?page=1&limit=10'
    var method = 'GET'
    sendAjax(method, url, data)
  }


window.onload = function() {
  firtLoadlist()
}


// 以下为按钮操作
// role/batchdelrole.do删除    role/selectbyid.do 点击修改,查询页面跳转  role/update.do 保存修改

// 发送数据方法
var singlesendAjax = function(method, url, datas, callback) {
  // log('send data method')
  $.ajax({
    type: method,
    url: url,
    data: {data:JSON.stringify(datas)},
    success: callback
  })
}

//添加
  var add = function() {
      var findEl = document.querySelector('.add')
      findEl.addEventListener('click', function() {
        console.log('add')
        window.location.href = './addRole.html'
      })
  }

// 删除
  var deletes = function() {
      var findEl = document.querySelector('.delete')
      findEl.addEventListener('click', function() {
        console.log('delete')
        var method = 'POST'
        var url = '/slloan/role/batchdelrole'
        var data = {}
        data.id = result
        singlesendAjax(method, url, data, firtLoadlist)
      })
  }

// 修改时查询
  var updates = function() {
      var findEl = document.querySelector('.update')
      findEl.addEventListener('click', function() {
        var method = 'GET'
        var url = '/slloan/role/selectbyid'
        var data = {'id':'150'}
        console.log('update')
        singlesendAjax(method, url, data, null)
      })
  }

  // 修改保存
  var updateSave = function(element) {
    var findEl = document.querySelector(element)
    findEl.addEventListener('click', function() {
      var method = 'GET'
      var url = '/slloan/role/updateadd'
      var data = {'id':'150'}
      console.log('update')
      singlesendAjax(method, url, data, null)
    })
  }

  add()
  deletes()
  updates()
  // updateSave()


var result = []

  // 复选框事件
var checkboxEvs = function() {
  var parent = document.querySelector('.tab-data')
  parent.addEventListener('click', function(event) {
      console.log(event.target)
      var flag = event.target.checked
      console.log(flag)
      if(flag) {
        console.log('will go')
        var text = event.target.parentNode.nextSibling.innerText
        console.log('text', text)
        if(!result.includes(text)) {
          result.push(text)
        }
      }
      console.log(result)
      return result
  })
}

checkboxEvs()
