<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>理财人力花名册列表</title>
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
	          url:'${app}/money/manpowerRoster/doUpload',
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
				url : '${app}/money/manpowerRoster/pageList',
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
					field : 'employee_no',
					title : '编号',
					width : 100
				},{
					field : 'referral_code',
					title : '推荐码',
					width : 100
				},{
					field : 'id_card',
					title : '身份证号',
					width : 170
				},{
					field : 'employee_name',
					title : '员工姓名',
					width : 100
				},{
					field : 'district_name',
					title : '区域',
					width : 120
				},{
					field : 'org_name',
					title : '花名册机构名称',
					width : 160,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'org_principal',
					title : '机构负责人',
					width : 120
				},{
					field : 'level4_department',
					title : '四级部门/团',
					width : 130,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'level4_principal',
					title : '四级部门/团负责人',
					width : 100
				},{
					field : 'level5_department',
					title : '五级部门/组',
					width : 120,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'level5_principal',
					title : '五级部门/团负责人',
					width : 120
				},{
					field : 'position',
					title : '职位',
					width : 140,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'position_type',
					title : '职类',
					width : 140
				},{
					field : 'entry_date',
					title : '入职时间',
					width : 140
				},{
					field : 'is_positive',
					title : '是否转正',
					width : 140
				},{
					field : 'positive_date',
					title : '实际转正日期',
					width : 140
				},{
					field : 'dimission_date',
					title : '离职日期',
					width : 140
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
			
  	 <input type="hidden" id="path" name="ruleName" value="理财人力花名册.xlsx">
	</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
