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
		var datagrid;
		var datagrid2;
		$(function(){
			//理财 日指标列表
			datagrid = $('#datagrid').datagrid({
				url : '${app}/moneyThreshold/pageList',
				title : '理财日指标',
				toolbar:"#toolbars",
				border : false,
				singleSelect : true,
				columns : [[{
					field:'codeName',
					title : '指标名称',
					align: 'center',
					width : 120
					},
				{
					field : 'associatedData',
					title : '关联数据',
					width : 200
				},{
					field : 'redLineRules',
					title : '红线规则',
					width : 230
				},{
					field : 'yellowLineRules',
					title : '黄线规则',
					width : 290
				},{
					field : 'bludLineRules',
					title : '绿色规则',
					width : 230 
				},
				{
					field : '查看详情',
					title : '操作',
					width : 100,
					formatter:function(value,rowData,rowIndex){
							var beginCodeName = rowData.beginCodeName;
							var endCodeName = rowData.endCodeName;
							var beginValue = rowData.beginValue;
							var endValue = rowData.endValue;
							var redMath = rowData.redMath;
							var yellowMathOne = rowData.yellowMathOne;
							var yellowMathTwo = rowData.yellowMathTwo;
							var blueMath = rowData.blueMath;
							var retStr = "<a href='javascript:void(0);' onclick=edit('"+ beginCodeName + "','"+endCodeName+"','"+beginValue+"','"+endValue+"','"+redMath+"','"+yellowMathOne+"','"+yellowMathTwo+"','"+blueMath+"')>修改</a>";
							return retStr;
						}
				}]]
			});
			
			//理财 月指标列表
			datagrid2 = $('#datagrid2').datagrid({
				url : '${app}/moneyThreshold/pageMonthList',
				title : '理财月指标',
				toolbar:"#toolbars",
				border : false,
				singleSelect : false,
				columns : [[{
					field:'codeName',
					title : '指标名称',
					align: 'center',
					width : 120
					},
				{
					field : 'associatedData',
					title : '关联数据',
					width : 200
				},{
					field : 'redLineRules',
					title : '红线规则',
					width : 230
				},{
					field : 'yellowLineRules',
					title : '黄线规则',
					width : 290
				},{
					field : 'bludLineRules',
					title : '绿色规则',
					width : 230 
				},
				{
					field : '查看详情',
					title : '操作',
					width : 100,
					formatter:function(value,rowData,rowIndex){
						var beginCodeName = rowData.beginCodeName;
						var endCodeName = rowData.endCodeName;
						var beginValue = rowData.beginValue;
						var endValue = rowData.endValue;
						var redMath = rowData.redMath;
						var yellowMathOne = rowData.yellowMathOne;
						var yellowMathTwo = rowData.yellowMathTwo;
						var blueMath = rowData.blueMath;
						var retStr = "<a href='javascript:void(0);' onclick=edit('"+ beginCodeName + "','"+endCodeName+"','"+beginValue+"','"+endValue+"','"+redMath+"','"+yellowMathOne+"','"+yellowMathTwo+"','"+blueMath+"')>修改</a>";
						return retStr;
						}
				}]]
			});
			
		});
		
		//跳转到融资阈值修改页面
		function financingEdit(beginCodeName,endCodeName,threeCodeName,fourCodeName,beginValue,endValue,threeValue,fourValue,redMath,yellowMathOne,yellowMathTwo,blueMath,codeName,redMathTwo,yellowMathThree,yellowMathFour,blueMathTwo){
			//含有特殊字符(%) 进行转化
			var beginValue = encodeURIComponent(beginValue)
			var endValue = encodeURIComponent(endValue)
			var threeValue = encodeURIComponent(threeValue)
			var fourValue = encodeURIComponent(fourValue)
			if('批核率' == codeName || '批核件均' == codeName){
				location.href="${app}/financeThreshold/toUpdateMoreValue?beginCodeName="+beginCodeName+'&endCodeName='+endCodeName+'&threeCodeName='+threeCodeName+'&fourCodeName='+fourCodeName+'&redMath='+redMath+'&blueMathTwo='+blueMathTwo+'&yellowMathFour='+yellowMathFour+'&redMathTwo='+redMathTwo+'&yellowMathThree='+yellowMathThree+'&yellowMathOne='+yellowMathOne+'&yellowMathTwo='+yellowMathTwo+'&blueMath='+blueMath+'&endValue='+endValue+'&beginValue='+beginValue+'&threeValue='+threeValue+'&fourValue='+fourValue;
			}else{
				location.href="${app}/financeThreshold/toUpdate?beginCodeName="+beginCodeName+'&endCodeName='+endCodeName+'&threeCodeName='+threeCodeName+'&fourCodeName='+fourCodeName+'&redMath='+redMath+'&blueMathTwo='+blueMathTwo+'&yellowMathFour='+yellowMathFour+'&redMathTwo='+redMathTwo+'&yellowMathThree='+yellowMathThree+'&yellowMathOne='+yellowMathOne+'&yellowMathTwo='+yellowMathTwo+'&blueMath='+blueMath+'&endValue='+endValue+'&beginValue='+beginValue+'&threeValue='+threeValue+'&fourValue='+fourValue;
			}
			
		}
		
		//跳转到理财阈值修改页面
		function edit(beginCodeName,endCodeName,beginValue,endValue,redMath,yellowMathOne,yellowMathTwo,blueMath){
			location.href="${app}/moneyThreshold/toUpdate?beginCodeName="+beginCodeName+'&endCodeName='+endCodeName+'&redMath='+encodeURI(encodeURI(redMath))+'&yellowMathOne='+encodeURI(encodeURI(yellowMathOne))+'&yellowMathTwo='+encodeURI(encodeURI(yellowMathTwo))+'&blueMath='+encodeURI(encodeURI(blueMath))+'&endValue='+endValue+'&beginValue='+beginValue;
		}
	</script>
  </head>
  
  <body class="easyui-layout"  style="width: 100%;height: 100%;">
        <div >
	 		<table id="datagrid"></table>
	 	</div>
	 	<div >
	 		<table id="datagrid2"></table>
	 	</div>   

  </body>
</html>
