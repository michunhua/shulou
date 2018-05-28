// 依赖库方法
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
		var limit = myFilter(datas.lists[i].applyfor[0].time_Limit)  + datas.lists[i].applyfor[0].time_Limits
		var state = myFilter(datas.lists[i].notes[0].fallbackname)
		var address = myFilter(datas.lists[i].home_address_now)
		var time = myFilter(datas.lists[i].ctime)


		a.classList.add('mark')
		a.href = '#'
		a.innerText = applicationnumber
		td1.classList.add('flag')
		td1a.innerText = ID
		td1.innerText = userid
		td2.innerText = userName  + ' 元'
		td3.innerText = phone
		td4.innerText = number
		td5.innerText = limit
		td6.innerText = state
		td7.innerText = address
		td8.innerText = time
//      td9.innerText = 9
      span1.classList.add('upload')
      span1.href = '#'
      span1.innerText = '[   上传     ]'
      span2.classList.add('record')
      span2.name = state
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


//默认加载
var sendAjax = function(method, url, datas, callback) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        callback(data)
      }
    })
}

// 默认查询
var initData = function() {
	var method = 'GET'
	var url = '/slloan/loan/Decompression?page='+init.pages+'&limit='+ init.limit
	var datas = {}
	datas.rolename = localStorage.purrole
	datas.username = localStorage.purusername
	datas.city = localStorage.purcity
	datas.parentnodeId = localStorage.purid
	sendAjax(method, url, datas, addTable)
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
    data.state = e('.state').value
    data.min = e('.min').value
    data.max = e('.max').value
    return data
}

//查询方法
var inquireAjax = function(method, url, datas) {
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
        addTable(data)
      },
      error: function() {
    	  alert("服务器错误")
      }
    })
}

// 查询按钮事件响应
var envs = function(element) {
  var ens = e(element)
  ens.addEventListener('click', function() {
    var method = 'GET'
    var url = '/slloan/loan/revocationcertificate?page='+init.pages+'&limit='+ init.limit
    var datas = collectData()
    datas.rolename = localStorage.purrole
    datas.username = localStorage.purusername
    datas.city = localStorage.purcity
    datas.parentnodeId = localStorage.purid
    inquireAjax(method, url, datas)
  })
}


// 创建贷款按钮
var createBtn = function() {
	var ens = document.querySelector('#createLoan')
	ens.addEventListener('click', function() {
		window.location.href = '../../slloan/loan/loanjoin'
	})
}

