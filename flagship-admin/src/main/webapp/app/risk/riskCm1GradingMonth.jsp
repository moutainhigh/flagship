<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>C-M1月总计数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var rank_list=${rankList};
		var type_list=${typeList};
		var month_list=${monthList};
		var datagrid;
		$(function(){
			/* alert(rank_list); */
			$("#month").combobox({    
			    valueField:'recordDate', 
			    textField:'dateString',
			    data:month_list
			});  
			
			$("#rank").combobox({    
			    valueField:'rank', 
			    textField:'rankDesc',
			    data:rank_list
			});  
			
			$("#type").combobox({    
			    valueField:'type',    
			    textField:'typeDesc',
			    data:type_list
			}); 
			
			datagrid = $('#datagrid').datagrid({    
			    url:'${app}/risk/getCm1GradingMonthList',    
			    pagination : true,
				singleSelect : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
			    columns:[[    
			        {
					field : 'org_name',
					title : '机构',
					width : 100
					
				},
				{
					field : 'type',
					title : '类型',
					width : 100
					
				},
				{
					field : 'batch_30',
					title : '30',
					width : 100
					
				},
				{
					field : 'batch_10',
					title : '10',
					width : 100
					
				},
				{
					field : 'batch_15',
					title : '15',
					width : 100
					
				},
				{
					field : 'batch_20',
					title : '20',
					width : 100
					
				},
				{
					field : 's',
					title : '散标',
					width : 100
					
				},
				{
					field : 'date',
					title : '月份',
					width : 100
					
				},
				{
					field : 'cm1_value',
					title : 'C-M1回款率',
					width : 100
					
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
	</script>
  </head>
  
   <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:35px; overflow:hidden;">
  <form id="searchForm">
 		<table border="0" class="searchForm datagrid-toolbar" width="100%" height="100%">
 			<tr>
				<td  class="tdR" width="80px">月份:</td>
			<td class="tdR" width="100px" >
				<input id="month" name="recordDate" style="width: auto;"  />
				
			</td>
			
			<td  class="tdR" width="80px">组织级别:</td>
			<td class="tdR" width="100px" >
				<input id="rank" name="rank" style="width: auto;"  />
				
			</td>
			<td  class="tdR" width="80px">统计周期:</td>
			<td class="tdR" width="100px">
				<input id="type" name="type" style="width: auto;"  />
			</td>
			<td class="tdR" width="100px">
				<input id="orgName" name="orgName" class="easyui-textbox" data-options="prompt:'组织名称'" style="width: auto;"  />
			</td>
			<td>
				<a  style="margin-left: 30px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
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
