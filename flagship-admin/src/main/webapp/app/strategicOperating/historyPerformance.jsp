<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>历史业绩查看</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){ 
			/* 获取部门名称下拉框 */
			getOrgName();
			datagrid = $('#datagrid').datagrid({
				url : '${app}/stratage/historyPerformanceList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				singleSelect : true,
				toolbar:"#toolbar",
				border : false,  
				nowrap : false,  
				idField : 'orgNo',
				 frozenColumns: [[    
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
										field : 'recordDate',
										title : '日期',
										align: 'center',
										width : 100
									},{
										field : 'orgName',
										title : '部门',
										align: 'center',
										width : 120,
										formatter: function(value,row,index){
											if(null == value){
												 return "";
											}
					                         return "<span title='"+ value +"'>"+value+"</span>";
					                 }
									}
				                  ]],  
					 columns: [  
					            [
						             {"title":"信贷业务","colspan":4},  
						             {"title":"借款人意外险","colspan":4},
						             {"title":"车险（台）","colspan":4},  
						             {"title":"车险（保费）","colspan":4},
						             {"title":"寿险","colspan":4}
					             ],  
					            [
						             {"field":"creditPlan","align":"center","title":"月度目标（元）","rowspan":1},  
						             {"field":"creditDailyVal","align":"center","title":"单日业绩（元）","rowspan":1},  
						             {"field":"creditTotalVal","align":"center","title":"累计业绩（元）","rowspan":1},  
						             {"field":"creditRate","align":"center","title":"达成率","rowspan":1},
						             
						             {"field":"accidentPlan","align":"center","title":"月度目标（元）","rowspan":1},  
						             {"field":"accidentDailyVal","align":"center","title":"单日业绩（元）","rowspan":1},  
						             {"field":"accidentTotalVal","align":"center","title":"累计业绩（元）","rowspan":1},  
						             {"field":"accidentRate","align":"center","title":"达成率","rowspan":1},
						            
						             {"field":"carInsurancePlan","align":"center","title":"月度目标（台）","rowspan":1},  
						             {"field":"carInsuranceDailyVal","align":"center","title":"单日业绩（台）","rowspan":1},  
						             {"field":"carInsuranceTotalVal","align":"center","title":"累计业绩（元台）","rowspan":1},  
						             {"field":"carInsuranceRate","align":"center","title":"达成率","rowspan":1},
						             
						             {"field":"carInsuranceNumPlan","align":"center","title":"月度目标（元）","rowspan":1},  
						             {"field":"carInsuranceNumDailyVal","align":"center","title":"单日业绩（元）","rowspan":1},  
						             {"field":"carInsuranceNumTotalVal","align":"center","title":"累计业绩（元）","rowspan":1},  
						             {"field":"carInsuranceNumRate","align":"center","title":"达成率","rowspan":1}  ,
						             
						             {"field":"lifePlan","align":"center","title":"月度目标（元）","rowspan":1},  
						             {"field":"lifeDailyVal","align":"center","title":"单日业绩（元）","rowspan":1},  
						             {"field":"lifeTotalVal","align":"center","title":"累计业绩（元）","rowspan":1},  
						             {"field":"lifeRate","align":"center","title":"达成率","rowspan":1}  
					     ]
					]
			});
		});
		
		function getOrgName() {
			$.ajax({ 
				url: '${app }/stratage/getBusinessNameWithResultsView',
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
		//清空
		function clearFromFun(datagrid){
			clearForm(datagrid); 
			$('#years').combobox('reload');      
			$('#months').combobox('reload');      
			$('#days').combobox('reload');      
			getOrgName();     
		}
		//历史业绩查看：Excepl导出
		function doExport(){
			$("#state").val(2);//标识导出还是列表
			$("#searchForm").attr("action", "${app}/stratage/exportThisMonthView");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
	</script>
	
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  		<form id="searchForm">
  				 		<input type="hidden" id="state" name="state"><!-- 导出标识 -->
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
	  			<td width="10px"></td>
					<td class="tdR" width="3%">年份:</td>
					<td width="5px"></td>
					<td  class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
							id="years" name="years"
						data-options="
								url:'${app }/stratage/getYears',
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
					            var url = '${app }/stratage/getMonths?years='+rec.years;    
					            $('#months').combobox('reload', url);   
				                var months = $('#months').combobox('getValue');
				                months = months.split('月');
					            var url = '${app }/stratage/getDays?months='+months[0]+'&years='+rec.years;
					            $('#days').combobox('reload', url);  
					        }" >
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
				                },
				                onSelect: function(rec){    
				                var months = rec.months.split('月');
				                var years = $('#years').combobox('getValue');
					            var url = '${app }/stratage/getDays?months='+months[0]+'&years='+years;
					            $('#days').combobox('reload', url); 
						} ">
					</td>
					<td width="20px"></td>
					<td class="tdR" width="3%">日:</td>
					<td width="5px"></td>
					<td  class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
							id="days" name="days"
						data-options="
								valueField:'days',
								textField:'days',
								multiple: false ,
								panelHeight:'auto',
								onLoadSuccess:function(){  
								  var data = $('#days').combobox('getData');
				                    if(data.length != 0){  
				                        $('#days').combobox('select',data[0].days);
				                    }  
				                }">
				                
				              
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
						<sec:authorize ifAnyGranted='${ctrl.historyPerformanceExport}'>
							<a  style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-redo" onclick="doExport()">导出</a>
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