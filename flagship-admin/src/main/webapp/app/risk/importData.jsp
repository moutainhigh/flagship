<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据导入</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript">
	
	var datagrid;
	var datagrid2;
	var datagrid3;
	$(function(){
		/*
		 * 老系统逾期数据
		 */
		datagrid = $('#oldOverdueTable').datagrid({
			url : '${app}/risk/findOldOverdueList',
			title : '',
			pagination : true,
			pageSize : <%=Constants.PAGE_SIZE%>,
			pageList : [10,20,30,40,50],
			singleSelect : true,
			border : false,
			idField : 'id',
			columns : [[
			  {
				field : 'apply_no',
				title : '申请编号',
				width : 120
			},{
				field : 'sale_department',
				title : '部门',
				width : 120
			},{
				field : 'client_name',
				title : '客户名称',
				width : 120
			},{
				field : 'contract_no',
				title : '合同号',
				width : 120
			},{
				field : 'product_name',
				title : '产品',
				width : 120
			},{
				field : 'product_no',
				title : '产品编号',
				width : 120
			},{
				field : 'card_no',
				title : '卡号',
				width : 200
			},{
				field : 'bank',
				title : '银行',
				width : 220,
				formatter: function(value,row,index){
					if(null == value){
						 return "";
					}
                     return "<span title='"+ value +"'>"+value+"</span>";
             }
			},{
				field : 'hand_amount',
				title : '到手额',
				width : 120
			},{
				field : 'periods',
				title : '期数',
				width : 120
			},{
				field : 'initial_repayment_time',
				title : '第一次还款日期',
				width : 120,
				formatter: function(value,row,index){
					if(null == value){
						 return "";
					}
                     return "<span title='"+ value +"'>"+value+"</span>";
             }
		   },{
				field : 'principal_and_interest',
				title : '还款本息',
				width : 120
  			 }
		   ,{
				field : 'penalty',
				title : '违约金总额',
				width : 120
  			}
		   ,{
				field : 'penalty_interest',
				title : '罚息总额',
				width : 120
  			},{
				field : 'breach_penalty',
				title : '罚息违约金总额',
				width : 120
			   },{
				field : 'total_repayment_amount',
				title : '应还款总额',
				width : 120
			},{
				field : 'repayment_date',
				title : '还款日期',
				width : 200,
				formatter: function(value,row,index){
					if(null == value){
						 return "";
					}
                     return "<span title='"+ value +"'>"+value+"</span>";
             }
		   },{
				field : 'team_manager',
				title : '团队经理',
				width : 120
		   },{
				field : 'client_manager',
				title : '客户经理',
				width : 120
		   },{
				field : 'old_org_name',
				title : '营业部',
				width : 120,
				formatter: function(value,row,index){
					if(null == value){
						 return "";
					}
                     return "<span title='"+ value +"'>"+value+"</span>";
             }
		   },{
				field : 'new_org_name',
				title : '新营业部名称',
				width : 120
		   },{
				field : 'org_no',
				title : '新营业部编码',
				width : 120
		   },{
				field : 'create_time',
				title : '创建时间',
				width : 120
		   }
			]]
		});
		
		/*
		 * 老系统还款数据
		 */
		datagrid2 = $('#oldLoanTable').datagrid({
			url : '${app}/risk/findOldLoanList',
			title : '',
			pagination : true,
			pageSize : <%=Constants.PAGE_SIZE%>,
			pageList : [10,20,30,40,50],
			singleSelect : true,
			border : false,
			idField : 'id',
			columns : [[
			  {
				field : 'apply_no',
				title : '申请编号',
				width : 120
			},{
				field : 'client_name',
				title : '姓名',
				width : 120
			},{
				field : 'contract_no',
				title : '合同编号',
				width : 120
			},{
				field : 'product_name',
				title : '贷款类型',
				width : 120
			},{
				field : 'product_no',
				title : '产品编号',
				width : 120
			},{
				field : 'purpose',
				title : '用途',
				width : 120
			},{
				field : 'initial_repayment_time',
				title : '第一次还款日期',
				width : 120
		   },{
				field : 'periods',
				title : '贷款期限',
				width : 120
			},{
				field : 'contract_amount',
				title : '合同金额',
				width : 120
  			 }
		   ,{
				field : 'loan_amount',
				title : '放款金额',
				width : 120
  			}
		   ,{
				field : 'month_repayment',
				title : '月还款额',
				width : 120
  			},{
				field : 'pay_date',
				title : '付款时间',
				width : 120
			   },{
				field : 'old_org_name',
				title : '营业部',
				width : 120
			},{
				field : 'counselor',
				title : '咨询人',
				width : 120
		   },{
				field : 'salesman',
				title : '直销人',
				width : 120
		   },{
				field : 'contract_end_date',
				title : '合同结束日期',
				width : 120
		   },{
				field : 'district',
				title : '大区',
				width : 120
		   } ,{
				field : 'org_no',
				title : '新营业部编码',
				width : 120
		   },{
				field : 'create_time',
				title : '创建时间',
				width : 120
		   }
			]]
		});
		
		/*
		 * 手工调整表数据
		 */
		datagrid3 = $('#handAdjustTable').datagrid({
			url : '${app}/risk/findHandAdjustList',
			title : '',
			pagination : true,
			pageSize : <%=Constants.PAGE_SIZE%>,
			pageList : [10,20,30,40,50],
			singleSelect : true,
			border : false,
			idField : 'id',
			columns : [[
			  {
				field : 'contract_no',
				title : '合同编号',
				width : 200
			},{
				field : 'batch',
				title : '批次',
				width : 160
			},{
				field : 'adjustment',
				title : '增减量',
				width : 120
			},{
				field : 'create_time',
				title : '创建时间',
				width : 120
			}
			]]
		});
		
	});
	
	/*
	 * 老系统逾期表
	*/
	function uploadOldOverdue() {
		  var multipart = $("#overdueFileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
			load();//弹出遮罩层
	      $("#overdueForm").ajaxSubmit({
	          type : 'POST',
	          dataType:"json",
	          url:'${app}/risk/uploadOverdue',
	          success : function(data) {
	        	  disLoad(); // 关闭遮罩层
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
		          			$('#oldOverdueTable').datagrid('reload'); 
		                 },1500);
		          	}
	          }
	      });
	  } 
	/*
	 * 老系统放款表
	*/
	function uploadOldLoan() {
		  var multipart = $("#loanFileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
			load();//弹出遮罩层
	      $("#loanForm").ajaxSubmit({
	          type : 'POST',
	          dataType:"json",
	          url:'${app}/risk/uploadOldLoan',
	          success : function(data) {
	        	disLoad(); // 关闭遮罩层
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
	          			$('#oldLoanTable').datagrid('reload'); 
	                 },1500);
	          	}
	          }
	      });
	  } 
	
	/*
	 * 手工调整表
	*/
	function uploadHandAdjust() {
		  var multipart = $("#handAdjustFileName").val();
			if(multipart==""||multipart==null){
				alert("请先选择文件!");
				return ;
			}
			load();//弹出遮罩层
	      $("#handAdjustForm").ajaxSubmit({
	          type : 'POST',
	          dataType:"json",
	          url:'${app}/risk/uploadHandAdjust',
	          success : function(data) {
	          disLoad(); // 关闭遮罩层
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
	          			 $('#handAdjustTable').datagrid('reload');  
	                 },1500);
	          	}
	          }
	      });
	  } 
	//搜索
	function searchForm(id,status) {
		if(status == 1){
			datagrid.datagrid('load',serializeObject($('#'+id)));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}else if(status == 2){
			datagrid2.datagrid('load',serializeObject($('#'+id)));
			datagrid2.datagrid('clearSelections');
			datagrid2.datagrid('clearChecked');
		}
	}
	//清空
	function clearFrom(form,status){
		$("#"+form+" input[class^=easyui-textbox]").each(function(){
			$("#"+$(this).attr("id")).textbox('setValue','');
		});
		//清空datebox
		$("#"+form+" input[class^=easyui-datebox]").each(function(){
			$("#"+$(this).attr("id")).textbox('setValue','');
		});
		if(status == 1 ){
			datagrid.datagrid('load', {});
		}else if(status == 2){
			datagrid2.datagrid('load', {});
		}
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
	</script>
  </head>
  
  <body class="easyui-layout" >
	 <div  id="tabs" class="easyui-tabs"  style="width: 100%; height:auto;" > 
	    <div title="老系统逾期数据" style="background-color: #F4F4F4;" > 
	    
  				<form id="searchOverdueForm">
		  		<table border="0"  cellpadding="0" cellspacing="0">
		  		<tr height="20px"></tr>
		  			<tr>
		  			<td width="10px"></td>
						<td  class="tdR" width="3%">客户:</td>
						<td width="10px"></td>
						<td width="10%">
							<input id="clientName"  name="clientName" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
						<td  width="30px"></td>
						<td class="tdR" width="4%">营业部:</td>
						<td width="10px"></td>
						<td width="10%">
							<input  id="oldOrgName" name="oldOrgName"  class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
						<td  width="30px"></td>
						<td width="3%" class="tdR">时间:</td>
					<td>
						<input id="start_time" name="start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						&nbsp;至&nbsp;
						<input id="end_time" name="end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					</tr>
			</table>
		</form>
  		 <form id="overdueForm"  enctype="multipart/form-data">
			 <table>
			 	<tr>
			 	<td width="10px"></td>
			 		<td>
						<input type="file" name="file" id="overdueFileName" >
			 		</td>
			 		<td>
					    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadOldOverdue()">上传</a>
 						<a style="margin-left:95px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchForm('searchOverdueForm',1)">查询</a>
						<a  style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFrom('searchOverdueForm',1);">清空</a>
			 		</td>
			 	</tr>
			 </table>
			<input type="hidden" id="overdueName" name="overdueName" value="老系统逾期表.xlsx" >
	</form>
		<table id="oldOverdueTable" style="height:370px;" ></table>       
	 </div>
	    
	    <div title="老系统放款数据"  style="background-color: #F4F4F4;">
	       <form id="searchOldLoanForm">
		  		<table border="0"  cellpadding="0" cellspacing="0">
		  			<tr height="20px"></tr>
		  			<tr>
		  				<td width="10px"></td>
						<td  class="tdR" width="3%">客户:</td>
						<td width="10px"></td>
						<td width="10%">
							<input  id="oldLoanClientName" name="oldLoanClientName" maxlength="30" class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
							<td width="30px"></td>
						<td class="tdR" width="4%">营业部:</td>
							<td width="10px"></td>
						<td width="10%">
							<input id="oldLoanOrgName"  name="oldLoanOrgName"  class='easyui-textbox' style="width: 150px;height: 24px;"/>
						</td>
							<td width="30px"></td>
						<td width="3%" class="tdR">时间:</td>
					<td>
						<input id="oldLoan_start_time" name="oldLoan_start_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--起始时间--"/>
						&nbsp;至&nbsp;
						<input id="oldLoan_end_time" name="oldLoan_end_time" editable='false' maxlength="30" class='easyui-datebox' style="width: 150px;height: 24px;" data-options="required:false" placeholder ="--终止时间--"/>
					</td>
					</tr>
			</table>
		</form>
  		 <form id="loanForm"  enctype="multipart/form-data">
			 <table>
			 	<tr>
			 	<td width="10px"></td>
			 		<td>
						<input type="file" name="file" id="loanFileName" >
			 		</td>
			 		<td>
					    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadOldLoan()">上传</a>
						<a style="margin-left:95px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchForm('searchOldLoanForm',2)">查询</a>
						<a  style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFrom('searchOldLoanForm',2);">清空</a>
			 		</td>
			 	</tr>
			 </table>
			<input type="hidden"  name="loanName" value="老系统放款表.xlsx" >
	</form>
		<table id="oldLoanTable" style="height:370px;" ></table>       
	    </div> 
	    
	     
	    <div title="手工调整"  style="background-color: #F4F4F4;" >   
  		 <form id="handAdjustForm"  enctype="multipart/form-data">
			 <table>
			 <tr height="15px"></tr>
			 	<tr>
			 	<td width="10px"></td>
			 		<td>
						<input type="file" name="file" id="handAdjustFileName" >
			 		</td>
			 		<td width="50px"></td>
			 		<td>
					    <a  class="easyui-linkbutton" iconCls="icon-upload" onclick="uploadHandAdjust()">上传</a>
			 		</td>
			 	</tr>
			 </table>
			<input type="hidden"  name="handAdjustName" value="手工调整表.xlsx" >
	</form>
		<table id="handAdjustTable" style="height:410px;" ></table>
	    </div>  
</div>  
  </body>
</html>
