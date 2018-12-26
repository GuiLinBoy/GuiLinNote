<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
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
		<link href="../lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
		<link href="../lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
		<link href="../lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>详细资料</title>
	</head>

	<body>
		<div class="pd-20" style="">
			<form action="" method="post" class="form form-horizontal" id="form-article-add" style="">
				<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">左眼</span></div>
		<div class="mt-20">
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>球镜:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="" style="text-align: center;">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>柱镜:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>轴位:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>矫正视力:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div style="margin:auto;"class="col-12">
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">左眼</span></div>
		</div>
		
		<div class="mt-20">
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>球镜:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>柱镜:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>轴位:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>矫正视力:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
		</div>
		<div style="margin:auto;"class="col-12">
			<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">其他</span></div>
		</div>
		
		<div class="mt-20">
			<label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>瞳距:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-2" style="margin-top: 20px;">
				<input type="text" class="input-text" value="" placeholder="" id="" name="">
			</div>
			<div class="mt-20">
		    <label class="form-label col-3" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>日期：</label>
		    <div class="formControls col-2" style="margin-top: 20px;">
			<input class="input-text Wdate" type="text" id="login" onclick="WdatePicker({isShowClear:false,readOnly:true,lang:'zh-cn'})" style="width:120px;" />
			</div>
		</div>
		<!--图片上传-->
		<div  class="mt-20 col-12">
		<label class="form-label col-2" style="text-align: right;margin-top: 20px;"><span class="c-red">*</span>上传诊断图片:&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<div class="formControls col-10">
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<div id="filePicker-2" class="webuploader-container"><div class="webuploader-pick">点击选择图片</div><div id="rt_rt_1cv01pg5ar6luscko4g0t1fbj6" style="position: absolute; top: 20px; left: 0px; width: 168px; height: 44px; overflow: hidden; bottom: auto; right: auto;"><input type="file" name="file" class="webuploader-element-invisible" multiple="multiple"><label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label></div></div>
							<p>或将照片拖到这里，单次最多可选300张</p>
						</div>
					<ul class="filelist"></ul></div>
					<div class="statusBar" style="display:none;">
						<div class="progress" style="display: none;"> <span class="text">0%</span> <span class="percentage" style="width: 0%;"></span> </div>
						<div class="info">共0张（0B），已上传0张</div>
						<div class="btns">
							<div id="filePicker2" class="webuploader-container"><div class="webuploader-pick">继续添加</div><div id="rt_rt_1cv01pg5c1gu21d3c194lsa11opf9" style="position: absolute; top: 0px; left: 0px; width: 38px; height: 2px; overflow: hidden; bottom: auto; right: auto;"><input type="file" name="file" class="webuploader-element-invisible" multiple="multiple"><label style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label></div></div>
							<div class="uploadBtn state-pedding">开始上传</div>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="row cl" style="padding-top: 20px;">
					<div class="col-9 col-offset-2">
						<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;完成预约并提交&nbsp;&nbsp;">
						<a href="javascript:history.go(-1);"><input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;取消&nbsp;&nbsp;"></a>
					</div>
				</div>
		</form>
		</div>
		<script type="text/javascript" src="../lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="../lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="../lib/icheck/jquery.icheck.min.js"></script>
		<script type="text/javascript" src="../lib/Validform/5.3.2/Validform.min.js"></script>
		<script type="text/javascript" src="../lib/webuploader/0.1.5/webuploader.min.js"></script>
		<script type="text/javascript" src="../lib/ueditor/1.4.3/ueditor.config.js"></script>
		<script type="text/javascript" src="../lib/ueditor/1.4.3/ueditor.all.min.js">
		</script>
		<script type="text/javascript" src="../lib/My97DatePicker/WdatePicker.js"></script> 
		<script type="text/javascript" src="../lib/My97DatePicker/lang/zh-cn.js"></script> 
		<script type="text/javascript" src="../lib/My97DatePicker/lang/zh-tw.js"></script> 
		<script type="text/javascript" src="../lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
		<script type="text/javascript" src="../js/H-ui.js"></script>
		<script type="text/javascript" src="../js/H-ui.admin.js"></script>
		<script type="text/javascript">
			$(function() {
						/*用户-删除*/
						function member_del(obj, id) {
							layer.confirm('确认要删除吗？', function(index) {
								$(obj).parents("tr").remove();
								layer.msg('已删除!', {
									icon: 1,
									time: 1000
								});
							});
						}
		</script>
	</body>
</html>