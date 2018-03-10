<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>队列异常列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
	
	   
		var datagrid;
		$(function(){
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/risk/mqExceptionList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				singleSelect : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[
				  {
					field : 'queue',
					title : '队列名',
					width : 300
				},{
					field : 'message',
					title : '消息',
					width : 600,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'create_time',
					title : '创建时间',
					width : 250
				}]]
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
			location.href="${app}/app/systemAdmin/mqException_list.jsp";
		}
		
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  <div region="north" border="false"  overflow:hidden;background-color: #F4F4F4">
  <form id="searchForm">
		  		<table border="0" class="searchForm datagrid-toolbar" cellpadding="0" cellspacing="0">
		  			<tr>
						<td class="tdR" width="5%">队列名称:</td>
						<td width="10%">
							<input id="queue" name="queue" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
						<td width="3%" class="tdR">时间:</td>
					<td>
						<input id="start_time" name="start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						至
						<input id="end_time" name="end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					<td>
						<a  class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a  class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun();">清空</a>
			 		</td>
					</tr>
			</table>
		</form>
	</div>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
