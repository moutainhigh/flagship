<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>模块管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
	  $(function(){
		  $(":checkbox").map(function(){
		        if($(this).val()==1){
		          $(this).attr("checked",true)
		        }
		     })
		     
		  var flag = '${flag}';
		  if('zw' == flag || '' == flag){
			  $('#tt').tabs('select','Tab1');
		  }else{ 
			  $('#tt').tabs('select','市场风险管理部');

		  }
		  
	  })

	function reset(formId){
		 document.getElementById(formId).reset(); 
	  }
	  
	  function submitForm(id,visible,sort,flag){
		    var status = true;
		  	var data;
		  	
		  	//得到所有id
			var ids ='';
		  	$("input[name='"+id+"']").each(function(){
				  ids +=$(this).val()+",";
		     });
		  	ids = ids.substring(0, ids.length-1);
		  	
		  	//得到所有是否选中状态
		  	 var visibles; 
		     var result = [];
		     $("input[name='"+visible+"']").each(function(){
		         var num = this.checked ? 1 : 0;
		         result.push(num);
		     });
		     
		     if(-1 == result.indexOf(1)){
		    	 status = false;
				 $.messager.alert("信息提示", "应至少显示一个模块!", "info",function(){});
		     }
		     visibles = result.join();
		    
		     //得到所有顺序
		     var sorts = '';
		 	$("input[name='"+sort+"']").each(function(){
		 		if("" == $(this).val()){
		 			$(this).val(-1)
		 		}
		 		sorts +=$(this).val()+",";
		     });
		 	sorts =sorts.substring(0, sorts.length-1);
		 	
		 	//判断顺序是否为空
	 	  	if(-1 != sorts.indexOf(-1)){
				 $.messager.alert("信息提示", "顺序必填!", "info",function(){});
		    	 status = false;
		     }
		 	data = ids+":"+visibles+":"+sorts;
		 		if(status){
		 			 $.ajax({
				          type: 'POST',
			              url:'${app}/risk/updatePage',
			              dataType: "json",            
			              data:{data:data},
			              success:function(data){
			            	  $.messager.show({
			           				title:'信息提示',
			           				msg:'修改成功',
			           				timeout:3000,
			           				showType:'slide'
			           			});
			            	   if(data){
			            		   $.messager.show({
				           				title:'信息提示',
				           				msg:'修改成功',
				           				timeout:2000,
				           				showType:'slide'
				           			});
			            		  }else{
			            		   $.messager.show({
			           				title:'信息提示',
			           				msg:'修改失败!',
			           				timeout:2000,
			           				showType:'slide'
			           			});
			            	   }
			            	   var url = "${app}/risk/toRiskModuleManage?flag="+flag;
		            		   setTimeout('jumpurl("'+url+'")',1000);
			                 }
			              })  
		 		}
		 		
	  }
	  
	    function jumpurl(url){  
			location.href=url;
		} 
	    
	</script>
  </head>
  
  <body style="background: white;">
  <div id="tt" class="easyui-tabs" style="width:600px;height:470px;">   
    <div id="Tab1" title="&nbsp;&nbsp;&nbsp;执委&nbsp; &nbsp;&nbsp;" style="padding:20px;">  
    	<form method="post" id="zwForm">
    	<table width='400' height='200' bgcolor='#E0ECFF' border='0' cellspacing='1'>
    			<tr>
    				<td>
    					<span style="font-weight:bold;font-size:12px">是否显示</span>
    				</td>
    				
    				<td>
    				 <span style="font-weight:bold;font-size:12px"> 显示顺序</span>
    				 </td>
    			</tr>
   			 <c:forEach items="${zwList}" var="m">  
           	 <tr>  
           	      
                 <td>
                 <input type="hidden" value="${m.id}" name="zwId" varStatus="id">
	                 <input id="${id.index}" name="zwVisible" type="checkbox" value="${m.visible}"/>
	                	 ${m.model}
                 </td> 
                 <td>
                 <input name="zwSort"  class="easyui-numberbox" type="text" sizeWidth="20" style="width: 40px;" value="${m.sorting}" data-options="min:1" required="true" missingMessage="顺序必须填写"/>
             </tr>  
    	</c:forEach> 
    		<tr>
	    			<td>
						<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:reset('zwForm');">取消</a>
					</td>
					<td>
						<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm('zwId','zwVisible','zwSort','zw');">保存</a>
					</td>
    		</tr> 
    	</table>
    	</form>
    </div>   
    <div id="Tab2" title="市场风险管理部"  style="overflow:auto;padding:20px;">   
        <form method="post" id="scForm">
	          <table width='400' height='200' bgcolor='#E0ECFF' border='0' cellspacing='1'>
	    			<tr>
	    				<td>
	    					<span style="font-weight:bold;font-size:12px">是否显示</span>
	    				</td>
	    				
	    				<td>
	    				 <span style="font-weight:bold;font-size:12px"> 显示顺序</span>
	    				 </td>
	    			</tr>
	    			<c:forEach items="${scList}" var="m">  
	           	 <tr>  
	                 <td>
	                  	<input type="hidden" value="${m.id}" name="scId">
		                 <input type="checkbox" name="scvisible"  value="${m.visible}"/>
		                 ${m.model}
	                 </td> 
	                 <td>
	                 <input name="scSort"  class="easyui-numberbox" type="text" sizeWidth="20" style="width: 40px;" value="${m.sorting}" data-options="min:1" required="true" missingMessage="顺序必须填写"/>
	                 </td> 
	             </tr>  
	    	</c:forEach> 
	    			<tr>
		    			<td>
							<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:reset('scForm');">取消</a>
						</td>
						<td>
							<a class="easyui-linkbutton" id="submitButton"  iconCls="icon-ok" onclick="submitForm('scId','scvisible','scSort','sc');">保存</a>
						</td>
	    			</tr>
	    	</table>
        </form>
    </div>   
</div>  
  </body>
</html>
