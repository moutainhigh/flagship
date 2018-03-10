<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>阈值设置列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script type="text/javascript">
	
	$(function() {
		//提交
		$("#saveButton").click(function() {	
			$.ajax({
				url:"${app}/financeThreshold/doUpdateMoreValue",
				data:$("form").serialize(),
				type:"post",
				dataType:"json",
				success:function(data){
					if(data){
						$.messager.show({
		                    title:'信息提示',
		                    msg:"修改成功！",
		                    timeout:5000,
		                    showType:'slide'
		                });
						//关闭form表单
						$("#formDiv").dialog("close");
					    //返回列表页面
					    setTimeout(function(){
						    location.href="${app}/financeThreshold/set";
               			  },1000);

					}else{
						$.messager.show({
		                    title:'信息提示',
		                    msg:"系统异常！",
		                    timeout:5000,
		                    showType:'slide'
		                });
					}
				}
			})
		})
		
		//取消
		$("#cancelButton").click(function() {	
			  location.href="${app}/financeThreshold/set";
		})
	})
	
	function onBlur1(){
		var value= $("#beginValueOne").val();
		document.getElementById("beginValueTwo").value=value;
	}
	function onBlur3(){
		var value= $("#beginValueTwo").val();
		document.getElementById("beginValueOne").value=value;
	}
	
	
	function onBlur2(){
		var value= $("#fourValueOne").val();
		document.getElementById("fourValueTwo").value=value;
	}
	function onBlur6(){
		var value= $("#fourValueTwo").val();
		document.getElementById("fourValueOne").value=value;
	}
	
	
	function onBlur4(){
		var value= $("#endValueOne").val();
		document.getElementById("endValueTwo").value=value;
	}
	function onBlur7(){
		var value= $("#endValueTwo").val();
		document.getElementById("endValueOne").value=value;
	}
	
	
	function onBlur5(){
		var value= $("#threeValueOne").val();
		document.getElementById("threeValueTwo").value=value;
	}
	
	function onBlur8(){
		var value= $("#threeValueTwo").val();
		document.getElementById("threeValueOne").value=value;
	}


	</script>
  </head>
  
 <body class="easyui-layout">
 
 <div id="formDiv"  class="easyui-dialog" closable="false" title="融资阈值设置" style="width:500px;height:250px;"   
        data-options="iconCls:'icon-save',modal:true,buttons:'#formButtons'">   
  
  <form  id="ff" style="margin: 30px;">
		
		<table>
			<tr>
				<td>
				 ${redMath}
				 <input  type="hidden" name="beginCodeName" value="${beginCodeName}" />
				<input  id="beginValueOne" name="beginValue" value="${beginValue}" size="3" onBlur="onBlur1()" />
				 ${redMathTwo}
				  <input  type="hidden" name="fourCodeName" value="${fourCodeName}" />
				<input  id="fourValueOne" name="fourValue" value="${fourValue}" size="3" onBlur="onBlur2()" />
				</td>
			</tr>
			<tr>
				<td>
				${yellowMathOne }
				<input   id="beginValueTwo" value="${beginValue}" size="3" onBlur="onBlur3()" />
				${yellowMathTwo}
				 <input  type="hidden" name="endCodeName" value="${endCodeName}" />
				<input id="endValueOne" name="endValue" size="3" value="${endValue}" onblur="onBlur4()" />
				${yellowMathThree}
				 <input  type="hidden" name="threeCodeName" value="${threeCodeName}" />
			    <input id="threeValueOne"  name="threeValue" size="3" value="${threeValue}" onblur="onBlur5()" />
			 	 ${yellowMathFour}
			   <input id="fourValueTwo"  size="3" value="${fourValue}" onblur="onBlur6()" />
				</td>
			</tr>
			<tr>
				<td>
				${blueMath}
				<input id="endValueTwo"  size="3" value="${endValue}" onBlur="onBlur7()"/>
				${blueMathTwo}
				<input id="threeValueTwo"  size="3" value="${threeValue}" onBlur="onBlur8()"/>
				</td>
			</tr>
		</table>
	</form>
  
     
</div>  
<div id="formButtons">
	<a id="saveButton" class="easyui-linkbutton"
		data-options="iconCls :'icon-edit'">提交</a>
	 <a
		class="easyui-linkbutton" data-options="iconCls :'icon-cancel'"
		id="cancelButton">取消</a>
</div>

  </body>
</html>
