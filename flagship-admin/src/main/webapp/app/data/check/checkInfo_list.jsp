<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户端用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){ 
			datagrid = $('#datagrid').datagrid({
				url : '${app}/checkData/checkInfoList',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[{
					field : 'date',
					title : '日期',
					align: 'center',
					width: 80
				},{
					field : 'type',
					title : '访问类型',
					align: 'center',
					width: 80
				},{
					field : 'sort',
					title : '次数',
					align: 'center',
					sortable:true,
					width: 50
				},{
					field : 'name',
					title : '接口名',
					align: 'left',
					width: 220
				},{
					field : 'result',
					title : '状态',
					align: 'center',
					width: 50
				},{
					field : 'detail',
					title : '详情',
					align: 'left',
					width: 510,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                      			   return "<span title='"+ value +"'>"+value+"</span>";
           				      }
				} ]]
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
			clearForm(datagrid); 
		}
		/* 开始时间变化事件 */
		function onChangeBeginDate(date){  
			var beginDate =  $('#beginDate').datebox('getValue');
			var endDate =  $('#endDate').datebox('getValue');
			$.post('${app }/checkData/getSort',{
				'beginDate' : beginDate,
				'endDate' : endDate
			},function(data){
				var data = $.parseJSON(data);
			    $('#times').combobox('clear');
			    $('#times').combobox('loadData', data);
			}); 
		} 
		/* 结束时间变化事件  */
		function onChangeEndDate(date){  
			var beginDate =  $('#beginDate').datebox('getValue');
			var endDate =  $('#endDate').datebox('getValue');
			$.post('${app }/checkData/getSort',{
				'beginDate' : beginDate,
				'endDate' : endDate
			},function(data){
				var data = $.parseJSON(data);
			    $('#times').combobox('clear');
			    $('#times').combobox('loadData', data);
			}); 
		} 
		//导出Excel
		function doExport(){
			$("#searchForm").attr("action", "${app}/checkData/exportExcel");
			$("#searchForm").attr("method", "POST");
			$("#searchForm").submit();
		}
		
		//弹出加载层
		function load() {  
				    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
				    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
			}
				//取消加载层  
		function disLoad() {  
				    $(".datagrid-mask").remove();  
				    $(".datagrid-mask-msg").remove();  
				    
				    
		 }
		/* 检查数据 */	
		 function checkData(){
						load();//弹出遮罩层
						 $.ajax({
							url : '${app}/checkData/checkDate',
							type : "post",
							dataType : "json",
							success : function(jsonMap) {
								disLoad(); // 关闭遮罩层
								closeMask();
								parent.refreshTab("${app}/checkData/toCheckDataInfoList?messageCode="
										+ jsonMap.messageCode, "数据校验");
							}
						}) 
					}
		
	</script>
	
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:30px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="35px">日期:</td>
					<td width="50px">
						<input id="beginDate" name="beginDate"  type="text" class="easyui-datebox" 
						style="width: 130px;height: 24px;"  data-options="onSelect:onChangeBeginDate"/>  
					</td>
					<td class="tdR" width="5px">至</td>
					<td width="50px">
						<input id="endDate" name="endDate"  type="text" class="easyui-datebox"
						 style="width: 130px;height: 24px;"  data-options="onSelect:onChangeEndDate"/>  
					</td>
					<td class="tdR" width="65px">访问类型:</td>
					<td  class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
							id="type" name="type"
						data-options="
								url:'${app }/checkData/getTypes',
								method:'get',
								valueField:'type',
								textField:'type',
								multiple: false ,
								panelHeight:'auto'
						">
					</td>
					<td class="tdR" width="55px">结果:</td>
					<td class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
							id="result" name="result"
						data-options="
								url:'${app }/checkData/getResult',
								method:'get',
								valueField:'result',
								textField:'result',
								multiple: false ,
								panelHeight:'auto'
						">
					</td>
					<td class="tdR" width="55px">次数:</td>
					<td class="tdR" width="100px" >
						<input style="width: 100px;" class="easyui-combobox" 
						id="times"	name="times"
						data-options="
								url:'${app }/checkData/getSort',
								method:'get',
								valueField:'times',
								textField:'times',
								multiple: false ,
								panelHeight:'auto'
						">
					</td>
					<td >
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
						<a class="easyui-linkbutton" iconCls="icon-redo" onclick="doExport();">导出</a>
						<a class="easyui-linkbutton" iconCls="icon-right_a" onclick="checkData();">检查数据</a>
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