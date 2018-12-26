<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
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
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>客户资料</title>
</head>
<body>
<div class="pd-20">
	<div class="text-c"> 
	<form action="ShowCustomer">
		 日期范围：
		<input name="startTime" value="${startTime}" type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}'})" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input name="endTime" value="${endTime}" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d'})" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="userName" value="${userName}" id="" placeholder="顾客姓名" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</form>
	</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20"> <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont"></i> 批量删除</a> <span class="r">共有资料：<strong>200</strong> 条</span> </div>
	<div class="mt-20">
	<s:hidden name="id"></s:hidden>
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="40"><input type="checkbox" name="" value=""></th>
					<th width="80">姓名</th>
					<th width="80">性别</th>
					<th width="80">联系电话</th>
				 	<th width="80">服务项目</th>
				<!--	<th width="80">详细资料</th>
					<th width="80">消费记录</th>
					<th width="80">积分</th>
					<th width="80">服务时间</th> -->
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
			<s:if test="null!=usersList && usersList.size!=0">
			<s:iterator value="usersList" status="td">
				<tr class="text-c">
					<td><input type="checkbox" value="" name=""></td>
					<td>
					<s:property value="userName"/>
					</td>
					<td>
					<s:if test="userSex==0">未知</s:if>
					<s:elseif test="userSex==1">男</s:elseif>
					<s:elseif test="userSex==2">女</s:elseif>
					</td>
					<td>
					<s:property value="userNumber"/>
					</td>
					<td>
					<s:iterator value="itemList">
					<s:property value="itemName"/>
					</s:iterator> 
					</td>
					<td class="f-14 td-manage">
					<a style="text-decoration:none" class="ml-5" href="editNewproject?id=<s:property value="id"/>"  href="javascript:;" title="编辑">
					<em class="Hui-iconfont"></em></a>
					<a style="text-decoration:none" class="ml-5" onClick="canDel('<s:property value="id"/>','<s:property value="userName"/>')" href="javascript:;" title="删除">
					<em class="Hui-iconfont"></em></a>
					</td> 		
				</tr>
			</s:iterator>
			</s:if>
			<s:else>

			<tr class="text-c">
			<td colspan="6">
			暂无数据
			</td>
			</tr>
			</s:else>
			</tbody>
		</table>
	</div>
</div>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="../lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../js/H-ui.js"></script> 
<script type="text/javascript" src="../js/H-ui.admin.js"></script>
<script type="text/javascript">
/*资讯-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-删除*/
function canDel(id){
			if(confirm('是否删除?')){
				window.location='DeleteUser?id='+id;
			}
			
		}
/*资讯-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
	});
}
</script> 
</body>
</html>