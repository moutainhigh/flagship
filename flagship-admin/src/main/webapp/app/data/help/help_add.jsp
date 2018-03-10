<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>添加帮助问题页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
	//提交
	function submitForm() {
		var base = new Base64();
		var sortId = $('#type').combobox('getValue');
		var question =$("#question").val();
		var answer = base.encode($("#text").val());
		if (sortId == "" || typeof (sortId) == "undefined") {
			$.messager.alert('提示信息', '请选择分类', 'info');
			return false;
		}
		if(question.length >'100'){
			$.messager.alert('提示信息', '内容100以内', 'info');
			return false;
		}
		$.ajax({
			type: "post",
             url: "${app}/help/doAdd",
             data: {"sortId":sortId,"question":question,"answer":answer},
             dataType: "json",
             success: function(data){
	           parent.createTab('${app}/app/data/help/help_list.jsp','帮助');
			   parent.closeTab("添加帮助问题");
             }
		})
	}
	//取消
	function resetForm() {
		parent.closeTab("添加帮助问题");
	}	
	</script>
	
	<script language="javascript" type="text/javascript">
       KindEditor.ready(function(K) {
        var  editor1 = K.create('textarea[name="pictureFile"]', {
            resizeType : 1, //这里的name属性值和下面的对应，你改成你项目用的name属性值
                    allowPreviewEmoticons : false,
                    allowImageUpload : true, //打开本地上传图片功能
                    uploadJson:'${app}/image/imageUpload',
                    filePostName:'pictFile',
                    urlType:'absolute',
                    items : [
                          'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
       'table', 'hr','image'],
            afterBlur : function() {   
                  this.sync();  //焦点问题，这里不写会出问题.同步KindEditor的值到textarea文本框      
            }
                });                     
            });
     
       </script>
  </head>
  
  <body style="background: white;">
  	<form id="addForm" class="easyui-form" method="post" modelAttribute="appuser">
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>选择分类:</td>
				<td>
					<input id="type" style="width: 200px;"   class="easyui-combobox"  name="sortId"
					data-options="
							url:'${app }/help/getAllSorts',
							method:'get',
							valueField:'id',
							textField:'name',
							multiple: false ,
							panelHeight:'auto'
					">
				</td>
				</tr>
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>问题描述:</td>
				<td>
					<textarea id="question"  style="width:645px;height:50px;" name="question"></textarea>
				</td>
			</tr> 
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>问题解答:</td>
				<td>
					<textarea id="text" style="width:650px;height:360px;" name="pictureFile"></textarea> 
				</td>
			</tr> 
			<tr>
				<td colspan="4" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table>
		<div id="deptDialog"></div>
	</form>
  </body>
</html>

