<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>修改系统配置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
		//提交
		function submitForm(){
			var empEditForm = $("#propertyEditForm");
			empEditForm.form('submit',{
				
				url:'${app}/finance/doEditProperty',
				onSubmit:function(){
					if(empEditForm.form("validate")){
						openMask();
						return true;
					}else{
						return false;
					}
				},
				success:function(data){
					parent.refreshTab("${app}/finance/toPropertyConfigList");
				}
			});
		}
		
		//取消
		function resetForm(){
			parent.refreshTab("${app}/finance/toPropertyConfigList");
		}
		
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="propertyEditForm" class="easyui-form" method="post" modelAttribute="employee">
  		<input type="hidden" id="id" name="id" value="${properties.id}"/>
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>属性名称:</td>
				<td width="35%">
					<input readonly="readonly" type="text" id="propertyName" name="propertyName" value="${properties.propertyName}" class="easyui-textbox"  style="width: 175px;height: 24px;"/>
				</td>
				<td width="15%" class="tdR"><span style="color: red">*</span>属性值(Stirng):</td>
				<td width="35%">
					<input type="text" id="propertyStringValue" name="propertyStringValue" value="${properties.propertyStringValue}" class="easyui-textbox"  style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>属性值(数字):</td>
				<td>
					<input type="text" id="propertyFigureValue" name="propertyFigureValue" value="${properties.propertyFigureValue}" class="easyui-textbox"  style="width: 175px;height: 24px;"/>
				</td>
				<td class="tdR"><span style="color: red">*</span>属性描述:</td>
				<td>
					<input readonly="readonly" type="text" id="propertyDesc" name="propertyDesc" value="${properties.propertyDesc}" class='easyui-textbox'  style="width: 175px;height: 24px;"/>
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
