<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/zTree.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>发邮件</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
	   //风控经理下拉框追加内容
	    var json = [];
		var zTreeObj = null;
		var deptDialog = null;
		$(document).ready(function(){
			var setting = {
					data: {
						key:{name: 'org_name'},
						simpleData: {
							enable: true,
							idKey: 'org_no',
							pIdKey: 'parent_no'
						}
					},
					view: {
						showTitle: false,
						expandSpeed: 0
					},
					callback: {
						onClick: toClick
					}
			};
			
			var zTreeNodes = null;
			//查询树节点
			$.ajax({
				url : "${app}/risk/findOrgStructTree",
				type : "post",
				dataType : "json",
				async : false,
				success : function(msg){
					if(msg != null && msg.length != 0){
						zTreeNodes = msg;
					}
				},		
				error : function(){
					$.messager.alert("提示信息","系统错误！","info");
				}
			});
			
			zTreeObj = $.fn.zTree.init($("#treeOrgStruct"), setting, zTreeNodes);
			zTreeObj.expandAll(false);//全部展开
			
			//初始化加载营业部列表
			datagrid = $('#datagrid').datagrid({
				url : '${app}/risk/findChildListByOrgNo?orgNo='+"A"+'&rank='+0,
				title : '',
				pagination : true,
				pageSize : <%=Constants.PAGE_SIZE_TWENTY%>,
				pageList : [10,20,30,40,50],
				fit : true,
				border : false,
				columns : [[{
					field:'subcenter_no',title:'subcenter_no',width:30,hidden:true
				},{
					field:'cd',
					checkbox:true
				},
				  {
					field : 'org_no',
					title : '组织编号',
					width : 240
					
				},{
					field : 'org_name',
					title : '组织名称',
					width : 160,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'org_short_name',
					title : '简称',
					width : 160
				},{
					field : 'principal_name',
					title : '负责人',
					width : 160
				},{
					field : 'credit_manager_name',
					title : '风控经理',
					width : 160,
					formatter: function(value,row,index){
						if(null == value){
							 return "";
						}
                         return "<span title='"+ value +"'>"+value+"</span>";
                 }
				},{
					field : 'parent_name',
					title : '上级组织',
					width : 160
				},{
					field : 'subcenter_name',
					title : '分中心',
					width : 80
				},{
					field : 'operate',
					title : '操作',
					width : 50,
					formatter : function(value,row,index){
						return "<a href='#' onclick='edit(\""+row.id+"\",\""+index+"\");' >编辑</a>";
					}
				}
				]]
			});
		});
		
		//右键回调事件
		function toClick(event, treeId, treeNode) {
			if(treeNode != null){
				zTreeObj.selectNode(treeNode);//选中当前节点
				orgNo = treeNode.org_no;
				rank = treeNode.rank;
				if(rank == undefined){
					rank = 0;
				}
				datagrid = $('#datagrid').datagrid({
					url : '${app}/risk/findChildListByOrgNo?orgNo='+orgNo+'&rank='+rank,
					title : '',
					pagination : true,
					pageSize : <%=Constants.PAGE_SIZE_TWENTY%>,
					pageList : [10,20,30,40,50],
					fit : true,
					border : false,
					columns : [[{
						field:'cd',
						checkbox:true
					},
					  {
						field : 'org_no',
						title : '组织编号',
						width : 240
						
					},{
						field : 'org_name',
						title : '组织名称',
						width : 160
					},{
						field : 'org_short_name',
						title : '简称',
						width : 160
					},{
						field : 'principal_name',
						title : '负责人',
						width : 160
					},{
						field : 'credit_manager_name',
						title : '风控经理',
						width : 160
					},{
						field : 'parent_name',
						title : '上级组织',
						width : 160
					},{
						field : 'subcenter_name',
						title : '分中心',
						width : 80
					},{
						field : 'operate',
						title : '操作',
						width : 50,
						formatter : function(value,row,index){
							return "<a href='#' onclick='edit(\""+row.id+"\",\""+index+"\");' >编辑</a>";
						}
					}
					]]
				});
			}
		}

	
	//取消修改负责人
	function resetForm(){
		$('#datagrid').datagrid('reload'); 
		$('#datagrid').datagrid('clearSelections');
		$('#editPrincipal').dialog('close'); 
		$('#searchPrincipal').dialog('close');
	}
	//查找负责人
	function openSearch(){
        $("#write_into_flag").val("manager");
		document.getElementById("searchPrincipal").style.display = "block";
		$('#searchPrincipal').dialog({
			title:'查找负责人',
			width: 500,    
			height: 325,
			closed: false,    
		    left: 690,
		    top:60
		});
	}
	
	
    //编辑负责人
	function edit(id,index){
    	
    	//清空复选框状态
		 $("#isDetail").attr("checked",false);
		 $("#isManagerDetail").attr("checked",false);
		document.getElementById("editPrincipal").style.display = "block";
		$('#editPrincipal').dialog({
			title:'编辑负责人',
			 width: 410,    
			 height: 325,    
			 closed: false,    
			 modal: true ,
			 left: 280,
			 top:60,
		    onClose:function(){
		    	$('#searchPrincipal').dialog('close');
				$('#editPrincipal').dialog('close');
				$('#datagrid').datagrid('reload'); 
				$('#datagrid').datagrid('clearSelections');
		    }
		});   
		 var row = $('#datagrid').datagrid('getData').rows[index];
		//编号
		$("#org_no").textbox("setValue",row.org_no);
		$("#org_name").textbox("setValue",row.org_name);
		$("#parent_name").textbox("setValue",row.parent_name);
		$("#org_short_name").textbox("setValue",row.org_short_name);
		$("#principal_name").textbox("setValue",row.principal_name);
		//员工编号
		$("#principal_no").val(row.principal_no);
		
		//分中心回显
		$("#subcenter_no").combobox({
			url: "${app}/risk/queryAllRiskSubcenter",
			valueField: 'subcenterNo',
		    textField: 'name',
		    onLoadSuccess: function(){
				if(row.subcenter_no){
					$(this).combobox("select",row.subcenter_no);
				}
		    }
		});
		//清空 风控经理编号 和 姓名 下拉框内容
		 json = [];
		 $('#creditManagerName').combobox({
				  data:[],
                  url:'',
                  valueField:'id',
                  textField:'text',
          });
	   	 var creditManagerNo =row.credit_manager_no;
	   	 var creditManagerName =row.credit_manager_name;
	   	 if(null != creditManagerNo && '' != creditManagerName && null !=creditManagerName ){
	   		 var nos = creditManagerNo.split(",");
	       	 var names = creditManagerName.split(",");
	       	 for (var i=0;i<nos.length;i++){
	       		 var obj = {}; 
	       		 obj.id = nos[i];
	       		 obj.text = names[i];
	       		 obj.selected= true;
	       		 json.push(obj);
	       	 }
	       	 data = $.parseJSON(JSON.stringify(json));
	   		$("#creditManagerName").combobox("loadData", data); 
	   	 } 
	   	 
	   	 //回显复选框
	   	 var isDetail =row.is_principal_detail;
	   	 var isManagerDetail =row.is_credit_manager_detail;
		 if(isDetail == "1"){
			 $('#isDetail').attr('checked',true)
		 }
		 if(isManagerDetail == "1"){
			 $('#isManagerDetail').attr('checked',true)
		 }
		 if(row.principal_send_aging == undefined){
			 //未设置 默认C
			$('#principal_send_aging').combobox('select',0);
		 }else{
			 //已设置 回显
			 $('#principal_send_aging').combobox('select',row.principal_send_aging);
		 }

    } 
    
	//负责人列表
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
			}]],
			onClickRow: function (index, row) { 
				
				 var writeIntoFlag = $("#write_into_flag").val();
                 var name = row["name"];
                 var orgNo = row["workCode"];

                 if (writeIntoFlag == 'manager'){
                	//员工姓名
     				$("#principal_name").textbox("setValue",name);
     				//员工编号
     				$("#principal_no").val(orgNo);
                     //关闭搜索框
                     $('#searchPrincipal').dialog("close");
                     reset();
                 } else if (writeIntoFlag == 'creditManager'){
                 	var obj = {};
                 	var id = row["workCode"];
     				obj.id= id;
     				obj.text = row["name"];
     				obj.selected = true;
                 	var length = json.length;
     				if(length == 0){
     					json.push(obj);
     				}else{
     					//判断json中是否有重复数据
     					var flag = checkJson(json,id);
     					if(flag){
     						json.push(obj);
     					}
     				}
     				data = $.parseJSON(JSON.stringify(json));
     				$("#creditManagerName").combobox("loadData", data); 
                 }
            }
        });
	}	
	
	
	
	//修改负责人
	function submitForm(){
		
		 
		//判断复选框是否被选中
		if ($('#isDetail').attr('checked')) {
		    $("#isPrincipalDetail").val("1");
		}else{
		    $("#isPrincipalDetail").val("0");
		}
		if($('#isManagerDetail').attr('checked')) {
			    $("#isCreditManagerDetail").val("1");
		}else{
			 $("#isCreditManagerDetail").val("0");
		}
		
		//风控经理编号 姓名 赋值
		var value = $('#creditManagerName').combobox('getValues');
		var text = $('#creditManagerName').combobox('getText');
		$("#creditManagerNo").val(value);
		$("#creditName").val(text);
		//简称 非空校验
		var shortName = $("#org_short_name").textbox("getValue");
		var flag = true;
		if(2>shortName.length || shortName.length>5){
			$.messager.show({
				title:'信息提示',
				msg:'简称长度2~5!',
				timeout:3000,
				showType:'slide'
			});
			flag = false;
			return;
		}
		var name = $("#principal_name").textbox("getValue");
		//负责人姓名不为空 判断编号是否为空
		if('' != name){
			var principalNo = $("#principal_no").val();
			if(null == principalNo || '' ==principalNo){
				$.messager.show({
					title:'信息提示',
					msg:'不能直接输入负责人!',
					timeout:3000,
					showType:'slide'
				});
				flag = false;
				return;
			}else{
			 flag = true;
			}
		}
		if(flag){
			var editPrincipalForm = $("#editPrincipalForm");
			editPrincipalForm.form('submit',{
				 url:'${app}/risk/editOrgData',
				 success:function(data){
						var obj = eval("(" + data + ")");
						if(obj){
							$('#datagrid').datagrid('reload'); 
							$('#editPrincipal').dialog('close');
							$('#datagrid').datagrid('clearSelections');
						}else{
							$.messager.show({
								title:'信息提示',
								msg:'修改失败!',
								timeout:3000,
								showType:'slide'
							});
							$('#editPrincipal').dialog('close');
							$('#searchPrincipal').dialog('close');
						}
					}
			});
		}		
	}

	
	//点击发邮件 弹框
	function sendEmail(){
		var rows = datagrid.datagrid('getChecked');
		var flag = true;
		//检查是否选择记录
		if(rows.length > 0){
			for(var i = 0; i < rows.length; i++){
				//判断风控经理和负责人是否都为
				var name = rows[i].principal_name ;
				var mName = rows[i].credit_manager_name ;
				if('' == name || undefined == name ){
					if('' == mName || undefined == mName){
						var flag = false;
						 $.messager.show({
								title:'信息提示',
								msg:"请编辑"+rows[i].org_name+'的负责人或风控经理!',
								timeout:3000,
								showType:'slide'
							}); 
					}
				}
			}
			if(flag){
				var rows = datagrid.datagrid('getChecked');
				//定义
				var array=new Array();
				for(var i = 0; i < rows.length; i++){
					var obj = new Object();
					obj.orgNo = rows[i].org_no;//组织编号 	
					obj.orgShortName= rows[i].org_short_name//简称
					obj.principalNo = rows[i].principal_no//负责人编号
					obj.creditManagerNo = rows[i].credit_manager_no//风控编号
					obj.isPrincipalDetail = rows[i].is_principal_detail//负责人是否发送详细数据
					obj.isCreditManagerDetail = rows[i].is_credit_manager_detail//风控经理是否发送详细数据
					array.push(obj);
				}
				document.getElementById("searchEmailAddress").style.display = "block";
				var json = JSON.stringify(array);
				 var pJson = encodeURI(encodeURI(json));
				var datagrid3
				datagrid3 = $('#emailTable').datagrid({
						url : '${app}/risk/findEmailAddressByList?json='+pJson,
						title : '',
						pagination : false,
						fit : true,
						singleSelect : true,
						border : false,
						columns : [[
						   {
							field : 'orgNo',
							title : '部门编号',
							width : 230,
							hidden:'true'
						},{
							field : 'orgShortName',
							title : '部门简称',
							width : 145
							
						},{
							field : 'name',
							title : '员工姓名',
							width : 120
							
						},
						  {
							field : 'email',
							title : '邮箱地址',
							width : 200
							
						},
						 {
							field : 'status',
							title : '状态',
							width : 60,
							formatter : function(value,row,index){
								var str ;
								if("1" == row.status){
									str = "在职";
								}else{
									str = "离职";
								}
								return str;
							}
							
						}]]
					});
					$('#searchEmailAddress').dialog({
						title:'日报发送',
						width: 540,    
					    height: 300, 
					    modal:true,
					    closed: false,    
					    left: 300,
					    top:60,
					    buttons:[{
							text:'确定',
							iconCls:'icon-ok',
							handler:function(){
								send();
							}
						},{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								cancelSend();
							}
						}]
					});
		}
		}else{
			$.messager.show({
				title:'信息提示',
				msg:'请选择要发送邮件的记录!',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	
	//发送邮件
	function send(){
		//得到邮箱地址列表所有邮箱
		var datas = $("#emailTable").datagrid('getRows');
		var orgNo;//营业部编号
		var receiverName;//负责人
		var receiverNo;//负责人编号
		var receiverAddress;//邮箱
		var principal;//是否是负责人
		var isPrincipalDetail;//负责人是否发送详细数据
		var isCreditManagerDetail;//风控经理是否发送详细数据
		var isLeave;//是否离职
			for(var i = 0; i < datas.length; i++){
				orgNo = datas[i].orgNo;
				receiverName=datas[i].name;
				receiverNo=datas[i].principalNo;
				receiverAddress=datas[i].email;
				principal = datas[i].principal;
				isPrincipalDetail= datas[i].isPrincipalDetail;
				isCreditManagerDetail= datas[i].isCreditManagerDetail;
				isLeave = datas[i].status;
				//调用发送邮件接口
				$.ajax({
					url : "${app}/risk/sendEmail",
					type : "post",
					dataType : "json",
					async:false, //设置为同步
					data:{"orgNo":orgNo,"receiverName":receiverName,"receiverNo":receiverNo,
						 "receiverAddress":receiverAddress,"principal":principal,
						 "isPrincipalDetail":isPrincipalDetail,
						 "isCreditManagerDetail":isCreditManagerDetail},
					success : function(data){
							$.messager.show({
								title:'信息提示',
								msg:'邮件发送完毕,请查看邮件发送详情!',
								timeout:5000,
								showType:'slide'
							});
							$('#searchEmailAddress').dialog("close");
							$('#datagrid').datagrid('reload'); 
							$('#datagrid').datagrid('clearSelections');
					}
				});
	        }
		
	}
	//取消邮件发送
	function cancelSend(){
		$('#searchEmailAddress').dialog("close");
		$('#datagrid').datagrid('reload'); 
		$('#datagrid').datagrid('clearSelections');
	}
	 //查找风控经理
    function openSearch_creditManager() {
        $("#write_into_flag").val("creditManager");
        document.getElementById("searchPrincipal").style.display = "block";
        $('#searchPrincipal').dialog({
            title: '查找风控经理',
            width: 500,
            height: 325,
            closed: false,
            left: 690,
            top: 60
        });
    	reset();
    }
	 
    //下拉框去重
	function checkJson(json,id){
		for(var i in json){
			 if(json[i].id == id ){
				 return false;
				 break;
			}
		}
		return true;
	}
    
    function reset(){
    	 //清空搜索框和datagrid的值
        $("#principalName").textbox("setValue","");
		//清空datagrid
		$('#principalTable').datagrid('loadData',{total:0,rows:[]});
    }
    
  //重置负责人
    function resetPrincipal(){
    	$("#principal_no").val('');
    }
	</script>
  </head>
<body class="easyui-layout" fit="true" style="width: 100%;height: 100%;">
  	<div region="west" split="true" title="组织机构树"  class="trreDiv">
			 <ul id="treeOrgStruct" class="ztree"></ul> 
      </div>
	<div region="center" style="background:#eee;">
		<!-- 结束展示树的div --> 
		<div id="div"  style="height:10%;width: 100%">
	 		    <a style="background-image: url(${app}/images/se.png); width: 92px;height:25px;
	 		    margin-top:12px;margin-left:10px;font-size:12px;" class="easyui-linkbutton" 
	 		    onclick="javascript:sendEmail();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发邮件</a>
		</div>
		<!-- 营业部列表 -->
		<div style="height:90%;width: 100%">
	  			<table id="datagrid"></table>
	  	</div>
  	</div>
  	<div id="editPrincipal"  style="display: none; overflow: hidden;">
  		<form id="editPrincipalForm" method="post" style="margin-left: 40px;padding: 10px;">
  		    <!-- 负责人的编号 -->
  			<input type="hidden" id="principal_no" name="principalNo" />
  			<table>
  				<tr>
  					<td>编号</td>
  					<td>
  					<input type="text" id="org_no" name="orgNo" class="easyui-textbox"  style="width: 175px;height: 24px;" readonly="true"/>
  					</td>
  				</tr>
  				<tr>
  					<td>名称</td>
  					<td>
  					<input type="text" id="org_name" name="orgName" class="easyui-textbox"  style="width: 175px;height: 24px;" readonly="true"/>
  					</td>
  				</tr>
  				<tr>
  					<td>上级</td>
  					<td>
  					<input type="text" id="parent_name" name="parent_name" class="easyui-textbox"  style="width: 175px;height: 24px;" readonly="true"/>
  					</td>
  				</tr>
  				<tr>
  					<td>简称</td>
  					<td>
  					<input type="text" id="org_short_name" name="orgShortName" class="easyui-textbox"  style="width: 175px;height: 24px;"/>
  					</td>
  				</tr>
  				<tr>
  					<td>负责人</td>
  					<td>
  						<input type="text" data-options="events:{focus:openSearch,change:resetPrincipal}"  id="principal_name" name="principalName" class="easyui-textbox"  style="width: 175px;height: 24px;" />
  				 		<input type="checkbox" id="isDetail" name="isDetail">发送明细数据
  						<input type="hidden" id="isPrincipalDetail" name="isPrincipalDetail">
  					</td>
  				</tr>
  				 <tr>
		            <td>风控经理</td>
		            <td>
            		<input  class="easyui-combobox" id="creditManagerName"  style="width: 175px;height: 30px;" data-options="events:{focus:openSearch_creditManager},valueField:'id', textField:'text', panelHeight:'auto',multiple:true"  editable="false">
		             	<input type="checkbox" id="isManagerDetail" name="isManagerDetail">发送明细数据
		             	<input type="hidden" id="isCreditManagerDetail" name="isCreditManagerDetail">
		              <!-- 风控经理编号 -->
		  		    <input type="hidden" id="creditManagerNo"  name="creditManagerNo">
		  		     <!-- 风控经理姓名 -->
		  		    <input type="hidden" id="creditName" name="creditManagerName">
		            </td>
		        </tr>
  				<tr>
  					<td>分中心</td>
  					<td>
  						<input id="subcenter_no" name="subcenterNo" style="width: 175px;height: 24px;"/>
  					</td>
  				</tr>
  				<tr>
  					<td>逾期账龄</td>
  					<td>
						<select id = "principal_send_aging"  name="principalSendAging" class="easyui-combobox" name="dept" style="width:175px;" data-options="panelHeight:'auto'">   
						    <option value="0">C</option>   
						    <option value="2">C、M2</option>   
						    <option value="3">C、M2、M3</option>   
					</select>
			    </td>
  				</tr>
  				<tr>
				<td colspan="4" align="center">
				    <br>
					<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm();">提交</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:resetForm();">取消</a>
				</td>
				</tr>
  			</table>
  		</form>
  	</div>
  	<!-- 查找联系人 -->
  		<div id="searchPrincipal"  style="display: none;overflow: hidden;" >
  		
  			<div class="easyui-layout" data-options="fit:true" style="width: 100%;height: 100%;" >
  			
	  			<div data-options="region:'north',split:false" style="width: 100%;height:14%">
		 			<input type="text" id="principalName"  class="easyui-textbox"  style="width: 200px;height: 24px;"/>
	  				<a class="easyui-linkbutton" iconCls="icon-search" onclick="searchPrincipal()">查询</a>
		 		     <!-- 标识 是负责人 还是风控经理 -->
		 		     <input type="hidden" id="write_into_flag" value="manager">
		 		</div>
		 		
		 		<div data-options="region:'center',split:false" style="width: 100%;height:86%">
		 			<table id="principalTable"></table>
		 		</div>
		 		
	 		</div>
	 		
  		</div>
  	<!-- 查找联系人邮箱地址 -->
  	<div id="searchEmailAddress" style="display: none; overflow: hidden;" >
  		<table id="emailTable"></table>
  	</div>
  </body>
</html>