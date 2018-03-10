<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>邮箱发送详情</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript">
	
		var datagrid;
		$(function(){
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/risk/findEmailList',
				title : '',
				pagination : true,
				singleSelect : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[
				  {
					field : 'org_no',
					title : '组织机构编号',
					width : 260
					
				},{
					field : 'receiver_no',
					title : '收件人员工编号',
					width : 120
				},{
					field : 'receiver_name',
					title : '收件人姓名',
					width : 120
				},{
					field : 'receiver_address',
					title : '收件人邮箱地址',
					width : 200
				},{
					field : 'result',
					title : '发送状态',
					width : 120
				},{
					field : 'create_time',
					title : '发送时间',
					width : 200
				}
				]]
			});
		});
		
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		//清空
		function clearFromFun(datagrid){
			location.href="${app}/app/risk/emailList.jsp";
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  <div region="north" border="false" style="height:40px; overflow:hidden;background-color: #F4F4F4">
		<form id="searchForm">
		  		<table border="0" class="searchForm datagrid-toolbar" cellpadding="0" cellspacing="0">
		  		<tr height="5px"></tr>
		  			<tr>
		  			<td  width="10px"></td>
						<td  class="tdR" width="5%">发送状态:</td>
						<td  width="5px"></td>
						<td width="10%">
						<select class="easyui-combobox" data-options="panelHeight:70" name="result" style="width:100px;">   
						    <option value="">请选择</option>   
						    <option value="1">成功</option>   
						    <option value="0">失败</option>   
						</select>  
						</td>
						<td  class="tdR">发送日期:</td>
						<td  width="5px"></td>
					<td>
						<input id="start_time" name="start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						&nbsp;至&nbsp;
						<input id="end_time" name="end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					<td>
						<a style="margin-left:50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a  style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun();">清空</a>
					</td>
					</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
