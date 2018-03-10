<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>理财机构名称对应列表</title>
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
	          url:'${app}/money/orgMap/doUpload',
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
	                    timeout:5000,
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
				url : '${app}/money/orgMap/pageList',
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
					title : 'BI',
					width : 150,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'roster_org_name',
					title : '花名册',
					width : 150,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'moneymgr_org_name',
					title : '理财机构名称',
					width : 150
				},{
					field : 'address',
					title : '地址',
					width : 350,
					formatter: function(value,row,index){  
						if(null == value){
							 return "";
						}
                        return "<span title='"+ value +"'>"+value+"</span>";
                }
				},{
					field : 'abscissa',
					title : '横坐标',
					width : 150
				},{
					field : 'ordinate',
					title : '纵坐标',
					width : 150
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
  	 <input type="hidden" id="path" name="ruleName" value="理财机构名称对应表.xlsx">
	</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
