<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>月度C-M1查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){ 
			/* 获取部门名称下拉框 */
			getOrgName();
			datagrid = $('#datagrid').datagrid({
				url : '${app}/stratage/monthCM1list',
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'orgNo',
				columns : [[{
					field:'rowNumbers',  
				    title: '序号',  
				    align: 'center',  
				    width: 50, 
				    formatter: function(val,rec,index){  
						var op = $('#datagrid').datagrid('options');  
					    return op.pageSize * (op.pageNumber - 1) + (index + 1);  
					}
				},{
					field : 'recordDate',
					title : '日期',
					align: 'center',
					width: 80
				},{
					field : 'orgName',
					title : '部门',
					align: 'center',
					width: 160
				},{
					field : 'planVal',
					title : '当月目标',
					align: 'center',
					sortable:true,
					width: 80
				},{
					field : 'actualVal',
					title : 'C-M1',
					align: 'center',
					width: 80
				}]]
			});
		});
		/* 获取部门名称 */
		function getOrgName() {
			$.ajax({ 
				url: '${app }/stratage/getBusinessName',
				dataType: 'json', 
				success: function(data){   
					// 修改ajax返回的值
					data.unshift({'orgNo':'0','orgName':'全部'});   //unshift方法添加到第一行，push方法添加到末尾
					$('#orgNo').combobox({            
						data:data,        
						valueField:'orgNo',        
						textField:'orgName', 
						editable:false, //不可编辑
						multiple: false ,
						panelHeight:'auto'
					});    
				}
			});
		}
		
		//搜索
		function searchFun() {
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
        //导出Excel
        function doExport(){
            $("#searchForm").attr("action", "${app}/stratage/monthCM1list/exportExcel");
            $("#searchForm").attr("method", "POST");
            $("#searchForm").submit();
        }
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid); 
			$('#years').combobox('reload');      
			$('#months').combobox('reload');      
			getOrgName();     
		}
		
	</script>
	
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
	  				<td width="10px"></td>
					<td class="tdR" width="3%">年份:</td>
					<td width="5px"></td>
					<td  class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
							id="years" name="years"
						data-options="
								url:'${app }/stratage/getYears?flag=cm1',
								method:'get',
								valueField:'years',
								textField:'years',
								multiple: false ,
								panelHeight:'auto',
								onLoadSuccess:function(){  
								  var data = $('#years').combobox('getData');
				                    if(data.length != 0){  
				                        $('#years').combobox('select',data[0].years);
				                    }  
				                },
				                onSelect: function(rec){    
					            var url = '${app }/stratage/getMonths?flag=cm1&years='+rec.years;    
					            $('#months').combobox('reload', url);    
					        }"  
								
						">
					</td>
					<td width="20px"></td>
					<td class="tdR" width="55px">月份:</td>
					<td width="5px"></td>
					<td class="tdR" width="100px" >
						<input  style="width: 100px;" class="easyui-combobox" 
							id="months" name="months"
						data-options="
								valueField:'months',
								textField:'months',
								multiple: false ,
								panelHeight:'auto',
								onLoadSuccess:function(){  
								  var data = $('#months').combobox('getData');
				                    if(data.length != 0){  
				                        $('#months').combobox('select',data[0].months);
				                    }  
				                }
						"
						>
					</td>
					<td width="20px"></td>
					<td class="tdR" width="55px">部门:</td>
					<td width="5px"></td>
					<td class="tdR" width="100px" >
						<input id="orgNo" style="width: auto;"  value="0"
						class="easyui-combobox"  name="orgNo">
					</td>
					<td >
						<a style="margin-left: 30px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">重置</a>
						<sec:authorize ifAnyGranted='${ctrl.thisMonthImport}'>
							<a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-redo" onclick="doExport();">导出</a>
						</sec:authorize>
					</td>
				</tr>
				 
			</table>
			<br>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	
  </body>
</html>