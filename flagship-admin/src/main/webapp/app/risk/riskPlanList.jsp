<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<!--  弹窗title居中  -->
  	<style>
       .panel-title {
          text-align: center;
          font-size: 14px;
      }
	</style>
    <title>风控目标</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
	
	//下载最新模板
	function downLoad(){
		   location.href="${app}/risk/riskPlanDownLoad";
	   };
	
	// 上传风控目标(初始化月目标)
	 function upload() {
		  var multipart = $("#fileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
	      $("#form").ajaxSubmit({
	          type : 'POST',
	          dataType:"json",
	          url:'${app}/risk/riskPlanUpLoad',
	          success : function(data) {
	           var count = data.count;
	           var error = data.error;
	          	if(1 == data.status ){
	          		$.messager.show({
	                    title:'信息提示',
	                    msg:error,
	                    timeout:5000,
	                    showType:'slide'
	                });
	          	}else if(2 == data.status ){
	          		$.messager.show({
	                    title:'信息提示',
	                    msg:"请下载最新的模板",
	                    timeout:5000,
	                    showType:'slide'
	                });
	          	}else{
	          		$.messager.show({
	                    title:'信息提示',
	                    msg:"成功上传"+count+"条数据",
	                    timeout:3000,
	                    showType:'slide'
	                });
	          		setTimeout(function(){
	          			window.location.reload();
	                 },1500);
	          	}
	          }
	      });
	  };
	  
		// 按月下载模板
	  function downLoadAgain(){
		   var date = $("#dowloadAgain").val();
		   location.href="${app}/risk/riskPlanDownLoad?date="+date;
	   };
		// 再次上传风控目标(重新校对)
		 function uploadAgain() {
			  var multipart = $("#fileNameAgain").val();
				if(multipart==""||multipart==null){
					alert("请先选择文件!");
					return ;
				}
				
		      $("#formAgain").ajaxSubmit({
		          type : 'POST',
		          dataType:"json",
		          data:{
						'date':$("#again").val(),
					},
		          url:'${app}/risk/riskPlanUpLoadByRecordDate',
		          success : function(data) {
		           var count = data.count;
		           var error = data.error;
		          	if(1 == data.status ){
		          		$.messager.show({
		                    title:'信息提示',
		                    msg:error,
		                    timeout:5000,
		                    showType:'slide'
		                });
		          	}else if(2==data.status){
		          		$.messager.show({
		                    title:'信息提示',
		                    msg:"上传信息不符！请检查！",
		                    timeout:5000,
		                    showType:'slide'
		                });
		          	}else{
		          		$.messager.show({
		                    title:'信息提示',
		                    msg:"成功上传"+count+"条数据",
		                    timeout:3000,
		                    showType:'slide'
		                });
		          		setTimeout(function(){
		          			window.location.reload();
		                 },1500);
		          	}
		          }
		      });
		  };
	   
	  /* 查看 */
	  function look(rowIndex){
		  var rows = $('#datagrid').datagrid('getRows');
		  var row = rows[rowIndex]; 
		  var monthDate = row.monthDate;
		  var dateParam = monthDate.replace('年','-');
		  dateParam = dateParam.replace('月','-01');
		  $("#again").val(dateParam);
		  $("#dowloadAgain").val(dateParam);
		  $('#win').window({    
			    width:'600px',    
			    height:'400px',    
			    modal:true,
			    collapsible:false,
			    minimizable:false,
			    maximizable:false,
			    closable:true,
			    minimizable:false,
			    draggable:false,
			    resizable:false,
			});
		    $("#win").panel({title:'查看风控目标'});
		    $("#riskPlanTitle").text(monthDate + "风控目标");  
		    var datagrid_look;
		    datagrid_look = $('#datagrid_look').datagrid({
				url : '${app}/risk/getRiskPlanListByRecordDate',
				title : '',
				pagination : false,
				fit : true,
				singleSelect : true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[
				  {
					field : 'orgName',
					title : '机构名称',
					width : 128,
				},{
					field : 'parentOrgName',
					title : '上级名称',
					width : 128,
				},{
					field : 'cm1Rate',
					title : 'C-M1回款率',
					width : 110,
					formatter: function(value,row,index){
						var rate;
						if(null != value){
							rate = value*100;
							rate = rate.toFixed(2);
							rate = rate + '%';
						}
						return rate;
                 }
				},{
					field : 'm2PressureRate',
					title : 'M2压降率',
					width : 110,
					formatter: function(value,row,index){
						var rate;
						if(null != value){
							rate = value*100;
							rate = rate.toFixed(2);
							rate = rate + '%';
						}
						return rate;
                 }
				},{
					field : 'm3PressureRate',
					title : 'M3压降率',
					width : 108,
					formatter: function(value,row,index){
						var rate;
						if(null != value){
							rate = value*100;
							rate = rate.toFixed(2);
							rate = rate + '%';
						}
						return rate;
                 }
				}]],
				queryParams: {
					monthDate: dateParam
				}
			});
	  }
	   
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/risk/riskPlanList',
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
					field : 'monthDate',
					title : '月份',
					width : 200,
					align:'center'
				},{
					field : 'upDateTime',
					title : '更新日期',
					width : 200,
					align:'center'
				},{
					field : 'content',
					title : '操作',
					width : 200,
					align:'center',
					formatter: function(value,row,index){
						return '<a  onclick="look('+index+')"><font color=blue>查看</font></a>';
                 }
				}
				]]
			});
		});
		
		
	</script>
  </head>
 <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;" >
 	<div id="win"  title="" >
 		<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;" >
	 		<div data-options="region:'north',split:false,collapsible:false" style="width: 100%;height:10%">
	 			<center><a id="riskPlanTitle"  style="text-align:center;font-size:15px;"></a></center>
	 		</div>
	 		<div data-options="region:'center',split:false" style="width: 100%;height:73%">
	 			<table id="datagrid_look" ></table>
	 		</div>
	 		
	 		<div data-options="region:'south',split:false" style="width: 100%;height:17%">
	 		<form id="formAgain"  enctype="multipart/form-data" style="margin-top: 10px;">
	 			<table>
				 	<tr>
				 		<td width="10px"></td>
				 		 <td>
							<input type="file" name="fileAgain" id="fileNameAgain" >
						</td>
						<td>
				 		 	<a  class="easyui-linkbutton" iconCls="icon-download" 
				 		 		 onclick="downLoadAgain()">模板下载</a>
				 		 </td>
				 		<td>
						    <a  style="margin-left: 5px;" id="again"  value="" class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadAgain()">再次上传</a>
				 		</td>
				 	</tr>
				 </table>
	 		</form>
	 		
	 		</div>
 		</div>
  	</div>  
  	<div region="north" border="false" style="height:50px;  overflow:hidden;background-color: #F4F4F4">
	 <form id="form"  enctype="multipart/form-data" style="margin-top: 10px;">
	 <table>
	 	<tr>
	 	<td width="10px"></td>
	 		<td>
				<input type="file" name="file" id="fileName" >
	 		</td>
	 		<td>
			    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="upload()">上传</a>
			    <a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-download" onclick="downLoad()">模板下载</a>
	 		</td>
	 	</tr>
	 </table>
	  <!-- 模板下载名称 -->
  	 <input type="hidden" id="path" name="ruleName" value="">
	</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
  <input type="hidden" id="dowloadAgain" value="">
</html>
