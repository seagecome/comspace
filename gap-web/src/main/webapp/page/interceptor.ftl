<html>
<head>
<title>注册</title>
<script src="https://static.zhong.com/pc/skin/js/jquery-1.11.2.js?20171030"></script>
</head>
<body>
<form action="${config.domainHost}/wrapper/fromRequest.do" method="post">
新增一个用户<br />
并把从库登录日志显示出来<br />

userId:<input type="text" name="userId" id="userId"/>
userName:<input type="text" name="userName" id="userName"/>

<input type="submit"/>
</form>

<script>
function aa(){
	var url="http://127.0.0.1:8080/gap-web/wrapper/fromRequest.do";
	var data={
		"userId":"wolaile",
		"userName":"Tom"
	}
	$.ajax({
		url:url,
		type:"post",
		datatype:"json",
		data:data,
		success:function(respone){
			alert('ok');
		}
	})
}
</script>

</body>
</html>