<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="../css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="../css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>其他参数</title>
</head>
<body>
<div class="pd-20">

	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="#" onclick="admin_add('权限','AddQuanXian.html','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限</a></span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			
			<tr class="text-c">
			    <th width="90">序号</th>
				<th width="150">权限名称</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
				<tr class="text-c">
				<td>1</td>
				<td>超级管理员</td>
				<td class="td-status"><a title="编辑" href="javascript:;" onclick="admin_edit('权限编辑','AddQuanXian.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
			<tr class="text-c">
				<td>2</td>
				<td>管理员</td>
				<td class="td-status">  <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','AddQuaanXian.html','2','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="pd-20">

	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="javascript:;" onclick="admin_add('添加管理员','AddWorkname.html','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加职位名称</a></span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			
			<tr class="text-c">
			    <th width="90">序号</th>
				<th width="150">职位名称</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
				<tr class="text-c">
				<td>1</td>
				<td>验光师</td>
				<td class="td-status"><a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','AddWorkname.html','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
			<tr class="text-c">
				
				<td>2</td>
				
				<td>验光</td>
				<td class="td-status">  <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','AddWorkname.html','2','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="pd-20">	
</div>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="../lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="../js/H-ui.js"></script> 
<script type="text/javascript" src="../js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
</script>
</body>
</html>