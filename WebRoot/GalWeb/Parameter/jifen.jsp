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
<title>积分获取管理</title>
</head>
<body>
<div class="pd-20">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a href="jifenAdd.html" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加积分获取方式 </a></span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			
			<tr class="text-c">
			    <th width="90">获取方式</th>
				<th width="150">兑换规则和积分数额</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
				<tr class="text-c">
				<td>转发分享小程序</td>
				<td>50</td>
				<td class="td-status"><a title="编辑" href="jifenAdd.html"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
			<tr class="text-c">
				
				<td>到店花费</td>
				<td>每一元可以获得10积分</td>
				<td class="td-status">  <a title="编辑" href="jifenAdd.html"class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="admin_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr>
		</tbody>
	</table>
</div>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>  
<script type="text/javascript" src="../lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="../lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="../js/H-ui.js"></script> 
<script type="text/javascript" src="../js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}

</script>
</body>
</html>