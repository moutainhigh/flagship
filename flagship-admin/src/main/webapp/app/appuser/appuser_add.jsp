<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>添加客户端用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<style type="text/css">
	
	/* title 居中 */
       .panel-title {
          text-align: center;
     	 }
		.trreDiv {
			float: left;
			width: 23%;
			height: 100%;
			font-size: 15px;
			border: 1px solid #6699FF;
		}
		#treeDept {
			float: left;
			width: 95%;
			height: 90%;
			overflow: auto;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#sort").combobox({
					onChange : function(n, o) {
						var str="";
						var sortId = $("input[name=sortId]").val();
						$.ajax({
							type: "post",
				             url: "${app}/app/getAllUser",
				             data: {sortId:sortId},
				             dataType: "json",
				             success: function(data){
				            	  if(data == "" ){
				            		  $('#letter').combobox('clear'); 
				            	  } else{
				            		  for(var o in data){ 
						            		 var toUserId = data[o].id;
						            		 str += toUserId + ",";
						            	}
						            	var newstr=str.substring(0,str.length-1);
						            	$('#letter').combobox('setValues',newstr.split(","))
				            	  }
				             }
						})
				}
			});
			
		});
		
		

		//提交
		function submitForm() {
			var addForm = $("#addForm");
			addForm.form('submit', {
				 url : '${app}/app/doAdd', 
				onSubmit : function() {
					if (addForm.form("validate")) {
						
						var permissionIds = $("#permissionIds").val();
						if (permissionIds == "" || permissionIds == undefined) {
							$.messager.alert('提示信息', '请设置权限', 'info');
							return false;
						} 
						
						var sortId = $("input[name=sortId]").val();
						if (sortId == "" || typeof (sortId) == "undefined") {
							$.messager.alert('提示信息', '请选择分类', 'info');
							return false;
						} 
						var employeeNo = $("#employeeNo").textbox("getValue")
						if (employeeNo == "" || typeof (employeeNo) == "undefined") {
							$.messager.alert('提示信息', '请输入员工编号', 'info');
							return false;
						} 
						var status = $("#status").val();
						if(status == 0){
							$.messager.alert('提示信息', '该员工已离职，不能添加', 'info');
							return false;
						}
						/* var letter = $("#letter").combobox("getValue");
						if (letter == "" || letter == undefined) {
							$.messager.alert('提示信息', '请设置私信联系人', 'info');
							return false;
						}  */
						
						return true;
					} else {
						return false;
					}
					
				},
				success : function(data) {
					closeMask();
					var obj = eval("(" + data + ")");
					parent.refreshTab("${app}/app/toAppUserList?messageCode="
							+ obj.messageCode, "客户端用户管理");
					parent.closeTab("添加客户端用户");
				}
			});
		}
		//获取风控下的所有子孙级查看是否有选中，有则返回true，无则返回false
		getAllChildrenNodes = function (treeNode,flag){ 
		      if (treeNode.isParent) {
		        var childrenNodes = treeNode.children;
		        if (childrenNodes) {
		            for (var i = 0; i < childrenNodes.length; i++) {
		            	var zTreeObj = $.fn.zTree.getZTreeObj("treePermission");
		            	var node = zTreeObj.getNodeByParam("id", childrenNodes[i].id, null);
		            	var checked = node.checked;
		            	if(checked){
		            		flag=checked;
		            		return flag;
		            	}
		            	flag = getAllChildrenNodes(childrenNodes[i], flag);
		            }
		        }
		    }
		    return flag;
		}

		//取消
		function resetForm() {
			parent.closeTab("添加客户端用户");
			$('#searchPrincipal').dialog('close');
		}
		//选择权限
		var deptDialog = null;
		function selectDept(){
			str = $("#permissionIds").val();
			deptDialog = $('#deptDialog').dialog({  
				top:100,
				title: "选择权限",   
				width: 450,   
				height: 320,  
				closed: false,   
				cache: false,   
				href: "${app}/app/toSelectPermission",   
				modal: true,
				 buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						var treeObj = $.fn.zTree.getZTreeObj("treePermission");
						var nodes = treeObj.getCheckedNodes(true);
						var ids = "";
						var names = "";
						for(var i in nodes){
							names+=nodes[i].name+","
							ids+=nodes[i].id+","
						}
						var node = treeObj.getNodeByParam("id", 3, null); 
						var flag =false;
						flag = getAllChildrenNodes(node,flag);
						if(flag){
							ids+=3;
							ids+=",";
						}
						var ids=ids.substring(0,ids.length-1);
						var names=names.substring(0,names.length-1);
						getLevelDeptInfo(names);
						$("#permissionIds").val('');
						$("#permissionIds").val(ids);
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						deptDialog.dialog('close');
					}
				}] ,
				 onLoad: function () {
					 //得到所有的节点
					 var treeObj = $.fn.zTree.getZTreeObj("treePermission");
					 var nodes = treeObj.getNodes();
					 var act=treeObj.transformToArray(nodes);
					 if(str != null && str != ''){
						 var array = str.split(",");
						 //遍历所有选中的节点
						 for(var i=0;i<array.length;i++){
							//遍历所有的节点
							  for(var j=0;j<act.length;j++){
								   if(array[i] == act[j].id){
									   //回显
									   treeObj.checkNode(act[j],true,true);
								  } 
						 	}  
						 }
						
					 }
					 
                 }
			}); 
		}
		
		 //赋值
		function getLevelDeptInfo(names){
					$("#permissionName").html(names);
					$('#deptDialog').dialog('close');
		}
		 
		//查找员工编号
		function openSearch(){
			document.getElementById("searchPrincipal").style.display = "block";
			$('#searchPrincipal').dialog({
				title:'查找员工编号',
				width: 540,    
			    height: 250,    
			    closed: false,    
			    left: 10,
			    top:210
			});
		}
		
	//员工详细信息列表
	function searchPrincipal(){
		var name = $("#principalName").textbox("getValue");
		var datagrid2
		datagrid2 = $('#principalTable').datagrid({
			url : '${app}/risk/findPrincipalName?name='+encodeURI(encodeURI(name)),
			title : '',
			pagination : false,
			fit : true,
			singleSelect : true,
			border : false,
			idField : 'id',
			columns : [[
			  {
				field : 'name',
				title : '姓名',
				width : 120
				
			},{
				field : 'workCode',
				title : '员工编号',
				width : 120
			},{
				field : 'jobTitle',
				title : '职务',
				width : 120
			},{
				field : 'depName',
				title : '部门',
				width : 120
			},
			{
				field : 'status',
				title : '状态',
				width : 60,
				formatter:function(value,rowData,rowIndex){
					var retStr = "";
					if(value == 1){
						retStr = "在职";
					}else{
						retStr = "离职";
					}
					return retStr;
				}
			}]],
			onClickRow: function (index, row) { 
				var orgNo = row["workCode"]; 
				var status = row["status"];
				//员工编号
				$("#employeeNo").textbox("setValue",orgNo);
				//是否离职
				$("#status").val(status);
				$('#searchPrincipal').dialog('close');
            }
		});
	}	
	</script>
  </head>
  
  <body style="background: white;">
  	<form id="addForm" class="easyui-form" method="post" modelAttribute="appuser">
		<!-- 是否离职 -->
		<input type="hidden" id="status">
		<table class="tableForm" border="1" width="100%" >
			<tr>
				<td width="15%" class="tdR"><span style="color: red">*</span>姓名:</td>
				<td width="35%">
					<input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true,validType:['length[0,10]']" style="width: 175px;height: 24px;"/>
				</td>
				<td width="15%" class="tdR"><span style="color: red">*</span>用户名:</td>
				<td width="35%">
					<input type="text" id="username" name="username" class="easyui-textbox" data-options="required:true,validType:['length[0,20]']" style="width: 175px;height: 24px;"/>
				</td> 
			</tr>
			<tr>
				<td class="tdR"><span style="color: red">*</span>手机号:</td>
				<td>
					<input type="text" id="mobile" name="mobile" class="easyui-textbox" data-options="required:true,validType:['length[0,11]','mobileIsValid']" style="width: 175px;height: 24px;"/>
				</td>
				<td class="tdR"><span style="color: red">*</span>默认密码:</td>
				<td>
					<input type="text" id="password" name="password" value="hzcf1234" class='easyui-textbox'  data-options="required:true,validType:['length[0,20]']" style="width: 175px;height: 24px;"/>
				</td>
			</tr>
			 
			<tr>
				<td class="tdR"><span style="color: red">*</span>设置权限:</td>
				<td>
				    <!-- 添加页面 再次选择用于回显 -->
					<input type="hidden" name="oauth"  id="permissionIds">
					<span id="permissionName" style="padding-left: 5px;"></span>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="selectDept();">选择权限</a>
				</td>
				<td class="tdR"><span style="color: red">*</span>分类:</td>
				<td >
					<input id="sort" style="width: 180px;" class="easyui-combobox" 
						name="sortId"
					data-options="
							url:'${app }/app/getSorts',
							method:'get',
							valueField:'id',
							textField:'name',
							multiple: false ,
							panelHeight:'auto'
					" >
				</td>
			</tr> 
				<tr>
				
					<td class="tdR">员工编号：</td>
  					<td>
  						<input type="text" data-options="events:{focus:openSearch}"  id="employeeNo" name="employeeNo" class="easyui-textbox"  style="width: 175px;height: 24px;" readonly="true" />
  					</td>
  					
  					
				<td class="tdR">私信设置:</td>
				<td >
					<input id="letter" style="width: 400px;"   class="easyui-combobox" 
						name="letter" 
					data-options="
							url:'${app }/app/getAllUser',
							method:'get',
							valueField:'id',
							textField:'name',
							multiple: true ,
							panelHeight:'auto'
					">
				</td>
				
			</tr> 
			<tr>
				<td colspan="4" align="center">
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
			</tr>
		</table>
		<div id="deptDialog"></div>
	</form>
  		
  	<!-- 查找员工编号-->	
  	<div id="searchPrincipal"  style="display: none;overflow: hidden;" >
  			<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;" >
	  			<div data-options="region:'north',split:false" style="width: 100%;height:14%">
		 			<input type="text" id="principalName"  class="easyui-textbox"  style="width: 200px;height: 24px;"/>
	  				<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchPrincipal()">查询</a>
		 		</div>
		 		
		 		<div data-options="region:'center',split:false" style="width: 100%;height:86%">
		 			<table id="principalTable"></table>
		 		</div>
	 		</div>
  		</div>
  		
  	</div>
  </body>
</html>
