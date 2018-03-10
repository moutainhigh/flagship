<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>本月业绩查看列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){
			 $('#start_time').datebox().datebox('calendar').calendar({ 
				   validator: function(date){ 
					   //获得当前日期的 t-1天
					    var today=new Date();
						var t=today.getTime()-1000*60*60*24;
						var endDate =new Date(t);
					   //得到指定月份的第一天
					    var startDate=new Date(endDate);
     					 startDate.setDate(1);
     					 startDate.setHours(0);
     					 startDate.setMinutes(0);
     					 startDate.setSeconds(0);
     					 startDate.setMilliseconds(0);
					    return date >= startDate && date <= endDate;
				   } 
				  });  
			 
			  $('#end_time').datebox().datebox('calendar').calendar({ 
				   validator: function(date){ 
					   //获得当前日期的 t-1天
					    var today=new Date();
						var t=today.getTime()-1000*60*60*24;
						var endDate =new Date(t);
					   //得到指定月份的第一天
					    var startDate=new Date(endDate);
    					 startDate.setDate(1);
    					 startDate.setHours(0);
    					 startDate.setMinutes(0);
    					 startDate.setSeconds(0);
    					 startDate.setMilliseconds(0);
					    return date >= startDate && date <= endDate;
				   } 
				  });   
		//页面加载  
			var curr_time = new Date();     
			$("#start_time").datebox("setValue",myformatter(curr_time));  
			$("#end_time").datebox("setValue",myformatter(curr_time)); 
			
			/* 获取部门名称下拉框 */
			getOrgName();
			datagrid = $('#datagrid').datagrid({
				url : '${app}/stratage/thisMonthView',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				singleSelect : true,
				toolbar:"#toolbar",
				border : 2,  
				nowrap : false,  
				idField : 'id',
			    frozenColumns: [[    
								{
									field:'rowNumbers',  
								    title: 'NO',  
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
					             {"field":"creditPlan","title":"月度目标（元）","rowspan":1},  
					             {"field":"creditDailyVal","title":"单日业绩（元）","rowspan":1},  
					             {"field":"creditTotalVal","title":"累计业绩（元）","rowspan":1},  
					             {"field":"creditRate","title":"达成率","rowspan":1},
					             
					             {"field":"accidentPlan","title":"月度目标（元）","rowspan":1},  
					             {"field":"accidentDailyVal","title":"单日业绩（元）","rowspan":1},  
					             {"field":"accidentTotalVal","title":"累计业绩（元）","rowspan":1},  
					             {"field":"accidentRate","title":"达成率","rowspan":1},
					            
					             {"field":"carInsurancePlan","title":"月度目标（台）","rowspan":1},  
					             {"field":"carInsuranceDailyVal","title":"单日业绩（台）","rowspan":1},  
					             {"field":"carInsuranceTotalVal","title":"累计业绩（台）","rowspan":1},  
					             {"field":"carInsuranceRate","title":"达成率","rowspan":1},
					             
					             {"field":"carInsuranceNumPlan","title":"月度目标（元）","rowspan":1},  
					             {"field":"carInsuranceNumDailyVal","title":"单日业绩（元）","rowspan":1},  
					             {"field":"carInsuranceNumTotalVal","title":"累计业绩（元）","rowspan":1},  
					             {"field":"carInsuranceNumRate","title":"达成率","rowspan":1}  ,
					             
					             {"field":"lifePlan","title":"月度目标（元）","rowspan":1},  
					             {"field":"lifeDailyVal","title":"单日业绩（元）","rowspan":1},  
					             {"field":"lifeTotalVal","title":"累计业绩（元）","rowspan":1},  
					             {"field":"lifeRate","title":"达成率","rowspan":1}  
				     ]
				]
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
			location.href="${app}/app/strategicOperating/thisMonthView_list.jsp";
		}
		/* 获取部门名称 */
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
		function myformatter(date){  
		    var y = date.getFullYear();  
		    var m = date.getMonth()+1;  
		    var d = date.getDate()-1;  
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
		}  
		
		//本月业绩查看：Excepl导出
		function doExport(){
			$("#state").val(1);//标识导出还是列表
			$("#searchForm").attr("action", "${app}/stratage/exportThisMonthView");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
	</script>
  </head>
  
   <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  <form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
		 		<input type="hidden" id="state" name="state"><!-- 导出标识 -->
 		  			<tr>
 		  				<td width="10px"></td>
						<td  class="tdR" width="3%">部门:</td>
						<td width="5px"></td>
						<td class="tdR" width="100px" >
							<input id="orgNo" style="width: auto;"  value="0"
							class="easyui-combobox"  name="orgNo">
						</td>
						<td width="20px"></td>
						<td width="3%" class="tdR">日期:</td>
						<td width="5px"></td>
					<td>
						<input id="start_time" name="start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						&nbsp;至&nbsp;
						<input id="end_time" name="end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
						<a  style="margin-left: 30px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a   style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun();">重置</a>
						<sec:authorize ifAnyGranted='${ctrl.thisMonthViewExport}'>
							<a  style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-redo"  onclick="doExport()">导出</a>
						</sec:authorize>
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
