<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>组织机构名称对应列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	   
	<script type="text/javascript">
		var datagrid;
		$(function(){
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/stratage/structMapping',
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
								field:'rowNumbers',  
							    title: '序号',  
							    align: 'center',  
							    formatter: function(val,rec,index){  
									var op = $('#datagrid').datagrid('options');  
								    return op.pageSize * (op.pageNumber - 1) + (index + 1);  
								},
								width : 60
							},
				  {
					field : 'org_no',
					title : '机构编号',
					width : 200
				},{
					field : 'org_name',
					title : '机构名称',
					width : 300
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
