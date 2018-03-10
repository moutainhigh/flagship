<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>融资营业部对应列表</title>
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
	          url:'${app}/finance/orgMap/doUpload',
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
				url : '${app}/finance/orgMap/pageList',
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
					field : 'bi_org_name',
					title : '营业部',
					width : 170,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'org_short_name',
					title : '营业部简称',
					width : 170
				},{
					field : 'principal',
					title : '负责人',
					width : 100
				},{
					field : 'manage_office',
					title : '管办',
					width : 120
				},{
					field : 'province',
					title : '省份',
					width : 160,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'opening_date',
					title : '开业时间',
					width : 120
				},{
					field : 'abscissa',
					title : '经度',
					width : 130
				},{
					field : 'ordinate',
					title : '纬度',
					width : 100
				}
				]]
			});
		});
		
		
	</script>
  </head>
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:50px;  overflow:hidden;background-color: #F4F4F4">
	 <form id="form"  enctype="multipart/form-data" style="margin-top: 10px;">
	 <table>
	 	<tr>
	 		<td>
				<input type="file" name="file" id="fileName" >
	 		</td>
	 		<td>
			    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="upload()">上传</a>
			    <a class="easyui-linkbutton" iconCls="icon-download" onclick="downLoad()">模板下载</a>
	 		</td>
	 	</tr>
	 </table>
	 <!-- 模板下载名称 -->
  	<input type="hidden" id="path" name="ruleName" value="融资营业部对应表.xlsx" >
	</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