//具体查询
var numberSearch = function(element) {
	var envs = e(element)
		envs.addEventListener('click', function(event) {
			var indicate = event.target.classList
			if(indicate == 'mark') {
				localStorage.relieID = event.target.parentNode.nextSibling.innerText
				window.location.href = '/slloan/loan/lrelieinfoloan'
			}
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

// 上一页
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


// 上传&流转记录
var uploadCertificate = function(element) {
	var intent = e(element)
	intent.addEventListener('click', function(event) {
		if(event.target.classList.contains("upload")) {
			layer.open({
				  title: '上传凭证'
					  ,content: '<form  id="frm-reg"  enctype="multipart/form-data"><fieldset>'+
						'<legend>上传凭证</legend>'+
						'上传类型 <input type="text" id="uploadtype1" value="转账凭证" name="upload_type" /><br>'+
						'用户名：<input type="text" value="" id="username1" name="username" readonly="readonly"/><br/> '+
						'city：<input type="text" value="" id="city1" name="city" readonly="readonly"/><br/>'+
						'id：<input type="text" id="id1"  value="" name="id" readonly="readonly"/><br/>'+
						'<input type="text" id="uid"  value="" name="uid" readonly="readonly" style = "display:none;"/><br/>'+
						'角色名:<input type="text" id="rolename1"  value="" name="rolename" readonly="readonly"/><br/>'+
						'状态:<input type="text" id="specialState"  value="" name="state" readonly="readonly"/><br/>'+
						'备注 :<textarea type="text" id="note1"  value="" name="note"/></textarea><br/>  '+
						'<input type="file"  name="file" onchange="document.getElementById("filepath1").value=this.value;"/>'+
						'<input type="hidden" name="filepath" id="filepath1" onchange="alert(this.value)" value=""/>'+
						'<input type="file" style="display: none;" id="aabbccddeeff"  name="file" onchange="document.getElementById("filepath2").value=this.value;"/>'+
						'<input type="hidden" name="filepath" id="filepath2" onchange="alert(this.value)" value=""/>'+
						'<div id="filewidth" style="width: 278px;margin-top: 4px;"></div>'+
						'<input type="button" class="layui-btn layui-btn-normal" onclick="certificate()"  value="多个凭证上传" />'+
						'<input type="button" id = "upimage" class="layui-btn layui-btn-normal" onclick="updateUserInfo()"  value="提交" />'+
						
						'</fieldset>'+
						'</form>'+
						'<div id="imagedata1" class="nav-container">'+
						'</div></br>'
				,btn: ['取消']
				});     
			var worth = e('#specialState')
			worth.value = event.target.parentNode.classList
			userInitUpload("#username1", '#city1', '#id1', '#rolename1')
			var mark = document.querySelector('#uid')
			mark.value = event.target.parentNode.classList.value
			updataButton("#upimage")
		} else if(event.target.classList.contains("record")) {
		}
	}) 
} 

function certificate(){
	 var input = document.createElement('input');  
	 	input.setAttribute('type', 'file');  
	 	input.setAttribute('name', 'file');  
	    input.setAttribute('style', ' height: 28px;');  
	 document.getElementById('filewidth').appendChild(input );
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
    url: "/slloan/financevoucher/forensicsrelief",
    type:"post",  
    data:form,  
    cache: false,  
    processData: false,  
    contentType: false,  
    success:function(data){
  	  var len = data.length;
  	  if(len == undefined){
  		  if(data.msg == "fail") {
  			alert("操作失败！"+data.value);
  		  } else {
  	  		alert("操作成功！"+data.value);
  	  		window.location.reload()
  	  		var file = data.obj[0].filepath
  	      	var udata = data.obj[0]
  	      	var dd = "<img   src='"+file+"' ><input type='button' onclick='uuuu("+data.obj[0].id+")'  value='提交' />";
  		  }
  	  }else{
  		  alert("不支持的文件类型,请选择后缀带有(jpg,png,jpge,bmp,png)格式")
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
    $.ajax({
      type: method,
      url: url,
      data: {data:JSON.stringify(datas)},
      success: function(data) {
          var content = data.obj.b.applicationnumber
          var tableString = ''
          if(data.obj.a.length) {
          	var flag = data.obj.a.length
          	for(var i = 0; i < flag; i++) {
                  var rolename = data.obj.a[i].rolename
                  var username = data.obj.a[i].username
                  var circulation = data.obj.a[i].circulation
                  var updatedata = data.obj.a[i].updatedata
                  tableString += "<tr><td>" + content + "</td><td>"+ circulation +"</td><td>"+ rolename +"</td><td>"+ username +"</td><td>"+ updatedata +"</td><tr>" 
                  
          	}
          } else {
              tableString += "<tr><td>" + content + "</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><td>"+ 'null' +"</td><tr>"
          }
  		layer.open({
  			  title: '流转记录',
  			  area: ['830px', '560px'],
  			  content: "<table border='1'><tr><th>申请编号</th><th>状态</th><th>负责角色</th><th>负责人</th><th>处理时间</th></tr>" +
  			  		 tableString + "</table>",
  			});
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
		} else if(event.target.classList.contains("record")) {
			var method = "GET"
		    var url = "/slloan/sumiteregresses/selectwhole"
		    var datas = {}
			datas.state = event.target.name
			datas.id = event.target.parentNode.classList.value
		    datas.username = localStorage.purusername
		    datas.city = localStorage.purcity
			datas.parentnodeId = localStorage.purid
			hang_cirulationAjax(method, url, datas)
		}
	})
}

	//限制上传点击次数
	var updataImags = function(select) {
		var intent = document.querySelector(select)
		intent.disabled = 'disabled'
		setTimeout(function() {
			intent.disabled = ''
		}, 3000)
	}

	// 具体点击按钮
	var updataButton = function(element) {
		var intent = document.querySelector(element)
		intent.addEventListener('click', function() {
			updataImags(element)
		})
	}

var __main = function() {
  initData()
  numberSearch(".tab-data")
  envs('#save-data')
  uploadCertificate(".tab-data")
  Hang_cirulation('.tab-data')
}

__main()
