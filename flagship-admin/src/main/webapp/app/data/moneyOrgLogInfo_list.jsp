<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>理财月度累计列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/money/orgLogInfo/pageList',
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
					field : 'bi_org_name',
					title : 'BI机构名称',
					width : 130
				},{
					field : 'roster_org_name',
					title : '花名册机构名称',
					width : 130
				},{
					field : 'type',
					title : '类型',
					width : 130
				},{
					field : 'create_time',
					title : '创建时间',
					width : 160
				}
				]]
			});
		});
		
		
	</script>
  </head>
  
   <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
