<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>理财对比列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript">
	
	 function upload() {
		  var multipart = $("#fileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
	      $("#form").ajaxSubmit({
	          type : 'POST',
	          dataType:"json",
	          url:'${app}/money/compare/doUpload',
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
	  } 

	  function downLoad(){
		   var fileName =  $("#path").val();
      	   var name = encodeURI(encodeURI(fileName));
		   location.href="${app}/money/downLoad?fileName="+name;
	   }
	   
		var datagrid;
		$(function(){
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/money/compare/pageList',
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
					field : 'org_name',
					title : '机构名称',
					width : 80,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'district_name',
					title : '区域名称',
					width : 60
				},{
					field : 'district_principal',
					title : '区域负责人',
					width : 100
				},{
					field : 'month_plan',
					title : '本月计划任务',
					width : 240
				},{
					field : 'counselor_num_last_month',
					title : '上月咨询师人数',
					width : 160
				},{
					field : 'record_date',
					title : '月份',
					width : 120
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
			location.href="${app}/app/data/moneyCompare_list.jsp";
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  <div region="north" border="false" style="height:75px; overflow:hidden;background-color: #F4F4F4">
		<form id="searchForm">
		  		<table border="0" class="searchForm datagrid-toolbar" cellpadding="0" cellspacing="0">
		  			<tr>
						<td  class="tdR" width="5%">机构名称:</td>
						<td width="10%">
							<input id="org_name" name="orgName" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
						<td class="tdR" width="5%">区域名称:</td>
						<td width="10%">
							<input id="district_name" name="districtName" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
						<td width="3%" class="tdR">月份:</td>
					<td>
						<input id="start_time" name="start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						至
						<input id="end_time" name="end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					</tr>
			</table>
		</form>
	 <form id="form"  enctype="multipart/form-data">
	 <table>
	 	<tr>
	 		<td>
				<input type="file" name="file" id="fileName" >
	 		</td>
	 		<td>
			    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="upload()">上传</a>
			    <a class="easyui-linkbutton" iconCls="icon-download" onclick="downLoad()">模板下载</a>
				<a style="margin-left:82px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
				<a  class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun();">清空</a>
	 		</td>
	 	</tr>
	 </table>
			
	    <input type="hidden" id="path" name="ruleName" value="理财对比表.xlsx">
	</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
