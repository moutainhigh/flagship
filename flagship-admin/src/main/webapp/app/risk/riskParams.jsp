<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>风控参数</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/common/header.jsp"%>
	<script type="text/javascript">
	$(function(){
		getSeparate();
		 $('#tt').tabs({
	        onSelect:function(title,index){
	        	if(0 == index){
	        		getSeparate();
	        	}else if(1 == index){
	        		getPorductWarList();
	        	}
	        }
	    }); 
		
	})
	
	/* 贷后分割 -- 查找最新*/
	function getSeparate() {
		 $.ajax({
				async:false,
				type:'post',
				url:"${app}/risk/getSeparate",
				dataType:'json',
				success:function(json){
					
					$('#cut').numberspinner('setValue', json.val);  
				}
			});
	};
	
	/* 贷后分割-- 修改 */
	
	/* 
		 if(null == separate || "" == separate){
			 $.messager.alert('警告','分割时间不能为空');   
		 } */
	function update(obj){
	 if(obj.value == "更改"){
		 obj.value="确认";
		 $('#cutUpdate').css('color','#5498d2');
		 $("#cut").numberbox('readonly',false);
	 }else{
		 var reg = /^[1-9]\d*$/;
		 var separate = $('#cut').numberspinner('getValue'); 
		 if(!reg.test(separate)){
			  $.messager.alert('警告','请输入正确的格式！！！'); 
		  }else{
			 $.ajax({
					async:false,
					type:'post',
					data:{
						'separate':separate,
					},
					url:"${app}/risk/insertSeparate",
					dataType:'json',
					success:function(){
						 obj.value="更改";
						 $("#cut").numberbox('readonly',true);
						 $('#cutUpdate').css('color','#45c87e');
						 $.messager.show({
								title:'信息提示',
								msg:'修改成功!',
								timeout:5000,
								showType:'slide'
							});
					}
				});
		 }
	 }
	};
	
	/* 产品预警 列表 */
	function getPorductWarList() {
		var result = '';
		 $.ajax({
				async:false,
				type:'post',
				url:"${app}/risk/getPorductWarList",
				dataType:'json',
				success:function(json){
					var porductWarList = json.list;
					result+='<table class="tableForm" border="1" width="520px" >';
					for(var i=0;i<porductWarList.length;i++){ 
						var warningValue = porductWarList[i].warningValue==undefined?'':porductWarList[i].warningValue.toFixed(2);
						result+='<tr>'+
						'<td width="238px" align="center" style="height:40px;background: #f6f8fc;border-color:#c1d3f3"><span id="p_'+i+'" style="margin-left:0px;font-size: 14px;">' +porductWarList[i].productName+ '</span></td>'+
						'<td width="142px" align="center" style="height:40px;background: #f6f8fc;border-color:#c1d3f3"><input type="text" id="'+i+'" maxlength="6" value="'+warningValue+'"style="text-align:center;width:40px;"'+
						'readonly="true";>%</td>'+
						'<td width="142px" align="center" style="height:40px;background: #f6f8fc;border-color:#c1d3f3"><input id="b_'+i+'" type="button" style="color: #45c87e" value="更改" onclick="upWaring(this)"></td>'+
						'</tr>'
					}
					result+='</table>';
					$('#productWaring').html('');
					$("#productWaring").append(result);
				}
			});
	};
	
	/* 产品预警-- 修改 */
	function upWaring(obj){
		
	 var id = obj.id.substring(2);
	  if(obj.value == "更改"){
		 obj.value="确认";
		 $('#'+obj.id).css('color','#5498d2');
		 $("#"+id).attr("readonly",false) ;
	 }else{
		 var waringVal = $("#"+id).val();
		  if(undefined== waringVal || ""==waringVal){
			  getPorductWarList();
		  }else{
			  if(waringVal<=0 || waringVal>100 || isNaN(waringVal)){
				  $.messager.alert('警告','请输入正确的格式！！！'); 
				 /*  getPorductWarList(); */
			  }else{
				  waringVal = waringVal/100;
					 waringVal = waringVal.toFixed(4)
					 var product = $("#p_"+id).text();
						 $.ajax({
								async:false,
								type:'post',
								data:{
									'warningValue':waringVal,
									'product':product,
								},
								url:"${app}/risk/insertWaringVal",
								dataType:'json',
								success:function(){
									 obj.value="更改";
									 $('#'+obj.id).css('color','#45c87e');
									 $("#"+id).attr("readonly",true);
									 $.messager.show({
											title:'信息提示',
											msg:'修改成功!',
											timeout:5000,
											showType:'slide'
										});
									 getPorductWarList();
								}
							});
			  };
			 	 
		  }
		  /*
		 waringVal = waringVal.substring(0,waringVal.length-1);
		 alert(waringVal) */
		
		 
	 }  
	};
	</script>
  </head>
  
  <body style="background: white;">
  <div id="tt" class="easyui-tabs" style="width:600px;height:470px;">   
    <div title="贷后分割" style="padding:20px;">  
    	<br><br> 
     	 <span style="margin-left:0px;font-size: 14px;">贷后分割时间点：</span> 
      	<input  id="cut"  value=""  class="easyui-numberspinner" style="width:55px;" readonly="true";>
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input id="cutUpdate" type="button" value="更改" style="color: #45c87e" onclick="update(this)"/>
    </div>   
    <div id="productWaring" title="产品预警"  style="overflow:auto;padding:20px;">   
        
    </div>   
</div>  
  </body>
</html>
