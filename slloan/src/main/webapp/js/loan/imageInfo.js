console.log('影像信息')


layui.use('upload', function(){
	console.log('上传照片')
	  var upload = layui.upload;
	   
	  //执行实例
	  var uploadInst = upload.render({
	    elem: '#test1' //绑定元素
	    ,url: '/upload/' //上传接口
	    ,done: function(res){
	      //上传完毕回调
	    }
	    ,error: function(){
	      //请求异常回调
	    }
	  });
});