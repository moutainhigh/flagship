<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>APP广播</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		$(document).ready(function(){
			 
		}); 
		 
		 
		
		//提交
		function submitForm(){
			var addForm = $("#addForm");
			addForm.form('submit',{
				url:'${app}/app/doBroadCast',
				onSubmit:function(){
					if(addForm.form("validate")){
						if($("input[name=title]").val() != "" && $("input[name=content]").val() != ""){
							openMask();
							return true;
						}else {
							$.messager.alert('提示信息','请填写完整的表单','info');
							return false;
						}
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/app/toAppUserList?messageCode=" + obj.messageCode,"客户端用户管理");
					parent.closeTab("APP广播");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.closeTab("APP广播");
		}
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="addForm" class="easyui-form" method="post"  >
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>标题:</td>
				<td >
					<input type="text" id="title" name="title" class="easyui-textbox" data-options="required:true,validType:['length[0,10]']" style="width: 175px;height: 24px;"/>
				</td>
				
			</tr>
			<tr>
			
			<td width="15%" class="tdR"><span style="color: red">*</span>内容:</td>
				<td  >
					<input type="text" id="conent" name="content" class="easyui-textbox" data-options="multiline:true,required:true" style="width: 175px;height: 50px;"/>
				</td> 
				
			</tr>
			 
			 
			<tr>
				<td colspan="2" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table> 
	</form>
  </body>
</html>
