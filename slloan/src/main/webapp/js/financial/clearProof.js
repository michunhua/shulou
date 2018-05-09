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

//依赖库方法
layui.use('laydate', function(){
  var laydate = layui.laydate;

  //执行一个laydate实例
  laydate.render({
    elem: '#test1' //指定元素
  });
  //日期
  laydate.render({
    elem: '#date'
  });
  laydate.render({
	    elem: '#dates'
	  });
});

//过滤数据
var myFilter = function(data) {
	var result = ""
	if(data) {
		return data
	} else {
		return  result
	}
}


// 添加表格具体数据格
var addTable = function(data) {
    var datas = data
//    var len = 
    var len = datas.lists
    var pageElement = document.querySelector('.tab-data')
    var totalPage = document.querySelector('.totalPage')
    totalPage.innerText = datas.totalPage
    pageElement.innerHTML = null
    for(var i = 0; i < len.length; i++) {
      log(i)
      var tr = document.createElement('tr')
      var td0 = document.createElement('td')
      var a = document.createElement('a')
      var td1 = document.createElement('td')
      var td1a = document.createElement('td')
      var td2 = document.createElement('td')
      var td3 = document.createElement('td')
      var td4 = document.createElement('td')
      var td5 = document.createElement('td')
      var td6 = document.createElement('td')
      var td7 = document.createElement('td')
      var td8 = document.createElement('td')
      var td9 = document.createElement('td')
      var span1 = document.createElement('a')
   	  var span2 = document.createElement('a')
   	  var applicationnumber = myFilter(datas.lists[i].applicationnumber)
   	  var ID = myFilter(data.lists[i].notes[0].id)
		var userid = myFilter(datas.lists[i].name)
		var userName = myFilter(datas.lists[i].applyfor[0].amount)
		var phone = myFilter(datas.lists[i].mobile_phone)
		var number = myFilter(datas.lists[i].id_number)
		var limit = myFilter(datas.lists[i].applyfor[0].time_Limit)
		var state = myFilter(datas.lists[i].state)
		var address = myFilter(datas.lists[i].home_address_now)
		var time = myFilter(datas.lists[i].ctime)


		a.classList.add('mark')
		a.href = '#'
		a.innerText = applicationnumber
		td1.classList.add('flag')
		td1a.innerText = ID
		td1.innerText = userid
		td2.innerText = userName
		td3.innerText = phone
		td4.innerText = number
		td5.innerText = limit
		td6.classList.add("state")
		td6.innerText = state
		td7.innerText = address
		td8.innerText = time
//      td9.innerText = 9
      span1.classList.add('upload')
      span1.href = '#'
      span1.innerText = '[   上传     ]'
      span2.classList.add('record')
      span2.href = '#'  
      span2.innerText = '[ 流转记录   ]'
    	  
      td9.classList.add(ID)
      td9.appendChild(span1)
      td9.appendChild(span2)
      td0.appendChild(a)
      tr.appendChild(td0)
      tr.appendChild(td1a)
      tr.appendChild(td1)
      tr.appendChild(td2)
      tr.appendChild(td3)
      tr.appendChild(td4)
      tr.appendChild(td5)
      tr.appendChild(td6)
      tr.appendChild(td7)
      tr.appendChild(td8)
      tr.appendChild(td9)
      pageElement.insertAdjacentElement('beforeend', tr)
    }
}

var testData = {
		test: '233'
}

//addTable(testData)


//默认加载 方法
var sendAjax = function(method, url, datas) {
console.log(' send data ajax')
 $.ajax({
   type: method,
   url: url,
   data: {data:JSON.stringify(datas)},
   success: function(data) {
     addTable(data)
   }, 
   error: function() {
	   alert('服务器加载数据错误')
   }
 })
}

//默认查询
var initData = function() {
	console.log('初始化加载数据')
	var method = 'GET'
	var url = '/slloan/loan/loanjslist?page='+init.pages+'&limit='+ init.limit
	var datas = {}
	 datas.rolename = localStorage.purrole
	  datas.username = localStorage.purusername
	  datas.city = localStorage.purcity
	  datas.parentnodeId = localStorage.purid
		console.log('初始化加载数据233')
	sendAjax(method, url, datas)
	console.log('执行没有？')
}

//收集数据
var collectData = function() {
    var data = {}
    data.userName = e('.username').value
    data.iphone = e('.iphone').value
    data.IDcard = e('.Idcard').value
    data.numbering = e('.application').value
    data.start = e('.start').value
    data.end = e('.end').value
    data.min = e('.min').value
    data.max = e('.max').value
    return data
}

//查询方法
var inquireAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        addTable(data)
      },
      error: function() {
    	  alert("服务器错误")
      }
    })
}

// 事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
    console.log('running', datas)
    var method = 'GET'
    var url = '/slloan/loan/financeselect'
    var datas = collectData()
    datas.state = es('.state')[0].innerText
    datas.rolename = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
    datas.parentnodeId = localStorage.purid
    inquireAjax(method, url, datas)
  })
}


//创建贷款按钮
var createBtn = function() {
	var ens = document.querySelector('#createLoan')
	ens.addEventListener('click', function() {
		window.location.href = '../../slloan/loan/loanjoin'
	})
}

//具体查询
var numberSearch = function() {
	var envs = es('.mark')
	for(var i = 0; i < envs.length; i++) {
		envs[i].addEventListener('click', function(event) {
			console.log(event.target.innerText)
		})
	}
}

//收集数据
var collectData = function() {
    var data = {}
    data.userName = e('.username').value
    data.iphone = e('.iphone').value
    data.IDcard = e('.Idcard').value
    data.numbering = e('.application').value
    data.start = e('.start').value
    data.end = e('.end').value
    data.min = e('.min').value
    data.max = e('.max').value
    return data
}

