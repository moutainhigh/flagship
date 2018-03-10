<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>客户端帮助问题列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
		var datagrid;
		$(function(){ 
			
			
				//下拉列表添加“全部”选项
				$.ajax({ 
					url: '${app }/help/getAllSorts',
					dataType: 'json', 
					success: function(data){   
						// 修改ajax返回的值
						data.unshift({'id':'0','name':'全部'});   //unshift方法添加到第一行，push方法添加到末尾
						$('#type').combobox({            
							data:data,        
							valueField:'id',        
							textField:'name', 
							editable:false //不可编辑
						});    
					}
				});
			
			datagrid = $('#datagrid').datagrid({
				url : '${app}/help/helpList',
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE%>,
				pageList : [10,20,30,40,50],
				fit : true,
				singleSelect:true,
				toolbar:"#toolbar",
				border : false,
				idField : 'id',
				columns : [[{
					field:'cd',
					checkbox:true
				},{
					field : 'name',
					title : '类型',
					width : 120,
				},{
					field : 'question',
					title : '问题',
					width : 600 
				},{
					field : 'browse_num',
					title : '浏览量',
					width : 120 
				},{
					field : 'create_time',
					title : '时间',
					width : 260 
				}
				]]
			});
		
		
			$("#type").combobox({
				onChange : function(n, o) {
					datagrid.datagrid('load',serializeObject($("#searchForm")));
					datagrid.datagrid('clearSelections');
					datagrid.datagrid('clearChecked');
			}
		});
 		});
		
		
		//添加
		function toAdd(){
			parent.createTab('${app}/help/toAdd','添加帮助问题');
		}
		
		//修改
		function toEdit(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				parent.createTab('${app}/help/toEdit/' + rows[0].cid,'修改帮助问题');
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要修改的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
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
		}
		//提交
		function submitForm(){
			var addForm = $("#addForm");
			addForm.form('submit',{
				url:'${app}/app/doUniBroad',
				onSubmit:function(){
					if(addForm.form("validate")){
						if($("input[name=title]").val() != "" && $("input[name=content]").val() != ""){
							openMask();
							return true;
						}else {
							$.messager.alert('提示信息','请填写完整的表单','info');
							return false;
						}
					}else{
						return false;
					}
				},
				success:function(data){
					closeMask();
					$('#w').window('close');
					$('#datagrid').datagrid('unselectAll');
				}
			});
			
		}
		
		//取消
		function resetForm(){
			$('#w').window('close');
		}
		
		//删除帮助问题
		function deleteFun(){
			var rows = datagrid.datagrid('getSelections');
			if(rows.length > 0){
				if(rows.length > 1){
					$.messager.show({
						title:'信息提示',
						msg:'该操作只能选择一条记录!',
						timeout:5000,
						showType:'slide'
					});
					return;
				}
				
				$.messager.confirm("请确认", "您确实要删除该帮助问题吗？", function(b){
					if(b){
							$.ajax({
								type:'post',
								url:"${app}/help/delete?id="+rows[0].cid+"&sortId="+rows[0].sid,
								dataType:'json',
								success:function(msg){
									if(msg){
									parent.createTab('${app}/app/data/help/help_list.jsp','帮助');
									}
									$.messager.alert('提示信息','此类问题不能少于一个，暂时无法删除','info');
								}
							});
						}
				}); 
			}else{
				$.messager.show({
					title:'信息提示',
					msg:'请选择要删除的记录!',
					timeout:5000,
					showType:'slide'
				});
			}
		}
	</script>
  </head>
  
  <body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="north" border="false" style="height:28px; overflow:hidden;">
  		<form id="searchForm">
	  		<table border="0" class="searchForm datagrid-toolbar" width="100%">
	  			<tr>
		  			<td>
					<input id="type" style="width: 200px;"   class="easyui-combobox" value="0" name="sortId">
				</td>
					<td class="tdR" width="5%">问题名称:</td>
					<td width="10%">
						<input id="name" name="question" maxlength="30" class='easyui-textbox' style="width: 150px;height: 22px;"/>
					</td>
					<td>
					<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
				</td>
				</tr>
			</table>
		</form>
	</div>
	<br>
  	<div region="center" border="false" style="overflow: hidden;">
  		<table id="datagrid"></table>
  	</div>
 	<div id="toolbar" style="display: none;">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="toAdd();">新增问题</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
  		<a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="toEdit();">修改</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-no',plain:true" onclick="deleteFun();">删除</a>
		<img src="${app}/images/separator.jpg" style="vertical-align: middle; *margin-top: -4px">
	</div>
  </body>
</html>
