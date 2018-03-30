//请求的是json，输出的是json 
  function reuqestJson(){ 
	  var jsondatebinding={
			  "username":$("#username").val(),
			  "newpassword":$("#newpassword").val(),
			  "oldpassword":$("#oldpassword").val()
	  }
   $.ajax({ 
    type:'post', 
	url:'/slloan/user/updatepwd',
	data:jsondatebinding,
    success:function(data){//返回json结果 
    	 if(data == true){
    		alert("修改成功")
    	}else {
    		alert("用户名或密码不正确")
    	}
    } 
   },function error(msg){
	   alert(msg)
   }); 
  }