//查询方法
var inquireAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
        addTable(data)
      },
      error: function() {
    	  alert('服务器错误')
      }
    })
}

// 事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
    console.log('running', datas)
    var method = 'GET'
    var url = '/slloan/loan/settlementdocument'
    var datas = collectData()
    datas.state = es('.state')[0].innerText
     datas.rolename = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
    datas.parentnodeId = localStorage.purid
    inquireAjax(method, url, datas)
  })
}

//分页接口 user/userlist
var init = {
		pages: 1,
		limit: 10,
}

var currpages = function() {
	var pages = document.querySelector('.currtPage')
	pages.innerText = init.pages
}

currpages()

//下一页
var nextpage = function() {
	var envs = document.querySelector('.next')
	envs.addEventListener('click', function() {
		var flag = Number(document.querySelector('.totalPage').innerText)
		if(init.pages >= 1 && init.pages < flag) {
			init.pages = init.pages + 1
			initData()
			currpages()
		} else {
			layer.open({
				  title: '注意'
				  ,content: '已经没有下一页!'
				});
		}
	})
}

nextpage()

//上一页
var previoupage = function() {
	var envs = document.querySelector('.previous')
	envs.addEventListener('click', function() {
		if(init.pages > 1) {
			init.pages = init.pages - 1
			initData()
			currpages()
		} else {
			layer.open({
				  title: '注意'
				  ,content: '已经没有上一页!'
				});
		}
	})
}

previoupage()


//上传&流转记录
var uploadCertificate = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains("upload")) {
			console.log('上传')
			layer.open({
				  title: '上传凭证'
				  ,content: '<form  id="frm-reg"  enctype="multipart/form-data"><fieldset>'+
						'<legend>上传凭证</legend>'+
						'上传类型 <input type="text" id="uploadtype1" value="结算凭证" name="upload_type" /><br>'+
						'用户名：<input type="text" value="" id="username1" name="username" readonly="readonly"/><br/> '+
						'city：<input type="text" value="" id="city1" name="city" readonly="readonly"/><br/>'+
						'id：<input type="text" id="id1"  value="" name="id" readonly="readonly"/><br/>'+
						'角色名:<input type="text" id="rolename1"  value="" name="rolename" readonly="readonly"/><br/>'+
						'状态:<input type="text" id="specialState"  value="" name="state" readonly="readonly"/><br/>'+
						'备注 :<textarea type="text" id="note1"  value="" name="note"/></textarea><br/>  '+
						'<input type="file"  name="file" onchange="document.getElementById("filepath1").value=this.value;"/>'+
						'<input type="hidden" name="filepath" id="filepath1" onchange="alert(this.value)" value=""/>'+
						'<input type="button" class="layui-btn layui-btn-normal" onclick="updateUserInfo()"  value="提交" />'+
						'</fieldset>'+
						'</form>'+
						'<div id="imagedata1" class="nav-container">'+
						'</div></br>'
				 ,btn: ['取消']
				});     
			var worth = e('#specialState')
			worth.value = event.target.parentNode.classList
			userInitUpload("#username1", '#city1', '#id1', '#rolename1')
		} else if(event.target.classList.contains("record")) {
			console.log('流转记录')
		}
	}) 
} 


//添加默认上传相关的用户值
var userInitUpload = function(element, elements, elementss, elementes) {
	var intent = document.querySelector(element)
	var intents = document.querySelector(elements)
	var intentss = document.querySelector(elementss)
	var intentes = document.querySelector(elementes)

	
	intent.value = localStorage.purusername
	intents.value = localStorage.purcity
	intentss.value = localStorage.purid
	intentes.value = localStorage.purrole
}

function updateUserInfo() {
    var form = new FormData(document.getElementById("frm-reg"));  
    $.ajax({  
    url: "/slloan/financevoucher/transferaccountsvoucher",
    type:"post",  
    data:form,  
    cache: false,  
    processData: false,  
    contentType: false,  
    success:function(data){
  	  var len = data.length;
  	  console.log(len)
  	  if(len == undefined){
  		  alert("操作成功！"+data.value); 
  		window.location.reload()
  		  var file = data.obj[0].filepath
      	console.log( data.obj[0])
      	var udata = data.obj[0]
      	var dd = "<img   src='"+file+"' ><input type='button' onclick='uuuu("+data.obj[0].id+")'  value='提交' />";
      	  $("#imagedata1").append(dd)
  	  }else{
  		  alert(data)
  	  }
    },complete:function(msg){ 
        //请求完成后调用的回调函数（请求成功或失败时均调用）
        
    } ,  
    error:function(e){  
        alert("网络错误，请重试！！");  
     }  
    });          
  }
function uuuu(udata){
alert(udata)
}

//流转记录查询
var hang_cirulationAjax = function(method, url, datas) {
  console.log(' send data ajax')
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        console.log(data)
      }, 
      error: function() {
    	  layer.msg('服务器错误')
      }
    })
}

//挂起&流转记录位置
var Hang_cirulation = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains("upload")) {   
			console.log('挂起')
		} else if(event.target.classList.contains("record")) {
			console.log('流转记录')
			var method = "GET"
		    var url = "/slloan/sumiteregresses/selectwhole"
		    var datas = {}
			datas.state = 0
			datas.id = event.target.parentNode.classList.value
		    datas.username = localStorage.purusername
		    datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			hang_cirulationAjax(method, url, datas)
		}
	})
}


var __main = function() {
initData()
numberSearch()
envs('#save-data')
uploadCertificate(".tab-data")
Hang_cirulation('.tab-data')

}

__main()