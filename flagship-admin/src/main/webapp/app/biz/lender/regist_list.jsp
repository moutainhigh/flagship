<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册用户列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		
		var datagrid;
		$(function(){
			datagrid = $('#datagrid').datagrid({
				url : '${app}/lenderDetail/registList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				toolbar:"#toolbar",
				border : false,
				singleSelect : true,
				idField : 'id',
				columns : [[{
					field:'id',
					checkbox:true
				},{
					field : 'nick_name',
					title : '用户名',
					width : 200
				},{
					field : 'mobile',
					title : '手机号',
					width : 120
				},{
					field : 'referral_code',
					title : '推荐码',
					width : 80 
				},{
					field : 'certification_time',
					title : '实名认证次数',
					width : 120,
					editor:{type:'numberbox',options:{precision:0}, required:true}
				},{
					field : 'register_way_name',
					title : '注册方式',
					width : 80 
				},{
					field : 'use_status',
					title : '使用状态',
					width : 80 
				},{
					field : 'system_code',
					title : '系统编码标示',
					width : 150 
				},{
					field : 'create_time',
					title : '注册时间',
					width : 140
				},{
					field : 'operate',
					title : '操作',
					width : 150,
					formatter : function(value, rowData, rowIndex){
						if(rowData.use_status != '不可用'){
							var a ="<sec:authorize ifAnyGranted='${ctrl.join_blacklist}'><a href='javascript:void(0);' onclick=joinBlacklist("+rowData.id+")>加入黑名单</a></sec:authorize>";
							var e ="<sec:authorize ifAnyGranted='${ctrl.regist_mgr_edit}'><a href='javascript:void(0);' onclick=editRow('"+rowIndex+"')>编辑</a></sec:authorize>";
							var s ="<a href='javascript:void(0);' onclick=saveRow('"+rowIndex+"')>保存</a>";
							var c ="<a href='javascript:void(0);' onclick=cancelRow('"+rowIndex+"')>取消</a>";
							var sep = "&nbsp;&nbsp;";
							if(rowData.editing){
								return s+sep+c;
							}else{
								return a+sep+e;
							}
						}
					}
				}]],
				onBeforeEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				},
				onAfterEdit: function(index,row,changes){
					var id = row.id;
					var certificationTime = row.certification_time;
					row.editing = false;
					updateActions(index);
					$.ajax({
			    		type: "POST",
			    	    data: {id:id, certificationTime:certificationTime},
			    	    url: "${app}/lenderDetail/updateCertificationTime",
					    dataType:"json",
			    	    success: function(msg){
			    			if(msg.messageCode!="error"){
			    			   $.messager.show({
					 				title:'信息提示',
					 				msg:'保存成功',
					 				timeout:5000,
					 				showType:'slide'
					 			});
			    		    }else{
			    			   $.messager.show({
					 				title:'信息提示',
					 				msg:'保存失败',
					 				timeout:5000,
					 				showType:'slide'
					 			});
			    			   $('#datagrid').datagrid('cancelEdit', index);
			    		    }
			    	    }
				    });
					
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					updateActions(index);
				}
			});
		});
		
		function updateActions(index){
			$('#datagrid').datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		
		function editRow(index){
			$('#datagrid').datagrid('beginEdit', index);
		}
		
		function saveRow(index){
			$('#datagrid').datagrid('endEdit', index);
		}
		
		function cancelRow(index){
			$('#datagrid').datagrid('cancelEdit', index);
		}
		
		function joinBlacklist(id){
			$.messager.confirm('确认','确认将该用户加入黑名单?',function(r){
			    if (r){
			    	$.ajax({
			    		type: "POST",
			    	    data: {id:id},
			    	    url: "${app}/lenderDetail/joinBlacklist",
					    dataType: "json",
			    	    success: function(msg){
			    			if(msg.messageCode!="error"){
			    			   $.messager.show({
					 				title:'信息提示',
					 				msg:'加入黑名单成功',
					 				timeout:5000,
					 				showType:'slide'
					 			});
			    			   datagrid.datagrid('load');
			    		    }else{
			    			   $.messager.show({
					 				title:'信息提示',
					 				msg:'系统异常',
					 				timeout:5000,
					 				showType:'slide'
					 			});
			    		    }
			    	    }
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
			$("#registerWay").combobox("setValue","");
			$("#systemCode").combobox("setValue","");
			clearForm(datagrid);
		}
		
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:64px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
					<td class="tdR" width="8%">用户名:</td>
					<td width="20%">
						<input id="nickName" name="nickName" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="8%">手机号:</td>
					<td width="20%">
						<input id="mobile" name="mobile" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR" width="8%">注册日期:</td>
					<td width="40%">
						<input id="minCreateTime" name="minCreateTime" editable='false' maxlength="30" class='easyui-datebox' style="width: 110px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						~
						<input id="maxCreateTime" name="maxCreateTime" editable='false' maxlength="30" class='easyui-datebox' style="width: 110px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
				</tr>
				<tr>
					<td class="tdR">推荐码:</td>
					<td>
						<input id="referralCode" name="referralCode" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
					</td>
					<td class="tdR">注册方式:</td>
					<td>
						<select id ="registerWay" name ="registerWay" style="width:150px;" editable="false" data-options="panelHeight:112" class="easyui-combobox">
							<option value="" selected="selected">---方式---</option>
							<c:forEach var="obj" items="${CodeMap['REGISTER_WAY']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
					</td>
					<td class="tdR">系统编码标示:</td>
					<td>
						<select id ="systemCode" name ="systemCode" style="width:150px;" editable="false" data-options="panelHeight:70" class="easyui-combobox">
							<option value="" selected="selected">---标示---</option>
							<c:forEach var="obj" items="${CodeMap['SYSTEM_CODE']}">
								<option value="${obj.key}">${obj.value}</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
						<a class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun(datagrid);">清空</a>
					</td>
	  			</tr>
			</table>
		</form>
	</div>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
  </body>
</html>
