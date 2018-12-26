<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="../css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="../css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="../lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="../lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加用户</title>
</head>
<body>
<div class="pd-20">
  <form action="" method="post" class="form form-horizontal" id="form-member-add">
  <div class="row cl">
      <label class="form-label col-3"><span class="c-red">*</span>选择医生：</label>
      <div class="formControls col-5"> <span class="select-box">
        <select class="select" size="1" name="demo1" datatype="*" nullmsg="请职位名称！">
          <option value="" selected>请选择</option>
          <option value="1">李医生</option>
          <option value="2">赵医生</option>
          <option value="3">大医生</option>
        </select>
        </span> </div>
       </div>
        <div class="row cl" style="margin-top: 20px;">
        <div class="text-c">
        	<label class="form-label col-3"><span class="c-red">*</span>外出日期范围：</label>
        	<div class="col-5">
		<input type="text" onfocus="WdatePicker({minDate:'%y-%M-%d',lang:'zh-cn'})" id="datemin" class="input-text Wdate" placeholder="" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({minDate:'%y-%M-%d',lang:'zh-cn'})" id="datemax" class="input-text Wdate" placeholder="" style="width:120px;">
		</div>
    </div>
    </div>
    <div class="row cl" style="padding-top: 20px;">
					<div class="col-9 col-offset-2"">
						<input class="btn btn-primary radius" style="" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
						
							<input class="btn btn-primary radius" id="" type="button" onclick="tui()" value="&nbsp;&nbsp;取消&nbsp;&nbsp;" >
					</div>
				</div>
  </form>
</div>
</div>
<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="../lib/Validform/5.3.2/Validform.min.js"></script>
<script type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script> 
		<script type="text/javascript" src="../lib/My97DatePicker/lang/zh-cn.js"></script> 
		<script type="text/javascript" src="../lib/My97DatePicker/lang/zh-tw.js"></script> 
<script type="text/javascript" src="../lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="../js/H-ui.js"></script> 
<script type="text/javascript" src="../js/H-ui.admin.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-member-add").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
function tui(){
	window.location.href="../Frist/welcome.html"
}
</script>
</body>
</html>