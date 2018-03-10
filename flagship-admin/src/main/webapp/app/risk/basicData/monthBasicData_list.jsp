<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>月数据</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
	   
		var datagrid;
		$(function(){
			
			//初始化月份
			$('#cm1_month_time').combobox({    
			    url:'${app}/risk/getCm1MonthTime',    
			    valueField:'dateValue',    
			    textField:'dateText',
			    panelHeight:'auto',
			    loadFilter:function(data){
					  data.push({'dateValue':'all','dateText':'全部月份'});   
			          return data;  
			      },
			      onLoadSuccess: function () {  //加载完成后,设置选中第一项  
	                    var data = $(this).combobox("getData");  
	                    $(this).combobox("select", data[0].dateValue);  
	                } ,
	              
	               //选择月份改变 批次 营业部 分割点
	              onSelect: function (o) {
	                		var newDate = o.dateValue;
	                	  //时间改变 更新营业部 、批次 、贷后分割点 
						   getBusinessName(newDate);
						   getCm1Batch(newDate);
						   findSeparate(newDate);
						   checkPermission(newDate); 
	                }  ,//初始化加载页面
	                onBeforeLoad: function (o) {
                		var newDate = o.dateValue;
					   getBusinessName('');
					   getCm1Batch('');
					   findSeparate('');
					   checkPermission(''); 
                }  
			});  

			 
				
				//加载事业部
				function getBusinessName(date){
					$('#cm1_business_unit_name').combobox({    
						url:'${app }/risk/getCm1ComboBoxName?status='+1, 
						 valueField:'business_unit_no',
						 textField:'business_unit_name',
						 panelHeight:'auto',
						 loadFilter:function(data){
							  data.push({'business_unit_no':'all','business_unit_name':'全部'});   
					          return data;  
					      }, 
						 onSelect: function(rec){ 
							 var business_unit_no = rec.business_unit_no;
							 getDistrictName(business_unit_no,date);
						}
					});
			}
			
			//加载大区
			function getDistrictName(business_unit_no,date){
				$('#cm1_district_name').combobox({ 
					 url:'getCm1ComboBoxName?status=2&business_unit_no='+business_unit_no,    
					 valueField:'district_no',
					 textField:'district_name',
					 panelHeight:'auto',
					 loadFilter:function(data){
						  data.push({'district_no':'all','district_name':'全部'});   
				          return data;  
				      },
					 onSelect: function(rec){ 
						var district_no= rec.district_no;
						 getOrgName(business_unit_no,district_no,date);
					} 
				});
			}
 			//加载营业部		
			function getOrgName(business_unit_no,district_no,date){
				$('#cm1_org_name').combobox({    
				 url:'getCm1ComboBoxName?status=3 &business_unit_no='+business_unit_no+'&district_no='+district_no+'&recordDate='+date,    
				 valueField:'org_no',
				 textField:'org_name',
				 panelHeight:'auto',
				 loadFilter:function(data){
					  data.push({'org_no':'all','org_name':'全部'});   
			          return data;  
			      }
			});
			}
			
			//加载批次
			function getCm1Batch(date){
				$('#cm1_batch').combobox({  
					 url:'getCm1ComboBoxName?status=4&recordDate='+date,    
					 valueField:'batch',
					 textField:'batch',
					 panelHeight:'auto',
					 loadFilter:function(data){
						  data.push({'batch':'all','batch':'全部'});   
				          return data;  
				      }
				});
			}
			//查询贷后分割点
			function findSeparate(date){
				$.ajax({
					type: "post",
		             url: "${app}/risk/findSeparate",
		             data: {recordDate:date},
		             dataType: "json",
		             success: function(data){
		            	  var json = [];
		            	  var obj1 = {};
		            	  obj1.id= '2';
		            	  obj1.text =data.separate+"前";
		            	  var obj2 = {};
		            	  obj2.id= '3';
		            	  obj2.text =data.separate+"后";
		            	  var obj3 = {};
		            	  obj3.id= '全部';
		            	  obj3.text ="整体";
		            	  json.push(obj1);
		            	  json.push(obj2);
		            	  json.push(obj3);
		            	  data = $.parseJSON(JSON.stringify(json));
		            	  $("#cm1_type").combobox("loadData", data);
		             }
				})
			}
		      
	        /*
	         *判断权限
	        */
	        function checkPermission(date){
	        	var no ="${result.no}";
		        var rank = "${result.rank}";
		        var pNo = "${result.parent_no}";
		      //执委
		        if("fengkongzw" == no){
		        	$("#cm1_type").combobox("setValue",'全部');
		        };
		        //贷后（只允许查询x期后）
		        if("fengkongdh" == no){
		        	$("#cm1_type").combobox("setValue",'3');
					$("#cm1_type").combobox('disable');
					//加载所有事业部
					getBusinessName('');
					
		        }
		        //分中心 和市场风险管理部（只允许查询x期前）
		         if(rank == 6 || "fengkongglb" == no){
		        	$("#cm1_type").combobox("setValue",'2');
					$("#cm1_type").combobox('disable');
					//加载所有事业部
					getBusinessName('');
		        } 
		        
		      	  //事业部
			        if(rank == 3){
						//通过所属事业部编号 加载大区
			        	getDistrictName(no,'');
			        	$("#cm1_business_unit_name").combobox('setValue',no);
						$("#cm1_business_unit_name").combobox('disable');
						//只能看x期前
			        	$("#cm1_type").combobox("setValue",'2');
						$("#cm1_type").combobox('disable');
			        }
		       
			      //大区
			        if(rank == 4){
			        	//通过所属事业部编号 加载大区
			        	getDistrictName(pNo,'');
			        	//通过所属事业部编号 大区编号 加载营业部
			        	getOrgName(pNo,no,date);
			        	//事业部回显 并取消可选
			        	$("#cm1_business_unit_name").combobox('setValue',pNo);
						$("#cm1_business_unit_name").combobox('disable');
						
						//大区回显 并取消可选
						$("#cm1_district_name").combobox("setValue",no);
						$("#cm1_district_name").combobox('disable');
						
						//只能看x期前
			        	$("#cm1_type").combobox("setValue",'2');
						$("#cm1_type").combobox('disable');

			        }
	        };
	        
	        
	        	 //时间
		        var cm1_time  =$("#cm1_month_time").combobox('getValue');
		        //批次
		        var cm1_batch = $("#cm1_batch").combobox('getValue');
		        //合同编号 
		        var cm1_contract_no = $("#cm1_contract_no").textbox('getValue');
		      //x期前（后）
		        var cm1_type =$("#cm1_type").combobox('getValue');
		        //事业部名称
		        var cm1_business_unit_name = $("#cm1_business_unit_name").combobox('getValue');
		        //大区名称
				var cm1_district_name = $("#cm1_district_name").combobox('getValue');

		        //营业部名称
				var cm1_org_name = $("#cm1_org_name").combobox('getValue');
				//是否逾期
				var cm1_is_overdue = $("#cm1_is_overdue").combobox('getValue');
				
		        datagrid = $('#datagrid').datagrid({
					url : '${app}/risk/getCm1OverDetailList',
					title : '',
					pagination : true,
					pageSize : <%=Constants.PAGE_SIZE%>,
					pageList : [10,20,30,40,50],
					singleSelect : true,
					queryParams: {
						"cm1_type":$("#cm1_type").combobox('getValue'),
						"cm1_time":cm1_time,
						"cm1_contract_no":cm1_contract_no,
						"cm1_batch":cm1_batch,
						"cm1_business_unit_name":cm1_business_unit_name,
						"cm1_district_name":cm1_district_name,
						"cm1_org_name":cm1_org_name,
						"cm1_is_overdue":cm1_is_overdue
					},
					border : false,
					idField : 'id',
					columns : [[
					  {
						field : 'contract_no',
						title : '合同编码',
						width : 200
					},{
						field : 'record_date',
						title : '月份',
						width : 100
					},{
						field : 'batch',
						title : '批次',
						width : 100
					},{
						field : 'is_overdue',
						title : '是否逾期',
						width : 100
					},{
						field : 'payed_money',
						title : '分子',
						width : 100
					},{
						field : 'repayment_money',
						title : '分母',
						width : 100
					},{
						field : 'business_unit_name',
						title : '事业部',
						width : 120
					},{
						field : 'district_name',
						title : '大区',
						width : 160,
						formatter: function(value,row,index){
							if(null == value){
								 return "";
							}
	                         return "<span title='"+ value +"'>"+value+"</span>";
	                 }
					},{
						field : 'org_name',
						title : '营业部',
						width : 120
					},{
						field : 'client_manager',
						title : '客户经理',
						width : 130,
						formatter: function(value,row,index){
							if(null == value){
								 return "";
							}
	                         return "<span title='"+ value +"'>"+value+"</span>";
	                 }
					},{
						field : 'client_name',
						title : '客户',
						width : 100
					},{
						field : 'name',
						title : '分中心',
						width : 120,
						formatter: function(value,row,index){
							if(null == value){
								 return "";
							}
	                         return "<span title='"+ value +"'>"+value+"</span>";
	                 }
					}
					]]
				});
	       
		});
		function theDayBeforeFormatter(date){  
		    var y = date.getFullYear();  
		    var m = date.getMonth()+1;  
		    var d = date.getDate()-1;  
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
		}
		 function myformatter(date){  
	            var y = date.getFullYear();  
	            var m = date.getMonth()+1;  
	            var d = date.getDate();  
	            return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);  
	        }  
		//cm1搜索
		function searchFun() {
			//事业部 赋值
			$("#cm1_business_unit_name_hidden").val($("#cm1_business_unit_name").combobox("getValue"));
			//X期前 （后）赋值
			$("#cm1_type_hidden").val($("#cm1_type").combobox('getValue'));
			//大区赋值
			$("#cm1_district_name_hidden").val($("#cm1_district_name").combobox('getValue'));
			datagrid.datagrid('load',serializeObject($("#searchForm")));
			datagrid.datagrid('clearSelections');
			datagrid.datagrid('clearChecked');
		}
		//cm1清空
		function clearFromFun(){
			location.href="${app}/risk/toMonthlyBasicDataList";
		}
		
		//导出Excel
        function doExport(){
        	//事业部 赋值
			$("#cm1_business_unit_name_hidden").val($("#cm1_business_unit_name").combobox("getValue"));
			//X期前 （后）赋值
			$("#cm1_type_hidden").val($("#cm1_type").combobox('getValue'));
			//大区赋值
			$("#cm1_district_name_hidden").val($("#cm1_district_name").combobox('getValue'));
			
            $("#searchForm").attr("action", "${app}/risk/riskBasicDataExport?isExport="+'month');
            $("#searchForm").attr("method", "POST");
            $("#searchForm").submit();
        }
	</script>
  </head>
  <body class="easyui-layout" >
	 <div  id="tabs" class="easyui-tabs"  style="width: 100%; height:auto;" > 
	    <div title="C-M1回款率" style="background-color: #F4F4F4;" > 
  				<form id="searchForm">
		  		<table border="0"  cellpadding="0" cellspacing="0" style="margin-top: 10px;">
		  			<tr>
		  	
						<td>
						<span style="margin-left: 43px;" ></span>   
						月份:
						<input id="cm1_month_time"  name="cm1_time" editable='false' maxlength="30" class='easyui-combobox'/>
					   
					  	<span style="margin-left: 50px;" ></span>   
					   	    批次:
					   	 <input id="cm1_batch" name="cm1_batch" class="easyui-combobox"  value="全部"  editable="false" data-options="valueField:'batch',textField:'batch'"/>
					    
					     <span style="margin-left: 45px;" ></span> 
					              合同编号:
					  	<input id="cm1_contract_no" name="cm1_contract_no" maxlength="30" class='easyui-textbox' />
						
						<!-- x期前（后） -->
						<span style="margin-left: 45px;" ></span>
						  <input  id="cm1_type" class="easyui-combobox"  data-options="valueField:'id', textField:'text', panelHeight:'auto',multiple:false"  editable="false">
						 <input type="hidden" id="cm1_type_hidden" name="cm1_type">
						 <a  style="margin-left: 30px;margin-bottom: 3px;" class="easyui-linkbutton" iconCls="icon-search" onclick="searchFun()">查询</a>
					</td>
				</tr>
				<tr>
					<td>
					                      事业部名称：
					     	<input id="cm1_business_unit_name"   class="easyui-combobox"  value="全部" editable="false"/>
					       	<input type="hidden" id="cm1_business_unit_name_hidden" name="cm1_business_unit_name" >
					     	<span style="margin-left: 20px;" ></span> 
					     	   大区名称：
							<input id="cm1_district_name"   value="全部" style="width:150px;"  class="easyui-combobox" >
						   <input type="hidden" id="cm1_district_name_hidden" name="cm1_district_name">
					      
					          
					       <span style="margin-left: 20px;" ></span> 
					                      营业部名称：
							<input id="cm1_org_name"  name="cm1_org_name" style="width:150px;"  value="全部"  class="easyui-combobox">
							<!-- 是否逾期 -->
							<span style="margin-left: 20px;" ></span> 
							<select id="cm1_is_overdue" name="cm1_is_overdue" class="easyui-combobox" panelHeight="auto" name="dept" style="width:150px;">   
							    <option value="全部">全部</option>   
							    <option value="0">逾期</option>
							    <option value='1'>未逾期</option>   
							</select>  	
							  <a style="margin-left: 30px;" class="easyui-linkbutton" iconCls="icon-clear" onclick="clearFromFun();">重置</a>
							 <sec:authorize ifAnyGranted='${ctrl.monthDataExport}'>
								<a style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-redo" onclick="doExport();">导出</a>
							</sec:authorize> 
					</td>
					<td>
					</td>
				</tr>
			</table>
		</form>
		<table id="datagrid" style="height:405px;" ></table>       
	 </div>
</div>  
  </body>
</html>
